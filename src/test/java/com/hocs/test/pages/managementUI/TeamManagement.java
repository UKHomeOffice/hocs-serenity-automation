package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.base_page.Page;
import config.Users;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static net.serenitybdd.core.Serenity.setSessionVariable;

public class TeamManagement extends Page {

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

    public void assertTeamManagementPageTitle() {
        managementUIPageTitle.shouldContainText("Team search");
    }

    public void selectATeam(String teamName) {
        waitABit(500);
        typeInto(teamSearchBar, teamName);
        setSessionVariable("teamName").to(teamName);
        teamSearchBar.sendKeys(Keys.ENTER);
        clickOn(viewTeamButton);
    }

    public void selectTeamWithNoUsers() {
        waitABit(500);
        typeInto(teamSearchBar, "Data & Identity Unit");
        teamSearchBar.sendKeys(Keys.ENTER);
        clickOn(viewTeamButton);
    }

    public void selectAUser(Users user) {
        addTeamMembersButton.waitUntilClickable().click();
        userSearchBar.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().sendKeys(user.getAllocationText());
        waitABit(6000);
        userSearchBar.sendKeys(Keys.ENTER);
        clickOn(addSelectedUsersButton);
        waitABit(2000);
    }

    public void clickAddSelectedUsers() {
        clickOn(addTeamMembersButton);
        clickOn(addSelectedUsersButton);
    }

    public void removeUserFromTeamWithAssignedCases(String nameOfUser) {
        WebElementFacade removeButtonOfUser = findAll("//td[@class='govuk-table__cell'][contains(text(), '" + nameOfUser +
                "')]/."
                + ".//td//a").get(0);
        clickOn(removeButtonOfUser);
    }

    public void assertTeamName() {
        String header = teamNameHeader.getText();
        teamNameHeader.shouldContainText("Team: " + sessionVariableCalled("teamName"));
    }

    public void assertThatUserIsVisibleInTeamList() {
        WebElementFacade membersInTeamTable = findAll("(//tr[@class='govuk-table__row'])[2]").get(0);

        String nameOfTeamInHeader = sessionVariableCalled("teamName").toString();

        teamNameHeader.shouldContainText(nameOfTeamInHeader);
        membersInTeamTable.shouldContainText(Users.AUTOMATION_USER.getAllocationText());
    }

    public void removeFirstUserInListAndStoreName() {
        WebElementFacade firstMemberInTeamTable = findBy("(//td[@class='govuk-table__cell'])[1]");
        String nameAndEmailOfFirstUser = firstMemberInTeamTable.getText();
        setSessionVariable("userNameAndEmail").to(nameAndEmailOfFirstUser);
        clickOn(firstRemoveButtonInList);
    }

    public void clearTeamMember(String name) {
        WebElementFacade removeButton = $("//td[contains (text(), '" + name + "')]/parent::tr//a[text() = 'Remove']");
        if (isElementDisplayed($(removeButton))) {
            clickOn(removeButton);
        }
    }

    public void assertThatRemovedUserIsNoLongerVisibleInList() {
        waitABit(500);
        String removedUser = sessionVariableCalled("user").toString();
        $("//body").shouldNotContainText(removedUser);
    }

    public void assertThatTeamContainsNoUsers() {
        waitABit(500);
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
        waitABit(500);
        $("//table[@class='govuk-table']").shouldContainText(Users.EAMON.getAllocationText());
        $("//table[@class='govuk-table']").shouldContainText(Users.CASEY.getAllocationText());
    }

    public void assertSelectSomeUsersErrorMessage() {
        errorMessage.shouldContainText("Please select some users before submitting.");
    }
}
