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

import static net.serenitybdd.core.Serenity.pendingStep;

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

    @When("^I unallocate the case from myself")
    public void unallocateCase() {
        page.clickOn(homepage.performanceProcessTeam);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(homepage.home);
        page.clickOn(homepage.myCases);
        workstacks.clickCheckboxRelevantToCaseReference();
        page.clickOn(workstacks.unallocateFromMeButton);
    }

    @Then("^the case should not be visible in my workstack$")
    public void assertThatCaseHasBeenUnallocatedFromMe() {
        workstacks.assertCaseReferenceIsNotVisible();
    }

    @When("^I unallocate all the cases from myself$")
    public void unallocateAllCasesFromMyCases() {
        workstacks.clickAllWorkstackCheckboxes();
        page.clickOn(workstacks.unallocateFromMeButton);
    }

    @Then("^no cases should be visible in my workstack$")
    public void assertThatAllCasesHaveBeenUnallocatedFromMyWorkstack() {
        workstacks.assertThatThereAreNoCasesInWorkstack();
    }

    @When("^I select the check box against a case and allocate it to myself$")
    public void allocateCaseUsingCheckbox() {
        page.clickOn(homepage.performanceProcessTeam);
        workstacks.clickCheckboxRelevantToCaseReference();
        page.clickOn(workstacks.allocateCheckboxCaseToMeButton);
        page.clickOn(workstacks.home);
    }

    @When("^I unallocate all cases from the users in the team$")
    public void unallocatedAllCasesFromTeamwork() {
        workstacks.clickAllWorkstackCheckboxes();
        page.clickOn(workstacks.unallocateFromMeButton);
    }


    @Then("^the case should be added to my workstack$")
    public void assertThatCaseHasBeenAddedToMyWorkstack() {
        page.clickOn(homepage.myCases);
        workstacks.assertCaseReferenceIsVisible();
    }

    @When("^I enter the Case Reference type \"([^\"]*)\" into the filter$")
    public void enterCaseReferenceType(String caseReferenceType) {
        page.clickOn(workstacks.selectWorkstackFilter);
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

    @Then("^the cases should be filtered by the \"([^\"]*)\" Case Reference$")
    public void assertCasesAreFilteredByCaseReference(String caseReference) {
        workstacks.assertCasesAreFilteredByRef(caseReference);
    }

    @When("^I click the \"([^\"]*)\" case type filter card$")
    public void clickCaseTypeFilterCard(String caseTypeCard) {
        page.clickOn(homepage.performanceProcessTeam);
        switch (caseTypeCard.toUpperCase()) {
            case "MIN":
                page.clickOn(workstacks.dcuMINFilterCard);
                break;
            case "TRO":
                page.clickOn(workstacks.dcuTROFilterCard);
                break;
            default:
                pendingStep(caseTypeCard + " is not defined within " + getMethodName());
        }
    }

    @Then("^the cases should be filtered by the \"([^\"]*)\" Current Stage")
    public void assertCasesAreFilteredByCurrentStage(String currentStage) {
        workstacks.assertCasesAreFilteredByStage(currentStage);
    }

    @When("^I enter the Current Stage \"([^\"]*)\" into the filter$")
    public void enterCurrentStage(String currentStage) {
        page.clickOn(workstacks.selectWorkstackFilter);
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
        page.clickOn(workstacks.dashboardBreadcrumb);
    }

    @Then("^I should be taken back to the homepage$")
    public void assertThatDashboardBreadcrumbTakesUserToHomepage() {
        homepage.assertHomePageTitle();
    }

    @When("^I click the team breadcrumb$")
    public void clickTeamBreadcrumbOnTeamWorkstack() {
        page.clickOn(workstacks.dcuMINFilterCard);
        page.clickOn(workstacks.teamBreadcrumb);
    }

    @Then("^I should be taken to the team page of the team workstack$")
    public void assertThatTeamBreadcrumbTakesUserToTeamPage() {
        workstacks.assertThatDCUMinFilterCardIsVisible();
    }

    @When("^I click the workflow breadcrumb$")
    public void clickWorkflowBreadcrumbOnTeamWorkstack() {
        page.clickOn(workstacks.dcuMINFilterCard);
        page.clickOn(workstacks.dataInputFilterCard);
        page.clickOn(workstacks.workflowBreadcrumb);
    }

    @Then("^I should be taken to workflow page of the team workstack$")
    public void assertThatWorkflowBreadcrumbTakesUserToWorkflowPage() {
        workstacks.assertThatDataInputFilterCardIsVisible();
    }

}
