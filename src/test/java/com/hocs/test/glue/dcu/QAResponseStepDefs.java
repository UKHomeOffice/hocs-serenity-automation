package com.hocs.test.glue.dcu;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.Workstacks;

import com.hocs.test.pages.mpam.QA;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class QAResponseStepDefs extends BasePage {

    Homepage homepage;

    QAResponse qaResponse;

    Workstacks workstacks;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the QA response stage")
    public void completeQAResponseStage() {
        if (!qaResponse.QAAcceptRadioButton.isVisible()) {
            homepage.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        safeClickOn(qaResponse.QAAcceptRadioButton);
        System.out.println("Finished QA Response, returning to home page.");
        safeClickOn(qaResponse.continueButton);
    }

    @Then("an error message should be displayed as I have not selected a radio button on the QA approve response screen")
    public void assertThatQAApproveResponseErrorMessageIsShown() {
        qaResponse.assertQADoYouApproveErrorMessage();
    }

    @Then("an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response")
    public void assertThatQAResponseFeedbackErrorMessageIsShown() {
        qaResponse.assertQAWhatIsYourFeedbackErrorMessage();
    }

    @And("I select to modify the primary draft")
    public void iSelectToModifyThePrimaryDraft() {
        qaResponse.clickQAResponseModifyRadioButton();
        safeClickOn(continueButton);
    }
}
