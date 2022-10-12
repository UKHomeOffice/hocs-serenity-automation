package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.ClaimSchema;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.wcs.Tier2Coordination;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class Tier2StepDefs extends BasePage {

    Tier2Coordination tier2Coordination;

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

    @Then("the adjudicators office decision details are correctly displayed in the case details accordion")
    public void adjudicatorsDecisionDetailsAreCorrectlyDisplayedInCaseDetails() {
        openOrCloseAccordionSection("Case Details");
        tier2Coordination.assertTier2AdjudicatorsOfficeDecisionInformationIsCorrectInAccordion();
    }

    @And("I enter a Tier 2 review withdrawal outcome and decision date")
    public void iEnterATier2ReviewWithdrawalOutcomeAndDecisionDate() {
        tier2Coordination.selectAWithdrawDecision();
        tier2Coordination.enterOfferWithdrawnDate(getTodaysDate());
    }

    @And("I enter a Tier 2 adjudicators office decision and decision date")
    public void iEnterATierAdjudicatorsOfficeDecisionAndDecisionDate() {
        tier2Coordination.selectAAdjudicatorsOfficeDecision();
        tier2Coordination.enterTier2AdjudicatorOfficeDecisionDate(getTodaysDate());
        tier2Coordination.selectClaimantAcceptsUpheldOffer();
    }
}
