package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
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

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the Markup stage")
    public void completeTheMarkupStage() {
        if (!markup.policyResponseRadioButton.isVisible()) {
            homepage.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        markup.moveCaseFromMarkupToInitialDraft();
    }

    @When("I assign the Topic {string}")
    public void enterSpecificMarkupTopic(String topic) {
        homepage.getAndClaimCurrentCase();
        safeClickOn(markup.policyResponseRadioButton);
        safeClickOn(markup.continueButton);
        if (topic.toUpperCase().equals("NEW CHILD TOPIC")) {
            markupAddTopics.enterATopic(sessionVariableCalled("newChildTopic").toString());
        } else {
            markupAddTopics.enterATopic(topic);
        }
    }

    @When("I add the topic {string}")
    public void enterTheTopic(String topic) {
        markupAddTopics.enterATopicWithoutContinuingToTheDraftStage(topic);
        setSessionVariable("topic").to(topic);
    }

    @When("I add the new child topic")
    public void iAddTheNewChildTopic() {
        enterTheTopic(sessionVariableCalled("newChildTopic").toString());
    }

    @When("I override the {string} team to {string}")
    public void overrideTheDefaultTeam(String defaultTeam, String overrideTeam) {
        switch (defaultTeam.toUpperCase()) {
            case "INITIAL DRAFT":
                markupAddTopics.selectOverrideInitialDraftTeamByVisibleText(overrideTeam);
                safeClickOn(finishButton);
                break;
            case "PRIVATE OFFICE":
                markupAddTopics.selectOverridePrivateOfficeTeamByVisibleText(overrideTeam);
                setSessionVariable("draftTeam").to(markupAddTopics.autoAssignedDraftTeam.getValue());
                safeClickOn(finishButton);
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
        markup.assertTopicIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected a response")
    public void assertThatMarkupResponseErrorMessageIsShown() {
        markup.assertSortOfResponseErrorMessage();
    }

    @Then("an error message should be displayed as I have not added a topic")
    public void assertThatAddATopicErrorMessageIsShown() {
        markup.assertAddATopicErrorMessage();
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
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                safeClickOn(homepage.animalsInScienceTeam);
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                safeClickOn(homepage.policeWorkforceProfessionalismUnit);
                break;
            case "MINISTER FOR LORDS":
                safeClickOn(homepage.ministerForLordsTeam);
                break;
            case "EXTREMISM ANALYSIS UNIT":
                safeClickOn(homepage.extremismAnalysisUnit);
                break;
            case "COUNTER EXTREMISM UNIT":
                safeClickOn(homepage.counterExtremismUnit);
            case "COUNTER-TERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                safeClickOn(homepage.counterTerrorismLegislationInvestigatoryPowersUnit);
                break;
            case "PRESS OFFICE":
                safeClickOn(homepage.pressOffice);
                break;
            case "FINANCE":
                safeClickOn(homepage.financeTeam);
                break;
            case "CHEMICAL, BIOLOGICAL, RADIOLOGICAL, NUCLEAR & EXPLOSIVES":
                safeClickOn(homepage.chemBioRadioNuclearExplosives);
                break;
            case "MINISTER OF STATE FOR IMMIGRATION":
                safeClickOn(homepage.ministerOfStateForImmigrationTeam);
                break;
            case "MINISTER OF STATE FOR SECURITY AND ECONOMIC CRIME":
                safeClickOn(homepage.ministerOfStateForSecurityAndEconomicCrime);
                break;
            case "MINISTER OF STATE FOR POLICING AND FIRE SERVICE":
                safeClickOn(homepage.ministerOfStateForPolicingAndFireServiceTeam);
                break;
            default:
                pendingStep(team + " is not defined within " + getMethodName());
        }
        workstacks.assertVisibilityOfCaseReference(true);
    }

    @Then("the Other Government Department name free text field is displayed")
    public void assertOtherGvmtDepTBIsDisplayed() {
        markup.assertOGDDestinationTextBoxIsDisplayed();
    }

    @Then("the No Response Needed casenote field is displayed")
    public void assertNoResponseNeededTextBox() {
        markup.assertNRNTextBoxIsDisplayed();
    }

    @Then("the reason for rejection casenote field is displayed")
    public void assertRejectionReasonTextBox() {
        markup.assertRejectTextBoxIsDisplayed();
    }

    @Then("a mandatory Topic free text field is displayed")
    public void aMandatoryFreeTextFieldIsAvailable() {
        markupAddTopics.assertTopicsTextFieldDisplayed();
    }

    @When("I close the case with a decision of {string}")
    public void iCloseTheCaseWithADecisionOf(String status) {
        switch (status.toUpperCase()) {
            case "REFER TO OGD":
                safeClickOn(markup.referToOgdRadioButton);
                break;
            case "NO REPLY NEEDED":
                safeClickOn(markup.noReplyNeededRadioButton);
                break;
            default:
                pendingStep(status + " is not defined within " + getMethodName());
        }
        safeClickOn(markup.continueButton);
        safeClickOn(finishButton);
    }

    @When("I select an initial decision of {string}")
    public void iSelectAnInitialDecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "FAQ":
                markup.selectFAQRadioButton();
                break;
            case "NO RESPONSE NEEDED":
                markup.selectNoReplyNeededRadioButton();
                break;
            case "POLICY RESPONSE":
                markup.selectPolicyResponseRadioButton();
                break;
            case "REFER TO OGD":
                markup.selectReferToOGDRadioButton();
                break;
            case "REJECT TO DATA INPUT":
                markup.selectRejectToDataInput();
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

    @And("I complete Markup with {string} selected as the Private Office team")
    public void iCompleteMarkupWithAsThePrivateOfficeTeam(String privateOfficeTeam) {
        markup.getToMarkupEnterANewTopicScreenPrerequisites();
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        markupAddTopics.selectOverridePrivateOfficeTeamByVisibleText(privateOfficeTeam);
        safeClickOn(finishButton);
    }
}
