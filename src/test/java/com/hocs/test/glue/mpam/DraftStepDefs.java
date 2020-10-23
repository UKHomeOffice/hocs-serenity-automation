package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.Draft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DraftStepDefs extends BasePage {

    Draft draft;

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
            case "CONTRIBUTION REQUESTED":
                draft.selectContributionRequested();
                draft.enterContributionRequestDeadlineDate(todayPlusMinusNDaysGetDay(1), todayPlusMinusNDaysGetMonth(0),
                        todayPlusMinusNDaysGetYear(0));
                draft.enterRequestDescription("test request contribution description");
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @When("I take the Draft \\(On Hold) case off hold")
    public void iTakeTheDraftOnHoldCaseOffHold() {
        draft.takeTriageCaseOffHold();
    }

    @When("I de-escalate the Draft \\(Escalated) case")
    public void iDeEscalateTheDraftEscalatedCase() {
        draft.deescalateTriageCase();
    }

    @And("I select to close the Draft \\(Escalated) case")
    public void iSelectToCloseTheDraftEscalatedCase() {
        draft.selectToCloseEscalatedCase();
    }

    @And("I submit a reason to close the case at Draft \\(Escalated) stage")
    public void iSubmitAReasonToCloseTheCaseAtDraftEscalatedStage() {
        draft.submitReasonToCloseEscalatedCase("Test close case at Draft (Escalated) stage");
    }

    @When("I select the {string} action at Draft \\(Contribution Requested) stage")
    public void iSelectTheActionAtDraftContributionRequestedStage(String action) {
        switch (action.toUpperCase()) {
            case "ESCALATE TO WORKFLOW MANAGER":
                safeClickOn(draft.escalateToWorkflowManagerRadioButton);
                break;
            case "CONTRIBUTIONS RECEIVED":
                safeClickOn(draft.contributionsReceivedRadioButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
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
            case "CONTRIBUTION REQUEST DEADLINE REQUIRED":
                draft.selectResponseChannel("Email");
                safeClickOn(draft.contributionRequestedRadioButton);
                safeClickOn(confirmButton);
                draft.enterRequestDescription("Test");
                safeClickOn(confirmButton);
                break;
            case "CONTRIBUTION REQUEST DESCRIPTION REQUIRED":
                draft.selectResponseChannel("Email");
                safeClickOn(draft.contributionRequestedRadioButton);
                safeClickOn(confirmButton);
                draft.enterContributionRequestDeadlineDate(todayPlusMinusNDaysGetDay(1), todayPlusMinusNDaysGetMonth(0),
                        todayPlusMinusNDaysGetYear(0));
                safeClickOn(confirmButton);
                break;
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
            case "CONTRIBUTION REQUEST DEADLINE REQUIRED":
                draft.assertContributionRequestDeadlineRequiredErrorMessageDisplayed();
                break;
            case "CONTRIBUTION REQUEST DESCRIPTION REQUIRED":
                draft.assertContributionRequestDescriptionRequiredErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }
}
