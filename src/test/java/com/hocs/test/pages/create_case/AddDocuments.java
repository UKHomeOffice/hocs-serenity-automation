package com.hocs.test.pages.create_case;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AddDocuments extends Page {

    @FindBy(id = "DTENDispatchDeadline-day")
    private WebElementFacade dispatchDeadlineDay;

    @FindBy(id = "DTENDispatchDeadline-month")
    private WebElementFacade dispatchDeadlineMonth;

    @FindBy(id = "DTENDispatchDeadline-year")
    private WebElementFacade dispatchDeadlineYear;

    @FindBy(id = "DTENDraftDeadline-day")
    private WebElementFacade draftDeadlineDay;

    @FindBy(id = "DTENDraftDeadline-month")
    private WebElementFacade draftDeadlineMonth;

    @FindBy(id = "DTENDraftDeadline-year")
    private WebElementFacade draftDeadlineYear;

    @FindBy(id = "add_document")
    private WebElementFacade addDocument;

    @FindBy(xpath = "//input[@id='document']")
    private WebElementFacade addStandardLineDocument;

    @FindBy(xpath = "//a[text()='Document type is required']")
    private WebElementFacade documentTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Document is required']")
    private WebElementFacade documentIsRequiredErrorMessage;

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Add documents");
    }

    public void bulkUploadDocuments(int documents) {
        String pathf1 = "C:\\Users\\eamon.droko\\dev\\home-office\\hocs-serenity-automation\\src\\test\\resources"
                + "\\documents\\test1.docx";
        String pathf2 = "C:\\Users\\eamon.droko\\dev\\home-office\\hocs-serenity-automation\\src\\test\\resources"
                + "\\documents\\test2.docx";
        String pathf3 = "C:\\Users\\eamon.droko\\dev\\home-office\\hocs-serenity-automation\\src\\test\\resources"
                + "\\documents\\test3.docx";
        String allF = pathf1 + " \n " + pathf2 + " \n " + pathf3;
        addDocument.sendKeys(allF);
    }

    public void enterDispatchDeadlineDay(int days) {
        typeInto(dispatchDeadlineDay, todayPlusNDaysGetDay(days));
    }

    public void enterDispatchDeadlineMonth(int months) {
        typeInto(dispatchDeadlineMonth, todayPlusNDaysGetMonth(months));
    }

    public void enterDispatchDeadlineYear(int years) {
        typeInto(dispatchDeadlineYear, todayPlusNDaysGetYear(years));
    }

    public void enterDraftDeadlineDay(int days) {
        typeInto(draftDeadlineDay, todayPlusNDaysGetDay(days));
    }

    public void enterDraftDeadlineMonth(int months) {
        typeInto(draftDeadlineMonth, todayPlusNDaysGetMonth(months));
    }

    public void enterDraftDeadlineYear(int years) {
        typeInto(draftDeadlineYear, todayPlusNDaysGetYear(years));
    }

    public void assertDocumentTypeIsRequiredErrorMessage() {
        assertThat(documentTypeIsRequiredErrorMessage.getText(), is ("Document type is required"));
    }

    public void assertDocumentIsRequiredErrorMessage() {
        assertThat(documentIsRequiredErrorMessage.getText(), is("Document is required"));
    }

    public void uploadDocument() {
        upload("src/test/resources/documents/test1.docx").to(addDocument);
    }

    public void uploadStandardLineDocument() {
        upload("src/test/resources/documents/test1.docx").to(addStandardLineDocument);
    }
}




