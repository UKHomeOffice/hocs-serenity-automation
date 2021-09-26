package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;


public class QAResponseStepDefs extends BasePage {

    Dashboard dashboard;

    QAResponse qaResponse;

    Workstacks workstacks;

    CaseView caseView;

    @When("I complete the QA response stage")
    public void completeQAResponseStage() {
        if (!qaResponse.QAAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(caseView.allocateToMeLink);
        }
        safeClickOn(qaResponse.QAAcceptRadioButton);
        System.out.println("Finished QA Response, returning to home page.");
        safeClickOn(continueButton);
    }

    @And("I select to modify the primary draft")
    public void iSelectToModifyThePrimaryDraft() {
        qaResponse.clickQAResponseModifyRadioButton();
        safeClickOn(continueButton);
    }
}
