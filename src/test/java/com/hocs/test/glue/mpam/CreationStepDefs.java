package com.hocs.test.glue.mpam;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.mpam.Creation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreationStepDefs extends BasePage {

    Dashboard dashboard;

    Creation creation;

    Correspondents correspondents;

    SummaryTab summaryTab;

    @When("I complete all required fields for Creation stage")
    public void iCompleteAllRequiredFieldsForCaseCreation() {
        creation.completeRequiredQuestions();
    }

    @When("I select {string} as the Business Area and {string} as the Reference Type")
    public void selectSpecificBusinessAreaAndRefType(String businessArea, String refType) {
        creation.selectASpecificBusinessArea(businessArea);
        creation.selectASpecificRefType(refType);
    }

    @And("I complete the other required fields for Creation stage")
    public void iCompleteTheOtherRequiredFieldsForCaseCreation() {
        creation.selectASpecificMinisterialSignOffTeam("Home Secretary");
        creation.selectASpecificAddressee("Home Secretary");
        creation.selectASpecificUrgency("Standard");
        creation.selectASpecificInboundChannel("Email");
    }

    @Then("the case summary should list the correct primary correspondent")
    public void theSummaryShouldListTheCorrectPrimaryCorrespondent() {
        dashboard.ensureViewingCurrentCase();
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("primaryCorrespondent"), "Primary correspondent");
    }

    @When("I select {string} as the Urgency and {string} as the Reference Type")
    public void selectSpecificUrgencyAndReferenceType(String urgency, String refType) {
        creation.selectASpecificUrgency(urgency);
        creation.selectASpecificRefType(refType);
    }

    @And("I select {string} as the Ministerial sign off team when completing the creation stage")
    public void selectAsSignOffTeamWhenCompletingTheCreationStage(String signOffTeam) {
        creation.selectASpecificBusinessArea("UKVI");
        creation.selectASpecificRefType("Ministerial");
        creation.selectASpecificMinisterialSignOffTeam(signOffTeam);
        creation.selectASpecificAddressee(signOffTeam);
        creation.selectASpecificUrgency("Standard");
        creation.selectASpecificInboundChannel("Email");
        clickContinueButton();
        correspondents.addAMemberCorrespondent();
        clickTheButton("Move to Triage");
    }

    @And("I try to advance a case with a public correspondent at Creation stage")
    public void iTryToAdvanceACaseWithAPublicCorrespondentAtCreationStage() {
        creation.triggerMPCorrespondentIsMandatoryScreen();
    }

    @Then("the MP correspondent is mandatory screen is displayed")
    public void theMPCorrespondentIsMandatoryScreenIsDisplayed() {
        creation.assertMPCorrespondentIsRequiredScreenIsDisplayed();
    }

    @When("I enter the details of a/an {string} MPAM case")
    public void iEnterTheDetailsOfAMPAMCase(String refType) {
        creation.selectABusinessArea();
        creation.selectASpecificRefType(refType);
        if (refType.equalsIgnoreCase("MINISTERIAL")) {
            creation.selectAMinisterialSignOffTeam();
            creation.selectAnAddressee();
        }
        creation.selectAnUrgency();
        creation.selectAnInboundChannel();
        clickContinueButton();
    }
}
