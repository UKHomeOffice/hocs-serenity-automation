package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.dcu.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    Correspondents correspondents;

    SummaryTab summaryTab;

    @When("I enter an invalid date")
    public void enterAnInvalidDate() {
        dataInput.overwriteCorrespondenceReceivedDate("29/02/2019");
    }

    @When("I fill all mandatory fields on the Data Input page")
    public void fillMandatoryFields() {
        dataInput.fillAllMandatoryCorrespondenceFields();
    }

    @And("I select {string} for Home Secretary interest and complete the data input stage")
    public void completeDataInputStageWithSpecifiedHomeSecInterest(String interest) {
        setSessionVariable("homeSecInterest").to(interest);
        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        dataInput.selectACorrespondenceReceivedChannel();
        dataInput.selectASpecificCopyToNoTenOption("No");
        dataInput.selectASpecificHomeSecInterestOption(interest);
        if (minCase()) {
            dataInput.selectAHomeSecReplyOption();
        }
        clickContinueButton();
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    @Then("the Home Secretary interest decision should match the one displayed in the summary tab")
    public void assertHomeSecInterestInputMatchesSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.assertHomeSecInterestMatchesDecisionAtDataInput();
    }

    @And("I complete the Data Input stage, selecting that the case is a potential Home Secretary Reply case")
    public void iCompleteTheDataInputStageSelectingThatTheCaseIsAPotentialHomeSecretaryReplyCase() {
        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        dataInput.selectACorrespondenceReceivedChannel();
        dataInput.selectASpecificCopyToNoTenOption("No");
        dataInput.selectAHomeSecInterestOption();
        dataInput.selectASpecificHomeSecReplyOption("Yes");
        clickContinueButton();
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    @When("I submit an invalid {string} date")
    public void iSubmitAnInvalidDate(String dateField) {
        switch (dateField.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED":
                dataInput.overwriteCorrespondenceReceivedDate(getDatePlusMinusNDaysAgo(1));
                break;
            case "CORRESPONDENCE SENT":
                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(1));
                break;
            default:
                pendingStep(dateField + " is not defined within " + getMethodName());
        }
        clickContinueButton();
    }

    @But("I do not enter a {string} date")
    public void iDoNotEnterA(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case "CORRESPONDENCE RECEIVED":
                dataInput.clearDateCorrespondenceReceived();
                break;
            case "CORRESPONDENCE SENT":
                dataInput.clearDateCorrespondenceSent();
                break;
            default:
                pendingStep(fieldName + " is not defined within " + getMethodName());
        }
        clickContinueButton();
    }

    @Then("{string} error message is displayed")
    public void errorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "INVALID DATE":
                assertExpectedErrorMessageIsDisplayed("must be a date in the past");
                break;
            case "CORRESPONDENCE RECEIVED":
                assertExpectedErrorMessageIsDisplayed("When was the correspondence received? is required");
                break;
            case "CORRESPONDENCE SENT":
                assertExpectedErrorMessageIsDisplayed("When was the correspondence sent? is required");
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }
}
