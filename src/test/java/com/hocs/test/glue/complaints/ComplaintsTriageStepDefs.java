package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsTriage;
import io.cucumber.java.en.And;

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
        if(transferTo.equals("CCHReturns")){
            complaintsTriage.selectTransferToCCH();
        } else if(transferTo.equals("IE Detention")){
            complaintsTriage.selectTransferToIEDET();
        }
    }

    @And("I submit details on the Triage Capture Reason page")
    public void iSubmitDetailsOnTheTriageCaptureReasonPage() {
        if (bfCase() || bf2Case()) {
            complaintsTriage.enterDetailsOnBFTriageCaptureReasonPage();
        } else {
            complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        }
        clickTheButton("Continue");
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
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        complaintsTriage.selectLOAReceived();
    }

    @And("the overdue contribution request should be highlighted")
    public void highlightingCheckAtServiceTriage() {
        complaintsTriage.assertOverdueContributionRequestIsHighlighted();
    }

    @And("I accept the previous Claim Category selection")
    public void iAcceptThePreviousClaimCategorySelection() {
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
    }

    @And("I accept the previous Case Details selection")
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
            case "TRANSFER COMPLAINT TO CCHReturns":
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
}
