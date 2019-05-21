package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.ReferToOGD;
import com.hocs.test.pages.markup.NoResponseNeeded;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.draft.Draft;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;

public class MarkUpStepDefs {

    Homepage homepage;

    Page page;

    Topics topics;

    Workstacks workstacks;

    MarkUpDecision markUpDecision;

    ReferToOGD refer;

    NoResponseNeeded noResponseNeeded;

    SuccessfulCaseCreation successfulCaseCreation;

    QAResponse qaResponse;

    DraftingTeamDecision draftingTeamDecision;

    @When("^I complete the markup stage$")
    public void completeTheMarkupStage() {
        page.clickOn(homepage.centralDraftingTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(markUpDecision.policyResponseRadioButton);
        page.clickOn(markUpDecision.continueButton);
        page.clickOn(topics.addTopicButton);
        topics.enterRealTopic();
        page.sleep(1000);
        page.clickOn(markUpDecision.addButton);
        page.sleep(1000);
        page.clickOn(markUpDecision.continueButton);
        page.sleep(1000);
        page.clickOn(markUpDecision.finishButton);
    }

    @When("^I assign the Topic \"([^\"]*)\"$")
    public void enterSpecificMarkupTopic(String topic) {
        page.clickOn(homepage.centralDraftingTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(markUpDecision.policyResponseRadioButton);
        page.clickOn(markUpDecision.continueButton);
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
                pendingStep(topic + " is not defined within " + getMethodName());
        }

    }

    @When("^I override the \"([^\"]*)\" team to \"([^\"]*)\"$")
    public void overrideTheDefaultTeam(String defaultTeam, String overrideTeam) {
        switch (defaultTeam.toUpperCase()) {
            case "INITIAL DRAFT" :
                topics.selectOverrideInitialDraftTeamByVisibleText(overrideTeam);
                page.clickOn(page.finishButton);
                break;
            case "PRIVATE OFFICE" :
                topics.selectOverridePrivateOfficeTeamByVisibleText(overrideTeam);
                setSessionVariable("draftTeam").to(topics.autoAssignedDraftTeam.getValue());
                page.clickOn(page.finishButton);
                draftingTeamDecision.initialDraftFullFlow();
                qaResponse.qaResponseFullFlow();
                break;
            default:
                pendingStep(defaultTeam + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the continue button on the markup response screen$")
    public void clickContinueButtonOnMarkupResponseScreen() {
        page.clickOn(markUpDecision.continueButton);
    }

    @When("^I click the continue button on the add a topic screen$")
    public void clickContinueButtonOnAddATopicScreen() {
        page.clickOn(markUpDecision.policyResponseRadioButton);
        page.clickOn(markUpDecision.continueButton);
        markUpDecision.sleep(500);
        page.clickOn(markUpDecision.continueButton);
    }

    @When("^I click the add button on the add topic screen")
    public void clickAddButtonOnAddTopicScreen() {
        page.clickOn(markUpDecision.policyResponseRadioButton);
        page.clickOn(markUpDecision.continueButton);
        page.clickOn(markUpDecision.addATopicButton);
        page.clickOn(markUpDecision.addButton);
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
                pendingStep(ordinal + " is not defined within " + getMethodName());
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
                pendingStep(draftingTeam + " is not defined within " + getMethodName());
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
            default:
                pendingStep(privateOfficeTeam + " is not defined within " + getMethodName());
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
                page.clickOn(homepage.publicProtectionUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "WINDRUSH COMPENSATION TEAM" :
                //homepage.selectWindrusCompensationTeam();
                //workstacks.assertCaseReferenceIsVisible();
                break;
            case "POLICE STRATEGY & REFORM UNIT" :
                break;
            case "ANIMALS IN SCIENCE REGULATION UNIT" :
                page.clickOn(homepage.animalsInScienceTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "POLICE WORKFORCE AND PROFESSIONALISM UNIT" :
                page.clickOn(homepage.policeWorkforceProfessionalismUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER FOR LORDS" :
                page.clickOn(homepage.ministerForLordsTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "EXTREMISM ANALYSIS UNIT" :
                page.clickOn(homepage.extremismAnalysisUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "COUNTER EXTREMISM UNIT" :
                page.clickOn(homepage.counterExtremismUnit);
                workstacks.assertCaseReferenceIsVisible();
            case "UNDER SECRETARY OF STATE FOR CRIME, SAFEGUARDING AND VULNERABILITY" :
                //homepage.selectUnderSecretaryStateCrimeSafeguarding();
                //workstacks.assertCaseReferenceIsVisible();
                break;
            case "COUNTER-TERRORISM LEGISLATION AND INVESTIGATORY POWERS UNIT" :
                page.clickOn(homepage.counterTerrorismLegislationInvestigatoryPowersUnit);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "PRESS OFFICE" :
                page.clickOn(homepage.pressOffice);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "FINANCE" :
                page.clickOn(homepage.financeTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "CHEMICAL, BIOLOGICAL, RADIOLOGICAL, NUCLEAR & EXPLOSIVES" :
                page.clickOn(homepage.chemBioRadioNuclearExplosives);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR IMMIGRATION" :
                page.clickOn(homepage.ministerOfStateForImmigrationTeam);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR SECURITY AND ECONOMIC CRIME" :
                page.clickOn(homepage.ministerOfStateForSecurityAndEconomicCrime);
                workstacks.assertCaseReferenceIsVisible();
                break;
            case "MINISTER OF STATE FOR POLICING AND FIRE SERVICE" :
                page.clickOn(homepage.ministerOfStateForPolicingAndFireServiceTeam);
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

    @Then("^the Other Government Department name free text field is displayed$")
    public void assertOtherGvmtDepTBIsDisplayed() {
        refer.assertOGDTitleTextBoxIsDisplayed();
    }

    @Then("^the No Response Needed casenote field is displayed$")
    public void assertNoResponseNeededTextBox() {
        noResponseNeeded.assertNRNTextBoxIsDisplayed();
    }

}
