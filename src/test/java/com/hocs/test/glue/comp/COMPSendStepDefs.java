package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.COMPSend;
import io.cucumber.java.en.And;

public class COMPSendStepDefs extends BasePage {

    COMPSend compSend;

    @And("I select a Case Outcome")
    public void iSelectACaseOutcome() {
        compSend.selectACaseOutcome();
    }

    @And("I submit the Response details")
    public void iEnterTheRepsonseDetails() {
        compSend.selectAResponseChannel();
        compSend.enterADateOfResponse();
        clickTheButton("Complete");
    }
}