package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DocumentsStepDefs extends Page {

    AddDocuments addDocuments;

    @And("^a document \"([^\"]*)\" previewable$")
    public void documentPreview(String preview) {
        switch (preview.toUpperCase()) {
            case "IS":

                break;
            case "IS NOT":

                break;
            default:
                pendingStep(preview + " is not defined within " + getMethodName());
        }
    }

    @When("^I am viewing a case with \"([^\"]*)\" documents attached$")
    public void viewingACaseWithAttachedDocs(String arg0) {
        switch (arg0.toUpperCase()) {
            case "NO":
                break;
            case "NON PREVIEWABLE":
                break;
            case "PREVIEWABLE":
                break;
            default:
                pendingStep(arg0 + " is not defined within " + getMethodName());
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
                associatedDocumentsIsDisplayed();
                break;
            case "AM NOT":
                associatedDocumentsIsNotDisplayed();
                break;
            default:
                pendingStep(view + " is not defined within " + getMethodName());
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
                pendingStep(view + " is not defined within " + getMethodName());
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

    @Then("^I should see the \"([^\"]*)\" message$")
    public void iSeeTheMessage(String message) {
        switch (message.toUpperCase()) {
            case "DOCUMENT PENDING":
                break;
            case "DOCUMENT UPLOAD FAILED":
                break;
            case "NO DOCUMENTS":
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    @And("I upload a \"([^\"]*)\" document")
    public void IUploadADocument(String docType) {
        switch (docType.toUpperCase()) {
            case "ORIGINAL":
                addDocuments.addAOriginalDocument();
                break;
            case "DRAFT":
                addDocuments.addADraftDocument();
                break;
            case "FINAL":
                addDocuments.addAFinalDocument();
                break;
            default:
                pendingStep(docType + " is not defined within " + getMethodName());
        }
        clickOn(continueButton);
    }
}
