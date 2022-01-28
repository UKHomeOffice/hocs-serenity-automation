package com.hocs.test.glue.mpam;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.TimelineTab;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.mpam.Misallocations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MisallocationsStepDefs extends BasePage {

    Misallocations misallocations;

    TimelineTab timelineTab;

    Workdays workdays;

    @And("I select to transfer a case to {string} at the {string} stage")
    public void iSelectToTransferACaseTo(String transferTo, String stage) {
        misallocations.transferCaseFromStageTo(stage, transferTo);
    }

    @And("I amend the Transfer due date of the case")
    public void iAmendTheTransferDueDateOfTheCaseTo() {
        misallocations.updateTransferDueDate(workdays.getDateXWorkdaysFromTodayForGivenCaseType(10, "MPAM"));
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
}
