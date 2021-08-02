package com.hocs.test.glue.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Search;
import com.hocs.test.pages.wcs.WCSSearch;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class WCSSearchStepDefs extends BasePage {

    Dashboard dashboard;

    WCSSearch WCSSearch;

    Search search;

    @Then("the created {string} claim should be visible in the search results")
    public void theCreatedCaseShouldBeIncludedInTheSearchResults(String searchType) {
        switch (searchType.toUpperCase()) {
            case "CLAIMANT DOB":
                try {
                    WCSSearch.assertCaseIsVisibleInSearchResults();
                } catch (AssertionError e) {
                    try {
                        waitABit(5000);
                        dashboard.selectSearchLinkFromMenuBar();
                        search.waitForSearchCriteriaPage();
                        WCSSearch.searchByClaimantsDOB(getTodaysDate());
                        WCSSearch.assertCaseIsVisibleInSearchResults();
                    } catch (AssertionError e2) {
                        waitABit(5000);
                        dashboard.selectSearchLinkFromMenuBar();
                        search.waitForSearchCriteriaPage();
                        WCSSearch.searchByClaimantsDOB(getTodaysDate());
                        WCSSearch.assertCaseIsVisibleInSearchResults();
                    }
                }
                break;
            case "NATIONAL INSURANCE NO":
                try {
                    WCSSearch.assertCaseIsVisibleInSearchResults();
                } catch (AssertionError e) {
                    try {
                        waitABit(5000);
                        dashboard.selectSearchLinkFromMenuBar();
                        search.waitForSearchCriteriaPage();
                        WCSSearch.searchByNationalInsuranceNo("QQ123456C");
                        WCSSearch.assertCaseIsVisibleInSearchResults();
                    } catch (AssertionError e2) {
                        waitABit(5000);
                        dashboard.selectSearchLinkFromMenuBar();
                        search.waitForSearchCriteriaPage();
                        WCSSearch.searchByNationalInsuranceNo("QQ123456C");
                        WCSSearch.assertCaseIsVisibleInSearchResults();
                    }
                }
                break;
            case "PREVIOUS HOCS REFERENCE":
                try {
                    WCSSearch.assertCaseIsVisibleInSearchResults();
                } catch (AssertionError e) {
                    try {
                        waitABit(5000);
                        dashboard.selectSearchLinkFromMenuBar();
                        search.waitForSearchCriteriaPage();
                        WCSSearch.searchByPreviousHOCSRef("WCS/0000000/19");
                        WCSSearch.assertCaseIsVisibleInSearchResults();
                    } catch (AssertionError e2) {
                        waitABit(5000);
                        dashboard.selectSearchLinkFromMenuBar();
                        search.waitForSearchCriteriaPage();;
                        WCSSearch.searchByPreviousHOCSRef("WCS/0000000/19");
                        WCSSearch.assertCaseIsVisibleInSearchResults();
                    }
                }
                break;
            default:
                pendingStep(searchType + " is not defined within " + getMethodName());
        }

    }

    @When("I search for the case/claim by reference number")
    public void iSearchForTheCaseByReferenceNumber() {
        WCSSearch.searchByWCSReference(getCurrentCaseReference().toString());
    }

    @Then("the created case/claim should be displayed as a search result")
    public void theCaseShouldBeDisplayedAsASearchResult() {
        try {
            WCSSearch.assertCaseIsVisibleInSearchResults();
        } catch (AssertionError e) {
            waitABit(5000);
            dashboard.selectSearchLinkFromMenuBar();
            search.waitForSearchCriteriaPage();
            WCSSearch.searchByWCSReference(getCurrentCaseReference());
            WCSSearch.assertCaseIsVisibleInSearchResults();
        }
    }

    @When("I search by the claimant name {string}")
    public void iSearchByTheClaimantName(String name) {
        WCSSearch.searchByClaimantName(name);
    }

    @Then("all search results should have a claimant name that contains {string}")
    public void allSearchResultsShouldHaveAClaimantNameThatContains(String name) {
        WCSSearch.assertAllResultsContainClaimantName(name);
    }

    @When("I search for Claimant DOB using today's date")
    public void iSearchForClaimantDOBUsingTodaySDate() {
        WCSSearch.searchByClaimantsDOB(getTodaysDate());
    }

    @When("I search for the National Insurance No {string}")
    public void iSearchForTheNationalInsuranceNo(String niNo) {
        WCSSearch.searchByNationalInsuranceNo(niNo);
    }

    @When("I search for the previous HOCS reference {string}")
    public void iSearchForThePreviousHOCSReference(String ref) {
        WCSSearch.searchByPreviousHOCSRef(ref);
    }
}

