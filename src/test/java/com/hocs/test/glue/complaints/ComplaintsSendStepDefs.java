package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsSend;
import io.cucumber.java.en.And;

public class ComplaintsSendStepDefs extends BasePage {

    ComplaintsSend complaintsSend;

    @And("I select a Case Outcome")
    public void iSelectACaseOutcome() {
        complaintsSend.selectACaseOutcome();
    }

    @And("I submit the Response details")
    public void iEnterTheRepsonseDetails() {
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
    }
}