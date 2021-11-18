package com.hocs.test.glue.decs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.SummaryTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class SummaryTabStepDefs extends BasePage {

    CaseView caseView;

    SummaryTab summaryTab;

    @And("I select the summary tab")
    public void iSelectTheSummaryTab() {
        caseView.waitForCaseToLoad();
        summaryTab.selectSummaryTab();
    }

    @And("I select the previous COMP case reference from the COMP2 case summary tab")
    public void thePreviousCOMPCaseReferenceIsDisplayedInTheCOMP2CaseSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.selectPreviousCaseReference();
    }

    @Then("the deadline of the FOI case should be extended the correct number of days")
    public void theDeadlineOfTheFOICaseShouldBeExtended() {
        summaryTab.selectSummaryTab();
        summaryTab.assertDeadlineOfExtendedFOICase();
    }

    @Then("the information entered for the FOI appeal should be displayed in the Summary tab")
    public void theInformationEnteredForTheFOIAppealShouldBeDisplayedInTheSummaryTab() {
        summaryTab.selectSummaryTab();
        summaryTab.assertAppealInformationIsDisplayed();
    }
}
