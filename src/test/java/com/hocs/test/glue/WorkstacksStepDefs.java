package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.Search;
import com.hocs.test.pages.Workstacks;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import org.openqa.selenium.NoSuchElementException;

public class WorkstacksStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Search search;

    CreateCase createCase;

    CreateCase_SuccessPage createCaseSuccessPage;

    EndToEndStepDefs endToEndStepDefs;

    @Given("I allocate the case to myself")
    public void allocateCaseToMyself() {
        safeClickOn(workstacks.allocateSelectedToMeButton);
    }

    @When("I unallocate the case from myself")
    public void unallocateCase() {
        safeClickOn(workstacks.unallocateButton);
    }

    @When("I select the check box against the case and allocate it to myself")
    public void allocateCaseUsingCheckbox() {
        workstacks.clickCheckboxRelevantToCaseReference();
        safeClickOn(workstacks.allocateSelectedToMeButton);
    }

    @Then("the case {string} be visible in my workstack")
    public void theCaseBeVisibleInMyWorkstack(String input) {
        safeClickOn(dashboard.myCases);
        switch (input.toUpperCase()) {
            case "SHOULD":
                workstacks.assertVisibilityOfCaseReference(true);
                break;
            case "SHOULD NOT":
                workstacks.assertVisibilityOfCaseReference(false);
                break;
            default:
                pendingStep(input + " is not defined within " + getMethodName());
        }
    }

    @When("I enter the Case Reference type {string} into the filter")
    public void enterCaseReferenceType(String caseReferenceType) {
        safeClickOn(workstacks.workstackFilter);
        switch (caseReferenceType.toUpperCase()) {
            case "MIN":
            case "DTEN":
            case "TRO":
                typeInto(workstacks.workstackFilter, caseReferenceType);
                break;
            default:
                pendingStep(caseReferenceType + " is not defined within " + getMethodName());
        }
    }

    @Then("the cases should be filtered by the {string} Case Reference")
    public void assertCasesAreFilteredByCaseReference(String caseReference) {
        workstacks.assertCasesAreFilteredByRef(caseReference);
    }

    @When("I click the {string} case type filter card")
    public void clickCaseTypeFilterCard(String caseTypeCard) {
        safeClickOn(dashboard.performanceProcessTeam);
        switch (caseTypeCard.toUpperCase()) {
            case "MIN":
                safeClickOn(workstacks.dcuMINFilterCard);
                break;
            case "DTEN":
                safeClickOn(workstacks.dcuN10FilterCard);
                break;
            case "TRO":
                safeClickOn(workstacks.dcuTROFilterCard);
                break;
            default:
                pendingStep(caseTypeCard + " is not defined within " + getMethodName());
        }
    }

    @Then("the cases should be filtered by the {string} Current Stage")
    public void assertCasesAreFilteredByCurrentStage(String currentStage) {
        workstacks.assertCasesAreFilteredByStage(currentStage);
    }

    @When("I enter the current stage {string} into the filter")
    public void enterCurrentStage(String currentStage) {
        safeClickOn(workstacks.workstackFilter);
        typeInto(workstacks.workstackFilter, currentStage.toUpperCase());
    }

    @Then("all cases should be allocated to the user {string}")
    public void assertAllCasesAssignedToAllocatedUser(User user) {
        workstacks.assertAllAllocatedUsersAre(user);
    }

    @And("I select a case and unallocate it from myself")
    public void iSelectACaseAndUnallocateItFromMyself() {
        workstacks.clickCheckboxRelevantToCaseReference();
        safeClickOn(workstacks.unallocateButton);
    }

    @And("I filter the workstack using the current cases reference")
    public void iFilterTheWorkstackUsingTheCurrentCasesReference() {
        workstacks.filterByCurrentCaseReference();
    }

    @And("I create a new case and view it in the Performance and Process team workstack")
    public void iCreateANewCaseAndViewItInThePerformanceAndProcessTeamWorkstack() {
        createCase.createCaseOfType("MIN");
        goToDashboard();
        safeClickOn(dashboard.performanceProcessTeam);
        workstacks.filterByCurrentCaseReference();
        waitABit(500);
    }

    @Then("the case should be allocated to me in the workstack")
    public void theCaseShouldBeAllocatedToMeInTheWorkstack() {
        workstacks.assertOwnerIs(getCurrentUser());
    }

    @When("I allocate the current case to {string}")
    public void iAssignTheCurrentCaseNumberToAnotherUser(String user) {
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.selectAllocationUserByVisibleText(User.valueOf(user).getAllocationText());
    }

    @Then("the owner field should display {string}")
    public void theOwnerFieldShouldDisplayTheSelectedUser(String user) {
        workstacks.assertAssignedUser(User.valueOf(user));
    }

    @Then("I should own the case")
    public void theCurrentUserShouldOwnTheCase() {
        workstacks.assertAssignedUser(getCurrentUser());
    }

    @When("I create three cases, and view them in performance and process workstack")
    public void createThreeCasesAndReassign() {
        createCase.createCaseOfType("MIN");
        goToDashboard();
        waitABit(500);
        createCase.createCaseOfType("MIN");
        goToDashboard();
        waitABit(500);
        createCase.createCaseOfType("MIN");
        goToDashboard();
        waitABit(500);
        safeClickOn(dashboard.performanceProcessTeam);
    }

    @Then("I assign these three cases to {string}")
    public void assignThreeCasesToUser(String user) {
        workstacks.allocateThreeCasesCreated(User.valueOf(user));
    }

    @Then("I check that the three cases created have been correctly assigned to {string}")
    public void checkThreeCasesProperlyReassigned(String user) {
        workstacks.assertAssignedUserOnThreeCases(User.valueOf(user));
    }

    @When("I create three cases, and assign them to {string}")
    public void iCreateThreeCasesAndAssignToUser(String user) {
        createCase.createCaseOfType("MIN");
        safeClickOn(createCaseSuccessPage.newCaseReference);
        workstacks.caseDetailsSelectAllocationUserByVisibleText(User.valueOf(user).getAllocationText());
        goToDashboard();
        createCase.createCaseOfType("MIN");
        safeClickOn(createCaseSuccessPage.newCaseReference);
        workstacks.caseDetailsSelectAllocationUserByVisibleText(User.valueOf(user).getAllocationText());
        goToDashboard();
        createCase.createCaseOfType("MIN");
        safeClickOn(createCaseSuccessPage.newCaseReference);
        workstacks.caseDetailsSelectAllocationUserByVisibleText(User.valueOf(user).getAllocationText());
        goToDashboard();
    }

    @Then("I view these cases in Performance and Process workstack, and unallocate from {string}")
    public void iUnallocateThreeCasesCreated(String user) {
        safeClickOn(dashboard.performanceProcessTeam);
        workstacks.unallocateThreeCasesFromSelectedUser(User.valueOf(user));
    }

    @Then("I then check whether the correct cases have been unallocated")
    public void checkWhetherCorrectCasesUnallocated() {
        workstacks.assertThatThreeCasesHaveBeenUnassigned();
    }

    @When("I assign this case to me, and check if it has been correctly allocated")
    public void iAssignTheCurrentCaseNumberToMe() {
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.clickAllocateSelectedToMeButton();
        waitABit(1000);
        workstacks.assertCaseIsAssignedToMe();
    }

    @When("I enter the correct Data Input team workstack for {string} cases")
    public void iEnterTheCorrectDataInputTeamWorkstackForCases(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "TRO":
                dashboard.selectPerformanceProcessTeam();
                break;
            case "DTEN":
                dashboard.selectTransferN10Team();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
                break;
        }
    }

    @When("I narrow down the visible cases using the {string} filter card")
    public void iNarrowDownTheVisibleCasesInThePerformanceAndProcessTeamWorkstackUsingTheFilterCard(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                workstacks.clickDCUMINFilterCard();
                break;
            case "TRO":
                workstacks.clickDCUTROFilterCard();
                break;
            case "DTEN":
                workstacks.clickDCUTENFilterCard();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
                break;
        }
    }

    @Then("the created case should be visible in the workstack")
    public void theCreatedCaseShouldBeVisibleInTheWorkstack() {
        workstacks.assertVisibilityOfCaseReference(true);
    }

    @Then("only {string} cases should be visible")
    public void onlyCasesShouldBeVisible(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                workstacks.assertThatDCUMINisOnlyVisibleCaseType();
                break;
            case "DTEN":
                workstacks.assertThatDCUTENisOnlyVisibleCaseType();
                break;
            case "TRO":
                workstacks.assertThatDCUTROisOnlyVisibleCaseType();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("the case should be assigned {string} points")
    public void theCaseShouldBeAssignedPoints(String expectedPoints) {
        workstacks.assertPointsOfCurrentCaseEqual(expectedPoints);
    }

    @And("I navigate to the {string} workstack and order the {string} column from {string}")
    public void orderWorkstackColumnBy(String stage, String column, String order) {
        if (stage.toUpperCase().equals("MTS TEAM")) {
            dashboard.selectMTSTeam();
        } else {
            dashboard.selectCorrectMPAMTeamByStage(stage);
        }
        workstacks.orderMPAMWorkstackColumn(column, order);
    }

    @And("I navigate to my cases and order the {string} column from {string}")
    public void orderMyCasesColumns(String column, String order) {
        dashboard.selectMyCases();
        workstacks.orderMPAMWorkstackColumn(column, order);
    }

    @And("I search for active MPAM cases and order the {string} column from {string}")
    public void orderSearchResultColumns(String column, String order) {
        safeClickOn(dashboard.searchPage);
        safeClickOn(search.mpamCaseCheckbox);
        safeClickOn(search.caseStatusActiveCheckbox);
        safeClickOn(searchButton);
        workstacks.orderMPAMWorkstackColumn(column, order);
    }

    @And("the {string} column is ordered from {string}")
    public void columnIsProperlyOrdered(String column, String order) throws ParseException {
        workstacks.assertColumnIsOrderedProperly(column, order);
    }

    @And("I view the MPAM case in the appropriate {string} stage workstack")
    public void iViewTheCaseInTheWorkstack(String stage) {
        goToDashboard();
        dashboard.selectCorrectMPAMTeamByStage(stage);
    }

    @And("I select to take the next unallocated case from the team workstack")
    public void iSelectToTakeTheNextCaseFromTheTeamWorkstack() {
        workstacks.selectTakeNextCase();
    }

    @Then("the highest priority unallocated case is loaded and allocated to the user")
    public void theHighestPriorityUnallocatedCaseIsLoadedAndAllocatedToTheUser() {
        workstacks.assertCorrectCaseIsTaken();
    }

    @Then("the case deadline {string} be highlighted")
    public void theCaseDeadlineBeHighlighted(String shouldShouldNot) {
        switch (shouldShouldNot.toUpperCase()) {
            case "SHOULD":
                workstacks.assertThatDeadlineHighlightedIs(true);
                break;
            case "SHOULD NOT":
                workstacks.assertThatDeadlineHighlightedIs(false);
                break;
            default:
                pendingStep(shouldShouldNot + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} case should be higher up the workstack than the {string} case")
    public void theCaseShouldBeHigherUpTheWorkstackThanTheCase(String highPriorityCase, String lowPriorityCase) {
        workstacks.assertHigherPriorityCaseIsFirstInWorkstack(highPriorityCase, lowPriorityCase);
    }

    @Then("the contribution request deadline should be visible in the {string} workstack")
    public void theContributionRequestDeadlineShouldBeVisibleInTheWorkstack(String stage) {
        dashboard.selectCorrectMPAMTeamByStage(stage);
        workstacks.assertCaseStageContains(sessionVariableCalled("requestDeadline"));
    }

    @Then("the follow-up due date should be visible in the {string} workstack")
    public void theFollowUpDueDateShouldBeVisibleInTheWorkstack(String stage) {
        dashboard.selectCorrectMPAMTeamByStage(stage);
        workstacks.assertCaseStageContains(sessionVariableCalled("dueDate"));
    }

    @Then("the Minister sign off team is correctly displayed")
    public void theMinisterSignOffTeamIsCorrectlyDisplayed() {
        workstacks.assertMinisterSignOffTeam();
    }

    @Then("the earliest due date of the contribution requests is displayed in workstacks")
    public void theEarliestDueDateOfTheContributionRequestsIsDisplayed() {
        goToDashboard();
        waitABit(500);
        safeClickOn(dashboard.myCases);
        workstacks.assertDueDateOfContributionRequest();
    }

    @Then("the stage that the case was rejected at should be displayed in the rejected workstack column")
    public void theStageThatTheCaseWasRejectedAtShouldBeDisplayedInTheRejectedWorkstackColumn() {
        workstacks.assertRejectedFieldOfCurrentCase();
    }

    @And("I enter a {string} workstack")
    public void iEnterAWorkstack(String workstack) {
        switch (workstack.toUpperCase()) {
            case "DCU MY CASES":
                if (dashboard.getNumberOfCasesInWorkstackFromDashboardCard("My Cases") != 0) {
                    dashboard.selectMyCases();
                } else {
                    createCase.createCaseOfType("MIN");
                    createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                    goToDashboard();
                    dashboard.selectMyCases();
                }
                break;
            case "DCU TEAM":
                try {
                    dashboard.selectPerformanceProcessTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCaseOfType("MIN");
                    goToDashboard();
                    dashboard.selectPerformanceProcessTeam();
                }
                break;
            case "UKVI MY CASES":
                try {
                    dashboard.selectMyCases();
                } catch (NoSuchElementException e) {
                    createCase.createCaseOfType("MPAM");
                    goToDashboard();
                    dashboard.selectMyCases();
                }
                break;
            case "MTS TEAM":
                try {
                    dashboard.selectMTSTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCaseOfType("MTS");
                    goToDashboard();
                    dashboard.selectMTSTeam();
                }
                break;
            case "CAMPAIGN":
            case "TRIAGE":
            case "DRAFT":
            case "CREATION":
                setSessionVariable("businessArea").to("UKVI");
                setSessionVariable("refType").to("Ministerial");
                try {
                    dashboard.selectCorrectMPAMTeamByStage(workstack);
                } catch (NoSuchElementException e) {
                    endToEndStepDefs.iCreateACaseAndMoveItToAStage("MPAM", workstack);
                    goToDashboard();
                    dashboard.selectCorrectMPAMTeamByStage(workstack);
                }
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} workstack should contain the expected columns")
    public void theWorkstackShouldContainTheExpectedColumns(String workstack) {
        workstacks.assertExpectedColumnsPresent(workstack);
    }


    @Then("the Transfer deadline date is correct in the Awaiting Transfer team workstack")
    public void theTransferDeadlineDateIsCorrectInTheAwaitingTransferTeamWorkstack() {
        workstacks.assertTransferDueDateOfCurrentCase();
    }
}
