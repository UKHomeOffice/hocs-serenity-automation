package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.DataInput;
import com.hocs.test.pages.to.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class TriageStepDefs extends BasePage {

    DataInput dataInput;

    Triage triage;

    @When("I set an Enquiry Subject and Reason")
    public void iSetAnEnquirySubjectAndReason() {
        triage.selectAnEnquirySubject();
        triage.selectAnEnquiryReason();
    }

    @And("I select a Business Unit Type and corresponding Business Unit")
    public void iSelectABusinessUnitTypeAndCorrespondingBusinessUnit() {
        triage.selectABusinessUnitType();
        triage.selectABusinessUnit();
    }

    @And("I confirm the case is ready to be drafted")
    public void iConfirmTheCaseIsReadyToBeDrafted() {
        selectTheStageAction("Ready to draft");
        clickFinishButton();
    }

    @And("I change the Business Area of the TO case to {string}")
    public void iChangeTheBusinessAreaOfTheCase(String businessArea) {
        triage.selectToChangeTheBusinessArea();
        waitForDECSPageWithTitle("Transfer To Business Area");
        dataInput.selectSpecificBusinessArea(businessArea);
        dataInput.enterReallocationReason();
        clickFinishButton();
    }

    @And("I change the channel the correspondence was received by")
    public void iChangeTheChannelTheCorrespondenceWasReceivedBy() {
        triage.selectADifferentChannelReceived();
    }

    @And("I save the changes")
    public void iSaveTheChanges() {
        selectSpecificRadioButton("Save changes");
        clickFinishButton();
    }

    @When("I select to close the Treat Official case")
    public void iSelectToCloseTheTreatOfficialCase() {
        selectTheStageAction("Close case");
        clickFinishButton();
    }

    @And("I select a reason to close the case")
    public void iSelectAReasonToCloseTheCase() {
        triage.selectAClosureReason();
    }

    @And("I submit supporting details for the closure")
    public void iSubmitSupportingDetailsForTheClosure() {
        triage.enterClosureDetails();
        clickConfirmButton();
    }
}

