package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;


public class SearchFormStepDefs {

    @Managed
    WebDriver driver;

    Page page;

    Homepage homepage;

    Workstacks workstacks;

    @When("^I enter a valid search query$")
    public void enterValidCaseReferenceForSearch() {
        homepage.selectCaseReferenceSearchBar("MIN/0122444/19");
        homepage.clickCaseReferenceSearchFindButton();
    }

//    @Then("^I should be taken directly to the case$")
//    public void assertThatCaseReferenceSearchTakesUserToCase() {
//        homepage.assertCaseReferenceInsideCase();
//    }

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
    public void assertThatCaseReferenceIsRequiredMessageIsShown(){
        homepage.assertCaseReferenceIsRequiredErrorMessage();
    }
}
