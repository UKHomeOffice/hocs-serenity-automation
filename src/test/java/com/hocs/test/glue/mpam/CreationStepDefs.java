package com.hocs.test.glue.mpam;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.mpam.Creation;
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
    public void selectSpecificBusinessAreaAndRefType(String businessArea, String refType) {
        creation.selectBusinessArea(businessArea);
        creation.selectRefType(refType);
    }

    @And("I complete the other required fields for Creation stage")
    public void iCompleteTheOtherRequiredFieldsForCaseCreation() {
        creation.selectUrgency("Standard");
        creation.selectInboundChannel("Email");
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

    @And("I calculate the date {int} days ago")
    public void iCalculateTheDateDaysAgo(int days) {
        System.out.println(todayPlusMinusNDaysGetDay(-days));
        System.out.println(todayPlusMinusNMonthsGetMonth(-days));
        System.out.println(todayPlusMinusNYearsGetYear(-days));
    }

    @When("I select {string} as the Urgency and {string} as the Reference Type")
    public void selectSpecificUrgencyAndReferenceType(String urgency, String refType) {
        creation.selectUrgency(urgency);
        creation.selectRefType(refType);
    }
}
