package com.hocs.test.glue.mpam;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.mpam.MTSDataInput;
import io.cucumber.java.en.And;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class MTSDataInputStepDefs extends BasePage {

    MTSDataInput MTSDataInput;

    SummaryTab summaryTab;

    @And("I complete the Data Input stage and close the MTS Case")
    public void iCompleteTheCaseDetailsStageOfTheMTSCase() {
        MTSDataInput.completeDataInputStageAndCloseMTSCase();
    }

    @And("the support note should be visible in the summary")
    public void theSupportNoteShouldBeVisibleInTheSummary() {
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("supportNote"), "Note to support case");
    }
}
