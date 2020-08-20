package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.Campaign;
import com.hocs.test.pages.mpam.DispatchStages;
import com.hocs.test.pages.mpam.Draft;
import com.hocs.test.pages.mpam.QA;
import com.hocs.test.pages.mpam.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CampaignStepDefs extends BasePage {

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    Campaign campaign;

    @And("I move the case into a Campaign from the {string} stage")
    public void moveCaseFromStageToCampaign(String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
            case "TRIAGE-ON HOLD":
            case "TRIAGE-ESCALATED":
            case "TRIAGE-CONTRIBUTION REQUESTED":
                triage.moveCaseFromTriageStageToCampaign();
                break;
            case "DRAFT":
            case "DRAFT-ON HOLD":
            case "DRAFT-ESCALATED":
            case "DRAFT-CONTRIBUTION REQUESTED":
                draft.moveCaseFromDraftStageToCampaign();
                break;
            case "QA":
            case "QA-ON HOLD":
            case "QA-ESCALATED":
                qa.moveCaseFromAQAStageToCampaign();
                break;
            case "PRIVATE OFFICE":
            case "AWAITING DISPATCH":
                dispatchStages.moveCaseFromADispatchStageToCampaign();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @Then("the case is added to the correct Campaign")
    public void caseIsAddedToCorrectCampaign() {
        campaign.assertCaseIsMovedToCorrectCampaign();
    }
}
