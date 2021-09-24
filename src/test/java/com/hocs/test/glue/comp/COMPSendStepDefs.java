package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPSend;
import io.cucumber.java.en.And;

public class COMPSendStepDefs extends BasePage {

    COMPSend compSend;

    @And("I select a Case Outcome at the {string} Send stage")
    public void iSelectACaseOutcomeAtTheServiceSendStage(String complaintType) {
        compSend.submitASelectedOutcome();
    }
}