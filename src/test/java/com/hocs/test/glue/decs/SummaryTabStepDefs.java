package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.Arrays;
import java.util.List;

public class SummaryTabStepDefs extends BasePage {

    CaseView caseView;

    SummaryTab summaryTab;

    Dashboard dashboard;

    @And("I select the summary tab")
    public void iSelectTheSummaryTab() {
        caseView.waitForCaseToLoad();
        summaryTab.selectSummaryTab();
    }

    @And("I select the previous COMP case reference from the COMP2 case summary tab")
    public void thePreviousCOMPCaseReferenceIsDisplayedInTheCOMP2CaseSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.selectPreviousCaseReference();
    }

    @Then("the deadline of the FOI case should be extended the correct number of days")
    public void theDeadlineOfTheFOICaseShouldBeExtended() {
        summaryTab.selectSummaryTab();
        summaryTab.assertDeadlineOfExtendedFOICase();
    }

    @Then("the information entered for the FOI appeal should be displayed in the summary")
    public void theInformationEnteredForTheFOIAppealShouldBeDisplayedInTheSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.assertAppealInformationIsDisplayed();
    }

    @Then("the stage deadline dates displayed in the summary are correct for a {string} case")
    public void theStageDeadlineDatesDisplayedInTheSummaryAreCorrectForACase(String deadlineDecidingFactor) {
        summaryTab.selectSummaryTab();
        switch (deadlineDecidingFactor.toUpperCase()) {
            case "MIN":
            case "HOME SECRETARY SIGN-OFF":
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Data Input");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Markup");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Initial Draft");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "QA Response");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Private Office Approval");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Ministerial Sign Off");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Transfer Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "No Response Needed Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Dispatch");
                break;
            case "DTEN":
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Dispatch");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Initial Draft");
                break;
            case "TRO":
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Data Input");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Markup");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Initial Draft");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "QA Response");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Transfer Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "No Response Needed Confirmation");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Dispatch");
                summaryTab.assertDeadlineDateOfStage(deadlineDecidingFactor, "Copy To Number 10");
                break;
            default:
                pendingStep(deadlineDecidingFactor + " is not defined within " + getMethodName());
        }
    }

    @Then("the (Stage 2 )case deadline date displayed in the summary is correct for a {string} case")
    public void theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString(String deadlineDecidingFactor) {
        summaryTab.assertDeadlineDateOfCaseIsCorrect(deadlineDecidingFactor);
    }

    @Then("the stage and case deadlines have altered to those for a 10 day SLA")
    public void theStageAndCaseDeadlinesHaveAlteredToThoseForADaySLA() {
        theStageDeadlineDatesDisplayedInTheSummaryAreCorrectForACase("HOME SECRETARY SIGN-OFF");
        theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString("HOME SECRETARY SIGN-OFF");
    }

    @Then("the stage and case deadlines revert back to those for a 20 day SLA")
    public void theDeadlineDatesRevertBackToThoseForADaySLA() {
        theStageDeadlineDatesDisplayedInTheSummaryAreCorrectForACase("MIN");
        theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString("MIN");
    }

    @And("the case {string} be allocated to me in the summary")
    public void theCaseShouldBeAllocatedToMeInTheSummary(String input) {
        summaryTab.selectSummaryTab();
        switch (input.toUpperCase()) {
            case "SHOULD":
                summaryTab.assertAllocatedUserIsMe(true);
                break;
            case "SHOULD NOT":
                summaryTab.assertAllocatedUserIsMe(false);
                break;
            default:
                pendingStep(input + " is not defined within " + getMethodName());
        }
    }

    @And("the case should be in the correct MPAM {string} team workstack")
    public void theCaseShouldBeInTheCorrectMPAMTeamWorkstack(String stage) {
        summaryTab.assertAllocatedMPAMTeam(stage);
    }

    @And("the summary should display the correct MPAM {string} stage team as the owning team")
    public void theSummaryShouldDisplayTheCorrectMPAMStageTeamAsTheOwningTeam(String stage) {
        summaryTab.assertAllocatedMPAMTeam(stage);
    }

    @Then("the claim should be sent/returned to the correct WCS Casework team")
    public void theClaimShouldBeReturnedToTheCaseworkTeamThatLastWorkedTheClaim() {
        dashboard.ensureViewingCurrentCase();
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("selectedCaseworkTeam"), "Team");
    }

    @And("the summary should display the owning team as {string}")
    public void theSummaryShouldDisplayTheOwningTeamAs(String teamName) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the summary should display the correct Private Office team")
    public void theSummaryShouldDisplayThePOTeamAs() {
        String privateOfficeTeam = "Minister for Lords";
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(privateOfficeTeam, "Private Office Team");
    }

    @And("the POGR case should be assigned to the correct investigating team")
    public void thePOGRCaseShouldBeAssignedToTheCorrectInvestigatingTeam() {
        String teamName = sessionVariableCalled("investigatingTeam");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the POGR case should be assigned to the correct Escalation team")
    public void thePOGRCaseShouldBeAssignedToTheCorrectEscalationTeam() {
        String teamName = sessionVariableCalled("businessArea").toString() + " Escalation";
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the summary should display {string} for {string}")
    public void theSummaryShouldDisplayFor(String value, String header) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(value, header);
    }

    @And("the summary should display the owning team as the correct Treat Official team for the selected business area")
    public void theSummaryShouldDisplayTheOwningTeamAsTheCorrectTreatOfficialTeamForTheSelectedBusinessArea() {
        String teamName = "Treat Official " + sessionVariableCalled("businessArea");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the case should still be owned by the correct Treat Official team for the selected business area")
    public void theCaseShouldStillBeOwnedByTheCorrectTreatOfficialTeamForTheSelectedBusinessArea() {
        waitForDECSPageWithTitle(getCurrentCaseReference());
        String teamName = "Treat Official " + sessionVariableCalled("businessArea");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @Then("the case should be moved to the correct Treat Official team for the new business area")
    public void theCaseShouldBeMovedToTheCorrectTreatOfficialTeamForTheNewBusinessArea() {
        waitABit(2000);
        if (!caseView.currentCaseIsLoaded()) {
            dashboard.loadCase(getCurrentCaseReference());
        }
        String teamName = "Treat Official " + sessionVariableCalled("businessArea");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(teamName, "Team");
    }

    @And("the summary should display the owning team as the selected FOI Group")
    public void theSummaryShouldDisplayTheOwningTeamAsTheSelectedFOIGroup() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("foiGroup"), "Team");
    }

    @And("the case should still be owned by the selected FOI Group")
    public void theCaseShouldStillBeOwnedByTheSelectedFOIGroup() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("foiGroup"), "Team");
    }

    @And("the summary should contain the Business Area, Channel Received, Home Secretary Interest selection, and Primary Correspondents details")
    public void theSummaryShouldContainTheBusinessAreaChannelReceivedAddresseeAndPrimaryCorrespondent() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("businessArea"), "Business Area");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("channelReceived"), "Channel Received");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("homeSecInterest"), "Home Secretary Interest");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentFullName"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentOrganisation"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentBuilding"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentStreet"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentTownOrCity"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentPostcode"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentCountry"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentTelephoneNumber"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentEmail"), "Primary correspondent");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("correspondentReference"), "Primary correspondent");
    }

    @And("the summary should contain the Recipient")
    public void theSummaryShouldContainTheRecipient() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("recipient"), "Recipient");
    }

    @And("the summary should contain the Enquiry Subject, Enquiry Reason and Business Unit")
    public void theSummaryShouldContainTheEnquiryReasonAndEnquirySubjectAndBusinessUnit() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("enquirySubject"), "Enquiry subject");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("enquiryReason"), "Enquiry reason");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("businessUnit"), "Business Unit");
    }

    @Then("the amended value for Channel Received should be saved to the case")
    public void theAmendedValueForChannelReceivedShouldBeSavedToTheCase() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("channelReceived"), "Channel Received");
    }

    @Then("the amended value for Business Unit Type and Business Unit should be saved to the case")
    public void theAmendedValueForBusinessUnitShouldBeSavedToTheCase() {
        waitABit(2000);
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("businessUnit"), "Business Unit");
    }

    @And("the summary should contain the selected/new campaign")
    public void theSummaryShouldContainTheSelectedCampaign() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("campaign"), "Campaign name");
    }

    @And("the summary should contain the selected/new stop list")
    public void theSummaryShouldContainTheSelectedStopList() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("stopList"), "Stop List name");
    }

    @And("the summary should contain the PSU reference")
    public void theSummaryShouldContainThePSUReference() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("psuReference"), "PSU reference");
    }

    @And("the summary should contain the Business Area, Channel Received, Reference Type and Urgency")
    public void theSummaryShouldContainTheBusinessAreaChannelReceivedReferenceTypeAndUrgency() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("businessArea"), "Business Area");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("inboundChannel"), "Channel received");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("refType"), "Does this correspondence need a Ministerial response?");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("urgency"), "Urgency");
    }

    @And("the closure reason and details should be visible in the Summary tab")
    public void theClosureReasonAndDetailsShouldBeVisibleInTheSummaryTab() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("closureReason"), "Why should this case be closed?");
    }

    @Then("the newly created recipient details should be displayed in the summary tab")
    public void theNewlyCreatedRecipientShouldBeDisplayedInTheSummaryTab() {
        String recipient = sessionVariableCalled("recipientName");
        summaryTab.assertRecipientIsAddedToTOCase(recipient);
    }

    @And("I can only see non-sensitive information in the Summary tab")
    public void iCanOnlySeeRegistrationInformationInTheSummary() {
        summaryTab.selectSummaryTab();
        List<String> expectedHeaders = Arrays
                .asList("Created", "Deadline", "Primary correspondent", "Channel", "When was the correspondence received?", "Response date");
        summaryTab.assertSummaryContainsOnlyExpectedHeaders(expectedHeaders);
    }

    @Then("I should not be able to see which stage the case is at")
    public void iShouldNotBeAbleToSeeWhichStageTheCaseIsAt() {
        summaryTab.assertNoActiveStageVisible();
    }

    @And("I should not be able to see which team the case is currently assigned to")
    public void iShouldNotBeAbleToSeeWhichTeamTheCaseIsCurrentlyAssignedTo() {
        summaryTab.assertNoAllocatedTeamVisible();
    }

    @And("I should not be able to see which user is currently assigned to the case")
    public void iShouldNotBeAbleToSeeWhichUserIsCurrentlyAssignedToTheCase() {
        summaryTab.assertNoAllocatedUserVisible();
    }

    @And("I can see all of the cases Summary data")
    public void iCanSeeAllOfTheCasesSummaryData() {
        summaryTab.selectSummaryTab();
        List<String> expectedHeaders = Arrays.asList("Created", "Deadline", "Primary correspondent", "Channel", "Business Area", "Case Outcome",
                "Enquiry Reason", "Is a Letter of Authority required?", "When was the correspondence received?", "Response date");
        summaryTab.assertSummaryContainsOnlyExpectedHeaders(expectedHeaders);
    }

    @Then("the Requested Question should be displayed in the summary tab")
    public void theRequestedQuestionShouldBeDisplayedInTheSummaryTab() {
        waitABit(500);
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("requestQuestion"), "Request Question");
    }

    @And("the summary should contain the Responsible Team")
    public void theSummaryShouldContainTheResponsibleTeam() {
        if (!sessionVariableCalled("foiGroup").equals("FOI UK Visas and Immigration")) {
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("responsibleTeam"), "Responsible Team");
        }
    }

    @And("the deadline of the case should be replaced with (the word ){string} in the Summary tab")
    public void theDeadlineOfTheCaseShouldBeReplacedWithTheWordInTheSummaryTab(String replacementValue) {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(replacementValue, "Deadline");
    }

    @And("the summary should contain details of the phone call")
    public void theSummaryShouldContainDetailsOfThePhoneCall() {
        String resolvedByPhone = sessionVariableCalled("resolvedByPhone");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(resolvedByPhone, "Was the case resolved by phone call?");
        if (resolvedByPhone.equalsIgnoreCase("Yes")) {
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("callDate"), "Date of Call");
        }
    }

    @And("the summary should contain submitted case closure details")
    public void aCaseClosureNoteShouldBeVisibleInTheSummaryShowingTheSubmittedReasonForClosingTheCase() {
        String closureReason = sessionVariableCalled("closureReason");
        String dropdownClosureReason = sessionVariableCalled("dropdownClosureReason");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(dropdownClosureReason, "Closure Reason");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(closureReason,"Enter a note for case closure");
    }

    @And("the summary should contain details of the {string} Complaint Outcome")
    public void theSummaryShouldContainDetailsOfTheComplaintOutcome(String psuComplaintOutcome) {
     if(psuComplaintOutcome.equalsIgnoreCase("Withdrawn")){
        String psuComplaintOutcomeWithdrawnReason = sessionVariableCalled("psuComplaintOutcomeWithdrawnReason");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuComplaintOutcome, "Complaint outcome");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuComplaintOutcomeWithdrawnReason, "Why has the complaint been withdrawn?");
        } else if (psuComplaintOutcome.equalsIgnoreCase("Substantiated") ||
                    psuComplaintOutcome.equalsIgnoreCase( "Partially substantiated") ||
                    psuComplaintOutcome.equalsIgnoreCase("Unsubstantiated")){
        String psuFinalResponseDate = sessionVariableCalled("psuFinalResponseDate");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuComplaintOutcome, "Complaint outcome");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuFinalResponseDate, "Final response sent");
        } else if (psuComplaintOutcome.equalsIgnoreCase("Not serious - send back to UKVI")) {
            String psuComplaintOutcomeReason = sessionVariableCalled("psuComplaintOutcomeReason");
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuComplaintOutcomeReason, "Complaint outcome");
        }
    }

    @And("the summary should contain details of the Complaint Origin")
    public void theSummaryShouldContainDetailsOfTheComplaintOrigin() {
        String complaintOrigin = sessionVariableCalled("complaintOrigin");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(complaintOrigin, "Complaint origin");
        if(complaintOrigin.equalsIgnoreCase("external contractor")){
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("selectExternalContractor"), "External contract");
        }
    }

    @And("the summary should contain details of the Complaint Outcome selection")
    public void theSummaryShouldContainDetailsOfTheComplaintOutcomeSelection() {
            String psuComplaintOutcome = sessionVariableCalled("complaintOutcome");
            String psuFinalResponseDate = sessionVariableCalled("psuFinalResponseDate");
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuComplaintOutcome, "Complaint outcome");
            summaryTab.assertSummaryContainsExpectedValueForGivenHeader(psuFinalResponseDate, "Final response sent");

    }
}
