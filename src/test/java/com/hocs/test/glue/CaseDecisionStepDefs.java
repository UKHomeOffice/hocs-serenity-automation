package com.hocs.test.glue;

import static org.junit.Assert.fail;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CaseDecisionStepDefs {

    @And("^I select (?:a|an) \"([^\"]*)\" topic for a case$")
    public void andISelectATopicForACase(String caseDecision) {
        switch (caseDecision.toUpperCase()) {
            case "FAQ":
                break;
            case "POLICY RESPONSE":
                break;
            default:
                fail("Please select from FAQ or Policy Response");
        }

    }

    @When("^I click to amend the answering \"([^\"]*)\"$")
    public void iClickToAmendTheAnswering(String arg) {
        switch (arg.toUpperCase()) {
            case "MINISTER":
                break;
            case "TEAM":
                break;
            case "UNIT":
                break;
            default:
                fail("Please select from Minister, Team or Unit");
        }

    }

    @Then("^I can only select from a fixed list of answering \"([^\"]*)\"$")
    public void iCanOnlySelectFromAFixedListOfAnswering(String arg) {
        switch (arg.toUpperCase()) {
            case "MINISTER":
                break;
            case "TEAM":
                break;
            case "UNIT":
                break;
            default:
                fail("Please select from Minister, Team or Unit");
        }

    }
}
