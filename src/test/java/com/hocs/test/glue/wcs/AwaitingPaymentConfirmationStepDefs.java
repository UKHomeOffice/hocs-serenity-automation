package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.AwaitingPaymentConfirmation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AwaitingPaymentConfirmationStepDefs {

    AwaitingPaymentConfirmation awaitingPaymentConfirmation;

    @When("I select that payment confirmation has been received")
    public void iSelectThatPaymentConfirmationHasBeenReceived() {
        awaitingPaymentConfirmation.selectClaimCompletePaymentConfirmed();
        awaitingPaymentConfirmation.yesCloseClaim();
    }

    @Then("an error message is displayed as I have not selected an option at the Awaiting Payment Confirmation stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheAwaitingPaymentConfirmationStage() {
        awaitingPaymentConfirmation.assertClaimCompleteIsRequiredErrorMessage();
    }
}
