package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DocumentsStepDefs {

    private Page page;

    @And("^a document \"([^\"]*)\" previewable$")
    public void documentPreview(String preview) {
        switch (preview.toUpperCase()) {
            case "IS":

                break;
            case "IS NOT":

                break;
            default:
                fail("Please state whether a document 'IS' or 'IS NOT' previewable");
        }
    }

    @When("^I am viewing a case with \"([^\"]*)\" documents attached$")
    public void iAmViewingACaseWithDocumentsAttached(String arg0) {
        switch (arg0.toUpperCase()) {
            case "NO":
                break;
            case "NON PREVIEWABLE":
                break;
            case "PREVIEWABLE":
                break;
            default:
                fail(arg0
                        + " is not defined in DocumentsStepDefs.iAmViewingACaseWithDocumentsAttached()");
        }
    }

    @When("^a document has \"([^\"]*)\" processing$")
    public void aDocumentHasProcessing(String arg0) {

    }

    @And("^any files have a validation error$")
    public void anyFilesHaveAValidationError() {

    }

    @Then("^I \"([^\"]*)\" able to view the documents$")
    public void iAmAbleToViewDocuments(String view) {
        switch (view.toUpperCase()) {
            case "AM":
                page.associatedDocumentsIsDisplayed();
                break;
            case "AM NOT":
                page.associatedDocumentsIsNotDisplayed();
                break;
            default:
                fail("Please state I 'AM' or 'AM NOT' able to view the documents");
        }
    }

    @Then("^I \"([^\"]*)\" able to see a preview of the document$")
    public void iCanSeeAPreviewOfTheDocument(String view) {
        switch (view.toUpperCase()) {
            case "AM":

                break;
            case "AM NOT":

                break;
            default:
                fail("Please state I 'AM' or 'AM NOT' able to see a preview of the document");
        }
    }

    @When("^I remove a document from the case$")
    public void iRemoveADocumentFromACase() {

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
    public void iUploadMultipleFilesWithOneValidationError() {

    }

    @And("^invalid files are not uploaded$")
    public void invalidFilesAreNotUploaded() {

    }

    @And("^no preview or download buttons are available for that document$")
    public void noPreviewOrDownloadButtonsAreAvailableForThatDocument() {

    }

    @Then("^no preview link is available for that document$")
    public void noPreviewLinkIsAvailableForThatDocument() {

    }

    @Then("^the document is removed from the case$")
    public void theDocumentIsRemovedFromTheCase() {

    }

    @And("^valid files are uploaded$")
    public void validFilesAreUploaded() {

    }
}
