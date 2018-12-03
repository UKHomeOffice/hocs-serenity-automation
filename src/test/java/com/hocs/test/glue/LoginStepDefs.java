package com.hocs.test.glue;

import static config.Users.*;
import static config.Usernames.*;
import static config.Passwords.*;
import static junit.framework.TestCase.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;

import config.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs {

    @Managed
    private
    WebDriver driver;

    Homepage homepage;

    @Steps(shared = true)
    NavigationStepDefs navigationStepDefs;

    private LoginPage loginPage;

    private Page page;


    @Then("^An invalid username or password error is displayed$")
    public void invalidUsernamePasswordErrorDisplayed() {
        loginPage.assertInvalidUsernamePassword();
    }


    @Given("^I am user \"([^\"]*)\"")
    public void iLoginas(String user) {
        navigateToHocs();
        Serenity.setSessionVariable("user").to(user);
        switch (user.toUpperCase()) {
            case "DCU":
                enterHocsLoginDetails(DCU);
                break;
            case "TEST":
                enterHocsLoginDetails(TEST);
                break;
            case "DANNY":
                enterHocsLoginDetails(DANNY);
                break;
            default:
                fail(user + " is not defined with LoginStepDefs.iLoginAs()");
        }
        page.clickContinueButton();
    }


    @Given("^I am on the Home Office Correspondance Login Page")
    public void homeUrl() {
        navigateToHocs();
    }


    @When("^I enter my username \"([^\"]*)\" in the username field$")
    public void enterUname(String username) {
        Serenity.setSessionVariable("username").to(username);
        switch (username) {
            case "DANNY LARGE":
                enterHocsUsername(DANNYLARGE);
                break;
            case "DCU":
                enterHocsUsername(DCUSER);
                break;
            case "TESTER":
                enterHocsUsername(TESTER);
                break;
            default:
                fail(username + "could not be added to the field");
        }
    }



    @And("^I enter my password \"([^\"]*)\" in the password field$")
    public void enterHocsPassword(String password) {
        Serenity.setSessionVariable("password").to(password);
        switch (password){
            case "DANNY PASS":
                enterHocsPassword(DANNYPASS);
                break;
            case "DCU PASS":
                enterHocsPassword(DCUPASS);
                break;
            case "TESTER PASS":
                enterHocsPassword(TESTERPASS);
                break;
            default:
                fail(password + "could not be sent to the field");
        }
    }


    @And("^Select the login button$")
    public void navigateToHome() {
        navigateToHocs();
    }


    @Then("^I will hit the Home Page$")
    public void theCaseIsMovedToTheStage(String expectedStage) {
        homepage.assertCaseStageInWorkstacks(expectedStage, driver);
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


    private void navigateToHocs() {
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
                case "CI":
                    baseUrl = Environments.QA.getEnvironmentURL();
                    break;
                default:
                    fail("Environment must be set to LOCAL, DEV or QA");
            }
        }
        driver.get(baseUrl);
    }

}
