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

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static final SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

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

    @FindBy(id = "date-day")
    protected WebElementFacade dateDayField;

    @FindBy(id = "date-month")
    protected WebElementFacade dateMonthField;

    @FindBy(id = "date-year")
    protected WebElementFacade dateYearField;

    @FindBy(id = "DateReceived-day")
    protected WebElementFacade dateReceivedDay;

    @FindBy(id = "DateReceived-month")
    protected WebElementFacade dateReceivedMonth;

    @FindBy(id = "DateReceived-year")
    protected WebElementFacade dateReceivedYear;

    @FindBy(linkText = "delete")
    public WebElementFacade deleteLink;

    @FindBy(css = "[id*=EndDate]")
    private WebElementFacade endDate;

    @FindBy(id = "error-details")
    protected WebElementFacade errorDetails;

    @FindBy(className = "govuk-error-summary")
    protected WebElementFacade errorMessage;

    @FindBy(linkText = "Correspondence System")
    public WebElementFacade home;

    @FindBy(css = "[value='Finish']")
    public WebElementFacade finishButton;

    @FindBy(name = "go")
    private WebElementFacade lookupButton;

    @FindBy(linkText = "new")
    public WebElementFacade newLink;

    @FindBy(css = "[value = 'Next']")
    public WebElementFacade nextButton;

    @FindBy(css = "[id*=newNotes]")
    private WebElementFacade newNotesField;

    @FindBy(css = "[id*=Notes]")
    private WebElementFacade notesField;

    @FindBy(className = "govuk-heading-l")
    protected WebElementFacade pageTitle;

    @FindBy(xpath = "//h1")
    protected WebElementFacade managementUIPageTitle;

    @FindBy(xpath = "//h1/span")
    protected WebElementFacade pageCaption;

    @FindBy(css = "[value='Reject']")
    public WebElementFacade rejectButton;

    @FindBy(id = "CaseNote_RejectionNote")
    protected WebElementFacade rejectReasonTextField;

    @FindBy(css = "[value = 'Search']")
    public WebElementFacade searchButton;

    @FindBy(css = "[id*=Staff]")
    protected WebElementFacade staffDropdown;

    @FindBy(css = "[id*=StartDate]")
    private WebElementFacade startDate;

    @FindBy(css = "[value = 'Finish']")
    public WebElementFacade submitButton;

    @FindBy(css = "[value = 'Update']")
    private WebElementFacade updateButton;

    @FindBy(linkText = "update")
    public WebElementFacade updateLink;

    @FindBy(linkText = "View")
    private WebElementFacade viewButton;

    @FindBy(linkText = "view")
    public WebElementFacade viewLink;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicButton;

    @FindBy(xpath = "//a[@href='mailto:HOCS@homeoffice.gov.uk']")
    private WebElementFacade emailFeedbackButton;

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
        pageTitle.withTimeoutOf(5, TimeUnit.SECONDS).waitUntilVisible();
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
        acceptButton.click();
    }

    public void clickAddButton() {
        clickOn(addButton);
    }

    public void clickBackButton() {
        backButton.click();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void waitForContinueButton() {
        waitFor(continueButton).waitUntilClickable();
        continueButton.click();
    }

    public void clickDeleteLink() {
        deleteLink.click();
    }

    public void goHome() {
        home.click();
    }

    public void clickFinishButton() {
        finishButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickNewLink() {
        newLink.click();
    }

    public void clickRejectButton() {
        rejectButton.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickUpdateLink() {
        updateLink.click();
    }

    public void clickViewLink() {
        viewLink.click();
    }

    //This returns date as 01-JAN-18, only needed for searching in Oracle DB
    public String databaseDateTodayPlusDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return dbDateFormat.format(cal.getTime());
    }

    //This returns date as 01-JAN-18, only needed for searching in Oracle DB
    public String databaseDateTodayPlusYears(int years) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, years);

        return dbDateFormat.format(cal.getTime());
    }

    //This returns date as 01-JAN-18, only needed for searching in Oracle DB

    public String databaseDateTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return dbDateFormat.format(cal.getTime());
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

    public void enterDayReceived() {
        typeInto(dateReceivedDay, "");
    }

    public void enterMonthReceived() {
        typeInto(dateReceivedMonth, "");
    }

    public void enterYearReceived() {
        typeInto(dateReceivedYear, "");
    }

    public void enterEndDate(String endDate) {
        this.endDate.clear();
        this.endDate.sendKeys(endDate);
        setSessionVariable("endDate").to(endDate);
    }

    public void enterInvalidDate() {
        typeInto(dateDayField, "31");
        typeInto(dateMonthField, "06");
        typeInto(dateYearField, "2018");
    }

    public void enterNoDate() {
        dateReceivedDay.clear();
        dateReceivedMonth.clear();
        dateReceivedYear.clear();
    }

    public void enterNewNotes(String notes) {
        typeInto(newNotesField, notes);
        setSessionVariable("newNotes").to(notes);
    }

    public void enterNotes(String notes) {
        typeInto(notesField, notes);
        setSessionVariable("notes").to(notes);
    }

    public void enterRejectionNotes() {
        waitFor(rejectReasonTextField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        typeInto(rejectReasonTextField, rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void enterStartDate(String startDate) {
        this.startDate.clear();
        this.startDate.sendKeys(startDate);
        setSessionVariable("startDate").to(startDate);
    }

    public void enterValidDate() {
        typeInto(dateDayField, "01");
        typeInto(dateMonthField, "01");
        typeInto(dateYearField, "2018");
    }

    public void javascriptScrollToElem(WebElementFacade element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void errorMessageIsDisplayed() {
        assertThat(isElementDisplayed(errorMessage), is(true));
    }

    public int generateRandomNumber(int digits) {
        Random rand = new Random();

        double maxNumber = (Math.pow(10, digits) - 1);

        return rand.nextInt((int) maxNumber) + 1;
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

    public void assertCaseIdIsOnCurrentPage() {
        String thisCaseID = sessionVariableCalled("caseReference").toString();
        WebElementFacade thisIdSearch = findAll("//td/a[contains(text(), '" + thisCaseID + "')]").get(0);
        assertThat((isElementDisplayed(thisIdSearch)), is(true));
    }

    protected String getErrorDetails() {
        return errorDetails.getText();
    }

    private String getErrorMessageText() {
        return errorMessage.getText();
    }


    protected String getHeaderText() {
        return pageTitle.getText();
    }

    private String getCaptionText() {
        return pageCaption.getText();
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

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));

        return definitionList.findElements(By.tagName("li")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    protected boolean isElementPresent(By element, WebDriver driver) {
        List<WebElement> s = driver.findElements(element);

        return s.size() != 0;
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

    public void lookupTerms() {
        lookupButton.click();
    }

    public void modifyHeadersAndNavigateTo(String page) {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(
                new File("C:\\Users\\dom.barnett\\Downloads\\ModHeader_v2_2_3_0.crx"));

        // launch the browser
        WebDriver driver = new ChromeDriver(options);

        // set the context on the extension so the localStorage can be accessed
        driver.get("chrome-extension://idgpnmonknjnojddfkpgkljpfnnfcklj/icon.png");

        // setup ModHeader with two headers (token1 and token2)
        ((JavascriptExecutor) driver).executeScript(
                "localStorage.setItem('profiles', JSON.stringify([{                " +
                        "  title: 'Selenium', hideComment: true, appendMode: '',           " +
                        "  headers: [                                                      " +
                        "    {enabled: true, name: 'X-Auth-Token', value: '01234', comment: ''}, " +
                        "    {enabled: true, name: 'X-Auth-Roles', value: 'CREATE,BULK,DOCUMENT,DCU,UKVI,FOI', comment: ''},  "
                        +
                        "    {enabled: true, name: 'X-Auth-UserId', value: 'Test', comment: ''}, " +
                        "    {enabled: true, name: 'X-Auth-Username', value: 'Test', comment: ''}  "
                        +
                        "  ],                                                              " +
                        "  respHeaders: [],                                                " +
                        "  filters: []                                                     " +
                        "}]));                                                             ");
        driver.navigate().to(page);
    }

    public boolean nextButtonIsDisplayed() {
        return isElementDisplayed(nextButton);
    }

    public void switchToAlertWindowAndClick(WebDriver driver, String buttonName) {
        switch (buttonName.toUpperCase()) {
            case "YES":
                driver.switchTo().alert().accept();
                break;
            case "NO":
                driver.switchTo().alert().dismiss();
                break;
            case "OK":
                driver.switchTo().alert().accept();
                break;
            default:
                pendingStep(buttonName + " is not defined within " + getMethodName());
        }
        driver.switchTo().alert().accept();
    }

    public void switchToOpenedWindow(WebDriver driver) {
        setSessionVariable("originalWindowHandle").to(driver.getWindowHandle());
        driver.getWindowHandle();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
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

    public String todayPlusYears(int years) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, years);

        return new SimpleDateFormat("YYYY").format(cal.getTime());
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

    public boolean updateButtonIsDisplayed() {
        return isElementDisplayed(updateButton);
    }

    public boolean updateLinkIsDisplayed() {
        return isElementDisplayed(updateLink);
    }

    public boolean isWebElementFacadePresent(WebElementFacade cssLink) {
        try {
            $(cssLink);
            return true;
        } catch (NoSuchElementException | ElementShouldBeEnabledException e) {
            return false;
        }
    }

    public void assertElementIsDisplayed(WebElementFacade element) {
        assertThat(isElementDisplayed(element), is(true));
    }

    public void assertElementIsNotDisplayed(WebElementFacade element) {
        assertThat(isElementDisplayed(element), is(false));
    }

    public void assertElementTextIs(WebElementFacade elem, String thisElementsText) {
        System.out.println(thisElementsText);
        assertThat(elem.getValue(), is(thisElementsText));
    }

    public void assertElementTextNotValue(WebElementFacade elem, String thisElementsText) {
        System.out.println(thisElementsText);
        assertThat(elem.getText(), is(thisElementsText));
    }
}
