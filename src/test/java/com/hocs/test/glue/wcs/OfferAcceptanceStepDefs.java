package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.OfferAcceptance;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OfferAcceptanceStepDefs {

    OfferAcceptance offerAcceptance;

    @When("I select that the offer was accepted by the claimant")
    public void iSelectThatTheOfferWasAcceptedByTheClaimant() {
        offerAcceptance.selectPaymentOfferAccepted();
    }

    @When("I select that the offer was rejected by the claimant")
    public void iSelectThatTheOfferWasRejectedByTheClaimant() {
        offerAcceptance.selectPaymentOfferRejected();
    }

    @Then("an error message is displayed as I have not selected an option at the Offer Acceptance stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheOfferAcceptanceStage() {
        offerAcceptance.assertPaymentIsRequiredOfferAcceptanceErrorMessage();
    }
}
