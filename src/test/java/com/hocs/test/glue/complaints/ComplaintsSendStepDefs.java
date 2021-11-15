package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsSend;
import io.cucumber.java.en.And;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class ComplaintsSendStepDefs extends BasePage {

    ComplaintsSend complaintsSend;

    @And("I select a Case Outcome")
    public void iSelectACaseOutcome() {
        complaintsSend.selectACaseOutcome();
    }

    @And("I submit the Response details")
    public void iEnterTheResponseDetails() {
        if (!iedetCase()) {
            complaintsSend.selectAResponseChannel();
        }
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
    }

    @And("I submit the SMC Send stage")
    public void iCompleteTheSendStage() {
        clickTheButton("Complete");
    }
}