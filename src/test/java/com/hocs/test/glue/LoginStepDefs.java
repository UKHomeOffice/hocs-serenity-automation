package com.hocs.test.glue;

import static config.User.FAKE;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.LoginPage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.managementUI.Dashboard;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs extends BasePage {

    Homepage homepage;

    Dashboard dashboard;

    LoginPage loginPage;

    Workstacks workstacks;

    CreateCase createCase;

    SummaryTab summaryTab;

    // Use in features where all scenarios require the same user

    @Given("I log in to {string} as user {string}")
    public void iLoginToAs(String platform, String user) {
        User targetUser = User.valueOf(user);
        if (System.getProperty("user") != null) {
            targetUser = User.valueOf(System.getProperty("user"));
            System.out.println("User value overriden to: " + targetUser.getUsername());
        }
        loginPage.navigateToPlatform(platform);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("Logging in as user: " + targetUser.getUsername());
            loginPage.enterHocsLoginDetails(targetUser);
            safeClickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, continuing test from dashboard");
            homepage.goHome();
        }
        setCurrentUser(targetUser);
    }

    // Use in features where different scenarios require different users

    @Given("I switch to user {string}")
    public void iSwitchToUser(String user) {
        User targetUser = User.valueOf(user);
        loginPage.navigateToDECS();
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("Logging in as user " + targetUser.getUsername());
            loginPage.enterHocsLoginDetails(targetUser);
            safeClickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, logging out");
            selectLogoutButton();
            iLoginToAs("DECS", user);
        }
        setCurrentUser(targetUser);
    }

    // Use for scenarios where the active session might have the correct user, but this needs to be checked

    @Given("I ensure I am logged into DECS as user {string}")
    public void iEnsureIAmLoggedIntoDecsAsUser(String user) {
        User targetUser = User.valueOf(user);
        loginPage.navigateToDECS();
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("Logging in as user " + targetUser.getUsername());
            loginPage.enterHocsLoginDetails(targetUser);
            safeClickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, checking active user matches target user");
            homepage.goHome();
            homepage.selectMyCases();
            if (workstacks.getTotalOfCases() == 0) {
                createCase.createCaseOfType("ANY");
                homepage.getAndClaimCurrentCase();
                homepage.goHome();
                safeClickOn(homepage.myCases);
            }
            try {
                workstacks.assertOwnerIs(targetUser);
                System.out.println("Active user matches target user, continuing test");
            } catch (AssertionError ae) {
                System.out.println("Active user does not match target user. Logging active user out of DECS");
                selectLogoutButton();
                iLoginToAs("DECS", user);
            }
        }
        setCurrentUser(targetUser);
    }

    @Given("I am on the Home Office Correspondence Login Page")
    public void homeUrl() {
        loginPage.navigateToDECS();
    }


    @When("I enter the login credentials for user {string} and click the login button")
    public void enterCredentialsAndClickLogin(String user) {
        setSessionVariable("user").to(User.valueOf(user));
        loginPage.enterHocsUsername(User.valueOf(user).getUsername());
        loginPage.enterHocsPassword(User.valueOf(user).getPassword());
        safeClickOn(loginPage.continueButton);
    }

    @And("I enter the password of user {string} in the password field")
    public void IEnterMyHocsPassword(String user) {
        loginPage.enterHocsPassword(User.valueOf(user).getPassword());
    }

    @When("I enter invalid login credentials on the login screen")
    public void enterInvalidLoginCredentials() {
        loginPage.enterHocsLoginDetails(FAKE);
        safeClickOn(homepage.continueButton);
    }

    @Then("an error message should be displayed as the credentials are invalid")
    public void assertThatInvalidCredentialsErrorMessageIsShown() {
        loginPage.assertLoginErrorMessage();
    }

    @Then("I should be taken to the homepage")
    public void assertHomePage() {
        homepage.assertOnHomePage();
    }

    @When("I logout of the application")
    public void selectLogoutButton() {
        safeClickOn(homepage.logoutButton);
    }

    @When("I enter the login credentials of another user {string} and click the login button")
    public void loginAsDifferentUserAfterLogout(String user) {
        loginPage.navigateToDECS();
        loginPage.enterHocsUsername(User.valueOf(user).getUsername());
        loginPage.enterHocsPassword(User.valueOf(user).getPassword());
        safeClickOn(loginPage.continueButton);
    }

    @And("I am prompted to log in")
    public void iAmPromptedToLogIn() {
        if (!isElementDisplayed($(loginPage.usernameField))) {
            safeClickOn(homepage.logoutButton);
            loginPage.navigateToDECS();
        }
    }

    @Then("I should be logged in as the user {string}")
    public void iShouldBeLoggedInAsTheUser(String user) {
        User inputUser = User.valueOf(user);
        homepage.goHome();
        safeClickOn(homepage.myCases);
        if (workstacks.getTotalOfCases() == 0) {
            createCase.createCaseOfType("ANY");
            homepage.getAndClaimCurrentCase();
            homepage.goHome();
            safeClickOn(homepage.myCases);
        }
        safeClickOn(workstacks.topCaseReferenceHypertext);
        summaryTab.selectSummaryTab();
        summaryTab.assertAllocatedUserIs(inputUser);
    }
}
