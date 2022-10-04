package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.mpam.QA;
import io.cucumber.java.en.And;

public class QAStepDefs extends BasePage {

    QA qa;

    Dashboard dashboard;

    @And("I select the {string} action at QA")
    public void iSelectOptionAtQA(String action) {
        switch (action.toUpperCase()) {
            case "APPROVE":
                safeClickOn(qa.approvedAtQARadioButton);
                clickConfirmButton();
                break;
            case "REJECTED, MOVE BACK TO DRAFTING":
                qa.selectToRejectCaseToDraft();
                break;
            case "REJECTED, MOVE BACK TO TRIAGE":
                qa.selectToRejectCaseToTriage();
                break;
            case "PUT ON HOLD":
                qa.putQACaseOnHold();
                break;
            case "ESCALATE TO WORKFLOW MANAGER":
                qa.selectEscalateQACaseToWorkflowManager();
                break;
            case "REQUEST SECRETARIAT CLEARANCE":
                qa.selectToRequestSecretariatClearance();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I complete the {string} action at QA")
    public void iCompleteActionAtQA(String action) {
        switch (action.toUpperCase()) {
            case "APPROVE":
                safeClickOn(qa.approvedAtQARadioButton);
                clickConfirmButton();
                break;
            case "REJECTED, MOVE BACK TO DRAFTING":
                qa.selectToRejectCaseToDraft();
                iSubmitAReasonToRejectTheCaseBackToDrafting();
                break;
            case "REJECTED, MOVE BACK TO TRIAGE":
                qa.selectToRejectCaseToTriage();
                iSubmitAReasonToRejectTheCaseBackToTriage();
                break;
            case "PUT ON HOLD":
                qa.putQACaseOnHold();
                break;
            case "ESCALATE TO WORKFLOW MANAGER":
                qa.selectEscalateQACaseToWorkflowManager();
                iSubmitAReasonToEscalateTheCaseAtQAStage();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I add a Clearance Request to the case at QA")
    public void iAddAClearanceRequestToTheCaseAtQA() {
        qa.addAClearanceRequest();
    }

    @And("I select the {string} action at the QA (On Hold) stage")
    public void iSelectOptionAtQAOnHold(String action) {
        switch (action.toUpperCase()) {
            case "KEEP ON HOLD":
                qa.keepCaseOnHold();
                break;
            case "TAKE OFF HOLD":
                qa.takeCaseOffHold();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I select the {string} action at the QA (Escalated) stage")
    public void iSelectOptionAtQAEscalated(String action) {
        switch (action.toUpperCase()) {
            case "KEEP ESCALATED":
                qa.keepCaseEscalated();
                break;
            case "ESCALATION COMPLETE":
                qa.takeCaseOffEscalation();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I enter the clearance received date for the clearance request")
    public void iEnterTheClearanceReceivedDateToTheClearanceRequest() {
        qa.enterClearanceReceivedDate();
    }

    @And("I {string} the Clearance Request at the QA (Secretariat Clearance Requested) stage")
    public void iSelectTheActionAtTheQASecretariatClearanceRequestedStage(String action) {
        switch (action.toUpperCase()) {
            case "APPROVE":
                qa.selectApprovedMoveToPrivateOfficeAtQASecretariatClearanceRequested();
                break;
            case "REJECT":
                qa.rejectACaseToDraftAtQASecretariatClearanceRequested();
                break;
            case "CANCEL":
                qa.cancelTheClearanceRequestAtQASecretariatClearanceRequested();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("the user triggers the {string} error message at QA")
    public void triggerErrorMessage(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                clickConfirmButton();
                break;
            case "REJECT AT TRIAGE REASON REQUIRED":
                safeClickOn(qa.rejectQAToTriageRadioButton);
                clickConfirmButton();
                safeClickOn(qa.triageRejectionTextField);
                clickConfirmButton();
                break;
            case "REJECT AT DRAFT REASON REQUIRED":
                safeClickOn(qa.rejectQAToDraftRadioButton);
                clickConfirmButton();
                safeClickOn(qa.draftRejectionTextField);
                clickConfirmButton();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

    @And("I submit a reason to reject the case back to drafting")
    public void iSubmitAReasonToRejectTheCaseBackToDrafting() {
        qa.submitReasonToRejectToDraft("Test reject to draft at QA");
    }

    @And("I submit a reason to reject the case back to triage")
    public void iSubmitAReasonToRejectTheCaseBackToTriage() {
           qa.submitReasonToRejectToTriage("Test reject to triage at QA");
    }

    @And("I select to close the QA \\(Escalated) case")
    public void iSelectToCloseTheQAEscalatedCase() {
        qa.selectToCloseEscalatedCase();
    }

    @And("I submit a reason to escalate the case at QA stage")
    public void iSubmitAReasonToEscalateTheCaseAtQAStage() {
        qa.submitReasonToEscalateCase("test reason to escalate case");
        dashboard.waitForDashboard();
    }
}
