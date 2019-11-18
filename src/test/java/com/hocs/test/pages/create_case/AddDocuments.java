package com.hocs.test.pages.create_case;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AddDocuments extends Page {

    @FindBy(id = "add_document")
    private WebElementFacade addDocument;

    @FindBy(xpath = "//a[text()='Document type is required']")
    private WebElementFacade documentTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Document is required']")
    private WebElementFacade documentIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='document']")
    public WebElementFacade addDocumentsButton;

    @FindBy(id = "document_type")
    public WebElementFacade documentTypeDropDown;

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

    public void assertDocumentTypeIsRequiredErrorMessage() {
        assertThat(documentTypeIsRequiredErrorMessage.getText(), is("Document type is required"));
    }

    public void assertDocumentIsRequiredErrorMessage() {
        assertThat(documentIsRequiredErrorMessage.getText(), is("Document is required"));
    }

    public void uploadDocument() {
        upload("src/test/resources/documents/test1.docx").to(addDocument);
    }

    public void selectDocumentTypeByIndex(int index) {
        documentTypeDropDown.selectByIndex(index);
    }

    public void addAOriginalDocument() {
        clickOn(addDocumentsButton);
        selectDocumentTypeByIndex(3);
        uploadDocument();
        clickOn(addButton);
    }

    public void addADraftDocument() {
        clickOn(addDocumentsButton);
        selectDocumentTypeByIndex(2);
        uploadDocument();
        clickOn(addButton);
    }

    public void addAFinalDocument() {
        clickOn(addDocumentsButton);
        selectDocumentTypeByIndex(3);
        uploadDocument();
        clickOn(addButton);
    }
}




