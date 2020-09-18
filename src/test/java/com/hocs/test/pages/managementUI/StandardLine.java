package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StandardLine extends BasePage {

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

    @FindBy(xpath = "//a[@href='#expiryDate-error']")
    public WebElementFacade pastExpirationDateErrorMessage;

    @FindBy(xpath = "//p[@class='govuk-body']")
    public WebElementFacade standardLineCreationSuccessMessage;

    public void enterStandardLineTopic() {
        waitABit(500);
        safeClickOn(topicTypeahead);
        topicTypeahead.sendKeys("Animal alternatives (3Rs)");
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
        standardLineIsRequiredErrorMessage.shouldContainText("The Standard Line is required");
    }

    public void assertExpiryDateIsRequiredErrorMessage() {
        expiryDateIsRequiredErrorMessage.shouldContainText("The Expiry Date is invalid");
    }

    public void assertTopicIsRequiredErrorMessage() {
        topicIsRequiredErrorMessage.shouldContainText("The Topic is required");
    }

    public void assertDateMustBeInFutureErrorMessage() {
        pastExpirationDateErrorMessage.shouldContainText("The Expiry Date must be in the future");
    }

    public void assertStandardLineSuccessMessage(){
        standardLineCreationSuccessMessage.shouldContainText("The standard line was created successfully");
    }
}
