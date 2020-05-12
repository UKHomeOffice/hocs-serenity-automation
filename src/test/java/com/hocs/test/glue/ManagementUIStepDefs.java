package com.hocs.test.glue;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.dcu.fetchExistingDCUCases;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.LoginPage;
import com.hocs.test.pages.managementUI.AddChildTopic;
import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.managementUI.LinkTopicToTeam;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.managementUI.StandardLine;
import config.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ManagementUIStepDefs extends BasePage {

    Homepage homepage;

    GenericInputStepDefs genericInputStepDefs;

    LoginPage loginPage;

    fetchExistingDCUCases fetchExistingDCUCases;

    Markup markupDecision;

    Markup_AddTopics markupAddTopics;

    Dashboard dashboard;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    AddChildTopic addChildTopic;

    StandardLine standardLine;

    LinkTopicToTeam linkTopicToTeam;

    @When("I navigate to the {string} Management page")
    public void navigateToSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "STANDARD LINE":
                safeClickOn(dashboard.addStandardLineButton);
                break;
            case "TEAM":
                safeClickOn(dashboard.addRemoveUsersButton);
                break;
            case "ADD CHILD TOPIC":
                safeClickOn(dashboard.addChildTopicButton);
                break;
            case "ADD A UNIT":
                safeClickOn(dashboard.addUnitButton);
                break;
            case "VIEW UNITS":
                safeClickOn(dashboard.viewUnitsButton);
                break;
            case "LINK TOPIC TO TEAM":
                safeClickOn(dashboard.linkTopicToTeamButton);
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @Then("I should be taken to the {string} Management page")
    public void assertThatTheUserIsTakenToTheSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "STANDARD LINE":
                standardLine.assertAddStandardLinePageTitle();
                break;
            case "TEAM":
                teamManagement.assertTeamManagementPageTitle();
                break;
            case "ADD A UNIT":
                unitManagement.assertAddUnitPageTitle();
                break;
            case "VIEW UNITS":
                unitManagement.assertViewUnitPageTitle();
                break;
            case "ADD CHILD TOPIC":
                addChildTopic.assertAddChildTopicPageTitle();
                break;
            case "LINK TOPIC TO TEAM":
                linkTopicToTeam.assertLinkTopicToTeamPageTitle();
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @When("I select the {string} team from the dropdown")
    public void selectTeamFromDropdown(String teamName) {
        teamManagement.selectATeam(teamName);
    }

    @And("I add the user {string} to the team")
    public void addUserToSelectedTeam(String user) {
        waitABit(500);
        teamManagement.assertTeamName();
        teamManagement.selectAUser(Users.valueOf(user));
    }

    @Then("the user should be visible in the team list")
    public void assertThatUserIsVisibleInTeamList() {
        teamManagement.assertThatUserIsVisibleInTeamList();
    }

    @And("I remove the user {string} from the team")
    public void removeUserFromTeamAndStoreUserName(String user) {
        teamManagement.clearTeamMember(user);
        setSessionVariable("user").to(user);
    }

    @Then("that user should no longer appear in the list of team members")
    public void assertThatUserHasBeenRemovedFromTeam() {
        teamManagement.assertThatRemovedUserIsNoLongerVisibleInList();
    }

    @When("I search for a team with no assigned users")
    public void navigateToTeamWithNoAssignedUsers() {
        teamManagement.selectTeamWithNoUsers();
    }

    @Then("no users should be shown in user list")
    public void assertTeamHasNoUsers() {
        teamManagement.assertThatTeamContainsNoUsers();
    }

    @And("I attempt to remove the user {string}")
    public void attemptRemoveUserFromTeamWithAssignedCases(String user) {
        teamManagement.removeUserFromTeamWithAssignedCases(Users.valueOf(user).getUsername());
    }

    @Then("an error message should be displayed as they have cases assigned in that team")
    public void assertThatCasesAssignedErrorMessageIsDisplayed() {
        teamManagement.assertUserHasCasesErrorMessage();
    }

    @Then("an error message should displayed as no team been selected")
    public void assertThatSelectTeamErrorMessageIsDisplayed() {
        teamManagement.assertSelectATeamErrorMessage();
    }

    @Then("an error message should be displayed as they have not entered a display name and short code")
    public void assertThatDisplayNameAndShortCodeErrorMessagesAreDisplayed() {
        unitManagement.assertDisplayNameAndShortCodeErrorMessages();
    }

    @And("I add the users {string} and {string} to the team")
    public void addTwoUsersToSelectedTeam(String firstUser, String secondUser) {
        waitABit(500);
        teamManagement.assertTeamName();
        teamManagement.clearTeamMember(firstUser);
        teamManagement.clearTeamMember(secondUser);
        setSessionVariable("firstUser").to(Users.valueOf(firstUser));
        teamManagement.selectAUser(Users.EAMON);
        setSessionVariable("secondUser").to(Users.valueOf(secondUser));
        teamManagement.selectAUser(Users.CASEY);
    }

    @Then("the users should be visible in the team list")
    public void assertThatUsersAreBothVisibleInTeamList() {
        teamManagement.assertMultipleUsersAddedToTeam();
    }

    @And("I click the Add Selected Users button")
    public void clickTheAddSelectedUsersButtonWithoutSelectingUser() {
        teamManagement.clickAddSelectedUsers();
    }

    @Then("an error message should be displayed as no users have been selected")
    public void assertThatUserShouldBeDisplayedErrorMessageIsDisplayed() {
        teamManagement.assertSelectSomeUsersErrorMessage();
    }

    @Then("an error message should be displayed as all Standard Line information has not been added")
    public void assertThatAllStandardLineErrorMessagesAreDisplayed() {
        standardLine.assertStandardLineIsRequiredErrorMessage();
        standardLine.assertExpiryDateIsRequiredErrorMessage();
        standardLine.assertTopicIsRequiredErrorMessage();
    }

    @Then("the Standard Line should be added to the selected topic")
    public void assertThatStandardLineHasBeenAdded() {
        standardLine.assertStandardLineSuccessMessage();
    }

    @And("I select a topic that {string} have linked teams")
    public void iSelectATopicThatHaveLinkedTeams(String topicState) {
        switch (topicState.toUpperCase()) {
            case "DOES":
                linkTopicToTeam.selectATopic("Register of faith leaders");
                break;
            case "DOES NOT":
                linkTopicToTeam.selectATopic(sessionVariableCalled("newChildTopic").toString());
                break;
            default:
                pendingStep(topicState + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as no topic has been selected")
    public void anErrorMessageShouldBeDisplayedAsNoTopicHasBeenSelected() {
        linkTopicToTeam.assertTopicIsRequiredErrorMessage();
    }

    @Given("I have created a new child topic")
    public void iHaveCreatedANewChildTopic() {
        navigateToSelectedManagementPage("ADD CHILD TOPIC");
        addChildTopic.selectAParentTopic("Police Website");
        addChildTopic.inputNewChildTopic();
    }

    @And("I select a {string} team")
    public void iSelectATeam(String typeOfTeam) {
        switch (typeOfTeam.toUpperCase()) {
            case "INITIAL DRAFT AND QA RESPONSE STAGES":
                linkTopicToTeam.selectADraftAndQATeam("Advice Team");
                break;
            case "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES":
                linkTopicToTeam.selectAPrivateAndMinisterTeam("Permanent Secretary");
                break;
            default:
                pendingStep(typeOfTeam + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as no {string} team has been selected")
    public void anErrorMessageShouldBeDisplayedAsNoTeamHasBeenSelected(String typeOfTeam) {
        switch (typeOfTeam.toUpperCase()) {
            case "INITIAL DRAFT AND QA RESPONSE STAGES":
                linkTopicToTeam.assertDraftandQATeamIsRequiredErrorMessage();
                break;
            case "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES":
                linkTopicToTeam.assertPrivateAndMinisterTeamIsRequiredErrorMessage();
                break;
            default:
                pendingStep(typeOfTeam + " is not defined within " + getMethodName());
        }

    }

    @Then("the summary should correctly detail the topic and the teams chosen to link to it")
    public void theSummaryShouldCorrectlyDetailTheTopicAndTheTeamsChosenToLinkToIt() {
        linkTopicToTeam.assertSummaryDisplaysSelectedTopicAndTeams();
    }

    @Given("I have linked teams to the new child topic")
    public void iHaveLinkedTeamsToTheNewChildTopic() {
        navigateToSelectedManagementPage("LINK TOPIC TO TEAM");
        iSelectATopicThatHaveLinkedTeams("DOES NOT");
        clickTheButton("Submit");
        iSelectATeam("INITIAL DRAFT AND QA RESPONSE STAGES");
        iSelectATeam("PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES");
        clickTheButton("Submit");
        clickTheButton("Submit");
    }

    @And("I navigate to {string}")
    public void iNavigateTo(String site) {
        switch (site.toUpperCase()) {
            case "HOCS":
                loginPage.navigateToHocs();
                break;
            case "MANAGEMENT UI":
                loginPage.navigateToManagementUI();
                break;
            default:
                pendingStep(site + " is not defined within " + getMethodName());
        }

    }


    @And("I discover the current default team links for a topic")
    public void iDiscoverTheCurrentDefaultTeamLinksForATopic() {
        fetchExistingDCUCases.giveMeACase("MIN", "MARKUP");
        markupDecision.getToMarkupAddATopicScreenPrerequisites();
        markupAddTopics.enterATopicWithoutHittingFinish("Register of faith leaders");
        markupAddTopics.getCurrentDefaultTeamsForTopic();
    }

    @And("I select to amend the team links for the topic")
    public void iSelectToAmendTheTeamLinksForTheTopic() {
        navigateToSelectedManagementPage("LINK TOPIC TO TEAM");
        iSelectATopicThatHaveLinkedTeams("DOES");
        clickTheButton("Submit");
    }

    @And("I select a different {string} team")
    public void iSelectADifferentTeam(String typeOfTeam) {
        switch (typeOfTeam.toUpperCase()) {
            case "INITIAL DRAFT AND QA RESPONSE STAGES":
                switch (sessionVariableCalled("defaultDraftTeam").toString()) {
                    case "Advice Team":
                        linkTopicToTeam.selectADraftAndQATeam("North Region");
                        break;
                    case "North Region":
                        linkTopicToTeam.selectADraftAndQATeam("Advice Team");
                        break;
                    default:
                        pendingStep(sessionVariableCalled("defaultDraftTeam").toString() + " is not defined within "
                                + getMethodName());
                }
                break;
            case "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES":
                switch (sessionVariableCalled("defaultPrivateOfficeTeam").toString()) {
                    case "Minister for Lords":
                        linkTopicToTeam.selectAPrivateAndMinisterTeam("Permanent Secretary");
                        break;
                    case "Permanent Secretary":
                        linkTopicToTeam.selectAPrivateAndMinisterTeam("Minister for Lords");
                        break;
                    default:
                        pendingStep(sessionVariableCalled("defaultPrivateOfficeTeam") + " is not defined within "
                                + getMethodName());
                }
                break;
            default:
                pendingStep(typeOfTeam + " is not defined within " + getMethodName());
        }
    }

    @When("I check the default team links in HOCS again")
    public void iCheckTheDefaultTeamLinksInHOCSAgain() {
        iNavigateTo("HOCS");
        fetchExistingDCUCases.giveMeACase("MIN", "MARKUP");
        markupDecision.getToMarkupAddATopicScreenPrerequisites();
        markupAddTopics.enterATopic("Register of faith leaders");
    }

    @When("I enter a display name")
    public void iEnterADisplayName() {
        addChildTopic.inputNewChildTopic();
    }

    @When("I select a parent topic")
    public void iSelectAParentTopic() {
        addChildTopic.selectAParentTopic("Specific Cases");
    }

    @Then("I am returned to the dashboard screen")
    public void iAmReturnedToTheDashboardScreen() {
        dashboard.assertElementIsDisplayed(dashboard.subheading);
    }

    @Then("an error message should be displayed as no parent topic has been selected")
    public void anErrorMessageShouldBeDisplayedAsNoParentTopicHasBeenSelected() {
        addChildTopic.assertParentTopicIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as no display name has been entered")
    public void anErrorMessageShouldBeDisplayedAsNoDisplayNameHasBeenEntered() {
        addChildTopic.assertDisplayNameIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed stating that topic already exists")
    public void anErrorMessageShouldBeDisplayedStatingThatTopicAlreadyExists() {
        addChildTopic.assertDuplicateTopicErrorMessage();
    }

    @And("I enter a parent topic and display name that duplicate an existing child topic")
    public void iEnterAParentTopicAndDisplayNameThatDuplicateAnExistingChildTopic() {
        addChildTopic.selectAParentTopic("Biometrics");
        addChildTopic.inputAChildTopicDisplayName("TEST TOPIC");
    }

    @And("I select a different parent topic")
    public void iSelectADifferentParentTopic() {
        addChildTopic.selectAParentTopic("Biometrics");
    }

    @And("I enter the same display name")
    public void iEnterTheSameDisplayName() {
        addChildTopic.inputAChildTopicDisplayName(sessionVariableCalled("newChildTopic").toString());
    }

    @And("I get a case and progress to the point of adding a topic")
    public void iCreateACaseAndProgressToThePointOfAddingATopic() {
        fetchExistingDCUCases.giveMeACase("MIN", "MARKUP");
        markupDecision.getToMarkupAddATopicScreenPrerequisites();
    }

    @Then("an error message should be displayed as the topic was not recognised as a valid topic")
    public void anErrorMessageShouldBeDisplayedAsTheTopicWasNotRecognisedAsAValidTopic() {
        markupDecision.assertTopicIsRequiredErrorMessage();
    }

    @And("a success message is displayed")
    public void aSuccessMessageIsDisplayed() {
        dashboard.assertSuccessMessageDisplayed();
    }

    @When("I add a new Standard Line")
    public void userAddsANewStandardLine() {
        standardLine.enterStandardLineTopic();
        standardLine.addStandardLineDocument();
        standardLine.enterStandardLineExpirationDate();
        safeClickOn(unitManagement.submitButton);
    }

    @When("I enter a Standard Line expiration date in the past")
    public void enterPastStandardLineExpirationDate() {
        standardLine.enterStandardLineTopic();
        standardLine.addStandardLineDocument();
        standardLine.enterPastStandardLineExpirationDate();
        safeClickOn(unitManagement.submitButton);
    }

    @Then("an error message should be displayed as the expiration date must be in the future")
    public void assertThatExpirationDateMustBeInFutureErrorMessageIsDisplayed() {
        standardLine.assertDateMustBeInFutureErrorMessage();
    }

    @And("I enter a {string} Display Name")
    public void iEnterADisplayName(String displayNameType) {
        switch (displayNameType.toUpperCase()) {
            case "NEW":
                unitManagement.inputNewUnitDisplayName();
                break;
            case "DUPLICATE":
                unitManagement.inputUnitDisplayName(sessionVariableCalled("unitDisplayName"));
                break;
            default:
                pendingStep(displayNameType + " is not defined within " + getMethodName());
        }
    }

    @And("I enter a {string} Short Code")
    public void iEnterAShortCode(String shortCodeType) {
        switch (shortCodeType.toUpperCase()) {
            case "NEW":
                unitManagement.inputNewUnitShortCode();
                break;
            case "DUPLICATE":
                unitManagement.inputUnitShortCode(sessionVariableCalled("unitShortCode"));
                break;
            default:
                pendingStep(shortCodeType + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed a unit with those details already exists")
    public void anErrorMessageShouldBeDisplayedAUnitWithThoseDetailsAlreadyExists() {
        unitManagement.assertUnitAlreadyExistsErrorMessage();
    }

    @Then("a list of units should be displayed")
    public void aListOfUnitsShouldBeDisplayed() {
        unitManagement.assertListOfUnitsVisible();
    }

    @Then("the previously created unit should be listed")
    public void thePreviouslyCreatedUnitShouldBeListed() {
        unitManagement.assertListContainsCreatedUnit();
    }

}

