package com.hocs.test.glue;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.workstacks.Workstacks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class QAResponseStepDefs extends Page {

    Homepage homepage;

    QAResponse qaResponse;

    Workstacks workstacks;

    @When("I complete the QA response stage")
    public void completeQAResponseStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(qaResponse.QAAcceptRadioButton);
        System.out.println("Finished QA Response, returning to home page.");
        clickOn(qaResponse.continueButton);
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
