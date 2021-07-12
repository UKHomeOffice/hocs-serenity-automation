package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.ArchivedRejected;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArchivedRejectedStepDefs {

    ArchivedRejected archivedRejected;

    @When("I select to return the claim to Registration")
    public void iSelectToReturnTheClaimToRegistration() {
        archivedRejected.selectToRestoreClaimToRegistration();
    }

    @When("I select to return the claim to Eligibility")
    public void iSelectToReturnTheClaimToEligibility() {
        archivedRejected.selectToRestoreClaimToEligibility();
    }

    @When("I select to permanently close the claim")
    public void iSelectToPermanentlyCloseTheClaim() {
        archivedRejected.selectToPermanentlyCloseClaim();
    }


    @Then("an error message is displayed as I have not selected an option at the Archived Identity Rejected stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheArchivedIdentityRejectedStage() {
        archivedRejected.assertArchiveIdentityRejectedIsRequiredErrorMessage();
    }

    @Then("an error message is displayed as I have not selected an option at the Archived Eligibility Rejected stage")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionAtTheArchivedEligibilityRejectedStage() {
        archivedRejected.assertArchiveEligibilityRejectedIsRequiredErrorMessage();
    }
}
