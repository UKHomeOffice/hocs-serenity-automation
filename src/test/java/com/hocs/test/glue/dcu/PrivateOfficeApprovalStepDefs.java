package com.hocs.test.glue.dcu;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import io.cucumber.java.en.And;

public class PrivateOfficeApprovalStepDefs extends BasePage {

    Dashboard dashboard;

    PrivateOfficeApproval privateOfficeApproval;

    DCUProgressCase dcuProgressCase;

    Markup markup;

    @And("I override the Primary Topic of the case at the Private Office stage to {string}")
    public void iOverrideTheOfTheCaseAtThePrivateOfficeStage(String topic) {
        privateOfficeApproval.selectToChangeTopic();
        safeClickOn(continueButton);
        markup.addTopicToCase(topic);
        markup.selectPrimaryTopic(topic);
        privateOfficeApproval.enterAReasonForChangingTopic();
        safeClickOn(continueButton);
    }

    @And("I select to change minister")
    public void iSelectToChangeMinister() {
        privateOfficeApproval.selectToChangeMinister();
        safeClickOn(continueButton);
    }

    @And("I select {string} as the new Private Office team")
    public void iSelectAsTheNewMinister(String newPOTeam) {
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(newPOTeam);
    }

    @And("I submit a reason for changing Private Office team")
    public void iEnterAsTheReasonForChangingPrivateOfficeTeam() {
        privateOfficeApproval.enterAReasonForChangingPOTeam();
        safeClickOn(finishButton);
    }

    @And("I change the minister to {string}")
    public void iChangeTheMinisterTo(String minister) {
        privateOfficeApproval.selectToChangeMinister();
        safeClickOn(continueButton);
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(minister);
        privateOfficeApproval.enterAReasonForChangingPOTeam();
        safeClickOn(finishButton);
    }

    @And("I advance the case to the Private Office Approval stage")
    public void iAdvanceTheCaseToThePrivateOfficeApprovalStage() {
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromInitialDraftToQaResponse();
        dcuProgressCase.moveCaseFromQAResponseToPrivateOfficeApprovalOrDispatch();
    }

    @And("I reject the case at the Private Office Approval stage")
    public void iRejectTheCaseAtThePrivateOfficeApprovalStage() {
        privateOfficeApproval.selectIfApproveResponse("No");
        safeClickOn(continueButton);
        privateOfficeApproval.enterRejectionReason();
        safeClickOn(finishButton);
    }

    @And("I approve the case at the Private Office Approval stage")
    public void iApproveTheCaseAtThePrivateOfficeApprovalStage() {
        privateOfficeApproval.selectIfApproveResponse("Yes");
        safeClickOn(continueButton);
    }
}
