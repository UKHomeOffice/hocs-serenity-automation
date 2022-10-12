package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Search;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import config.CaseType;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class WorkstacksStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Search search;

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

    MPAMProgressCase mpamProgressCase;

    COMPProgressCase compProgressCase;

    CaseView caseView;

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
        safeClickOn(workstacks.caseFilter);
        switch (caseReferenceType.toUpperCase()) {
            case "MIN":
            case "DTEN":
            case "TRO":
                workstacks.caseFilter.sendKeys(caseReferenceType);
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
    public void clickCaseTypeFilterCard(String caseTypeString) {
        WebElementFacade filterCard = findBy("//span[text()='" + CaseType.valueOf(caseTypeString).getCorrespondenceTypeLabel() + "']");
        safeClickOn(filterCard);
    }

    @Then("the cases should be filtered by the {string} Current Stage")
    public void assertCasesAreFilteredByCurrentStage(String currentStage) {
        workstacks.assertCasesAreFilteredByStage(currentStage);
    }

    @When("I enter the current stage {string} into the filter")
    public void enterCurrentStage(String currentStage) {
        safeClickOn(workstacks.caseFilter);
        workstacks.caseFilter.sendKeys(currentStage.toUpperCase());
    }

    @Then("all cases should be allocated to the user {string}")
    public void assertAllCasesAssignedToAllocatedUser(User user) {
        workstacks.assertAllAllocatedUsersAre(user);
    }

    @And("I select the case/claim and unallocate it")
    public void iSelectTheCaseAndUnallocateItFromMyself() {
        workstacks.clickCheckboxRelevantToCaseReference();
        safeClickOn(workstacks.unallocateButton);
    }

    @And("I click the link for the current case in the workstack")
    public void iClickTheLinkForTheCurrentCase() {
        workstacks.goToCurrentCaseFromWorkstack();
    }

    @And("I filter the workstack using the current cases reference")
    public void iFilterTheWorkstackUsingTheCurrentCasesReference() {
        workstacks.filterByCurrentCaseReference();
    }

    @And("I create a new case and view it in the Performance and Process team workstack")
    public void iCreateANewCaseAndViewItInThePerformanceAndProcessTeamWorkstack() {
        createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
        dashboard.goToDashboard();
        safeClickOn(dashboard.performanceProcessTeam);
        waitABit(1000);
    }

    @Then("the case should be allocated to me in the workstack")
    public void theCaseShouldBeAllocatedToMeInTheWorkstack() {
        workstacks.assertOwnerOfCurrentCaseIs(getCurrentUser());
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
        createCase.createCSCaseOfTypeWithDocument(CaseType.TRO);
        setSessionVariable("caseReference1").to(getCurrentCaseReference());
        dashboard.goToDashboard();
        waitABit(1000);
        createCase.createCSCaseOfTypeWithDocument(CaseType.TRO);
        setSessionVariable("caseReference2").to(getCurrentCaseReference());
        dashboard.goToDashboard();
        waitABit(1000);
        createCase.createCSCaseOfTypeWithDocument(CaseType.TRO);
        setSessionVariable("caseReference3").to(getCurrentCaseReference());
        dashboard.goToDashboard();
        waitABit(1000);
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
        int n = 0;
        while (n < 3) {
            createCase.createCSCaseOfTypeWithDocument(CaseType.TRO);
            safeClickOn(confirmationScreens.caseReference);
            caseView.allocateToUserByVisibleText(User.valueOf(user).getAllocationText());
            dashboard.goToDashboard();
            n++;
        }
    }

    @Then("I view these cases in Performance and Process workstack, and unallocate from {string}")
    public void iUnallocateThreeCasesCreated(String user) {
        safeClickOn(dashboard.performanceProcessTeam);
        workstacks.unallocateThreeCasesFromSelectedUser();
    }

    @Then("I then check whether the correct cases have been unallocated")
    public void checkWhetherCorrectCasesUnallocated() {
        workstacks.assertThatThreeCasesHaveBeenUnassigned();
    }

    @When("I assign this case to me, and check if it has been correctly allocated")
    public void iAssignTheCurrentCaseNumberToMe() {
        waitABit(3500);
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.clickAllocateSelectedToMeButton();
        waitABit(3500);
        workstacks.assertCaseIsAssignedToMe();
    }

    @When("I enter the correct Data Input team workstack for {string} cases")
    public void iEnterTheCorrectDataInputTeamWorkstackForCases(String caseTypeString) {
        switch (CaseType.valueOf(caseTypeString)) {
            case MIN:
            case TRO:
                dashboard.selectPerformanceProcessTeam();
                break;
            case DTEN:
                dashboard.selectTransferN10Team();
                break;
            default:
                pendingStep(caseTypeString + " is not defined within " + getMethodName());
                break;
        }
        workstacks.waitForWorkstackToLoad();
    }

    @Then("the created case should be visible in the workstack")
    public void theCreatedCaseShouldBeVisibleInTheWorkstack() {
        workstacks.assertVisibilityOfCaseReference(true);
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
        dashboard.selectSearchLinkFromMenuBar();
        search.selectCaseTypeSearchCheckboxForCaseType(CaseType.MPAM);
        safeClickOn(search.caseStatusActiveCheckbox);
        clickSearchButton();
        workstacks.orderMPAMWorkstackColumn(column, order);
    }

    @And("the {string} column is ordered from {string}")
    public void columnIsProperlyOrdered(String column, String order) throws ParseException {
        workstacks.assertColumnIsOrderedProperly(column, order);
    }

    @And("I view the MPAM case(s) in the appropriate {string} stage workstack")
    public void iViewTheCaseInTheWorkstack(String stage) {
        dashboard.goToDashboard();
        dashboard.selectCorrectMPAMTeamByStage(stage);
        workstacks.waitForWorkstackToLoad();
    }

    @And("I select to take the next unallocated case from the team workstack")
    public void iSelectToTakeTheNextCaseFromTheTeamWorkstack() {
        workstacks.selectTakeNextCase();
    }

    @Then("one of the highest priority unallocated cases is loaded and allocated to the user")
    public void oneOfTheHighestPriorityUnallocatedCasesIsLoadedAndAllocatedToTheUser() {
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
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Current Stage",sessionVariableCalled("requestDeadline"));
    }

    @Then("the follow-up due date should be visible in the {string} workstack")
    public void theFollowUpDueDateShouldBeVisibleInTheWorkstack(String stage) {
        dashboard.selectCorrectMPAMTeamByStage(stage);
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Current Stage",sessionVariableCalled("dueDate"));

    }

    @Then("the Minister sign off team is correctly displayed")
    public void theMinisterSignOffTeamIsCorrectlyDisplayed() {
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Minister Sign Off", sessionVariableCalled("signOffTeam"));
    }

    @Then("the earliest due date of the contribution requests is displayed in workstacks")
    public void theEarliestDueDateOfTheContributionRequestsIsDisplayed() {
        waitABit(1000);
        dashboard.goToDashboard();
        iEnterAWorkstack("MPAM Draft");
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Current Stage", sessionVariableCalled("contributionDueDate"));
    }

    @Then("the stage that the case was rejected at should be displayed in the rejected workstack column")
    public void theStageThatTheCaseWasRejectedAtShouldBeDisplayedInTheRejectedWorkstackColumn() {
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Rejected", sessionVariableCalled("rejectionStage"));

    }

    @And("I enter a/the {string} workstack")
    public void iEnterAWorkstack(String workstack) {
        switch (workstack.toUpperCase()) {
            case "DCU MY CASES":
                if (dashboard.getNumberOfCasesInWorkstackFromDashboardCard("My Cases") == 0) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
                    confirmationScreens.goToCaseFromConfirmationScreen();
                    caseView.clickAllocateToMeLink();
                    dashboard.goToDashboard();
                }
                dashboard.selectMyCases();
                break;
            case "DCU TEAM":
                try {
                    dashboard.selectTransferN10Team();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
                    dashboard.goToDashboard();
                    dashboard.selectPerformanceProcessTeam();
                }
                break;
            case "MPAM MY CASES":
                if (dashboard.getNumberOfCasesInWorkstackFromDashboardCard("My Cases") == 0) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.MPAM);
                    confirmationScreens.goToCaseFromConfirmationScreen();
                    caseView.clickAllocateToMeLink();
                    dashboard.goToDashboard();
                }
                dashboard.selectMyCases();
                break;
            case "MTS TEAM":
                try {
                    dashboard.selectMTSTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.MTS);
                    dashboard.goToDashboard();
                    dashboard.selectMTSTeam();
                }
                break;
            case "MPAM CAMPAIGN":
            case "MPAM TRIAGE":
            case "MPAM DRAFT":
            case "MPAM QA":
            case "MPAM CREATION":
                String stage = workstack.split(" ")[1];
                setSessionVariable("businessArea").to("UKVI");
                setSessionVariable("refType").to("Ministerial");
                try {
                    dashboard.selectCorrectMPAMTeamByStage(stage);
                } catch (NoSuchElementException e) {
                    mpamProgressCase.moveCaseFromCurrentStageToTargetStage("N/A", stage);
                    dashboard.goToDashboard();
                    dashboard.selectCorrectMPAMTeamByStage(stage);
                }
                break;
            case "COMPLAINT REGISTRATION":
                try {
                    dashboard.selectWorkstackByTeamName(workstack);
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
                    dashboard.goToDashboard();
                    dashboard.selectWorkstackByTeamName(workstack);
                }
                break;
            case "CCT TRIAGE":
                try {
                    dashboard.selectWorkstackByTeamName("CCT Stage 1 Triage Team");
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
                    dashboard.goToDashboard();
                    dashboard.selectWorkstackByTeamName("CCT Stage 1 Triage Team");
                }
                break;
            case "EX-GRATIA":
            case "MINOR MISCONDUCT":
                try {
                    dashboard.selectWorkstackByTeamName(workstack);
                } catch (NoSuchElementException e) {
                    compProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(CaseType.COMP, "N/A", workstack + " TRIAGE");
                    dashboard.goToDashboard();
                    dashboard.selectWorkstackByTeamName(workstack);
                }
                break;

            case "IE DETENTION":
                try {
                    dashboard.selectIEDETTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.IEDET);
                    dashboard.goToDashboard();
                    dashboard.selectIEDETTeam();
                }
                break;
            case "SERIOUS MISCONDUCT":
                try {
                    dashboard.selectSMCTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.SMC);
                    dashboard.goToDashboard();
                    dashboard.selectSMCTeam();
                }
                break;
            case "SERIOUS MISCONDUCT MY CASES":
                if (dashboard.getNumberOfCasesInWorkstackFromDashboardCard("My Cases") == 0) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.SMC);
                    confirmationScreens.goToCaseFromConfirmationScreen();
                    caseView.clickAllocateToMeLink();
                    dashboard.goToDashboard();
                }
                dashboard.selectMyCases();
                break;
            case "BORDER FORCE":
                try {
                    dashboard.selectBFTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.BF);
                    dashboard.goToDashboard();
                    dashboard.selectBFTeam();
                }
                break;
            case "BORDER FORCE (STAGE 2)":
                try {
                    dashboard.selectBF2Team();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.BF2);
                    dashboard.goToDashboard();
                    dashboard.selectBF2Team();
                }
                break;
            case "FOI TEAM":
                try {
                    dashboard.selectFOICreationTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.FOI);
                    dashboard.goToDashboard();
                    dashboard.selectFOICreationTeam();
                }
                break;
            case "POGR REGISTRATION":
                try {
                    dashboard.selectPOGRRegistrationTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.POGR);
                    dashboard.goToDashboard();
                    dashboard.selectPOGRRegistrationTeam();
                }
                break;
            case "POGR MY CASES":
                if (dashboard.getNumberOfCasesInWorkstackFromDashboardCard("My Cases") == 0) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.POGR);
                    confirmationScreens.goToCaseFromConfirmationScreen();
                    caseView.clickAllocateToMeLink();
                    dashboard.goToDashboard();
                }
                dashboard.selectMyCases();
                break;
            case "POGR REGISTRATION (STAGE 2)":
                try {
                    dashboard.selectPOGR2RegistrationTeam();
                } catch (NoSuchElementException e) {
                    createCase.createCSCaseOfTypeWithDocument(CaseType.POGR2);
                    dashboard.goToDashboard();
                    dashboard.selectPOGR2RegistrationTeam();
                }
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} workstack should contain only the expected columns")
    public void theWorkstackShouldContainOnlyTheExpectedColumns(String workstack) {
        workstacks.assertExpectedColumnsPresent(workstack);
    }

    @And("I record the highest priority cases in the workstack")
    public void iRecordTheHighestPriorityCasesInTheWorkstack() {
        workstacks.recordHighestPriorityCases();
    }

    @Then("the case deadline should be highlighted {string}")
    public void theCaseDeadlineShouldBeHighlightedRed(String colour) {
        switch (colour.toUpperCase()) {
            case "YELLOW":
                workstacks.assertThatDeadlineHighlightedIsYellow();
                break;
            case "RED":
                workstacks.assertThatDeadlineHighlightedIsRed();
                break;
            default:
                pendingStep(colour + " is not defined within " + getMethodName());
        }
    }

    @Then("the overdue contribution request should be highlighted in red")
    public void theOverdueContributionRequestShouldBeHighlightedInRed() {
        workstacks.assertOverdueContributionRequestIsHighlighted();
    }

    @Then("the displayed contribution request status of the case should be correct")
    public void theDisplayedContributionRequestStatusOfTheCaseShouldBeCorrect() {
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Contributions", sessionVariableCalled("expectedContributionRequestStatus"));
    }

    @And("I record the number of cases currently in the {string} workstack, and how many of those are unallocated")
    public void iRecordTheNumberOfCasesCurrentlyInTheTriageWorkstackAndHowManyOfThoseAreUnassigned(String teamName) {
        setSessionVariable("totalOfCases").to(dashboard.getNumberOfCasesInWorkstackFromDashboardCard(teamName));
        setSessionVariable("totalOfUnallocatedCases").to(dashboard.getNumberOfUnallocatedCasesInWorkstackFromDashboardCard(teamName));
    }

    @Then("the number of cases in the {string} workstack should not have increased")
    public void theNumberOfCasesInTheTriageWorkstackShouldNotHaveIncreased(String teamName) {
        int previousTotal = sessionVariableCalled("totalOfCases");
        int currentTotal = dashboard.getNumberOfCasesInWorkstackFromDashboardCard(teamName);
        assertThat(currentTotal == previousTotal, is(true));
    }

    @And("the number of unassigned cases in the {string} workstack should not have increased")
    public void theNumberOfUnassignedCasesInTheTriageWorkstackShouldNotHaveIncreased(String teamName) {
        int previousTotal = sessionVariableCalled("totalOfUnallocatedCases");
        int currentTotal = dashboard.getNumberOfUnallocatedCasesInWorkstackFromDashboardCard(teamName);
        assertThat(currentTotal == previousTotal, is(true));
    }

    @And("I shouldn't be able to see the case in the {string} workstack")
    public void iShouldnTBeAbleToSeeTheCaseInTheTriageWorkstack(String teamName) {
        dashboard.selectWorkstackByTeamName(teamName);
        workstacks.assertVisibilityOfCaseReference(false);
    }

    @Then("the rejected column of the case in the {string} workstack should display rejected by {string}")
    public void theRejectedColumnOfTheCaseInTheWorkstackShouldDisplayRejectedBy(String workstack, String rejectionStage) {
        dashboard.goToDashboard();
        dashboard.selectWorkstackByTeamName(workstack);
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Rejected", rejectionStage);
    }

    @And("the {string} workstack should display a HS symbol next to the case reference")
    public void theWorkstackShouldDisplayAHSSymbolNextToTheCaseReference(String workstack) {
        dashboard.selectWorkstackByTeamName(workstack);
        workstacks.assertHomeSecretarySymbolVisibleForCase(getCurrentCaseReference());
    }

    @And("the teams workstack should display the new deadline date for the case")
    public void theTeamsWorkstackShouldDisplayTheNewDeadlineDateForTheCase() {
        dashboard.goToDashboard();
        dashboard.selectWorkstackByTeamName("FOI Creation");
        workstacks.filterByCurrentCaseReference();
//        workstacks.assertDeadlineIsCorrect();
    }

    @Then("I should be able to tell that the case has an overdue contribution")
    public void theUserShouldBeAbleToTellThatTheCaseHasAnOverdueContribution() {
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Next due contribution date", "Overdue");
    }

    @Then("I should be able to tell when the contribution request is due")
    public void theUserShouldBeAbleToTellWhenTheContributionRequestIsDue() {
        String expectedValue = sessionVariableCalled("contributionDueDate");
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Next due contribution date", expectedValue);
    }

    @Then("the I can see the new transfer deadline displayed as the cases deadline")
    public void theICanSeeTheNewTransferDeadlineDisplayedAsTheCasesDeadline() {
        String expectedValue = sessionVariableCalled("transferDueDate");
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Deadline", expectedValue);
    }

    @And("I should be able to tell that the case has been extended")
    public void iShouldBeAbleToTellThatTheCaseHasBeenExtended() {
        workstacks.waitForWorkstackToLoad();
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Extended", "Yes");
    }

    @Then("the QA (Secretariat Clearance Request) due date should be displayed in the Current Stage column")
    public void thQASecretariatClearanceRequestDueDateShouldBeDisplayedInTheCurrentStageColumn() {
        workstacks.assertSecretariatClearanceRequestedDueDate();
    }

    @Then("an overdue Secretariat Clearance Request is highlighted in red")
    public void anOverdueSecretariatClearanceRequestIsHighlightedInRed() throws ParseException {
        workstacks.assertOverdueSecretariatClearanceRequestIsHighlighted();
    }

    @And("I enter a random team workstack from the dashboard")
    public void iEnterARandomTeamWorkstackFromTheDashboard() {
        dashboard.selectARandomWorkstack();
        workstacks.waitForWorkstackToLoad();
    }

    @When("I select a Workflow/Stage filter card")
    public void iSelectACaseTypeFilterCard() {
        workstacks.selectAFilterCard();
    }

    @Then("only cases of/at that Workflow/Stage should be displayed in the workstack")
    public void onlyCasesOfThatWorkflowShouldBeDisplayedInTheWorkstack() {
        workstacks.assertCaseTotalIs(Integer.parseInt(sessionVariableCalled("filterCardCaseTotal")));
    }

    @And("the deadline of the case should be replaced with (the word ){string} in the {string} workstack")
    public void theDeadlineOfTheCaseShouldBeReplacedWithTheWordInTheWorkstack(String replacementValue, String teamName) {
        dashboard.goToDashboard();
        dashboard.selectWorkstackByTeamName(teamName);
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Deadline", replacementValue);
    }

    @And("the deadline of the case should be replaced with (the word ){string} in the My Cases workstack")
    public void theDeadlineOfTheCaseShouldBeReplacedWithTheWordInTheMyCasesWorkstack(String replacementValue) {
        dashboard.goToDashboard();
        dashboard.selectMyCases();
        workstacks.assertSpecifiedColumnContainsValueForCurrentCase("Deadline", replacementValue);
    }
}