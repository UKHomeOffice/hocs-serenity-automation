package com.hocs.test.glue.dcu;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import io.cucumber.java.en.And;

public class PrivateOfficeApprovalStepDefs extends BasePage {

    Dashboard dashboard;

    PrivateOfficeApproval privateOfficeApproval;

    DCUProgressCase dcuProgressCase;

    Markup markup;

    CaseView caseView;

    @And("I override the Primary Topic of the case at the Private Office stage to {string}")
    public void iOverrideTheOfTheCaseAtThePrivateOfficeStage(String topic) {
        privateOfficeApproval.selectToChangeTopic();
        clickContinueButton();
        markup.addTopicToCase(topic);
        privateOfficeApproval.enterAReasonForChangingTopic();
        markup.selectPrimaryTopic(topic);
        caseView.allocateToMeLink.waitUntilVisible();
    }

    @And("I select to change minister")
    public void iSelectToChangeMinister() {
        privateOfficeApproval.selectToChangeMinister();
        clickContinueButton();
    }

    @And("I select {string} as the new Private Office team")
    public void iSelectAsTheNewMinister(String newPOTeam) {
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(newPOTeam);
    }

    @And("I submit a reason for changing Private Office team")
    public void iEnterAsTheReasonForChangingPrivateOfficeTeam() {
        privateOfficeApproval.enterAReasonForChangingPOTeam();
        clickFinishButton();
        getButtonElementFromDisplayedText("Finish").waitUntilNotVisible();
    }

    @And("I change the minister to {string}")
    public void iChangeTheMinisterTo(String minister) {
        privateOfficeApproval.selectToChangeMinister();
        clickContinueButton();
        privateOfficeApproval.selectNewPrivateOfficeTeamFromDropdown(minister);
        privateOfficeApproval.enterAReasonForChangingPOTeam();
        clickFinishButton();
        
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
        clickContinueButton();
        privateOfficeApproval.enterRejectionReason();
        clickFinishButton();
    }

    @And("I approve the case at the Private Office Approval stage")
    public void iApproveTheCaseAtThePrivateOfficeApprovalStage() {
        privateOfficeApproval.selectIfApproveResponse("Yes");
        clickContinueButton();
    }
}
