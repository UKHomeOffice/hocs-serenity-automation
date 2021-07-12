package com.hocs.test.glue.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.SummaryTab;
import com.hocs.test.pages.ukvi.DispatchStages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DispatchStagesStepDefs extends BasePage {

    DispatchStages dispatchStages;

    SummaryTab summaryTab;

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

    @And("I select a response channel")
    public void iSelectAResponseChannel() {
        dispatchStages.selectAResponseChannel();
    }
}
