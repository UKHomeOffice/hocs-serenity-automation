package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.TimelineTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MarkupStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Markup markup;

    SummaryTab summaryTab;

    TimelineTab timelineTab;

    @When("I assign the Topic {string}")
    public void enterSpecificMarkupTopic(String topic) {
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
        clickContinueButton();
        if (topic.equalsIgnoreCase("NEW CHILD TOPIC")) {
            topic = sessionVariableCalled("newChildTopic").toString();
        }
        markup.addTopicToCase(topic);
        markup.confirmPrimaryTopic();
    }

    @When("I add the topic {string}")
    public void enterTheTopic(String topic) {
        markup.addTopicToCase(topic);
        setSessionVariable("topic").to(topic);
    }

    @When("I add the new child topic")
    public void iAddTheNewChildTopic() {
        enterTheTopic(sessionVariableCalled("newChildTopic").toString());
    }

    @When("I complete the Markup stage overriding the {string} team to {string}")
    public void overrideTheDefaultTeam(String defaultTeam, String overrideTeam) {
        markup.selectPolicyResponseRadioButton();
        clickContinueButton();
        markup.addTopicToCase("Animal Alternatives (3Rs)");
        markup.confirmPrimaryTopic();
        switch (defaultTeam.toUpperCase()) {
            case "INITIAL DRAFT":
                markup.selectSpecificOverrideInitialDraftTeam(overrideTeam);
                break;
            case "PRIVATE OFFICE":
                markup.selectSpecificOverridePrivateOfficeTeam(overrideTeam);
                setSessionVariable("draftTeam").to(markup.defaultDraftTeam.getValue());
                break;
            default:
                pendingStep(defaultTeam + " is not defined within " + getMethodName());
        }
        clickFinishButton();
    }

    @And("I override the initial draft team of the case to the team created in Management UI")
    public void iOverrideTheInitialDraftTeamOfTheCaseToTheTeamCreatedInMUI() {
        markup.selectSpecificOverrideInitialDraftTeam(sessionVariableCalled("draftingTeamName"));
        clickFinishButton();
    }

    @Then("the topic should be added to the case")
    public void assertTopicOnCase() {
        markup.clickContinueButton();
        summaryTab.selectSummaryTab();
        summaryTab.assertSummaryContainsExpectedValueForGivenHeader(sessionVariableCalled("topic"), "Primary topic");
    }

    @Then("the topic can be viewed in the case timeline")
    public void assertTopicIsInTimelines() {
        markup.clickContinueButton();
        timelineTab.selectTimelineTab();
        timelineTab.assertTopicAddedLogVisible();
    }

    @Then("the case should be assigned to the {string} for drafting")
    public void theCaseShouldBeAssignedToTheDraftTeam(String draftingTeam) {
        if (draftingTeam.equalsIgnoreCase("NEW DRAFTING AND QA TEAM")) {
            assertElementTextIs(markup.defaultDraftTeam, sessionVariableCalled("chosenDraftAndQATeam").toString());
        } else {
            assertElementTextIs(markup.defaultDraftTeam, draftingTeam);
        }
    }

    @Then("the case should be assigned to the {string} for approval")
    public void theCaseShouldBeAssignedToThePrivateOfficeTeam(String privateOfficeTeam) {
        if (privateOfficeTeam.equalsIgnoreCase("NEW PRIVATE AND MINISTERIAL TEAM")) {
            assertElementTextIs(markup.defaultPrivateOfficeTeam,
                    sessionVariableCalled("chosenPrivateAndMinisterTeam").toString());
        } else {
            assertElementTextIs(markup.defaultPrivateOfficeTeam, privateOfficeTeam);
        }
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
        markup.assertTopicsTextFieldDisplayed();
    }

    @When("I select an initial decision of {string}")
    public void iSelectAnInitialDecisionOf(String decision) {
        markup.selectASpecificResponseType(decision);
        clickContinueButton();
    }

    @And("I click the Add a topic link")
    public void iClickTheAddATopicLink() {
        markup.clickAddTopicLink();
    }

    @And("I submit a transfer destination and transfer reason")
    public void iEnterATransferDestinationAndTransferReason() {
        markup.enterAOGDDestination();
        markup.enterAOGDReason();
        clickFinishButton();
    }

    @And("I submit a reason that no response is needed")
    public void iEnterAReasonThatNoResponseIsNeeded() {
        markup.enterANoResponseNeededReason();
        clickFinishButton();
    }

    @And("I complete Markup with {string} selected as the Private Office team")
    public void iCompleteMarkupWithAsThePrivateOfficeTeam(String privateOfficeTeam) {
        markup.selectPolicyResponseRadioButton();
        clickContinueButton();
        markup.addTopicToCase("Animal alternatives (3Rs)");
        markup.confirmPrimaryTopic();
        markup.selectSpecificOverridePrivateOfficeTeam(privateOfficeTeam);
        clickFinishButton();
        dashboard.waitForDashboard();
    }

    @And("I select a Primary topic of {string}")
    public void iSelectAPrimaryTopicOf(String topic) {
        markup.addTopicToCase(topic);
        markup.confirmPrimaryTopic();
    }

    @And("I override the Initial Draft team to {string}")
    public void iOverrideTheInitialDraftTeamTo(String initialDraftTeam) {
        markup.selectSpecificOverrideInitialDraftTeam(initialDraftTeam);
    }

    @And("I override the Private Office team to {string}")
    public void iOverrideThePrivateOfficeTeamTo(String privateOfficeTeam) {
        markup.selectSpecificOverridePrivateOfficeTeam(privateOfficeTeam);
    }

    @And("I confirm the (Initial Draft)( and )(Private Office) team")
    public void iAcceptTheSelectedInitialDraftAndPrivateOfficeTeam() {
        markup.confirmInitialDraftAndOrPrivateOfficeTeam();
    }

    @And("I reject the case at the Markup stage")
    public void iRejectTheCaseAtTheStage() {
        markup.selectRejectToDataInput();
        clickContinueButton();
        markup.enterARejectionReason();
        clickFinishButton();
    }

    @But("I do not enter a {string}")
    public void iDoNotEnterA() {
        clickFinishButton();
    }
}
