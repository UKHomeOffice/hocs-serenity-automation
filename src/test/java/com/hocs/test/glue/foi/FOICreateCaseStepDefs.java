package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import io.cucumber.java.en.And;

public class FOICreateCaseStepDefs extends BasePage {

    CreateCase createCase;

    @And("I select how the request was received")
    public void iSelectHowTheRequestWasReceived() {
        createCase.selectCorrespondenceInboundChannel();
    }

    @And("I enter the correspondent details")
    public void iEnterTheCorrespondentDetails() {
        createCase.enterCorrespondentDetails();
    }

    @And("I select the {string} FOI topic")
    public void iSelectTheFOITopic(String topic) {
        createCase.selectFOITopic(topic);
    }

    @And("I enter the Request Question")
    public void iEnterTheRequestQuestion() {
        createCase.enterRequestQuestion();
    }
}
