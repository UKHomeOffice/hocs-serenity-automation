package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Documents extends BasePage {

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

    @FindBy(xpath = "//h2[contains(text(), 'Available Standard line')]")
    public WebElementFacade availableStandardLineHeader;

    @FindBy(css = "[value='Remove']")
    public WebElementFacade removeButton;

    @FindBy(xpath = "//td/strong[contains(text(), 'PENDING')]")
    public WebElementFacade pendingTag;

    @FindBy(xpath = "//td/strong[contains(text(), 'UPLOADED')]")
    public WebElementFacade uploadedTag;

    //Simple methods

    public void selectDocumentTypeByText(String docType) {
        String caseType = sessionVariableCalled("caseType");
        if(!caseType.equals("MPAM")) {
            docType = docType.toUpperCase();
        }
        documentTypeDropDown.selectByVisibleText(docType);
    }

    public void uploadDocumentOfSize(int fileSize) {
        upload("src/test/resources/documents/" + fileSize + "MB.docx").to(addDocument);
    }

    public void uploadDocumentOfType(String type) {
        setSessionVariable("docType").to(type);
        addDocument.withTimeoutOf(Duration.ofSeconds(5)).waitUntilPresent();
        upload("src/test/resources/documents/test." + type).to(addDocument);
    }


    //Multi-step methods

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
        safeClickOn(addDocumentsButton);
        selectDocumentTypeByText("ORIGINAL");
        uploadDocumentOfType("docx");
        safeClickOn(addButton);
    }

    public void addADraftDocumentAtDraftStage() {
        availableStandardLineHeader.withTimeoutOf(Duration.ofMinutes(1)).waitUntilVisible();
        safeClickOn(addDocumentsButton);
        selectDocumentTypeByText("DRAFT");
        uploadDocumentOfType("docx");
        safeClickOn(addButton);
    }

    public void addAFinalDocument() {
        safeClickOn(addDocumentsButton);
        selectDocumentTypeByText("FINAL");
        uploadDocumentOfType("docx");
        safeClickOn(addButton);
    }

    public void clickPreviewButtonForFile(String fileIdentifier) {
        WebElementFacade previewButton = findBy("//td[contains(text(), '" + fileIdentifier + "')]/following-sibling::td/a"
                + "[contains(text(), 'Preview')]");
        safeClickOn(previewButton);
    }

    public String getDocumentIDforFile(String fileIdentifier) {
        WebElementFacade downloadButton = findBy("//td[contains(text(), '" + fileIdentifier + "')]/following-sibling::td/a"
                + "[contains(text(), 'Download')]");
        downloadButton.waitUntilVisible();
        return downloadButton.getAttribute("href").split("/")[9];
    }

    public void clickRemoveLinkForFile(String fileIdentifier) {
        addDocumentLink.waitUntilVisible();
        WebElementFacade removeLink =
                findBy("//td[contains(text(), '" + fileIdentifier
                        + "')]/following-sibling::td/a[contains(text(), 'Remove')]");
        removeLink.waitUntilClickable().withTimeoutOf(Duration.ofSeconds(10));
        safeClickOn(removeLink);
    }

    public void clickRemoveButton() {
        safeClickOn(removeButton);
        manageDocumentsLink.waitUntilVisible();
    }

    //Assertions

    public void assertDocumentTypeIsRequiredErrorMessage() {
        documentTypeIsRequiredErrorMessage.shouldContainText("Document type is required");
    }

    public void assertDocumentIsRequiredErrorMessage() {
        documentIsRequiredErrorMessage.shouldContainText("Document is required");
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
        addDocumentErrorMessage.shouldContainText("file which is not allowed");
    }

    public void assertFileTooLargeErrorMessage() {
        addDocumentErrorMessage.shouldContainText("The total file size is too large.  If you are uploading multiple "
                + "files. Please try smaller batches.");
    }

    public void assertDocumentIsDisplayedInPreviewPane(String fileIdentifier) {
        String documentID = getDocumentIDforFile(fileIdentifier);
        String src = (String) ((JavascriptExecutor) getDriver())
                .executeScript("return document.getElementsByTagName(\"embed\")[0].getAttribute(\"src\");");
        assertThat(src.contains(documentID), is(true));
    }

    public void assertDocumentIsUnderHeader(String header) {
        WebElementFacade documentUnderHeader =
                findBy("//h2[text()='" + header + "']/following-sibling::table[1]//a[@download]");
        assertThat(documentUnderHeader.isVisible(), is(true));
    }

    public void assertPendingTagVisible() {
        waitFor(pendingTag);
        assertThat(pendingTag.isVisible(), is(true));
    }

    public void waitForFileToUpload(Object fileIdentifier) {
        WebElementFacade documentUploadedTag =
                findBy("//td[contains(text(), '" + fileIdentifier + "')]/following-sibling::td/a[@download]");
        documentUploadedTag.withTimeoutOf(Duration.ofMinutes(1)).waitUntilVisible();
    }

    public void assertDocumentPresentIs(Boolean condition) {
        assertThat(uploadedTag.isVisible()||pendingTag.isVisible(), is(condition));
    }
}