package com.hocs.test.glue.to;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.to.StopList;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StopListStepDefs extends BasePage {

    StopList stopList;

    CaseView caseView;

    Dashboard dashboard;

    SummaryTab summaryTab;

    @And("I place the case on a stop list")
    public void iPlaceTheCaseOnAStopList() {
        selectTheStageAction("Place on a stop list");
        clickFinishButton();
        stopList.selectAStopList();
        clickConfirmButton();
    }

    @And("the Case Details accordion should contain the selected stop list")
    public void theCaseDetailsAccordionShouldContainTheSelectedStopList() {
        openOrCloseAccordionSection("Stop List");
        caseView.assertExpectedValueIsVisibleInOpenCaseDetailsAccordionForGivenKey(sessionVariableCalled("stopList"), "Stop List name");
    }

    @When("I select to take the case off of the stop list and move it to triage")
    public void iSelectToMoveTheCaseToTriage() {
        selectTheStageAction("Take out of Stop List, move to triage");
        clickConfirmButton();
    }

    @When("I select to take the case off of the stop list and move it to draft")
    public void iSelectToMoveTheCaseToDraft() {
        selectTheStageAction("Take out of Stop List, move to draft");
        clickConfirmButton();
    }

    @And("I put the case onto the new stop list")
    public void iPutTheCaseIntoTheNewCampaign() {
        selectTheStageAction("Place on a stop lis");
        clickFinishButton();
        stopList.selectASpecificStopList(sessionVariableCalled("newStopList"));
        clickConfirmButton();
    }

    @Then("the case should have been put onto the new stop list")
    public void theCaseShouldHaveBeenPutIntoTheNewCampaign() {
        dashboard.loadCase(getCurrentCaseReference());
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("stopList"),"Stop List name");
    }
}
