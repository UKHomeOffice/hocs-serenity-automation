package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.ServiceQA;
import io.cucumber.java.en.And;

public class ServiceQAStepDefs extends BasePage {

    ServiceQA serviceQA;

    @And("I {string} the response at the Service QA stage")
    public void responseAtServiceQAStage(String action) {
        serviceQA.selectActionAtServiceQA(action);
        if (action.equalsIgnoreCase("REJECT")) {
            serviceQA.submitRejectionReason();
        }
    }

}
