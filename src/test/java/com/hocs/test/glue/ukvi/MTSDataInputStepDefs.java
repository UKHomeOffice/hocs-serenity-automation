package com.hocs.test.glue.ukvi;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.ukvi.MTSDataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MTSDataInputStepDefs extends BasePage {

    MTSDataInput MTSDataInput;

    AddCorrespondent addCorrespondent;

    @And("I complete the Data Input stage and close the MTS Case")
    public void iCompleteTheCaseDetailsStageOfTheMTSCase() {
        MTSDataInput.completeDataInputStageAndCloseMTSCase();
    }

    @And("I trigger the {string} error at the MTS Data Input stage")
    public void iTriggerTheErrorAtTheMTSCaseDetailsStage(String errorMessage) {
        MTSDataInput.triggerErrorMessage(errorMessage);
    }

    @And("I check the validation at the MTS Data Input stage")
    public void iTriggerAllErrorMessagesAtTheMTSDataInputStage() {
        safeClickOn(continueButton);
        MTSDataInput.assertErrorMessageIsDisplayed("Primary Correspondent");
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        safeClickOn(continueButton);
        safeClickOn(continueButton);
        MTSDataInput.assertErrorMessageIsDisplayed("Business Area");
        MTSDataInput.assertErrorMessageIsDisplayed("Business Unit");
        MTSDataInput.assertErrorMessageIsDisplayed("Urgency");
        MTSDataInput.assertErrorMessageIsDisplayed("Channel Received");
        MTSDataInput.assertErrorMessageIsDisplayed("Enquiry Subject");
        MTSDataInput.assertErrorMessageIsDisplayed("Enquiry Reason");
        MTSDataInput.assertErrorMessageIsDisplayed("Your Business Area");
        MTSDataInput.assertErrorMessageIsDisplayed("Date of Surgery");
        MTSDataInput.assertErrorMessageIsDisplayed("Telephone Surgery Official Engagement");
        MTSDataInput.assertErrorMessageIsDisplayed("Actions");
    }

    @Then("the {string} error message should be displayed at MTS Data Input Stage")
    public void theErrorMessageShouldBeDisplayedAtMTSCaseDetailsStage(String errorMessage) {
        MTSDataInput.assertErrorMessageIsDisplayed(errorMessage);
    }
}
