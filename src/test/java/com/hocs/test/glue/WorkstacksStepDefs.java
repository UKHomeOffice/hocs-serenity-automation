package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;

import config.Users;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WorkstacksStepDefs extends Page {

    Homepage homepage;

    Workstacks workstacks;

    CreateCase createCase;

    @Given("^I allocate the case to myself$")
    public void allocateCaseToMyself() {
        clickOn(workstacks.allocateCheckboxCaseToMeButton);
    }

    @When("^I unallocate the case from myself$")
    public void unallocateCase() {
        clickOn(workstacks.unallocateFromMeButton);
    }

    @When("^I select the check box against the case and allocate it to myself$")
    public void allocateCaseUsingCheckbox() {
        workstacks.clickCheckboxRelevantToCaseReference();
        clickOn(workstacks.allocateCheckboxCaseToMeButton);
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
            case "DTEN":
                clickOn(workstacks.dcuN10FilterCard);
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

    @Then("^all cases should be allocated to the user \"([^\"]*)\"$")
    public void assertAllCasesAssignedToAllocatedUser(Users user) {
        workstacks.assertAllAllocatedUsersAre(user);
    }

    @And("^I select a case and unallocate it from myself$")
    public void iSelectACaseAndUnallocateItFromMyself() {
        workstacks.clickCheckboxRelevantToCaseReference();
        clickOn(workstacks.unallocateFromMeButton);
    }

    @And("^I filter the workstack using the current cases reference$")
    public void iFilterTheWorkstackUsingTheCurrentCasesReference() {
        workstacks.filterByCurrentCaseReference();
    }

    @And("^I create a new case and view it in the Performance and Process team workstack$")
    public void iCreateANewCaseAndViewItInThePerformanceAndProcessTeamWorkstack() {
        createCase.createDCUMinSingleCase();
        homepage.goHome();
        clickOn(homepage.performanceProcessTeam);
        workstacks.filterByCurrentCaseReference();
    }

    @Then("^the case should be allocated to me$")
    public void theCaseShouldBeAllocatedToMe() {
        workstacks.assertOwnerIs(Users.EAMON);
    }
}
