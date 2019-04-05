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

    @When("^I enter a valid search query$")
    public void enterValidCaseReferenceForSearch() {

        homepage.selectCaseReferenceSearchBar();
        homepage.clickCaseReferenceSearchFindButton();
    }

    @Then("^I should be taken directly to the case$")
    public void assertThatCaseReferenceSearchTakesUserToCase() {
        workstacks.sleep(500);

        if (workstacks.isElementDisplayed(workstacks.allocateToMeButton)) {
            workstacks.allocateToMeButton.click();
            workstacks.assertCaseReferenceInsideCase();
        } else {
            workstacks.assertCaseReferenceInsideCase();
        }

    }

    @When("^I enter a non-existent case reference$")
    public void enterInvalidCaseReferenceForSearch() {
        homepage.selectCaseReferenceSearchBar("MIN/0123456/19");
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


}


