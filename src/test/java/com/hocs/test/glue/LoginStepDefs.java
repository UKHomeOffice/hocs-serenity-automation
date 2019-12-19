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

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefs extends Page {

    Homepage homepage;

    Dashboard dashboard;

    LoginPage loginPage;

    Workstacks workstacks;

    @Given("^I am user \"([^\"]*)\"")
    public void iLoginAs(String user) {
        loginPage.navigateToHocs();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            switch (user.toUpperCase()) {
                case "DCU":
                    loginPage.enterHocsLoginDetails(DCU);
                    break;
                case "TEST":
                    loginPage.enterHocsLoginDetails(TEST);
                    break;
                case "CASEY":
                    loginPage.enterHocsLoginDetails(CASEY);
                    break;
                case "EAMON":
                    loginPage.enterHocsLoginDetails(EAMON);
                    break;
                case "PROD":
                    loginPage.enterHocsLoginDetails(PROD);
                    break;
                default:
                    pendingStep(user + " is not defined within " + getMethodName());
            }
            clickOn(loginPage.continueButton);
        } else {
            System.out.println("Browser not closed down correctly, attempting to continue test");
            homepage.goHome();
        }
    }

    @Given("^I log in as the designated user$")
    public void iLogInAsTheDesignatedUser() {
        String user = System.getProperty("user");

        if (user == null) {
            System.out.println("User parameter not set. Defaulting to 'EAMON'");
            user = "EAMON";
        }

        loginPage.navigateToHocs();

        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            switch (user.toUpperCase()) {
                case "DCU":
                    loginPage.enterHocsLoginDetails(DCU);
                    break;
                case "TEST":
                    loginPage.enterHocsLoginDetails(TEST);
                    break;
                case "CASEY":
                    loginPage.enterHocsLoginDetails(CASEY);
                    break;
                case "EAMON":
                    loginPage.enterHocsLoginDetails(EAMON);
                    break;
                case "PROD":
                    loginPage.enterHocsLoginDetails(PROD);
                    break;
                default:
                    pendingStep(user + " is not defined within " + getMethodName());
            }
            clickOn(loginPage.continueButton);
        } else {
            System.out.println("Browser not closed down correctly, attempting to continue test");
            homepage.goHome();
        }
    }

    @Given("^that I have navigated to the Management UI as the user \"([^\"]*)\"$")
    public void iHaveNavigatedToTheManagementUI(String user) {
        loginPage.navigateToManagementUI();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            switch (user.toUpperCase()) {
                case "DCU":
                    loginPage.enterHocsLoginDetails(DCU);
                    break;
                case "TEST":
                    loginPage.enterHocsLoginDetails(TEST);
                    break;
                case "CASEY":
                    loginPage.enterHocsLoginDetails(CASEY);
                    break;
                case "EAMON":
                    loginPage.enterHocsLoginDetails(EAMON);
                    break;
                case "PROD":
                    loginPage.enterHocsLoginDetails(PROD);
                    break;
                default:
                    pendingStep(user + " is not defined within " + getMethodName());
            }
            clickOn(loginPage.continueButton);
        } else {
            System.out.println("Browser not closed down correctly, attempting to continue test");
            dashboard.goToDashboard();
        }
    }

    @Given("^that I have navigated to the Management UI as the designated user$")
    public void iHaveNavigatedToTheManagementUIAsTheDesignatedUser() {

        String user = System.getProperty("user");

        if (user == null) {
            System.out.println("User parameter not set. Defaulting to 'EAMON'");
            user = "EAMON";
        }

        loginPage.navigateToManagementUI();

        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            switch (user.toUpperCase()) {
                case "DCU":
                    loginPage.enterHocsLoginDetails(DCU);
                    break;
                case "TEST":
                    loginPage.enterHocsLoginDetails(TEST);
                    break;
                case "CASEY":
                    loginPage.enterHocsLoginDetails(CASEY);
                    break;
                case "EAMON":
                    loginPage.enterHocsLoginDetails(EAMON);
                    break;
                case "PROD":
                    loginPage.enterHocsLoginDetails(PROD);
                    break;
                default:
                    pendingStep(user + " is not defined within " + getMethodName());
            }
            clickOn(loginPage.continueButton);
        } else {
            System.out.println("Browser not closed down correctly, attempting to continue test");
            dashboard.goToDashboard();
        }
    }

    @Given("^I am on the Home Office Correspondence Login Page")
    public void homeUrl() {
        loginPage.navigateToHocs();
    }


    @When("^I enter my login credentials \"([^\"]*)\" and click the login button$")
    public void enterCredentialsAndClickLogin(String credentials) {
        setSessionVariable("credentials").to(credentials);
        switch (credentials.toUpperCase()) {
            case "CASEY PROSSER":
                loginPage.enterHocsUsername(CASEY.getUsername());
                loginPage.enterHocsPassword(CASEY.getPassword());
                break;
            case "DCU":
                loginPage.enterHocsUsername(DCU.getUsername());
                loginPage.enterHocsPassword(DCU.getPassword());
                break;
            case "TESTER":
                loginPage.enterHocsUsername(TEST.getUsername());
                loginPage.enterHocsPassword(TEST.getPassword());
                break;
            case "EAMON DROKO":
                loginPage.enterHocsUsername(EAMON.getUsername());
                loginPage.enterHocsPassword(EAMON.getPassword());
                break;
            default:
                pendingStep(credentials + " is not defined within " + getMethodName());
        }
        clickOn(loginPage.continueButton);
    }

    @And("^I enter my password \"([^\"]*)\" in the password field$")
    public void IEnterMyHocsPassword(String password) {
        setSessionVariable("password").to(password);
        switch (password) {
            case "CASEY PASS":
                loginPage.enterHocsPassword(CASEY.getPassword());
                break;
            case "DCU PASS":
                loginPage.enterHocsPassword(DCU.getPassword());
                break;
            case "TESTER PASS":
                loginPage.enterHocsPassword(TEST.getPassword());
                break;
            case "EAMON PASS":
                loginPage.enterHocsPassword(EAMON.getPassword());
                break;
            default:
                pendingStep(password + " is not defined within " + getMethodName());
        }
    }

    @When("^I enter invalid login credentials on the login screen$")
    public void enterInvalidLoginCredentials() {
        loginPage.enterHocsLoginDetails(FAKE);
        clickOn(homepage.continueButton);
    }

    @Then("^an error message should be displayed as the credentials are invalid$")
    public void assertThatInvalidCredentialsErrorMessageIsShown() {
        loginPage.assertLoginErrorMessage();
    }

    @Then("^I should be taken to the homepage$")
    public void assertHomePage() {
        homepage.assertHomePageTitle();
    }

    @When("^I logout as the initial user$")
    public void selectLogoutButton() {
        clickOn(homepage.logoutButton);
    }

    @When("^I enter the login credentials of another user \"([^\"]*)\" and click the login button$")
    public void loginAsDifferentUserAfterLogout(String credentials) {
        loginPage.navigateToHocs();
        setSessionVariable("credentials").to(credentials);
        switch (credentials.toUpperCase()) {
            case "CASEY PROSSER":
                loginPage.enterHocsUsername(CASEY.getUsername());
                loginPage.enterHocsPassword(CASEY.getPassword());
                break;
            case "DCU":
                loginPage.enterHocsUsername(DCU.getUsername());
                loginPage.enterHocsPassword(DCU.getPassword());
                break;
            case "TESTER":
                loginPage.enterHocsUsername(TEST.getUsername());
                loginPage.enterHocsPassword(TEST.getPassword());
                break;
            case "EAMON DROKO":
                loginPage.enterHocsUsername(EAMON.getUsername());
                loginPage.enterHocsPassword(EAMON.getPassword());
                break;
            default:
                pendingStep(credentials + " is not defined within " + getMethodName());
        }
        clickOn(loginPage.continueButton);
    }

    @And("^I am prompted to log in$")
    public void iAmPromptedToLogIn() {
        if (!isElementDisplayed($(loginPage.usernameField))) {
            clickOn(homepage.logoutButton);
            loginPage.navigateToHocs();
        }
    }

    @Then("^I should be logged in as the \"([^\"]*)\" user$")
    public void iShouldBeLoggedInAsTheUser(String owner) {
        homepage.selectMyCases();
        switch (owner.toUpperCase()) {
            case "CASEY PROSSER":
                workstacks.assertOwnerIs(CASEY.getUsername());
                break;
            case "DCU":
                workstacks.assertOwnerIs(DCU.getUsername());
                break;
            case "TESTER":
                workstacks.assertOwnerIs(TEST.getUsername());
                break;
            case "EAMON DROKO":
                workstacks.assertOwnerIs(EAMON.getUsername());
                break;
            default:
                pendingStep(owner + " is not defined within " + getMethodName());
        }
    }
}
