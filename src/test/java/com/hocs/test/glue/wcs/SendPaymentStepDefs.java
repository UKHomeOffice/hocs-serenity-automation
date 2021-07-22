package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.wcs.SendPayment;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendPaymentStepDefs extends BasePage {

    SendPayment sendPayment;

    @When("I select that an interim payment has been sent")
    public void iSelectThatAnInterimPaymentHasBeenSent() {
        sendPayment.selectInterimPaymentSent();
    }

    @When("I select that a final payment has been sent")
    public void iSelectThatAFinalPaymentHasBeenSent() {
        sendPayment.selectFinalPaymentSent();
    }

    @Then("an error message is displayed as I have not selected an option at the Send Payment stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheSendPaymentStage() {
        sendPayment.assertPaymentIsRequiredSendPaymentErrorMessage();
    }
}
