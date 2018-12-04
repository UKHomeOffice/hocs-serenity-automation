package com.hocs.test.glue;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.forms.TestForm;
import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.WebDriver;


public class TeamQueueStepDefs {

    @Given("^$")
    public void someMethod() {
    }

    // Set X to integer variable per case or test
    @When("^I have X cases in my teamqueue$")
    public void iHaveSomeCasesInMyTeamqueue() {
    }

    //Set X to integer variable per test or case
    @When("^I have X cases in myworkqueue$")
    public void iHaveSomeCasesInMyWorkqueue() {}

    @When("^I assign them to myself$")
    public void assignCasesToMyself() {}

    @When("^I enter <SomeFilter> into the Filter$")
    public void enterStringIntoFilter() {
    }

    @When("^I enter <TOPIC> into the filter$")
    public void sendKeysTopicToFilter() {
    }

    @When("^I enter <Primary Correspondent> into the filter$")
    public void sendKeysPrimaryCorrespondent() {}

    @When("^I select multiple unassigned cases$")
    public void selectMultipleUnassignedCases() {}

    @When("^I select multiple cases assigned to <anotherUser>$")
    public void selectMultipleAssignedToAnotherUser() {}

    @When("^I assign them to <anotherUser>$")
    public void assignCasesToAnotherUser() {}

    @When("^I select the reference number$")
    public void selectTheReferenceNumber() {}

    @When("^I assign int cases to <anotherUser>$")
    public void assignNumOfCasesToAnotherUser() {}

    @And("^$")
    public void someMethod2() {
    }


    @Then("^The total of all cases per case type in the Workflowqueue will be equal to X$")
    public void assertTotalWorkflowQueue() {
    }

    @Then("^My teamqueue should display 1 overdue case$")
    public void assertTeamQueueShows1Overdue() {
    }

    @Then("^My workflowqueue should display 1 overdue case for that <casetype>$")
    public void assertWorkflowQueuePerCaseType() {
    }

    @Then("^The unassigned cases should be equal to X$")
    public void assertUnassignedCases() {
    }

    @Then("^Columns <columns> should be visible$")
    public void assertColumnsAreVisible() {
    }

    @Then("^The cases are assigned to <anotherUser>$")
    public void assertCasesAssignedToAnotherUser() {
    }

    @Then("^The cases are assigned to me$")
    public void assertTheCasesAreAssignedToMe() {
    }

    @Then("^USERS column should only represent Unassigned$")
    public void assertUsersColumnOnlyRepresentsUnassigned() {}

    @Then("^USERS column should only represent Teammate_Name$")
    //USERS column should only represent Unassigned
    public void assertUsersColumnOnlyRepresentsSomeString() {
        // Assert that "enteredString" is exactly equal to
        // All Strings in array from table - 'Users'
    }

    @Then("^The CASETYPE column should only represent someString")
    //The CASETYPE column should only represent <CaseType>
    public void assertCasetypeColumnOnlyDisplaysTestedString() {
    }

    @Then("^I will be taken to the casework of that case at the relevant stage$")
    public void assertIAmTakenToTheCasesWork() {}

    @Then("^The case with CaseReference is displayed$")
    public void assertCaseReferenceIsDisplayed() {
    }

    @Then("^a Breadcrumb represents the 'team' page which was navigated to$")
    public void assertABreadcrumbIsDisplayed() {
    }

    @Then("^the results should include cases from outside the 20 visible results$")
    public void assertResultsNotFromFirst20() {
    }

    @Then("^STAGE column should only represent <Stage>$")
    public void assertColumnOnlyRepresentsColumn() {}

    @Then("^These cases are added to the total assigned to me on the 'team' page$")
    public void assertTotalAssignedToMeOnTeamsPage() {}

    @Then("^These cases are added to the total assigned to me on the 'home' page myworkqueue$")
    public void assertTotalAssignedToMyWorkqueue() {}

    @Then("^These cases are not added to the total assigned to me on the 'home' page$")
    public void assertCasesNotAddedToAssignedToMeHome() { }

    @Then("^These cases are not added to the total assigned to me on the 'team' page$")
    public void assertCasesNotAddedToAssignedToMeTeam() { }

    @Then("^<anotherUser2> can see the cases are assigned to them on the 'home' page$")
    public void assertCasesAreNotAssignedToAnotherUserHome() { }

    @Then("^<anotherUser2> can see the cases are assigned to them on the 'team' page$")
    public void assertCasesAreNotAssignedToAnotherUserTeam() { }

    /*@Then("^I should only see 20 results$")
    public void iShouldOnlySeeXResults(Int results) {
    }*/

}
