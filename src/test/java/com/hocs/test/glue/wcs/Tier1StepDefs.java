package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.wcs.Tier1AwaitingResponse;
import com.hocs.test.pages.wcs.Tier1Review;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Tier1StepDefs extends BasePage {

    Tier1Review tier1Review;

    Tier1AwaitingResponse tier1AwaitingResponse;

    @And("I select to progress the claim")
    public void iSelectToProgressTheClaim() {
        tier1Review.selectToProgressClaim();
    }

    @Then("an error message is displayed as I have not selected an action on the Tier 1 Review page")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnActionOnTheTierReviewPage() {
        tier1Review.assertActionIsRequiredErrorMessage();
    }

    @When("I select that the claimant accepted the offer")
    public void iSelectThatTheClaimantAcceptedTheOffer() {
        tier1AwaitingResponse.selectClaimantAcceptsOffer();
    }

    @When("I select that the claimant rejected the interim or preliminary offer, or the offer was rejected")
    public void iSelectThatTheClaimantRejectedTheInterimOffer() {
        tier1AwaitingResponse.selectClaimantRejectsNonFinalOffer();
    }

    @When("I select that the claimant rejected the final offer")
    public void iSelectThatTheClaimantRejectedTheFinalOffer() {
        tier1AwaitingResponse.selectClaimantRejectsFinalOffer();
    }

    @Then("an error message is displayed as I have not selected an option on the Tier 1 Awaiting Response page")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionOnTheTierAwaitingResponsePage() {
        tier1AwaitingResponse.assertResponseFromClaimantIsRequiredErrorMessage();
    }

    @And("I enter a Tier 1 review withdrawal outcome and decision date")
    public void iEnterATier1ReviewWithdrawalOutcomeAndDecisionDate() {
        tier1Review.selectAWithdrawDecision();
        tier1Review.enterOfferWithdrawnDate(getTodaysDate());
    }

    @And("I enter a interim first outcome and decision date")
    public void iEnterAInterimFirstOutcomeAndDecisionDate() {
        tier1Review.selectAInterimFirstOutcome();
        tier1Review.enterInterimFirstDecisionDate(getTodaysDate());
    }

    @And("I enter a interim  second outcome and decision date")
    public void iEnterAInterimSecondOutcomeAndDecisionDate() {
        tier1Review.selectAInterimSecondOutcome();
        tier1Review.enterInterimSecondDecisionDate(getTodaysDate());
    }

    @And("I enter a final first outcome and decision date")
    public void iEnterAFinalFirstOutcomeAndDecisionDate() {
        tier1Review.selectAFinalFirstOutcome();
        tier1Review.enterFinalFirstDecisionDate(getTodaysDate());
    }

    @And("I enter a final second outcome and decision date")
    public void iEnterAFinalSecondOutcomeAndDecisionDate() {
        tier1Review.selectAFinalSecondOutcome();
        tier1Review.enterFinalSecondDecisionDate(getTodaysDate());
    }

    @Then("error messages are displayed as I have not entered any outcomes")
    public void errorMessagesAreDisplayedAsIHaveNotEnteredAnyOutcomes() {
        tier1Review.assertWithdrawnOutcomeIsRequiredErrorMessage();
        tier1Review.assertInterimFirstOutcomeIsRequiredErrorMessage();
        tier1Review.assertFinalFirstOutcomeIsRequiredErrorMessage();
        tier1Review.assertInterimSecondOutcomeIsRequiredErrorMessage();
        tier1Review.assertFinalSecondOutcomeIsRequiredErrorMessage();
    }

    @And("I complete the Tier 1 Review page")
    public void iCompleteTheTierReviewPage() {
        iEnterATier1ReviewWithdrawalOutcomeAndDecisionDate();
        iEnterAInterimFirstOutcomeAndDecisionDate();
        iEnterAInterimSecondOutcomeAndDecisionDate();
        iEnterAFinalFirstOutcomeAndDecisionDate();
        iEnterAFinalSecondOutcomeAndDecisionDate();
        iSelectToProgressTheClaim();
    }

    @When("I select to return the claim to Eligibility")
    public void iSelectToReturnTheClaimToEligibility() {
        tier1Review.selectToReturnClaimToEligibility();
    }

    @When("I select to return the claim to Registration")
    public void iSelectToReturnTheClaimToRegistration() {
        tier1Review.selectToReturnClaimToRegistration();
    }

    @Then("an error message is displayed as I have not selected an action on the Tier 1 ER/IR Review page")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnActionOnTheTierERReviewPage() {
        tier1Review.assertResultOfReviewIsRequiredErrorMessage();
    }
}
