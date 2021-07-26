package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.OfferApproval;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OfferApprovalStepDefs {

    OfferApproval offerApproval;

    @When("I select to return claim to the Casework team due to PNC result")
    public void iSelectToReturnClaimToTheCaseworkTeamDueToPNCResult() {
        offerApproval.returnToCaseworkTeam();
    }

    @When("I select to Approve the offer")
    public void iSelectToApproveTheOffer() {
        offerApproval.selectPaymentOfferApproved();
    }

    @When("I select to return the claim to QA for corrections")
    public void iSelectToReturnTheClaimToQAForCorrections() {
        offerApproval.returnToQa();
    }

    @Then("an error message is displayed as I have not selected an option at the Offer Approval stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheOfferApprovalStage() {
        offerApproval.assertPaymentIsRequiredOfferApprovalErrorMessage();
    }
}
