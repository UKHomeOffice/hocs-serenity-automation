package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

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
        switch (stage.toUpperCase()) {
            case "TRIAGE":
            case "TRIAGE-ON HOLD":
            case "TRIAGE-ESCALATED":
            case "TRIAGE-CONTRIBUTION REQUESTED":
            case "DRAFT":
            case "DRAFT-ON HOLD":
            case "DRAFT-ESCALATED":
            case "DRAFT-CONTRIBUTION REQUESTED":
            case "QA":
            case "QA-ON HOLD":
            case "QA-ESCALATED":
            case "PRIVATE OFFICE":
            case "AWAITING DISPATCH":
                campaign.moveCaseFromAStageToCampaign();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @And("I add the case to the new campaign")
    public void moveCaseFromStageToSpecificCampaign() {
        campaign.moveCaseFromAStageToSpecificCampaign();
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
