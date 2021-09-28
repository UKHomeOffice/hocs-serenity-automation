package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;


public class QAResponseStepDefs extends BasePage {

    QAResponse qaResponse;

    CaseView caseView;

    SummaryTab summaryTab;

    @And("I select to modify the primary draft")
    public void iSelectToModifyThePrimaryDraft() {
        qaResponse.selectModifyPrimaryDraftRadioButton();
        safeClickOn(continueButton);
    }

    @And("the case should be owned by the Private Office team")
    public void theCaseShouldBeOwnedByThePrivateOfficeTeam() {
        openOrCloseAccordionSection("Markup");
        String privateOfficeTeam = caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenHeading("Private Office Team").get(0);
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(privateOfficeTeam, "Team");
    }

    @And("I approve the Primary Draft")
    public void iApproveThePrimaryDraft() {
        qaResponse.selectApprovePrimaryDraftRadioButton();
        safeClickOn(continueButton);
    }
}
