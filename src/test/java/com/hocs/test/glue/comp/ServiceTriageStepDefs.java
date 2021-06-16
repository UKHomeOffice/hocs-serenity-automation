package com.hocs.test.glue.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.comp.ServiceTriage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ServiceTriageStepDefs extends BasePage {

    ServiceTriage serviceTriage;

    @And("I accept the case at Service Triage stage")
    public void iAcceptTheCaseAtServiceTriageStage() {
        serviceTriage.selectAcceptCase();
    }

    @And("I select to Transfer the case to CCH")
    public void iSelectToTransferTheCaseToCCH() {
        serviceTriage.selectTransferComplaint();
    }

    @And("I enter a reason for transfer and continue")
    public void iEnterAReasonForTransferAndContinue() {
        serviceTriage.enterTransferReason();
        serviceTriage.selectTransferToCCH();
    }

    @And("I enter details on the Triage Capture Reason page")
    public void iEnterDetailsOnTheTriageCaptureReasonPage() {
        serviceTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I send the case to drafting")
    public void iSendTheCaseToDrafting() {
        serviceTriage.selectReadyForDrafting();
    }

    @And("I escalate the case to WFM at Service Triage stage")
    public void iEscalateTheCaseToWFM() {
        serviceTriage.escalateCaseToWFM();
    }

    @And("I select to complete the case at Service Triage")
    public void iSelectToCompleteTheCase() {
        serviceTriage.selectCompleteTheCase();
    }

    @And("I enter a completion note at Service Triage")
    public void iEnterACompletionNote() {
        serviceTriage.enterCompletionReason();
    }

    @And("I confirm I want to close the case at Service Triage")
    public void iConfirmIWantToCloseTheCaseAtServiceTriage() {
        serviceTriage.selectPermanentlyCloseCase("Yes");
    }

    @And("I select that a Letter of Authority is required")
    public void iSelectThatALOAIsRequired() {
        serviceTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        serviceTriage.selectLOAReceived();
    }

    @And("the overdue contribution request should be highlighted")
    public void highlightingcheck() {
        serviceTriage.assertOverdueContributionRequestIsHighlighted();
    }
}
