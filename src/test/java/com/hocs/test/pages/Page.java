package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Page extends PageObject {

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static final SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    @FindBy(css = "[value = 'Next']")
    public WebElementFacade nextButton;

    @FindBy(id = "date-day")
    protected WebElementFacade dateDayField;

    @FindBy(id = "date-month")
    protected WebElementFacade dateMonthField;

    @FindBy(id = "date-year")
    protected WebElementFacade dateYearField;

    @FindBy(css = "[value='Edit']")
    protected WebElementFacade editButton;

    @FindBy(id = "error-details")
    protected WebElementFacade errorDetails;

    @FindBy(linkText = "Finish")
    protected WebElementFacade finishButton;

    @FindBy(id = "name")
    protected WebElementFacade nameField;

    @FindBy(className = "govuk-heading-l")
    protected WebElementFacade pageTitle;

    @FindBy(linkText = "Print")
    protected WebElementFacade printButton;

    @FindBy(css = "[value='Reject']")
    protected WebElementFacade rejectButton;

    @FindBy(id = "")
    protected WebElementFacade rejectReasonTextField;

    @FindBy(css = "[id*=Staff]")
    protected WebElementFacade staffDropdown;

    @FindBy(id = "surname")
    protected WebElementFacade surnameField;

    @Managed
    WebDriver driver;

    @FindBy(css = "[value='Accept All']")
    private WebElementFacade acceptAllButton;

    @FindBy(css = "[value='Accept']")
    private WebElementFacade acceptButton;

    @FindBy(css = "[value = 'Add']")
    private WebElementFacade addButton;

    @FindBy(css = "[value='Add/Update']")
    private WebElementFacade addUpdateButton;

    @FindBy(id = "")
    private WebElementFacade allocationNoteField;

    @FindBy(css = "[id*=documentTable]")
    private WebElementFacade associatedDocumentTable;

    @FindBy(css = "[class = 'govuk-back-link ']")
    private WebElementFacade cancelButton;

    @FindBy(css = "[value='Close']")
    private WebElementFacade closeButton;

    @FindBy(css = "[value = 'Confirm']")
    private WebElementFacade confirmButton;

    @FindBy(id = "Date")
    private WebElementFacade dateField;

    @FindBy(linkText = "delete")
    private WebElementFacade deleteLink;

    @FindBy(css = "[value = 'Document']")
    private WebElementFacade documentButton;

    @FindBy(css = "[id*=EndDate]")
    private WebElementFacade endDate;

    @FindBy(linkText = "First")
    private WebElementFacade firstButton;

    @FindBy(linkText = "Last")
    private WebElementFacade lastButton;

    @FindBy(name = "go")
    private WebElementFacade lookupButton;

    @FindBy(css = "input[type='submit'][value='More']")
    private WebElementFacade moreButton;

    @FindBy(linkText = "new")
    private WebElementFacade newLink;

    @FindBy(css = "[id*=newNotes]")
    private WebElementFacade newNotesField;

    @FindBy(css = "[id*=Notes]")
    private WebElementFacade notesField;

    @FindBy(linkText = "Previous")
    private WebElementFacade previousButton;

    @FindBy(css = "[value = 'Refresh']")
    private WebElementFacade refreshButton;

    @FindBy(css = "[value = 'Save']")
    private WebElementFacade saveButton;

    @FindBy(css = "[value = 'Search']")
    private WebElementFacade searchButton;

    @FindBy(css = "[id*=StartDate]")
    private WebElementFacade startDate;

    @FindBy(css = "[type = 'submit']")
    private WebElementFacade submitButton;

    @FindBy(css = "[value = 'Update']")
    private WebElementFacade updateButton;

    @FindBy(linkText = "update")
    private WebElementFacade updateLink;

    @FindBy(linkText = "View")
    private WebElementFacade viewButton;

    @FindBy(linkText = "view")
    private WebElementFacade viewLink;

    public void assertTitle(String title) {
        assertThat(getTitle(), is(title));
    }

    public void associatedDocumentsIsDisplayed() {
        associatedDocumentTable.isDisplayed();
    }

    public void associatedDocumentsIsNotDisplayed() {
        associatedDocumentTable.shouldNotBeVisible();
    }

    public void clearCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public void clickAcceptButton() {
        acceptButton.click();
    }

    public void clickAcceptAllButton() {
        acceptAllButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void clickAddUpdateButton() {
        addUpdateButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void clickDeleteLink() {
        deleteLink.click();
    }

    public void clickDocumentButton() {
        documentButton.click();
    }

    public void clickFirstButton() {
        firstButton.click();
    }

    public void clickLastButton() {
        lastButton.click();
    }

    public void clickMoreButton() {
        moreButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickNewLink() {
        newLink.click();
    }

    public void clickPreviousButton() {
        previousButton.click();
    }

    public void clickRefreshButton() {
        refreshButton.click();
    }

    public void clickRejectButton() {
        rejectButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clickUpdateButton() {
        updateButton.click();
    }

    public void clickUpdateLink() {
        updateLink.click();
    }

    public void clickViewButton() {
        waitFor(viewButton);
        viewButton.click();
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

    public void enterDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
        Serenity.setSessionVariable("date").to(date);
    }

    public void enterEndDate(String endDate) {
        this.endDate.clear();
        this.endDate.sendKeys(endDate);
        Serenity.setSessionVariable("endDate").to(endDate);
    }

    public void enterInvalidDate() {
        dateDayField.clear();
        dateDayField.sendKeys("31");
        dateMonthField.clear();
        dateMonthField.sendKeys("06");
        dateYearField.clear();
        dateYearField.sendKeys("2018");
    }

    public void enterNewNotes(String notes) {
        newNotesField.clear();
        newNotesField.sendKeys(notes);
        Serenity.setSessionVariable("newNotes").to(notes);
    }

    public void enterNotes(String notes) {
        notesField.clear();
        notesField.sendKeys(notes);
        Serenity.setSessionVariable("notes").to(notes);
    }

    public void enterRejectionNotes() {
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        rejectReasonTextField.sendKeys(rejectionReason);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void enterStartDate(String startDate) {
        this.startDate.clear();
        this.startDate.sendKeys(startDate);
        Serenity.setSessionVariable("startDate").to(startDate);
    }

    public void enterValidDate() {
        dateDayField.clear();
        dateDayField.sendKeys("01");
        dateMonthField.clear();
        dateMonthField.sendKeys("01");
        dateYearField.clear();
        dateYearField.sendKeys("2018");
    }

    public int generateRandomNumber(int digits) {
        Random rand = new Random();

        double maxNumber = (Math.pow(10, digits) - 1);
        return rand.nextInt((int) maxNumber) + 1;
    }

    public String generateRandomString() {
        StringBuilder randStr = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }

        return randStr.toString();
    }

    protected String getErrorDetails() {
        return errorDetails.getText();
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
                fail(buttonName
                        + " is not defined within the Page class, switchToAlertWindowAndClick method");
        }
        driver.switchTo().alert().accept();
    }

    public void switchToOpenedWindow(WebDriver driver) {
        Serenity.setSessionVariable("originalWindowHandle").to(driver.getWindowHandle());
        driver.getWindowHandle();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String today() {
        Date date = Calendar.getInstance().getTime();

        return simpleDateFormat.format(date);
    }

    public String todayPlusDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return simpleDateFormat.format(cal.getTime());
    }

    public String todayPlusYears(int years) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, years);

        return simpleDateFormat.format(cal.getTime());
    }

    public String tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return simpleDateFormat.format(cal.getTime());
    }

    public boolean updateButtonIsDisplayed() {
        return isElementDisplayed(updateButton);
    }

    public boolean updateLinkIsDisplayed() {
        return isElementDisplayed(updateLink);
    }
}
