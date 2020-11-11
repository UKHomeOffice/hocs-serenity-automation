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

    SummaryTab summaryTab;

    PeopleTab peopleTab;

    @When("I click the search button on the search page")
    public void clickSearchButtonOnSearchPageWithNoCriteria() {
        search.performSearch();
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
        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
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

    @And("I enter {string} into the {string} search criteria for DCU")
    public void enterIntoTheSearchCriteria(String value, String criteria) {
        search.enterDCUSearchCriteria(criteria, value);
    }

    @Then("the {string} of the search results should be {string}")
    public void assertThatSearchResultsContainCorrectValue(String dataType, String dataValue) {
        switch (dataType.toUpperCase()) {
            case "CASE TYPE":
                search.assertCaseTypeIsOnlyTypeVisible(dataValue);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                search.assertFirstAndLastSearchResultsMatchDateSearchCriteria("Before", dataValue);
                break;
            case "RECEIVED ON OR AFTER DATE":
                search.assertFirstAndLastSearchResultsMatchDateSearchCriteria("After", dataValue);
                break;
            case "CORRESPONDENT NAME":
            case "TOPIC":
            case "HOME SEC INTEREST":
            case "SIGN OFF TEAM":
            case "MINISTERIAL SIGN OFF TEAM":
                search.assertFirstAndLastResultOf(dataType);
                break;
            default:
                pendingStep(dataType + " is not defined within " + getMethodName());
        }
    }

    @And("I click the case reference link of the first case in the results list")
    public void iClickTheCaseReferenceLinkOfTheFirstCaseInTheResultsList() {
        setSessionVariable("caseReference").to(search.topSearchResultCaseReference.getText());
        safeClickOn(search.topSearchResultCaseReference);
    }

    @Then("the search results should contain the expected information")
    public void theSearchResultsShouldContainTheExpectedInformation() {
        search.assertExpectedTablesHeadersPresent();

    }

    @Then("cases received on or {string} {string} should be displayed")
    public void casesReceivedOnOrShouldBeDisplayed(String beforeOrAfter, String date) {
        search.assertFirstAndLastSearchResultsMatchDateSearchCriteria(beforeOrAfter, date);
    }

    @Then("{int} cases should be displayed")
    public void casesShouldBeDisplayed(int number) {
        search.assertNumberOfCasesDisplayed(number);
    }

    @Then("the created case should be visible in the search results")
    public void theCreatedCaseShouldBeIncludedInTheSearchResults() {
        int retest = 0;
        while (retest < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                break;
            } catch (AssertionError a) {
                retest ++;
                safeClickOn(homepage.searchPage);
                search.enterDCUSearchCriteria("Topic", sessionVariableCalled("searchTopic"));
                safeClickOn(searchButton);
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @Then("both active and closed cases will be returned in the search results")
    public void bothActiveAndClosedCasesWillBeReturnedInTheSearchResults() {
        search.assertActiveCaseVisibleIs(true);
        search.assertClosedCaseVisibleIs(true);
    }


    @And("I select active cases")
    public void iSelectActiveCases() {
        safeClickOn(search.caseStatusActiveCheckbox);
    }

    @Then("Only active cases will be returned in the search results")
    public void onlyActiveCasesWillBeReturnedInTheSearchResults() {
        search.assertActiveCaseVisibleIs(true);
        search.assertClosedCaseVisibleIs(false);
    }

    @Then("cases with the queried Sign-off Team should be displayed in the results list")
    public void casesWithTheQueriedSignOffTeamShouldBeDisplayedInTheResultsList() {
        int retry = 0;
        while (retry < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                break;
            } catch (AssertionError e) {
                waitABit(7500);
                retry++;
                safeClickOn(homepage.searchPage);
                enterIntoTheSearchCriteria("MIN", "Case Type");
                enterIntoTheSearchCriteria(sessionVariableCalled("searchSignOffTeam"), "Sign Off Team");
                search.performSearch();
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @And("I look for the current case that was received on or before the date searched")
    public void lookForTheCurrentCaseInTheSearchResultsBeforeDate() {
        int retry = 0;
        String caseType = sessionVariableCalled("searchCaseType");
        while (retry < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                break;
            } catch (AssertionError aE) {
                waitABit(7500);
                retry++;
                safeClickOn(homepage.searchPage);
                enterIntoTheSearchCriteria(caseType, "Case Type");
                enterIntoTheSearchCriteria("01/01/2019", "Received On Or Before Date");
                search.performSearch();
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }
    @And("I look for the current case that was received on or after the date searched")
    public void lookForTheCurrentCaseInTheSearchResultsAfterDate() {
        int retry = 0;
        String afterDateCaseType = sessionVariableCalled("searchCaseType");
        while (retry < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                break;
            } catch (AssertionError aE) {
                waitABit(7500);
                retry++;
                safeClickOn(homepage.searchPage);
                enterIntoTheSearchCriteria(afterDateCaseType, "Case Type");
                enterIntoTheSearchCriteria("22/09/2020", "Received On Or After Date");
                search.performSearch();
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @And("I search for an UKVI case with {string} as it's {string}")
    public void searchForMPAMCaseWith(String infoValue, String infoType) {
        if (search.mpamCaseCheckbox.isVisible()) {
            safeClickOn(search.mpamCaseCheckbox);
        }
        if (search.mtsCaseCheckbox.isVisible()) {
            safeClickOn(search.mtsCaseCheckbox);
        }
        search.enterMPAMSearchCriteria(infoType, infoValue);
        setSessionVariable("infoValue").to(infoValue);
        safeClickOn(searchButton);
    }

    @And("I check that the UKVI search results have the correct {string}")
    public void checkMPAMCaseHasCorrect(String infoType) {
        WebElementFacade topSearchResult = findBy("//tr[1]/td/a[contains(text(), 'MPAM') or contains(text(), 'MTS')]");
        setSessionVariable("topSearchResult").to(topSearchResult.getText());
        WebElementFacade bottomSearchResult = findBy(
                "//tr[" + workstacks.getTotalOfCases() + "]/td/a[contains(text(), 'MPAM') or contains(text(), 'MTS')]");
        setSessionVariable("bottomSearchResult").to(bottomSearchResult.getText());
        switch (infoType.toUpperCase()) {
            case "REFERENCE TYPE":
                safeClickOn(topSearchResult);
                summaryTab.selectSummaryTab();
                WebElementFacade firstRefTypeResponse = findBy("//th[contains(text(), 'Ministerial response')]/following-sibling::td");
                firstRefTypeResponse.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                summaryTab.selectSummaryTab();
                WebElementFacade secondRefTypeResponse = findBy("//th[contains(text(), 'Ministerial response')]/following-sibling::td");
                secondRefTypeResponse.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(topSearchResult);
                peopleTab.selectPeopleTab();
                peopleTab.assertCorrespondentIsAttachedToCase(sessionVariableCalled("infoValue"));
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                peopleTab.selectPeopleTab();
                peopleTab.assertCorrespondentIsAttachedToCase(sessionVariableCalled("infoValue"));
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                safeClickOn(topSearchResult);
                peopleTab.selectPeopleTab();
                WebElementFacade firstCorrespondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                firstCorrespondentRefNumber.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                peopleTab.selectPeopleTab();
                WebElementFacade secondCorrespondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                secondCorrespondentRefNumber.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "CAMPAIGN":
                safeClickOn(topSearchResult);
                summaryTab.selectSummaryTab();
                WebElementFacade firstCaseCampaign = findBy("//th[text()='Campaign']/following-sibling::td");
                firstCaseCampaign.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                summaryTab.selectSummaryTab();
                WebElementFacade secondCaseCampaign = findBy("//th[text()='Campaign']/following-sibling::td");
                secondCaseCampaign.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                search.assertFirstAndLastResultOf(infoType);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                safeClickOn(topSearchResult);
                peopleTab.selectPeopleTab();
                search.assertThatSearchedCorrespondentNameIsShownInPeopleTab();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                peopleTab.selectPeopleTab();
                search.assertThatSearchedCorrespondentNameIsShownInPeopleTab();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }

    @And("I search for a case by it's case reference")
    public void searchForCaseByReference() {
        String caseRef = sessionVariableCalled("caseReference");
        searchForMPAMCaseWith(caseRef, "Case Reference");
        safeClickOn(searchButton);
    }

    @And("the one created case should be displayed")
    public void createdCaseShouldBeDisplayed(){
        int numberOfResults = workstacks.getTotalOfCases();
        int retest = 0;
        while (retest < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                assertThat(numberOfResults == 1, is(true));
                break;
            } catch (AssertionError a) {
                retest ++;
                safeClickOn(homepage.searchPage);
                searchForMPAMCaseWith(sessionVariableCalled("caseReference"), "Case Reference");
                safeClickOn(searchButton);
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @And("I search for a case using a random substring of a case reference")
    public void searchForCaseBySubstringOfCaseReference() {
        search.searchBySubstringOfCaseReference();
    }

    @Then("the displayed cases all contain the input substring case reference")
    public void displayedCasesAllContainSubstringCaseRef() {
        search.assertAllDisplayedCaseRefsContainSubstring();
    }

    @Then("the first and last search results are of interest to the Home Secretary")
    public void assertFirstAndLastSearchResultAreHomeSecInterest() {
        search.assertFirstAndLastResultOf("Home Sec Interest");
    }
}