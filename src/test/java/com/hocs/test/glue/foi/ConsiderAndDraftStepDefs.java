package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.ConsiderAndDraft;
import io.cucumber.java.en.And;

public class ConsiderAndDraftStepDefs extends BasePage {

    ConsiderAndDraft considerAndDraft;

    @And("I select that the case {string} in the correct drafting team")
    public void iSelectThatTheCaseInTheCorrectDraftingTeam(String input) {
        if (input.equalsIgnoreCase("IS")) {
            considerAndDraft.selectDraftAcceptance("Yes");
        } else if (input.equalsIgnoreCase("ISN'T")) {
            considerAndDraft.selectDraftAcceptance("No");
        }
    }

    @And("I enter a reason for the case to be returned to Acceptance")
    public void iEnterAReasonForTheCaseToBeReturnedToAcceptance() {
        considerAndDraft.enterRejectionReason();
    }

    @And("I select that the case {string} require a contribution")
    public void iSelectThatTheCaseRequireAContributionRequest(String input) {
        if (input.equalsIgnoreCase("DOES")) {
            considerAndDraft.isContributionRequestNeeded("Yes");
        } else if (input.equalsIgnoreCase("DOESN'T")) {
            considerAndDraft.isContributionRequestNeeded("No");
        }
        clickTheButton("Continue");
    }

    @And("I select {string} as the response type")
    public void iSelectTheResponseType(String responseType) {
        if (responseType.equalsIgnoreCase("FULL DISCLOSURE")) {
            considerAndDraft.selectResponseType("Full disclosure");
        } else if (responseType.equalsIgnoreCase("PARTIAL DISCLOSURE")) {
            considerAndDraft.selectResponseType("Partial disclosure");
        } else if (responseType.equalsIgnoreCase("EXEMPTION")) {
            considerAndDraft.selectResponseType("Exemption");
        }
    }

    @And("I select a reason for exemption")
    public void iSelectAReasonForExemption() {
        considerAndDraft.selectExemptionReason();
    }

}