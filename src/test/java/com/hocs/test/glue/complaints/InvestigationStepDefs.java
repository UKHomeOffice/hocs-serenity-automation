package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.complaints.Investigation;
import com.hocs.test.pages.decs.BasePage;
import io.cucumber.java.en.And;

public class InvestigationStepDefs extends BasePage {

    Investigation investigation;

    @And("I {string} the case at the Investigation stage")
    public void investigationAcceptanceDecision(String decision) {
        if (decision.equalsIgnoreCase("ACCEPT")) {
            investigation.acceptCaseAtInvestigation();
        } else if (decision.equalsIgnoreCase("REJECT")) {
            investigation.rejectCaseAtInvestigation();
        } else {
            pendingStep(decision + " is not a valid input for " + getMethodName());
        }
    }

    @And("I complete the {string} action at the Investigation stage")
    public void iSelectTheActionAtTheInvestigationStage(String action) {
        switch (action.toUpperCase()) {
            case "ALL INFORMATION COLLECTED - RESPOND":
                investigation.selectAllInformationCollectedRespondAction();
                break;
            case "NO RESPONSE - COMPLETE THE CASE":
                investigation.selectNoResponseCloseCaseAction();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(finishButton);
    }

    @And("I enter a rejection reason at the Investigation stage")
    public void iEnterARejectionReasonAtTheInvestigationStage() {
        investigation.enterRejectionReason();
    }
}