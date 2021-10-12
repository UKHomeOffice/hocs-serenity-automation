package com.hocs.test.glue.foi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.foi.Acceptance;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class AcceptanceStepDefs extends BasePage {

    Acceptance acceptance;

    SummaryTab summaryTab;

    @And("I select that the case {string} belong to this Directorate")
    public void iSelectThatTheCaseBelongToTheDirectorate(String input) {
        if (input.equalsIgnoreCase("DOES")) {
            acceptance.selectIfCaseIsInCorrectDirectorate("Yes");
        } else if (input.equalsIgnoreCase("DOESN'T")) {
            acceptance.selectIfCaseIsInCorrectDirectorate("No");
        } else {
            pendingStep(input + " is not defined within " + getMethodName());
        }
    }

    @And("I enter a rejection reason at the Acceptance stage")
    public void iEnterARejectionReasonAtTheAcceptanceStage() {
        acceptance.enterRejectionReason();
    }

    @And("I select the drafting team required to respond to the request")
    public void iSelectTheDraftingTeamRequiredToRespondToTheRequest() {
        acceptance.selectDraftTeam();
    }

    @Then("the case should be assigned to the Drafting team selected at Acceptance")
    public void theCaseShouldBeAssignedToTheDraftingTeamSelectedAtAcceptance() {
        summaryTab.assertAllocatedTeam(sessionVariableCalled("selectedDraftTeam"));
    }

    @And("I {string} the Acceptance stage")
    public void iTheAcceptanceStage(String buttonLabel) {
        clickTheButton(buttonLabel);
    }
}