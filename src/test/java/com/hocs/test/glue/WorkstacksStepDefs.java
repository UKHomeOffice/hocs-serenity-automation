package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.core.Serenity.setSessionVariable;

public class WorkstacksStepDefs {

    @Managed
    private
    WebDriver driver; // set webdriver to var driver

    @Steps(shared = true)
    NavigationStepDefs navigationStepDefs;

    private LoginPage loginpage; //require loginpage file features not sure why this is under @steps instead of @managed

    private Page page; //more pages

    Homepage homepage;

    Workstacks workstacks;

    SuccessfulCaseCreation successfulCaseCreation;

    @When("^I allocate the case to myself")
    public void allocateToMe() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
    }

    @Then("^they allocate the case to themself$")
    public void allocateCaseToUser() {
        workstacks.clickAllocateToMeButton();
    }

    @When("^I unallocate the case from myself")
    public void unallocateCase() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.clickUnallocateCasesButton();

    }

    @When("^I select the check box against a case and allocate it to myself$")
    public void allocateCaseUsingCheckbox() {
        homepage.selectPerformanceProcessTeam();
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.clickAllocatedSelectedToMeButton();
        workstacks.goHome();
    }


    @Then("^the case should be added to my workstack$")
    public void assertThatCaseIsAllocatedToMe() {
        homepage.selectMyCases();
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("^the case should not be visible in my workstack$")
    public void assertThatCaseHasBeenUnallocatedFromMe() {
        workstacks.assertCaseReferenceIsNotVisible();

    }

    @When("^I enter the Case Reference type \"([^\"]*)\" into the filter$")
    public void enterCaseReferenceType(String caseReferenceType) {
        workstacks.selectWorkstackFilter.click();
        switch (caseReferenceType.toUpperCase()) {
            case "MIN":
                workstacks.selectWorkstackFilter.sendKeys(caseReferenceType);
                break;
            case "DTEN":
                workstacks.selectWorkstackFilter.sendKeys(caseReferenceType);
                break;
            case "TRO":
                workstacks.selectWorkstackFilter.sendKeys(caseReferenceType);
                break;
            default:
                pendingStep(caseReferenceType + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the \"([^\"]*)\" case type filter card$")
    public void clickCaseTypeFilterCard(String caseTypeCard) {
        homepage.selectPerformanceProcessTeam();
        switch (caseTypeCard.toUpperCase()) {
            case "MIN":
                workstacks.clickMINFilterCard();
                break;
            case "TRO":
                workstacks.clickTROFilterCard();
                break;
            default:
                System.out.println(caseTypeCard
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                caseTypeCard = null;
                assumeNotNull(caseTypeCard);
        }
    }

    @When("^I enter the Current Stage \"([^\"]*)\" into the filter$")
    public void enterCurrentStage(String currentStage) {
        workstacks.selectWorkstackFilter.click();
        switch (currentStage.toUpperCase()) {
            case "DATA INPUT":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            case "MARK UP":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            case "INPUT DRAFT":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            case "QA RESPONSE":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            case "PRIVATE OFFICE APPROVAL":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            case "MINISTERIAL SIGN OFF":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            case "DISPATCH":
                workstacks.selectWorkstackFilter.sendKeys(currentStage);
                break;
            default:
                pendingStep(currentStage + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the dashboard breadcrumb$")
    public void clickDashboardBreadcrumbOnTeamWorkstack() {
        workstacks.dashboardBreadcrumb.click();
    }

    @Then("^I should be taken back to the homepage$")
    public void assertThatDashboardBreadcrumbTakesUserToHomepage() {
        homepage.assertPageTitle();
    }

    @When("^I click the team breadcrumb$")
    public void clickTeamBreadcrumbOnTeamWorkstack() {
        workstacks.clickMINFilterCard();
        workstacks.clickTeamBreadcrumb();
    }

    @Then("^I should be taken to the team page of the team workstack$")
    public void assertThatTeamBreadcrumbTakesUserToTeamPage() {
        workstacks.assertThatDCUMinFilterCardIsVisible();
    }

    @When("^I click the workflow breadcrumb$")
    public void clickWorkflowBreadcrumbOnTeamWorkstack() {
        workstacks.clickMINFilterCard();
        workstacks.clickDataInputFilterCard();
        workstacks.clickWorkflowBreadcrumb();
    }

    @Then("^I should be taken to workflow page of the team workstack$")
    public void assertThatWorkflowBreadcrumbTakesUserToWorkflowPage() {
        workstacks.assertThatDataInputFilterCardIsVisible();
    }

    @Then("^the cases should be filtered by the \"([^\"]*)\" Case Reference$")
    public void assertCasesAreFilteredByCaseReference(String caseReference) {
        workstacks.assertCasesAreFilteredByRef(caseReference);
    }

    @Then("^the cases should be filtered by the \"([^\"]*)\" Current Stage")
    public void assertCasesAreFilteredByCurrentStage(String currentStage) {
        workstacks.assertCasesAreFilteredByStage(currentStage);

    }

}
