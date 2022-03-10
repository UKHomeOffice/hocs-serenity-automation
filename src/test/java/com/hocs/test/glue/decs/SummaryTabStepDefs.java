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

    @Then("the case deadline date displayed in the summary is correct for a {string} case")
    public void theCaseDeadlineDateDisplayedInTheSummaryIsCorrectForACaseString(String deadlineDecidingFactor) {
        summaryTab.assertDeadlineDateOfCase(deadlineDecidingFactor);
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

    @Then("the claim should be sent/returned to the correct WCS Casework team")
    public void theClaimShouldBeReturnedToTheCaseworkTeamThatLastWorkedTheClaim() {
        dashboard.getCurrentCase();
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("selectedCaseworkTeam"), "Team");
    }

    @And("the summary should display the owning team as {string}")
    public void theSummaryShouldDisplayTheOwningTeamAs(String teamName) {
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
        waitForPageWithTitle(getCurrentCaseReference());
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
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("enquirySubject"), "Enquiry Subject");
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("enquiryReason"), "Enquiry Reason");
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

    @And("the closure reason and details should be visible in the Summary tab")
    public void theClosureReasonAndDetailsShouldBeVisibleInTheSummaryTab() {
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("closureReason"), "Why should this case be closed?");
    }

    @Then("the newly created recipient details should be displayed in the summary tab")
    public void theNewlyCreatedRecipientShouldBeDisplayedInTheSummaryTab() {
        String recipient = sessionVariableCalled("recipientName");
        summaryTab.assertRecipientIsAddedToTOCase(recipient);
    }
}
