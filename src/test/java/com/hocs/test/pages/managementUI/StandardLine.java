package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.BasePage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;
import org.openqa.selenium.Keys;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

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
        waitABit(500);
        safeClickOn(topicTypeahead);
        topicTypeahead.sendKeys(topic);
        setSessionVariable("standardLineTopic").to(topic);
        topicTypeahead.sendKeys(Keys.ENTER);
    }

    public void addStandardLineDocument() {
        upload("src/test/resources/documents/test12.docx").to(standardLineDocumentButton);
        setSessionVariable("standardLineDocument").to("test12.docx");
    }

    public void enterStandardLineExpirationDate() {
        typeInto(expirationDateDayTextBox, "12");
        typeInto(expirationDateMonthTextBox, "12");
        typeInto(expirationDateYearTextBox, "2020");
        setSessionVariable("standardLineExpiryDate").to("12/12/2020");
    }

    public void enterPastStandardLineExpirationDate() {
        typeInto(expirationDateDayTextBox, "01");
        typeInto(expirationDateMonthTextBox, "05");
        typeInto(expirationDateYearTextBox, "2018");
    }

    public void selectActionForStandardLine(String topic, String action) {
        WebElementFacade hypertext = findBy("//td[text()='" + topic + "']/following-sibling::td//a[text()='" + action + "']");
        safeClickOn(hypertext);
        waitABit(1000);
    }

    public void amendAStandardLine(String topic, String newDate) {
        String dd = newDate.split("/")[0];
        String mm = newDate.split("/")[1];
        String yyyy = newDate.split("/")[2];
        selectActionForStandardLine(topic, "Amend");
        setSessionVariable("standardLineTopic").to(topic);
        setSessionVariable("standardLineExpiryDate").to(newDate);
        typeInto(expirationDateDayTextBox, dd);
        typeInto(expirationDateMonthTextBox, mm);
        typeInto(expirationDateYearTextBox, yyyy);
        safeClickOn(submitButton);
    }

    public void toggleExpiredStandardLinesCheckbox() {
        safeClickOn(excludeExpiredCheckbox);
    }

    public void enterIntoStandardLineFilter(String input) {
        typeInto(manageStandardLinesFilter, input);
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
