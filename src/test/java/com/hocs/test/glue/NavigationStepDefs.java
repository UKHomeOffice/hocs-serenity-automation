package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SingleCase;
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

    SingleCase singleCase;

    @Given("^I navigate to the \"([^\"]*)\" Page$")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "TEST FORM":
                homepage.clickTestFormLink();
                break;
            case "CREATE SINGLE CASE":
                homepage.clickCreateSingleCase();
                break;
            default:
                fail(hocsPage + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" Page$")
    public void iAmTakenToThePage(String page) {
        switch (page.toUpperCase()) {
            case "HOCS HOME":
                homepage.pageTitleIsDisplayed();
                break;
            case "CREATE SINGLE CASE":
                singleCase.pageTitleIsDisplayed();
                break;
            default:
                fail(page + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }
}
