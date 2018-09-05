package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CaseDecisionStepDefs {

    private MarkUpDecision markUpDecision;
    private Page page;

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

    @Given("^a primary topic has been set$")
    public void aPrimaryTopicHasBeenSet() {

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

    @When("^I close the case with no reply needed$")
    public void iCloseTheCaseWithNoReplyNeeded() {

    }

    @When("^I amend the answering \"([^\"]*)\"$")
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

    @When("^I amend the \"([^\"]*)\"$")
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

    @When("^I \"([^\"]*)\" a case$")
    public void iMakeADecisionOnACase(String decision) {
        switch (decision.toUpperCase()) {
            case "APPROVE":
                break;
            case "REJECT":
                page.clickRejectButton();
                page.enterRejectionNotes();
                break;
            default:
                fail();
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

    @Given("^multiple topics have been set$")
    public void multipleTopicsHaveBeenSet() {

    }

    @Then("^the topic is set as the \"([^\"]*)\" Topic$")
    public void theTopicIsSetAsTheTopic(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                fail("Please enter PRIMARY or SECONDARY");
        }

    }

    @When("^I do not enter reasons for a \"([^\"]*)\" case closure$")
    public void iDoNotEnterReasonsForACaseClosure(String arg0) {

    }

    @When("^I refer the case to another Government Department$")
    public void iReferTheCaseToAnotherGovernmentDepartment() {
        markUpDecision.clickReferToOgdRadioButton();
    }

}
