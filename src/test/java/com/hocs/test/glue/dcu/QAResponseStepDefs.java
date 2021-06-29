package com.hocs.test.glue.dcu;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class QAResponseStepDefs extends BasePage {

    Dashboard dashboard;

    QAResponse qaResponse;

    Workstacks workstacks;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the QA response stage")
    public void completeQAResponseStage() {
        if (!qaResponse.QAAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
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
