package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class QAResponseStepDefs {

    Page page;

    Homepage homepage;

    QAResponse qaResponse;

    DataInput dataInput;

    SuccessfulCaseCreation successfulCaseCreation;

    @When("^I complete the QA response stage$")
    public void completeQAResponseStage() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpathDoubleClick();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        qaResponse.clickQAResponseAcceptRadioButton();
        System.out.println("Finished QA Response, returning to home page.");
        qaResponse.clickContinueButton();
    }

    @When("^I reject the case at the QA Response stage$")
    public void rejectAtQaResponse(){
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        qaResponse.clickQAResponseRejectRadioButton();
        qaResponse.clickContinueButton();
        qaResponse.enterDraftDecision();
        qaResponse.clickFinishButton();
    }

    @When("^I click the continue button on the do you approve the QA response screen$")
    public void clickContinueButtonOnApproveResponseScreen() {
        qaResponse.clickContinueButton();
    }

    @Then("^an error message should be displayed as I have not selected a radio button on the QA approve response screen$")
    public void assertThatQAApproveResponseErrorMessageIsShown() {
        qaResponse.assertQADoYouApproveErrorMessage();
    }

    @When("^I click the finish button on the QA response feedback screen$")
    public void clickFinishButtonOnQAResponseFeedbackScreen() {
        qaResponse.clickQAResponseRejectRadioButton();
        qaResponse.clickContinueButton();
        qaResponse.clickFinishButton();
    }

    @Then("^an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response$")
    public void assertThatQAResponseFeedbackErrorMessageIsShown() {
        qaResponse.assertQAWhatIsYourFeedbackErrorMessage();
    }
}
