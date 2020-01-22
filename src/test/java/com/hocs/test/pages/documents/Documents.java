package com.hocs.test.pages.documents;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.base_page.Page;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;

public class Documents extends Page {

    @FindBy(xpath = "//a[text() = 'Manage Documents']")
    public WebElementFacade manageDocumentsLink;

    @FindBy(xpath = "//a[text() = 'Add document']")
    public WebElementFacade addDocumentLink;

    @FindBy(id = "add_document")
    public WebElementFacade addDocument;

    @FindBy(xpath = "//a[text()='Document type is required']")
    public WebElementFacade documentTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Document is required']")
    public WebElementFacade documentIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href = '#add_document-error']")
    public WebElementFacade addDocumentErrorMessage;

    @FindBy(xpath = "//a[text()='document']")
    public WebElementFacade addDocumentsButton;

    @FindBy(id = "document_type")
    public WebElementFacade documentTypeDropDown;

    @FindBy(xpath = "//caption[contains(text(), 'Available Standard line')]")
    public WebElementFacade availableStandardLineHeader;

    @FindBy(css = "[value='Remove']")
    public WebElementFacade removeButton;

    @FindBy(xpath = "//td[contains(text(), '.docx')]/preceding-sibling::td/strong[contains(text(), 'PENDING')]")
    public WebElementFacade pendingTag;

    //Simple methods

    public void selectDocumentTypeByIndex(int index) {
        documentTypeDropDown.selectByIndex(index);
    }

    public void uploadDocxDocument() {
        upload("src/test/resources/documents/test.docx").to(addDocument);
    }

    public void uploadDOCXDocument() {
        upload("src/test/resources/documents/testDOCX.DOCX").to(addDocument);
    }

    public void uploadTxtDocument() {
        upload("src/test/resources/documents/test.txt").to(addDocument);
    }

    public void uploadTXTDocument() {
        upload("src/test/resources/documents/testTXT.TXT").to(addDocument);
    }

    public void uploadPdfDocument() {
        upload("src/test/resources/documents/test.pdf").to(addDocument);
    }

    public void uploadPDFDocument() {
        upload("src/test/resources/documents/testPDF.PDF").to(addDocument);
    }

    public void uploadXlsxDocument() {
        upload("src/test/resources/documents/test.xlsx").to(addDocument);
    }

    public void uploadXLSXDocument() {
        upload("src/test/resources/documents/testXLSX.XLSX").to(addDocument);
    }

    public void uploadCsvDocument() {
        upload("src/test/resources/documents/test.csv").to(addDocument);
    }

    public void upload51MBDocument() {
        upload("src/test/resources/documents/51MB.docx").to(addDocument);
    }

    public void upload5MBDocument() {
        upload("src/test/resources/documents/5MB.docx").to(addDocument);
    }

    public void uploadTiffDocument() {
        upload("src/test/resources/documents/test.tiff").to(addDocument);
    }

    public void uploadTIFFDocument() {
        upload("src/test/resources/documents/testTIFF.TIFF").to(addDocument);
    }

    //Multi-step methods

    public void uploadDocxAndTxtDocuments() {
        String workingDir = System.getProperty("user.dir");
        String filePath = workingDir + "\\src\\test\\resources\\documents\\test.docx" + "\n" + workingDir + "\\src\\test"
                + "\\resources\\documents\\test.txt";
        addDocument.sendKeys(filePath);
    }

    public void bulkUploadDocuments(int documents) {
        String allFiles = "";
        for (int i = 1; i <= documents; i++) {
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + "\\src\\test\\resources\\documents\\test" + i + ".docx";
            if (i != documents) {
                allFiles += filePath + "\n";
            } else {
                allFiles += filePath;
            }
        }
        addDocument.sendKeys(allFiles);
    }

    public void addAOriginalDocument() {
        clickOn(addDocumentsButton);
        selectDocumentTypeByIndex(1);
        uploadDocxDocument();
        clickOn(addButton);
    }

    public void addADraftDocumentAtDraftStage() {
        availableStandardLineHeader.withTimeoutOf(30, TimeUnit.SECONDS).waitUntilVisible();
        clickOn(addDocumentsButton);
        selectDocumentTypeByIndex(2);
        uploadDocxDocument();
        clickOn(addButton);
    }

    public void addAFinalDocument() {
        clickOn(addDocumentsButton);
        selectDocumentTypeByIndex(3);
        uploadDocxDocument();
        clickOn(addButton);
    }

    public void clickPreviewButtonForFile(String fileIdentifier) {
        WebElementFacade previewButton = findBy("//td[contains(text(), '" + fileIdentifier + "')]/following-sibling::td/a"
                + "[contains(text(), 'Preview')]");
        previewButton.click();
    }

    public String getDocumentIDforFile(String fileIdentifier) {
        WebElementFacade downloadButton = findBy("//td[contains(text(), '" + fileIdentifier + "')]/following-sibling::td/a"
                + "[contains(text(), 'Download')]");
        downloadButton.waitUntilVisible();
        return downloadButton.getAttribute("href").split("/")[9];
    }

    public void clickRemoveLinkForFile(String fileIdentifier) {
        WebElementFacade removeLink =
                findBy("//td[contains(text(), '" + fileIdentifier
                        + "')]/following-sibling::td/a[contains(text(), 'Remove')]");
        removeLink.click();
    }

    public void clickRemoveButton() {
        removeButton.click();
        manageDocumentsLink.waitUntilVisible();
    }

    //Assertions

    public void assertDocumentTypeIsRequiredErrorMessage() {
        assertThat(documentTypeIsRequiredErrorMessage.getText(), is("Document type is required"));
    }

    public void assertDocumentIsRequiredErrorMessage() {
        assertThat(documentIsRequiredErrorMessage.getText(), is("Document is required"));
    }

    public void assertFileIsVisible(String fileType) {
        WebElementFacade uploadedFile = findBy(
                "//table//td[contains(text(), '." + fileType + "')]");
        assertThat(uploadedFile.isVisible(), is(true));
    }

    public void assertFileIsNotVisible(String fileIdentifier) {
        if (!manageDocumentsLink.isCurrentlyVisible()) {
            clickBackButton();
        }
        WebElementFacade uploadedFile = findBy(
                "//caption[text() = 'Documents']/parent::table//td[contains(text(), '." + fileIdentifier + "')]");
        assertThat(uploadedFile.isVisible(), is(false));
    }

    public void assertDocumentHasTag(String tag) {
        manageDocumentsLink.waitUntilVisible();
        WebElementFacade documentTag =
                findBy("//td[contains(text(), '.docx')]/preceding-sibling::td/strong[contains(text(), '" + tag + "')]");
        assertThat(documentTag.isVisible(), is(true));
    }

    public void assertFileTypeIsNotAllowedErrorMessage() {
        assertThat(addDocumentErrorMessage.getText().contains("file which is not allowed"), is(true));
    }

    public void assertFileTooLargeErrorMessage() {
        assertThat(addDocumentErrorMessage.getText(), is("The total file size is too large.  If you are uploading multiple "
                + "files. Please try smaller batches."));
    }

    public void assertDocumentIsDisplayedInPreviewPane(String fileIdentifier) {
        String documentID = getDocumentIDforFile(fileIdentifier);
        String src = (String) ((JavascriptExecutor) getDriver())
                .executeScript("return document.getElementsByTagName(\"embed\")[0].getAttribute(\"src\");");
        assertThat(src.contains(documentID), is(true));
    }

    public void assertDocumentIsUnderHeader(String header) {
        pendingTag.waitUntilVisible();
        WebElementFacade documentUnderHeader =
                findBy("//h2[text()='" + header + "']/following-sibling::table[1]//strong[text()='PENDING']");
        assertThat(documentUnderHeader.isVisible(), is(true));
    }

    public void assertPendingTagVisible() {
        waitFor(pendingTag);
        assertThat(pendingTag.isVisible(), is(true));
    }

    public void waitForFileToUpload() {
        pendingTag.waitUntilVisible();
        pendingTag.withTimeoutOf(20, TimeUnit.SECONDS).waitUntilNotVisible();
    }
}