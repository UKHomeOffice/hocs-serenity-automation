package com.hocs.test.glue.wcs;

import com.hocs.test.pages.wcs.WCSMoveBackwards;
import io.cucumber.java.en.When;

public class WCSMoveBackwardsCaseStepDefs {

    WCSMoveBackwards wcsMoveBackwards;


    @When("I select move claim to Eligibility")
    public void iSelectMoveClaimToEligibility() {
        wcsMoveBackwards.selectMoveToEligibility();
    }

    @When("I select move claim to Triage")
    public void iSelectMoveClaimToTriage() {
        wcsMoveBackwards.selectMoveToTriage();
    }

    @When("I select move claim to Casework")
    public void iSelectMoveClaimToCasework() {
        wcsMoveBackwards.selectMoveToCasework();
    }

    @When("I select move claim to Tier 1")
    public void iSelectMoveClaimToTier1() {
        wcsMoveBackwards.selectMoveToTier1();
    }
}
