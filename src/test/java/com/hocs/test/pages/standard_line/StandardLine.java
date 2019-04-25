package com.hocs.test.pages.standard_line;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StandardLine extends Page {

    @Managed
    WebDriver driver;

    @FindBy(xpath = "//a[text()='Associated topic is required']")
    public WebElementFacade associatedTopicIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Expiration date is required']")
    public WebElementFacade expirationDateIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Document is required']")
    public WebElementFacade documentIsRequiredErrorMessage;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    public WebElementFacade associatedTopicTypeahead;

    @FindBy(xpath = "//input[@id='expiry_date-day']")
    public WebElementFacade expirationDateDayTextBox;

    @FindBy(xpath = "//input[@id='expiry_date-month']")
    public WebElementFacade expirationDateMonthTextBox;

    @FindBy(xpath = "//input[@id='expiry_date-year']")
    public WebElementFacade expirationDateYearTextBox;

    @FindBy(xpath = "//td[@class='govuk-table__cell'][text()='testtesttest.docx']")
    public WebElementFacade standardLineDocumentAssert;

    public void enterExpirationDate() {
        expirationDateDayTextBox.sendKeys("06");
        expirationDateMonthTextBox.sendKeys("03");
        expirationDateYearTextBox.sendKeys("2019");
    }

    public void assertAssociatedTopicErrorMessage() {
        assertThat(associatedTopicIsRequiredErrorMessage.getText(),
                is("Associated topic is required"));
    }

    public void assertExpirationDateIsRequiredErrorMessage() {
        assertThat(expirationDateIsRequiredErrorMessage.getText(),
                is("Expiration date is required"));
    }

    public void assertDocumentIsRequiredErrorMessage() {
        assertThat(documentIsRequiredErrorMessage.getText(), is("Document is required"));
    }

    public void assertStandardLineDocumentHasBeenAddedToTopic() {
        assertThat(standardLineDocumentAssert.getText(), is("testtesttest.docx"));
    }
}
