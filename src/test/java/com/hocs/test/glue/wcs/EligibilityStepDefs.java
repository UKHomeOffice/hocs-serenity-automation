package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.wcs.Eligibility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EligibilityStepDefs extends BasePage {

    Eligibility eligibility;

    @And("I put the claim on hold at the Eligibility stage")
    public void iPutTheClaimOnHoldAtTheEligibilityStage() {
        eligibility.putTheClaimOnHold();
    }

    @When("I take the claim off of hold at the Eligibility stage")
    public void iTakeTheClaimOffOfHoldAtTheEligibilityStage() {
        eligibility.takeTheClaimOffHold();
    }

    @When("I select to close the claim after no response from claimant")
    public void iCloseTheClaimAfterNoResponseFromClaimant() {
        eligibility.noResponseFromClaimantCloseClaim();
    }

    @And("I confirm the eligibility of the claimant")
    public void iConfirmTheEligibilityOfTheClaimant() {
        eligibility.confirmEligibility();
    }

    @Then("an error message is displayed as I have not confirmed the claimants eligibility")
    public void anErrorMessageIsDisplayedAsIHaveNotConfirmedTheClaimantsEligibility() {
        eligibility.assertCanYouConfirmClaimantEligibilityErrorMessage();
    }

    @And("I select that I cannot confirm the candidates eligibility")
    public void iSelectThatICannotConfirmTheCandidatesEligibility() {
        eligibility.cannotConfirmEligibility();
    }

    @Then("an error message should be displayed as I have not selected a rejection reason")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedARejectionReason() {
        eligibility.assertEligibilityRejectionReasonErrorMessage();
    }

    @And("I select an Eligibility rejection reason")
    public void iSelectAnEligibilityRejectionReason() {
        eligibility.selectRejectionReasonByIndex(1);
        clickOn(confirmButton);
    }

    @And("I confirm the claim should be closed")
    public void iConfirmTheClaimShouldBeClosed() {
        eligibility.confirmClaimShouldBeClosed();
    }

    @And("I decide to not close the claim")
    public void iDecideToNotCloseTheClaim() {
        eligibility.selectToNotCloseTheClaim();
    }
}
