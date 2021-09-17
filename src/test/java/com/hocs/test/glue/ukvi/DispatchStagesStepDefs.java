package com.hocs.test.glue.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
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
    public void  iSelectTheActionAtPrivateOfficeStage(String action) {
        dispatchStages.selectActionAtPrivateOffice(action);
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

    @When("I confirm that the case has been dispatched")
    public void iConfirmThatTheCaseHasBeenDispatched() {
        dispatchStages.selectDispatchAndCloseCaseAction();
        clickTheButton("Confirm");
    }

    @When("I select to return the case to Private Office")
    public void iSelectToReturnTheCaseToPrivateOffice() {
        dispatchStages.selectSendBackToPrivateOfficeAction();
        clickTheButton("Confirm");
    }
}
