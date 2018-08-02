package com.hocs.test.glue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class UploadDocumentsStepDefs {

    @And("^invalid files are not uploaded$")
    public void invalidFilesAreNotUploaded() {

    }

    @When("^I upload (\\d+) valid (?:document|documents)$")
    public void iUploadAValidDocument() {

    }

    @When("^I upload a document that is not on the whitelist$")
    public void iUploadADocumentThatIsNotOnTheWhitelist() {

    }

    @When("^I upload a document that is greater than the file size limits$")
    public void iUploadADocumentThatIsGreaterThanTheFileSizeLimits() {

    }

    @When("^I upload multiple files with one validation error$")
    public void iUploadMultipleFilesWithOneValidationError()  {

    }

    @And("^valid files are uploaded$")
    public void validFilesAreUploaded() {

    }
}
