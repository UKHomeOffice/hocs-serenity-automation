package com.hocs.test.glue;

import static config.Environments.DEV;
import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.login.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.browsermob.fixtureservices.BrowserMobFixtureService;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    @Steps(shared = true)
    LoginStepDefs loginStepDefs;

    BrowserMobFixtureService browserMobFixtureService;

    Homepage homepage;

    LoginPage loginPage;

    Page page;

    @Given("^I navigate to the \"([^\"]*)\" Page$")
    public void iNavigateToThePage(String environment) {
        switch (environment.toUpperCase()) {
            case "HOCS DEV":
                driver.get(DEV.getEnvironmentURL());
                break;
            case "TEST FORM":
                homepage.clickTestFormLink();
                break;
            default:
                fail(environment + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" Page$")
    public void iAmTakenToThePage(String page) {
        switch (page.toUpperCase()) {
            case "HOCS HOME":
                homepage.pageTitleIsDisplayed();
                break;
            default:
                fail(page + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }
}
