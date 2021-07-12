package com.hocs.test.glue.ukvi;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.platform.AddCorrespondent;
import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.Dashboard;
import com.hocs.test.pages.platform.Workstacks;
import com.hocs.test.pages.ukvi.Creation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreationStepDefs extends BasePage {

    Dashboard dashboard;

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
        creation.selectMinisterialSignOffTeam("Home Secretary");
        creation.selectAddressee("Home Secretary");
        creation.selectUrgency("Standard");
        creation.selectInboundChannel("Email");
    }

    @Then("the case summary should list the correct primary correspondent")
    public void theSummaryShouldListTheCorrectPrimaryCorrespondent() {
        dashboard.getCurrentCase();
        workstacks.selectSummaryTab();
        workstacks.assertPrimaryCorrespondentIs(sessionVariableCalled("primaryCorrespondent"));
    }

    @When("I select {string} as the Urgency and {string} as the Reference Type")
    public void selectSpecificUrgencyAndReferenceType(String urgency, String refType) {
        creation.selectUrgency(urgency);
        creation.selectRefType(refType);
    }

    @And("I select {string} as the Ministerial sign off team when completing the creation stage")
    public void selectAsSignOffTeamWhenCompletingTheCreationStage(String signOffTeam) {
        creation.moveCaseWithSpecificMinisterialSignOffTeamToTriageStage(signOffTeam);
    }

    @And("I try to advance a case with a public correspondent at Creation stage")
    public void iTryToAdvanceACaseWithAPublicCorrespondentAtCreationStage() {
        creation.triggerMPCorrespondentIsMandatoryScreen();
    }

    @Then("the MP correspondent is mandatory screen is displayed")
    public void theMPCorrespondentIsMandatoryScreenIsDisplayed() {
        creation.assertMPCorrespondentIsRequiredScreenIsDisplayed();
    }
}
