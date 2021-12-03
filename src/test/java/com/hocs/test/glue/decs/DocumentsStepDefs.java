package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Documents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DocumentsStepDefs extends BasePage {

    Documents documents;

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

    @And("I manage the documents of a new {string} case")
    public void iClickToManageTheDocumentsOfANewCase(String caseType) {
        createCase.createCSCaseOfTypeWithoutDocument(caseType);
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I manage the documents of a new case")
    public void iManageTheDocumentsOfANewCase() {
        createCase.createCSCaseOfRandomType();
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I manage the documents of a new DCU case")
    public void iManageTheDocumentsOfANewDCUCase() {
        createCase.createDCUCaseOfRandomType();
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I manage the documents of a new MPAM or MTS case")
    public void iManageTheDocumentsOfANewMPAMOrMTSCase() {
        createCase.createMPAMOrMTSCaseOfRandomType();
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I manage the documents of a new Complaints case")
    public void iManageTheDocumentsOfANewUKVIComplaintsCase() {
        createCase.createComplaintsCaseOfRandomType();
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I upload a {string} document")
    public void IUploadADocument(String docType) {
        switch (docType.toUpperCase()) {
            case "ORIGINAL":
                documents.addADocumentOfType("ORIGINAL");
                break;
            case "DRAFT":
                documents.addADraftDocumentAtDraftStage();
                break;
            case "REPLACEMENT DRAFT":
                documents.addADocumentOfType("DRAFT");
                setSessionVariable("replacement draft").to("docx");
                break;
            case "FINAL":
                documents.addADocumentOfType("FINAL");
                break;
            case "INITIAL RESPONSE":
                documents.addADocumentOfType("Initial response");
                break;
            case "INTERIM RESPONSE":
                documents.addADocumentOfType("Interim response");
                break;
            case "ACKNOWLEDGEMENT LETTER":
                documents.addADocumentOfType("Acknowledgement letter");
                break;
            case "FINAL RESPONSE":
                documents.addADocumentOfType("Final response");
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
        if (documents.addDocumentLink.isVisible()) {
            safeClickOn(documents.addDocumentLink);
        } else if (documents.addDocumentsButton.isVisible()) {
            safeClickOn(documents.addDocumentsButton);
        }
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
        documents.selectADocumentType();
        iUploadAFileThatIsMBInSize(fileSize1);
        documents.waitForFileToUpload(fileSize1);
        iClickManageDocuments();
        iClickAddDocuments();
        documents.selectADocumentType();
        iUploadAFileThatIsMBInSize(fileSize2);
        documents.waitForFileToUpload(fileSize2);
    }

    @Then("the {string} document should be selected to be displayed in the preview pane")
    public void theFileShouldBeDisplayedInThePreviewPane(String fileIdentifier) {
        documents.assertDocumentIsDisplayedInPreviewPane(fileIdentifier);
    }

    @And("I click the preview button of the {string} file")
    public void iClickThePreviewButtonOfTheFile(String fileIdentifier) {
        documents.clickPreviewButtonForFile(fileIdentifier);
    }

    @And("I add a {string} document to the case/claim")
    public void iAddADocumentToTheCase(String fileIdentifier) {
        iClickAddDocuments();
        documents.selectADocumentType();
        iUploadAFileOfType(fileIdentifier);
        iCanSeeTheFileInTheUploadedDocumentList(fileIdentifier);
    }

    @And("I add a {string} type document to the case")
    public void iAddATypeDocumentToTheCase(String docType) {
        iClickAddDocuments();
        documents.selectDocumentTypeByText(docType);
        iUploadAFileOfType("docx");
    }

    @And("I remove the {string} document")
    public void iClickTheRemoveLinkForTheFile(String fileIdentifier) {
        safeClickOn(documents.manageDocumentsLink);
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

    @And("I select a document type")
    public void iSelectADocumentType() {
        documents.selectADocumentType();
    }

    @And("I upload a file that fails to convert to PDF")
    public void iUploadAFileThatWillFailToConvertToPDF() {
        safeClickOn(documents.addDocumentLink);
        documents.selectADocumentType();
        documents.uploadDocumentThatFailsConversion();
        clickTheButton("Add");
    }

    @Then("document should have the Failed Conversion tag")
    public void documentShouldHaveTheFailedConversionTag() {
        documents.assertFailedConversionTagVisible();
    }

    @And("I confirm/approve the (new )primary draft document")
    public void iConfirmThePrimaryDraftDocument() {
        documents.confirmOrApprovePrimaryDraft();
    }

    @And("the selected document should be tagged as the primary draft")
    public void theSelectedDocumentShouldBeTaggedAsThePrimaryDraft() {
        documents.selectDocumentsTab();
        documents.assertThatPrimaryDraftIs(sessionVariableCalled("primaryDraft"));
    }

    @And("the document added at case creation should be listed under the {string} document type heading")
    public void theDocumentAddedAtCaseCreationShouldHaveTheDocumentType(String docType) {
        documents.selectDocumentsTab();
        documents.assertDocumentIsUnderHeader(docType);
    }
}
