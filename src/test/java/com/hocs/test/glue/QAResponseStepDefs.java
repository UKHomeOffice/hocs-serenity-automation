package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;

import cucumber.api.java.en.When;

public class QAResponseStepDefs {

    Page page;

    Homepage homepage;

    QAResponse qaResponse;

    @When("^I complete the QA Response stage$")
    public void completeQAResponseStage() {
        homepage.selectTeam3333QAResponse();
        qaResponse.clickQAResponseAcceptRadioButton();
        page.clickContinueButton();
    }
}
