package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.When;


public class QAResponseStepDefs {

    Page page;

    Homepage homepage;

    QAResponse qaResponse;

    DataInput dataInput;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    @When("^I complete the QA response stage$")
    public void completeQAResponseStage() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
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
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        qaResponse.clickQAResponseRejectRadioButton();
        qaResponse.clickContinueButton();
        qaResponse.enterDraftDecision();
        qaResponse.clickFinishButton();
    }

}
