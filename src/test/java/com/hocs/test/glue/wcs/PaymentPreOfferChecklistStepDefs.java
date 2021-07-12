package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.PaymentPreOfferChecklist;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaymentPreOfferChecklistStepDefs {

    PaymentPreOfferChecklist paymentPreOfferChecklist;

    @When("I select to return the claim to QA")
    public void iSelectToReturnTheClaimToQA() {
        paymentPreOfferChecklist.returnToQAForCorrections();
    }

    @When("I select to send the claim to offer approval")
    public void iSelectToSendTheClaimToOfferApproval() {
        paymentPreOfferChecklist.selectPaymentSendForApproval();
    }

    @Then("an error message is displayed as I have not selected an option at the Payment Pre-Offer Checklist stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtThePaymentPreOfferChecklistStage() {
        paymentPreOfferChecklist.assertPaymentIsRequiredPreOfferErrorMessage();
    }
}
