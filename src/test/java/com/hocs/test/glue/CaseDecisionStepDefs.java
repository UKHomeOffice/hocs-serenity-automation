package com.hocs.test.glue;

import static org.junit.Assert.fail;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CaseDecisionStepDefs {

    @Then("^a mandatory \"([^\"]*)\" free text field is available$")
    public void aMandatoryFreeTextFieldIsAvailable(String textField) {
        switch (textField.toUpperCase()) {
            case "ALLOCATION NOTE":
                break;
            case "REASON FOR NO REPLY NEEDED":
                break;
            default:
                fail();
        }
    }

    @And("^I select (?:a|an) \"([^\"]*)\" topic for a case from the type function$")
    public void andISelectATopicForACaseFromTheTypeFunction(String caseDecision) {
        switch (caseDecision.toUpperCase()) {
            case "FAQ":
                break;
            case "POLICY RESPONSE":
                break;
            default:
                fail("Please select from FAQ or Policy Response");
        }

    }

    @And("^I select (?:a|an) \"([^\"]*)\" topic for a case from the dropdown$")
    public void andISelectATopicForACaseFromTheDropdown(String caseDecision) {
        switch (caseDecision.toUpperCase()) {
            case "FAQ":
                break;
            case "POLICY RESPONSE":
                break;
            default:
                fail("Please select from FAQ or Policy Response");
        }

    }

    @Then("^an optional \"([^\"]*)\" free text field is available$")
    public void anOptionalFreeTextFieldIsAvailable(String textField) {
        switch (textField.toUpperCase()) {
            case "ALLOCATION NOTE":
                break;
            case "REASON FOR NO REPLY NEEDED":
                break;
            default:
                fail();
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

    @When("^I select an initial decision of \"([^\"]*)\"$")
    public void iSelectAnInitialDecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "FAQ":
                break;
            case "NO REPLY NEEDED":
                break;
            case "POLICY RESPONSE":
                break;
            case "TRANSFER TO OGD":
                break;
            default:
                fail();
        }
    }

    @When("^I click to amend the \"([^\"]*)\"$")
    public void iClickToAmendThe(String amendment) {
        switch (amendment.toUpperCase()) {
            case "MINISTER":
                break;
            case "DRAFTING TEAM":
                break;
            default:
                fail();
        }
    }
}
