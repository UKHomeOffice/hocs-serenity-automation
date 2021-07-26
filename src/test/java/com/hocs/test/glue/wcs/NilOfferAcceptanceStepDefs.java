package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.NilOfferAcceptance;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NilOfferAcceptanceStepDefs {

    NilOfferAcceptance nilOfferAcceptance;

    @When("I select that the nil offer was rejected by the claimant")
    public void iSelectThatTheNilOfferWasRejectedByTheClaimant() {
        nilOfferAcceptance.selectPaymentNilOfferRejected();
    }

    @Then("an error message is displayed as I have not selected an option at the Nil Offer Acceptance stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheNilOfferAcceptanceStage() {
        nilOfferAcceptance.assertPaymentIsRequiredNilOfferErrorMessage();
    }

    @When("I select that there was no challenge to the Nil Offer")
    public void iSelectThatThereWasNoChallengeToTheNilOffer() {
        nilOfferAcceptance.selectPaymentNoChallenge();
    }
}
