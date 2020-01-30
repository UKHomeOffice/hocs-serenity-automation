package com.hocs.test.glue;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.timeline.TimelineTab;
import config.Users;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class TimelineStepDefs extends Page {

    TimelineTab timelineTab;

    @And("^I select the Timeline tab$")
    public void iSelectTheTimelineTab() {
        timelineTab.selectTimelineTab();
    }

    @And("^I create a case note with random content$")
    public void iCreateACaseNoteWithRandomContent() {
        timelineTab.createACaseNote();
    }

    @Then("^a note should be created at the top of the timeline$")
    public void aNoteShouldBeCreatedAtTheTopOfTheTimeline() {
        timelineTab.assertCaseNoteAtTopOfTimeline();
    }

    @And("^it should have the same content$")
    public void itShouldHaveTheSameContent() {
        timelineTab.assertTopNoteContainsEnteredText(sessionVariableCalled("createdNoteContents"));
    }

    @And("^it should state that user \"([^\"]*)\" created it$")
    public void itShouldStateThatUserCreatedIt(Users user) {
        timelineTab.assertTopNoteSignatureContainsCreator(user);
    }

    @Then("^a log should be at the top of the timeline$")
    public void aLogShouldBeAtTheTopOfTheTimeline() {
        timelineTab.assertLogAtTopOfTimeline();
    }

    @Then("^there should be a log showing the case was allocated to user \"([^\"]*)\" at stage \"([^\"]*)\"$")
    public void thereShouldBeALogShowingTheCaseWasAllocatedToUserAtStage(Users user, String stage) {
        timelineTab.assertAllocationLogVisible(user, stage);
    }

    @And("^a log should be visible for completing the \"([^\"]*)\" stage$")
    public void aLogShouldBeVisibleForCompletingTheStage(String stage) {
        timelineTab.assertStageCompletionLogVisible(stage);
    }

    @And("^a log should be visible for starting the \"([^\"]*)\" stage$")
    public void aLogShouldBeVisibleForStartingTheStage(String stage) {
        timelineTab.assertStageStartedLogVisible(stage);
    }

    @And("^I attempt to add an empty case note$")
    public void iAttemptToAddAnEmptyCaseNote() {
        timelineTab.clickAddCaseNote();
        clickAddButton();
    }

    @Then("^an error message is displayed as I have not entered text content for the case note$")
    public void anErrorMessageIsDisplayedAsIHaveNotEnteredTextContentForTheCaseNote() {
        timelineTab.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @And("^I create another case note with random content$")
    public void iCreateAnotherCaseNoteWithRandomContent() {
        timelineTab.createAnotherCaseNote();
    }

    @Then("^the top note in the timeline should be the second note created$")
    public void theTopNoteInTheTimelineShouldBeTheSecondNoteCreated() {
        timelineTab.assertTopNoteContainsEnteredText(sessionVariableCalled("secondNoteContents"));
    }

    @And("^the one below it should be the first note created$")
    public void theOneBelowItShouldBeTheFirstNoteCreated() {
        timelineTab.assertSecondNoteContainsEnteredText(sessionVariableCalled("createdNoteContents"));
    }
}
