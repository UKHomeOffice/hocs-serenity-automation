package com.hocs.test.pages.base_page;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Page extends PageObject {

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz";

    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    @FindBy(linkText = "Logout")
    public WebElementFacade logoutButton;

    @FindBy(css = "[value='Accept']")
    public WebElementFacade acceptButton;

    @FindBy(css = "[value = 'Add']")
    public WebElementFacade addButton;

    @FindBy(id = "CaseNote_AllocationNote")
    private WebElementFacade allocationNoteField;

    @FindBy(className = "govuk-caption-l")
    private WebElementFacade caseId;

    @FindBy(xpath = "//a[@class='govuk-back-link']")
    public WebElementFacade backButton;

    @FindBy(css = "[value='Continue']")
    public WebElementFacade continueButton;

    @FindBy(id = "Date")
    private WebElementFacade dateField;

    @FindBy(linkText = "delete")
    public WebElementFacade deleteLink;

    @FindBy(className = "govuk-error-summary")
    protected WebElementFacade errorMessage;

    @FindBy(linkText = "Correspondence System")
    public WebElementFacade home;

    @FindBy(css = "[value='Finish']")
    public WebElementFacade finishButton;

    @FindBy(linkText = "new")
    public WebElementFacade newLink;

    @FindBy(css = "[value = 'Next']")
    public WebElementFacade nextButton;

    @FindBy(className = "govuk-heading-l")
    protected WebElementFacade pageTitle;

    @FindBy(xpath = "//h1")
    protected WebElementFacade managementUIPageTitle;

    @FindBy(css = "[value='Reject']")
    public WebElementFacade rejectButton;

    @FindBy(id = "CaseNote_RejectionNote")
    protected WebElementFacade rejectReasonTextField;

    @FindBy(css = "[value = 'Search']")
    public WebElementFacade searchButton;

    @FindBy(css = "[value = 'Finish']")
    public WebElementFacade submitButton;

    @FindBy(linkText = "update")
    public WebElementFacade updateLink;

    @FindBy(linkText = "view")
    public WebElementFacade viewLink;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicButton;

    public void waitABit(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assertErrorMessageText(String text) {
        assertThat(getErrorMessageText(), containsString(text));
    }

    public void assertPageTitle(String title) {
        pageTitle.waitUntilVisible();
        try {
            assertThat(getHeaderText(), containsString(title));
        } catch (AssertionError e) {
            waitABit(2000);
            assertThat(getHeaderText(), containsString(title));
        }
    }

    public void clearCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public void clickAcceptButton() {
        clickOn(acceptButton);
    }

    public void clickAddButton() {
        clickOn(addButton);
    }

    public void clickBackButton() {
        clickOn(backButton);
    }

    public void clickContinueButton() {
        waitFor(continueButton).waitUntilClickable();
        clickOn(continueButton);
    }

    public void clickDeleteLink() {
        clickOn(deleteLink);
    }

    public void goHome() {
        clickOn(home);
    }

    public void clickFinishButton() {
        clickOn(finishButton);
    }

    public void clickNextButton() {
        clickOn(nextButton);
    }

    public void clickNewLink() {
        clickOn(newLink);
    }

    public void clickRejectButton() {
        clickOn(rejectButton);
    }

    public void clickSearchButton() {
        clickOn(searchButton);
    }

    public void clickSubmitButton() {
        clickOn(submitButton);
    }

    public void clickUpdateLink() {
        clickOn(updateLink);
    }

    public void clickViewLink() {
        clickOn(viewLink);
    }

    public void enterAllocationNote() {
        String allocationNote = generateRandomString();
        typeInto(allocationNoteField, allocationNote);
        setSessionVariable("allocationNote").to(allocationNote);
    }

    public void enterDate(String date) {
        typeInto(dateField, date);
        setSessionVariable("date").to(date);
    }

    public void enterRejectionNotes() {
        waitFor(rejectReasonTextField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        typeInto(rejectReasonTextField, rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void javascriptScrollToElem(WebElementFacade element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void errorMessageIsDisplayed() {
        assertThat(isElementDisplayed(errorMessage), is(true));
    }

    protected String generateRandomString() {
        StringBuilder randStr = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }

        return randStr.toString();
    }

    public String getCaseId() {
        setSessionVariable("caseReference").to(caseId.getText());

        return caseId.getText();
    }

    private String getErrorMessageText() {
        return errorMessage.getText();
    }

    protected String getHeaderText() {
        return pageTitle.getText();
    }

    private int getRandomNumber() {
        Random randomGenerator = new Random();

        int randomInt = randomGenerator.nextInt(CHAR_LIST.length());

        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    public boolean isElementDisplayed(WebElementFacade element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException | NullPointerException e) {
            return false;
        }
    }

    public String getCurrentDay() {
        Calendar cal = Calendar.getInstance();

        return dayFormat.format(cal.getTime());
    }

    public String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();

        return monthFormat.format(cal.getTime());
    }

    public String getCurrentYear() {
        Calendar cal = Calendar.getInstance();

        return yearFormat.format(cal.getTime());
    }

    public String todayPlusNDaysGetDay(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return dayFormat.format(cal.getTime());
    }

    public String todayPlusNDaysGetMonth(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return monthFormat.format(cal.getTime());
    }

    public String todayPlusNDaysGetYear(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return yearFormat.format(cal.getTime());
    }


    public String tomorrowsDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return dayFormat.format(cal.getTime());
    }

    public String tomorrowsMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return monthFormat.format(cal.getTime());
    }

    public String tomorrowsYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return yearFormat.format(cal.getTime());
    }

    public void assertElementIsDisplayed(WebElementFacade element) {
        assertThat(isElementDisplayed(element), is(true));
    }

    public void assertElementTextIs(WebElementFacade elem, String thisElementsText) {
        System.out.println(thisElementsText);
        assertThat(elem.getValue(), is(thisElementsText));
    }
}
