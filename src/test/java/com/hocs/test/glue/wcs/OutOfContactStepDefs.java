package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.wcs.OutOfContactTab;
import io.cucumber.java.en.And;

public class OutOfContactStepDefs extends BasePage {

    CaseView caseView;

    OutOfContactTab outOfContactTab;

    @And("I select the Out of Contact tab")
    public void iSelectTheOutOfContactTab() {
        caseView.waitForCaseToLoad();
        outOfContactTab.selectOutOfContactTab();
    }

    @And("I select Pre-Offer in the Out of Contact Tab")
    public void iSelectPreOffer() {
        outOfContactTab.selectPreOffer();
    }

    @And("I select Post-Offer in the Out of Contact Tab")
    public void iSelectPostOffer() {
        outOfContactTab.selectPostOffer();
    }
}
