package com.hocs.test.glue.UKVI;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.ukvi.QA;
import io.cucumber.java.en.And;

public class QAStepDefs extends BasePage {

    QA qa;

       @And("I select the {string} action at QA")
    public void iSelectOptionAtQA(String action) {
        switch (action.toUpperCase()) {
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


    @And("the user should be able to display the {string} error message at QA")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "ACTIONS REQUIRED":
                qa.assertActionsRequiredErrorMessageDisplayed();
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
    }

}
