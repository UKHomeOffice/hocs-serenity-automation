package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsTriageAndInvestigation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ComplaintsTriageAndInvestigationStepDefs extends BasePage {


    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    @And("I accept the case at {string} Triage stage")
    public void iAcceptTheCaseAtServiceTriageStage(String complaintType) {
        complaintsTriageAndInvestigation.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriageAndInvestigation.enterDateOfAcceptance();
        }
        waitABit(500);
        clickTheButton("Continue");
    }

    @And("I accept the case at Triage stage")
    public void iAcceptTheCaseAtTriageStage() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        waitABit(500);
        clickTheButton("Continue");
    }
    @And("I select to Transfer the complaint")
    public void iSelectToTransferTheComplaint() {
        complaintsTriageAndInvestigation.selectTransferComplaint();
    }

    @And("I enter a reason for {string} transfer and continue")
    public void iEnterAReasonForTransferAndContinue(String transferTo) {
        complaintsTriageAndInvestigation.enterTransferReason();
        switch (transferTo) {
            case "CCH":
                complaintsTriageAndInvestigation.selectTransferToCCH();
                break;
            case "IE Detention":
                complaintsTriageAndInvestigation.selectTransferToIEDET();
                break;
            case "PSU":
                complaintsTriageAndInvestigation.selectTransferToPSU();
                break;
        }
    }

    @And("I submit details on the Triage Capture Reason page")
    public void iSubmitDetailsOnTheTriageCaptureReasonPage() {
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
    }

    @And("I enter information on the Triage Details page")
    public void iEnterInformationOnTheTriageDetailsPage() {
        complaintsTriageAndInvestigation.enterDetailsOnBFTriageDetailsPage();
    }

    @And("I send the case to drafting")
    public void iSendTheCaseToDrafting() {
        complaintsTriageAndInvestigation.selectReadyForDrafting();
    }

    @And("I escalate the case to WFM at Triage/Investigation stage")
    public void iEscalateTheCaseToWFM() {
        complaintsTriageAndInvestigation.escalateCaseToWFM();
    }

    @And("I select to complete the case at Triage")
    public void iSelectToCompleteTheCase() {
        complaintsTriageAndInvestigation.selectCompleteTheCase();
    }

    @And("I enter a completion note at Service Triage")
    public void iEnterACompletionNote() {
        complaintsTriageAndInvestigation.enterCompletionReason();
    }

    @And("I confirm I want to close the case at Service Triage")
    public void iConfirmIWantToCloseTheCaseAtServiceTriage() {
        complaintsTriageAndInvestigation.selectPermanentlyCloseCase("Yes");
    }

    @And("I select that a Letter of Authority is required")
    public void iSelectThatALOAIsRequired() {
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        complaintsTriageAndInvestigation.selectSpecificOptionForIsLOARequired("Yes");
        clickTheButton("Continue");
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        complaintsTriageAndInvestigation.enterLoAReceivedDetails();
    }

    @And("the overdue contribution request should be highlighted")
    public void highlightingCheckAtServiceTriage() {
        complaintsTriageAndInvestigation.assertOverdueContributionRequestIsHighlighted();
    }

    @And("I accept the (previous )Claim Category selection")
    public void iAcceptThePreviousClaimCategorySelection() {
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
    }

    @And("I accept the (previous )Case Details selection")
    public void iAcceptThePreviousSeveritySelection() {
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
    }

    @And("I select the {string} action for an IEDET case at the Triage stage")
    public void iSelectTheActionForAnIEDETCaseAtTriage(String action) {
        switch (action.toUpperCase()) {
            case "TRANSFERRED TO THIRD PARTY SUPPLIER":
                complaintsTriageAndInvestigation.selectTransferredToThirdPartySupplier();
                break;
            case "TRANSFERRED TO IE DETENTION COMPLIANCE TEAM":
                complaintsTriageAndInvestigation.selectTransferredToIEDetentionComplianceTeam();
                break;
            case "NO FURTHER CONSIDERATION":
                complaintsTriageAndInvestigation.selectNoFurtherConsideration();
                break;
            case "TRANSFER COMPLAINT TO CCH":
                complaintsTriageAndInvestigation.selectTransferComplaintToCCH();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickTheButton("Continue");
    }

    @And("I enter details on PSU Reference page")
    public void iEnterDetailsOnPSUReferencePage() {
        complaintsTriageAndInvestigation.enterPSUReference();
        clickTheButton("Continue");
    }

    @And("I select {string} as additional information on Triage Case Details page")
    public void iSelectAsAdditionalInformationOnTriageCaseDetailsPage(String additionalInformation) {
        complaintsTriageAndInvestigation.selectAdditionalInformation(additionalInformation);
        clickTheButton("Continue");
    }

    @And("I select a Close Reason")
    public void iSelectACloseReason() {
        complaintsTriageAndInvestigation.selectACloseReason();
    }

    @And("I enter a reason for closing the case")
    public void iEnterAReasonForClosingTheCase() {
        complaintsTriageAndInvestigation.enterCompletionReason();
        clickTheButton("Complete case");
    }

    @And("I do not accept the case at Triage")
    public void iDoNotAcceptTheCaseAtTriage() {
        complaintsTriageAndInvestigation.selectTransferOfflineAndCloseTheCase();
    }

    @And("I enter reason for Triage transfer and close the case")
    public void iEnterReasonForTriageTransferAndCloseTheCase() {
        complaintsTriageAndInvestigation.enterTransferReason();
        clickTheButton("Close Case");
    }

    @Then("I should be able to select the new COMP Enquiry Reason")
    public void iShouldBeAbleToSelectTheNewEnquiryReason() {
        complaintsTriageAndInvestigation.selectEnquiryReason(sessionVariableCalled("enquiryReasonName"));
    }

    @Then("I should be able to select the new COMP Business Area")
    public void iShouldBeAbleToSelectTheNewCOMPBusinessArea() {
        String directorate = sessionVariableCalled("directorate");
        selectSpecificOptionFromDropdownWithHeading(directorate, "Directorate");
        String businessArea = sessionVariableCalled("businessAreaName");
        selectSpecificOptionFromDropdownWithHeading(businessArea, "Business Area");
    }

    @And("I select {string} for has the complainant requested a payment")
    public void iSelectForHasComplainantRequestedAPayment(String yesNo) {
        complaintsTriageAndInvestigation.selectComplainantHasRequestedPayment(yesNo);
    }

    @And("I enter {string} as the amount requested by the complainant")
    public void iEnterAsTheAmountRequestedByTheComplainant(String amount) {
        complaintsTriageAndInvestigation.enterAmountRequestedByComplainant(amount);
    }

    @And("I select {string} for are we issuing an offer for a Consolatory payment")
    public void iSelectForAreWeIssuingAnOfferForAConsolatoryPayment(String yesNo) {
        complaintsTriageAndInvestigation.selectAreWeIssuingOfferForConsolatoryPayment(yesNo);
    }

    @And("I enter {string} as the Consolatory payment offer sent to the complainant")
    public void iEnterAsTheConsolatoryPaymentOfferSentToTheComplainant(String amount) {
        complaintsTriageAndInvestigation.enterConsolatoryPaymentOffer(amount);
    }

    @And("I select {string} for are we issuing an offer for an Ex-Gratia payment")
    public void iSelectForAreWeIssuingAnOfferForAExGratiaPayment(String yesNo) {
        complaintsTriageAndInvestigation.selectAreWeIssuingOfferForExGratiaPayment(yesNo);
    }

    @And("I enter {string} as the Ex-Gratia payment offer sent to the complainant")
    public void iEnterAsTheExGratiaPaymentOfferSentToTheComplainant(String amount) {
        complaintsTriageAndInvestigation.enterExGratiaPaymentOffer(amount);
    }

    @Then("the total payment offer sent to the complainant should be the sum of the payment offers previously entered")
    public void theTotalPaymentOfferSentToTheComplainantShouldBeTheSumOfThePaymentOffersPreviouslyEntered() {
        complaintsTriageAndInvestigation.assertTotalPaymentOfferIsCorrect();
    }

    @And("I {string} the case at the Investigation stage")
    public void complaintsTriageAndInvestigationAcceptanceDecision(String decision) {
        if (decision.equalsIgnoreCase("ACCEPT")) {
            complaintsTriageAndInvestigation.acceptCaseAtInvestigation();
        } else if (decision.equalsIgnoreCase("REJECT")) {
            complaintsTriageAndInvestigation.rejectCaseAtInvestigation();
        } else {
            pendingStep(decision + " is not a valid input for " + getMethodName());
        }
    }

    @And("I complete the {string} action at the Investigation stage")
    public void iSelectTheActionAtTheInvestigationStage(String action) {
        switch (action.toUpperCase()) {
            case "ALL INFORMATION COLLECTED - RESPOND":
                complaintsTriageAndInvestigation.selectAllInformationCollectedRespondAction();
                break;
            case "CLOSE THE CASE":
                complaintsTriageAndInvestigation.selectCloseCaseAction();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(finishButton);
    }

    @And("I enter a transfer reason at the Investigation stage")
    public void iEnterATransferReasonAtTheInvestigationStage() {
        complaintsTriageAndInvestigation.enterTransferReason();
        if (sessionVariableCalled("businessArea").equals("HMPO")) {
            safeClickOn(continueButton);
        }
    }

    @And("I select that the case is to be transferred to an {string} team")
    public void iSelectThatTheCaseIsToBeTransferredToATeam(String internalExternal) {
        complaintsTriageAndInvestigation.selectInternalOrExternalTransfer(internalExternal);
    }

    @And("I select an investigating team")
    public void iSelectAnInvestigatingTeam() {
        complaintsTriageAndInvestigation.selectInvestigatingTeam();
        safeClickOn(continueButton);
    }

    @And("I enter any required information at the Investigation stage")
    public void iEnterAnyRequiredInformationAtTheInvestigationStage() {
        if(sessionVariableCalled("isLoARequired").equals("Yes")) {
            complaintsTriageAndInvestigation.enterLoAReceivedDetails();
        }
    }

    @And("I select a Closure Reason")
    public void iSelectAClosureReason() {
        complaintsTriageAndInvestigation.selectAClosureReason();
    }
}
