package com.hocs.test.glue;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.ukvi.CaseCreation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CaseCreationStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    CaseCreation caseCreation;

    AddCorrespondent addCorrespondent;

    @When("I complete all required fields for Case Creation")
    public void iCompleteAllRequiredFieldsForCaseCreation() {
        caseCreation.completeRequiredQuestions();
    }

    @When("I select {string} as the Business Area and {string} as the Reference Type")
    public void aCaseWithSpecificTopicIsCreated(String businessArea, String refType) {
        caseCreation.selectBusinessArea(businessArea);
        caseCreation.selectRefType(refType);
    }

    @And("I complete the other required fields for Case Creation")
    public void iCompleteTheOtherRequiredFieldsForCaseCreation() {
        caseCreation.selectChannel("Email");
        caseCreation.selectPriority("Standard");
    }

    @Then("the case summary should list the correct primary correspondent")
    public void theSummaryShouldListTheCorrectPrimaryCorrespondent() {
        homepage.getCurrentCase();
        workstacks.selectSummaryTab();
        workstacks.assertPrimaryCorrespondentIs(sessionVariableCalled("primaryCorrespondent"));
    }

    @Then("an error message should be displayed as I must complete all required questions at Case Creation stage")
    public void anErrorMessageShouldBeDisplayedAsIMustCompleteAllRequiredQuestionsAtCaseCreationStage() {
        caseCreation.assertCaseCreationRequiredQuestionErrorMessages();
    }

    @Then("an error message should be displayed as I must enter a Primary Correspondent at Case Creation stage")
    public void anErrorMessageShouldBeDisplayedAsIMustEnterAPrimaryCorrespondentAtCaseCreationStage() {
        addCorrespondent.assertWhichIsThePrimaryCorrespondentIsRequiredErrorMessage();
    }
}
