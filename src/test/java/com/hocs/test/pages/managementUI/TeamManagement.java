package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.BasePage;
import config.User;
import java.time.Duration;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class TeamManagement extends BasePage {

    @FindBy(xpath = "//input[@id='Teams-input']")
    public WebElementFacade teamSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade viewTeamButton;

    @FindBy(css = "button[type='submit']:nth-of-type(1)")
    public WebElementFacade addTeamMembersButton;

    @FindBy(xpath = "//input[@id='users-input']")
    public WebElementFacade userSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade addSelectedUsersButton;

    @FindBy(xpath = "//h2[@class='govuk-heading-l']")
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

    public void assertTeamManagementPageTitle() {
        managementUIPageTitle.shouldContainText("Team search");
    }

    public void selectATeam(String teamName) {
        waitABit(1000);
        teamSearchBar.sendKeys(teamName);
        waitABit(1000);
        setSessionVariable("teamName").to(teamName);
        teamSearchBar.sendKeys(Keys.ENTER);
        safeClickOn(viewTeamButton);
    }

    public void selectAUser(User user) {
        addTeamMembersButton.waitUntilClickable().click();
        userSearchBar.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().sendKeys(user.getAllocationText());
        waitABit(6000);
        userSearchBar.sendKeys(Keys.ENTER);
        safeClickOn(addSelectedUsersButton);
        waitABit(2000);
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
        String header = teamNameHeader.getText();
        teamNameHeader.shouldContainText("Team: " + sessionVariableCalled("teamName"));
    }

    public void assertThatUserIsVisibleInTeamList(User user) {
        int n = 0;
        boolean trueFalse = false;
        List<WebElementFacade> membersInTeamTable = findAll("//tr[@class='govuk-table__row']");
        while (n <= membersInTeamTable.size() && !trueFalse) {
            n++;
            String nameOfTeamInHeader = sessionVariableCalled("teamName").toString();
            teamNameHeader.shouldContainText(nameOfTeamInHeader);
            if (membersInTeamTable.get(n).containsText(user.getAllocationText())) {
                trueFalse = true;
            }
        }
        membersInTeamTable.get(n).shouldContainText(user.getAllocationText());
    }

    public void removeFirstUserInListAndStoreName() {
        WebElementFacade firstMemberInTeamTable = findBy("(//td[@class='govuk-table__cell'])[1]");
        String nameAndEmailOfFirstUser = firstMemberInTeamTable.getText();
        setSessionVariable("userNameAndEmail").to(nameAndEmailOfFirstUser);
        safeClickOn(firstRemoveButtonInList);
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

    public void assertThatRemovedUserIsNoLongerVisibleInList() {
        waitABit(1000);
        String removedUser = sessionVariableCalled("user").toString();
        $("//body").shouldNotContainText(removedUser);
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
}