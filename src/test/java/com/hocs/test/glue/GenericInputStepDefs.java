package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class GenericInputStepDefs {

    @Managed
    WebDriver driver;

    private Page page;

    @Then("^\"([^\"]*)\" dropdown defaults to \"([^\"]*)\"$")
    public void dropdownDefaultsTo(String dropdown, String expectedText) {
        switch (dropdown.toUpperCase()) {
            case "":
                break;
            default:
                fail(dropdown + " is not defined within GenericInputStepDefs");
        }
    }

    @Given("^I click the \"([^\"]*)\" button$")
    public void iClickTheButton(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case "":
                break;
            default:
                fail(buttonName + " is not defined in GenericStepDefs.iClickTheButton()");
        }
    }

    @When("^I enter \"([^\"]*)\" in the \"([^\"]*)\" box")
    public void iEnterIntoTheBox(String input, String element) {
        switch (element.toUpperCase()) {
            case "":
                break;
            default:
                fail(element + " is not defined within GenericInputStepDefs");
        }
    }

    @Then("^The page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        page.assertTitle(title);
    }

}
