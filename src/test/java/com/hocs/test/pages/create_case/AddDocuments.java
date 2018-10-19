package com.hocs.test.pages.create_case;

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

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Add documents");
    }

    public void bulkUploadDocuments(int documents) {
        String pathf1 = "C:\\Users\\dom.barnett\\dev\\home-office\\hocs-serenity-automation\\src\\test\\resources\\documents\\test1.docx";
        String pathf2 = "C:\\Users\\dom.barnett\\dev\\home-office\\hocs-serenity-automation\\src\\test\\resources\\documents\\test2.docx";
        String pathf3 = "C:\\Users\\dom.barnett\\dev\\home-office\\hocs-serenity-automation\\src\\test\\resources\\documents\\test3.docx";
        String allF = pathf1 + " \n " + pathf2 + " \n " + pathf3;
        addDocument.sendKeys(allF);
    }

    public void enterDispatchDeadlineDay(int days) {
        dispatchDeadlineDay.clear();
        dispatchDeadlineDay.sendKeys(todayPlusNDaysGetDay(days));
    }

    public void enterDispatchDeadlineMonth(int days) {
        dispatchDeadlineMonth.clear();
        dispatchDeadlineMonth.sendKeys(todayPlusNDaysGetMonth(days));
    }

    public void enterDispatchDeadlineYear(int days) {
        dispatchDeadlineYear.clear();
        dispatchDeadlineYear.sendKeys(todayPlusNDaysGetYear(days));
    }

    public void enterDraftDeadlineDay(int days) {
        draftDeadlineDay.clear();
        draftDeadlineDay.sendKeys(todayPlusNDaysGetDay(days));
    }

    public void enterDraftDeadlineMonth(int days) {
        draftDeadlineMonth.clear();
        draftDeadlineMonth.sendKeys(todayPlusNDaysGetMonth(days));
    }

    public void enterDraftDeadlineYear(int days) {
        draftDeadlineYear.clear();
        draftDeadlineYear.sendKeys(todayPlusNDaysGetYear(days));
    }

    public void uploadDocument() {
        upload("src/test/resources/documents/test1.docx").to(addDocument);
    }

}
