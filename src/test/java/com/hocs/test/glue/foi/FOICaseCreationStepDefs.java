package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.FOICaseCreation;
import io.cucumber.java.en.And;

public class FOICaseCreationStepDefs extends BasePage {

    FOICaseCreation foiCaseCreation;

    @And("I select how the request was received")
    public void iSelectHowTheRequestWasReceived() {
        foiCaseCreation.selectCorrespondenceInboundChannel();
    }

    @And("I enter the correspondent details")
    public void iEnterTheCorrespondentDetails() {
        foiCaseCreation.enterCorrespondentDetails();
    }

    @And("I select the FOI topic")
    public void iSelectTheFOITopic() {
        foiCaseCreation.selectFOITopic();
    }

    @And("I enter the Request Question")
    public void iEnterTheRequestQuestion() {
        foiCaseCreation.enterRequestQuestion();
    }

    @And("I select {string} for the validity of the request and continue")
    public void iSelectForTheValidityOfTheRequest(String validity) {
        foiCaseCreation.selectValidityOfRequest(validity);
        clickTheButton("Continue");
    }
}
