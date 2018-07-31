package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    Page page;

    Homepage homepage;

    @Given("^I navigate to the \"([^\"]*)\" Page$")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "TEST FORM":
                homepage.clickTestFormLink();
                break;
            default:
                fail(hocsPage + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" Page$")
    public void iAmTakenToThePage(String page) throws InterruptedException {
        switch (page.toUpperCase()) {
            case "HOCS HOME":
                homepage.pageTitleIsDisplayed();
                break;
            default:
                fail(page + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }
}
