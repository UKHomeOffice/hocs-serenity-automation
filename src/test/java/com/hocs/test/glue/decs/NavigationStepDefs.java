package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Search;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationStepDefs extends BasePage {

    CreateCase createCase;

    DataInput dataInput;

    Dashboard dashboard;

    Correspondents correspondents;

    Search search;

    CaseView caseView;

    SummaryTab summaryTab;

    Workstacks workstacks;

    @When("I navigate to the {string}( page)")
    public void iNavigateToThePage(String hocsPage) {
        switch (hocsPage.toUpperCase()) {
            case "DASHBOARD":
                dashboard.goToDashboard();
                break;
            case "CREATE SINGLE CASE":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                break;
            case "CREATE BULK CASES":
                dashboard.selectCreateBulkCasesLinkFromMenuBar();
                break;
            case "SEARCH":
                dashboard.selectSearchLinkFromMenuBar();
                search.waitForSearchCriteriaPage();
                break;
            default:
                pendingStep(hocsPage + " is not defined within " + getMethodName());
        }
    }

    @And ("I (click to )view( the case/cases/claim in) the {string} workstack")
    public void iClickToViewTheWorkstack(String workstackIdentifier) {
        if (!dashboard.onDashboard()) {
            dashboard.goToDashboard();
        }
        if (workstackIdentifier.equalsIgnoreCase("My Cases")) {
            dashboard.selectMyCases();
        } else {
            dashboard.selectWorkstackByTeamName(workstackIdentifier);
        }
        workstacks.waitForWorkstackToLoad();
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

    @Then("I am returned to the dashboard")
    public void iAmReturnedToTheDashboard() {
        dashboard.assertAtDashboard();
    }

    @Then("the case should be loaded")
    public void theCaseShouldBeLoaded() {
        caseView.currentCaseIsLoaded();
    }

    @And("I view the case/claim whilst not being the current owner")
    public void iViewTheCaseWhilstNotBeingTheCurrentOwner() {
        summaryTab.selectSummaryTab();
        String currentTeam = summaryTab.getSummaryTabValueForGivenHeader("Team");
        dashboard.goToDashboard();
        dashboard.selectWorkstackByTeamName(currentTeam);
        workstacks.unallocateSelectedCase(getCurrentCaseReference());
        workstacks.selectSpecificCaseReferenceLink(getCurrentCaseReference());
    }

    @Then("I should be returned to the dashboard")
    public void iShouldBeReturnedToTheDashboard() {
        dashboard.assertAtDashboard();
    }
}
