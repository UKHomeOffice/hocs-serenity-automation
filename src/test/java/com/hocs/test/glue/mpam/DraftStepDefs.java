package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.mpam.Draft;
import com.hocs.test.pages.mpam.MPAMMultipleContributions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DraftStepDefs extends BasePage {

    Draft draft;

    MPAMMultipleContributions MPAMMultipleContributions;

    Dashboard dashboard;

    @And("I move a Official case from Draft to Dispatch bypassing QA")
    public void moveBRefCaseFromDraftToDispatch() {
        draft.moveBRefCaseFromDraftToDispatch();
    }

    @And("I send the Draft case to {string}")
    public void sendDraftCaseTo(String action) {
        switch (action.toUpperCase()) {
            case "QA":
                safeClickOn(draft.moveToQARadioButton);
                setSessionVariable("action").to("Move to QA");
                clickConfirmButton();
                break;
            case "WORKFLOW MANAGER":
                draft.selectEscalateDraftCaseToWorkflowManager();
                draft.submitReasonToEscalateCase("test reason to escalate case");
                break;
            case "ON HOLD":
                draft.putCaseOnHold();
                break;
            case "CONTRIBUTIONS REQUESTED":
                MPAMMultipleContributions.sendMPAMCaseToContributionRequest();
                break;
            case "TRIAGE":
                draft.sendDraftCaseBackToTriage();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
    }

    @When("I take the Draft \\(On Hold) case off hold")
    public void iTakeTheDraftOnHoldCaseOffHold() {
        draft.takeDraftCaseOffHold();
    }

    @And("I select the {string} action at the Draft-Escalated stage")
    public void iSelectTheActionAtDraftEscalatedStage(String action) {
        switch (action.toUpperCase()) {
            case "DE-ESCALATE":
                draft.deescalateDraftCase();
                break;
            case "CLOSE CASE":
                draft.selectToCloseEscalatedCase();
                break;
            case "CONTRIBUTIONS REQUESTED":
                MPAMMultipleContributions.sendMPAMCaseToContributionRequest();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @When("I select the {string} action at Draft \\(Contribution Requested) stage")
    public void iSelectTheActionAtDraftContributionRequestedStage(String action) {
        switch (action.toUpperCase()) {
            case "ESCALATE TO WORKFLOW MANAGER":
                safeClickOn(draft.escalateToWorkflowManagerRadioButton);
                clickConfirmButton();
                break;
            case "CONTRIBUTIONS RECEIVED":
                safeClickOn(draft.contributionsReceivedRadioButton);
                clickConfirmButton();
                safeClickOn(MPAMMultipleContributions.unallocateCaseRadioButton);
                clickConfirmButton();
                break;
            default:
                pendingStep(errorMessageList + " is not defined within " + getMethodName());
        }
    }
}
