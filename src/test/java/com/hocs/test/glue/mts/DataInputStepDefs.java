package com.hocs.test.glue.mts;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mts.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    AddCorrespondent addCorrespondent;

    @And("I complete the Data Input stage and close the MTS Case")
    public void iCompleteTheCaseDetailsStageOfTheMTSCase() {
        dataInput.completeDataInputStageAndCloseMTSCase();
    }

    @And("I put the MTS case on hold")
    public void iPutTheMTSCaseOnHold() {
        dataInput.putMTSCaseOnHold();
    }

    @And("I trigger the {string} error at the MTS Data Input stage")
    public void iTriggerTheErrorAtTheMTSCaseDetailsStage(String errorMessage) {
        dataInput.triggerErrorMessage(errorMessage);
    }

    @And("I check the validation at the MTS Data Input stage")
    public void iTriggerAllErrorMessagesAtTheMTSDataInputStage() {
        safeClickOn(continueButton);
        dataInput.assertErrorMessageIsDisplayed("Primary Correspondent");
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        safeClickOn(continueButton);
        safeClickOn(continueButton);
        dataInput.assertErrorMessageIsDisplayed("Business Area");
        dataInput.assertErrorMessageIsDisplayed("Business Unit");
        dataInput.assertErrorMessageIsDisplayed("Urgency");
        dataInput.assertErrorMessageIsDisplayed("Channel Received");
        dataInput.assertErrorMessageIsDisplayed("Enquiry Subject");
        dataInput.assertErrorMessageIsDisplayed("Enquiry Reason");
        dataInput.assertErrorMessageIsDisplayed("Actions");
    }

    @Then("the {string} error message should be displayed at MTS Data Input Stage")
    public void theErrorMessageShouldBeDisplayedAtMTSCaseDetailsStage(String errorMessage) {
        dataInput.assertErrorMessageIsDisplayed(errorMessage);
    }
}
