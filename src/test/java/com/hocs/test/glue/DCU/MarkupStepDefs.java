package com.hocs.test.glue.DCU;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.dcu.InitialDraft;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MarkupStepDefs extends BasePage {

    Homepage homepage;

    Markup_AddTopics markupAddTopics;

    Workstacks workstacks;

    Markup markupDecision;

    Markup markup;

    InitialDraft initialDraft;

    CreateCase_SuccessPage createCaseSuccessPage;

    QAResponse qaResponse;

    @When("I complete the Markup stage")
    public void completeTheMarkupStage() {
        if (homepage.myCases.isVisible()) {
            homepage.getCurrentCase();
            safeClickOn(workstacks.allocateToMeButton);
        }
        markup.moveCaseFromMarkupToInitialDraft();
    }

    @When("I assign the Topic {string}")
    public void enterSpecificMarkupTopic(String topic) {
        homepage.getAndClaimCurrentCase();
        safeClickOn(markupDecision.policyResponseRadioButton);
        safeClickOn(markupDecision.continueButton);
        if (topic.toUpperCase().equals("NEW CHILD TOPIC")) {
            markupAddTopics.enterATopic(sessionVariableCalled("newChildTopic").toString());
        } else {
            markupAddTopics.enterATopic(topic);
        }
    }

    @When("I add the topic {string}")
    public void enterTheTopic(String topic) {
        switch (topic.toUpperCase()) {
            case "CARDIFF UNIVERSITY KITTENS":
                markupAddTopics.enterATopicWithoutContinuingToTheDraftStage(topic);
                setSessionVariable("topic").to(topic);
                break;
            case "EXTREMISTS LEAFLETING THE PUBLIC":
                markupAddTopics.enterATopicWithoutContinuingToTheDraftStage(topic);
                break;
            case "NEW CHILD TOPIC":
                markupAddTopics.enterATopicWithoutContinuingToTheDraftStage(sessionVariableCalled("newChildTopic").toString());
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
                markupAddTopics.selectOverrideInitialDraftTeamByVisibleText(overrideTeam);
                safeClickOn(finishButton);;
                break;
            case "PRIVATE OFFICE":
                markupAddTopics.selectOverridePrivateOfficeTeamByVisibleText(overrideTeam);
                setSessionVariable("draftTeam").to(markupAddTopics.autoAssignedDraftTeam.getValue());
                safeClickOn(finishButton);;
                homepage.getAndClaimCurrentCase();
                initialDraft.moveCaseFromInitialDraftToQaResponse();
                qaResponse.qaResponseFullFlow();
                break;
            default:
                pendingStep(defaultTeam + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not selected a topic")
    public void assertThatTopicIsRequiredErrorMessageIsShown() {
        markupDecision.assertTopicIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected a response")
    public void assertThatMarkupResponseErrorMessageIsShown() {
        markupDecision.assertSortOfResponseErrorMessage();
    }

    @Then("an error message should be displayed as I have not added a topic")
    public void assertThatAddATopicErrorMessageIsShown() {
        markupDecision.assertAddATopicErrorMessage();
    }

    @Then("the topic should be added to the case")
    public void assertTopicOnCase() {
        markup.clickContinueButton();
        markupAddTopics.assertTopicsAssigned();
    }

    @Then("the topic can be viewed in the case timeline")
    public void assertTopicIsInTimelines() {
        markup.clickContinueButton();
        markupAddTopics.assertTopicIsAssignedThroughTimeline();
    }

    @Then("the case should be assigned to the {string} for drafting")
    public void theCaseShouldBeAssignedToTheDraftTeam(String draftingTeam) {
        if (draftingTeam.toUpperCase().equals("NEW DRAFTING AND QA TEAM")) {
            assertElementTextIs(markupAddTopics.autoAssignedDraftTeam, sessionVariableCalled("chosenDraftAndQATeam").toString());
        } else {
            assertElementTextIs(markupAddTopics.autoAssignedDraftTeam, draftingTeam);
        }
    }

    @Then("the case should be assigned to the {string} for approval")
    public void theCaseShouldBeAssignedToThePrivateOfficeTeam(String privateOfficeTeam) {
        if (privateOfficeTeam.toUpperCase().equals("NEW PRIVATE AND MINISTERIAL TEAM")) {
            assertElementTextIs(markupAddTopics.autoAssignedPrivateOfficeTeam,
                    sessionVariableCalled("chosenPrivateAndMinisterTeam").toString());
        } else {
            assertElementTextIs(markupAddTopics.autoAssignedPrivateOfficeTeam, privateOfficeTeam);
        }
    }

    @Then("the case should be found in the {string} team")
    public void theCaseShouldBeFoundInTheTeamTeam(String team) {
        homepage.goHome();
        switch (team.toUpperCase()) {
            case "PUBLIC PROTECTION UNIT":
                safeClickOn(homepage.publicProtectionUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                safeClickOn(homepage.animalsInScienceTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                safeClickOn(homepage.policeWorkforceProfessionalismUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER FOR LORDS":
                safeClickOn(homepage.ministerForLordsTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "EXTREMISM ANALYSIS UNIT":
                safeClickOn(homepage.extremismAnalysisUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "COUNTER EXTREMISM UNIT":
                safeClickOn(homepage.counterExtremismUnit);
                workstacks.assertCaseReferenceIsVisible();
            case "COUNTER-TERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                safeClickOn(homepage.counterTerrorismLegislationInvestigatoryPowersUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "PRESS OFFICE":
                safeClickOn(homepage.pressOffice);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "FINANCE":
                safeClickOn(homepage.financeTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "CHEMICAL, BIOLOGICAL, RADIOLOGICAL, NUCLEAR & EXPLOSIVES":
                safeClickOn(homepage.chemBioRadioNuclearExplosives);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR IMMIGRATION":
                safeClickOn(homepage.ministerOfStateForImmigrationTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR SECURITY AND ECONOMIC CRIME":
                safeClickOn(homepage.ministerOfStateForSecurityAndEconomicCrime);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR POLICING AND FIRE SERVICE":
                safeClickOn(homepage.ministerOfStateForPolicingAndFireServiceTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            default:
                pendingStep(team + " is not defined within " + getMethodName());
        }
    }

    @Then("the Other Government Department name free text field is displayed")
    public void assertOtherGvmtDepTBIsDisplayed() {
        markupDecision.assertOGDDestinationTextBoxIsDisplayed();
    }

    @Then("the No Response Needed casenote field is displayed")
    public void assertNoResponseNeededTextBox() {
        markupDecision.assertNRNTextBoxIsDisplayed();
    }

    @Then("the reason for rejection casenote field is displayed")
    public void assertRejectionReasonTextBox() {
        markupDecision.assertRejectTextBoxIsDisplayed();
    }

    @Then("a mandatory Topic free text field is displayed")
    public void aMandatoryFreeTextFieldIsAvailable() {
        markupAddTopics.assertTopicsTextFieldDisplayed();
    }

    @When("I close the case with a decision of {string}")
    public void iCloseTheCaseWithADecisionOf(String status) {
        switch (status.toUpperCase()) {
            case "REFER TO OGD":
                safeClickOn(markupDecision.referToOgdRadioButton);
                break;
            case "NO REPLY NEEDED":
                safeClickOn(markupDecision.noReplyNeededRadioButton);
                break;
            default:
                pendingStep(status + " is not defined within " + getMethodName());
        }
        safeClickOn(markupDecision.continueButton);
        safeClickOn(finishButton);;
    }

    @When("I select an initial decision of {string}")
    public void iSelectAnInitialDecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "FAQ":
                markupDecision.selectFAQRadioButton();
                break;
            case "NO RESPONSE NEEDED":
                markupDecision.selectNoReplyNeededRadioButton();
                break;
            case "POLICY RESPONSE":
                markupDecision.selectPolicyResponseRadioButton();
                break;
            case "REFER TO OGD":
                markupDecision.selectReferToOGDRadioButton();
                break;
            case "REJECT TO DATA INPUT":
                markupDecision.selectRejectToDataInput();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
    }

    @And("I click the Add a topic link")
    public void iClickTheAddATopicLink() {
        markupAddTopics.clickAddTopicLink();
    }

    @And("I enter a transfer destination and transfer reason")
    public void iEnterATransferDestinationAndTransferReason() {
        markup.enterOGDDestinationAndReason();
    }

    @And("I enter a reason that no response is needed")
    public void iEnterAReasonThatNoResponseIsNeeded() {
        markup.enterNRNreason();
    }
}
