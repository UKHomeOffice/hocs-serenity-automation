package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.to.QA;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class QAStepDefs extends BasePage {

    QA qa;

    Documents documents;

    RecordCaseData recordCaseData;

    @And("I approve the draft response")
    public void iApproveTheDraftResponse() {
        selectTheStageAction("Approve");
        clickFinishButton();
        recordCaseData.addHeadingAndValueRecord("Approval Status", "Approved");
    }

    @When("I select to reject the case back to Triage stage")
    public void iRejectTheCaseBackToTriageStage() {
        selectTheStageAction("Reject, send to triage");
        clickFinishButton();
    }

    @When("I select to reject the case back to Draft stage")
    public void iRejectTheCaseBackToDraftStage() {
        selectTheStageAction("Reject, send to draft");
        clickFinishButton();
    }

    @And("I submit a reason to reject the case")
    public void iSubmitAReasonToRejectTheCase() {
        qa.enterARejectionReason();
        clickTheButton("Reject");
        recordCaseData.addHeadingAndValueRecord("Approval Status", "Rejected");
    }

    @And("I save the change of the primary draft")
    public void iSaveTheChangeOfThePrimaryDraft() {
        documents.recordPrimaryDraftDocument();
        selectTheStageAction("Save changes");
        clickFinishButton();
    }
}
