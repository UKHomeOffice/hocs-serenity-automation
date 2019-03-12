package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

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

    @When("^the current user allocates the case to themself$")
    public void allocateToMe() {
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
    }

    @Then("^they allocate the case to themself$")
    public void allocateCaseToUser() {
        workstacks.clickAllocateToMeButton();
    }

    @When("^they unallocate the case from themself")
    public void unallocateCase() {
        homepage.selectMyCases();
        workstacks.clickCheckboxRelevantToCaseReference();
        workstacks.clickUnallocateCasesButton();

    }

    @When("^they select the check box against the case$")
    public void selectCaseCheckbox() {
        homepage.firstStageFindMyCase();
        workstacks.clickCheckboxRelevantToCaseReference();
    }


    @Then("^The case is added to the current user's cases$")
    public void assertThatCaseIsAllocatedToMe() {
        homepage.selectMyCases();
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("^The case is not visible in the user's cases$")
    public void assertThatCaseHasBeenUnallocatedFromMe() {
        workstacks.assertCaseReferenceIsNotVisible();

    }

    @When("^They enter Case Reference type \"([^\"]*)\" into the filter$")
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

    @When("^They enter Current Stage \"([^\"]*)\" into the filter$")
    public void enterCurrentStage (String currentStage) {
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

    @Then("^The cases should be filtered by the \"([^\"]*)\" Case Reference$")
    public void assertCasesAreFilteredByCaseReference(String caseReference){
        workstacks.assertCasesAreFilteredByRef(caseReference);
    }

    @Then("^The cases should be filtered by the \"([^\"]*)\" Current Stage")
    public void assertCasesAreFilteredByCurrentStage(String currentStage){
        workstacks.assertCasesAreFilteredByStage(currentStage);

    }

}
