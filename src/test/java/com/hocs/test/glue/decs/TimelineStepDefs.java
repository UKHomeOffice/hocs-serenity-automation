package com.hocs.test.glue.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.TimelineTab;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TimelineStepDefs extends BasePage {

    TimelineTab timelineTab;

    @And("I select the Timeline tab")
    public void iSelectTheTimelineTab() {
        waitABit(5000);
        timelineTab.selectTimelineTab();
    }

    @And("I create a case note with random content")
    public void iCreateACaseNoteWithRandomContent() {
        timelineTab.createACaseNote();
    }

    @Then("a note should be created at the top of the timeline")
    public void aNoteShouldBeCreatedAtTheTopOfTheTimeline() {
        timelineTab.assertCaseNoteAtTopOfTimeline();
    }

    @And("it should have the same content")
    public void itShouldHaveTheSameContent() {
        timelineTab.assertTopNoteContainsEnteredText(sessionVariableCalled("createdNoteContents"));
    }

    @And("it should state that user {string} created it")
    public void itShouldStateThatUserCreatedIt(String user) {
        timelineTab.assertTopNoteSignatureContainsCreator(User.valueOf(user));
    }

    @Then("a log should be at the top of the timeline")
    public void aLogShouldBeAtTheTopOfTheTimeline() {
        timelineTab.assertLogAtTopOfTimeline();
    }

    @Then("there should be a log showing the case was allocated to user {string} at stage {string}")
    public void thereShouldBeALogShowingTheCaseWasAllocatedToUserAtStage(String user, String stage) {
        timelineTab.assertAllocationToUserLogVisible(User.valueOf(user), stage);
    }

    @And("a log should be visible for completing the {string} stage")
    public void aLogShouldBeVisibleForCompletingTheStage(String stage) {
        timelineTab.assertStageCompletionLogVisible(stage);
    }

    @And("a log should be visible for starting the {string} stage")
    public void aLogShouldBeVisibleForStartingTheStage(String stage) {
        timelineTab.assertStageStartedLogVisible(stage);
    }

    @And("I attempt to add an empty case note")
    public void iAttemptToAddAnEmptyCaseNote() {
        timelineTab.clickAddCaseNote();
        clickAddButton();
    }

    @Then("an error message is displayed as I have not entered text content for the case note")
    public void anErrorMessageIsDisplayedAsIHaveNotEnteredTextContentForTheCaseNote() {
        timelineTab.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @And("I create another case note with random content")
    public void iCreateAnotherCaseNoteWithRandomContent() {
        timelineTab.createAnotherCaseNote();
    }

    @Then("the top note in the timeline should be the second note created")
    public void theTopNoteInTheTimelineShouldBeTheSecondNoteCreated() {
        timelineTab.assertTopNoteContainsEnteredText(sessionVariableCalled("secondNoteContents"));
    }

    @And("the one below it should be the first note created")
    public void theOneBelowItShouldBeTheFirstNoteCreated() {
        timelineTab.assertSecondNoteContainsEnteredText(sessionVariableCalled("createdNoteContents"));
    }

    @And("I edit the top case note")
    public void iEditTopCaseNote() {
        timelineTab.editACaseNote("Test 1");
    }

    @And("the case note should appear between the {string} logs")
    public void editedCaseNoteDisplaysCorrectValue(String stage) {
        timelineTab.assertEditedCaseNoteAppearInCorrectStage(stage);
    }

    @And("the case note should contain the edited content")
    public void itShouldHaveEditedContent() {
        timelineTab.assertTopNoteContainsEnteredText("Case note 1." + sessionVariableCalled("createdNoteContents"));
    }

    @And("a Rejection note should be visible in the timeline showing the submitted reason for the return of the case")
    public void aRejectionNoteShouldBeVisibleInTheTimelineShowingTheReasonForTheReturnOfTheCase() {
        timelineTab.assertRejectionNoteVisible();
    }

    @And("an Escalation note should be visible in the timeline showing the submitted reason for the cases escalation")
    public void anEscalationNoteShouldBeVisibleInTheTimelineShowingTheReasonForTheCasesEscalation() {
        timelineTab.assertEscalationNoteVisible();
    }

    @And("a Case closure note should be visible in the timeline showing the submitted reason for closing the case")
    public void aCaseClosureNoteShouldBeVisibleInTheTimelineShowingTheReasonForClosingTheCase() {
        timelineTab.assertClosureNoteVisible();
    }

    @And("a Details of follow up note should be visible in the timeline showing the submitted details of the required action")
    public void aDetailsOfFollowUpNoteShouldBeVisibleInTheTimelineShowingTheSubmittedDetailsOfTheRequiredAction() {
        timelineTab.assertDetailsOfFollowUpNoteVisible();
    }

    @And("a Follow up not completed note should be visible in the timeline showing the submitted reason for not completing the action")
    public void aFollowUpNotCompletedNoteShouldBeVisibleInTheTimelineShowingTheSubmittedReasonForNotCompletingTheAction() {
        timelineTab.assertFollowUpNotCompletedNoteVisible();
    }

    @And("a Conversion note should be visible in the timeline showing the submitted notes on the conversion of the case")
    public void aConversionNoteShouldBeVisibleInTheTimelineShowingTheSubmittedNotesOnTheConversionOfTheCase() {
        timelineTab.assertConversionNoteVisible();
    }

    @And("a Case withdrawn note should be visible showing the submitted notes on the withdrawal of the case")
    public void aCaseWithdrawnNoteShouldBeVisibleInTheTimelineShowingTheSubmittedNotesOnTheWithdrawalOfTheCase() {
        timelineTab.assertCaseWithdrawnNoteVisible();
    }

    @Then("a Change note should be visible in the timeline showing the submitted reason for changing the primary topic")
    public void aChangeNoteShouldBeVisibleInTheTimelineShowingTheSubmittedReasonForChangingThePrimaryTopic() {
        timelineTab.assertChangeNoteVisible();
    }

    @Then("a Case transfer reason note is visible in the timeline showing the submitted reason for the transfer request")
    public void aCaseTransferReasonNoteIsVisibleInTheTimelineShowingTheSubmittedReasonForTheTransferRequest() {
        timelineTab.assertCaseTransferReasonNoteVisible();
    }

    @And("a Case Extension note should be visible in the timeline showing the submitted reason for the extension")
    public void aCaseExtensionNoteShouldBeVisibleInTheTimelineShowingTheSubmittedReasonForTheExtension() {
        timelineTab.assertCaseExtensionNoteVisible();
    }

    @And("an Allocation note should be visible in the timeline showing the details of the allocation")
    public void aAllocationNoteShouldBeVisibleInTheTimelineShowingTheDetailsOfTheAllocation() {
        timelineTab.assertAllocationNoteVisible();
    }

    @And("an Appeal Created note should be visible in the timeline for the selected appeal type")
    public void anAppealCreatedNoteShouldBeVisibleInTheTimelineForTheSelectedAppealType() {
        timelineTab.assertAppealCreatedNoteVisible();
    }

    @And("an Appeal Updated note should be visible in the timeline for the selected appeal type")
    public void anAppealUpdatedNoteShouldBeVisibleInTheTimelineForTheSelectedAppealType() {
        timelineTab.assertAppealUpdatedNoteVisible();
    }

    @And("an Interest Created note should be visible in the timeline for the interested party")
    public void anInterestCreatedNoteShouldBeVisibleInTheTimelineForTheSelectedAppealType() {
        timelineTab.assertInterestCreatedNoteVisible();
    }

    @And("an Interest Updated note should be visible in the timeline for the interested party")
    public void anInterestUpdatedNoteShouldBeVisibleInTheTimelineForTheSelectedAppealType() {
        timelineTab.assertInterestUpdatedNoteVisible();
    }
}
