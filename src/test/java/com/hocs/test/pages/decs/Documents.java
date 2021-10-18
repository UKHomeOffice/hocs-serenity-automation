package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class Documents extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[text()='Documents']")
    public WebElementFacade documentsTab;

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

    @FindBy(xpath = "//td/strong[contains(text(), 'FAILED_CONVERSION')]")
    public WebElementFacade failedConversionTag;

    @FindBy(xpath = "//td/strong[contains(text(), 'UPLOADED')]")
    public WebElementFacade uploadedTag;

    @FindBy(xpath = "//strong[text()='Primary Draft']")
    public WebElementFacade primaryDraftDocumentTag;

    //Simple methods

    public void selectDocumentsTab() {
        if(!documentsTabIsActiveTab()) {
            safeClickOn(documentsTab);
        }
    }

    public boolean documentsTabIsActiveTab() {
        return documentsTab.getAttribute("class").contains("active");
    }

    public void selectDocumentTypeByText(String docType) {
        String caseType = sessionVariableCalled("caseType");
        if(!caseType.equals("MPAM") && !caseType.equals("WCS") && !caseType.equals("FOI") && !caseType.equals("IEDET")) {
            docType = docType.toUpperCase();
        }
        documentTypeDropDown.selectByVisibleText(docType);
    }

    public void selectADocumentType() {
        selectRandomOptionFromDropdownWithHeading("Document type");
    }

    public void uploadDocumentOfSize(int fileSize) {
        System.err.println(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator  + fileSize + "MB.docx");
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator  + fileSize + "MB.docx").to(addDocument);
    }

    public void uploadDocumentOfType(String type) {
        setSessionVariable("docType").to(type);
        addDocument.withTimeoutOf(Duration.ofSeconds(10)).waitUntilPresent();
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator + "test."  + type).to(addDocument);
    }

    public void uploadDocumentThatFailsConversion() {
        addDocument.withTimeoutOf(Duration.ofSeconds(10)).waitUntilPresent();
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator + "broken.jpg").to(addDocument);
    }


    //Multi-step methods

    public void bulkUploadDocuments(int documents) {
        String allFiles = "";
        for (int i = 1; i <= documents; i++) {
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                    "documents" +  File.separator + "test" + i + ".docx";
                        if (i != documents) {
                allFiles += filePath + "\n";
            } else {
                allFiles += filePath;
            }
        }
        addDocument.sendKeys(allFiles);
    }

    public void addADocumentOfType(String docType) {
        if (addDocumentsButton.isVisible()) {
            safeClickOn(addDocumentsButton);
        } else if (addDocumentLink.isVisible()) {
            safeClickOn(addDocumentLink);
        }
        selectDocumentTypeByText(docType);
        uploadDocumentOfType("docx");
        safeClickOn(addButton);
        waitABit(500);
    }

    public void addADraftDocumentAtDraftStage() {
        addDocumentsButton.withTimeoutOf(Duration.ofMinutes(1)).waitUntilVisible();
        safeClickOn(addDocumentsButton);
        selectDocumentTypeByText("DRAFT");
        uploadDocumentOfType("docx");
        safeClickOn(addButton);
        setSessionVariable("draft").to("docx");
        recordCaseData.addHeadingAndValueRecord("Primary draft document", "test.docx");
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
        waitFor(documentUnderHeader);
        assertThat(documentUnderHeader.isVisible(), is(true));
    }

    public void assertPendingTagVisible() {
        if (!pendingTag.isVisible()) {
            assertThat(pendingTag.isVisible(), is(true));
        } else {
            WebElementFacade downloadHypertext = findBy("//a[@download]");
            assertThat(downloadHypertext.isVisible(), is(true));
        }
    }

    public void assertFailedConversionTagVisible() {
        failedConversionTag.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
    }

    public void waitForFileToUpload(Object fileIdentifier) {
        WebElementFacade documentUploadedTag =
                findBy("//td[contains(text(), '" + fileIdentifier + "')]/following-sibling::td/a[@download]");
        documentUploadedTag.withTimeoutOf(Duration.ofMinutes(1)).waitUntilVisible();
    }

    public void assertDocumentPresentIs(Boolean condition) {
        assertThat(uploadedTag.isVisible()||pendingTag.isVisible(), is(condition));
    }

    public void assertVisibilityOfPrimaryDraftDocumentTag() {
        assertThat(primaryDraftDocumentTag.isVisible(), is(true));
    }

}