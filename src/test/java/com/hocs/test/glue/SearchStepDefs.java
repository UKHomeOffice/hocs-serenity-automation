package com.hocs.test.glue;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.Search;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class SearchStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    Search search;

    CreateCase createCase;

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

    @Then("only DCU {string} case type results should be displayed in the results list")
    public void assertThatOnlySelectedCaseTypeResultsAreDisplayed(String caseType) {
        search.assertOnSearchPage();
        switch (caseType.toUpperCase()) {
            case "MIN":
                search.assertThatDTENCaseIsNotVisible();
                search.assertThatTROCaseIsNotVisible();
                break;
            case "DTEN":
                search.assertThatMINCaseIsNotVisible();
                search.assertThatTROCaseIsNotVisible();
                break;
            case "TRO":
                search.assertThatMINCaseIsNotVisible();
                search.assertThatDTENCaseIsNotVisible();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("the {string} of the search results should be {string}")
    public void assertThatSearchResultsContainCorrectValue(String dataType, String dataValue) {
        WebElementFacade topSearchResult = findBy("//tr[1]/td/a");
        setSessionVariable("topSearchResult").to(topSearchResult.getText());
        WebElementFacade bottomSearchResult = findBy("//tr[" + workstacks.getTotalOfCases() + "]/td/a");
        setSessionVariable("bottomSearchResult").to(bottomSearchResult.getText());
        switch (dataType.toUpperCase()) {
            case "CASE TYPE":
                switch (dataValue.toUpperCase()) {
                    case "MIN":
                        search.assertThatDTENCaseIsNotVisible();
                        search.assertThatTROCaseIsNotVisible();
                        break;
                    case "DTEN":
                        search.assertThatMINCaseIsNotVisible();
                        search.assertThatTROCaseIsNotVisible();
                        break;
                    case "TRO":
                        search.assertThatMINCaseIsNotVisible();
                        search.assertThatDTENCaseIsNotVisible();
                        break;
                    default:
                        pendingStep(dataValue + " is not defined within " + getMethodName());
                }
                break;
            case "RECEIVED ON OR BEFORE DATE":
                search.assertFirstAndLastSearchResultsMatchDateSearchCriteria("Before", dataValue);
                break;
            case "RECEIVED ON OR AFTER DATE":
                search.assertFirstAndLastSearchResultsMatchDateSearchCriteria("After", dataValue);
                break;
            case "CORRESPONDENT NAME":
                safeClickOn(topSearchResult);
                safeClickOn(summaryTab);
                search.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                safeClickOn(summaryTab);
                search.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
                break;
            case "TOPIC":
                safeClickOn(topSearchResult);
                safeClickOn(summaryTab);
                search.assertThatSearchedTopicNameIsShownInCaseSummary();
                goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.hitEnterCaseReferenceSearchBar();
                safeClickOn(summaryTab);
                search.assertThatSearchedTopicNameIsShownInCaseSummary();
                break;
            case "HOME SEC INTEREST":
            case "SIGN OFF TEAM":
            case "MINISTERIAL SIGN OFF TEAM":
                search.assertFirstAndLastResultOf(dataType);

        }
    }

    @Then("cases with the queried correspondent name should be displayed in the results list")
    public void assertThatFirstCaseInCorrespondentSearchResultMatchesSearchQuery() {
        search.viewSummaryOfFirstSearchResultAdvancedPastDataInput();
        search.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
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
                enterIntoTheSearchCriteria(sessionVariableCalled("signOffTeam"), "Sign Off Team");
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @And("I look for the current case that was received on or before the date searched")
    public void lookForTheCurrentCaseInTheSearchResultsBeforeDate() {
        int retry = 0;
        String beforeDateCaseType = sessionVariableCalled("beforeReceivedCaseType");
        while (retry < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                break;
            } catch (AssertionError aE) {
                waitABit(7500);
                retry++;
                safeClickOn(homepage.searchPage);
                enterIntoTheSearchCriteria(beforeDateCaseType, "Case Type");
                enterIntoTheSearchCriteria("01/01/2019", "Received On Or Before Date");
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }
    @And("I look for the current case that was received on or after the date searched")
    public void lookForTheCurrentCaseInTheSearchResultsAfterDate() {
        int retry = 0;
        String afterDateCaseType = sessionVariableCalled("afterReceivedCaseType");
        while (retry < 5) {
            try {
                search.assertCurrentCaseIsDisplayedInSearchResults();
                break;
            } catch (AssertionError aE) {
                waitABit(10000);
                retry++;
                safeClickOn(homepage.searchPage);
                enterIntoTheSearchCriteria(afterDateCaseType, "Case Type");
                enterIntoTheSearchCriteria("01/01/2019", "Received On Or After Date");
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @And("I search for an MPAM case with {string} as it's {string}")
    public void searchForMPAMCaseWith(String infoValue, String infoType) {
        if (search.mpamCaseCheckbox.isVisible()) {
            safeClickOn(search.mpamCaseCheckbox);
        }
        search.enterMPAMSearchCriteria(infoType, infoValue);
        setSessionVariable("infoValue").to(infoValue);
        safeClickOn(searchButton);
    }

    @And("I check that the MPAM search results have the correct {string}")
    public void checkMPAMCaseHasCorrect(String infoType) {
        WebElementFacade topSearchResult = findBy("//tr[1]/td/a[contains(text(), 'MPAM')]");
        setSessionVariable("topSearchResult").to(topSearchResult.getText());
        WebElementFacade bottomSearchResult = findBy("//tr[" + workstacks.getTotalOfCases() + "]/td/a[contains(text(), 'MPAM')]");
        setSessionVariable("bottomSearchResult").to(bottomSearchResult.getText());
        switch (infoType.toUpperCase()) {
            case "REFERENCE TYPE":
                safeClickOn(topSearchResult);
                safeClickOn(summaryTab);
                WebElementFacade firstRefTypeResponse = findBy("//th[contains(text(), 'Ministerial response')]/following-sibling::td");
                firstRefTypeResponse.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                safeClickOn(summaryTab);
                WebElementFacade secondRefTypeResponse = findBy("//th[contains(text(), 'Ministerial response')]/following-sibling::td");
                secondRefTypeResponse.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(topSearchResult);
                safeClickOn(summaryTab);
                WebElementFacade firstMemberOfParliamentName = findBy("//th[contains(text(), 'Primary correspondent')"
                        + "]/following-sibling::td/span[1]");
                firstMemberOfParliamentName.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                safeClickOn(summaryTab);
                WebElementFacade secondMemberOfParliamentName = findBy("//th[contains(text(), 'Primary correspondent')"
                        + "]/following-sibling::td/span[1]");
                secondMemberOfParliamentName.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                safeClickOn(topSearchResult);
                safeClickOn(peopleTab);
                WebElementFacade firstCorrespondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                firstCorrespondentRefNumber.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                safeClickOn(peopleTab);
                WebElementFacade secondCorrespondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                secondCorrespondentRefNumber.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "CAMPAIGN":
                safeClickOn(topSearchResult);
                safeClickOn(summaryTab);
                WebElementFacade firstCaseCampaign = findBy("//th[text()='Campaign']/following-sibling::td");
                firstCaseCampaign.shouldContainText(sessionVariableCalled("infoValue"));
                homepage.goHome();
                homepage.enterCaseReferenceIntoSearchBar(sessionVariableCalled("bottomSearchResult"));
                homepage.caseReferenceSearchBar.sendKeys(Keys.ENTER);
                safeClickOn(summaryTab);
                WebElementFacade secondCaseCampaign = findBy("//th[text()='Campaign']/following-sibling::td");
                secondCaseCampaign.shouldContainText(sessionVariableCalled("infoValue"));
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                search.assertFirstAndLastResultOf(infoType);
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