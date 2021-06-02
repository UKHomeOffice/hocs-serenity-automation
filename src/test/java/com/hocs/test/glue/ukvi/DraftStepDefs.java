package com.hocs.test.glue.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.ukvi.Draft;
import com.hocs.test.pages.ukvi.MultipleContributions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DraftStepDefs extends BasePage {

    Draft draft;

    MultipleContributions multipleContributions;

    @And("I move a Official case from Draft to Dispatch bypassing QA")
    public void moveBRefCaseFromDraftToDispatch() {
        draft.moveBRefCaseFromDraftToDispatch();
    }

    @And("I send the Draft case to {string}")
    public void sendDraftCaseTo(String action) {
        switch (action.toUpperCase()) {
            case "QA":
                draft.moveCaseFromDraftToQA();
                break;
            case "WORKFLOW MANAGER":
                draft.selectEscalateDraftCaseToWorkflowManager();
                draft.submitReasonToEscalateCase("test reason to escalate case");
                break;
            case "ON HOLD":
                draft.putCaseOnHold();
                break;
            case "CONTRIBUTIONS REQUESTED":
                multipleContributions.sendCaseToContributionRequest();
                break;
            case "TRIAGE":
                draft.sendDraftCaseBackToTriage();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        waitForDashboard();
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
                multipleContributions.sendCaseToContributionRequest();
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
                safeClickOn(confirmButton);
                break;
            case "CONTRIBUTIONS RECEIVED":
                safeClickOn(draft.contributionsReceivedRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(multipleContributions.unallocateCaseRadioButton);
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @When("the user triggers the {string} error message at Draft by not entering the correct information")
    public void theUserTriggersTheErrorMessageAtDraftByNotEnteringTheCorrectInformation(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                draft.selectResponseChannel("Email");
                safeClickOn(confirmButton);
                break;
            case "RESPONSE CHANNEL REQUIRED":
                safeClickOn(draft.escalateToWorkflowManagerRadioButton);
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} error message should be displayed at Draft")
    public void theErrorMessageShouldBeDisplayedAtDraft(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                draft.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "RESPONSE CHANNEL REQUIRED":
                draft.assertResponseChannelRequiredErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }
}
