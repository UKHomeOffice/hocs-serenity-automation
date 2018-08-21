package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AddDocuments extends Page {

    @FindBy(id = "add_document")
    private WebElementFacade addDocument;

    @FindBy(id = "DateReceived-day")
    private WebElementFacade dateReceivedDay;

    @FindBy(id = "DateReceived-month")
    private WebElementFacade dateReceivedMonth;

    @FindBy(id = "DateReceived-year")
    private WebElementFacade dateReceivedYear;

    public void enterDayReceived() {
        dateReceivedDay.clear();
        dateReceivedDay.sendKeys("");
    }

    public void enterMonthReceived() {
        dateReceivedMonth.clear();
        dateReceivedMonth.sendKeys("");
    }

    public void enterYearReceived() {
        dateReceivedYear.clear();
        dateReceivedYear.sendKeys("");
    }

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Add documents");
    }

    public void uploadDocument() {
        upload("documents/test.docx").to(addDocument);
    }

}
