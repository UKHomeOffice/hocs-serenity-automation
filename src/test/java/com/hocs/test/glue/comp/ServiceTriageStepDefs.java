package com.hocs.test.glue.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.comp.ServiceTriage;
import com.hocs.test.pages.ukvi.MultipleContributions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ServiceTriageStepDefs extends BasePage {

    MultipleContributions multipleContributions;

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

    @And("I select to complete the case")
    public void iSelectToCompleteTheCase() {
        serviceTriage.selectCompleteTheCase();
    }

    @And("I enter a completion note")
    public void iEnterACompletionNote() {
        serviceTriage.enterCompletionReason();
    }

    @And("I confirm I want to close the case")
    public void iConfirmIWantToCloseTheCase() {
        serviceTriage.selectPermanentlyCloseCase("Yes");
    }

    @And("I add a {string} contribution")
    public void iAddAContribution(String contributionType) {
        if (contributionType.equalsIgnoreCase("COMPLAINANT")) {
            safeClickOn(serviceTriage.addComplainantContributionHypertext);
        } else if (contributionType.equalsIgnoreCase("BUSINESS")) {
            safeClickOn(serviceTriage.addBusinessContributionHypertext);
        }
        multipleContributions.addAContribution();
    }

    @And("I add a contribution with a due date in the past")
    public void iAddAContributionWithADueDateInThePast() {
        safeClickOn(serviceTriage.addComplainantContributionHypertext);
        multipleContributions.addAnOverdueCOMPContribution();
    }

    @Then("the contributions due date should be displayed as overdue")
    public void theContributionsDueDateShouldBeDisplayedAsOverdue() {
        serviceTriage.assertContributionIsOverdue();
    }

    @And("I select that a Letter of Authority is required")
    public void iSelectThatALOAIsRequired() {
        serviceTriage.enterDetailsOnTriageCaptureReasonPage();
    }

    @And("I can mark that the LoA was received and enter the LoA date")
    public void iCanMarkThatTheLOAWasReceivedAndEnterTheLOADates() {
        serviceTriage.selectLOAReceived();
    }
}
