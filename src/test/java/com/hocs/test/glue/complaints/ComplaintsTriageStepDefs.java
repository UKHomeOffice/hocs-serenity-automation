package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsTriage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ComplaintsTriageStepDefs extends BasePage {


    ComplaintsTriage complaintsTriage;

    @And("I accept the case at {string} Triage stage")
    public void iAcceptTheCaseAtServiceTriageStage(String complaintType) {
        complaintsTriage.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriage.enterDateOfAcceptance();
        }
        waitABit(500);
        clickTheButton("Continue");
    }

    @And("I accept the case at Triage stage")
    public void iAcceptTheCaseAtTriageStage() {
        complaintsTriage.selectAcceptCase();
        waitABit(500);
        clickTheButton("Continue");
    }
    @And("I select to Transfer the complaint")
    public void iSelectToTransferTheComplaint() {
        complaintsTriage.selectTransferComplaint();
    }

    @And("I enter a reason for {string} transfer and continue")
    public void iEnterAReasonForTransferAndContinue(String transferTo) {
        complaintsTriage.enterTransferReason();
        if(transferTo.equals("CCH")){
            complaintsTriage.selectTransferToCCH();
        } else if(transferTo.equals("IE Detention")){
            complaintsTriage.selectTransferToIEDET();
        } else if(transferTo.equals("PSU")){
            complaintsTriage.selectTransferToPSU();
        }
    }

    @And("I submit details on the Triage Capture Reason page")
    public void iSubmitDetailsOnTheTriageCaptureReasonPage() {
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
    }

    @And("I enter information on the Triage Details page")
    public void iEnterInformationOnTheTriageDetailsPage() {
        complaintsTriage.enterDetailsOnBFTriageDetailsPage();
    }

    @And("I send the case to drafting")
    public void iSendTheCaseToDrafting() {
        complaintsTriage.selectReadyForDrafting();
    }

    @And("I escalate the case to WFM at Triage stage")
    public void iEscalateTheCaseToWFM() {
        complaintsTriage.escalateCaseToWFM();
    }

    @And("I select to complete the case at Triage")
    public void iSelectToCompleteTheCase() {
        complaintsTriage.selectCompleteTheCase();
    }

    @And("I enter a completion note at Service Triage")
    public void iEnterACompletionNote() {
        complaintsTriage.enterCompletionReason();
    }

    @And("I confirm I want to close the case at Service Triage")
    public void iConfirmIWantToCloseTheCaseAtServiceTriage() {
        complaintsTriage.selectPermanentlyCloseCase("Yes");
    }

    @And("I select that a Letter of Authority is required")
    public void iSelectThatALOAIsRequired() {
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        complaintsTriage.selectSpecificOptionForIsLOARequired("Yes");
        clickTheButton("Continue");
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        complaintsTriage.enterLoAReceivedDetails();
    }

    @And("the overdue contribution request should be highlighted")
    public void highlightingCheckAtServiceTriage() {
        complaintsTriage.assertOverdueContributionRequestIsHighlighted();
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
                complaintsTriage.selectTransferredToThirdPartySupplier();
                break;
            case "TRANSFERRED TO IE DETENTION COMPLIANCE TEAM":
                complaintsTriage.selectTransferredToIEDetentionComplianceTeam();
                break;
            case "NO FURTHER CONSIDERATION":
                complaintsTriage.selectNoFurtherConsideration();
                break;
            case "TRANSFER COMPLAINT TO CCH":
                complaintsTriage.selectTransferComplaintToCCH();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickTheButton("Continue");
    }

    @And("I enter details on PSU Reference page")
    public void iEnterDetailsOnPSUReferencePage() {
        complaintsTriage.enterPSUReference();
        clickTheButton("Continue");
    }

    @And("I select {string} as additional information on Triage Case Details page")
    public void iSelectAsAdditionalInformationOnTriageCaseDetailsPage(String additionalInformation) {
        complaintsTriage.selectAdditionalInformation(additionalInformation);
        clickTheButton("Continue");
    }

    @And("I select a Close Reason")
    public void iSelectACloseReason() {
        complaintsTriage.selectACloseReason();
    }

    @And("I enter a reason for closing the case")
    public void iEnterAReasonForClosingTheCase() {
        complaintsTriage.enterCompletionReason();
        clickTheButton("Complete case");
    }

    @And("I do not accept the case at Triage")
    public void iDoNotAcceptTheCaseAtTriage() {
        complaintsTriage.selectTransferOfflineAndCloseTheCase();
    }

    @And("I enter reason for Triage transfer and close the case")
    public void iEnterReasonForTriageTransferAndCloseTheCase() {
        complaintsTriage.enterTransferReason();
        clickTheButton("Close Case");
    }

    @Then("I should be able to select the new COMP Enquiry Reason")
    public void iShouldBeAbleToSelectTheNewEnquiryReason() {
        complaintsTriage.selectEnquiryReason(sessionVariableCalled("enquiryReasonName"));
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
        complaintsTriage.selectComplainantHasRequestedPayment(yesNo);
    }

    @And("I enter {string} as the amount requested by the complainant")
    public void iEnterAsTheAmountRequestedByTheComplainant(String amount) {
        complaintsTriage.enterAmountRequestedByComplainant(amount);
    }

    @And("I select {string} for are we issuing an offer for a Consolatory payment")
    public void iSelectForAreWeIssuingAnOfferForAConsolatoryPayment(String yesNo) {
        complaintsTriage.selectAreWeIssuingOfferForConsolatoryPayment(yesNo);
    }

    @And("I enter {string} as the Consolatory payment offer sent to the complainant")
    public void iEnterAsTheConsolatoryPaymentOfferSentToTheComplainant(String amount) {
        complaintsTriage.enterConsolatoryPaymentOffer(amount);
    }

    @And("I select {string} for are we issuing an offer for an Ex-Gratia payment")
    public void iSelectForAreWeIssuingAnOfferForAExGratiaPayment(String yesNo) {
        complaintsTriage.selectAreWeIssuingOfferForExGratiaPayment(yesNo);
    }

    @And("I enter {string} as the Ex-Gratia payment offer sent to the complainant")
    public void iEnterAsTheExGratiaPaymentOfferSentToTheComplainant(String amount) {
        complaintsTriage.enterExGratiaPaymentOffer(amount);
    }

    @Then("the total payment offer sent to the complainant should be the sum of the payment offers previously entered")
    public void theTotalPaymentOfferSentToTheComplainantShouldBeTheSumOfThePaymentOffersPreviouslyEntered() {
        complaintsTriage.assertTotalPaymentOfferIsCorrect();
    }
}
