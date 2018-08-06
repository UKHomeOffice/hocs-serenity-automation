package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.And;
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

    @When("^I click the \"([^\"]*)\" link")
    public void clickLink(String name) {
        switch (name.toUpperCase()) {
            case "UPDATE":
                page.clickUpdateLink();
                break;
            case "NEW":
                page.clickNewLink();
                break;
            case "DELETE":
                page.clickDeleteLink();
                break;
            case "VIEW":
                page.clickViewLink();
                break;
            default:
                fail(name + " is not defined within GenericStepDefs.clickLink()");
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

    @When("^I fill all mandatory fields on the \"([^\"]*)\" page with valid data")
    public void fillMandatoryFields(String pageName) {
        switch (pageName.toUpperCase()) {
            case "":
                break;
            default:
                fail(pageName + " is not defined within GenericInputStepDefs.fillAllMandatoryFields()");
        }
    }


    @Then("^The page title is \"([^\"]*)\"$")
    public void pageTitleIs(String title) {
        page.assertTitle(title);
    }

    @Then("^I see the \"([^\"]*)\" message$")
    public void iSeeTheMessage(String message) {
        switch (message.toUpperCase()) {
            case "DOCUMENT PENDING":
                break;
            case "DOCUMENT UPLOAD FAILED":
                break;
            case "NO DOCUMENTS":
                break;
            default:
                fail(message + " is not defined in GenericStepDefs.iSeeTheMessage()");
        }
    }

    @When("^I enter an invalid date$")
    public void iEnterAnInvalidDate() {
        page.enterInvalidDate();
    }

    @And("^I am at the \"([^\"]*)\" stage$")
    public void iAmAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "MARK UP":
                break;
                default:
                    fail(stage + " is not definted in GenericInputStepDefs.iAmAtTheStage()");
        }
    }

}
