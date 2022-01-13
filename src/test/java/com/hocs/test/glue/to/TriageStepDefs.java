package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.Triage;
import io.cucumber.java.en.And;
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
}

