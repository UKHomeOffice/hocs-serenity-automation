package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Search;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;


public class SearchStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Search search;

    CreateCase createCase;

    CaseView caseView;

    @And("I enter {string} into the {string} search field in the {string} search configuration")
    public void iEnterIntoTheSearchFieldForTheCaseType(String value, String criteria, String caseType) {
        setSessionVariable("searchValue").to(value);
        setSessionVariable("searchCriteria").to(criteria);
        setSessionVariable("searchConfig").to(caseType);
        switch (caseType.toUpperCase()) {
            case "DCU":
                search.enterDCUSearchCriteria(criteria, value);
                break;
            case "MPAM":
                if (search.mpamCaseCheckbox.isCurrentlyVisible()) {
                    safeClickOn(search.mpamCaseCheckbox);
                }
                if (search.mtsCaseCheckbox.isCurrentlyVisible()) {
                    safeClickOn(search.mtsCaseCheckbox);
                }
                search.enterMPAMSearchCriteria(criteria, value);
                break;
            case "COMP":
                search.enterCOMPSearchCriteria(criteria, value);
                break;
            case "FOI":
                search.enterFOISearchCriteria(criteria, value);
                break;
            case "BF":
                search.enterBFSearchCriteria(criteria, value);
                break;
            case "TO":
                search.enterTOSearchCriteria(criteria, value);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I check that the search results have the correct {string}")
    public void iCheckThatTheSearchResultsHaveTheCorrect(String criteria) throws ParseException {
        String caseType = sessionVariableCalled("searchConfig");
        if (search.zeroSearchResultsReturned()) {
            createCase.generateSearchCaseData(caseType, sessionVariableCalled("searchValue"), criteria);
        }
        switch (caseType.toUpperCase()) {
            case "DCU":
                search.assertDCUInformationRandomSearchResult(criteria);
                break;
            case "MPAM":
                search.assertMPAMInformationRandomSearchResult(criteria);
                break;
            case "COMP":
                search.assertCOMPInformationRandomSearchResult(criteria);
                break;
            case "FOI":
                search.assertFOIInformationRandomSearchResult(criteria);
                break;
            case "BF":
                search.assertBFInformationRandomSearchResult(criteria);
                break;
            case "TO":
                search.assertTOInformationRandomSearchResult(criteria);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I click the search button on the search page")
    public void clickSearchButtonOnSearchPageWithNoCriteria() {
        safeClickOn(search.searchButton);
        search.waitForResultsPage();
    }

    @When("I enter a non-hocs format case reference")
    public void iEnterANonHocsFormatCaseReference() {
        dashboard.enterCaseReferenceIntoSearchBar("MIN0000000019");
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that the case reference entered is an invalid format")
    public void anErrorMessageShouldBeDisplayedStatingThatTheCaseReferenceEnteredIsAnInvalidFormat() {
        dashboard.assertCaseReferenceIsInvalidFormatErrorMessage();
    }

    @Then("an error message should be displayed as I have not entered any search criteria")
    public void assertThatNoSearchCriteriaErrorMessageIsShown() {
        search.assertNoSearchCriteriaErrorMessage();
    }

    @When("I enter a valid case reference into the load case search bar")
    public void enterValidCaseReferenceForSearch() {
        createCase.createCSCaseOfType("MIN");
        dashboard.goToDashboard();
        dashboard.enterCaseReferenceIntoSearchBar(getCurrentCaseReference());
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("I should be taken directly to the case")
    public void assertThatCaseReferenceSearchTakesUserToCase() {
        workstacks.waitABit(500);
        if (workstacks.isElementDisplayed(caseView.allocateToMeLink)) {
            workstacks.assertCaseReferenceBeforeAllocation();
        } else {
            workstacks.assertCaseReferenceAfterAllocation();
        }
    }

    @When("I enter a non-existent (case )reference")
    public void enterInvalidCaseReferenceForSearch() {
        dashboard.enterCaseReferenceIntoSearchBar("MIN/0000000/19");
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that there are no active workflows for the case")
    public void assertThatNoActiveWorkflowsErrorMessageIsShown() {
        dashboard.assertNoActiveWorkflowsForCaseErrorMessage();
    }

    @When("I press enter in the Load Case search bar")
    public void PressEnterInTheLoadCaseBar() {
        dashboard.hitEnterCaseReferenceSearchBar();
    }

    @Then("an error message should be displayed stating that a case reference is required")
    public void assertThatCaseReferenceIsRequiredMessageIsShown() {
        dashboard.assertCaseReferenceIsRequiredErrorMessage();
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
                dashboard.selectSearchLinkFromMenuBar();
                search.enterDCUSearchCriteria(sessionVariableCalled("searchCriteria"), sessionVariableCalled("searchValue"));
                safeClickOn(searchButton);
            }
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("the created case should be the only case visible in the search results")
    public void createdCaseShouldBeVisibleInTheSearchResults(){
        workstacks.filterByCurrentCaseReference();
        waitABit(1000);
        int numberOfResults = workstacks.getTotalOfCases();
        int retest = 0;
        while (retest < 5) {
            if (numberOfResults < 1) {
                retest ++;
                dashboard.selectSearchLinkFromMenuBar();
                iEnterIntoTheSearchFieldForTheCaseType(getCurrentCaseReference(), "Case Reference", "MPAM");
                safeClickOn(searchButton);
                workstacks.filterByCurrentCaseReference();
                waitABit(1000);
            } else if (numberOfResults > 1) {
                Assert.fail("More than one case has matching case reference");
            } else {
                break;
            }
        }
        search.assertCurrentCaseIsDisplayed();
    }

    @And("I click the (case )reference of the case/claim in search results")
    public void iClickTheReferenceOfARandomSearchResult() {
        WebElementFacade caseReference = findBy("//a[text()='" + getCurrentCaseReference() + "']");
        safeClickOn(caseReference);
    }

    @And("I search for a case using a random substring of a case reference")
    public void searchForCaseBySubstringOfCaseReference() {
        search.searchBySubstringOfCaseReference();
    }

    @Then("the displayed cases all contain the input substring case reference")
    public void displayedCasesAllContainSubstringCaseRef() {
        search.assertAllDisplayedCaseRefsContainSubstring();
    }

    @Then("{int} cases should be displayed")
    public void numberOfCasesShouldBeDisplayed(int number) {
        int numberOfCasesDisplayed = search.getNumberOfSearchResults();
        assertThat(number == numberOfCasesDisplayed, is(true));
    }

    @And("I search for the case by its case reference")
    public void iSearchForTheCaseByItsCaseReference() {
        int i = 0;
        while (i < 6) {
            dashboard.selectSearchLinkFromMenuBar();
            search.enterCOMPSearchCriteria("Case Reference", getCurrentCaseReference());
            safeClickOn(searchButton);
            search.waitForResultsPage();
            if(!search.zeroSearchResultsReturned()) {
                break;
            }
            waitABit(10000);
            i++;
        }
    }

    @And("I search for the COMP case escalated to COMP2 by it's case reference")
    public void iSearchForTheEscalatedCOMPCaseByCaseReference() {
        String compCaseRef = sessionVariableCalled("compCaseReference");
        search.enterCOMPSearchCriteria("Case Reference", compCaseRef);
        safeClickOn(searchButton);
        search.waitForResultsPage();
    }

    @And("I load the COMP2 case by selecting its case reference from the Escalate Case column")
    public void iLoadTheCOMP2CaseBySelectingTheCaseReferenceInTheEscalateCaseColumn() {
        search.selectCOMP2CaseRefOfEscalatedCOMPCase(sessionVariableCalled("compCaseReference"));
    }
}