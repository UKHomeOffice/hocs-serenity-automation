package com.hocs.test.glue.comp;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.comp.ComplaintClosed;
import io.cucumber.java.en.And;

public class ComplaintClosedStepDefs extends BasePage {

    ComplaintClosed complaintClosed;

    @And("I select the {string} action at Complaint Closed")
    public void iSelectTheActionAtComplaintClosed(String action) {
        complaintClosed.selectActionAtComplaintClosed(action);
    }

    @And("I enter a completion note at Complaint Closed")
    public void iEnterACompletionNoteAtComplaintClosed() {
        complaintClosed.enterReasonForCaseCompletion();
    }

    @And("I confirm I want to close the case at Complaint Closed")
    public void iConfirmIWantToCloseTheCaseAtComplaintClosed() {
        complaintClosed.selectActionAtCompleteConfirmation("Yes");
    }

}
