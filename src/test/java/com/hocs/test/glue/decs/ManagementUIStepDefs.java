package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.managementUI.WithdrawACase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.LoginPage;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.managementUI.AddChildTopic;
import com.hocs.test.pages.managementUI.LinkTopicToTeam;
import com.hocs.test.pages.managementUI.ListsManagement;
import com.hocs.test.pages.managementUI.MUIDashboard;
import com.hocs.test.pages.managementUI.StandardLine;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import com.hocs.test.pages.managementUI.UserManagement;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManagementUIStepDefs extends BasePage {

    LoginPage loginPage;

    CreateCase createCase;

    DCUProgressCase dcuProgressCase;

    Markup markup;

    MUIDashboard MUIDashboard;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    AddChildTopic addChildTopic;

    StandardLine standardLine;

    LinkTopicToTeam linkTopicToTeam;

    UserManagement userManagement;

    ListsManagement listsManagement;

    Dashboard dashboard;

    WithdrawACase withdrawACase;

    @When("I navigate to the {string} Management page")
    public void navigateToSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "ADD A STANDARD LINE":
                safeClickOn(MUIDashboard.addStandardLineButton);
                break;
            case "MANAGE STANDARD LINES":
                safeClickOn(MUIDashboard.manageStandardLinesHypertext);
                break;
            case "TEAM":
                safeClickOn(MUIDashboard.manageATeamButton);
                break;
            case "CREATE DCU DRAFTING TEAM":
                safeClickOn(MUIDashboard.createDCUDraftingTeamHypertext);
                break;
            case "ADD CHILD TOPIC":
                safeClickOn(MUIDashboard.addChildTopicButton);
                break;
            case "ADD A UNIT":
                safeClickOn(MUIDashboard.addUnitButton);
                break;
            case "VIEW UNITS":
                safeClickOn(MUIDashboard.viewUnitsButton);
                break;
            case "LINK TOPIC TO TEAM":
                safeClickOn(MUIDashboard.linkTopicToTeamButton);
                break;
            case "USER MANAGEMENT":
                safeClickOn(MUIDashboard.userManagementHypertext);
                break;
            case "CAMPAIGN MANAGEMENT":
                safeClickOn(MUIDashboard.manageMPAMCampaignsHypertext);
                break;
            case "WITHDRAW A CASE":
                safeClickOn(MUIDashboard.withdrawACaseHypertext);
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @Then("I should be taken to the {string} Management page")
    public void assertThatTheUserIsTakenToTheSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "ADD A STANDARD LINE":
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
        waitABit(1000);
        teamManagement.assertTeamName();
        teamManagement.addTeamMember(User.valueOf(user));
    }

    @And("I remove the user {string} from the team")
    public void removeUserFromTeamAndStoreUserName(String user) {
        teamManagement.removeTeamMember(User.valueOf(user));
    }

    @Then("the user {string} be visible in the team list")
    public void assertUserVisiblityInTeamList(String shouldShouldNot) {
        switch (shouldShouldNot.toUpperCase()) {
            case "SHOULD":
                teamManagement.assertThatUserVisibleInTeamListIs(true);
                break;
            case "SHOULD NOT":
                teamManagement.assertThatUserVisibleInTeamListIs(false);
                break;
            default:
                pendingStep(shouldShouldNot + " is not defined within " + getMethodName());
        }
    }

    @When("I search for a team with no assigned users")
    public void navigateToTeamWithNoAssignedUsers() {
        teamManagement.selectATeam("Data & Identity Unit");
    }

    @Then("no users should be shown in user list")
    public void assertTeamHasNoUsers() {
        teamManagement.assertThatTeamContainsNoUsers();
    }

    @And("I attempt to remove the user {string}")
    public void attemptRemoveUserFromTeamWithAssignedCases(String user) {
        teamManagement.removeUserFromTeamWithAssignedCases(User.valueOf(user).getAllocationText());
    }

    @Then("an error message should be displayed as they have cases/claims assigned in that team")
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
        waitABit(1000);
        teamManagement.assertTeamName();
        teamManagement.clearTeamMember(firstUser);
        teamManagement.clearTeamMember(secondUser);
        setSessionVariable("firstUser").to(User.valueOf(firstUser));
        teamManagement.selectAUser(User.CAMERON);
        setSessionVariable("secondUser").to(User.valueOf(secondUser));
        teamManagement.selectAUser(User.CASEY);
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
                linkTopicToTeam.selectATopic("101 non-emergency number (cost)");
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
                linkTopicToTeam.selectADraftAndQATeam("Animals in Science Regulation Unit");
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

    @And("I discover the current default team links for a topic")
    public void iDiscoverTheCurrentDefaultTeamLinksForATopic() {
        createCase.createCSCaseOfType("MIN");
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromDataInputToMarkup();
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        waitABit(1000);
        markup.addTopicToCase("101 non-emergency number (cost)");
        markup.confirmPrimaryTopic();
        markup.recordDefaultTeamsForTopic();
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
                    case "Animals in Science Regulation Unit":
                        linkTopicToTeam.selectADraftAndQATeam("North Region");
                        break;
                    case "North Region":
                        linkTopicToTeam.selectADraftAndQATeam("Animals in Science Regulation Unit");
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

    @When("I check the default team links in CS again")
    public void iCheckTheDefaultTeamLinksInCSAgain() {
        loginPage.open();
        createCase.createCSCaseOfType("MIN");
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromDataInputToMarkup();
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        waitABit(1000);
        markup.addTopicToCase("101 non-emergency number (cost)");
        markup.confirmPrimaryTopic();
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
        MUIDashboard.assertElementIsDisplayed(MUIDashboard.subheading);
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

    @And("I progress the case to the point of adding a topic")
    public void iCreateACaseAndProgressToThePointOfAddingATopic() {
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
safeClickOn(continueButton);
waitABit(1000);
    }

    @And("a success message is displayed")
    public void aSuccessMessageIsDisplayed() {
        MUIDashboard.assertSuccessMessageDisplayed();
    }

    @When("I add a new Standard Line with {string} as the topic")
    public void userAddsANewStandardLine(String topic) {
        standardLine.enterStandardLineTopic(topic);
        standardLine.addStandardLineDocument();
        standardLine.enterStandardLineExpirationDate();
        safeClickOn(unitManagement.submitButton);
    }

    @When("I enter a Standard Line expiration date in the past")
    public void enterPastStandardLineExpirationDate() {
        standardLine.enterStandardLineTopic("Animal alternatives (3Rs)");
        standardLine.addStandardLineDocument();
        standardLine.enterPastStandardLineExpirationDate();
        safeClickOn(unitManagement.submitButton);
    }

    @And("I select the {string} action for the {string} standard line")
    public void iSelectTheActionForTheStandardLine(String action, String topic) {
        standardLine.selectActionForStandardLine(topic, action);
    }

    @And("I amend the expiry date of the {string} standard line to {int} days from today")
    public void iAmendTheExpiryDateOfTheStandardLineTo(String topic, Integer days) {
        standardLine.amendAStandardLine(topic, days);
    }

    @And("I enter {string} into the standard line filter")
    public void iEnterIntoStandardLineFilter(String input) {
        standardLine.enterIntoStandardLineFilter(input);
    }

    @And("I select the checkbox to include expired standard lines")
    public void iSelectTheCheckboxToIncludeExpiredStandardLines() {
        standardLine.toggleExpiredStandardLinesCheckbox();
    }

    @And("the standard line {string} visible")
    public void theStandardLineVisible(String input) {
        standardLine.assertCreatedStandardLineDisplayed(input);
    }

    @Then("the standard lines in the {string} column should contain {string}")
    public void theStandardLinesShouldBeFilteredBy(String column, String contents) {
        standardLine.assertStandardLinesAreFilteredBy(column, contents);
    }

    @Then("an error message should be displayed as the expiration date must be in the future")
    public void assertThatExpirationDateMustBeInFutureErrorMessageIsDisplayed() {
        standardLine.assertDateMustBeInFutureErrorMessage();
    }

    @Then("the standard line expiry date has been correctly amended")
    public void theStandardLineExpiryDateHasBeenCorrectlyAmended() {
        standardLine.assertStandardLineExpiryDateIsUpdated();
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

    @And("I load the teams of which {string} is a member")
    public void loadTeamsWithMember(String member) {
        userManagement.searchForAUsersTeams(member);
    }

    @And("I add the user to the {string} team")
    public void addUserToTeam(String team) {
        userManagement.addUserToTeams(team);
    }

    @And("I remove the user from the {string} team")
    public void iRemoveUserFromTeam(String team) {
        userManagement.removeTeam(team);
    }

    @Then("the teams the user is a part of are displayed")
    public void teamsUserIsInAreDisplayed() {
        userManagement.assertCorrectUserTeamsAreDisplayed();
    }

    @Then("the team should be visible in the users list of teams")
    public void teamIsAddedToListOfTeams() {
        userManagement.assertNewTeamIsAddedToUserTeams();
    }

    @Then("the success ribbon should be displayed once the user is added")
    public void successRibbonIsDisplayedUponsAddition() {
        userManagement.assertSuccessfulAdditionToTeamBanner();
    }

    @Then("the team should be removed from the users list of teams")
    public void teamShouldBeRemovedFromListOfTeams() {
        userManagement.assertTeamHasBeenRemoved();
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

    @And("I add a Campaign with random name and campaign code")
    public void addACampaignWithNameAndCode() {
        listsManagement.addANewCampaign();
    }

    @Then("the new Campaign has been added to the list of Campaigns")
    public void newCampaignHasBeenAddedToListOfCampaigns() {
        listsManagement.assertCampaignAddedToCampaignTable();
    }

    @And("I edit a Campaign name")
    public void editCampaignNameFrom() {
        listsManagement.amendACampaign();
    }

    @Then("the Campaign name should have changed in the list of Campaigns")
    public void campaignNameShouldHaveChangedInTheList() {
        listsManagement.assertCampaignAddedToCampaignTable();
    }

    @And("I click the view team button")
    public void iClickTheViewTeamButton() {
        safeClickOn(teamManagement.viewTeamButton);
    }

    @And("I create a new DCU drafting team")
    public void iCreateANewDCUDraftingTeam() {
        teamManagement.createDraftingTeamWithRandomName();
    }

    @And("I edit the name of the created DCU drafting team")
    public void iEditTheNameOfTheNewlyCreatedDCUDraftingTeam() {
        teamManagement.editNewDCUDraftingTeamName();
    }

    @And("I load the {string} DCU Drafting team through team management")
    public void iLoadTheNewlyDCUDraftingTeamThroughTeamManagement(String action) {
        switch (action.toUpperCase()) {
            case "CREATED":
                teamManagement.selectATeam(sessionVariableCalled("draftingTeamName"));
                break;
            case "RENAMED":
                teamManagement.selectATeam(sessionVariableCalled("newDraftingTeamName"));
                break;
            case "DEACTIVATED":
                teamManagement.selectATeam(sessionVariableCalled("deactivatedTeamName"));
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} DCU Drafting team is displayed")
    public void theNewlyCreatedDCUDraftingTeamIsDisplayed(String action) {
        if (action.equalsIgnoreCase("CREATED")) {
            teamManagement.assertNewTeamIsDisplayed();
        } else if (action.equalsIgnoreCase("RENAMED")) {
            teamManagement.assertRenamedTeamIsDisplayed();
        }
    }

    @Then("the success message for team {string} should be displayed")
    public void theSuccessMessageForTeamShouldBeDisplayed(String action) {
        switch (action.toUpperCase()) {
            case "CREATION":
                teamManagement.assertSuccessMessageOfTeamCreation();
                break;
            case "RENAME":
                teamManagement.assertSuccessMessageOfTeamRename();
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    @And("I navigate to edit team")
    public void iNavigateToEditTeam() {
        unitManagement.clickEditTeamButton();
    }

    @And("I change to a different unit")
    public void iChangeToADifferentUnit() throws InterruptedException {
        unitManagement.selectNewUnitName();
    }

    @Then("success message is displayed")
    public void successMessageIsDisplayed() {
        unitManagement.assertCorrectSuccessMessageDisplayed();
    }

    @And("I enter the (cases )reference, a valid withdrawal date and text into the note field")
    public void iEnterTheCasesReferenceAValidWithdrawalDateAndTextIntoTheNoteField() {
        withdrawACase.enterCaseReference(getCurrentCaseReference());
        withdrawACase.enterWithdrawalDate();
        withdrawACase.enterWithdrawalNotes("Test withdrawal notes");
    }

    @And("I {string} the team in team management")
    public void iTheTeamInTeamManagement(String action) {
        if (action.equalsIgnoreCase("Deactivate")) {
            teamManagement.deactivateTeam();
        } else if (action.equalsIgnoreCase("Reactivate")) {
            teamManagement.reactivateTeam();
        }
    }

    @And("I select to include deactivated teams in teams typeahead")
    public void iSelectToIncludeDeactivatedTeamsInTeamsTypeahead() {
        teamManagement.selectShowDeactivatedTeamCheckbox();
    }

    @Then("the team should be displayed as {string} in team management")
    public void theTeamShouldBeDisplayedAsInTeamManagement(String status) {
        teamManagement.assertActiveStatusOfTeam(status);
    }

    @Then("a message should be displayed stating that the team has been successfully {string}")
    public void aMessageShouldBeDisplayedStatingThatTheTeamHasBeenSuccessfully(String message) {
        if (message.equalsIgnoreCase("Deactivated")) {
            teamManagement.assertSuccessMessageOfDeactivation();
        } else if (message.equalsIgnoreCase("Reactivated")) {
            teamManagement.assertSuccessMessageOfReactivation();
        }
    }

    @Then("the deactivated team should be displayed")
    public void theDeactivatedTeamShouldBeDisplayed() {
        teamManagement.assertDeactivatedTeamIsDisplayed();
    }
}

