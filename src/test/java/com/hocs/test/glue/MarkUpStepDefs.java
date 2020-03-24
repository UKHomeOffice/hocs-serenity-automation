package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.MarkupFull;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.summary.CaseSummaryPage;
import com.hocs.test.pages.timeline.TimelineTab;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.draft.Draft;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MarkUpStepDefs extends Page {

    Homepage homepage;

    Topics topics;

    Workstacks workstacks;

    MarkUpDecision markUpDecision;

    MarkupFull markup;

    Draft draft;

    SuccessfulCaseCreation successfulCaseCreation;

    QAResponse qaResponse;

    TimelineTab timelineTab;

    @When("I complete the markup stage")
    public void completeTheMarkupStage() {
        markup.markupStageFullFlow();
    }

    @When("I assign the Topic {string}")
    public void enterSpecificMarkupTopic(String topic) {
        clickOn(homepage.centralDraftingTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        clickOn(workstacks.allocateToMeButton);
        clickOn(homepage.home);
        clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        clickOn(markUpDecision.policyResponseRadioButton);
        clickOn(markUpDecision.continueButton);
        if (topic.toUpperCase().equals("NEW CHILD TOPIC")) {
            topics.enterATopic(sessionVariableCalled("newChildTopic").toString());
        } else {
            topics.enterATopic(topic);
        }
    }

    @When("I add the topic {string}")
    public void enterTheTopic(String topic) {
        switch (topic.toUpperCase()) {
            case "CARDIFF UNIVERSITY KITTENS":
                topics.enterATopicWithoutContinuingToTheDraftStage(topic);
                setSessionVariable("topic").to(topic);
                break;
            case "EXTREMISTS LEAFLETING THE PUBLIC":
                topics.enterATopicWithoutContinuingToTheDraftStage(topic);
                break;
            case "NEW CHILD TOPIC":
                topics.enterATopicWithoutContinuingToTheDraftStage(sessionVariableCalled("newChildTopic").toString());
                setSessionVariable("topic").to(sessionVariableCalled("newChildTopic").toString());
                break;
            default:
                pendingStep(topic + " is not defined within " + getMethodName());
        }
    }

    @When("I override the {string} team to {string}")
    public void overrideTheDefaultTeam(String defaultTeam, String overrideTeam) {
        switch (defaultTeam.toUpperCase()) {
            case "INITIAL DRAFT":
                topics.selectOverrideInitialDraftTeamByVisibleText(overrideTeam);
                clickOn(finishButton);
                break;
            case "PRIVATE OFFICE":
                topics.selectOverridePrivateOfficeTeamByVisibleText(overrideTeam);
                setSessionVariable("draftTeam").to(topics.autoAssignedDraftTeam.getValue());
                clickOn(finishButton);
                draft.initialDraftFullFlow();
                qaResponse.qaResponseFullFlow();
                break;
            default:
                pendingStep(defaultTeam + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not selected a topic")
    public void assertThatTopicIsRequiredErrorMessageIsShown() {
        markUpDecision.assertTopicIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected a response")
    public void assertThatMarkupResponseErrorMessageIsShown() {
        markUpDecision.assertSortOfResponseErrorMessage();
    }

    @Then("an error message should be displayed as I have not added a topic")
    public void assertThatAddATopicErrorMessageIsShown() {
        markUpDecision.assertAddATopicErrorMessage();
    }

    @Then("the topic should be added to the case")
    public void assertTopicOnCase() {
        markup.clickContinueButton();
        topics.assertTopicsAssigned();
    }

    @Then("the topic can be viewed in the case timeline")
    public void assertTopicIsInTimelines() {
        markup.clickContinueButton();
        topics.assertTopicIsAssignedThroughTimeline();
    }

    @Then("the case should be assigned to the {string} for drafting")
    public void theCaseShouldBeAssignedToTheDraftTeam(String draftingTeam) {
        if (draftingTeam.toUpperCase().equals("NEW DRAFTING AND QA TEAM")) {
            assertElementTextIs(topics.autoAssignedDraftTeam, sessionVariableCalled("chosenDraftAndQATeam").toString());
        } else {
            assertElementTextIs(topics.autoAssignedDraftTeam, draftingTeam);
        }
    }

    @Then("the case should be assigned to the {string} for approval")
    public void theCaseShouldBeAssignedToThePrivateOfficeTeam(String privateOfficeTeam) {
        if (privateOfficeTeam.toUpperCase().equals("NEW PRIVATE AND MINISTERIAL TEAM")) {
            assertElementTextIs(topics.autoAssignedPrivateOfficeTeam,
                    sessionVariableCalled("chosenPrivateAndMinisterTeam").toString());
        } else {
            assertElementTextIs(topics.autoAssignedPrivateOfficeTeam, privateOfficeTeam);
        }
    }

    @Then("the case should be found in the {string} team")
    public void theCaseShouldBeFoundInTheTeamTeam(String team) {
        homepage.goHome();
        switch (team.toUpperCase()) {
            case "PUBLIC PROTECTION UNIT":
                clickOn(homepage.publicProtectionUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                clickOn(homepage.animalsInScienceTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                clickOn(homepage.policeWorkforceProfessionalismUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER FOR LORDS":
                clickOn(homepage.ministerForLordsTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "EXTREMISM ANALYSIS UNIT":
                clickOn(homepage.extremismAnalysisUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "COUNTER EXTREMISM UNIT":
                clickOn(homepage.counterExtremismUnit);
                workstacks.assertCaseReferenceIsVisible();
            case "COUNTER-TERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                clickOn(homepage.counterTerrorismLegislationInvestigatoryPowersUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "PRESS OFFICE":
                clickOn(homepage.pressOffice);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "FINANCE":
                clickOn(homepage.financeTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "CHEMICAL, BIOLOGICAL, RADIOLOGICAL, NUCLEAR & EXPLOSIVES":
                clickOn(homepage.chemBioRadioNuclearExplosives);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR IMMIGRATION":
                clickOn(homepage.ministerOfStateForImmigrationTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR SECURITY AND ECONOMIC CRIME":
                clickOn(homepage.ministerOfStateForSecurityAndEconomicCrime);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR POLICING AND FIRE SERVICE":
                clickOn(homepage.ministerOfStateForPolicingAndFireServiceTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            default:
                pendingStep(team + " is not defined within " + getMethodName());
        }
    }

    @Then("the Other Government Department name free text field is displayed")
    public void assertOtherGvmtDepTBIsDisplayed() {
        markUpDecision.assertOGDTitleTextBoxIsDisplayed();
    }

    @Then("the No Response Needed casenote field is displayed")
    public void assertNoResponseNeededTextBox() {
        markUpDecision.assertNRNTextBoxIsDisplayed();
    }

    @Then("the reason for rejection casenote field is displayed")
    public void assertRejectionReasonTextBox() {
        markUpDecision.assertRejectTextBoxIsDisplayed();
    }

    @Then("a mandatory Topic free text field is displayed")
    public void aMandatoryFreeTextFieldIsAvailable() {
        topics.assertTopicsTextFieldDisplayed();
    }

    @When("I close the case with a decision of {string}")
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

    @When("I select an initial decision of {string}")
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
            case "REJECT TO DATA INPUT":
                markUpDecision.selectRejectToDataInput();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
    }
}
