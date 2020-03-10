package com.hocs.test.glue;

import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.workstacks.Workstacks;

import static config.Users.*;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;

import config.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.seleniumhq.jetty9.server.Authentication.User;

public class LoginStepDefs extends Page {

    Homepage homepage;

    Dashboard dashboard;

    LoginPage loginPage;

    Workstacks workstacks;

    @Given("I am user {string}")
    public void iLoginAs(String user) {
        loginPage.navigateToHocs();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            if (isElementDisplayed($(loginPage.usernameField))) {
                System.out.println("On fresh browser, beginning test..");
                loginPage.enterHocsLoginDetails(Users.valueOf(user));
                clickOn(loginPage.continueButton);
            } else {
                System.out.println("Session still active, continuing test from homepage");
                homepage.goHome();
            }
        } else {
            System.out.println("Browser not closed down correctly, attempting to continue test");
            homepage.goHome();
        }
    }

    @Given("I log in as the designated user")
    public void iLogInAsTheDesignatedUser() {
        String user = System.getProperty("user");

        if (user == null) {
            System.out.println("User parameter not set. Defaulting to 'EAMON'");
            user = "EAMON";
        }
        loginPage.navigateToHocs();
        if (isElementDisplayed($(loginPage.usernameField))) {
            if (isElementDisplayed($(loginPage.usernameField))) {
                System.out.println("On fresh browser, beginning test..");
                loginPage.enterHocsLoginDetails(Users.valueOf(user));
                clickOn(loginPage.continueButton);
            } else {
                System.out.println("Session still active, continuing test from homepage");
                homepage.goHome();
            }
        }
    }

    @Given("that I have navigated to the Management UI as the user {string}")
    public void iHaveNavigatedToTheManagementUI(String user) {
        loginPage.navigateToManagementUI();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            loginPage.enterHocsLoginDetails(Users.valueOf(user));
            clickOn(loginPage.continueButton);
        } else {
            System.out.println("Session still active, continuing test from homepage");
            dashboard.goToDashboard();
        }
    }

    @Given("that I have navigated to the Management UI as the designated user")
    public void iHaveNavigatedToTheManagementUIAsTheDesignatedUser() {
        String user = System.getProperty("user");
        if (user == null) {
            System.out.println("User parameter not set. Defaulting to 'EAMON'");
            user = "EAMON";
        }
        loginPage.navigateToManagementUI();
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            loginPage.enterHocsLoginDetails(Users.valueOf(user));
            clickOn(loginPage.continueButton);
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
        setSessionVariable("user").to(Users.valueOf(user));

        loginPage.enterHocsUsername(Users.valueOf(user).getUsername());
        loginPage.enterHocsPassword(Users.valueOf(user).getPassword());

        clickOn(loginPage.continueButton);
    }

    @And("I enter the password of user {string} in the password field")
    public void IEnterMyHocsPassword(String user) {
        loginPage.enterHocsPassword(Users.valueOf(user).getPassword());
    }

    @When("I enter invalid login credentials on the login screen")
    public void enterInvalidLoginCredentials() {
        loginPage.enterHocsLoginDetails(FAKE);
        clickOn(homepage.continueButton);
    }

    @Then("an error message should be displayed as the credentials are invalid")
    public void assertThatInvalidCredentialsErrorMessageIsShown() {
        loginPage.assertLoginErrorMessage();
    }

    @Then("I should be taken to the homepage")
    public void assertHomePage() {
        homepage.assertHomePageTitle();
    }

    @When("I logout as the initial user")
    public void selectLogoutButton() {
        clickOn(homepage.logoutButton);
    }

    @When("I enter the login credentials of another user {string} and click the login button")
    public void loginAsDifferentUserAfterLogout(String user) {
        loginPage.navigateToHocs();
        loginPage.enterHocsUsername(Users.valueOf(user).getUsername());
        loginPage.enterHocsPassword(Users.valueOf(user).getPassword());
        clickOn(loginPage.continueButton);
    }

    @And("I am prompted to log in")
    public void iAmPromptedToLogIn() {
        if (!isElementDisplayed($(loginPage.usernameField))) {
            clickOn(homepage.logoutButton);
            loginPage.navigateToHocs();
        }
    }

    @Then("I should be logged in as the user {string}")
    public void iShouldBeLoggedInAsTheUser(String user) {
        homepage.selectMyCases();
        workstacks.assertOwnerIs(Users.valueOf(user));
    }
}
