package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.UnallocatedCaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MarkupStepDefs extends BasePage {

    Dashboard dashboard;

    Markup_AddTopics markupAddTopics;

    Workstacks workstacks;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the Markup stage")
    public void completeTheMarkupStage() {
        if (!continueButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        markup.moveCaseFromMarkupToInitialDraft();
    }

    @When("I assign the Topic {string}")
    public void enterSpecificMarkupTopic(String topic) {
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        if (topic.equalsIgnoreCase("NEW CHILD TOPIC")) {
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
                markupAddTopics.selectSpecificOverrideInitialDraftTeam(overrideTeam);
                safeClickOn(finishButton);
                break;
            case "PRIVATE OFFICE":
                markupAddTopics.selectSpecificOverridePrivateOfficeTeam(overrideTeam);
                setSessionVariable("draftTeam").to(markupAddTopics.autoAssignedDraftTeam.getValue());
                safeClickOn(finishButton);
                dashboard.getAndClaimCurrentCase();
                initialDraft.moveCaseFromInitialDraftToQaResponse();
                qaResponse.qaResponseFullFlow();
                break;
            default:
                pendingStep(defaultTeam + " is not defined within " + getMethodName());
        }
    }

    @And("I override the initial draft team of the case to the team created in Management UI")
    public void iOverrideTheInitialDraftTeamOfTheCaseToTheTeamCreatedInMUI() {
        markupAddTopics.selectSpecificOverrideInitialDraftTeam(sessionVariableCalled("draftingTeamName"));
        safeClickOn(finishButton);
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
        goToDashboard();
        switch (team.toUpperCase()) {
            case "PUBLIC PROTECTION UNIT":
                safeClickOn(dashboard.publicProtectionUnit);
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT":
                safeClickOn(dashboard.animalsInScienceTeam);
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT":
                safeClickOn(dashboard.policeWorkforceProfessionalismUnit);
                break;
            case "MINISTER FOR LORDS":
                safeClickOn(dashboard.ministerForLordsTeam);
                break;
            case "EXTREMISM ANALYSIS UNIT":
                safeClickOn(dashboard.extremismAnalysisUnit);
                break;
            case "COUNTER EXTREMISM UNIT":
                safeClickOn(dashboard.counterExtremismUnit);
            case "COUNTER-TERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT":
                safeClickOn(dashboard.counterTerrorismLegislationInvestigatoryPowersUnit);
                break;
            case "PRESS OFFICE":
                safeClickOn(dashboard.pressOffice);
                break;
            case "FINANCE":
                safeClickOn(dashboard.financeTeam);
                break;
            case "CHEMICAL, BIOLOGICAL, RADIOLOGICAL, NUCLEAR & EXPLOSIVES":
                safeClickOn(dashboard.chemBioRadioNuclearExplosives);
                break;
            case "MINISTER OF STATE FOR IMMIGRATION":
                safeClickOn(dashboard.ministerOfStateForImmigrationTeam);
                break;
            case "MINISTER OF STATE FOR SECURITY AND ECONOMIC CRIME":
                safeClickOn(dashboard.ministerOfStateForSecurityAndEconomicCrime);
                break;
            case "MINISTER OF STATE FOR POLICING AND FIRE SERVICE":
                safeClickOn(dashboard.ministerOfStateForPolicingAndFireServiceTeam);
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

    @When("I select an initial decision of {string}")
    public void iSelectAnInitialDecisionOf(String decision) {
        markup.selectASpecificResponseType(decision);
    }

    @And("I click the Add a topic link")
    public void iClickTheAddATopicLink() {
        markupAddTopics.clickAddTopicLink();
    }

    @And("I enter a transfer destination and transfer reason")
    public void iEnterATransferDestinationAndTransferReason() {
        markup.enterAOGDDestination();
        markup.enterAOGDReason();
    }

    @And("I enter a reason that no response is needed")
    public void iEnterAReasonThatNoResponseIsNeeded() {
        markup.enterANoResponseNeededReason();
    }

    @And("I complete Markup with {string} selected as the Private Office team")
    public void iCompleteMarkupWithAsThePrivateOfficeTeam(String privateOfficeTeam) {
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        markupAddTopics.clickAddTopicLink();
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        markupAddTopics.selectSpecificOverridePrivateOfficeTeam(privateOfficeTeam);
        safeClickOn(finishButton);
    }

    @And("I complete the Markup stage, with the Home Secretary team selected as the Drafting team")
    public void iCompleteTheMarkupStageWithTheHomeSecretaryTeamSelectedAsTheDraftingTeam() {
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        markupAddTopics.clickAddTopicLink();
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }
}
