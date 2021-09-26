package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPTriage;
import io.cucumber.java.en.And;

public class COMPTriageStepDefs extends BasePage {

    COMPTriage compTriage;

    @And("I accept the case at {string} Triage stage")
    public void iAcceptTheCaseAtServiceTriageStage(String complaintType) {
        compTriage.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            compTriage.enterDateOfAcceptance();
        }
        waitABit(500);
        clickTheButton("Continue");
    }

    @And("I select to Transfer the case to CCH")
    public void iSelectToTransferTheCaseToCCH() {
        compTriage.selectTransferComplaint();
    }

    @And("I enter a reason for transfer and continue")
    public void iEnterAReasonForTransferAndContinue() {
        compTriage.enterTransferReason();
        compTriage.selectTransferToCCH();
    }

    @And("I enter details on the Triage Capture Reason page")
    public void iEnterDetailsOnTheTriageCaptureReasonPage() {
        compTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I send the case to drafting")
    public void iSendTheCaseToDrafting() {
        compTriage.selectReadyForDrafting();
    }

    @And("I escalate the case to WFM at Service Triage stage")
    public void iEscalateTheCaseToWFM() {
        compTriage.escalateCaseToWFM();
    }

    @And("I select to complete the case at Service Triage")
    public void iSelectToCompleteTheCase() {
        compTriage.selectCompleteTheCase();
    }

    @And("I enter a completion note at Service Triage")
    public void iEnterACompletionNote() {
        compTriage.enterCompletionReason();
    }

    @And("I confirm I want to close the case at Service Triage")
    public void iConfirmIWantToCloseTheCaseAtServiceTriage() {
        compTriage.selectPermanentlyCloseCase("Yes");
    }

    @And("I select that a Letter of Authority is required")
    public void iSelectThatALOAIsRequired() {
        compTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        compTriage.selectLOAReceived();
    }

    @And("the overdue contribution request should be highlighted")
    public void highlightingCheckAtServiceTriage() {
        compTriage.assertOverdueContributionRequestIsHighlighted();
    }

    @And("I accept the previous Claim Category selection")
    public void iAcceptThePreviousClaimCatagorySelection() {
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
    }

    @And("I accept the previous Severity selection")
    public void iAcceptThePreviousSeveritySelection() {
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
    }
}
