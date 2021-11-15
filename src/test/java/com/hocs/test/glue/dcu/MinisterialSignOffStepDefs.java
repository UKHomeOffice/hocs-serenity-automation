package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class MinisterialSignOffStepDefs extends BasePage {

    MinisterialSignOff ministerialSignOff;

    @And("I select that the case is not applicable for Ministerial sign-off")
    public void iSelectThatTheCaseIsNotApplicableForMinisterialSignOff() {
        ministerialSignOff.selectNotApplicableToApproveResponse();
        safeClickOn(continueButton);
    }

    @And("I do not approve the response at the Ministerial Sign Off stage")
    public void iDoNotApproveTheResponseAtTheMinisterialSignOffStage() {
        ministerialSignOff.selectToApproveResponse("No");
        safeClickOn(continueButton);
        ministerialSignOff.enterRejectionReason();
        safeClickOn(continueButton);
    }

    @When("I approve the response at the Ministerial Sign Off stage")
    public void iApproveTheResponseAtTheMinisterialSignOffStage() {
        ministerialSignOff.selectToApproveResponse("Yes");
        safeClickOn(continueButton);
    }

    @And("I submit a reason why it is not applicable")
    public void iSubmitAReasonWhyItIsNotApplicable() {
        ministerialSignOff.enterNotApplicableReason();
        safeClickOn(continueButton);
    }
}
