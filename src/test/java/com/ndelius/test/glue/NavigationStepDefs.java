package com.ndelius.test.glue;

import static config.Environments.LOCAL;
import static config.Services.HOCS;
import static org.junit.Assert.fail;

import com.ndelius.test.pages.Page;
import com.ndelius.test.pages.homepage.Homepage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    Homepage homepage;

    Page page;

    @Given("^I navigate to the \"([^\"]*)\" Page$")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "HOCS DEMO FORM":
                page.modifyHeadersAndNavigateTo(LOCAL.getEnvironmentURL() + HOCS.getPort());
                break;
            default:
                fail(hocsPage + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" Page$")
    public void iAmTakenToThePage(String page) {
        switch (page.toUpperCase()) {
            case "HOCS DEMO FORM":
                //driver.get(LOCAL.getEnvironmentURL() + HOCS.getPort() + "/action/test/form");
                break;
            default:
                fail(page + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }
}
