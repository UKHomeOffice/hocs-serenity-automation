package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class QAResponseStepDefs {

    Page page;

    Homepage homepage;

    QAResponse qaResponse;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    @When("^I complete the QA response stage$")
    public void completeQAResponseStage() {
        page.clickOn(homepage.animalsInScienceTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(qaResponse.QAAcceptRadioButton);
        System.out.println("Finished QA Response, returning to home page.");
        page.clickOn(qaResponse.continueButton);
    }

    @When("^I reject the case at the QA Response stage$")
    public void rejectAtQaResponse(){
        page.clickOn(homepage.animalsInScienceTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(qaResponse.QARejectRadioButton);
        page.clickOn(qaResponse.continueButton);
        qaResponse.enterDraftDecision();
        page.clickOn(qaResponse.finishButton);
    }

    @When("^I click the continue button on the do you approve the QA response screen$")
    public void clickContinueButtonOnApproveResponseScreen() {
        page.clickOn(qaResponse.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected a radio button on the QA approve response screen$")
    public void assertThatQAApproveResponseErrorMessageIsShown() {
        qaResponse.assertQADoYouApproveErrorMessage();
    }

    @When("^I click the finish button on the QA response feedback screen$")
    public void clickFinishButtonOnQAResponseFeedbackScreen() {
        page.clickOn(qaResponse.QARejectRadioButton);
        page.clickOn(qaResponse.continueButton);
        page.clickOn(qaResponse.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered feedback in the text box for the disapproved QA response$")
    public void assertThatQAResponseFeedbackErrorMessageIsShown() {
        qaResponse.assertQAWhatIsYourFeedbackErrorMessage();
    }
}
