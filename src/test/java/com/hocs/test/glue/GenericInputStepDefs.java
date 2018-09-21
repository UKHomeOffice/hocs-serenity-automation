package com.hocs.test.glue;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.forms.TestForm;
import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.WebDriver;

public class GenericInputStepDefs {

    @Managed
    WebDriver driver;

    private DataInput dataInput;

    private Page page;

    TestForm testForm;

    private Homepage homepage;

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
            case "CONTINUE":
                page.clickContinueButton();
                break;
            case "NEXT":
                page.clickNextButton();
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
            case "DATA INPUT":
                dataInput.fillAllMandatoryFields();
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

    @Then("^\"([^\"]*)\" error message is displayed$")
    public void errorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "INVALID DATE":
                page.assertErrorMessageText("");
                break;
            case "CORRESPONDENCE RECEIVED":
                page.assertErrorMessageText("When was the correspondence received? is required");
                break;
            case "CORRESPONDENCE SENT":
                page.assertErrorMessageText("When was the correspondence sent? is required");
                break;
            default:
                fail(errorMessage + " is not defined in GenericStepDefs.errorMessageIsDisplayed");
        }

    }

    @When("^I enter an invalid \"([^\"]*)\" date$")
    public void iEnterAnInvalidDate(String dateField) {
        switch (dateField.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED":
                dataInput.invalidCorrespondenceReceivedDate();
                break;
            case "CORRESPONDENCE SENT":
                dataInput.invalidCorrespondenceSentDate();
                break;
            default:
                fail(dateField + " is not defined in GenericStepDefs.iEnterAnInvalidDate");
        }
    }

    @And("^I am at the \"([^\"]*)\" stage$")
    public void iAmAtTheStage(String stage) {
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                homepage.clickFirstDataInputAllocate();
                break;
            case "DATA INPUT QA":
                try {
                    homepage.clickFirstDataInputQaAllocate();
                } catch (ElementShouldBeEnabledException e) {
                    homepage.clickFirstDataInputQaCasework();
                }
                break;
            case "MARKUP":
                homepage.clickFirstMarkupAllocate();
                break;
            default:
                fail(stage + " is not defined in GenericStepDefs.iAmAtTheStage");
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
        page.errorMessageIsDisplayed();
    }

    @Then("^the case is moved to the \"([^\"]*)\" stage$")
    public void theCaseIsMovedToTheStage(String expectedStage) {
        homepage.assertCaseStageInWorkstacks(expectedStage);

    }


    @Then("^the file is downloaded$")
    public void theFileIsDownloaded() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I cannot exit the case$")
    public void iCannotClickToExitTheCase() {

    }

    @When("^I attempt to reject a case without reason$")
    public void iAttemptToRejectACaseWithoutReason() {
        page.clickRejectButton();
        while (page.isElementDisplayed(page.nextButton)) {
            page.clickNextButton();
        }
    }

    @When("^I \"([^\"]*)\" the case$")
    public void iActionTheCase(String action) {
        setSessionVariable("caseId").to(page.getCaseId());
        switch (action.toUpperCase()) {
            case "ACCEPT":
                page.clickAcceptButton();
                page.clickContinueButton();
                break;
            case "ALLOCATE":
                break;
            case "DISPATCH":
                break;
            case "REJECT":
                page.clickRejectButton();
                page.clickContinueButton();
                page.enterRejectionNotes();
                break;
            default:
                fail(action + " is not defined in GenericStepDefs.iActionTheCase");
        }

    }

    @But("^I do not enter a \"([^\"]*)\"$")
    public void iDoNotEnterA(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED DATE":
                dataInput.clearDateCorrespondenceReceived();
                break;
            case "CORRESPONDENCE SENT DATE":
                dataInput.clearDateCorrespondenceSent();
                break;
            default:
                fail(fieldName + " is not defined in GenericStepDefs.iDoNotEnterA");
        }
    }

    @Then("^the case is completed$")
    public void theCaseIsCompleted() {
        homepage.assertCaseIsComplete();
    }

    @Then("^\"([^\"]*)\" link is displayed$")
    public void linkIsDisplayed(String linkText) {
        switch (linkText.toUpperCase()) {
            case "ADD A CORRESPONDENT":
                dataInput.addACorrespondentLinkIsDisplayed();
                break;
            default:
                fail(linkText + " is not defined in GenericStepDefs.linkIsDisplayed");
        }
    }

    @And("^I \"([^\"]*)\" the rejection note$")
    public void iTheRejectionNote(String rejection) {
        switch (rejection.toUpperCase()) {
            case "COMPLETE":
                page.enterRejectionNotes();
                page.clickFinishButton();
                break;
            case "DO NOT COMPLETE":
                page.clickFinishButton();
                break;
            default:
                fail(rejection + " is not defined in GenericStepDefs.iTheRejectionNote");
        }

    }
}

