package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;

public class WCSMoveBackwards extends BasePage {


    public void selectMoveToEligibility() {
        selectSpecificRadioButton("Move to Eligibility");
        clickConfirmButton();
    }

    public void selectMoveToTriage() {
        selectSpecificRadioButton("Move to Triage");
        clickConfirmButton();
    }

    public void selectMoveToCasework() {
        selectSpecificRadioButton("Move to Casework");
        clickConfirmButton();
    }

    public void selectMoveToTier1() {
        selectSpecificRadioButton("Move to Tier1");
        clickConfirmButton();
    }
}
