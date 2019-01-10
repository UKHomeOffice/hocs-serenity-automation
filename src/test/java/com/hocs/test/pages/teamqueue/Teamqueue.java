package com.hocs.test.pages.teamqueue;


import com.hocs.test.pages.Page;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

public class Teamqueue extends Page {

    @Managed
    WebDriver driver;

    @FindBy(linkText = "Dashboard")
    public WebElementFacade dashboardButton;


    public void clickDashboard() {
        dashboardButton.click();
    }

    public void waitForDashboardButton() {
        waitFor(dashboardButton);
    }


    public void iHaveCasesInMyTeamqueue() {
    }


    public void iHaveCasesInMyWorkqueue() {
    }


    public void assignCasesToMe() {
    }


    public void sendStringToFilter() {
    }


    public void sendStringTopicToFilter() {
    }


    public void sendStringPrimeCorresToFilter() {
    }


    public void selectManyUnassignedCases() {
    }


    public void selectManyAssignedToAnotherUser() {
    }


    public void assignedManyToAnotherUser() {
    }


    public void iSelectTheReferenceNumber() {
    }


    public void assignTheseCasesToAnotherUser() {
    }

    public void someOtherMethods2() {
    }

   public void assertCaseIsNotVisible(){
        String assertElement
                = Serenity.sessionVariableCalled("assertCase").toString();
        element(assertElement).shouldNotBePresent();
    }

    // ^^ This element is already gone by this point in the test.

    public void assertWorkflowQueueTotal() {
    }

    public void assertTeamqueueDisplaysOverdue() {
    }

    public void assertWorkflowQueuePerTypeOfCase() {
    }

    public void assertUnassignedAgainstX() {
    }

    public void assertColumnsAreReallyVisible() {
    }

    public void assertCasesAreAssignedToAnotherUser() {
    }

    public void assertTheseCasesAreAssignedToMe() {
    }

    public void assertUsersColumnRepresentsOnlyUnassigned() {
    }

    public void assertUsersColumnOnlyRepresentsThisString() {
    }

    public void assertIAmTakenToCasework() {
    }

    public void assertThisCaseReferenceIsDisplayed() {
    }

    public void assertBreadcrumbsAreDisplayed() {
    }

    public void assertResultsNotFromFirst20() {
    }

    public void assertColumnOnlyRepresentsThisColumn() {
    }

    public void assertTotalAssignedToMeTeamsPage() {
    }

    public void assertTotalAssignedToMeWorkqueue() {
    }

    public void assertCasesNotAddedToAssignedMeHome() {
    }

    public void assertCasesNotAddedToAssignedMeTeam() {
    }

    public void assertCasesAreNotAssignedAnotherUserHome() {
    }

    public void assertThatCasesAreNotAssignedToAnotherUserTeam() {
    }

    public void assertCasetypeColumnOnlyShowsTestedString() {
    }
}
