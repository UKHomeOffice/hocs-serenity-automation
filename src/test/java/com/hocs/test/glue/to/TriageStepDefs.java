package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriageStepDefs extends BasePage {

    Triage triage;

    @When("I set an Enquiry Subject and Reason")
    public void iSetAnEnquirySubjectAndReason() {
        triage.selectSetEnquirySubjectAndReasonLink();
        triage.selectAnEnquirySubject();
        clickTheButton("Continue");
        triage.selectAnEnquiryReason();
        clickTheButton("Continue");
    }

    @And("I select a Business Unit Type and corresponding Business Unit")
    public void iSelectABusinessUnitTypeAndCorrespondingBusinessUnit() {
        triage.selectABusinessUnitType();
        triage.selectABusinessUnit();
    }

    @And("I confirm the case is ready to be drafted")
    public void iConfirmTheCaseIsReadyToBeDrafted() {
        triage.selectTheAction("Ready to draft");
        clickTheButton("Finish");
    }

    @And("I change the Business Area of the case")
    public void iChangeTheBusinessAreaOfTheCase() {
        triage.selectToChangeTheBusinessArea();
        waitForPageWithTitle("Transfer To Business Area");
        triage.selectADifferentBusinessArea();
        clickTheButton("Finish");
    }

    @And("I change the channel the correspondence was received by")
    public void iChangeTheChannelTheCorrespondenceWasReceivedBy() {
        triage.selectADifferentChannelReceived();
    }

    @And("I save the changes")
    public void iSaveTheChanges() {
        triage.selectTheAction("Save changes");
        clickTheButton("Finish");
    }
}

