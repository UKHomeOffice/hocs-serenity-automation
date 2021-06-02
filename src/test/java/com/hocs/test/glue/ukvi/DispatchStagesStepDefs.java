package com.hocs.test.glue.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.ukvi.DispatchStages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DispatchStagesStepDefs extends BasePage {

    DispatchStages dispatchStages;

    SummaryTab summaryTab;

    @And("I trigger the {string} error message at Awaiting Dispatch stage by not entering the correct information")
    public void triggerAwaitingDispatchErrorMessage(String errorMessage) {
       dispatchStages.triggerAwaitingDispatchErrorMessage(errorMessage);
    }

    @And("the user triggers the {string} error message at Private Office by not entering the correct information")
    public void triggerPrivateOfficeErrorMessage(String errorMessage) {
        dispatchStages.triggerPrivateOfficeErrorMessage(errorMessage);
    }

    @And("then the {string} error message should be displayed at a Dispatch stage")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                dispatchStages.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "DISPATCHED DATE REQUIRED":
                dispatchStages.assertDispatchedDateRequiredErrorMessageDisplayed();
                break;
            case "FOLLOW-UP DATE REQUIRED":
                dispatchStages.assertFollowUpDateRequiredErrorMessageDisplayed();
                break;
            case "FOLLOW-UP DETAILS REQUIRED":
                dispatchStages.assertFollowUpDetailsRequiredErrorMessageDisplayed();
                break;
            case "FOLLOW-UP NOT COMPLETED REASON REQUIRED":
                dispatchStages.assertFollowUpNotCompletedReasonRequiredErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @When("I select the {string} action at Awaiting Dispatch stage")
    public void iSelectTheActionAtAwaitingDispatchStage(String action) {
        switch (action.toUpperCase()) {
            case "DISPATCHED, CLOSE CASE":
                safeClickOn(dispatchStages.dispatchedCloseCaseRadioButton);
                break;
            case "DISPATCHED (FOLLOW-UP)":
                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                break;
            case "RETURN TO DRAFT":
                safeClickOn(dispatchStages.returnToDraftButton);
                setSessionVariable("rejectionStage").to("Dispatch");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
    }

    @When("I select the {string} action at Private Office stage")
    public void iSelectTheActionAtPrivateOfficeStage(String action) {
        switch (action.toUpperCase()) {
            case "DISPATCHED":
                safeClickOn(dispatchStages.dispatchedRadioButtonAtPrivateOffice);
                break;
            case "DRAFT REJECTED BY PRIVATE OFFICE":
                safeClickOn(dispatchStages.draftRejectedRadioButton);
                setSessionVariable("rejectionStage").to("PO");
                break;
            case "DISPATCHED (FOLLOW-UP)":
                safeClickOn(dispatchStages.dispatchedFollowUpRadioButton);
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
    }

    @When("I select the {string} action at Dispatched \\(follow-up) stage")
    public void iSelectTheActionAtDispatchedFollowUpStage(String action) {
        switch (action.toUpperCase()) {
            case "FOLLOW-UP COMPLETED":
                safeClickOn(dispatchStages.followUpCompletedRadioButton);
                break;
            case "CLOSE FOLLOW-UP ACTIONED NOT COMPLETED":
                safeClickOn(dispatchStages.closeFollowUpNotCompletedRadioButton);
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
    }

    @And("I submit a reason to reject the case back to Draft stage")
    public void iEnterAReasonToRejectTheCaseBackToDraftStage() {
        dispatchStages.submitReasonToRejectToDraft("Test reject to draft at private office");
    }

    @And("the rejection reason entry box should be visible")
    public void theRejectionReasonEntryBoxShouldBeVisible() {
        dispatchStages.assertThatRejectionReasonTextAreaVisible();
    }

    @And("I enter a date of dispatch and confirm to close the case")
    public void iEnterADispatchedDateAndConfirmToCloseTheCase() {
        dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(dispatchStages.confirmAndCloseCaseButton);
    }

    @Then("I can see the previous selected response channel is still selected")
    public void iCanSeeThePreviousSelectedResponseChannelIsSelected() {
        dispatchStages.assertThatResponseChannelIsPreSelected();
    }

    @And("the follow-up due date should be visible in the summary")
    public void theFollowUpDueDateShouldBeVisibleInTheSummary() {
        summaryTab.assertFollowUpDueDateVisible();
    }

    @And("I enter a dispatched date")
    public void iEnterADispatchedDate() {
        dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
    }

    @And("I enter a follow-up date")
    public void iEnterAFollowUpDate() {
        dispatchStages.followUpDateInput(getDatePlusMinusNDaysAgo(1));
    }

    @And("I enter follow-up details and confirm")
    public void iEnterFollowUpDetailsAndConfirm() {
        dispatchStages.enterFollowUpDetails("test follow-up details");
        safeClickOn(confirmButton);
    }

    @And("enter a reason for not completing the follow up action")
    public void enterAReasonForNotCompletingTheFollowUpAction() {
        dispatchStages.enterReasonForNotCompletingFollowUp("test follow-up not completed reason");
        safeClickOn(dispatchStages.confirmAndCloseCaseButton);
    }

    @And("I submit a reason to reject the case back to the Draft stage")
    public void iSubmitAReasonToRejectTheCaseBackToTheDraftStage() {
        dispatchStages.submitReasonToReturnToDraft("Test return to draft at Awaiting Dispatch");
    }
}
