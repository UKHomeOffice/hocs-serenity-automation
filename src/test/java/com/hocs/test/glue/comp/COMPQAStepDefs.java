package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPQA;
import io.cucumber.java.en.And;

public class COMPQAStepDefs extends BasePage {

    COMPQA COMPQA;

    @And("I {string} the response at the Service QA stage")
    public void responseAtServiceQAStage(String action) {
        COMPQA.selectActionAtServiceQA(action);
        if (action.equalsIgnoreCase("REJECT")) {
            COMPQA.submitRejectionReason();
        }
    }

}
