package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
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

    @And("I send the Triage case to {string}")
    public void escalateToWorkflowManager(String stage) {
        switch (stage.toUpperCase()) {
            case "WORKFLOW MANAGER":
                triage.escalateTriageCaseToWorkflowManager();
                break;
            case "ON HOLD":
                triage.putTriageCaseOnHold();
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
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @And("I record the current options for Business Area")
    public void iRecordTheCurrentOptionsForBusinessArea() {
        triage.recordCurrentBusinessAreaOptions();
    }

    @Then("the options for Business Area should change")
    public void theOptionsForBusinessAreaShouldChange() {
        triage.assertBusinessAreaOptionsChanged();
    }

    @When("I change the Business Unit of the case to {string}")
    public void iChangeTheBusinessUnitOfTheCase(String businessArea) {
        safeClickOn(accordionMPAM.caseDetailsAccordionButton);
        creation.selectBusinessArea(businessArea);
    }

    @When("I de-escalate the Triage \\(Escalated) case")
    public void iDescalateTheTriageEscalatedCase() {
        triage.deescalateTriageCase();
    }
}