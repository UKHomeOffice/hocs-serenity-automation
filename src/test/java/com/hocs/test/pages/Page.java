package com.hocs.test.pages;

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

    protected static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz";

    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    protected static final SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    @FindBy(className = "govuk-heading-l")
    public WebElementFacade pageTitle;

    @FindBy(css = "[value='Add/Update']")
    protected WebElementFacade addUpdateButton;

    @FindBy(css = "[id*=documentTable]")
    protected WebElementFacade associatedDocumentTable;

    @FindBy(css = "[id*=CRN]")
    protected WebElementFacade CRN;

    @FindBy(name = "go")
    protected WebElementFacade lookupButton;

    @FindBy(css = "[value = 'Search']")
    protected WebElementFacade searchButton;

    @FindBy(css = "[type = 'submit']")
    protected WebElementFacade submitButton;

    @FindBy(linkText = "Print")
    protected WebElementFacade printButton;

    @FindBy(css = "[value = 'Refresh']")
    protected WebElementFacade refreshButton;

    @FindBy(css = "[value = 'Save']")
    protected WebElementFacade saveButton;

    @FindBy(css = "input[type='submit'][value='More']")
    protected WebElementFacade moreButton;

    @FindBy(css = "[value = 'Document']")
    protected WebElementFacade documentButton;

    @FindBy(linkText = "update")
    protected WebElementFacade updateLink;

    @FindBy(linkText = "view")
    protected WebElementFacade viewLink;

    @FindBy(linkText = "View")
    protected WebElementFacade viewButton;

    @FindBy(id = "Date")
    protected WebElementFacade dateField;

    @FindBy(css = "[value='Withdraw']")
    protected WebElementFacade withdrawButton;

    @FindBy(css = "[value='Edit']")
    protected WebElementFacade editButton;

    @FindBy(css = "[value='Reject']")
    protected WebElementFacade rejectButton;

    @FindBy(css = "[value='Close']")
    protected WebElementFacade closeButton;

    @FindBy(css = "[value='Transfer']")
    protected WebElementFacade offenderTransfer;

    @FindBy(css = "[value='Accept All']")
    protected WebElementFacade acceptAllButton;

    @FindBy(css = "[value='Accept']")
    protected WebElementFacade acceptButton;

    @FindBy(css = "[id*=Staff]")
    protected WebElementFacade staffDropdown;

    @FindBy(css = "[id*=StartDate]")
    protected WebElementFacade startDate;

    @FindBy(css = "[id*=EndDate]")
    protected WebElementFacade endDate;

    @FindBy(css = "[id*=searchResults]")
    protected WebElementFacade searchResults;

    @FindBy(css = "input[type = 'submit'][value = 'Clear Search Fields']")
    protected WebElementFacade clearSearchFields;

    @FindBy(css = "[value = 'Confirm']")
    protected WebElementFacade confirmButton;

    @FindBy(linkText = "First")
    protected WebElementFacade firstButton;

    @FindBy(linkText = "Previous")
    protected WebElementFacade previousButton;

    @FindBy(css = "[value = 'Next']")
    protected WebElementFacade nextButton;

    @FindBy(linkText = "Last")
    protected WebElementFacade lastButton;

    @FindBy(className = "highlight")
    protected WebElementFacade noTableResults;

    @FindBy(css = "[value = 'Cancel']")
    protected WebElementFacade cancelButton;

    @FindBy(linkText = "delete")
    protected WebElementFacade deleteButton;

    @FindBy(css = "[value = 'Add']")
    protected WebElementFacade addButton;

    @FindBy(css = "[value = 'View Documents']")
    protected WebElementFacade viewDocumentsButton;

    @FindBy(css = "[value = 'Update']")
    protected WebElementFacade updateButton;

    @FindBy(id = "name")
    protected WebElementFacade nameBox;

    @FindBy(id = "surname")
    protected WebElementFacade surnameBox;

    @FindBy(css = "[id*=Team]")
    protected WebElementFacade teamDropdown;

    @FindBy(css = "[id*='Title']")
    protected WebElementFacade titleDropdown;

    @FindBy(css = "[id*=FromDate]")
    protected WebElementFacade fromDate;

    @FindBy(css = "[id*=ToDate]")
    protected WebElementFacade toDate;

    @FindBy(css = "[id*=Gender]")
    protected WebElementFacade genderDropdown;

    @FindBy(css = "[id*=appearancesTable]")
    protected WebElementFacade appearancesTable;

    @FindBy(css = "[id*=PrisonerNumber]")
    protected WebElementFacade prisonerNumber;

    @FindBy(css = "[value = 'Renew]")
    protected WebElementFacade renewButton;

    @FindBy(linkText = "new")
    protected WebElementFacade newLink;

    @FindBy(linkText = "delete")
    protected WebElementFacade deleteLink;

    @FindBy(css = "[id*=Notes]")
    protected WebElementFacade notesBox;

    @FindBy(css = "[id*=newNotes]")
    protected WebElementFacade newNotesBox;

    @FindBy(css = "[id*=NationalSearch]")
    protected WebElementFacade searchTypeDropdown;

    @Managed
    WebDriver driver;

    @FindBy(css = "[id*='Relationship']")
    private WebElementFacade relationshipTypeDropdown;

    @FindBy(xpath = "//select[contains(@id, 'Gender')]/option[2]")
    private WebElementFacade genderDropdownSecondOption;

    public void associatedDocumentsIsDisplayed() {
        associatedDocumentTable.isDisplayed();
    }

    public void associatedDocumentsIsNotDisplayed() {
        associatedDocumentTable.shouldNotBeVisible();
    }

    public void enterStartDate(String startDate) {
        this.startDate.clear();
        this.startDate.sendKeys(startDate);
        Serenity.setSessionVariable("startDate").to(startDate);
    }

    public void enterEndDate(String endDate) {
        this.endDate.clear();
        this.endDate.sendKeys(endDate);
        Serenity.setSessionVariable("endDate").to(endDate);
    }

    public void enterNotes(String notes) {
        notesBox.clear();
        notesBox.sendKeys(notes);
        Serenity.setSessionVariable("notes").to(notes);
    }

    public void enterNewNotes(String notes) {
        newNotesBox.clear();
        newNotesBox.sendKeys(notes);
        Serenity.setSessionVariable("newNotes").to(notes);
    }

    public boolean updateLinkIsDisplayed() {
        return isElementDisplayed(updateLink);
    }

    public boolean updateButtonIsDisplayed() {
        return isElementDisplayed(updateButton);
    }

    public void clickNewLink() {
        newLink.click();
    }

    public void clickDeleteLink() {
        deleteLink.click();
    }

    public void clickAddUpdateButton() {
        addUpdateButton.click();
    }

    public void lookupTerms() {
        lookupButton.click();
    }

    public void assertTitle(String title) {
        assertThat(getTitle(), is(title));
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));

        return definitionList.findElements(By.tagName("li")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clearCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public String today() {
        Date date = Calendar.getInstance().getTime();

        return simpleDateFormat.format(date);
    }

    //This returns date as 01-JAN-18, only needed for searching in Oracle DB
    public String databaseDateTodayPlusYears(int years) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, years);

        return dbDateFormat.format(cal.getTime());
    }

    //This returns date as 01-JAN-18, only needed for searching in Oracle DB
    public String databaseDateTodayPlusDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return dbDateFormat.format(cal.getTime());
    }

    //This returns date as 01-JAN-18, only needed for searching in Oracle DB
    public String databaseDateTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return dbDateFormat.format(cal.getTime());
    }

    public String todayPlusYears(int years) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, years);

        return simpleDateFormat.format(cal.getTime());
    }

    public String todayPlusDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return simpleDateFormat.format(cal.getTime());
    }

    public String tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);

        return simpleDateFormat.format(cal.getTime());
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

    protected int getRandomNumber() {
        Random randomGenerator = new Random();

        int randomInt = randomGenerator.nextInt(CHAR_LIST.length());

        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    public int generateRandomNumber(int digits) {
        Random rand = new Random();

        double maxNumber = (Math.pow(10, digits) - 1);
        return rand.nextInt((int) maxNumber) + 1;
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

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void clearSearchFields() {
        clearSearchFields.click();
    }

    public void switchToOpenedWindow(WebDriver driver) {
        Serenity.setSessionVariable("originalWindowHandle").to(driver.getWindowHandle());
        driver.getWindowHandle();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
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

    public void clickRefreshButton() {
        refreshButton.click();
    }

    public void clickMoreButton() {
        moreButton.click();
    }

    public String getHeaderText() {
        return pageTitle.getText();
    }

    public void clickAcceptButton() {
        acceptButton.click();
    }

    public void clickAcceptAllButton() {
        acceptAllButton.click();
    }
    
    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickClearSearchFields() {
        clearSearchFields.click();
    }

    public void clickViewDocumentsButton() {
        viewDocumentsButton.click();
    }

    public void clickDocumentButton() {
        documentButton.click();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public void clickRenewButton() {
        renewButton.click();
    }

    public void clickCloseButton() {
        closeButton.click();
    }

    public void clickViewButton() {
        waitFor(viewButton);
        viewButton.click();
    }

    public void clickViewLink() {
        viewLink.click();
    }

    public void clickUpdateButton() {
        updateButton.click();
    }

    public void clickUpdateLink() {
        updateLink.click();
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public void clickFirstButton() {
        firstButton.click();
    }

    public void clickPreviousButton() {
        previousButton.click();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void clickLastButton() {
        lastButton.click();
    }

    public void enterDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
        Serenity.setSessionVariable("date").to(date);
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

}
