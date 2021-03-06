package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MinisterialSignOffStepDefs extends BasePage {

    Dashboard dashboard;

    MinisterialSignOff ministerialSignOff;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the Ministerial Sign Off stage")
    public void completeTheMinisterSignOffStagePerCaseType() {
        if (!ministerialSignOff.ministerSignOffAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        safeClickOn(ministerialSignOff.ministerSignOffAcceptRadioButton);
        safeClickOn(ministerialSignOff.continueButton);
    }

    @And("I return the case at Ministerial Sign Off to Private Office Approval")
    public void iReturnCaseAtMinisterialSignOffToPrivateOfficeApproval() {
        ministerialSignOff.moveCaseFromMinisterSignOffToPrivateOfficeApproval();
    }

    @Then("an error message should be displayed as I have not selected a radio button on the approve response screen")
    public void assertThatApproveResponseErrorMessageIsShown() {
        ministerialSignOff.assertDoYouApproveTheResponseErrorMessage();
    }

    @Then("an error message should be displayed as I have not entered feedback in the text box")
    public void assertThatFeedbackResponseMinisterSignOffErrorMessageIsShown() {
        ministerialSignOff.assertFeedbackResponseMinisterSignOffErrorMessage();
    }

}
