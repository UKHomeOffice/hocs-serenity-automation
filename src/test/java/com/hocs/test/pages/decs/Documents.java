package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import config.CaseType;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Documents extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[text()='Documents']")
    public WebElementFacade documentsTab;

    @FindBy(xpath = "//a[text() = 'Manage Documents']")
    public WebElementFacade manageDocumentsLink;

    @FindBy(id = "add_document")
    public WebElementFacade addDocument;

    @FindBy(xpath = "//a[text()='Document type is required']")
    public WebElementFacade documentTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Document is required']")
    public WebElementFacade documentIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href = '#add_document-error']")
    public WebElementFacade addDocumentErrorMessage;

    @FindBy(id = "document_type")
    public WebElementFacade documentTypeDropDown;

    @FindBy(xpath = "//h2[contains(text(), 'Available Standard line')]")
    public WebElementFacade availableStandardLineHeader;

    @FindBy(css = "[value='Remove']")
    public WebElementFacade removeButton;

    @FindBy(xpath = "//td/strong[contains(text(), 'Pending')]")
    public WebElementFacade pendingTag;

    @FindBy(xpath = "//td/strong[contains(text(), 'Failed PDF Conversion')]")
    public WebElementFacade failedConversionTag;

    @FindBy(xpath = "//td/strong[contains(text(), 'Malware Found')]")
    public WebElementFacade failedVirusScanTag;

    @FindBy(xpath = "//td/strong[contains(text(), 'UPLOADED')]")
    public WebElementFacade uploadedTag;

    @FindBy(xpath = "//strong[text()='Primary Draft']")
    public WebElementFacade primaryDraftDocumentTag;

    @FindBy(xpath = "//strong[text()='Primary Draft']/parent::td/preceding-sibling::td")
    public WebElementFacade primaryDraftDocumentName;

    //Simple methods

    public void selectDocumentsTab() {
        selectTheTab("Documents");
    }

    public void refreshDocumentTab() {
        refreshTheTab("Documents");
    }

    public void selectDocumentTypeByText(String docType) {
        waitABit(4000);
        if(dcuCase()) {
            docType = docType.toUpperCase();
        }
        documentTypeDropDown.waitUntilVisible().selectByVisibleText(docType);
    }

    public void selectADocumentType() {
        String selectedDocumentType = selectRandomOptionFromDropdownWithHeading("Document type");
        setSessionVariable("documentType").to(selectedDocumentType);
    }

    public void uploadDocumentOfSize(int fileSize) {
        System.err.println(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator  + fileSize + "MB.docx");
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator  + fileSize + "MB.docx").to(addDocument);
    }

    public void uploadFileOfType(String fileType) {
        setSessionVariable("fileType").to(fileType);
        addDocument.withTimeoutOf(Duration.ofSeconds(10)).waitUntilPresent();
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator + "test."  + fileType).to(addDocument);
    }

    public void uploadDocumentThatFailsConversion() {
        addDocument.withTimeoutOf(Duration.ofSeconds(10)).waitUntilPresent();
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator + "broken.jpg").to(addDocument);
    }

    public void uploadDocumentThatFailsScan() {
        addDocument.withTimeoutOf(Duration.ofSeconds(10)).waitUntilPresent();
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator + "fails.txt").to(addDocument);
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

    public void addADocumentOfDocumentType(String docType) {
        waitABit(4000);
        clickVisibleAddDocumentsLink();
        selectDocumentTypeByText(docType);
        uploadFileOfType("docx");
        clickAddButton();
        setSessionVariable(docType.toLowerCase()).to("docx");
        waitABit(1000);
    }

    public void clickVisibleAddDocumentsLink() {
        List<WebElementFacade> addDocumentLinks = findAll("//a[contains(text(),'Add') and contains(.,'document')]");
        for ( WebElementFacade addDocumentLink : addDocumentLinks
        ) {
            if (addDocumentLink.isVisible()) {
                safeClickOn(addDocumentLink);
                break;
            }
        }
        waitForDECSPageWithTitle("Add Documents");
    }

    public void addADocumentOfFileType(String fileType) {
        clickVisibleAddDocumentsLink();
        selectADocumentType();
        uploadFileOfType(fileType);
        clickAddButton();
        waitABit(500);
    }

    public void addADocumentOfDocumentTypeAndFileType(String docType, String fileType) {
        clickVisibleAddDocumentsLink();
        selectDocumentTypeByText(docType);
        uploadFileOfType(fileType);
        clickAddButton();
        waitABit(500);
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
        waitForDECSPageWithTitle("Manage Documents");
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
                "//dd[contains(text(), '." + fileType + "')]");
        assertThat(uploadedFile.isVisible(), is(true));
    }

    public void assertFileIsNotVisible(String fileIdentifier) {
        if (!manageDocumentsLink.isCurrentlyVisible()) {
            clickTheLink("Back");
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
                findBy("//h2[text()='" + header + "']/following-sibling::dl [1]//a[@download]");
        documentUnderHeader.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        assertThat(documentUnderHeader.isVisible(), is(true));
    }

    public void assertFailedConversionTagVisible() {
        failedConversionTag.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
    }

    public void assertFailedVirusScanTagVisible() {
        failedVirusScanTag.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
    }

    public void waitForFileToUpload(Object fileIdentifier) {
        WebElementFacade documentUploadedTag =
                findBy("//dd[contains(text(), '" + fileIdentifier + "')]/parent::div//following-sibling::dd//a[@download]");
        documentUploadedTag.withTimeoutOf(Duration.ofMinutes(3)).waitUntilVisible();
    }

    public void assertDocumentPresentIs(Boolean condition) {
        assertThat(uploadedTag.isVisible()||pendingTag.isVisible(), is(condition));
    }

    public void assertVisibilityOfPrimaryDraftDocumentTag() {
        assertThat(primaryDraftDocumentTag.isVisible(), is(true));
    }

    public void selectPrimaryDraft(String fileIdentifier) {
        WebElementFacade documentToSelect = find(By.xpath("//label[contains(text(),'"+ fileIdentifier +"')]"));
        safeClickOn(documentToSelect);
    }

    public void recordPrimaryDraftDocument() {
        WebElementFacade selectedPrimaryDraftDocument = findBy("//input[contains(@name,'Draft')][@checked]/following-sibling::label");
        WebElementFacade selectedPrimaryDraftHeading = findBy("//input[contains(@name,'Draft')][@checked]/ancestor::fieldset//span");
        selectedPrimaryDraftDocument.waitUntilVisible();
        recordCaseData.addHeadingAndValueRecord(selectedPrimaryDraftHeading.getText(), selectedPrimaryDraftDocument.getText());
        setSessionVariable("primaryDraft").to(selectedPrimaryDraftDocument.getText());
    }

    public void recordFinalResponseDocument() {
        WebElementFacade selectedPrimaryDraftDocument = findBy("//input[@name='FinalResponse'][@checked]/following-sibling::label");
        WebElementFacade selectedPrimaryDraftHeading = findBy("//input[@name='FinalResponse'][@checked]/ancestor::fieldset//span");
        selectedPrimaryDraftDocument.waitUntilVisible();
        recordCaseData.addHeadingAndValueRecord(selectedPrimaryDraftHeading.getText(), selectedPrimaryDraftDocument.getText());
        setSessionVariable("finalResponse").to(selectedPrimaryDraftDocument.getText());
    }

    public void assertThatPrimaryDraftIs(String fileName) {
        primaryDraftDocumentName.waitUntilVisible();
        primaryDraftDocumentName.shouldContainText(fileName);
    }

    public void selectToManageDocuments() {
        safeClickOn(manageDocumentsLink);
    }

    public void assertExpectedDocumentTypesPresent(CaseType caseType) {
        List<String> availableDocumentTypes = getSelectableOptionsFromDropdownWithHeading("Document type");
        List<String> requiredDocumentTypes = new ArrayList<>();
        switch (caseType) {
            case MIN:
            case DTEN:
                requiredDocumentTypes.addAll(Arrays.asList("ORIGINAL", "DRAFT", "FINAL", "CONTRIBUTION", "BACKGROUND NOTE"));
                break;
            case TRO:
                requiredDocumentTypes.addAll(Arrays.asList("ORIGINAL", "DRAFT", "FINAL", "CONTRIBUTION", "BACKGROUND NOTE", "Migration document"));
                break;
            case MPAM:
                requiredDocumentTypes.addAll(Arrays.asList("Original correspondence", "Further correspondence from MPs Office", "Contributions "
                        + "requested", "Contributions received" , "Draft response (includes QA rejected)", "Background note", "Final response", "Additional correspondence (Holding Replies)"));
                break;
            case MTS:
                requiredDocumentTypes.addAll(Arrays.asList("Original correspondence", "Further correspondence from MPs Office", "Contributions "
                        + "requested", "Contributions received" , "Draft response (includes QA rejected)", "Background note", "Final response"));
                break;
            case COMP:
                requiredDocumentTypes.addAll(Arrays.asList("To document", "Public correspondence", "Complaint leaflet", "Complaint letter", "Email"
                        , "CRF", "DRAFT", "Appeal Leaflet", "IMB Letter", "Final Response", "Migration document"));
                break;
            case COMP2:
                requiredDocumentTypes.addAll(Arrays.asList("To document", "Public correspondence", "Complaint leaflet", "Complaint letter", "Email"
                        , "CRF", "DRAFT", "Appeal Leaflet", "IMB Letter", "Final Response"));
                break;
            case IEDET:
                requiredDocumentTypes.addAll(Arrays.asList("Original complaint", "Letter of Authority", "Interim response", "DRAFT","Final response", "Withdrawal letter"
                        , "Other", "Migration document"));
                break;
            case BF:
                requiredDocumentTypes.addAll(Arrays.asList("To document", "Public correspondence", "Complaint leaflet", "Complaint letter", "Email"
                        , "CRF", "DRAFT","Final response", "Migration document"));
                break;
            case BF2:
                requiredDocumentTypes.addAll(Arrays.asList("To document", "Public correspondence", "Complaint leaflet", "Complaint letter", "Email"
                        , "CRF", "DRAFT","Final response"));
                break;
            case TO:
                requiredDocumentTypes.addAll(Arrays.asList("Initial Correspondence", "Initial Draft", "Final Response", "Contribution Request", "Contribution Response"
                        , "Background Note", "Migration document"));
                break;
            case FOI:
                requiredDocumentTypes.addAll(Arrays.asList("Request", "Initial response", "Draft response", "Clearances", "Final responses"
                        , "Correspondence", "Contribution", "Miscellaneous", "Appeal Response"));
                break;
            case POGR:
                requiredDocumentTypes.addAll(Arrays.asList("Original Complaint", "Interim Letter", "Draft", "Final Response","Contribution Request"
                        ,"Contribution Response", "Migration document"));
                break;
            case POGR2:
                requiredDocumentTypes.addAll(Arrays.asList("Original Complaint", "Interim Letter", "Draft", "Final Response","Contribution Request"
                        ,"Contribution Response"));
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        for (String requiredDocumentType : requiredDocumentTypes) {
            if (!availableDocumentTypes.contains(requiredDocumentType)) {
                Assert.fail("'" + requiredDocumentType + "' Document Type is not available for a " + caseType + " case");
            }
            availableDocumentTypes.remove(requiredDocumentType);
        }
        if (!availableDocumentTypes.isEmpty()) {
            Assert.fail("Unexpected document type/s: '" + availableDocumentTypes.toString() + "' were present for a" + caseType + " case");
        }
    }
}