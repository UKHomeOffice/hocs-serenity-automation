package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.draft.Draft;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class MarkUpStepDefs {

    Homepage homepage;

    Page page;

    Topics topics;

    Workstacks workstacks;

    MarkUpDecision markUpDecision;

    SuccessfulCaseCreation successfulCaseCreation;

    QAResponse qaResponse;

    Draft draft;

    DraftingTeamDecision draftingTeamDecision;

    @When("^I complete the markup stage$")
    public void completeTheMarkupStage() {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        topics.clickAddTopicButton();
        topics.enterRealTopic();
        page.sleep(1000);
        markUpDecision.clickAddButton();
        page.sleep(1000);
        markUpDecision.clickContinueButton();
        page.sleep(1000);
        markUpDecision.clickFinishButton();
    }

    @When("^I assign the Topic \"([^\"]*)\"$")
    public void enterSpecificMarkupTopic(String topic) {
        homepage.selectCentralDraftingTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        switch (topic.toUpperCase()) {
            case "CARDIFF UNIVERSITY KITTENS":
                topics.enterATopic(topic);
                break;
            case "CYBER STALKING AND HARASSMENT" :
                topics.enterATopic(topic);
                break;
            case "DOMESTIC VIOLENCE PROTECTION ORDERS" :
                topics.enterATopic(topic);
                break;
            default:
                System.out.println(topic
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                topic = null;
                assumeNotNull(topic);
        }

    }

    @When("^I override the \"([^\"]*)\" team to \"([^\"]*)\"$")
    public void overrideTheDefaultTeam(String defaultTeam, String overrideTeam) {
        switch (defaultTeam.toUpperCase()) {
            case "INITIAL DRAFT" :
                topics.selectOverrideInitialDraftTeamByVisibleText(overrideTeam);
                page.clickFinishButton();
                break;
            case "PRIVATE OFFICE" :
                topics.selectOverridePrivateOfficeTeamByVisibleText(overrideTeam);
                setSessionVariable("draftTeam").to(topics.autoAssignedDraftTeam.getValue());
                page.clickFinishButton();
                draftingTeamDecision.initialDraftFullFlow();
                qaResponse.qaResponseFullFlow();
                break;
            default:
                pendingStep(defaultTeam + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the continue button on the markup response screen$")
    public void clickContinueButtonOnMarkupResponseScreen() {
        workstacks.clickAllocateToMeButton();
        markUpDecision.clickContinueButton();
    }

    @When("^I click the continue button on the add a topic screen$")
    public void clickContinueButtonOnAddATopicScreen() {
        workstacks.clickAllocateToMeButton();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        markUpDecision.sleep(500);
        markUpDecision.clickContinueButton();
    }

    @When("^I click the add button on the add topic screen")
    public void clickAddButtonOnAddTopicScreen() {
        workstacks.clickAllocateToMeButton();
        markUpDecision.clickPolicyResponseRadioButton();
        markUpDecision.clickContinueButton();
        markUpDecision.sleep(500);
        markUpDecision.clickAddTopic();
        markUpDecision.clickAddButton();
    }

    @Then("^an error message should be displayed as I have not selected a topic$")
    public void assertThatTopicIsRequiredErrorMessageIsShown() {
        markUpDecision.assertTopicIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected a response$")
    public void assertThatMarkupResponseErrorMessageIsShown() {
        markUpDecision.assertSortOfResponseErrorMessage();
    }

    @Then("^an error message should be displayed as I have not added a topic$")
    public void assertThatAddATopicErrorMessageIsShown() {
        markUpDecision.assertAddATopicErrorMessage();
    }

    @Then("^the topic should be set as the \"([^\"]*)\" topic$")
    public void theTopicShouldBeSetAsTheOrdinalTopic(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                System.out.println(ordinal
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                ordinal = null;
                assumeNotNull(ordinal);
        }
    }

    @Then("^the case should be assigned to the \"([^\"]*)\" for drafting$")
    public void theCaseShouldBeAssignedToTheDraftTeam(String draftingTeam) {
        switch (draftingTeam.toUpperCase()) {
            case "ANIMALS IN SCIENCE REGULATION UNIT" :
                topics.assertElementTextIs(
                        topics.autoAssignedDraftTeam, draftingTeam);
                break;
            case "PUBLIC PROTECTION UNIT" :
                topics.assertElementTextIs(
                        topics.autoAssignedDraftTeam, draftingTeam);
                break;
            case "DOMESTIC VIOLENCE PROTECTION ORDERS" :
                topics.assertElementTextIs(
                        topics.autoAssignedDraftTeam, draftingTeam);
                break;
            default:
                System.out.println(draftingTeam
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                draftingTeam = null;
                assumeNotNull(draftingTeam);
        }
    }

    @Then("^the case should be assigned to the \"([^\"]*)\" for approval$")
    public void theCaseShouldBeAssignedToThePrivateOfficeTeam(String privateOfficeTeam) {
        switch (privateOfficeTeam.toUpperCase()) {
            case "MINISTER FOR LORDS" :
                topics.assertElementTextIs(topics.autoAssignedPrivateOfficeTeam,
                        privateOfficeTeam);
                break;
            case "UNDER SECRETARY OF STATE FOR CRIME, SAFEGUARDING AND VULNERABILITY" :
                topics.assertElementTextIs(topics.autoAssignedPrivateOfficeTeam,
                        privateOfficeTeam);
                break;
        }
    }

    @When("^I select a primary topic$")
    public void iSelectAPrimaryTopic() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the case should be found in the \"([^\"]*)\" team$")
    public void theCaseShouldBeFoundInTheTeamTeam(String team) {
        homepage.goHome();
        switch (team.toUpperCase()) {
            case "PUBLIC PROTECTION UNIT" :
                homepage.selectPublicProtectionUnit();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "WINDRUSH COMPENSATION TEAM" :
                //homepage.selectWindrusCompensationTeam();
                //workstacks.assertCaseReferenceIsVisible();
                break;
            case "POLICE STRATEGY & REFORM UNIT" :
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT" :
                homepage.selectAnimalsInScienceTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT" :
                homepage.selectPoliceWorkforceProfessionalismTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER FOR LORDS" :
                homepage.selectMinisterForLordsTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "EXTREMISM ANALYSIS UNIT" :
                homepage.selectExtremismAnalysisUnit();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "COUNTER EXTREMISM UNIT" :
                homepage.selectCounterExtremismUnit();
                workstacks.assertCaseReferenceIsVisible();
            case "UNDER SECRETARY OF STATE FOR CRIME, SAFEGUARDING AND VULNERABILITY" :
                //homepage.selectUnderSecretaryStateCrimeSafeguarding();
                //workstacks.assertCaseReferenceIsVisible();
                break;
            case "COUNTER-TERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT" :
                homepage.selectCounterTerrorismLegislationInvestigatoryPowersUnit();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "PRESS OFFICE" :
                homepage.selectPressOffice();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "FINANCE" :
                homepage.selectFinanceTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "CHEMICAL, BIOLOGICAL, RADIOLOGICAL, NUCLEAR & EXPLOSIVES" :
                homepage.selectChemBioRadioNuclearExplosivesTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR IMMIGRATION" :
                homepage.selectImmigrationMinisterTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR SECURITY AND ECONOMIC CRIME" :
                homepage.selectMinisterOfStateForSecurityEconomicCrimeTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR POLICING AND FIRE SERVICE" :
                homepage.selectMinisterOfStatePolicingFireTeam();
                workstacks.assertCaseReferenceIsVisible();
                break;
            default:
                pendingStep(team + " is not defined within " + getMethodName());
        }
    }

    @Then("^the \"([^\"]*)\" should be assigned to the case$")
    public void assertTopicIsAssignedToTheCase(String topic) {
        String thisText = topics.assignedTopic.getText();
        System.out.println(thisText);
        topics.assertElementTextNotValue(topics.assignedTopic, topic);
    }
}
