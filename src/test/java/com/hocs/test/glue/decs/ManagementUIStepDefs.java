package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.managementUI.MUI;
import com.hocs.test.pages.managementUI.TemplateManagement;
import com.hocs.test.pages.managementUI.WithdrawACase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.LoginPage;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.managementUI.TopicManagement;
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

    MUI mui;

    MUIDashboard muiDashboard;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    TopicManagement topicManagement;

    StandardLine standardLine;

    LinkTopicToTeam linkTopicToTeam;

    UserManagement userManagement;

    ListsManagement listsManagement;

    Dashboard dashboard;

    WithdrawACase withdrawACase;

    TemplateManagement templateManagement;

    @When("I select to {string}")
    public void iSelectAManagementUIDashboardLink(String linkText) {
        muiDashboard.selectDashboardLinkWithText(linkText);
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
        iSelectAManagementUIDashboardLink("Add child topic");
        topicManagement.selectAParentTopic("Police Website");
        topicManagement.inputNewChildTopic();
    }

    @And("I can create a child topic with the newly created parent topic linked")
    public void iCanCreateAChildTopicWithTheNewlyCreatedParentTopicLinked() {
        topicManagement.selectAParentTopic(sessionVariableCalled("newParentTopic"));
        topicManagement.inputNewChildTopic();
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
        iSelectAManagementUIDashboardLink("Link topic to team");
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
        iSelectAManagementUIDashboardLink("Link topic to team");
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
        topicManagement.inputNewChildTopic();
    }

    @When("I select a parent topic")
    public void iSelectAParentTopic() {
        topicManagement.selectAParentTopic("Specific Cases");
    }

    @Then("I am returned to the dashboard screen")
    public void iAmReturnedToTheDashboardScreen() {
        muiDashboard.assertElementIsDisplayed(muiDashboard.subheading);
    }

    @Then("an error message should be displayed as no parent topic has been selected")
    public void anErrorMessageShouldBeDisplayedAsNoParentTopicHasBeenSelected() {
        topicManagement.assertParentTopicIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as no display name has been entered")
    public void anErrorMessageShouldBeDisplayedAsNoDisplayNameHasBeenEntered() {
        topicManagement.assertDisplayNameIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed stating that topic already exists")
    public void anErrorMessageShouldBeDisplayedStatingThatTopicAlreadyExists() {
        topicManagement.assertDuplicateTopicErrorMessage();
    }

    @And("I enter a parent topic and display name that duplicate an existing child topic")
    public void iEnterAParentTopicAndDisplayNameThatDuplicateAnExistingChildTopic() {
        topicManagement.selectAParentTopic("Biometrics");
        topicManagement.inputAChildTopicDisplayName("TEST TOPIC");
    }

    @And("I select a different parent topic")
    public void iSelectADifferentParentTopic() {
        topicManagement.selectAParentTopic("Biometrics");
    }

    @And("I enter the same display name")
    public void iEnterTheSameDisplayName() {
        topicManagement.inputAChildTopicDisplayName(sessionVariableCalled("newChildTopic").toString());
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
        muiDashboard.assertSuccessMessageDisplayed();
    }

    @When("I add a new Standard Line with {string} as the topic")
    public void userAddsANewStandardLine(String topic) {
        if (standardLine.addNewStandardLineButton.isCurrentlyVisible()) {
            safeClickOn(standardLine.addNewStandardLineButton);
        }
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

    @Then("the new {string} campaign has been added to the list of campaigns")
    public void newCampaignHasBeenAddedToListOfCampaigns(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MPAM":
                muiDashboard.selectDashboardLinkWithText("Manage MPAM campaigns");
                break;
            case "TO":
                muiDashboard.selectDashboardLinkWithText("Manage Treat Official campaigns");
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        listsManagement.assertVisibilityOfCampaignInCampaignTable();
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
        withdrawACase.enterWithdrawalDate(getTodaysDate());
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

    @Then("the {string} management page should be displayed")
    public void theManagementPageShouldBeDisplayed(String pageTitle) {
        assertManagementUIPageTitle(pageTitle);
    }

    @And("I submit the details for the new user")
    public void iEnterTheDetailsForTheNewUser() {
        userManagement.enterNewUserDetails();
        clickTheButton("Submit");
    }

    @And("I create a new parent topic")
    public void iCreateANewParentTopic() {
        topicManagement.inputAParentTopicDisplayedName();
        clickTheButton("Submit");
    }

    @And("I load the templates for the {string} case type")
    public void iLoadTheTemplatesForTheCaseType(String caseType) {
        templateManagement.selectACaseType(caseType);
    }

    @And("I add a new template to the case type")
    public void iAddANewTemplateToTheCaseType() {
        templateManagement.addTemplate();
    }

    @And("I remove a template from the case type")
    public void iRemoveATemplateFromTheCaseType() {
        templateManagement.removeTemplate();
    }

    @Then("the new business unit is added to the list of business units")
    public void theNewBusinessUnitIsAddedToTheListOfBusinessUnits() {
        iSelectAManagementUIDashboardLink("Manage MPAM Business Units");
        listsManagement.selectASpecificBusinessArea(sessionVariableCalled("businessArea"));
        clickTheButton("Submit");
        listsManagement.assertVisibilityOfBusinessUnitInBusinessUnitTable();
    }

    @Then("the template should be removed from the case type")
    public void theTemplateShouldBeRemovedFromTheCaseType() {
        templateManagement.assertTemplateRemoval();
    }

    @Then("the template should be displayed in the list of available templates")
    public void theTemplateShouldBeDisplayedInTheListOfAvailableTemplates() {
        templateManagement.assertTemplateIsDisplayedInDECS();
    }


//    Lists Management

    @And("I select to add a new campaign")
    public void iSelectToAddANewCampaign() {
        listsManagement.clickTheAddNewCampaignButton();
    }

    @And("I submit details for the new campaign")
    public void addACampaignWithNameAndCode() {
        listsManagement.enterCampaignName();
        listsManagement.enterCampaignCode();
        clickTheButton("Submit");
    }

    @Then("the success message for adding a campaign should be displayed")
    public void theSuccessMessageForAddingACampaignShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingCampaignVisible();
    }

    @Then("I should be able to view the new/renamed {string} campaign in the table of campaigns")
    public void campaignNameShouldHaveChangedInTheList(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MPAM":
                muiDashboard.selectDashboardLinkWithText("Manage MPAM campaigns");
                break;
            case "TREAT OFFICIAL":
                muiDashboard.selectDashboardLinkWithText("Manage Treat Official campaigns");
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        listsManagement.assertVisibilityOfCampaignInCampaignTable();
    }

    @And("I have an existing {string} campaign I want to amend")
    public void iHaveAnExistingCampaignIWantToAmend(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MPAM":
                muiDashboard.selectDashboardLinkWithText("Manage MPAM campaigns");
                break;
            case "TREAT OFFICIAL":
                muiDashboard.selectDashboardLinkWithText("Manage Treat Official campaigns");
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        listsManagement.clickTheAddNewCampaignButton();
        listsManagement.enterCampaignName();
        listsManagement.enterCampaignCode();
        clickTheButton("Submit");
    }

    @And("I select to amend the campaign")
    public void iSelectToAmendTheCampaign() {
        listsManagement.clickAmendLinkFor(sessionVariableCalled("campaignName"));
    }

    @And("I submit a new name for the campaign")
    public void iSubmitANewNameForTheCampaign() {
        listsManagement.enterCampaignName();
        clickTheButton("Submit");
    }

    @Then("the success message for amending a campaign should be displayed")
    public void theSuccessMessageForAmendingACampaignShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingCampaignVisible();
    }

    @And("I have added a new {string} campaign in MUI")
    public void iHaveAddedANewCampaignInMUI(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MPAM":
                muiDashboard.selectDashboardLinkWithText("Manage MPAM campaigns");
                break;
            case "TREAT OFFICIAL":
                muiDashboard.selectDashboardLinkWithText("Manage Treat Official campaigns");
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        listsManagement.clickTheAddNewCampaignButton();
        listsManagement.enterCampaignName();
        listsManagement.enterCampaignCode();
        clickTheButton("Submit");
    }

    @And("I select to add a new representative")
    public void iSelectToAddANewRepresentative() {
        listsManagement.clickTheAddNewRepresentativeButton();
    }

    @And("I submit details for the new representative")
    public void iSubmitDetailsForTheNewRepresentative() {
        listsManagement.enterRepresentativeName();
        clickTheButton("Submit");
    }

    @Then("the success message for adding a representative should be displayed")
    public void theSuccessMessageForAddingARepresentativeShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingRepresentativeVisible();
    }

    @And("I should be able to view the new representative in the table of representatives")
    public void iShouldBeAbleToViewTheNewRepresentativeInTheTableOfRepresentatives() {
        iSelectAManagementUIDashboardLink("Manage Ex-Gratia Business Area Representatives");
        listsManagement.assertVisibilityOfRepresentativeInRepresentativeTable();
    }

    @When("I select to delete the representative")
    public void iSelectToDeleteTheRepresentative() {
        listsManagement.clickDeleteLinkFor(sessionVariableCalled("representativeName"));
    }

    @Then("the success message for deleting a representative should be displayed")
    public void theSuccessMessageForDeletingARepresentativeShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForDeletingRepresentativeVisible();
    }

    @And("I should not be able to view the deleted representative in the table of representatives")
    public void iShouldNotBeAbleToViewTheDeletedRepresentativeInTheTableOfRepresentatives() {
        iSelectAManagementUIDashboardLink("Manage Ex-Gratia Business Area Representatives");
        listsManagement.assertRepresentativeNotVisibleInRepresentativeTable();
    }

    @And("I select a Business Area to add a new Business Unit to")
    public void iSelectABusinessAreaToAddABusinessUnitTo() {
        listsManagement.selectABusinessArea();
        clickTheButton("Submit");
    }

    @And("I select to add a new Business Unit")
    public void iSelectToAddANewBusinessUnit() {
        listsManagement.clickTheAddNewBusinessUnitButton();
    }

    @And("I submit details for the new Business Unit")
    public void iSubmitDetailsForTheNewBusinessUnit() {
        listsManagement.enterBusinessUnitName();
        clickTheButton("Submit");
    }

    @Then("the success message for adding a Business Unit should be displayed")
    public void theSuccessMessageForAddingABusinessUnitShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingBusinessUnitVisible();
    }

    @And("I should be able to view the new/renamed Business Unit in the table of Business Units")
    public void iShouldBeAbleToViewTheNewBusinessUnitInTheTableOfBusinessUnits() {
        iSelectAManagementUIDashboardLink("Manage MPAM Business Units");
        listsManagement.selectASpecificBusinessArea(sessionVariableCalled("businessArea"));
        clickTheButton("Submit");
        listsManagement.assertVisibilityOfBusinessUnitInBusinessUnitTable();
    }

    @And("I have an existing Business Unit I want to amend")
    public void iHaveAnExistingBusinessUnitIWantToAmend() {
        iSelectAManagementUIDashboardLink("Manage MPAM Business Units");
        listsManagement.selectABusinessArea();
        clickTheButton("Submit");
        listsManagement.clickTheAddNewBusinessUnitButton();
        listsManagement.enterBusinessUnitName();
        clickTheButton("Submit");
    }

    @And("I select the correct Business Area")
    public void iSelectTheCorrectBusinessUnit() {
        listsManagement.selectASpecificBusinessArea(sessionVariableCalled("businessArea"));
        clickTheButton("Submit");
    }

    @And("I select to amend the Business Unit")
    public void iSelectToAmendTheBusinessUnit() {
        listsManagement.clickAmendLinkFor(sessionVariableCalled("businessUnitName"));
    }

    @And("I submit a new name for the Business Unit")
    public void iSubmitANewNameForTheBusinessUnit() {
        listsManagement.enterBusinessUnitName();
        clickTheButton("Submit");
    }

    @Then("the success message for amending a Business Unit should be displayed")
    public void theSuccessMessageForAmendingABusinessUnitShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingBusinessUnitVisible();
    }

    @And("I select a Enquiry Subject to add a new Enquiry Reason to")
    public void iSelectAEnquirySubjectToAddANewEnquiryReasonTo() {
        listsManagement.selectAnEnquirySubject();
        clickTheButton("Submit");
    }

    @And("I select to add a new {string} Enquiry Reason")
    public void iSelectToAddANewEnquiryReason(String caseType) {
        listsManagement.clickTheAddNewEnquiryReasonButton(caseType);
    }

    @And("I submit details for the new {string} Enquiry Reason")
    public void iSubmitDetailsForTheNewEnquiryReason(String caseType) {
        listsManagement.enterEnquiryReasonName();
        if (caseType.equalsIgnoreCase("MPAM")) {
            clickTheButton("Add");
        } else if (caseType.equalsIgnoreCase("COMP")) {
            listsManagement.enterEnquiryReasonCode();
            clickTheButton("Submit");
        }
    }

    @Then("the success message for adding an Enquiry Reason should be displayed")
    public void theSuccessMessageForAddingAnEnquiryReasonShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingEnquiryReasonVisible();
    }

    @And("I should be able to view the new/renamed {string} Enquiry Reason in the table of Enquiry Reasons")
    public void iShouldBeAbleToViewTheNewEnquiryReasonInTheTableOfEnquiryReasons(String caseType) {
        if (caseType.equalsIgnoreCase("MPAM")) {
            iSelectAManagementUIDashboardLink("Manage MPAM Enquiry Reasons");
            listsManagement.selectASpecificEnquirySubject(sessionVariableCalled("enquirySubject"));
            clickTheButton("Submit");
        } else if (caseType.equalsIgnoreCase("COMP")) {
            iSelectAManagementUIDashboardLink("Manage UKVI Complaint Enquiry Reasons");
        }
        listsManagement.assertVisibilityOfEnquiryReasonInEnquiryReasonTable();
    }

    @And("I have an existing Enquiry Reason I want to amend")
    public void iHaveAnExistingEnquiryReasonIWantToAmend() {
        iSelectAManagementUIDashboardLink("Manage MPAM Enquiry Reasons");
        listsManagement.selectAnEnquirySubject();
        clickTheButton("Submit");
        listsManagement.clickTheAddNewEnquiryReasonButton("MPAM");
        listsManagement.enterEnquiryReasonName();
        clickTheButton("Add");
    }

    @And("I select the correct Enquiry Subject")
    public void iSelectTheCorrectEnquirySubject() {
        listsManagement.selectASpecificEnquirySubject(sessionVariableCalled("enquirySubject"));
        clickTheButton("Submit");
    }

    @And("I select to amend the Enquiry Reason")
    public void iSelectToAmendTheEnquiryReason() {
        listsManagement.clickAmendLinkFor(sessionVariableCalled("enquiryReasonName"));
    }

    @And("I submit a new name for the {string} Enquiry Reason")
    public void iSubmitANewNameForTheEnquiryReason(String caseType) {
        listsManagement.enterEnquiryReasonName();
        if (caseType.equalsIgnoreCase("MPAM")) {
            clickTheButton("Amend");
        } else if (caseType.equalsIgnoreCase("COMP")) {
            clickTheButton("Submit");
        }
    }

    @Then("the success message for amending an Enquiry Reason should be displayed")
    public void theSuccessMessageForAmendingAnEnquiryReasonShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingEnquiryReasonVisible();
    }

    @And("I have added a new {string} Enquiry Reason in MUI")
    public void iHaveAddedANewEnquiryReasonInMUI(String caseType) {
        if (caseType.equalsIgnoreCase("MPAM")) {
            iSelectAManagementUIDashboardLink("Manage MPAM Enquiry Reasons");
            listsManagement.selectAnEnquirySubject();
            clickTheButton("Submit");
        } else if (caseType.equalsIgnoreCase("COMP")) {
            iSelectAManagementUIDashboardLink("Manage UKVI Complaint Enquiry Reasons");
        }
        listsManagement.clickTheAddNewEnquiryReasonButton(caseType);
        listsManagement.enterEnquiryReasonName();
        if (caseType.equalsIgnoreCase("COMP")) {
            listsManagement.enterEnquiryReasonCode();
            clickTheButton("Submit");
        } else if (caseType.equalsIgnoreCase("MPAM")) {
            clickTheButton("Add");
        }
    }

    @And("I select to add a new account manager")
    public void iSelectToAddANewAccountManager() {
        listsManagement.clickTheAddNewAccountManagerButton();
    }

    @And("I submit details for the new account manager")
    public void iSubmitDetailsForTheNewAccountManager() {
        listsManagement.enterAccountManagerName();
        listsManagement.enterAccountManagerCode();
        clickTheButton("Submit");
    }

    @Then("the success message for adding an account manager should be displayed")
    public void theSuccessMessageForAddingAnAccountManagerShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingAccountManagerVisible();
    }

    @And("I should be able to view the new/renamed account manager in the table of account managers")
    public void iShouldBeAbleToViewTheNewAccountManagerOnTheViewAndEditAccountManagersPage() {
        muiDashboard.selectDashboardLinkWithText("Manage FOI Account Managers");
        listsManagement.assertVisibilityOfAccountManagerInAccountManagerTable();
    }

    @And("I have an existing account manager I want to amend")
    public void iHaveAnExistingAccountManagerIWantToAmend() {
        iSelectAManagementUIDashboardLink("Manage FOI Account Managers");
        listsManagement.clickTheAddNewAccountManagerButton();
        listsManagement.enterAccountManagerName();
        listsManagement.enterAccountManagerCode();
        clickTheButton("Submit");
    }

    @And("I select to amend the account manager")
    public void iSelectToAmendTheAccountManager() {
        listsManagement.clickAmendLinkFor(sessionVariableCalled("accountManagerName"));
    }

    @And("I submit a new name for the account manager")
    public void iSubmitANewNameForTheAccountManager() {
        listsManagement.enterAccountManagerName();
        clickTheButton("Submit");
    }

    @Then("the success message for amending an account manager should be displayed")
    public void theSuccessMessageForAmendingAnAccountManagerShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingAccountManagerVisible();
    }

    @And("I select to add a new interested party")
    public void iSelectToAddANewIntersetedParty() {
        listsManagement.clickTheAddNewInterestedPartyButton();
    }

    @And("I submit details for the new interested party")
    public void iSubmitDetailsForTheNewInterestedParty() {
        listsManagement.enterInterestedPartyName();
        listsManagement.enterInterestedPartyCode();
        clickTheButton("Submit");
    }

    @Then("the success message for adding an interested party should be displayed")
    public void theSuccessMessageForAddingAnInterestedPartyShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingInterestedPartyVisible();
    }

    @And("I should be able to view the new/renamed interested party in the table of interested parties")
    public void iShouldBeAbleToViewTheNewInterestedPartyOnTheViewAndEditInterestedPartiesPage() {
        muiDashboard.selectDashboardLinkWithText("Manage FOI Interested Parties");
        listsManagement.assertVisibilityOfInterestedPartyInInterestedPartyTable();
    }

    @And("I have an existing interested party I want to amend")
    public void iHaveAnExistingInterestedPartyIWantToAmend() {
        iSelectAManagementUIDashboardLink("Manage FOI Interested Parties");
        listsManagement.clickTheAddNewInterestedPartyButton();
        listsManagement.enterInterestedPartyName();
        listsManagement.enterInterestedPartyCode();
        clickTheButton("Submit");
    }

    @And("I select to amend the interested party")
    public void iSelectToAmendTheInterestedParty() {
        listsManagement.clickAmendLinkFor(sessionVariableCalled("interestedPartyName"));
    }

    @And("I submit a new name for the interested party")
    public void iSubmitANewNameForTheInterestedParty() {
        listsManagement.enterInterestedPartyName();
        clickTheButton("Submit");
    }

    @Then("the success message for amending an interested party should be displayed")
    public void theSuccessMessageForAmendingAnInterestedPartyShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingInterestedPartyVisible();
    }

    @And("I have added a new interested party in MUI")
    public void iHaveAddedANewInterestedPartyInMUI() {
        iSelectAManagementUIDashboardLink("Manage FOI Interested Parties");
        listsManagement.clickTheAddNewInterestedPartyButton();
        listsManagement.enterInterestedPartyName();
        listsManagement.enterInterestedPartyCode();
        clickTheButton("Submit");
    }

    @And("I select to add a new recipient")
    public void iSelectToAddANewRecipient() {
        listsManagement.clickTheAddNewRecipientButton();
    }

    @And("I submit details for the new recipient")
    public void iSubmitDetailsForTheNewRecipient() {
        listsManagement.enterRecipientName();
        listsManagement.enterRecipientCode();
        clickTheButton("Submit");
    }

    @Then("the success message for adding a new recipient should be displayed")
    public void theSuccessMessageForAddingANewRecipientShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingRecipientVisible();
    }

    @Then("I should be able to view the new/renamed recipient in the table of recipients")
    public void theNewRecipientDetailsShouldBeDisplayedInTheListOfRecipients() {
        muiDashboard.selectDashboardLinkWithText("Manage Treat Official Recipients");
        listsManagement.assertVisibilityOfRecipientInRecipientTable();
    }

    @And("I have an existing recipient I want to amend")
    public void iHaveAnExistingRecipientIWantToAmend() {
        iSelectAManagementUIDashboardLink("Manage Treat Official Recipients");
        listsManagement.clickTheAddNewRecipientButton();
        listsManagement.enterRecipientName();
        listsManagement.enterRecipientCode();
        clickTheButton("Submit");
    }

    @And("I select to amend the recipient")
    public void iSelectToAmendTheRecipient() {
        listsManagement.clickAmendLinkFor(sessionVariableCalled("recipientName"));
    }

    @And("I submit a new name for the recipient")
    public void iSubmitANewNameForTheRecipient() {
        listsManagement.enterRecipientName();
        clickTheButton("Submit");
    }

    @Then("the success message for amending a recipient should be displayed")
    public void theSuccessMessageForAmendingARecipientShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingRecipientVisible();
    }

    @And("I have added a new recipient in MUI")
    public void iHaveAddedANewRecipientInMUI() {
        iSelectAManagementUIDashboardLink("Manage Treat Official Recipients");
        listsManagement.clickTheAddNewRecipientButton();
        listsManagement.enterRecipientName();
        listsManagement.enterRecipientCode();
        clickTheButton("Submit");
    }

    @And("I select a directorate to add the new Business Area to")
    public void iSelectADirectorateToAddTheNewBusinessAreaTo() {
        listsManagement.selectADirectorate();
        clickTheButton("Submit");
    }

    @And("I add a new business area to the selected directorate")
    public void iAddANewBusinessAreaToTheSelectedDirectorate() {
        listsManagement.clickAddNewBusinessAreaButton();
        listsManagement.enterBusinessAreaName();
        clickTheButton("Submit");
    }

    @And("I navigate to the business area list and select the amend link for the business area")
    public void iSelectTheAmendLinkForBusinessArea() {
        iSelectAManagementUIDashboardLink("Manage UKVI Complaints Business Areas");
        String directorate = sessionVariableCalled("directorate");
        listsManagement.selectSpecificDirectorate(directorate);
        clickTheButton("Submit");
        listsManagement.clickAmendLinkFor(sessionVariableCalled("businessAreaName"));
    }

    @And("I amend the name of the business area")
    public void iAmendTheNameOfTheBusinessArea() {
        listsManagement.enterBusinessAreaName();
        clickTheButton("Submit");
    }

    @And("I should be able to view the created/renamed business area in the table of business areas")
    public void iShouldBeAbleToViewTheCreatedBusinessAreaInTheTableOfBusinessAreas() {
        iSelectAManagementUIDashboardLink("Manage UKVI Complaints Business Areas");
        String directorate = sessionVariableCalled("directorate");
        listsManagement.selectSpecificDirectorate(directorate);
        clickTheButton("Submit");
        listsManagement.assertVisibilityOfBusinessAreaInBusinessAreaTable();
    }

    @Then("the success message for adding a new business area should be displayed")
    public void theSuccessMessageForAddingANewBusinessAreaShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAddingBusinessAreaVisible();
    }

    @Then("the success message for amending a Business Area should be displayed")
    public void theSuccessMessageForAmendingABusinessAreaShouldBeDisplayed() {
        listsManagement.assertSuccessMessageForAmendingBusinessAreaVisible();
    }

    @And("I withdraw the case")
    public void iWithdrawTheCase() {
        mui.withdrawACaseInMUI(getCurrentCaseReference());
        loginPage.navigateToCS();
    }
}

