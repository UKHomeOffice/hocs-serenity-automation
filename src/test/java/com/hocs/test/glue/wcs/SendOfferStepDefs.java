package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.SendOffer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendOfferStepDefs {

    SendOffer sendOffer;

    @When("I select to record that a monetary offer has been sent")
    public void iSelectToRecordThatAMonetaryOfferHasBeenSent() {
        sendOffer.selectOfferSentToClaimant();
    }

    @When("I select to record that a Nil payment offer has been sent")
    public void iSelectToRecordThatANilPaymentOfferHasBeenSent() {
        sendOffer.selectNilOfferSentToClaimant();
    }

    @When("I select to return the claim to Offer Approval due to PNC failure")
    public void iSelectToReturnTheClaimToOfferApprovalDueToPNCFailure() {
        sendOffer.returnToOfferApproval();
    }

    @Then("an error message is displayed as I have not selected an option at the Send Offer stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheSendOfferStage() {
        sendOffer.assertPaymentIsRequiredSendOfferErrorMessage();
    }
}
