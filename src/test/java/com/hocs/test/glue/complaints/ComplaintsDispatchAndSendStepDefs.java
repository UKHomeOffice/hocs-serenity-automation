package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsDispatchAndSend;
import io.cucumber.java.en.And;

public class ComplaintsDispatchAndSendStepDefs extends BasePage {

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    @And("I select a Case/Dispatch Outcome")
    public void iSelectACaseOutcome() {
        complaintsDispatchAndSend.selectACaseOutcome();
    }

    @And("I submit the Response details")
    public void iEnterTheResponseDetails() {
        if (!iedetCase() && !smcCase()) {
            complaintsDispatchAndSend.selectAResponseChannel();
        }
        complaintsDispatchAndSend.enterADateOfResponse();
        if (!pogrCase()) {
            clickTheButton("Complete");
        } else {
            clickTheButton("Dispatch and Close case");
        }
    }

    @And("I select a Case Outcome for each Reason for Complaint")
    public void iSelectACaseOutcomeForEachReasonForComplaint() {
        complaintsDispatchAndSend.selectBFCaseOutcomes();
    }

    @And("I can see the selected Reasons for Complaint")
    public void iCanSeeTheSelectedReasonsForComplaint() {
        waitForPageWithTitle("Complaint Send Response");
        complaintsDispatchAndSend.assertReasonsForComplaintAreVisible();
    }
}