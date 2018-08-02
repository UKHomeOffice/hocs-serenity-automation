package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ViewDocumentsStepDefs {

    private Page page;

    @When("^I am viewing a case with \"([^\"]*)\" (?:document|documents) attached$")
    public void iAmViewingACaseWithDocumentsAttached(String arg0) {
    }

    @When("^a document has \"([^\"]*)\" processing$")
    public void aDocumentHasProcessing(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I ([^\"]*) able to view the documents$")
    public void iAmAbleToViewDocuments(String view) {
        switch (view.toUpperCase()) {
            case "AM":
                page.associatedDocumentsIsDisplayed();
                break;
            case "AM NOT":
                page.associatedDocumentsIsNotDisplayed();
                break;
            default:
                fail(page
                        + " is not defined within GenericInputStepDefs class, iAmAbleToViewDocuments method");
        }
    }

}
