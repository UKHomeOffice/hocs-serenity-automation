package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPTriage;
import io.cucumber.java.en.And;

public class COMPTriageStepDefs extends BasePage {

    COMPTriage COMPTriage;

    @And("I accept the case at {string} Triage stage")
    public void iAcceptTheCaseAtServiceTriageStage(String complaintType) {
        COMPTriage.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            COMPTriage.enterDateOfAcceptance();
        }
        waitABit(500);
        clickTheButton("Continue");
    }

    @And("I select to Transfer the case to CCH")
    public void iSelectToTransferTheCaseToCCH() {
        COMPTriage.selectTransferComplaint();
    }

    @And("I enter a reason for transfer and continue")
    public void iEnterAReasonForTransferAndContinue() {
        COMPTriage.enterTransferReason();
        COMPTriage.selectTransferToCCH();
    }

    @And("I enter details on the Triage Capture Reason page")
    public void iEnterDetailsOnTheTriageCaptureReasonPage() {
        COMPTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I send the case to drafting")
    public void iSendTheCaseToDrafting() {
        COMPTriage.selectReadyForDrafting();
    }

    @And("I escalate the case to WFM at Service Triage stage")
    public void iEscalateTheCaseToWFM() {
        COMPTriage.escalateCaseToWFM();
    }

    @And("I select to complete the case at Service Triage")
    public void iSelectToCompleteTheCase() {
        COMPTriage.selectCompleteTheCase();
    }

    @And("I enter a completion note at Service Triage")
    public void iEnterACompletionNote() {
        COMPTriage.enterCompletionReason();
    }

    @And("I confirm I want to close the case at Service Triage")
    public void iConfirmIWantToCloseTheCaseAtServiceTriage() {
        COMPTriage.selectPermanentlyCloseCase("Yes");
    }

    @And("I select that a Letter of Authority is required")
    public void iSelectThatALOAIsRequired() {
        COMPTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        COMPTriage.selectLOAReceived();
    }

    @And("the overdue contribution request should be highlighted")
    public void highlightingCheckAtServiceTriage() {
        COMPTriage.assertOverdueContributionRequestIsHighlighted();
    }
}
