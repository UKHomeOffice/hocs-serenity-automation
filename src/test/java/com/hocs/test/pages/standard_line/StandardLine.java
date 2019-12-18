package com.hocs.test.pages.standard_line;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StandardLine extends Page {

    @Managed
    WebDriver driver;

    @FindBy(xpath = "//a[@href='#files-error']")
    public WebElementFacade standardLineIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#expiryDate-error']")
    public WebElementFacade expiryDateIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#topic-error']")
    public WebElementFacade topicIsRequiredErrorMessage;

    @FindBy(xpath = "//input[@id='topics-input']")
    public WebElementFacade topicTypeahead;

    @FindBy(xpath = "//input[@class='govuk-file-upload']")
    public WebElementFacade standardLineDocumentButton;

    @FindBy(xpath = "//input[@id='expiryDate-day']")
    public WebElementFacade expirationDateDayTextBox;

    @FindBy(xpath = "//input[@id='expiryDate-month']")
    public WebElementFacade expirationDateMonthTextBox;

    @FindBy(xpath = "//input[@id='expiryDate-year']")
    public WebElementFacade expirationDateYearTextBox;

    @FindBy(xpath = "//td[@class='govuk-table__cell'][text()='testtesttest.docx']")
    public WebElementFacade standardLineDocumentAssert;

    @FindBy(xpath = "//a[@href='#expiryDate-error']")
    public WebElementFacade pastExpirationDateErrorMessage;

    @FindBy(xpath = "//p[@class='govuk-body']")
    public WebElementFacade standardLineCreationSuccessMessage;

    public void enterStandardLineTopic() {
        waitABit(500);
        topicTypeahead.sendKeys("Cardiff University Kittens");
        topicTypeahead.sendKeys(Keys.ENTER);
    }

    public void addStandardLineDocument() {
        upload("src/test/resources/documents/test12.docx").to(standardLineDocumentButton);
    }

    public void enterStandardLineExpirationDate() {
        typeInto(expirationDateDayTextBox, "12");
        typeInto(expirationDateMonthTextBox, "12");
        typeInto(expirationDateYearTextBox, "2020");
    }

    public void enterPastStandardLineExpirationDate() {
        typeInto(expirationDateDayTextBox, "01");
        typeInto(expirationDateMonthTextBox, "05");
        typeInto(expirationDateYearTextBox, "2018");
    }

    public void assertAddStandardLinePageTitle() {
        assertThat($("//h1").getText(), Is.is("Add a Standard Line"));
    }

    public void assertStandardLineIsRequiredErrorMessage(){
        assertThat(standardLineIsRequiredErrorMessage.getText(), is("The Standard Line is required"));
    }

    public void assertExpiryDateIsRequiredErrorMessage() {
        assertThat(expiryDateIsRequiredErrorMessage.getText(), is("The Expiry Date is invalid"));
    }

    public void assertTopicIsRequiredErrorMessage() {
        assertThat(topicIsRequiredErrorMessage.getText(), is("The Topic is required"));
    }

    public void assertStandardLineDocumentHasBeenAddedToTopic() {
        assertThat(standardLineDocumentAssert.getText(), is("testtesttest.docx"));
    }

    public void assertDateMustBeInFutureErrorMessage() {
        assertThat(pastExpirationDateErrorMessage.getText(), is("The Expiry Date must be in the future"));
    }

    public void assertStandardLineSuccessMessage(){
        assertThat(standardLineCreationSuccessMessage.getText(), is("The standard line was created successfully"));
    }
}
