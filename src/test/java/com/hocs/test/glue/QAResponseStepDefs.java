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
        homepage.findMyQAResponseCase();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
        qaResponse.clickQAResponseAcceptRadioButton();
        System.out.println("Finished QA Response, returning to home page.");
        page.clickContinueButton();
    }

    @When("^I reject the case at the QA Response stage$")
    public void rejectAtQaResponse(){
        homepage.findMyQAResponseCase();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
        qaResponse.clickQAResponseRejectRadioButton();
        System.out.println("Case rejected, entering decision note");
        page.clickContinueButton();
        qaResponse.enterDraftDecision();
        page.clickFinishButton();
    }

}
