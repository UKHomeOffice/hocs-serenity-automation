package com.hocs.test.glue.platform;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.platform.AddCorrespondent;
import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.CreateCase;
import com.hocs.test.pages.platform.Dashboard;
import com.hocs.test.pages.platform.LoginPage;
import com.hocs.test.pages.platform.Search;
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

    LoginPage loginPage;

    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "DASHBOARD":
                safeClickOn(dashboard.decsDashboardLink);
                waitForDashboard();
                break;
            case "CREATE SINGLE CASE":
                safeClickOn(dashboard.createSingleCaseLink);
                break;
            case "CREATE BULK CASES":
                safeClickOn(dashboard.createBulkCasesLink);
                break;
            case "SEARCH":
                dashboard.selectSearchLinkFromMenuBar();
                search.waitForSearchCriteriaPage();
                break;
            default:
                pendingStep(hocsPage + " is not defined within " + getMethodName());
        }
    }

    @And("I navigate to {string}")
    public void iNavigateTo(String platform) {
        loginPage.navigateToPlatform(platform);
    }

    @And ("I click to view( the case in) the {string} workstack")
    public void iClickToViewTheWorkstack(String workstackIdentifier) {
        if (!onDashboard()) {
            goToDECSDashboard();
        }
        if (workstackIdentifier.equalsIgnoreCase("My Cases")) {
            dashboard.selectMyCases();
        } else {
            dashboard.selectWorkstackByTeamName(workstackIdentifier);
        }
    }

    @And("I load the current case/claim")
    public void loadCase() {
        dashboard.getCurrentCase();
    }

    @And("I load and claim the current case/claim")
    public void loadAndClaimCase() {
        dashboard.getAndClaimCurrentCase();
    }

    @And("I claim the current case")
    public void claimCase() {
        dashboard.claimCurrentCase();
    }

    @Then("I am returned to my home screen")
    public void returnedToHomeScreen() {
        dashboard.assertElementIsDisplayed(dashboard.createSingleCaseLink);
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
