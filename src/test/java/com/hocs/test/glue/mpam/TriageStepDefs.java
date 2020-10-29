package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.mpam.AccordionMPAM;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriageStepDefs extends BasePage {

    Triage triage;

    Creation creation;

    AccordionMPAM accordionMPAM;

    SummaryTab summaryTab;

    @And("I send the Triage case to {string}")
    public void sendTheTriageCaseTo(String stage) {
        switch (stage.toUpperCase()) {
            case "WORKFLOW MANAGER":
                triage.selectEscalateTriageCaseToWorkflowManager();
                triage.submitReasonToEscalateCase("test reason to escalate case");
                break;
            case "ON HOLD":
                triage.putTriageCaseOnHold();
                break;
            case "CONTRIBUTION REQUESTED":
                triage.selectContributionRequested();
                triage.enterContributionRequestedDeadlineDate(todayPlusMinusNDaysGetDay(1), todayPlusMinusNDaysGetMonth(1),
                        todayPlusMinusNDaysGetYear(1));
                triage.enterRequestDescription("test request contribution description");
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @And("I select an enquiry subject and continue")
    public void iSelectAnEnquirySubjectAndContinue() {
        triage.selectEnquirySubject("Other");
    }

    @And("I select an enquiry reason and continue")
    public void iSelectAnEnquiryReasonAndContinue() {
        triage.selectEnquiryReason("DNA");
    }

    @Then("the set enquiry subject and reason should be displayed on the MPAM Triage page")
    public void theSelectedEquirySubjectAndReasonShouldBeDisplayedOnTheMPAMTriagePage() {
        triage.assertSetEnquirySubject(sessionVariableCalled("enquirySubject"));
        triage.assertSetEnquiryReason(sessionVariableCalled("enquiryReason"));
    }

    @And("I take the Triage \\(On Hold) case off hold")
    public void iTakeTheTriageOnHoldCaseOffHold() {
        triage.takeTriageCaseOffHold();
    }

    @When("the user triggers the {string} error message at Triage by not entering the correct information")
    public void theUserTriggersTheErrorMessageAtTriageByNotEnteringTheCorrectInformation(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                triage.businessUnitDropdown.selectByIndex(1);
                safeClickOn(triage.confirmButton);
                break;
            case "BUSINESS UNIT REQUIRED":
                safeClickOn(triage.readyToDraftRadioButton);
                safeClickOn(triage.confirmButton);
                break;
            case "ENQUIRY SUBJECT REQUIRED":
                safeClickOn(triage.setEnquiryHypertext);
                safeClickOn(continueButton);
                break;
            case "ENQUIRY REASON REQUIRED":
                safeClickOn(triage.setEnquiryHypertext);
                triage.selectEnquirySubject("Other");
                safeClickOn(continueButton);
                break;
            case "CONTRIBUTION REQUEST DEADLINE REQUIRED":
                triage.selectContributionRequested();
                triage.enterRequestDescription("test");
                safeClickOn(confirmButton);
                break;
            case "CONTRIBUTION REQUEST DESCRIPTION REQUIRED":
                triage.selectContributionRequested();
                triage.enterContributionRequestedDeadlineDate(todayPlusMinusNDaysGetDay(1), todayPlusMinusNDaysGetMonth(1),
                        todayPlusMinusNDaysGetYear(1));
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} error message should be displayed at Triage")
    public void theErrorMessageShouldBeDisplayedAtTriage(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                triage.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "BUSINESS UNIT REQUIRED":
                triage.assertBusinessUnitRequiredErrorMessageDisplayed();
                break;
            case "ENQUIRY SUBJECT REQUIRED":
                triage.assertEnquirySubjectRequiredErrorMessageDisplayed();
                break;
            case "ENQUIRY REASON REQUIRED":
                triage.assertEnquiryReasonRequiredErrorMessageDisplayed();
                break;
            case "CONTRIBUTION REQUEST DEADLINE REQUIRED":
                triage.assertContributionRequestDeadlineRequiredErrorMessageDisplayed();
                break;
            case "CONTRIBUTION REQUEST DESCRIPTION REQUIRED":
                triage.assertContributionRequestDescriptionRequiredErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @And("I record the current options for Business Unit")
    public void iRecordTheCurrentOptionsForBusinessUnit() {
        triage.recordCurrentBusinessUnitOptions();
    }

    @Then("the options for Business Unit should change")
    public void theOptionsForBusinessUnitShouldChange() {
        triage.assertBusinessUnitOptionsChanged();
    }

    @When("I select {string} as the new Business Area of the case")
    public void iSelectAsTheNewBusinessAreaOfTheCase(String businessArea) {
        creation.selectBusinessArea(businessArea);
    }

    @When("I de-escalate the Triage \\(Escalated) case")
    public void iDescalateTheTriageEscalatedCase() {
        triage.deescalateTriageCase();
    }

    @And("I select to close the Triage \\(Escalated) case")
    public void iSelectToCloseTheTriageEscalatedCase() {
        triage.selectToCloseEscalatedCase();
    }

    @And("the contribution request deadline should be visible in the summary")
    public void theContributionRequestDeadlineShouldBeVisibleInTheSummary() {
        summaryTab.assertContributionRequestDeadlineVisible();
    }

    @When("I select the {string} action at Triage \\(Contribution Requested) stage")
    public void iSelectTheActionAtTriageContributionRequestedStage(String action) {
        switch (action.toUpperCase()) {
            case "ESCALATE TO WORKFLOW MANAGER":
                safeClickOn(triage.escalateToWorkflowManagerRadioButton);
                break;
            case "CONTRIBUTIONS RECEIVED":
                safeClickOn(triage.contributionsReceivedRadioButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
    }

    @And("I select to change the Business Area")
    public void iSelectToChangeTheBusinessArea() {
        accordionMPAM.openCaseDetailsAccordion();
        safeClickOn(triage.changeBusinessAreaLink);
    }

    @When("I change the Business Area of the case to {string}")
    public void iChangeTheBusinessAreaOfTheCaseTo(String businessArea) {
        iSelectToChangeTheBusinessArea();
        waitABit(500);
        creation.selectBusinessArea(businessArea);
        triage.selectSaveChangesAction();
        triage.setBusinessUnit();
        clickContinueButton();
    }

    @Then("the new Business Area should be selected in the accordion")
    public void theNewBusinessAreaShouldBeSelectedInTheAccordion() {
        accordionMPAM.openCaseDetailsAccordion();
        accordionMPAM.assertCorrectBusinessAreaSelected();
    }
}

