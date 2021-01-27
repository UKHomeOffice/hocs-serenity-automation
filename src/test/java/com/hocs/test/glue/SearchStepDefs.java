package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.PeopleTab;
import com.hocs.test.pages.Search;
import com.hocs.test.pages.SummaryTab;
import com.hocs.test.pages.UnallocatedCaseView;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;


public class SearchStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    Search search;

    CreateCase createCase;

    UnallocatedCaseView unallocatedCaseView;

    @When("I click the search button on the search page")
    public void clickSearchButtonOnSearchPageWithNoCriteria() {
        safeClickOn(search.searchButton);
    }

    @Then("an error message should be displayed as I have not entered any search criteria")
    public void assertThatNoSearchCriteriaErrorMessageIsShown() {
        search.assertNoSearchCriteriaErrorMessage();
    }

    @When("I enter a valid case reference into the load case search bar")
    public void enterValidCaseReferenceForSearch() {
        createCase.createCaseOfType("MIN");
        goHome();
        homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("caseReference"));
        homepage.hitEnterCaseReferenceSearchBar();
    }

    @Then("I should be taken directly to the case")
    public void assertThatCaseReferenceSearchTakesUserToCase() {
        workstacks.waitABit(500);
        if (workstacks.isElementDisplayed(unallocatedCaseView.allocateToMeLink)) {
            workstacks.assertCaseReferenceBeforeAllocation();
        } else {
            workstacks.assertCaseReferenceAfterAllocation();
        }
    }

    @When("I enter a non-existent case reference")
    public void enterInvalidCaseReferenceForSearch() {
        homepage.enterCaseReferenceIntoSearchBar("MIN/0000000/19");
        homepage.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that there are no active workflows for the case")
    public void assertThatNoActiveWorkflowsErrorMessageIsShown() {
        homepage.assertNoActiveWorkflowsForCaseErrorMessage();
    }

    @When("I press enter in the Load Case search bar")
    public void PressEnterInTheLoadCaseBar() {
        homepage.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that a case reference is required")
    public void assertThatCaseReferenceIsRequiredMessageIsShown() {
        homepage.assertCaseReferenceIsRequiredErrorMessage();
    }

    @And("I enter {string} into the {string} DCU search criteria")
    public void enterIntoTheSearchCriteria(String value, String criteria) {
        setSessionVariable("searchCriteria").to(criteria);
        setSessionVariable("searchValue").to(value);
        search.enterDCUSearchCriteria(criteria, value);
    }

    @Then("I check that the DCU search results have the correct {string}")
    public void assertThatSearchResultsContainCorrectValue(String dataType) {
        search.assertDCUInformationRandomSearchResult(dataType);
    }

    @Then("the search results should contain the expected information")
    public void theSearchResultsShouldContainTheExpectedInformation() {
        search.assertExpectedTablesHeadersPresent();
    }

    @Then("the created DCU case should be visible in the search results")
    public void theCreatedDCUCaseShouldBeVisibleInTheSearchResults() {
        int retest = 0;
        while (retest < 5) {
            try {
                search.assertCurrentCaseIsDisplayed();
                break;
            } catch (AssertionError a) {
                retest ++;
                safeClickOn(homepage.searchPage);
                search.enterDCUSearchCriteria(sessionVariableCalled("searchCriteria"), sessionVariableCalled("searchValue"));
                safeClickOn(searchButton);
            }
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("I enter {string} into the {string} UKVI search criteria")
    public void searchForMPAMCaseWith(String infoValue, String infoType) {
        if (search.mpamCaseCheckbox.isVisible()) {
            safeClickOn(search.mpamCaseCheckbox);
        }
        if (search.mtsCaseCheckbox.isVisible()) {
            safeClickOn(search.mtsCaseCheckbox);
        }
        search.enterMPAMSearchCriteria(infoType, infoValue);
        setSessionVariable("infoValue").to(infoValue);
        setSessionVariable("infoType").to(infoType);
    }

    @And("I check that the UKVI search results have the correct {string}")
    public void checkMPAMCaseHasCorrect(String infoType) {
        search.assertMPAMInformationRandomSearchResult(infoType);
    }

    @And("I search for a case by it's case reference")
    public void searchForCaseByReference() {
        String caseRef = sessionVariableCalled("caseReference");
        searchForMPAMCaseWith(caseRef, "Case Reference");
        safeClickOn(searchButton);
    }

    @And("the created MPAM case should be visible in the search results")
    public void createdMPAMCaseShouldBeVisibleInTheSearchResults(){
        int numberOfResults = workstacks.getTotalOfCases();
        int retest = 0;
        while (retest < 5) {
            try {
                search.assertCurrentCaseIsDisplayed();
                assertThat(numberOfResults == 1, is(true));
                break;
            } catch (AssertionError a) {
                retest ++;
                safeClickOn(homepage.searchPage);
                searchForMPAMCaseWith(sessionVariableCalled("infoType"), "infoValue");
                safeClickOn(searchButton);
            }
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("I search for a case using a random substring of a case reference")
    public void searchForCaseBySubstringOfCaseReference() {
        search.searchBySubstringOfCaseReference();
    }

    @Then("the displayed cases all contain the input substring case reference")
    public void displayedCasesAllContainSubstringCaseRef() {
        search.assertAllDisplayedCaseRefsContainSubstring();
    }

}