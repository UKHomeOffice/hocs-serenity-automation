package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.to.Dispatch;
import io.cucumber.java.en.And;

public class DisptachStepDefs extends BasePage {

    Dispatch dispatch;

    Documents documents;

    @And("I enter the details of the dispatch")
    public void iEnterTheDetailsOfTheDispatch() {
        dispatch.enterDateOfDispatch();
        dispatch.selectAFinalDispatchChannel();
        documents.recordFinalResponseDocument();
    }

    @And("I confirm the case is completed")
    public void iConfirmTheCaseIsCompleted() {
        selectTheStageAction("Complete case");
        clickTheButton("Finish");
    }
}
