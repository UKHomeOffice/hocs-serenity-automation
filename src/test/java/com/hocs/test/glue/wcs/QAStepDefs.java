package com.hocs.test.glue.wcs;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.wcs.QAOffer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QAStepDefs extends BasePage {

    QAOffer qaOffer;

    @And("I select to approve the offer")
    public void iSelectToApproveTheOffer() {
        qaOffer.selectClaimStatusApproveOffer();
    }

    @When("I select to return claim to the Casework team")
    public void iSelectToReturnClaimToCaseworker() {
        qaOffer.returnClaimToCaseworkStage();
    }

    @Then("an error message is displayed as I have not selected a Claim Status")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAClaimStatus() {
        qaOffer.assertQAOfferClaimStatusErrorMessage();
    }
}
