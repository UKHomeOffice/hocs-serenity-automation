package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.PaymentPreparation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaymentPreparationStepDefs {

    PaymentPreparation paymentPreparation;

    @Then("an error message is displayed as I have not selected an option at the Payment Preparation stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtThePaymentPreparationStage() {
        paymentPreparation.assertPaymentPrepStatusErrorMessage();
    }

    @When("I select to send the claim to Payment Approval")
    public void iSelectToSendTheClaimToPaymentApproval() {
        paymentPreparation.selectToSendClaimToOfferApproval();
    }
}
