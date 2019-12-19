package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WorkstacksStepDefs extends Page {

    Homepage homepage;

    Workstacks workstacks;

    SuccessfulCaseCreation successfulCaseCreation;

    @Given("^I allocate the case to myself$")
    public void allocateCaseToMyself() {
        clickOn(workstacks.allocateToMeButton);
    }

    @When("^I unallocate the case from myself$")
    public void unallocateCase() {
        clickOn(homepage.performanceProcessTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        clickOn(workstacks.allocateToMeButton);
        clickOn(homepage.home);
        clickOn(homepage.myCases);
        workstacks.clickCheckboxRelevantToCaseReference();
        clickOn(workstacks.unallocateFromMeButton);
    }

    @When("^I click the back to dashboard button$")
    public void clickBackToDashboardButton() {
        clickOn(workstacks.backToDashboardButton);
    }

    @Then("^the case should not be visible in my workstack$")
    public void assertThatCaseHasBeenUnallocatedFromMe() {
        workstacks.assertCaseReferenceIsNotVisible();
    }

    @When("^I unallocate all the cases from myself$")
    public void unallocateAllCasesFromMyCases() {
        workstacks.unallocatedAllCases();
    }

    @Then("^no cases should be visible in my workstack$")
    public void assertThatAllCasesHaveBeenUnallocatedFromMyWorkstack() {
        workstacks.assertThatThereAreNoCasesInWorkstack();
    }

    @When("^I select the check box against a case and allocate it to myself$")
    public void allocateCaseUsingCheckbox() {
        clickOn(homepage.performanceProcessTeam);
        workstacks.clickCheckboxRelevantToCaseReference();
        clickOn(workstacks.allocateCheckboxCaseToMeButton);
        clickOn(workstacks.home);
    }

    @When("^I unallocate all cases from the users in the team$")
    public void unallocatedAllCasesFromTeamWorkstack() {
        workstacks.clickAllWorkstackCheckboxes();
        clickOn(workstacks.unallocateFromMeButton);
    }

    @When("^I allocate all cases to a single user$")
    public void allocateAllCasesInTeamworkWorkstack() {
        workstacks.clickAllWorkstackCheckboxes();
        workstacks.selectAllocationUserByVisibleText("Eamon Droko (eamon.droko@ten10.com)");
    }


    @Then("^the case should be added to my workstack$")
    public void assertThatCaseHasBeenAddedToMyWorkstack() {
        clickOn(homepage.myCases);
        workstacks.assertCaseReferenceIsVisible();
    }

    @When("^I enter the Case Reference type \"([^\"]*)\" into the filter$")
    public void enterCaseReferenceType(String caseReferenceType) {
        clickOn(workstacks.selectWorkstackFilter);
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

    @Then("^the cases should be filtered by the \"([^\"]*)\" Case Reference$")
    public void assertCasesAreFilteredByCaseReference(String caseReference) {
        workstacks.assertCasesAreFilteredByRef(caseReference);
    }

    @When("^I click the \"([^\"]*)\" case type filter card$")
    public void clickCaseTypeFilterCard(String caseTypeCard) {
        clickOn(homepage.performanceProcessTeam);
        switch (caseTypeCard.toUpperCase()) {
            case "MIN":
                clickOn(workstacks.dcuMINFilterCard);
                break;
            case "TRO":
                clickOn(workstacks.dcuTROFilterCard);
                break;
            default:
                pendingStep(caseTypeCard + " is not defined within " + getMethodName());
        }
    }

    @Then("^the cases should be filtered by the \"([^\"]*)\" Current Stage")
    public void assertCasesAreFilteredByCurrentStage(String currentStage) {
        workstacks.assertCasesAreFilteredByStage(currentStage);
    }

    @When("^I enter the current stage \"([^\"]*)\" into the filter$")
    public void enterCurrentStage(String currentStage) {
        clickOn(workstacks.selectWorkstackFilter);
        typeInto(workstacks.selectWorkstackFilter, currentStage.toUpperCase());
    }

    @When("^I click the dashboard breadcrumb$")
    public void clickDashboardBreadcrumbOnTeamWorkstack() {
        clickOn(workstacks.dashboardBreadcrumb);
    }

    @Then("^I should be taken back to the homepage$")
    public void assertThatDashboardBreadcrumbTakesUserToHomepage() {
        homepage.assertHomePageTitle();
    }

    @When("^I click the team breadcrumb$")
    public void clickTeamBreadcrumbOnTeamWorkstack() {
        clickOn(workstacks.dcuMINFilterCard);
        clickOn(workstacks.teamBreadcrumb);
    }

    @Then("^I should be taken to the team page of the team workstack$")
    public void assertThatTeamBreadcrumbTakesUserToTeamPage() {
        workstacks.assertThatDCUMinFilterCardIsVisible();
    }

    @When("^I click the workflow breadcrumb$")
    public void clickWorkflowBreadcrumbOnTeamWorkstack() {
        clickOn(workstacks.dcuMINFilterCard);
        clickOn(workstacks.dataInputFilterCard);
        clickOn(workstacks.workflowBreadcrumb);
    }

    @Then("^I should be taken to workflow page of the team workstack$")
    public void assertThatWorkflowBreadcrumbTakesUserToWorkflowPage() {
        workstacks.assertThatDataInputFilterCardIsVisible();
    }

    @Then("^all cases should be allocated to that user$")
    public void assertAllCasesAssignedToAllocatedUser() {
        workstacks.assertAllAllocatedUsers();
    }

    @Then("^the case should no longer be visible in the workstack$")
    public void assertThatCaseInSessionVariableIsNotVisible() {
        if (isElementDisplayed(homepage.performanceProcessTeam)) {
            homepage.selectPerformanceProcessTeam();
            workstacks.assertCaseIsNotVisible();
        } else {
            workstacks.assertCaseIsNotVisible();
        }
    }

    @Then("^the case should no longer be visible in the \"([^\"]*)\" workstack$")
    public void assertThatCaseHasBeenDispatchedPerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                break;
            case "DCU TRO":
                clickOn(homepage.animalsInScienceTeam);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        workstacks.assertCaseIsNotVisible();
    }

}
