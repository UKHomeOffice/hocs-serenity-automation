package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class MinisterialSignOffStepDefs extends BasePage {

    Dashboard dashboard;

    MinisterialSignOff ministerialSignOff;

    CaseView caseView;

    @When("I complete the Ministerial Sign Off stage")
    public void completeTheMinisterSignOffStagePerCaseType() {
        if (!ministerialSignOff.ministerSignOffAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(caseView.allocateToMeLink);
        }
        safeClickOn(ministerialSignOff.ministerSignOffAcceptRadioButton);
        safeClickOn(continueButton);
    }

    @And("I return the case at Ministerial Sign Off to Private Office Approval")
    public void iReturnCaseAtMinisterialSignOffToPrivateOfficeApproval() {
        ministerialSignOff.moveCaseFromMinisterSignOffToPrivateOfficeApproval();
    }
}
