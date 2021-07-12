package com.hocs.test.glue.ukvi;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.TimelineTab;
import com.hocs.test.pages.ukvi.Misallocations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MisallocationsStepDefs extends BasePage {

    Misallocations misallocations;

    TimelineTab timelineTab;

    @And("I select to transfer a case to {string} at the {string} stage")
    public void iSelectToTransferACaseTo(String transferTo, String stage) {
        misallocations.transferCaseFromStageTo(stage, transferTo);
    }

    @And("I amend the Transfer due date of the case to {string}")
    public void iAmendTheTransferDueDateOfTheCaseTo(String date) {
        misallocations.updateTransferDueDate(date);
    }

    @And("I select the {string} action at the Awaiting Transfer stage")
    public void iSelectTheActionAtTheTransferStage(String action) {
        misallocations.selectActionAtTransferStage(action);
    }

    @And("I complete the required fields for Triage and move the case to Triage")
    public void iCompleteTheRequiredFieldsForTriageAndMoveTheCaseToTriage() {
        misallocations.completeRequiredFieldsForTriage();
    }

    @Then("the Transfer due date of the case should be updated")
    public void theTransferDueDateOfTheCaseShouldBeUpdated() {
        misallocations.assertDueDateHasBeenUpdated();
    }

    @Then("the reason for transfer is displayed in a case note in the case timeline")
    public void theReasonForTransferIsDisplayedInACaseNoteInTheCaseTimeline() {
        safeClickOn(timelineTab.timelineTab);
        timelineTab.assertCaseTransferReason();
    }
}
