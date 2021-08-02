package com.hocs.test.glue.decs;

import static config.User.COMP_USER;
import static config.User.DCU_USER;
import static config.User.DECS_USER;
import static config.User.FAKE;
import static config.User.UKVI_USER;
import static config.User.WCS_USER;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.LoginPage;
import com.hocs.test.pages.MuiLoginPage;
import com.hocs.test.pages.Workstacks;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;

public class LoginStepDefs extends BasePage {

    Dashboard dashboard;

    LoginPage loginPage;

    MuiLoginPage muiLoginPage;

    Workstacks workstacks;

    CreateCase createCase;

    User targetUser;

    @Given("I am logged into {string} as user {string}")
    public void iAmLoggedIntoAs(String platform, String user) {
        targetUser = User.valueOf(user);
        checkForOverrideUser();
        login(platform);
        if (loginPage.onLoginPage()) {
            loginPage.logInAsUser(targetUser);
        } else {
            System.out.println("Session still active, checking active user matches target user");
            goToDashboard(platform);
            if (!loggedInAsTargetUser()) {
                System.out.println("Active user does not match target user, logging out");
                selectLogoutButton();
                loginPage.logInAsUser(targetUser);
            }
        }
        setCurrentUser(targetUser);
    }

    private void login(String platform) {
        if (platform.equalsIgnoreCase("DECS")) {
            loginPage.open();
        } else if (platform.equalsIgnoreCase("MANAGEMENT UI")) {
            muiLoginPage.open();

        } else {
            System.err.println("Platform : " + platform + "Not Found");
        }
    }

    @Given("I switch to user {string}")
    public void iSwitchToUser(String user) {
        targetUser = User.valueOf(user);
        loginPage.open();
        if (!loginPage.onLoginPage()) {
            System.out.println("Session still active, logging out");
            selectLogoutButton();
        }
        loginPage.logInAsUser(targetUser);
        setCurrentUser(targetUser);
    }

    @Given("I am on the Home Office Correspondence Login Page")
    public void homeUrl() {
        loginPage.open();
    }

    @When("I enter the login credentials for user {string} and click the login button")
    public void enterCredentialsAndClickLogin(String user) {
        setSessionVariable("user").to(User.valueOf(user));
        loginPage.enterUsername(User.valueOf(user).getUsername());
        loginPage.enterPassword(User.valueOf(user).getPassword());
        safeClickOn(loginPage.continueButton);
    }

    @And("I enter the password of user {string} in the password field")
    public void IEnterMyHocsPassword(String user) {
        loginPage.enterPassword(User.valueOf(user).getPassword());
    }

    @When("I enter invalid login credentials on the login screen")
    public void enterInvalidLoginCredentials() {
        loginPage.enterLoginDetails(FAKE);
        safeClickOn(loginPage.continueButton);
    }

    @Then("an error message should be displayed as the credentials are invalid")
    public void assertThatInvalidCredentialsErrorMessageIsShown() {
        loginPage.assertLoginErrorMessage();
    }

    @Then("I should be taken to the homepage")
    public void assertHomePage() {
        dashboard.assertAtDashboard();
    }

    @When("I logout of the application")
    public void selectLogoutButton() {
        safeClickOn(dashboard.logoutButton);
    }

    @When("I enter the login credentials of another user {string} and click the login button")
    public void loginAsDifferentUserAfterLogout(String user) {
        loginPage.open();
        loginPage.enterUsername(User.valueOf(user).getUsername());
        loginPage.enterPassword(User.valueOf(user).getPassword());
        safeClickOn(loginPage.continueButton);
    }

    @And("I am prompted to log in")
    public void iAmPromptedToLogIn() {
        if (!isElementDisplayed($(loginPage.usernameField))) {
            safeClickOn(dashboard.logoutButton);
            loginPage.open();
        }
    }

    @Then("I should be logged in as the user {string}")
    public void iShouldBeLoggedInAsTheUser(String user) {
        targetUser = User.valueOf(user);
        assert loggedInAsTargetUser();
    }

    private void checkForOverrideUser() {
        if (System.getProperty("user") != null) {
            targetUser = User.valueOf(System.getProperty("user"));
            System.out.println("User value overriden to: " + targetUser.getUsername());
        }
    }

    private boolean loggedInAsTargetUser() {
        boolean targetUserLoggedIn = false;
        if (targetUser == DCU_USER | targetUser == UKVI_USER | targetUser == DECS_USER | targetUser == WCS_USER | targetUser == COMP_USER) {
            if (dashboard.checkTargetUserIsLoggedInUsingVisibleTeams(targetUser)) {
                targetUserLoggedIn = true;
            } else {
                safeClickOn(dashboard.createSingleCase);
                targetUserLoggedIn = createCase.checkTargetUserIsLoggedInUsingCaseOptions(targetUser);
                goToDECSDashboard();
            }
        } else {
            goToDECSDashboard();
            dashboard.selectMyCases();
            if (workstacks.getTotalOfCases() == 0) {
                createCase.createCaseOfType("ANY");
                dashboard.getAndClaimCurrentCase();
                goToDECSDashboard();
                dashboard.selectMyCases();
            }
            targetUserLoggedIn = workstacks.owningUserIs(targetUser);
        }
        return targetUserLoggedIn;
    }
}
