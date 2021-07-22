package com.hocs.test.glue.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.ukvi.AccordionMPAM;
import com.hocs.test.pages.ukvi.Creation;
import com.hocs.test.pages.ukvi.MPAMMultipleContributions;
import com.hocs.test.pages.ukvi.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriageStepDefs extends BasePage {

    Triage triage;

    Creation creation;

    AccordionMPAM accordionMPAM;

    MPAMMultipleContributions MPAMMultipleContributions;

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
            case "CONTRIBUTIONS REQUESTED":
                safeClickOn(triage.setEnquiryHypertext);
                triage.selectEnquirySubject("Person Specific");
                triage.selectEnquiryReason("Allowed appeal enquiry update");
                triage.setBusinessUnit();
                MPAMMultipleContributions.sendMPAMCaseToContributionRequest();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        waitForDashboard();
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
    public void theSelectedEnquirySubjectAndReasonShouldBeDisplayedOnTheMPAMTriagePage() {
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

    @And("I select the {string} action at the Triage-Escalated stage")
    public void iSelectTheActionAtTriageEscalatedStage(String action) {
        switch (action.toUpperCase()) {
            case "DE-ESCALATE":
                triage.deescalateTriageCase();
                break;
            case "CLOSE CASE":
                triage.selectToCloseEscalatedCase();
                break;
            case "CONTRIBUTIONS REQUESTED":
                safeClickOn(triage.setEnquiryHypertext);
                triage.selectEnquirySubject("Person Specific");
                triage.selectEnquiryReason("Allowed appeal enquiry update");
                triage.setBusinessUnit();
                MPAMMultipleContributions.sendMPAMCaseToContributionRequest();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I select to change the Business Area")
    public void iSelectToChangeTheBusinessArea() {
        accordionMPAM.openCaseDetailsAccordion();
        safeClickOn(triage.changeBusinessAreaLink);
    }

    @When("I change the Business Area of the case to {string}")
    public void iChangeTheBusinessAreaOfTheCaseTo(String businessArea) {
        iSelectToChangeTheBusinessArea();
        waitABit(1000);
        creation.selectBusinessArea(businessArea);
        triage.setBusinessUnit();
        clickContinueButton();
    }

    @Then("the new Business Area should be selected in the accordion")
    public void theNewBusinessAreaShouldBeSelectedInTheAccordion() {
        accordionMPAM.openCaseDetailsAccordion();
        accordionMPAM.assertCorrectBusinessAreaSelected();
    }
}

