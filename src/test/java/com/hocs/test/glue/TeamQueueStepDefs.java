package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.teamqueue.Teamqueue;
import com.hocs.test.pages.data_input.DataInput;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.serenitybdd.core.pages.WebElementFacade;


public class TeamQueueStepDefs {

    Teamqueue teamqueue;

    AddDocuments addDocuments;

    CreateCase createCase;

    Homepage homepage;

    DataInput dataInput;

    Page page;

    // Set X to integer variable per case or test
    @When("^I have X cases in my teamqueue$")
    public void iHaveSomeCasesInMyTeamqueue() {
        teamqueue.iHaveCasesInMyTeamqueue();
    }

    //Set X to integer variable per test or case
    @When("^I have X cases in myworkqueue$")
    public void iHaveSomeCasesInMyWorkqueue() {
        teamqueue.iHaveCasesInMyWorkqueue();
    }

    @When("^I assign them to myself$")
    public void assignCasesToMyself() {
        teamqueue.assignCasesToMe();
    }

    @When("^I enter <SomeFilter> into the Filter$")
    public void enterStringIntoFilter() {
        teamqueue.sendStringToFilter();
    }

    @When("^I enter <TOPIC> into the filter$")
    public void sendKeysTopicToFilter() {
        teamqueue.sendStringTopicToFilter();
    }

    @When("^I enter <Primary Correspondent> into the filter$")
    public void sendKeysPrimaryCorrespondent() {
        teamqueue.sendStringPrimeCorresToFilter();
    }

    @When("^I select multiple unassigned cases$")
    public void selectMultipleUnassignedCases() {
        teamqueue.selectManyUnassignedCases();
    }

    @When("^I select multiple cases assigned to <anotherUser>$")
    public void selectMultipleAssignedToAnotherUser() {
        teamqueue.selectManyAssignedToAnotherUser();
    }

    @When("^I assign them to <anotherUser>$")
    public void assignCasesToAnotherUser() {
        teamqueue.assignedManyToAnotherUser();
    }

    @When("^I select the reference number$")
    public void selectTheReferenceNumber() {
        teamqueue.iSelectTheReferenceNumber();
    }

    @When("^I assign int cases to <anotherUser>$")
    public void assignNumOfCasesToAnotherUser() {
        teamqueue.assignTheseCasesToAnotherUser();
    }

  /*  @And("^$")
    public void someMethod2() {
        teamqueue.someOtherMethods2();
    } */


    @Then("^The total of all cases per case type in the Workflowqueue will be equal to X$")
    public void assertTotalWorkflowQueue() {
        teamqueue.assertWorkflowQueueTotal();
    }

    @Then("^My teamqueue should display 1 overdue case$")
    public void assertTeamQueueShows1Overdue() {
        teamqueue.assertTeamqueueDisplaysOverdue();
    }

    @Then("^My workflowqueue should display 1 overdue case for that <casetype>$")
    public void assertWorkflowQueuePerCaseType() {
        teamqueue.assertWorkflowQueuePerTypeOfCase();
    }

    @Then("^The unassigned cases should be equal to X$")
    public void assertUnassignedCases() {
        teamqueue.assertUnassignedAgainstX();
    }

    @Then("^Columns <columns> should be visible$")
    public void assertColumnsAreVisible() {
        teamqueue.assertColumnsAreReallyVisible();
    }

    @Then("^The cases are assigned to <anotherUser>$")
    public void assertCasesAssignedToAnotherUser() {
        teamqueue.assertCasesAreAssignedToAnotherUser();
    }

    @Then("^The cases are assigned to me$")
    public void assertTheCasesAreAssignedToMe() {
        teamqueue.assertTheseCasesAreAssignedToMe();
    }

    @Then("^USERS column should only represent Unassigned$")
    public void assertUsersColumnOnlyRepresentsUnassigned() {
        teamqueue.assertUsersColumnRepresentsOnlyUnassigned();
    }

    @Then("^USERS column should only represent Teammate_Name$")
    //USERS column should only represent Unassigned
    public void assertUsersColumnOnlyRepresentsSomeString() {
        teamqueue.assertUsersColumnOnlyRepresentsThisString();
        // Assert that "enteredString" is exactly equal to
        // All Strings in array from table - 'Users'
    }

    @Then("^The CASETYPE column should only represent someString")
    //The CASETYPE column should only represent <CaseType>
    public void assertCasetypeColumnOnlyDisplaysTestedString() {
        teamqueue.assertCasetypeColumnOnlyShowsTestedString();
    }

    @Then("^I will be taken to the casework of that case at the relevant stage$")
    public void assertIAmTakenToTheCasesWork() {
        teamqueue.assertIAmTakenToCasework();
    }

    @Then("^The case with CaseReference is displayed$")
    public void assertCaseReferenceIsDisplayed() {
        teamqueue.assertThisCaseReferenceIsDisplayed();
    }

    @Then("^a Breadcrumb represents the 'team' page which was navigated to$")
    public void assertABreadcrumbIsDisplayed() {
        teamqueue.assertBreadcrumbsAreDisplayed();
    }

    @Then("^the results should include cases from outside the 20 visible results$")
    public void assertResultsNotFromFirst20() {
        teamqueue.assertResultsNotFromFirst20();
    }

    @Then("^STAGE column should only represent <Stage>$")
    public void assertColumnOnlyRepresentsColumn() {
        teamqueue.assertColumnOnlyRepresentsThisColumn();
    }

    @Then("^These cases are added to the total assigned to me on the 'team' page$")
    public void assertTotalAssignedToMeOnTeamsPage() {
        teamqueue.assertTotalAssignedToMeTeamsPage();
    }

    @Then("^These cases are added to the total assigned to me on the 'home' page myworkqueue$")
    public void assertTotalAssignedToMyWorkqueue() {
        teamqueue.assertTotalAssignedToMeWorkqueue();
    }

    @Then("^These cases are not added to the total assigned to me on the 'home' page$")
    public void assertCasesNotAddedToAssignedToMeHome() {
        teamqueue.assertCasesNotAddedToAssignedMeHome();
    }

    @Then("^These cases are not added to the total assigned to me on the 'team' page$")
    public void assertCasesNotAddedToAssignedToMeTeam() {
        teamqueue.assertCasesNotAddedToAssignedMeTeam();
    }

    @Then("^<anotherUser2> can see the cases are assigned to them on the 'home' page$")
    public void assertCasesAreNotAssignedToAnotherUserHome() {
        teamqueue.assertCasesAreNotAssignedAnotherUserHome();
    }

    @Then("^<anotherUser2> can see the cases are assigned to them on the 'team' page$")
    public void assertCasesAreNotAssignedToAnotherUserTeam() {
        teamqueue.assertThatCasesAreNotAssignedToAnotherUserTeam();
    }

    @Then("^The case should no longer be visible in the teamqueue$")
    public void assertThatCaseInSessionVariableIsNotVisible() {
        dataInput.selectTeam1();
        teamqueue.assertCaseIsNotVisible();
    }

    /*@Then("^I should only see 20 results$")
    public void iShouldOnlySeeXResults(Int results) {
    }*/

}
