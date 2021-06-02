package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Documents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DocumentsStepDefs extends BasePage {

    Documents documents;

    CreateCase createCase;

    CreateCase_SuccessPage createCaseSuccessPage;

    @And("I click to manage the documents of a new {string} case")
    public void iClickToManageTheDocumentsOfANewCase(String caseType) {
        createCase.createCaseOfTypeWithoutDocument(caseType);
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I upload a {string} document")
    public void IUploadADocument(String docType) {
        switch (docType.toUpperCase()) {
            case "ORIGINAL":
                documents.addAOriginalDocument();
                break;
            case "DRAFT":
                documents.addADraftDocumentAtDraftStage();
                break;
            case "SECOND DRAFT":
                documents.addADraftDocumentAtQAStage();
                break;
            case "FINAL":
                documents.addAFinalDocument();
                break;
            default:
                pendingStep(docType + " is not defined within " + getMethodName());
        }
    }

    @And("I click manage documents")
    public void iClickManageDocuments() {
        safeClickOn(documents.manageDocumentsLink);
    }

    @When("I click add documents")
    public void iClickAddDocuments() {
        safeClickOn(documents.addDocumentLink);
    }

    @And("I choose the document type {string}")
    public void iChooseTheDocumentType(String docType) {
        documents.selectDocumentTypeByText(docType);
    }

    @And("I upload a file of type {string}")
    public void iUploadAFileOfType(String fileType) {
        documents.uploadDocumentOfType(fileType);
        clickAddButton();
    }

    @Then("I can see the {string} file in the uploaded document list")
    public void iCanSeeTheFileInTheUploadedDocumentList(String fileType) {
        documents.waitForFileToUpload(fileType);
        documents.assertFileIsVisible(fileType);
    }

    @Then("the document should have the {string} tag")
    public void theDocumentShouldHaveTheTag(String Tag) {
        documents.assertDocumentHasTag(Tag.toUpperCase());
    }

    @Then("an error message should be displayed as I have not selected a document type")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedADocumentType() {
        documents.assertDocumentTypeIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected a file to upload")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedAFileToUpload() {
        documents.assertDocumentIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I have selected a file type which is not allowed")
    public void anErrorMessageShouldBeDisplayedAsIHaveSelectedAFileTypeWhichIsNotAllowed() {
        documents.assertFileTypeIsNotAllowedErrorMessage();
    }

    @And("I cannot see the {string} file in the uploaded document list")
    public void iCannotSeeTheFileInTheUploadedDocumentList(String fileIdentifier) {
        documents.assertFileIsNotVisible(fileIdentifier);
    }

    @And("I upload a file that is {int}MB in size")
    public void iUploadAFileThatIsMBInSize(int fileSize) {
        documents.uploadDocumentOfSize(fileSize);
        clickTheButton("Add");
    }

    @Then("an error message should be displayed as I have selected a file which is larger than the allowed limit")
    public void anErrorMessageShouldBeDisplayedAsIHaveSelectedAFileWhichIsLargerThanTheAllowedLimit() {
        documents.assertFileTooLargeErrorMessage();
    }

    @And("I upload a {int}MB and a {int}MB file")
    public void iUploadTwoFilesOfSizes(int fileSize1, int fileSize2) {
        iClickAddDocuments();
        iChooseTheDocumentType("Draft");
        iUploadAFileThatIsMBInSize(fileSize1);
        documents.waitForFileToUpload(fileSize1);
        iClickManageDocuments();
        iClickAddDocuments();
        iChooseTheDocumentType("Draft");
        iUploadAFileThatIsMBInSize(fileSize2);
        documents.waitForFileToUpload(fileSize2);
    }

    @Then("the {string} document should be select to be displayed in the preview pane")
    public void theFileShouldBeDisplayedInThePreviewPane(String fileIdentifier) {
        documents.assertDocumentIsDisplayedInPreviewPane(fileIdentifier);
    }

    @And("I click the preview button of the {string} file")
    public void iClickThePreviewButtonOfTheFile(String fileIdentifier) {
        documents.clickPreviewButtonForFile(fileIdentifier);
    }

    @And("I add a {string} document to the case")
    public void iAddADocumentToTheCase(String fileIdentifier) {
        iClickAddDocuments();
        iChooseTheDocumentType("Original correspondence");
        iUploadAFileOfType(fileIdentifier);
        iCanSeeTheFileInTheUploadedDocumentList(fileIdentifier);
    }

    @And("I select to remove the {string} document")
    public void iClickTheRemoveLinkForTheFile(String fileIdentifier) {
        documents.clickRemoveLinkForFile(fileIdentifier);
        documents.clickRemoveButton();
    }

    @Then("the document should have the Pending tag")
    public void theDocumentShouldHaveThePendingTag() {
        documents.assertPendingTagVisible();
    }

    @Then("the {string} document should be under the {string} header")
    public void theDocumentShouldBeUnderTheHeader(String fileIdentifier, String header) {
        documents.waitForFileToUpload(fileIdentifier);
        documents.assertDocumentIsUnderHeader(header);
    }

    @Then("the primary draft tag is next to the primary draft document")
    public void primaryDraftTagNextToPrimaryDraftDocument() {
        documents.assertVisibilityOfPrimaryDraftDocumentTag();
    }
}
