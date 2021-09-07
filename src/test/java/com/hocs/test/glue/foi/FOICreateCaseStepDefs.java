package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.FOICreateCase;
import io.cucumber.java.en.And;

public class FOICreateCaseStepDefs extends BasePage {

    FOICreateCase foiCreateCase;

    @And("I select how the request was received")
    public void iSelectHowTheRequestWasReceived() {
        foiCreateCase.selectCorrespondenceInboundChannel();
    }

    @And("I enter the correspondent details")
    public void iEnterTheCorrespondentDetails() {
        foiCreateCase.enterCorrespondentDetails();
    }

    @And("I select the {string} FOI topic")
    public void iSelectTheFOITopic(String topic) {
        foiCreateCase.selectFOITopic(topic);
    }

    @And("I enter the Request Question")
    public void iEnterTheRequestQuestion() {
        foiCreateCase.enterRequestQuestion();
    }
}
