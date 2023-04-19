package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.complaints.ComplaintsDispatchAndSend;
import com.hocs.test.pages.complaints.ComplaintsRegistrationAndDataInput;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsTriageAndInvestigation;

import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.SummaryTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


public class ComplaintsTriageAndInvestigationStepDefs extends BasePage {


    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    Documents documents;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    RecordCaseData recordCaseData;

    Dashboard dashboard;

    CaseView caseView;

    SummaryTab summaryTab;

    @And("I accept the case at {string} Triage stage")
    public void iAcceptTheCaseAtServiceTriageStage(String complaintType) {
        complaintsTriageAndInvestigation.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriageAndInvestigation.enterDateOfAcceptance();
        }
        waitABit(500);
        clickContinueButton();
    }

    @And("I accept the case at Triage stage")
    public void iAcceptTheCaseAtTriageStage() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        waitABit(500);
        clickContinueButton();
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
        clickContinueButton();
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
        clickContinueButton();
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
        waitForDECSPageWithTitle("Complaint category");
        clickContinueButton();
    }

    @And("I accept the (previous )Case Details selection")
    public void iAcceptThePreviousSeveritySelection() {
        waitForDECSPageWithTitle("Triage case details");
        clickContinueButton();
    }

    @And("I select the {string} action for an IEDET case at the Triage stage")
    public void iSelectTheActionForAnIEDETCaseAtTriage(String action) {
        switch (action.toUpperCase()) {
            case "THIRD PARTY SUPPLIER":
                complaintsTriageAndInvestigation.selectThirdPartySupplier();
                break;
            case "IE DETENTION COMPLIANCE TEAM":
                complaintsTriageAndInvestigation.selecIEDetentionComplianceTeam();
                break;
            case "DEPMU":
                complaintsTriageAndInvestigation.selectDEPMU();
                break;
            case "SEND TO CCH":
                complaintsTriageAndInvestigation.selectSendToCCH();
                complaintsTriageAndInvestigation.enterReasonForTransfer();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        clickFinishButton();
    }

    @And("I enter details on PSU Reference page")
    public void iEnterDetailsOnPSUReferencePage() {
        complaintsTriageAndInvestigation.enterPSUReference();
        clickContinueButton();
    }

    @And("I select {string} as additional information on Triage Case Details page")
    public void iSelectAsAdditionalInformationOnTriageCaseDetailsPage(String additionalInformation) {
        complaintsTriageAndInvestigation.selectAdditionalInformation(additionalInformation);
        clickContinueButton();
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
        clickFinishButton();
    }

    @And("I enter a transfer reason at the Investigation stage")
    public void iEnterATransferReasonAtTheInvestigationStage() {
        complaintsTriageAndInvestigation.enterTransferReason();
        if (sessionVariableCalled("businessArea").equals("HMPO")) {
            clickContinueButton();
        }
    }

    @And("I select that the case is to be transferred to an {string} team")
    public void iSelectThatTheCaseIsToBeTransferredToATeam(String internalExternal) {
        complaintsTriageAndInvestigation.selectInternalOrExternalTransfer(internalExternal);
    }

    @And("I select an investigating team")
    public void iSelectAnInvestigatingTeam() {
        complaintsTriageAndInvestigation.selectInvestigatingTeam();
        clickContinueButton();
    }

    @And("I enter any required information at the Investigation stage")
    public void iEnterAnyRequiredInformationAtTheInvestigationStage() {
        if (sessionVariableCalled("isLoARequired").equals("Yes")) {
            complaintsTriageAndInvestigation.enterLoAReceivedDetails();
        }
    }

    @And("I select a Closure Reason")
    public void iSelectAClosureReason() {
        complaintsTriageAndInvestigation.selectAClosureReason();
    }

    @And("I complete Triage and escalate the case to PSU")
    public void iCompleteTriageAndEscalateTheCaseToPSU() {
        clickTheButton("Finish and escalate to PSU");
    }

    @And("I choose to send the case to a team not on DECS")
    public void iChooseToSendTheCaseToATeamNotOnDECS() {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        selectSpecificRadioButton("No - send to team not on DECS");
        clickTheButton("Submit");
    }

    @And("I choose to send the case back to IEDET")
    public void iChooseToSendTheCaseBackToIEDET() {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        selectSpecificRadioButton("No - send back to IE Detention");
        clickTheButton("Submit");
    }

    @Then("When I attempt to continue without selecting a PSU Triage Option an error message is displayed")
    public void iAttemptToContinueWithoutSelectingAPSUTriageOption() {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        clickTheButton("Submit");
        assertExpectedErrorMessageIsDisplayed("Is this serious misconduct case for PSU to investigate? is required");
    }

    @Then("I enter the Final response and Final date")
    public void iEnterFinalResponseAndFinalDate() {
        clickTheButton("Close case");
        assertThatAnErrorMessageIsDisplayed();
        if(compCase() || comp2Case() || comp2DirectCase()){
            documents.addADocumentOfDocumentType("Final Response");
        } else {
            documents.addADocumentOfDocumentType("Final response");
        }
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(+5), "Final response sent");
        clickTheButton("Close case");
        assertExpectedErrorMessageIsDisplayed("Final response sent must be a date in the past");
        complaintsDispatchAndSend.enterFinalResponseSentDate();
        setSessionVariable("psuFinalResponseDate").to(getDatePlusMinusNDaysAgo(-1));
        clickTheButton("Close case");
    }


    @Then("I attempt to continue without selecting a Complaint Outcome an error message is displayed")
    public void iAttemptToContinueWithoutSelectingAComplaintOutcomeAnErrorMessageIsDisplayed() {
        dashboard.getCurrentCase();
        caseView.clickAllocateToMeLink();
        clickTheButton("Submit");
        waitABit(1000);
        assertExpectedErrorMessageIsDisplayed("Complaint outcome is required");
    }

    @And("I move to the review complaint categories screen and check the options there are working correctly")
    public void iMoveToTheReviewComplaintCategoriesScreenAndCheckTheOptionsThereAreWorkingCorrectly() {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        complaintsTriageAndInvestigation.checkPSUComplaintCategories();
    }

    @Then("I select {string} at {string} page")
    public void iSelectPSUComplaintOutcome(String psuCompliantOutcome, String complaintPage) {
        complaintsTriageAndInvestigation.selectPSUComplaintOutcome(psuCompliantOutcome);
    }

    @Then("I select Complaint Type at PSU Complaint Outcome Page page")
    public void iSelectComplaintTypeAtPSUComplaintOutcomePagePage() {
        dashboard.getCurrentCase();
        caseView.clickAllocateToMeLink();
        complaintsRegistrationAndDataInput.selectRandomCaseOutcomeToProgress();
        clickTheButton("Submit");
    }

    @And("I select {string} to transfer the complaint to PSU as {string}")
    public void iSelectToTransferTheComplaintToPSU(String transferToPSU, String header) {
        complaintsTriageAndInvestigation.selectTransferComplaintToPSU(transferToPSU, header);
        clickContinueButton();
        complaintsTriageAndInvestigation.selectAVisibleClaimCategory();
        clickTheButton("Finish and escalate to PSU");
    }

    @And("I select a Complaint Type")
    public void iSelectAComplaintType() {
        if(bf2Case()){
            summaryTab.assertComplaintType("Service");
            openOrCloseAccordionSection("Border Force Registration (Stage 2)");
            recordCaseData.assertComplaintTypeInTheReadOnlyAccordion("Service");
        }
        caseView.clickAllocateToMeLink();
        if(compCase() || comp2Case() || comp2DirectCase()){
            complaintsTriageAndInvestigation.selectAComplaintTypeForRecategorisedUKVI();
        } else if (bfCase()) {
            complaintsTriageAndInvestigation.selectAComplaintTypeForRecategorisedBF();
        } else if (bf2Case()) {
            waitForDECSPageWithTitle("Triage accept case");
        }


    }
}
