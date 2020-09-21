package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.mpam.Campaign;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CampaignStepDefs extends BasePage {

    Campaign campaign;

    SummaryTab summary;

    @And("I move the case into a Campaign from the {string} stage")
    public void moveCaseFromStageToCampaign(String stage) {
        campaign.moveCaseFromAStageToCampaign("Small boats");
    }

    @And("I add the case to the new campaign")
    public void moveCaseFromStageToSpecificCampaign() {
        campaign.moveCaseFromAStageToCampaign(sessionVariableCalled("newCampaign"));
    }

    @And("I move the case from Campaign to {string}")
    public void moveCaseFromCampaignToStage(String stage) {
        campaign.moveCaseFromCampaignToStage(stage);
    }

    @Then("the case is added to the correct Campaign")
    public void caseIsAddedToCorrectCampaign() {
        campaign.assertCaseIsMovedToCorrectCampaign();
    }

    @Then("the correct Campaign is displayed in the summary tab")
    public void correctCampaignDisplayedInSummaryTab() {
        safeClickOn(summaryTab);
        summary.assertCampaignMatchesInputInSummaryTab();
    }
}
