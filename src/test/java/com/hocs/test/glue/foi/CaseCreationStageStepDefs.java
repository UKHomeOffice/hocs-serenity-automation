package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.CaseCreationStage;
import io.cucumber.java.en.And;

public class CaseCreationStageStepDefs extends BasePage {

    CaseCreationStage caseCreationStage;

    @And("I select {string} for the validity of the request and continue")
    public void iSelectForTheValidityOfTheRequest(String validity) {
        caseCreationStage.selectValidityOfRequest(validity);
        clickTheButton("Continue");
    }
}
