package com.hocs.test.glue;

import com.hocs.test.pages.ukvi.CaseCreation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CaseCreationStepDefs {

    CaseCreation caseCreation;

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

    @Then("an error message should be displayed as I must enter a Primary Correspondent at Case Creation stage")
    public void anErrorMessageShouldBeDisplayedAsIMustEnterAPrimaryCorrespondentAtCaseCreationStage() {

    }
}
