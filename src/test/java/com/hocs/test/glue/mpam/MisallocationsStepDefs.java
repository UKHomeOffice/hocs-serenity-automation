package com.hocs.test.glue.mpam;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.Misallocations;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MisallocationsStepDefs extends BasePage {

    Creation creation;

    Misallocations misallocations;

    @And("I select to transfer a case to {string}")
    public void iSelectToTransferACaseTo(String transferTo) {
        creation.transferCaseToStage(transferTo);
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
}
