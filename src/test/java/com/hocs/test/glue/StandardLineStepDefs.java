package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.standard_line.StandardLine;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StandardLineStepDefs extends Page {

    Homepage homepage;

    StandardLine standardLine;

    AddDocuments addDocuments;

    @When("^I click the add button on the create standard line screen$")
    public void clickAddButtonOnStandardLineScreen() {
        clickOn(standardLine.associatedTopicTypeahead);
        clickOn(standardLine.addButton);
    }

    @Then("^an error message should be displayed as I have not selected an associated topic for the standard line$")
    public void assertThatAssociatedTopicErrorMessageIsShown() {
        standardLine.assertAssociatedTopicErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered an expiration date for the standard line$")
    public void assertThatExpirationDateErrorMessageIsShown() {
        standardLine.assertExpirationDateIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I have not added a document for the standard line$")
    public void assertThatNoAddedDocumentErrorMessageIsShown() {
        standardLine.assertDocumentIsRequiredErrorMessage();
    }

    @When("^I create a new standard line$")
    public void createNewStandardLineAndClickAddButton() {
        clickOn(standardLine.associatedTopicTypeahead);
        standardLine.enterExpirationDate();
        addDocuments.uploadStandardLineDocument();
        clickOn(standardLine.addButton);
        clickOn(homepage.animalsInScienceTeam);

    }
}
