package com.hocs.test.glue.decs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.SummaryTab;
import io.cucumber.java.en.Then;

public class SummaryTabStepDefs extends BasePage {

    CaseView caseView;

    SummaryTab summaryTab;

    @Then("the case should be loaded")
    public void theCaseShouldBeLoaded() {
        caseView.currentCaseIsLoaded();
    }
}
