package com.hocs.test.glue.to;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.to.Campaign;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import io.cucumber.java.en.And;

public class CampaignSteDefs extends BasePage {

    Campaign campaign;

    CaseView caseView;

    Dashboard dashboard;

    SummaryTab summaryTab;

    @And("I put the case into a {string}")
    public void iPutTheCaseIntoACampaign(String campaignCampaign) {
        campaign.selectTheAction("Put case into a " + campaignCampaign);
        clickTheButton("Finish");
        campaign.selectACampaign();
        clickTheButton("Confirm");
    }

    @And("the Case Details accordion should contain the selected campaign")
    public void theCaseDetailsAccordionShouldContainTheSelectedCampaign() {
        openOrCloseAccordionSection("Campaign");
        caseView.assertExpectedValueIsVisibleInOpenCaseDetailsAccordionForGivenKey(sessionVariableCalled("campaign"), "Campaign");
    }

    @When("I select to move the case to triage")
    public void iSelectToMoveTheCaseToTriage() {
        campaign.selectTheAction("Take out of Campaign, move to triage");
        clickTheButton("Confirm");
    }

    @When("I select to move the case to draft")
    public void iSelectToMoveTheCaseToDraft() {
        campaign.selectTheAction("Take out of Campaign, move to draft");
        clickTheButton("Confirm");
    }

    @And("I put the case into the new campaign")
    public void iPutTheCaseIntoTheNewCampaign() {
        campaign.selectTheAction("Put case into a campaign");
        clickTheButton("Finish");
        campaign.selectASpecificCampaign(sessionVariableCalled("newCampaign"));
        clickTheButton("Confirm");
    }

    @Then("the case should have been put into the new campaign")
    public void theCaseShouldHaveBeenPutIntoTheNewCampaign() {
        dashboard.loadCase(getCurrentCaseReference());
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("campaign"),"Campaign name");
    }
}
