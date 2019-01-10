package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;

import cucumber.api.java.en.When;


public class QAResponseStepDefs {

    Page page;

    Homepage homepage;

    QAResponse qaResponse;

    DataInput dataInput;

    SuccessfulCaseCreation successfulCaseCreation;

    @When("^I complete the QA response stage$")
    public void completeQAResponseStage() {
        dataInput.selectTeam1();
        successfulCaseCreation.clickSessionVariableViaLinkText();
        qaResponse.clickQAResponseAcceptRadioButton();
        System.out.println("Finished QA Response, returning to home page.");
        page.clickContinueButton();
    }

}
