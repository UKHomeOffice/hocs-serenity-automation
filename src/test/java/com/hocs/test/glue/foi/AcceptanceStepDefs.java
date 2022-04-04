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

    @And("I select that the case {string} belong in this Group")
    public void iSelectThatTheCaseBelongToTheGroup(String input) {
        if (input.equalsIgnoreCase("DOES")) {
            acceptance.selectIfCaseIsInCorrectGroup("Yes");
            clickTheButton("Continue");
        } else if (input.equalsIgnoreCase("DOESN'T")) {
            acceptance.selectIfCaseIsInCorrectGroup("No");
        } else {
            pendingStep(input + " is not defined within " + getMethodName());
        }
    }

    @And("I submit a rejection reason at the Acceptance stage")
    public void iEnterARejectionReasonAtTheAcceptanceStage() {
        acceptance.enterRejectionReason();
        clickTheButton("Continue");
    }

    @And("I select a Responsible Team and complete acceptance")
    public void iSelectAResponsibleTeamAndCompleteAcceptance() {
        acceptance.selectAResponsibleTeam();
    }
}