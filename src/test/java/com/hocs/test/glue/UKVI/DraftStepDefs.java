package com.hocs.test.glue.UKVI;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.ukvi.Draft;
import io.cucumber.java.en.And;

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
}
