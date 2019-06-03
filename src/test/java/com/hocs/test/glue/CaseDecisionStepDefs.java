package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;


public class CaseDecisionStepDefs extends Page {

    MarkUpDecision markUpDecision;

    Topics topics;

    @Then("^a mandatory \"([^\"]*)\" free text field is displayed$")
    public void aMandatoryFreeTextFieldIsAvailable(String textField) {
        switch (textField.toUpperCase()) {
            case "ALLOCATION NOTE":
                break;
            case "REASON FOR NO REPLY NEEDED":
                break;
            case "TOPIC" :
                topics.assertTopicsTextFieldDisplayed();
                break;
            default:
                pendingStep(textField + " is not defined within " + getMethodName());
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
                pendingStep(caseDecision + " is not defined within " + getMethodName());
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
                pendingStep(caseDecision + " is not defined within " + getMethodName());
        }

    }

    @Then("^an optional \"([^\"]*)\" free text field is available$")
    public void anOptionalFreeTextFieldIsAvailable(String textField) {
        switch (textField.toUpperCase()) {
            case "ALLOCATION NOTE":
                break;
            case "REASON FOR NO REPLY NEEDED":
                break;
            case "TOPICS":
                topics.assertTopicsTextFieldDisplayed();
                break;
            default:
                pendingStep(textField + " is not defined within " + getMethodName());
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
                pendingStep(arg + " is not defined within " + getMethodName());
        }

    }

    @When("^I close the case with a decision of \"([^\"]*)\"$")
    public void iCloseTheCaseWithADecisionOf(String status) {
        markUpDecision.getCaseId();
        switch (status.toUpperCase()) {
            case "REFER TO OGD":
                clickOn(markUpDecision.referToOgdRadioButton);
                break;
            case "NO REPLY NEEDED":
                clickOn(markUpDecision.noReplyNeededRadioButton);
                break;
            default:
                pendingStep(status + " is not defined within " + getMethodName());
        }
        clickOn(markUpDecision.continueButton);
        clickOn(markUpDecision.finishButton);

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
                pendingStep(arg + " is not defined within " + getMethodName());
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
                pendingStep(amendment + " is not defined within " + getMethodName());
        }
    }

    @When("^I \"([^\"]*)\" a case$")
    public void iMakeADecisionOnACase(String decision) {
        switch (decision.toUpperCase()) {
            case "APPROVE":
                break;
            case "REJECT":
                clickOn(markUpDecision.rejectButton);
                markUpDecision.enterRejectionNotes();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
    }

    @When("^I select an initial decision of \"([^\"]*)\"$")
    public void iSelectAnInitialDecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "FAQ":
                markUpDecision.selectFAQRadioButton();
                break;
            case "NO RESPONSE NEEDED":
                markUpDecision.selectNoReplyNeededRadioButton();
                break;
            case "POLICY RESPONSE":
                markUpDecision.selectPolicyResponseRadioButton();
                break;
            case "REFER TO OGD":
                markUpDecision.selectReferToOGDRadioButton();
                break;
            case "TRANSFER TO OGD":
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        markUpDecision.clickContinueButton();
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
                pendingStep(ordinal + " is not defined within " + getMethodName());
        }

    }

    @When("^I do not enter reasons for a \"([^\"]*)\" case closure$")
    public void iDoNotEnterReasonsForACaseClosure(String arg0) {

    }

    @When("^I refer the case to another Government Department$")
    public void iReferTheCaseToAnotherGovernmentDepartment() {
        clickOn(markUpDecision.referToOgdRadioButton);
    }
}
