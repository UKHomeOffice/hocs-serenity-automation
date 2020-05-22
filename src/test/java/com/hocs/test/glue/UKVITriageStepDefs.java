package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.ukvi.Triage;
import io.cucumber.java.en.And;

public class UKVITriageStepDefs {

    Homepage homepage;

    Triage triage;

    @And("I send the Triage case to {string}")
    public void escalateToWorkflowManager(String stage) {
        switch (stage.toUpperCase()) {
            case "WORKFLOW MANAGER":
                triage.escalateTriageCaseToWorkflowManager();
                break;
            case "ON HOLD":
                triage.putTriageCaseOnHold();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @And("the user triggers the {string} error message at triage")
    public void triggerErrorMessage(String error) {
        triage.triggerErrorMessage(error);
    }

    @And("the {string} error message should be displayed at triage")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        triage.assertErrorMessageDisplayed(errorMessage);
    }
}
