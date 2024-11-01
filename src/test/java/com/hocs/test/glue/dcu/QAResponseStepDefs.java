package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;


public class QAResponseStepDefs extends BasePage {

    QAResponse qaResponse;

    CaseView caseView;

    SummaryTab summaryTab;

    @And("I select to modify the primary draft")
    public void iSelectToModifyThePrimaryDraft() {
        qaResponse.selectModifyPrimaryDraftRadioButton();
        clickContinueButton();
    }

    @And("the case should( still)( be owned by)( be returned to) the Private Office team")
    public void theCaseShouldBeOwnedByThePrivateOfficeTeam() {
        openOrCloseAccordionSection("Markup");
        String privateOfficeTeam = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Private Office Team").get(0);
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(privateOfficeTeam, "Team");
    }

    @And("I approve the Primary Draft")
    public void iApproveThePrimaryDraft() {
        qaResponse.selectApprovePrimaryDraftRadioButton();
        clickContinueButton();
    }

    @And("I reject the case at the QA Response stage")
    public void iRejectTheCaseAtTheQAResponseStage() {
        qaResponse.selectReturnCaseToDraftingTeamRadioButton();
        clickContinueButton();
        qaResponse.enterRejectionReason();
        clickFinishButton();
    }
}
