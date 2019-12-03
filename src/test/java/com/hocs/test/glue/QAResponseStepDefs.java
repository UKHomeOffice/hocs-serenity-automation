package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class QAResponseStepDefs extends Page {

    Homepage homepage;

    QAResponse qaResponse;

    Workstacks workstacks;

    @When("^I complete the QA response stage$")
    public void completeQAResponseStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(qaResponse.QAAcceptRadioButton);
        System.out.println("Finished QA Response, returning to home page.");
        clickOn(qaResponse.continueButton);
    }

    @When("^I click the continue button on the do you approve the QA response screen$")
    public void clickContinueButtonOnApproveResponseScreen() {
        clickOn(qaResponse.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected a radio button on the QA approve response screen$")
    public void assertThatQAApproveResponseErrorMessageIsShown() {
        qaResponse.assertQADoYouApproveErrorMessage();
    }

    @When("^I click the finish button on the QA response feedback screen$")
    public void clickFinishButtonOnQAResponseFeedbackScreen() {
        clickOn(qaResponse.QARejectRadioButton);
        clickOn(qaResponse.continueButton);
        clickOn(qaResponse.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response$")
    public void assertThatQAResponseFeedbackErrorMessageIsShown() {
        qaResponse.assertQAWhatIsYourFeedbackErrorMessage();
    }
}
