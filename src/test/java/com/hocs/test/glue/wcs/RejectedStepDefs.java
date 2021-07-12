package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.Rejected;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RejectedStepDefs {

    Rejected rejected;

    @When("I select to archive the claim")
    public void iSelectToArchiveTheClaim() {
        rejected.selectToArchiveClaim();
    }

    @When("I select to send the claim to Tier 1 review")
    public void iSelectToSendTheClaimToTier1Review() {
        rejected.selectToSendClaimToTier1Review();
    }

    @Then("an error message is displayed as I have not selected an option at the Identity Rejected stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheIdentityRejectedStage() {
        rejected.assertReviewIdentityDecisionIsRequiredErrorMessage();
    }

    @Then("an error message is displayed as I have not selected an option at the Eligibility Rejected stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheEligibilityRejectedStage() {
        rejected.assertReviewEligibilityDecisionIsRequiredErrorMessage();
    }
}
