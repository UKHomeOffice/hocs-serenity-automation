package com.hocs.test.pages.teamqueue;


import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    // NAVIGATE THE TABLE FOR THE STAGE.....

    public String getStageFromWorkstacksTable() {
        WebElement caseReferenceStage = getDriver().findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseId")
                        + "']/../following-sibling::td[1]"));
        System.out.println(caseReferenceStage);

        return caseReferenceStage.getText();
    }

    public void assertCaseStage(String stage) {
        assertThat(getStageFromWorkstacksTable().toUpperCase(), is(stage.toUpperCase()));
    }

    public String getAllocatedUserFromWorkstacksTable() {
        WebElement caseReferenceStage = driver.findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseReference")
                        + "']/../following-sibling::td[2]"));

        return caseReferenceStage.getText();
    }

    public String getTeamFromWorkstacksTable() {
        WebElement caseReferenceStage = driver.findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseReference")
                        + "']/../following-sibling::td[3]"));

        return caseReferenceStage.getText();
    }

    public String getDeadlineFromWorkstacksTable() {
        WebElement caseReferenceStage = driver.findElement(
                By.xpath("//a[text()='" + sessionVariableCalled("caseReference")
                        + "']/../following-sibling::td[4]"));

        return caseReferenceStage.getText();
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
                = sessionVariableCalled("assertCase").toString();
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
