package com.hocs.test.glue.complaints;

import com.hocs.test.pages.complaints.ComplaintsRegistrationAndDataInput;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsDispatchAndSend;
import io.cucumber.java.en.And;

public class ComplaintsDispatchAndSendStepDefs extends BasePage {

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    @And("I select a Case/Dispatch Outcome")
    public void iSelectACaseOutcome() {
        complaintsDispatchAndSend.selectACaseOutcome();
    }

    @And("I select the complaint category and reason")
    public void iEnterTheComplaintCategoryAndReason() {
            complaintsRegistrationAndDataInput.selectComplaintCategory();
            complaintsRegistrationAndDataInput.selectComplaintReason();
    }

    @And("I select the new complaint category and reason")
    public void iEnterTheNewComplaintCategoryAndReason() {
        complaintsRegistrationAndDataInput.selectNewComplaintCategory();
        complaintsRegistrationAndDataInput.selectNewComplaintReason();
    }

    @And("I submit the Response details")
    public void iEnterTheResponseDetails() {
        if (!iedetCase() && !smcCase()) {
            complaintsDispatchAndSend.selectAResponseChannel();
        }
        complaintsDispatchAndSend.enterADateOfResponse();
        if (!pogrCase() && !pogr2Case()) {
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
        waitForDECSPageWithTitle("Complaint Send Response");
        complaintsDispatchAndSend.assertReasonsForComplaintAreVisible();
    }

    @And("I select if a refund is required")
    public void iSelectIfARefundIsRequired() {
        complaintsDispatchAndSend.selectIfRefundRequired();
    }

    @And("I enter details of any Gratis offered")
    public void iEnterDetailsOfAnyGratisOffered() {
        complaintsDispatchAndSend.enterGratisOfferedDetails();
    }
    @And("I complete the input screen")
    public void iCompleteTheInputScreen() {
        clickTheButton("Close");
        enterTextIntoTextAreaWithHeading("Enter note for case completion");
        clickTheButton("Complete case");
    }

    @And("I select an action to {string}")
    public void iSelectAnActionTo(String action) {
        if(action.equalsIgnoreCase("Close the case")){
            complaintsDispatchAndSend.selectAction(action);
        } else if (action.equalsIgnoreCase("Return to triage" )) {
            complaintsDispatchAndSend.selectAction(action);
        }
    }
}