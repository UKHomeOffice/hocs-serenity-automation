package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.When;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class TeamManagement extends Page {

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    public WebElementFacade teamSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade viewTeamButton;

    @FindBy(xpath = "//button[@class='govuk-button add-team-members-button']")
    public WebElementFacade addTeamMembersButton;

    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public WebElementFacade userSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade addSelectedUsersButton;

    @FindBy(xpath = "//h2[@class='govuk-heading-l']")
    public WebElementFacade teamNameHeader;

    @FindBy(xpath = "//tbody[@class='govuk-table__body']")
    public WebElementFacade teamMemberTable;

    public void assertTeamManagementPageTitle() {
        assertThat($("//h1").getText(), is("Team search"));
    }

    public void selectATeam(String teamName) {
        typeInto(teamSearchBar, teamName);
        setSessionVariable("teamName").to(teamName);
        teamSearchBar.sendKeys(Keys.ENTER);
        clickOn(viewTeamButton);
    }

    public void selectAUser(String nameOfUser) {
        clickOn(addTeamMembersButton);
        typeInto(userSearchBar, nameOfUser);
        setSessionVariable("nameOfUser").to(nameOfUser);
        userSearchBar.sendKeys(Keys.ENTER);
        clickOn(addSelectedUsersButton);
    }

    public void assertThatUserIsVisibleInTeamList() {
        String nameOfTeamInHeader = sessionVariableCalled("teamName").toString();
        String nameOfNewUser = sessionVariableCalled("nameOfUser").toString();

        assertThat(teamNameHeader.getText(), containsText(nameOfTeamInHeader));
        assertThat(teamMemberTable.getText(), containsText(nameOfNewUser));
    }
}
