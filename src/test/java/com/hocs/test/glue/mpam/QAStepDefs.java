package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.QA;
import io.cucumber.java.en.And;

public class QAStepDefs extends BasePage {

    QA qa;

       @And("I select the {string} action at QA")
    public void iSelectOptionAtQA(String action) {
        switch (action.toUpperCase()) {
            case "APPROVE":
                safeClickOn(qa.approvedAtQARadioButton);
                safeClickOn(confirmButton);
                break;
            case "REJECT QA AT DRAFT":
                qa.rejectQACaseAtDraft("TEST");
                break;
            case "REJECT QA AT TRIAGE":
                qa.rejectQACaseAtTriage("TEST");
                break;
            case "ON HOLD":
                qa.putQACaseOnHold();
                break;
            case "ESCALATE TO WORKFLOW MANAGER":
                qa.escalateQACaseToWorkflowManager();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I select the {string} action at the QA (On Hold) stage")
    public void iSelectOptionAtQAOnHold(String action) {
        switch (action.toUpperCase()) {
            case "KEEP ON HOLD":
                qa.keepCaseOnHold();
                break;
            case "TAKE OFF HOLD":
                qa.takeCaseOffEscalation();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("the user triggers the {string} error message at QA")
    public void triggerErrorMessage(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                safeClickOn(qa.confirmButton);
                break;
            case "REJECT AT TRIAGE REASON REQUIRED":
                safeClickOn(qa.rejectQAAtTriageRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(qa.triageRejectionTextField);
                safeClickOn(confirmButton);
                break;
            case "REJECT AT DRAFT REASON REQUIRED":
                safeClickOn(qa.rejectQAAtDraftRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(qa.draftRejectionTextField);
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }


    @And("the {string} error message should be displayed at QA")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                qa.assertActionsRequiredErrorMessageDisplayed();
                break;
            case "REJECT AT TRIAGE REASON REQUIRED":
                qa.assertRejectAtTriageReasonRequiredErrorMessageDisplayed();
                break;
            case "REJECT AT DRAFT REASON REQUIRED":
                qa.assertRejectAtDraftReasonRequiredErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }
}
