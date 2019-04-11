package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.search_form.SearchForm;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.core.Serenity.pendingStep;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class SearchFormStepDefs {

    Page page;

    Homepage homepage;

    Workstacks workstacks;

    SearchForm searchForm;

    @When("^I click the search button on the search page$")
    public void clickSearchButtonOnSearchPageWithNoCriteria() {
        homepage.selectSearchPage();
        searchForm.clickSearchButton();
    }

    @Then("^an error message should be displayed as I have not entered any search criteria$")
    public void assertThatNoSearchCriteriaErrorMessageIsShown() {
        searchForm.assertNoSearchCriteriaErrorMessage();
    }

    @When("^I enter a valid search query$")
    public void enterValidCaseReferenceForSearch() {
        homepage.selectCaseReferenceSearchBar("MIN/0120069/19");
        homepage.clickCaseReferenceSearchFindButton();
    }

    @Then("^I should be taken directly to the case$")
    public void assertThatCaseReferenceSearchTakesUserToCase() {
        workstacks.sleep(500);

        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            workstacks.assertCaseReferenceBeforeAllocation();
        } else {
            workstacks.assertCaseReferenceAfterAllocation();
        }

    }

    @When("^I enter a non-existent case reference$")
    public void enterInvalidCaseReferenceForSearch() {
        homepage.selectCaseReferenceSearchBar("MIN/0000000/19");
        homepage.clickCaseReferenceSearchFindButton();
    }

    @Then("^an error message should be displayed stating that there are no active workflows for the case$")
    public void assertThatNoActiveWorkflowsErrorMessageIsShown() {
        homepage.assertNoActiveWorkflowsForCaseErrorMessage();
    }

    @When("^I click the find button$")
    public void clickSearchFindButton() {
        homepage.clickCaseReferenceSearchFindButton();
    }

    @Then("^an error message should be displayed stating that a case reference is required$")
    public void assertThatCaseReferenceIsRequiredMessageIsShown() {
        homepage.assertCaseReferenceIsRequiredErrorMessage();
    }

    @When("^I search by the case type \"([^\"]*)\"$")
    public void selectCaseTypeCheckbox(String caseType) {
        homepage.selectSearchPage();
        searchForm.sleep(500);
        switch (caseType.toUpperCase()) {
            case "MIN":
                searchForm.selectMINCheckbox();
                break;
            case "DTEN":
                searchForm.selectDTENCheckbox();
                break;
            case "TRO":
                searchForm.selectTROCheckbox();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        searchForm.clickSearchButton();

    }

    @Then("^only the chosen \"([^\"]*)\" case type results should be displayed in the results list$")
    public void assertThatOnlySelectedCaseTypeResultsAreDisplayed(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                searchForm.assertThatDTENCaseIsNotVisible();
                searchForm.assertThatTROCaseIsNotVisible();
                break;
            case "DTEN":
                searchForm.assertThatMINCaseIsNotVisible();
                searchForm.assertThatTROCaseIsNotVisible();
                break;
            case "TRO":
                searchForm.assertThatMINCaseIsNotVisible();
                searchForm.assertThatDTENCaseIsNotVisible();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

    }

    @When("^I search by the case type \"([^\"]*)\" and another parameter \"([^\"]*)\"$")
    public void searchByCaseTypeAndAnotherParameter(String caseType, String anotherParameter) {
        homepage.selectSearchPage();
        switch (caseType.toUpperCase()) {
            case "MIN":
                searchForm.selectMINCheckbox();
                break;
            case "DTEN":
                searchForm.selectDTENCheckbox();
                break;
            case "TRO":
                searchForm.selectTROCheckbox();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (anotherParameter.toUpperCase()) {
            case "BOB CORRESPONDENT":
                searchForm.enterSearchCorrespondent("Bob");
                break;
            case "KITTENS TOPIC":
                searchForm.enterSearchTopic("Cardiff University Kittens");
                break;
            default:
                pendingStep(anotherParameter + " is not defined within " + getMethodName());
        }
        searchForm.clickSearchButton();
    }

    @Then("^cases that are \"([^\"]*)\" case type that also contain another parameter \"([^\"]*)\" should be displayed in "
            + "the results "
            + "list$")
    public void assertThatSearchResultsContainCaseTypeAndAnotherParameter(String caseType, String anotherParameter) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                searchForm.assertThatDTENCaseIsNotVisible();
                searchForm.assertThatTROCaseIsNotVisible();
                break;
            case "DTEN":
                searchForm.assertThatMINCaseIsNotVisible();
                searchForm.assertThatTROCaseIsNotVisible();
                break;
            case "TRO":
                searchForm.assertThatMINCaseIsNotVisible();
                searchForm.assertThatDTENCaseIsNotVisible();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (anotherParameter.toUpperCase()) {
            case "BOB CORRESPONDENT":
                searchForm.viewFirstSearchResultCaseSummary();
                searchForm.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
                break;
            case "KITTENS TOPIC":
                searchForm.viewFirstSearchResultCaseSummary();
                searchForm.assertThatSearchedTopicNameIsShownInCaseSummary();
                break;
            default:
                pendingStep(anotherParameter + " is not defined within " + getMethodName());
        }
    }

    @When("^I search by the correspondent name \"([^\"]*)\"$")
    public void enterCorrespondentNameSearchQuery(String correspondentName) {
        homepage.selectSearchPage();
        switch (correspondentName.toUpperCase()) {
            case "BOB":
                searchForm.enterSearchCorrespondent("Bob");
                break;
            case "CORBYN":
                searchForm.enterSearchCorrespondent("Rt Hon Jeremy Corbyn MP");
                break;
            default:
                pendingStep(correspondentName + " is not defined within " + getMethodName());
        }
        searchForm.clickSearchButton();
    }

    @Then("^cases with specified correspondent name \"([^\"]*)\" should be displayed in the results list$")
    public void assertThatFirstCaseInCorrespondentSearchResultMatchesSearchQuery(String correspondentName) {
        switch (correspondentName.toUpperCase()) {
            case "BOB":
                searchForm.viewFirstSearchResultCaseSummary();
                searchForm.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
                break;
            case "CORBYN":
                searchForm.viewFirstSearchResultCaseSummary();
                searchForm.assertThatSearchedCorrespondentNameIsShownInCaseSummary();
                break;
            default:
                pendingStep(correspondentName + " is not defined within " + getMethodName());

        }
    }


}


