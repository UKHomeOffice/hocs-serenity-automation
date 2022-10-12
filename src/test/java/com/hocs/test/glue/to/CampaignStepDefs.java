package com.hocs.test.glue.to;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.to.Campaign;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class CampaignStepDefs extends BasePage {

    Campaign campaign;

    CaseView caseView;

    Dashboard dashboard;

    SummaryTab summaryTab;

    @And("I put the case into a {string}")
    public void iPutTheCaseIntoACampaign(String campaignCampaign) {
        selectTheStageAction("Put case into a " + campaignCampaign);
        clickFinishButton();
        campaign.selectACampaign();
        clickConfirmButton();
    }

    @And("the Case Details accordion should contain the selected campaign")
    public void theCaseDetailsAccordionShouldContainTheSelectedCampaign() {
        openOrCloseAccordionSection("Campaign");
        caseView.assertExpectedValueIsVisibleInOpenCaseDetailsAccordionForGivenKey(sessionVariableCalled("campaign"), "Campaign");
    }

    @When("I select to take the case out of the campaign and move it to triage")
    public void iSelectToMoveTheCaseToTriage() {
        selectTheStageAction("Take out of Campaign, move to triage");
        clickConfirmButton();
    }

    @When("I select to take the case out of the campaign and move it to draft")
    public void iSelectToMoveTheCaseToDraft() {
        selectTheStageAction("Take out of Campaign, move to draft");
        clickConfirmButton();
    }

    @And("I put the case into the new campaign")
    public void iPutTheCaseIntoTheNewCampaign() {
        selectTheStageAction("Put case into a campaign");
        clickFinishButton();
        campaign.selectASpecificCampaign(sessionVariableCalled("campaignName"));
        clickConfirmButton();
    }

    @Then("the case should have been put into the new campaign")
    public void theCaseShouldHaveBeenPutIntoTheNewCampaign() {
        dashboard.loadCase(getCurrentCaseReference());
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("campaign"),"Campaign name");
    }
}
