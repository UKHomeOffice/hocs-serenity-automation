package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsQA;
import io.cucumber.java.en.And;

public class ComplaintsQAStepDefs extends BasePage {

    ComplaintsQA compQA;

    @And("I {string} the response at the Service QA stage")
    public void responseAtServiceQAStage(String action) {
        compQA.selectActionAtServiceQA(action);
        if (action.equalsIgnoreCase("REJECT")) {
            compQA.submitRejectionReason();
        }
    }

}
