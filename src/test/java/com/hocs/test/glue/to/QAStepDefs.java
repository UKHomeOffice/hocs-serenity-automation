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
        qa.selectTheAction("Approve");
        clickTheButton("Finish");
        recordCaseData.addHeadingAndValueRecord("Approval Status", "Approved");
    }

    @When("I select to reject the case back to Triage stage")
    public void iRejectTheCaseBackToTriageStage() {
        qa.selectTheAction("Reject, send to triage");
        clickTheButton("Finish");
    }

    @When("I select to reject the case back to Draft stage")
    public void iRejectTheCaseBackToDraftStage() {
        qa.selectTheAction("Reject, send to draft");
        clickTheButton("Finish");
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
        qa.selectTheAction("Save changes");
        clickTheButton("Finish");
    }
}
