package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.decs.BasePage;
import config.User;
import java.time.Duration;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class TeamManagement extends BasePage {

    @FindBy(xpath = "//input[@id='Teams-input']")
    public WebElementFacade teamSearchBar;

    @FindBy(xpath = "//button[contains(text(),'View team')]")
    public WebElementFacade viewTeamButton;

    @FindBy(css = "button[type='submit']:nth-of-type(1)")
    public WebElementFacade addTeamMembersButton;

    @FindBy(xpath = "//input[@id='users-input']")
    public WebElementFacade userSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade addSelectedUsersButton;

    @FindBy(xpath = "//span[contains(text(), 'Team:')]")
    public WebElementFacade teamNameHeader;

    @FindBy(xpath = "(//td//a)[1]")
    public WebElementFacade firstRemoveButtonInList;

    @FindBy(xpath = "(//div//p)[2]")
    public WebElementFacade errorMessage;

    @FindBy(id = "teamName")
    public WebElementFacade teamNameTextBox;

    @FindBy(id = "unit-input")
    public WebElementFacade unitTypeahead;

    @FindBy(xpath = "//button[text()='Edit Team']")
    public WebElementFacade editTeamButton;

    @FindBy(id = "newTeamName")
    public WebElementFacade newTeamNameTextBox;

    @FindBy(xpath = "//button[text()='Update']")
    public WebElementFacade updateButton;

    @FindBy(xpath = "//h2[text()='Success']/following-sibling::p")
    public WebElementFacade successMessage;

    @FindBy(xpath = "//button[text()='Add']")
    public WebElementFacade addTeamButton;

    @FindBy(xpath = "//button[text()='Deactivate team']")
    public WebElementFacade deactivateTeamButton;

    @FindBy(xpath = "//button[text()='Deactivate Team']")
    public WebElementFacade deactivateTeamConfirmationButton;

    @FindBy(xpath = "//button[text()='Reactivate team']")
    public WebElementFacade reactivateTeamButton;

    @FindBy(xpath = "//button[text()='Reactivate Team']")
    public WebElementFacade reactivateTeamConfirmationButton;

    @FindBy(xpath = "//a[text()='Reactivate team']")
    public WebElementFacade reactivateTeamHypertext;

    @FindBy(xpath = "//strong[text()='Inactive']")
    public WebElementFacade inactiveTag;

    @FindBy(id = "showDeactivated")
    public WebElementFacade showDeactivatedTeamsCheckbox;

    User addedOrRemovedUser;

    public void assertTeamManagementPageTitle() {
        managementUIPageTitle.shouldContainText("Team search");
    }

    public void selectATeam(String teamName) {
        waitABit(1000);
        teamSearchBar.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible().sendKeys(teamName);
        waitABit(1000);
        setSessionVariable("teamName").to(teamName);
        teamSearchBar.sendKeys(Keys.ENTER);
        safeClickOn(viewTeamButton);
    }

    public void selectAUser(User user) {
        userSearchBar.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().sendKeys(user.getAllocationText());
        WebElementFacade userOption = findBy("//div[contains(@class, 'govuk-typeahead__option')][text()='" + user.getAllocationText() + "']");
        userOption.withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable().click();
        waitABit(1000);
    }

    public void addTeamMember(User user) {
        addTeamMembersButton.waitUntilClickable().click();
        selectAUser(user);
        clickOn(addSelectedUsersButton);
        addedOrRemovedUser = user;
    }

    public void removeTeamMember(User user) {
        WebElementFacade removeUserButton = $("//td[contains (text(), '" + user.getAllocationText() + "')]/parent::tr//a[text() = 'Remove']");
        clickOn(removeUserButton);
        addedOrRemovedUser = user;
    }

    public void clickAddSelectedUsers() {
        safeClickOn(addTeamMembersButton);
        safeClickOn(addSelectedUsersButton);
    }

    public void removeUserFromTeamWithAssignedCases(String nameOfUser) {
        WebElementFacade removeButtonOfUser = findBy("//td[contains(text(), '" + nameOfUser + "')]/following-sibling::td/a");
        safeClickOn(removeButtonOfUser);
    }

    public void assertTeamName() {
        teamNameHeader.shouldContainText("Team: " + sessionVariableCalled("teamName"));
    }

    public void clearTeamMember(String name) {
        WebElementFacade removeButton = $("//td[contains (text(), '" + name + "')]/parent::tr//a[text() = 'Remove']");
        if (isElementDisplayed($(removeButton))) {
            safeClickOn(removeButton);
        }
    }

    public void createDraftingTeamWithRandomName() {
        String randomTeamValue = generateRandomString();
        String newTeamName = "Test Drafting Team - " + randomTeamValue;
        setSessionVariable("draftingTeamName").to(newTeamName);
        typeInto(teamNameTextBox, newTeamName);
        unitTypeahead.sendKeys("Border Force");
        unitTypeahead.sendKeys(Keys.ENTER);
        safeClickOn(addTeamButton);
    }

    public void editNewDCUDraftingTeamName() {
        String initialTeamName = sessionVariableCalled("draftingTeamName");
        setSessionVariable("initialDraftingTeamName").to(initialTeamName);
        selectATeam(initialTeamName);
        safeClickOn(editTeamButton);
        String newTeamName = "Edited " + initialTeamName;
        setSessionVariable("newDraftingTeamName").to(newTeamName);
        typeInto(newTeamNameTextBox, newTeamName);
        safeClickOn(updateButton);
    }

    public void deactivateTeam() {
        safeClickOn(deactivateTeamButton);
        safeClickOn(deactivateTeamConfirmationButton);
        waitABit(500);
        String teamName = teamNameHeader.getText().split(": ")[1];
        setSessionVariable("deactivatedTeamName").to(teamName);
    }

    public void reactivateTeam() {
        safeClickOn(reactivateTeamButton);
        safeClickOn(reactivateTeamConfirmationButton);
    }

    public void selectShowDeactivatedTeamCheckbox() {
        safeClickOn(showDeactivatedTeamsCheckbox);
    }

    public void assertNewTeamIsDisplayed() {
        waitABit(1000);
        String displayedTeam = teamNameHeader.getText().split(": ")[1];
        String newTeam = sessionVariableCalled("draftingTeamName");
        assertThat(displayedTeam.equalsIgnoreCase(newTeam), is(true));
    }

    public void assertRenamedTeamIsDisplayed() {
        waitABit(750);
        String displayedTeam = teamNameHeader.getText().split(": ")[1];
        String renamedTeam = sessionVariableCalled("newDraftingTeamName");
        assertThat(displayedTeam.equalsIgnoreCase(renamedTeam), is(true));
    }

    public void assertThatTeamContainsNoUsers() {
        waitABit(1000);
        assertThat(isElementDisplayed(firstRemoveButtonInList), is(false));
    }

    public void assertUserHasCasesErrorMessage() {
        waitForAnyTextToAppear("The user cannot be removed from the team as they have cases assigned");
        errorMessage.shouldContainText("The user cannot be removed from the team as they have cases assigned");
    }

    public void assertSelectATeamErrorMessage() {
        waitForAnyTextToAppear("Please select a team before submitting.");
        errorMessage.shouldContainText("Please select a team before submitting.");
    }

    public void assertMultipleUsersAddedToTeam() {
        waitABit(1000);
        $("//table[@class='govuk-table']").shouldContainText(User.CAMERON.getAllocationText());
        $("//table[@class='govuk-table']").shouldContainText(User.CASEY.getAllocationText());
    }

    public void assertSelectSomeUsersErrorMessage() {
        errorMessage.shouldContainText("Please select some users before submitting.");
    }

    public void assertSuccessMessageOfTeamCreation() {
        successMessage.shouldContainText("The team was created successfully.");
    }

    public void assertSuccessMessageOfTeamRename() {
        String initialTeamName = sessionVariableCalled("initialDraftingTeamName");
        String finalTeamName = sessionVariableCalled("newDraftingTeamName");
        successMessage.shouldContainText("Team name changed from " + initialTeamName + " to " + finalTeamName + ".");
    }

    public void assertSuccessMessageOfDeactivation() {
        successMessage.shouldContainText("has been deactivated successfully");
    }

    public void assertSuccessMessageOfReactivation() {
        successMessage.shouldContainText("has been reactivated successfully");
    }

    public void assertActiveStatusOfTeam(String status) {
        waitABit(1500);
        if (status.equalsIgnoreCase("INACTIVE")) {
            assertThat(inactiveTag.isCurrentlyVisible(), is(true));
        } else if (status.equalsIgnoreCase("ACTIVE")) {
            assertThat(inactiveTag.isCurrentlyVisible(), is(false));
        }
    }

    public void assertDeactivatedTeamIsDisplayed() {
        waitABit(500);
        teamNameHeader.shouldContainText(sessionVariableCalled("deactivatedTeamName"));
    }

    public void assertThatUserVisibleInTeamListIs(boolean assertion) {
        String nameOfTeamInHeader = sessionVariableCalled("teamName").toString();
        assertThat(teamNameHeader.getText(), containsText(nameOfTeamInHeader));
        WebElementFacade userInTeamList = findBy("//td[contains(text(), '" + addedOrRemovedUser.getAllocationText() + "')]");
        if (assertion) {
            successMessage.waitUntilVisible();
        } else {
            userInTeamList.waitUntilNotVisible();
        }
        assertThat(userInTeamList.isVisible(), is(assertion));
    }
}