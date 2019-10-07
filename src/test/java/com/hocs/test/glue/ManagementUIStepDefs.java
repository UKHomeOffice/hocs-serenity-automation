package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.managementUI.Dashboard;
import com.hocs.test.pages.managementUI.TeamManagement;
import com.hocs.test.pages.managementUI.UnitManagement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;

public class ManagementUIStepDefs extends Page {

    Dashboard dashboard;

    TeamManagement teamManagement;

    UnitManagement unitManagement;

    @When("^I navigate to the \"([^\"]*)\" Management page$")
    public void navigateToSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "TEAM":
                clickOn(dashboard.addRemoveUsersButton);
                break;
            case "UNIT":
                clickOn(dashboard.addUnitButton);
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @Then("^I should be taken to the \"([^\"]*)\" Management page$")
    public void assertThatTheUserIsTakenToTheSelectedManagementPage(String managementPage) {
        switch (managementPage.toUpperCase()) {
            case "TEAM":
                teamManagement.assertTeamManagementPageTitle();
                break;
            case "UNIT":
                unitManagement.assertUnitManagementPageTitle();
                break;
            default:
                pendingStep(managementPage + " is not defined within " + getMethodName());
        }
    }

    @When("^I select the \"([^\"]*)\" team from the dropdown$")
    public void selectTeamFromDropdown(String teamName) {
        switch (teamName.toUpperCase()) {
            case "PURSUE DISRUPTIONS UNIT":
                teamManagement.selectATeam(teamName);
                break;
            case "OSCT SECRETARIAT":
                teamManagement.selectATeam(teamName);
                break;
            default:
                pendingStep(teamName + " is not defined within " + getMethodName());
        }
    }

    @And("^I add the user \"([^\"]*)\" to the team$")
    public void addUserToSelectedTeam(String nameOfUser) {
        waitABit(500);
        teamManagement.assertTeamName();
        switch (nameOfUser.toUpperCase()) {
            case "EAMON.DROKO@TEN10.COM":
                teamManagement.selectAUser(nameOfUser);
                break;
            case "DANNY.LARGE@TEN10.COM":
                teamManagement.selectAUser(nameOfUser);
                break;
            default:
                pendingStep(nameOfUser + " is not defined within " + getMethodName());
        }
    }

    @Then("^the user should be visible in the team list$")
    public void assertThatUserIsVisibleInTeamList() {
        teamManagement.assertThatUserIsVisibleInTeamList();
    }
}

