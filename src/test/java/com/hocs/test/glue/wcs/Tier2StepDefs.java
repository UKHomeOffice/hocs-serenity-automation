package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.ClaimSchema;
import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.wcs.Tier2Coordination;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class Tier2StepDefs extends BasePage {

    Tier2Coordination tier2Coordination;

    ClaimSchema claimSchema;

    @And("I select that the claimant accepts the revised offer")
    public void iSelectThatTheClaimantAcceptsTheRevisedOffer() {
        tier2Coordination.selectClaimantAcceptsRevisedOffer();
    }

    @And("I select that the claimant rejects the revised offer")
    public void iSelectThatTheClaimantRejectsTheRevisedOffer() {
        tier2Coordination.selectClaimantRejectsRevisedOffer();
    }

    @And("I select that the claimant accepts the upheld offer")
    public void iSelectThatTheClaimantAcceptsTheUpheldOffer() {
        tier2Coordination.selectClaimantAcceptsUpheldOffer();
    }

    @And("I select that the claimant rejects the upheld offer")
    public void iSelectThatTheClaimantRejectsTheUpheldOffer() {
        tier2Coordination.selectClaimantRejectsUpheldOffer();
    }

    @Then("an error message is displayed as I have not selected an option on the Tier 2 Coordination page")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnOptionOnTheTierCoordinationPage() {
        tier2Coordination.assertTier2ReviewResultIsRequiredErrorMessage();
    }

    @And("I select that the adjudicators office has {string} their decision")
    public void selectAdjudicatorsOfficeDecision(String decision) {
        tier2Coordination.enterAdjudicatorOfficeDecisionInformation("01/01/2001", decision);
    }

    @Then("the adjudicators office decision details are correctly displayed in the case details accordion")
    public void adjudicatorsDecisionDetailsAreCorrectlyDisplayedInCaseDetails() {
        clickOn(claimSchema.caseDetailsAccordion);
        tier2Coordination.assertTier2AdjudicatorsOfficeDecisionInformationIsCorrectInAccordion();
    }

    @And("I enter a Tier 2 review withdrawal outcome and decision date")
    public void iEnterATier2ReviewWithdrawalOutcomeAndDecisionDate() {
        tier2Coordination.selectWithdrawDecision("Offer withdrawn");
        tier2Coordination.enterOfferWithdrawnDate(getTodaysDate());
    }
}
