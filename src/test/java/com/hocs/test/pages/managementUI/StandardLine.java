package com.hocs.test.pages.managementUI;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;

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

    @FindBy(id = "filter")
    public WebElementFacade manageStandardLinesFilter;

    @FindBy(xpath = "//div[text()='Exclude expired']/following-sibling::div/input")
    public WebElementFacade excludeExpiredCheckbox;

    @FindBy(xpath = "//button[text()='Add new standard line']")
    public WebElementFacade addNewStandardLineButton;

    @FindBy(xpath = "//input[@value='Submit']")
    public WebElementFacade submitButton;

    public void enterStandardLineTopic(String topic) {
        waitABit(1000);
        safeClickOn(topicTypeahead);
        topicTypeahead.sendKeys(topic);
        setSessionVariable("standardLineTopic").to(topic);
        topicTypeahead.sendKeys(Keys.ENTER);
    }

    public void addStandardLineDocument() {
        upload("src/test/resources/documents/Standard line test.docx").to(standardLineDocumentButton);
        setSessionVariable("standardLineDocument").to("Standard Line test.docx");
    }

    public void enterStandardLineExpirationDate() {
        String tomorrowsDate = getDatePlusMinusNDaysAgo(1);
        typeIntoDateFields(expirationDateDayTextBox, expirationDateMonthTextBox, expirationDateYearTextBox, tomorrowsDate);
        setSessionVariable("standardLineExpiryDate").to(tomorrowsDate);
    }

    public void enterPastStandardLineExpirationDate() {
        typeIntoDateFields(expirationDateDayTextBox, expirationDateMonthTextBox, expirationDateYearTextBox, getDatePlusMinusNDaysAgo(-300));
    }

    public void selectActionForStandardLine(String topic, String action) {
        if (action.equalsIgnoreCase("EXPIRE")) {
            setSessionVariable("standardLineExpiryDate").to(getTodaysDate());
        }
        WebElementFacade hypertext = findBy("//td[text()='" + topic + "']/following-sibling::td//a[text()='" + action + "']");
        jsClickOn(hypertext);
        waitABit(1000);
    }

    public void amendAStandardLine(String topic, Integer days) {
        String newDate = getDatePlusMinusNDaysAgo(days);
        selectActionForStandardLine(topic, "Amend");
        setSessionVariable("standardLineTopic").to(topic);
        setSessionVariable("standardLineExpiryDate").to(newDate);
        typeIntoDateFields(expirationDateDayTextBox, expirationDateMonthTextBox, expirationDateYearTextBox, newDate);
        safeClickOn(submitButton);
    }

    public void toggleExpiredStandardLinesCheckbox() {
        jsClickOn(excludeExpiredCheckbox);
    }

    public void enterIntoStandardLineFilter(String input) {
        manageStandardLinesFilter.sendKeys(input);
    }

    public void assertStandardLinesAreFilteredBy(String filterBy, String contents) {
        int n = 0;
        int columnNumber = 0;
        switch (filterBy.toUpperCase()) {
            case "TOPIC":
                columnNumber = 1;
                break;
            case "FILE NAME":
                columnNumber = 2;
                break;
            default:
                pendingStep(filterBy + " is not defined within " + getMethodName());
        }
        List<WebElementFacade> columnFields = findAll("//tbody/tr/td[" + columnNumber + "]");
        int sizeOfColumnFields = columnFields.size();
        while (n < sizeOfColumnFields) {
            assertThat(columnFields.get(n).getText().contains(contents), is(true));
            n++;
        }
    }

    public void assertStandardLineExpiryDateIsUpdated() {
        WebElementFacade dateField = findBy("//td[text()='" + sessionVariableCalled("standardLineTopic") + "']/parent::tr//span");
        String newDate = dateField.getText();
        assertThat(newDate.equals(sessionVariableCalled("standardLineExpiryDate")), is(true));
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

    public void assertCreatedStandardLineDisplayed(String input) {
        WebElementFacade standardLine = findBy("//td[text()='" + sessionVariableCalled("standardLineTopic") + "']/following-sibling::td[text()"
                + "='" + sessionVariableCalled("standardLineDocument") + "']/following-sibling::td//span[text()='" + sessionVariableCalled(
                        "standardLineExpiryDate") + "']");
        Boolean check = null;
        if (input.toUpperCase().equals("IS")) {
            check = true;
        }
        else if (input.toUpperCase().equals("ISN'T")) {
            check = false;
        }
        assertThat(standardLine.isVisible(), is(check));
    }
}
