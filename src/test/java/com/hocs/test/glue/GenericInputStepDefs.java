package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.forms.TestForm;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class GenericInputStepDefs {

    @Managed
    WebDriver driver;

    Page page;

    TestForm testForm;

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
                fail(pageName
                        + " is not defined within GenericInputStepDefs.fillAllMandatoryFields()");
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
            case "DRAFTING":
                break;
            case "MARK UP":
                break;
            case "QA":
                break;
            default:
                fail(stage + " is not definted in GenericInputStepDefs.iAmAtTheStage()");
        }
    }

    @When("^I set the date to \"([^\"]*)\"$")
    public void iSetTheDate(String date) {
        switch (date.toUpperCase()) {
            case "TODAY":
                page.getCurrentDay();
                page.getCurrentMonth();
                page.getCurrentYear();
                break;
            case "TOMORROW":
                break;
            case "YESTERDAY":
                break;
            default:
                fail("Please enter TODAY, TOMORROW or YESTERDAY");
        }
    }

    @When("I set a date of (\\d+) days time in the \"([^\"]*)\" field")
    public void iSetADateOfNDaysTimeInTheField(int days, String field) {
        String day = page.todayPlusNDaysGetDay(days);
        String month = page.todayPlusNDaysGetMonth(days);
        String year = page.todayPlusNDaysGetYear(days);

    }

    @Then("^an error message is displayed$")
    public void anErrorMessageIsDisplayed() {

    }

    @Then("^the case is moved to the \"([^\"]*)\" stage$")
    public void theCaseIsMovedToTheStage(String stage) {

    }

    @Then("^the file is downloaded$")
    public void theFileIsDownloaded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I cannot exit the case$")
    public void iCannotClickToExitTheCase() throws Throwable {

    }

    @When("^I attempt to reject a case without reason$")
    public void iAttemptToRejectACaseWithoutReason() {
        page.clickRejectButton();
        while (page.isElementDisplayed(page.nextButton)) {
            page.clickNextButton();
        }
    }

    @When("^I allocate the case$")
    public void iAllocateTheCase() {

    }
}
