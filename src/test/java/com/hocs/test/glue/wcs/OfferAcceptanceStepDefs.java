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

    @When("I select move claim to Eligibility")
    public void iSelectMoveClaimToEligibility() {
        offerAcceptance.selectMoveToEligibility();
    }

    @When("I select move claim to Triage")
    public void iSelectMoveClaimToTriage() {
        offerAcceptance.selectMoveToTriage();
    }

    @When("I select move claim to Casework")
    public void iSelectMoveClaimToCasework() {
        offerAcceptance.selectMoveToCasework();
    }

    @Then("an error message is displayed as I have not selected an option at the Offer Acceptance stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheOfferAcceptanceStage() {
        offerAcceptance.assertPaymentIsRequiredOfferAcceptanceErrorMessage();
    }
}
