package com.hocs.test.glue;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;

public class ViewDocumentsStepDefs {

    @When("^I am viewing a case with \"([^\"]*)\" (?:document|documents) attached$")
    public void iAmViewingACaseWithDocumentsAttached(String arg0) throws Throwable {
    }

    @When("^a document has \"([^\"]*)\" processing$")
    public void aDocumentHasProcessing(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
