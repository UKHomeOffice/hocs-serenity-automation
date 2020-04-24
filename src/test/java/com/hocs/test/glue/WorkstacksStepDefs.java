package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.Workstacks;

import config.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WorkstacksStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    CreateCase createCase;

    CreateCase_SuccessPage createCaseSuccessPage;

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

    @Then("the case should be added to my workstack")
    public void assertThatCaseHasBeenAddedToMyWorkstack() {
        safeClickOn(homepage.myCases);
        workstacks.assertCaseReferenceIsVisible();
    }

    @When("I enter the Case Reference type {string} into the filter")
    public void enterCaseReferenceType(String caseReferenceType) {
        safeClickOn(workstacks.selectWorkstackFilter);
        switch (caseReferenceType.toUpperCase()) {
            case "MIN":
                typeInto(workstacks.selectWorkstackFilter, caseReferenceType);
                break;
            case "DTEN":
                typeInto(workstacks.selectWorkstackFilter, caseReferenceType);
                break;
            case "TRO":
                typeInto(workstacks.selectWorkstackFilter, caseReferenceType);
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
        safeClickOn(homepage.performanceProcessTeam);
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
        safeClickOn(workstacks.selectWorkstackFilter);
        typeInto(workstacks.selectWorkstackFilter, currentStage.toUpperCase());
    }

    @Then("all cases should be allocated to the user {string}")
    public void assertAllCasesAssignedToAllocatedUser(Users user) {
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
        createCase.createDCUMinSingleCase();
        homepage.goHome();
        safeClickOn(homepage.performanceProcessTeam);
        workstacks.filterByCurrentCaseReference();
        waitABit(500);
    }

    @Then("the case should be allocated to me")
    public void theCaseShouldBeAllocatedToMe() {
        workstacks.assertOwnerIs(Users.EAMON);
    }

    @When("I assign the current case number to {string}")
    public void iAssignTheCurrentCaseNumberToAnotherUser(String user) {
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.selectAllocationUserByVisibleText(Users.valueOf(user).getAllocationText());
    }

    @Then("the owner field should display {string}")
    public void theOwnerFieldShouldDisplayTheSelectedUser(String user) {
        workstacks.assertAssignedUser(Users.valueOf(user));
    }

    @When("I create three cases, and view them in performance and process workstack")
    public void createThreeCasesAndReassign() {
        createCase.createDCUMinSingleCase();
        homepage.goHome();
        waitABit(500);
        createCase.createDCUMinSingleCase();
        homepage.goHome();
        waitABit(500);
        createCase.createDCUMinSingleCase();
        homepage.goHome();
        waitABit(500);
        safeClickOn(homepage.performanceProcessTeam);
    }

    @Then("I assign these three cases to {string}")
    public void assignThreeCasesToUser(String user) {
        workstacks.allocateThreeCasesCreated(Users.valueOf(user));
    }

    @Then("I check that the three cases created have been correctly assigned to {string}")
    public void checkThreeCasesProperlyReassigned(String user) {
        workstacks.assertAssignedUserOnThreeCases(Users.valueOf(user));
    }

    @When("I create three cases, and assign them to {string}")
    public void iCreateThreeCasesAndAssignToUser(String user) {
        createCase.createDCUMinSingleCase();
        createCaseSuccessPage.newCaseReference.click();
        workstacks.caseDetailsSelectAllocationUserByVisibleText(Users.valueOf(user).getAllocationText());
        homepage.goHome();
        createCase.createDCUMinSingleCase();
        createCaseSuccessPage.newCaseReference.click();
        workstacks.caseDetailsSelectAllocationUserByVisibleText(Users.valueOf(user).getAllocationText());
        homepage.goHome();
        createCase.createDCUMinSingleCase();
        createCaseSuccessPage.newCaseReference.click();
        workstacks.caseDetailsSelectAllocationUserByVisibleText(Users.valueOf(user).getAllocationText());
        homepage.goHome();
    }

    @Then("I view these cases in Performance and Process workstack, and unallocate from {string}")
    public void iUnallocateThreeCasesCreated(String user) {
        safeClickOn(homepage.performanceProcessTeam);
        workstacks.unallocateThreeCasesFromSelectedUser(Users.valueOf(user));
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

    @When("I enter the Performance and Process team workstack and narrow down the visible cases using the {string} filter card")
    public void iNarrowDownTheVisibleCasesInThePerformanceAndProcessTeamWorkstackUsingTheFilterCard(String caseType) {
        homepage.selectPerformanceProcessTeam();
        switch (caseType.toUpperCase()) {
            case "MIN":
                workstacks.clickDCUMINFilterCard();
                break;
            case "TRO":
                workstacks.clickDCUTROFilterCard();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
                break;
        }
    }

    @When("I enter the Transfers and No10 team workstack and narrow down the visible cases using the TRO filter card")
    public void iNarrowDownTheVisibleCasesInTheTransfersAndNoTeamWorkstackUsingTheFilterCard() {
        safeClickOn(homepage.transferN10Team);
        workstacks.clickDCUTENFilterCard();
    }

    @Then("the created case should be visible in the workstack")
    public void theCreatedCaseShouldBeVisibleInTheWorkstack() {
        workstacks.assertCaseReferenceIsVisible();
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
                break;
        }
    }
}
