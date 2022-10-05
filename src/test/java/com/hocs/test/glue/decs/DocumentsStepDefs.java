package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.complaints.BFProgressCase;
import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentsStepDefs extends BasePage {

    Documents documents;

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

    Dashboard dashboard;

    @And("I manage the documents of a new {string} case")
    public void iClickToManageTheDocumentsOfANewCase(String caseType) {
        createCase.createCSCaseOfType(caseType);
        confirmationScreens.goToCaseFromConfirmationScreen();
        documents.selectToManageDocuments();
    }

    @And("I manage the documents of a new case")
    public void iManageTheDocumentsOfANewCase() {
        createCase.createCSCaseOfRandomType();
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(documents.manageDocumentsLink);
    }

    @And("I click manage documents")
    public void iClickManageDocuments() {
        safeClickOn(documents.manageDocumentsLink);
    }

    @When("I click add documents")
    public void iClickAddDocuments() {
        documents.clickVisibleAddDocumentsLink();
    }

    @And("I choose the document type {string}")
    public void iChooseTheDocumentType(String docType) {
        documents.selectDocumentTypeByText(docType);
    }

    @And("I upload a file of type {string}")
    public void iUploadAFileOfType(String fileType) {
        documents.uploadFileOfType(fileType);
        clickAddButton();
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

    @Then("I can see the {string} file in the uploaded document list")
    public void iCanSeeTheFileInTheUploadedDocumentList(String fileType) {
        documents.waitForFileToUpload(fileType);
        documents.assertFileIsVisible(fileType);
    }

    @And("I cannot see the {string} file in the uploaded document list")
    public void iCannotSeeTheFileInTheUploadedDocumentList(String fileIdentifier) {
        documents.assertFileIsNotVisible(fileIdentifier);
    }

    @Then("the {string} document should be under the {string} header")
    public void theDocumentShouldBeUnderTheHeader(String fileIdentifier, String header) {
        documents.waitForFileToUpload(fileIdentifier);
        documents.assertDocumentIsUnderHeader(header);
    }

    @Then("the document should be listed under the expected Document Type header")
    public void theDocumentShouldBeListedUnderTheExpectedDocumentTypeHeader() {
        documents.waitForFileToUpload(sessionVariableCalled("fileType"));
        documents.assertDocumentIsUnderHeader(sessionVariableCalled("documentType"));
    }

    @And("the document added at case creation should be listed under the {string} document type heading")
    public void theDocumentAddedAtCaseCreationShouldHaveTheDocumentType(String docType) {
        documents.selectDocumentsTab();
        documents.assertDocumentIsUnderHeader(docType);
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
        documents.clickVisibleAddDocumentsLink();
        documents.selectADocumentType();
        iUploadAFileThatIsMBInSize(fileSize1);
        documents.waitForFileToUpload(fileSize1);
        iClickManageDocuments();
        documents.clickVisibleAddDocumentsLink();
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

    @And("I add a {string} type file to the case as a document")
    public void iAddADocumentToTheCaseClaimByUploadingAFile(String fileType) {
        documents.addADocumentOfFileType(fileType);
        iCanSeeTheFileInTheUploadedDocumentList(fileType);
    }

    @And("I add a/an {string} type document to the case")
    public void iAddATypeDocumentToTheCase(String docType) {
        documents.addADocumentOfDocumentType(docType);
        iCanSeeTheFileInTheUploadedDocumentList(sessionVariableCalled("fileType"));
        documents.assertDocumentIsUnderHeader(docType);
    }

    @And("I remove the {string} document")
    public void iClickTheRemoveLinkForTheFile(String fileIdentifier) {
        safeClickOn(documents.manageDocumentsLink);
        documents.clickRemoveLinkForFile(fileIdentifier);
        documents.clickRemoveButton();
    }

    @And("I select the {string} document as the primary draft")
    public void iSelectTheDocumentAsThePrimaryDraft(String document) {
        documents.selectPrimaryDraft(sessionVariableCalled(document));
        documents.recordPrimaryDraftDocument();
    }

    @And("I confirm/approve the (new )primary draft document")
    public void iConfirmThePrimaryDraftDocument() {
        if (checkButtonIsVisible("Continue")) {
            clickTheButton("Continue");
        } else {
            clickTheButton("Approve primary draft");
        }
    }

    @And("I upload my Primary {string} document")
    public void iUploadMyPrimaryDraftDocument(String docType) {
        iAddATypeDocumentToTheCase(docType);
        documents.recordPrimaryDraftDocument();
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
        documents.clickVisibleAddDocumentsLink();
        documents.selectADocumentType();
        documents.uploadDocumentThatFailsConversion();
        clickTheButton("Add");
    }

    @When("I upload a file that fails during the virus scan")
    public void iUploadAFileThatFailsDuringTheVirusScan() {
        documents.clickVisibleAddDocumentsLink();
        documents.selectADocumentType();
        documents.uploadDocumentThatFailsScan();
        clickTheButton("Add");
    }

    @Then("document should have the Failed Conversion tag")
    public void documentShouldHaveTheFailedConversionTag() {
        documents.assertFailedConversionTagVisible();
    }

    @Then("document should have the Failed Virus Scan tag")
    public void documentShouldHaveTheFailedVirusScanTag() {
        documents.assertFailedVirusScanTagVisible();
    }

    @And("the selected/replacement document should be tagged as the primary draft")
    public void theSelectedDocumentShouldBeTaggedAsThePrimaryDraft() {
        documents.refreshDocumentTab();
        try {
            documents.assertThatPrimaryDraftIs(sessionVariableCalled("primaryDraft"));
        } catch (NullPointerException e) {
            documents.refreshDocumentTab();
            documents.assertThatPrimaryDraftIs(sessionVariableCalled("primaryDraft"));
        }
    }

    @And("I select a file to be uploaded as a PIT Extension document")
    public void iSelectAFileToBeUploadedAsAPITExtensionDocument() {
        documents.uploadFileOfType("docx");
    }

    @And("I select a document to be added to the case as an Appeal Response document")
    public void iSelectADocumentToBeAddedToTheCaseAsAnAppealResponseDocument() {
        clickTheLink("Add a document");
        documents.uploadFileOfType("docx");
        clickAddButton();
    }

    @And("I upload another {string} document as a replacement")
    public void iUploadAnotherDocumentAsAReplacement(String docType) {
        documents.addADocumentOfDocumentTypeAndFileType(docType, "txt");
        setSessionVariable("replacement draft").to("txt");
    }

    @And("I add a document to the case as the (first )Registration user")
    public void iAddADocumentToTheCaseAsTheFirstRegistrationUser() {
        documents.addADocumentOfFileType("docx");
    }

    @And("I add a document to the case as the (second Registration )/(Casework )user")
    public void iAddADocumentToTheCaseAsTheSecondRegistrationUser() {
        dashboard.ensureViewingCurrentCase();
        documents.selectDocumentsTab();
        documents.selectToManageDocuments();
        documents.addADocumentOfFileType("txt");
    }

    @Then("I should not be able to see the document uploaded by the previous user")
    public void iShouldNotBeAbleToSeeTheDocumentUploadedByThePreviousUser() {
        documents.assertFileIsNotVisible("docx");
    }

    @And("I should be able to see the document uploaded by the current user")
    public void iShouldBeAbleToSeeTheDocumentUploadedByTheCurrentUser() {
        documents.assertFileIsVisible("txt");
    }

    @Then("I should be able to see the document uploaded by the previous user")
    public void iShouldBeAbleToSeeTheDocumentUploadedByThePreviousUser() {
        documents.assertFileIsVisible("docx");
    }

    @Then("I should see a dropdown containing the expected Document Types for the case I am working on")
    public void iShouldSeeADropdownContainingTheExpectedDocumentTypesForTheCaseIAmWorkingOn() {
        documents.assertExpectedDocumentTypesPresent(getCurrentCaseType());
    }
}
