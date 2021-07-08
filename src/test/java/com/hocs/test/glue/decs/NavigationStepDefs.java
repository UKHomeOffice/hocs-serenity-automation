package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.Search;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.fetchExistingDCUCases;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationStepDefs extends BasePage {

    CreateCase createCase;

    DataInput dataInput;

    Dashboard dashboard;

    fetchExistingDCUCases fetchExistingDCUCases;

    AddCorrespondent initialDraftRecordCorrespondentDetails;

    Search search;

    @When("I navigate to the {string}( page)")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "DASHBOARD":
                goToDECSDashboard();
                break;
            case "CREATE SINGLE CASE":
                safeClickOn(dashboard.createSingleCase);
                break;
            case "CREATE BULK CASES":
                safeClickOn(dashboard.createBulkCases);
                break;
            case "SEARCH":
                safeClickOn(dashboard.searchPage);
                search.waitForSearchCriteriaPage();
                break;
            default:
                pendingStep(hocsPage + " is not defined within " + getMethodName());
        }
    }

    @And ("I click to view( the case in) the {string} workstack")
    public void iClickToViewTheWorkstack(String teamName) {
        if (!dashboard.myCases.isCurrentlyVisible()) {
            goToDECSDashboard();
        }
        dashboard.selectWorkstackByTeamName(teamName);
    }

    @And("I load the current case")
    public void loadCase() {
        dashboard.getCurrentCase();
    }

    @And("I load and claim the current case")
    public void loadAndClaimCase() {
        dashboard.getAndClaimCurrentCase();
    }

    @And("I claim the current case")
    public void claimCase() {
        dashboard.claimCurrentCase();
    }

    @Then("I am returned to my home screen")
    public void returnedToHomeScreen() {
        dashboard.assertElementIsDisplayed(dashboard.createSingleCase);
    }

    @When("I get a {string} case at {string} stage")
    public void getMeACase(String caseType, String stage) {
        fetchExistingDCUCases.giveMeACase(caseType, stage);
        setCaseReferenceFromAssignedCase();
    }

    @Then("I am taken to the {string} page")
    public void iAmTakenToThePage(String pageName) {
        switch (pageName.toUpperCase()) {
            case "CREATE SINGLE CASE":
                createCase.assertPageTitle();
                break;
            case "HOME":
                dashboard.assertAtDashboard();
                break;
            case "RECORD CORRESPONDENT DETAILS":
                initialDraftRecordCorrespondentDetails.assertPageTitle();
                break;
            case "DATA INPUT":
                dataInput.assertPageTitle();
                break;
            default:
                pendingStep(pageName + " is not defined within " + getMethodName());
        }
        System.out.println("I have been taken to " + pageName);
    }
}
