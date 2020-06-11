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

    @When("I search by the case type {string}")
    public void selectCaseTypeCheckbox(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                safeClickOn(search.searchMINCheckbox);
                break;
            case "DTEN":
                safeClickOn(search.searchDTENCheckbox);
                break;
            case "TRO":
                safeClickOn(search.searchTROCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        safeClickOn(search.searchButton);
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

    @When("I search by the case type {string} and another parameter {string}")
    public void searchByCaseTypeAndAnotherParameter(String caseType, String anotherParameter) {
        safeClickOn(homepage.searchPage);
        switch (caseType.toUpperCase()) {
            case "MIN":
                safeClickOn(search.searchMINCheckbox);
                break;
            case "DTEN":
                safeClickOn(search.searchDTENCheckbox);
                break;
            case "TRO":
                safeClickOn(search.searchTROCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (anotherParameter.toUpperCase()) {
            case "PERMANENT SECRETARY SIGNOFF TEAM":
                search.selectSignOffTeam("Permanent Secretary");
                break;
            case "CARDIFF UNIVERSITY KITTENS TOPIC":
                search.enterSearchTopic("Cardiff University Kittens");
                break;
            default:
                pendingStep(anotherParameter + " is not defined within " + getMethodName());
        }
        safeClickOn(search.searchButton);
    }

    @Then("cases that are {string} case type that also contain another parameter {string} should be displayed in "
            + "the results "
            + "list")
    public void assertThatSearchResultsContainCaseTypeAndAnotherParameter(String caseType, String anotherParameter) {
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

        switch (anotherParameter.toUpperCase()) {
            case "PERMANENT SECRETARY SIGNOFF TEAM":
                search.assertFirstAndLastSearchResultsMatchSignOffTeam();
                break;
            case "CARDIFF UNIVERSITY KITTENS TOPIC":
                search.viewFirstSearchResultCaseSummary();
                search.assertThatSearchedTopicNameIsShownInCaseSummary();
                break;
            default:
                pendingStep(anotherParameter + " is not defined within " + getMethodName());
        }
    }

    @When("I search by the correspondent name {string}")
    public void enterCorrespondentNameSearchQuery(String correspondentName) {
        search.enterSearchCorrespondent(correspondentName);
        safeClickOn(search.searchButton);
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

    @When("I search for cases received on or after {string}-{string}-{string}")
    public void iSearchForCasesReceivedOnOrAfter(String dd, String mm, String yyyy) {
        search.enterReceivedOnOrAfterDate(dd, mm, yyyy);
        safeClickOn(search.searchButton);
    }

    @When("I search for cases received on or before {string}-{string}-{string}")
    public void iSearchForCasesReceivedOnOrBefore(String dd, String mm, String yyyy) {
        search.enterReceivedOnOrBeforeDate(dd, mm, yyyy);
        safeClickOn(search.searchButton);
    }

    @Then("cases received on or {string} {string} should be displayed")
    public void casesReceivedOnOrShouldBeDisplayed(String beforeOrAfter, String date) {
        search.assertFirstAndLastSearchResultsMatchDateSearchCriteria(beforeOrAfter, date);
    }

    @Then("{int} cases should be displayed")
    public void casesShouldBeDisplayed(int number) {
        search.assertNumberOfCasesDisplayed(number);
    }

    @When("I search for the topic")
    public void iSearchForTheTopic() {
        waitABit(5000);
        search.enterSearchTopic(sessionVariableCalled("searchTopic"));
        safeClickOn(search.searchButton);
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
                iSearchForTheTopic();
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

    @When("I search for a {string} case by the Sign-off Team {string}")
    public void iSearchByTheSignOffTeam(String caseType, String signOffTeam) {
        search.selectSignOffTeam(signOffTeam);
        setSessionVariable("signOffTeam").to(signOffTeam);
        switch (caseType) {
            case "MIN":
                safeClickOn(search.searchMINCheckbox);
                break;
            case "TRO":
                safeClickOn(search.searchTROCheckbox);
                break;
            case "DTEN":
                safeClickOn(search.searchDTENCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        safeClickOn(search.searchButton);
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
                iSearchByTheSignOffTeam("MIN", sessionVariableCalled("signOffTeam"));
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @When("I search for a made up topic")
    public void iSearchForAMadeUpTopic() {
        waitABit(2000);
        search.enterSearchTopic("Made up topic");
        safeClickOn(search.searchButton);
    }

    @And("I search for a {string} case received on or before {string}-{string}-{string}")
    public void iSearchForTheCurrentCaseReceivedBefore(String caseType, String dd, String mm, String yyyy) {
        setSessionVariable("beforeReceivedCaseType").to(caseType);
        search.enterReceivedOnOrBeforeDate(dd, mm, yyyy);
        switch (caseType.toUpperCase()) {
            case "MIN":
                safeClickOn(search.searchMINCheckbox);
                break;
            case "TRO":
                safeClickOn(search.searchTROCheckbox);
                break;
            case "DTEN":
                safeClickOn(search.searchDTENCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        waitABit(10000);
        search.safeClickOn(searchButton);
    }

    @And("I search for a {string} case received on or after {string}-{string}-{string}")
    public void iSearchForTheCurrentCaseReceivedAfter(String caseType, String dd, String mm, String yyyy) {
        setSessionVariable("afterReceivedCaseType").to(caseType);
        search.enterReceivedOnOrAfterDate(dd, mm, yyyy);
        switch (caseType.toUpperCase()) {
            case "MIN":
                safeClickOn(search.searchMINCheckbox);
                break;
            case "TRO":
                safeClickOn(search.searchTROCheckbox);
                break;
            case "DTEN":
                safeClickOn(search.searchDTENCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        waitABit(10000);
        search.safeClickOn(searchButton);
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
                switch (beforeDateCaseType) {
                    case "MIN":
                        safeClickOn(search.searchMINCheckbox);
                        break;
                    case "DTEN":
                        safeClickOn(search.searchDTENCheckbox);
                        break;
                    case "TRO":
                        safeClickOn(search.searchTROCheckbox);
                        break;
                    default:
                        pendingStep(beforeDateCaseType + " is not defined within " + getMethodName());
                }
                iSearchForCasesReceivedOnOrBefore("01", "01", "2019");
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
                switch (afterDateCaseType) {
                    case "MIN":
                        safeClickOn(search.searchMINCheckbox);
                        break;
                    case "DTEN":
                        safeClickOn(search.searchDTENCheckbox);
                        break;
                    case "TRO":
                        safeClickOn(search.searchTROCheckbox);
                        break;
                    default:
                        pendingStep(afterDateCaseType + " is not defined within " + getMethodName());
                }
                iSearchForCasesReceivedOnOrAfter("01", "01", "2019");
            }
        }
        search.assertCurrentCaseIsDisplayedInSearchResults();
    }

    @And("I search for an MPAM case with {string} as it's {string}")
    public void searchForMPAMCaseWith(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "REFERENCE TYPE":
                search.searchByRefType(infoValue);
                setSessionVariable("infoValue").to(infoValue);
                safeClickOn(searchButton);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                search.searchByMemberOfParliament(infoValue);
                setSessionVariable("infoValue").to(infoValue);
                safeClickOn(searchButton);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                search.searchByCorrespondentRefNumber(infoValue);
                setSessionVariable("infoValue").to(infoValue);
                safeClickOn(searchButton);
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
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
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}


