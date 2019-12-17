package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.search.Search;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.setSessionVariable;


public class SearchStepDefs extends Page {

    Homepage homepage;

    Workstacks workstacks;

    Search search;

    @When("^I click the search button on the search page$")
    public void clickSearchButtonOnSearchPageWithNoCriteria() {
        clickOn(search.searchButton);
    }

    @Then("^an error message should be displayed as I have not entered any search criteria$")
    public void assertThatNoSearchCriteriaErrorMessageIsShown() {
        search.assertNoSearchCriteriaErrorMessage();
    }

    @When("^I enter a valid case reference into the load case search bar$")
    public void enterValidCaseReferenceForSearch() {
        homepage.getValidCaseReferenceAndEnterIntoSearchBar();
        homepage.hitEnterCaseReferenceSearchBar();
    }

    @Then("^I should be taken directly to the case$")
    public void assertThatCaseReferenceSearchTakesUserToCase() {
        workstacks.waitABit(500);

        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            workstacks.assertCaseReferenceBeforeAllocation();
        } else {
            workstacks.assertCaseReferenceAfterAllocation();
        }

    }

    @When("^I enter a non-existent case reference$")
    public void enterInvalidCaseReferenceForSearch() {
        homepage.enterCaseReferenceIntoSearchBar("MIN/0000000/19");
        homepage.hitEnterCaseReferenceSearchBar();
    }

    @Then("^an error message should be displayed stating that there are no active workflows for the case$")
    public void assertThatNoActiveWorkflowsErrorMessageIsShown() {
        homepage.assertNoActiveWorkflowsForCaseErrorMessage();
    }

    @When("^I press enter in the Load Case search bar$")
    public void PressEnterInTheLoadCaseBar() {
        homepage.hitEnterCaseReferenceSearchBar();
    }

    @Then("^an error message should be displayed stating that a case reference is required$")
    public void assertThatCaseReferenceIsRequiredMessageIsShown() {
        homepage.assertCaseReferenceIsRequiredErrorMessage();
    }

    @When("^I search by the case type \"([^\"]*)\"$")
    public void selectCaseTypeCheckbox(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                clickOn(search.searchMINCheckbox);
                break;
            case "DTEN":
                clickOn(search.searchDTENCheckbox);
                break;
            case "TRO":
                clickOn(search.searchTROCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        clickOn(search.searchButton);
    }

    @Then("^only the chosen \"([^\"]*)\" case type results should be displayed in the results list$")
    public void assertThatOnlySelectedCaseTypeResultsAreDisplayed(String caseType) {
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

    @When("^I search by the case type \"([^\"]*)\" and another parameter \"([^\"]*)\"$")
    public void searchByCaseTypeAndAnotherParameter(String caseType, String anotherParameter) {
        clickOn(homepage.searchPage);
        switch (caseType.toUpperCase()) {
            case "MIN":
                clickOn(search.searchMINCheckbox);
                break;
            case "DTEN":
                clickOn(search.searchDTENCheckbox);
                break;
            case "TRO":
                clickOn(search.searchTROCheckbox);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (anotherParameter.toUpperCase()) {
            case "PERMANENT SECRETARY SIGNOFF TEAM":
                search.selectSignOffTeam("Permanent Secretary");
                break;
            case "KITTENS TOPIC":
                search.enterSearchTopic("Cardiff University Kittens");
                break;
            default:
                pendingStep(anotherParameter + " is not defined within " + getMethodName());
        }
        clickOn(search.searchButton);
    }

    @Then("^cases that are \"([^\"]*)\" case type that also contain another parameter \"([^\"]*)\" should be displayed in "
            + "the results "
            + "list$")
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
            case "KITTENS TOPIC":
                search.viewFirstSearchResultCaseSummary();
                search.assertThatSearchedTopicNameIsShownInCaseSummary();
                break;
            default:
                pendingStep(anotherParameter + " is not defined within " + getMethodName());
        }
    }

    @When("^I search by the correspondent name \"([^\"]*)\"$")
    public void enterCorrespondentNameSearchQuery(String correspondentName) {
        search.enterSearchCorrespondent(correspondentName);
        clickOn(search.searchButton);
    }

    @Then("^cases with the queried correspondent name should be displayed in the results list$")
    public void assertThatFirstCaseInCorrespondentSearchResultMatchesSearchQuery() {
        search.viewFirstSearchResultCaseSummary();
        search.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
    }

    @And("^I click the case reference link of the first case in the results list$")
    public void iClickTheCaseReferenceLinkOfTheFirstCaseInTheResultsList() {
        setSessionVariable("caseReference").to(search.topSearchResultCaseReference.getText());
        clickOn(search.topSearchResultCaseReference);
    }

    @Then("^the search results should contain the expected information$")
    public void theSearchResultsShouldContainTheExpectedInformation() {
        search.assertExpectedTablesHeadersPresent();

    }

    @When("^I search for cases received on or after \"([^\"]*)\"/\"([^\"]*)\"/\"([^\"]*)\"$")
    public void iSearchForCasesReceivedOnOrAfter(String dd, String mm, String yyyy) {
        search.enterReceivedOnOrAfterDate(dd, mm, yyyy);
        clickOn(search.searchButton);
    }

    @When("^I search for cases received on or before \"([^\"]*)\"/\"([^\"]*)\"/\"([^\"]*)\"$")
    public void iSearchForCasesReceivedOnOrBefore(String dd, String mm, String yyyy) {
        search.enterReceivedOnOrBeforeDate(dd, mm, yyyy);
        clickOn(search.searchButton);
    }

    @Then("^cases received on or \"([^\"]*)\" \"([^\"]*)\" should be displayed$")
    public void casesReceivedOnOrShouldBeDisplayed(String beforeOrAfter, String date) {
        search.assertFirstAndLastSearchResultsMatchDateSearchCriteria(beforeOrAfter, date);
    }

    @Then("^(\\d+) cases should be displayed$")
    public void casesShouldBeDisplayed(int number) {
        search.assertNumberOfCasesDisplayed(number);
    }

    @When("^I search for the topic \"([^\"]*)\"$")
    public void iSearchForTheTopic(String topic) {
        waitABit(2000);
        search.enterSearchTopic(topic);
        clickOn(search.searchButton);
    }

    @Then("^the created case should be visible in the search results$")
    public void theCreatedCaseShouldBeIncludedInTheSearchResults() {
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("^both active and closed cases will be returned in the search results$")
    public void bothActiveAndClosedCasesWillBeReturnedInTheSearchResults() {
        search.assertActiveCaseVisibleIs(true);
        search.assertClosedCaseVisibleIs(true);
    }


    @And("^I select active cases$")
    public void iSelectActiveCases() {
        clickOn(search.caseStatusActiveCheckbox);
    }

    @Then("^Only active cases will be returned in the search results$")
    public void onlyActiveCasesWillBeReturnedInTheSearchResults() {
        search.assertActiveCaseVisibleIs(true);
        search.assertClosedCaseVisibleIs(false);
    }

    @When("^I search by the Sign-off Team \"([^\"]*)\"$")
    public void iSearchByTheSignOffTeam(String signOffTeam) {
        search.selectSignOffTeam(signOffTeam);
        clickOn(search.searchButton);
    }


    @Then("^cases with the queried Sign-off Team should be displayed in the results list$")
    public void casesWithTheQueriedSignOffTeamShouldBeDisplayedInTheResultsList() {
        search.assertFirstAndLastSearchResultsMatchSignOffTeam();
    }
}


