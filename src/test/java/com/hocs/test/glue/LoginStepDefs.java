package com.hocs.test.glue;

import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.workstacks.Workstacks;
import config.*;

import static config.Users.*;
import static config.Usernames.*;
import static config.Passwords.*;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;

public class LoginStepDefs extends Page {

    @Managed
    WebDriver driver;

    Homepage homepage;

    Dashboard dashboard;

    LoginPage loginPage;

    Workstacks workstacks;

    @Then("^An invalid username or password error is displayed$")
    public void invalidUsernamePasswordErrorDisplayed() {
        loginPage.assertInvalidUsernamePassword();
    }

    @Given("^I am user \"([^\"]*)\"")
    public void iLoginAs(String user) {
        navigateToHocs();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            switch (user.toUpperCase()) {
                case "DCU":
                    enterHocsLoginDetails(DCU);
                    break;
                case "TEST":
                    enterHocsLoginDetails(TEST);
                    break;
                case "CASEY":
                    enterHocsLoginDetails(CASEY);
                    break;
                case "EAMON":
                    enterHocsLoginDetails(EAMON);
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
        navigateToManagementUI();
        setSessionVariable("user").to(user);
        if (isElementDisplayed($(loginPage.usernameField))) {
            System.out.println("On fresh browser, beginning test..");
            switch (user.toUpperCase()) {
                case "DCU":
                    enterHocsLoginDetails(DCU);
                    break;
                case "TEST":
                    enterHocsLoginDetails(TEST);
                    break;
                case "CASEY":
                    enterHocsLoginDetails(CASEY);
                    break;
                case "EAMON":
                    enterHocsLoginDetails(EAMON);
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
        navigateToHocs();
    }


    @When("^I enter my login credentials \"([^\"]*)\" and click the login button$")
    public void enterCredentialsAndClickLogin(String credentials) {
        setSessionVariable("credentials").to(credentials);
        switch (credentials.toUpperCase()) {
            case "CASEY PROSSER":
                enterHocsUsername(CASEYPROSSER);
                enterHocsPassword(CASEYPASS);
                break;
            case "DCU":
                enterHocsUsername(DCUSER);
                enterHocsPassword(DCUPASS);
                break;
            case "TESTER":
                enterHocsUsername(TESTER);
                enterHocsPassword(TESTERPASS);
                break;
            case "EAMON DROKO":
                enterHocsUsername(EAMONDROKO);
                enterHocsPassword(EAMONPASS);
                break;
            default:
                pendingStep(credentials + " is not defined within " + getMethodName());
        }
        clickOn(loginPage.continueButton);
    }

    @And("^I enter my password \"([^\"]*)\" in the password field$")
    public void enterHocsPassword(String password) {
        setSessionVariable("password").to(password);
        switch (password) {
            case "CASEY PASS":
                enterHocsPassword(CASEYPASS);
                break;
            case "DCU PASS":
                enterHocsPassword(DCUPASS);
                break;
            case "TESTER PASS":
                enterHocsPassword(TESTERPASS);
                break;
            case "EAMON PASS":
                enterHocsPassword(EAMONPASS);
                break;
            default:
                pendingStep(password + " is not defined within " + getMethodName());
        }
    }

    @When("^I enter invalid login credentials on the login screen$")
    public void enterInvalidLoginCredentials() {
        enterHocsLoginDetails(FAKE);
        clickOn(homepage.continueButton);
    }

    @Then("^an error message should be displayed as the credentials are invalid$")
    public void assertThatInvalidCredentialsErrorMessageIsShown() {
        loginPage.assertLoginErrorMessage();
    }

    @And("^Select the login button$")
    public void selectLoginButton() {
        clickOn(loginPage.continueButton);
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
        navigateToHocs();
        setSessionVariable("credentials").to(credentials);
        switch (credentials.toUpperCase()) {
            case "CASEY PROSSER":
                enterHocsUsername(CASEYPROSSER);
                enterHocsPassword(CASEYPASS);
                break;
            case "DCU":
                enterHocsUsername(DCUSER);
                enterHocsPassword(DCUPASS);
                break;
            case "TESTER":
                enterHocsUsername(TESTER);
                enterHocsPassword(TESTERPASS);
                break;
            case "EAMON DROKO":
                enterHocsUsername(EAMONDROKO);
                enterHocsPassword(EAMONPASS);
                break;
            default:
                pendingStep(credentials + " is not defined within " + getMethodName());
        }
        clickOn(loginPage.continueButton);
    }

    private void enterHocsLoginDetails(Users user) {
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
    }

    private void enterHocsUsername(Usernames username) {
        loginPage.enterUsername(username.getUsername());
    }

    private void enterHocsPassword(Passwords password) {
        loginPage.enterPassword(password.getPassword());
    }

    protected void navigateToHocs() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environments.QA.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environments.DEV.getEnvironmentURL();
                    break;
                case "LOCAL":
                    baseUrl = Environments.LOCAL.getEnvironmentURL() + Services.HOCS.getPort();
                    break;
                case "QA":
                    baseUrl = Environments.QA.getEnvironmentURL();
                    break;
                case "DEMO":
                    baseUrl = Environments.DEMO.getEnvironmentURL();
                default:
                    pendingStep(env + " is not defined within " + getMethodName());
            }
        }
        getDriver().get(baseUrl);
    }

    protected void navigateToManagementUI() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environments.MANAGEMENTUIQA.getEnvironmentURL();
        } else {
            baseUrl = Environments.MANAGEMENTUIDEV.getEnvironmentURL();
        }
        getDriver().get(baseUrl);
    }

    @And("^I am prompted to log in$")
    public void iAmPromptedToLogIn() {
        if (!isElementDisplayed($(loginPage.usernameField))) {
            clickOn(homepage.logoutButton);
            navigateToHocs();
        }
    }

    @Then("^I should be logged in as the \"([^\"]*)\" user$")
    public void iShouldBeLoggedInAsTheUser(String owner) {
        homepage.selectMyCases();
        switch (owner.toUpperCase()) {
            case "CASEY PROSSER":
                workstacks.assertOwnerIs(CASEYPROSSER.getUsername());
                break;
            case "DCU":
                workstacks.assertOwnerIs(DCU.getUsername());
                break;
            case "TESTER":
                workstacks.assertOwnerIs(TESTER.getUsername());
                break;
            case "EAMON DROKO":
                workstacks.assertOwnerIs(EAMONDROKO.getUsername());
                break;
            default:
                pendingStep(owner + " is not defined within " + getMethodName());
        }
    }
}
