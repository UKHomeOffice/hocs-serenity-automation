package com.hocs.test.glue.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.comp.ServiceSend;
import io.cucumber.java.en.And;

public class ServiceSendStepDefs extends BasePage {

    ServiceSend serviceSend;

    @And("I select a Case Outcome at the Service Send stage")
    public void iSelectACaseOutcomeAtTheServiceSendStage() {
        serviceSend.moveCaseFromServiceSendToComplaintClosed();
    }
}