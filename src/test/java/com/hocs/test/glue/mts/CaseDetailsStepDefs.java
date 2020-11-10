package com.hocs.test.glue.mts;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mts.CaseDetails;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CaseDetailsStepDefs extends BasePage {

    CaseDetails caseDetails;

    @And("I complete the Case Details stage and close the MTS Case")
    public void iCompleteTheCaseDetailsStageOfTheMTSCase() {
        caseDetails.completeDataInputStageAndCloseMTSCase();
    }

    @And("I put the MTS case on hold")
    public void iPutTheMTSCaseOnHold() {
        caseDetails.putMTSCaseOnHold();
    }

    @And("I trigger the {string} error at the MTS Case Details stage")
    public void iTriggerTheErrorAtTheMTSCaseDetailsStage(String errorMessage) {
        caseDetails.triggerErrorMessage(errorMessage);
    }

    @Then("the {string} error message should be displayed at MTS Case Details Stage")
    public void theErrorMessageShouldBeDisplayedAtMTSCaseDetailsStage(String errorMessage) {
        caseDetails.assertErrorMessageIsDisplayed(errorMessage);
    }
}
