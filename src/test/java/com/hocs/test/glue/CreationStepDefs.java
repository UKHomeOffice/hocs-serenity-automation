package com.hocs.test.glue;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.ukvi.Creation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreationStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    Creation creation;

    AddCorrespondent addCorrespondent;

    @When("I complete all required fields for Creation stage")
    public void iCompleteAllRequiredFieldsForCaseCreation() {
        creation.completeRequiredQuestions();
    }

    @When("I select {string} as the Business Area and {string} as the Reference Type")
    public void aCaseWithSpecificTopicIsCreated(String businessArea, String refType) {
        creation.selectBusinessArea(businessArea);
        creation.selectRefType(refType);
    }

    @And("I complete the other required fields for Creation stage")
    public void iCompleteTheOtherRequiredFieldsForCaseCreation() {
        creation.selectChannel("Email");
        creation.selectPriority("Standard");
    }

    @Then("the case summary should list the correct primary correspondent")
    public void theSummaryShouldListTheCorrectPrimaryCorrespondent() {
        homepage.getCurrentCase();
        workstacks.selectSummaryTab();
        workstacks.assertPrimaryCorrespondentIs(sessionVariableCalled("primaryCorrespondent"));
    }

    @Then("an error message should be displayed as I must complete all required questions at Creation stage")
    public void anErrorMessageShouldBeDisplayedAsIMustCompleteAllRequiredQuestionsAtCaseCreationStage() {
        creation.assertCaseCreationRequiredQuestionErrorMessages();
    }

    @Then("an error message should be displayed as I must enter a Primary Correspondent at Creation stage")
    public void anErrorMessageShouldBeDisplayedAsIMustEnterAPrimaryCorrespondentAtCaseCreationStage() {
        addCorrespondent.assertWhichIsThePrimaryCorrespondentIsRequiredErrorMessage();
    }
}
