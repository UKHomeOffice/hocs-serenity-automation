package com.hocs.test.glue;

import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.Workstacks;

import static config.User.*;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.LoginPage;
import com.hocs.test.pages.Homepage;

import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.NoSuchElementException;

public class LoginStepDefs extends BasePage {

    Homepage homepage;

    Dashboard dashboard;

    LoginPage loginPage;

    Workstacks workstacks;

    CreateCase createCase;

    @Given("I log in to DECS as user {string}")
    public void iLoginAs(String user) {
        User targetUser = User.valueOf(user);
        loginPage.navigateToHocs();
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("Logging in as user " + targetUser.getUsername());
            loginPage.enterHocsLoginDetails(targetUser);
            safeClickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, checking active user matches target user");
            homepage.goHome();
            homepage.selectMyCases();
            try{
            workstacks.assertOwnerIs(targetUser);
                System.out.println("Active user matches target user, continuing test");
            } catch (AssertionError ae) {
                System.out.println("Active user does not match target user. Logging active user out of DECS");
                selectLogoutButton();
                iLoginAs(user);
            }
        }
        setCurrentUser(targetUser);
    }

    @Given("I log in to DECS")
    public void iLogInToDECS() {
        String user = System.getProperty("user");
        if (user == null) {
            System.out.println("User parameter not set. Defaulting to 'AUTOMATION_USER'");
            user = "AUTOMATION_USER";
        }
        User targetUser = User.valueOf(user);
        loginPage.navigateToHocs();
            if (isElementDisplayed($(loginPage.usernameField))) {
                System.out.println("On fresh browser, beginning test..");
                loginPage.enterHocsLoginDetails(targetUser);
                safeClickOn(loginPage.continueButton);
            } else {
                System.out.println("Session still active, continuing test from homepage");
                homepage.goHome();
            }
            setCurrentUser(targetUser);
    }

    @Given("that I have navigated to the Management UI as the user {string}")
    public void iHaveNavigatedToTheManagementUI(String user) {
        loginPage.navigateToManagementUI();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            loginPage.enterHocsLoginDetails(User.valueOf(user));
            safeClickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, continuing test from homepage");
            dashboard.goToDashboard();
        }
    }

    @Given("that I have navigated to the Management UI as the designated user")
    public void iHaveNavigatedToTheManagementUIAsTheDesignatedUser() {
        String user = System.getProperty("user");
        if (user == null) {
            System.out.println("User parameter not set. Defaulting to 'Automation User'");
            user = "AUTOMATION_USER";
        }
        loginPage.navigateToManagementUI();
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            loginPage.enterHocsLoginDetails(User.valueOf(user));
            safeClickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, continuing test from homepage");
            dashboard.goToDashboard();
        }
    }

    @Given("I am on the Home Office Correspondence Login Page")
    public void homeUrl() {
        loginPage.navigateToHocs();
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

    @When("I logout as the initial user")
    public void selectLogoutButton() {
        safeClickOn(homepage.logoutButton);
    }

    @When("I enter the login credentials of another user {string} and click the login button")
    public void loginAsDifferentUserAfterLogout(String user) {
        loginPage.navigateToHocs();
        loginPage.enterHocsUsername(User.valueOf(user).getUsername());
        loginPage.enterHocsPassword(User.valueOf(user).getPassword());
        safeClickOn(loginPage.continueButton);
    }

    @And("I am prompted to log in")
    public void iAmPromptedToLogIn() {
        if (!isElementDisplayed($(loginPage.usernameField))) {
            safeClickOn(homepage.logoutButton);
            loginPage.navigateToHocs();
        }
    }

    @Then("I should be logged in as the user {string}")
    public void iShouldBeLoggedInAsTheUser(String user) {
        homepage.selectMyCases();
        try {
            workstacks.assertOwnerIs(User.valueOf(user));
        } catch (AssertionError | NoSuchElementException e) {
            createCase.createCaseOfType("MIN");
            homepage.getAndClaimCurrentCase();
            goHome();
            homepage.selectMyCases();
            workstacks.assertOwnerIs(User.valueOf(user));
        }
    }
}
