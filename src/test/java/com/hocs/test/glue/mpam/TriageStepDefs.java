package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.mpam.AccordionMPAM;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.MPAMMultipleContributions;
import com.hocs.test.pages.mpam.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriageStepDefs extends BasePage {

    Triage triage;

    Creation creation;

    AccordionMPAM accordionMPAM;

    MPAMMultipleContributions MPAMMultipleContributions;

    SummaryTab summaryTab;

    Dashboard dashboard;

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
                triage.selectSpecificEnquirySubject("Person Specific");
                triage.selectSpecificEnquiryReason("Allowed appeal enquiry update");
                triage.setBusinessUnit();
                MPAMMultipleContributions.sendMPAMCaseToContributionRequest();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
    }

    @When("I select to set the Enquiry Subject and Reason")
    public void clickTheLink() {
        safeClickOn(triage.setEnquiryHypertext);
    }

    @And("I select the {string} enquiry subject and continue")
    public void iSelectAnEnquirySubjectAndContinue(String enquirySubject) {
        triage.selectSpecificEnquirySubject(enquirySubject);
    }

    @And("I select the {string} enquiry reason and continue")
    public void iSelectAnEnquiryReasonAndContinue(String enquiryReason) {
        triage.selectSpecificEnquiryReason(enquiryReason);
    }

    @And("I select the {string} compliance measure")
    public void iSelectTheComplianceMeasure(String complianceMeasure) {
        triage.selectComplianceMeasure(complianceMeasure);
    }

    @And("I enter details of the compliance measures and continue")
    public void iEnterDetailsOfTheComplianceMeasures() {
        triage.enterComplianceMeasureDetails();

    }

    @Then("the summary tab should display {string} as a compliance measure")
    public void theSummaryTabShouldDisplayAsAComplianceMeasure(String complianceMeasure) {
        summaryTab.selectSummaryTab();
        summaryTab.assertComplianceMeasures(complianceMeasure);
    }

    @Then("the summary tab should display the details entered for EU National Compliance Measures")
    public void theSummaryTabShouldDisplayTheDetailsEnteredForEUNationalComplianceMeasures() {
        summaryTab.selectSummaryTab();
        summaryTab.assertComplianceMeasureDetails();
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
                clickConfirmButton();
                break;
            case "BUSINESS UNIT REQUIRED":
                safeClickOn(triage.readyToDraftRadioButton);
                clickConfirmButton();
                break;
            case "ENQUIRY SUBJECT REQUIRED":
                safeClickOn(triage.setEnquiryHypertext);
                clickContinueButton();
                break;
            case "ENQUIRY REASON REQUIRED":
                safeClickOn(triage.setEnquiryHypertext);
                triage.selectEnquirySubject("Other");
                clickContinueButton();
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
        creation.selectASpecificBusinessArea(businessArea);
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
                triage.selectSpecificEnquirySubject("Person Specific");
                triage.selectSpecificEnquiryReason("Allowed appeal enquiry update");
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
        creation.selectASpecificBusinessArea(businessArea);
        triage.setBusinessUnit();
        clickContinueButton();
    }

    @Then("the new Business Area should be selected in the accordion")
    public void theNewBusinessAreaShouldBeSelectedInTheAccordion() {
        accordionMPAM.openCaseDetailsAccordion();
        accordionMPAM.assertCorrectBusinessAreaSelected();
    }

    @And("I select the appropriate Enquiry Subject")
    public void iSelectTheAppropriateEnquirySubject() {
        triage.selectSpecificEnquirySubject(sessionVariableCalled("enquirySubject"));
    }

    @Then("I should be able to select the new Enquiry Reason")
    public void iShouldBeAbleToSelectTheNewEnquiryReason() {
        triage.selectSpecificEnquiryReason(sessionVariableCalled("enquiryReasonName"));
    }

    @And("I select the business unit")
    public void iSelectTheBusinessUnit() {
        triage.selectRandomOptionFromDropdownWithHeading("Business unit");
    }

    @And("I select the action {string}")
    public void iSelectTheAction(String action) {
        triage.selectSpecificRadioButton("Ready to draft");
        clickConfirmButton();
    }
}

