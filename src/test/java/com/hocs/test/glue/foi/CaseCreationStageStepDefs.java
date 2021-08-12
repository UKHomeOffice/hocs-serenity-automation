package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.CaseCreationStage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CaseCreationStageStepDefs extends BasePage {

    CaseCreationStage caseCreationStage;

    @And("I select {string} for the validity of the request and continue")
    public void iSelectForTheValidityOfTheRequest(String validity) {
        caseCreationStage.selectValidityOfRequest(validity);
        clickTheButton("Continue");
    }

    @And("I edit the {string} case details value at the Case Creation stage")
    public void iEditTheCaseDetailsValue(String value) {
        caseCreationStage.editCaseDetail(value);
    }

    @Then("the details entered when creating/editing the case are displayed")
    public void theDetailsEnteredAreDisplayed() {
        caseCreationStage.assertCaseDetailsAreCorrect();
    }
}
