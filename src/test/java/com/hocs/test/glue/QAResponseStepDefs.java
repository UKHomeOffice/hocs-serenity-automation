package com.hocs.test.glue;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.DCU_Workflow.QAResponse;
import com.hocs.test.pages.Workstacks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class QAResponseStepDefs extends BasePage {

    Homepage homepage;

    QAResponse qaResponse;

    Workstacks workstacks;

    @When("I complete the QA response stage")
    public void completeQAResponseStage() {
        if (homepage.myCases.isVisible()) {
            homepage.getCurrentCase();
            safeClickOn(workstacks.allocateToMeButton);
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

}
