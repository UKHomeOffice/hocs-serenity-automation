package com.hocs.test.glue.ukvi;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.ukvi.MTSDataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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
        summaryTab.assertSummaryContainsExpectedContentForGivenHeader("Note to support case", sessionVariableCalled("supportNote"));
    }
}
