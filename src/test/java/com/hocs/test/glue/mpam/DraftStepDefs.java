package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.mpam.Draft;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DraftStepDefs {

    Draft draft;

    @And("I move a B:Ref case from Draft to Dispatch bypassing QA")
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
                draft.escalateCaseToWorkflowManager();
                break;
            case "ON HOLD":
                draft.putCaseOnHold();
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
}