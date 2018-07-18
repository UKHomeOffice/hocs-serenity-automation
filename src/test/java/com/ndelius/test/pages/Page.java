package com.ndelius.test.pages;

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

    @FindBy(css = "h1")
    public WebElementFacade pageTitle;

    @FindBy(id = "offender-overview")
    public WebElementFacade offenderDetails;

    @FindBy(css = "[id*=CircumstanceType]")
    protected WebElementFacade circumstanceTypeDropdown;

    @FindBy(xpath = "//select[contains(@id, 'CircumstanceType')]/option[2]")
    protected WebElementFacade circumstanceTypeDropdownSecondOption;

    @FindBy(css = "[id*=CircumstanceSubType]")
    protected WebElementFacade circumstanceSubtypeDropdown;

    @FindBy(xpath = "//select[contains(@id, 'CircumstanceSubType')]/option[2]")
    protected WebElementFacade circumstanceSubtypeDropdownSecondOption;

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

    @FindBy(css = "[id*=Cluster]")
    protected WebElementFacade cluster;

    @FindBy(xpath = "//select[@id='caseloadForm:Team']/option[2]")
    protected WebElementFacade teamDropdownSecondOption;

    @FindBy(css = "[id*=Officer]")
    protected WebElementFacade officerDropdown;

    @FindBy(xpath = "//select[@id='caseloadForm:Officer']/option[2]")
    protected WebElementFacade officerDropdownSecondOption;

    @FindBy(css = "[id*=Filter]")
    protected WebElementFacade filterDropdown;

    @FindBy(css = "#offender-overview")
    protected WebElementFacade offenderOverview;

    @FindBy(css = "[id*=crn]")
    protected WebElementFacade caseReferenceNumber;

    @FindBy(css = "[id*=ReferralDate]")
    protected WebElementFacade referralDate;

    @FindBy(css = "[value = 'Document']")
    protected WebElementFacade documentButton;

    @FindBy(linkText = "update")
    protected WebElementFacade updateLink;

    @FindBy(linkText = "view")
    protected WebElementFacade viewLink;

    @FindBy(linkText = "View")
    protected WebElementFacade viewButton;

    @FindBy(linkText = "View Offender")
    protected WebElementFacade viewOffenderLink;

    @FindBy(id = "ConvictionDate")
    protected WebElementFacade addConvictionDate;

    @FindBy(id = "MainOffence")
    protected WebElementFacade mainOffenceDropdown;

    @FindBy(id = "NsiProvider")
    protected WebElementFacade nsiProviderDropdown;

    @FindBy(id = "NsiType")
    protected WebElementFacade nsiTypeDropdown;

    @FindBy(id = "NsiSubType")
    protected WebElementFacade nsiSubTypeDropdown;

    @FindBy(id = "OffenceDate")
    protected WebElementFacade offenceDate;

    @FindBy(id = "OffenceCount")
    protected WebElementFacade offenceCount;

    @FindBy(css = "[id*=Provider]")
    protected WebElementFacade providerDropdown;

    @FindBy(id = "Status")
    protected WebElementFacade statusDropdown;

    @FindBy(id = "StatusDate")
    protected WebElementFacade statusDateBox;

    @FindBy(id = "StatusTime")
    protected WebElementFacade statusTimeBox;

    @FindBy(id = "Date")
    protected WebElementFacade dateField;

    @FindBy(id = "Court")
    protected WebElementFacade courtDropdown;

    @FindBy(css = "[id*=Type]")
    protected WebElementFacade typeDropdown;

    @FindBy(css = "[value='Add Offender']")
    protected WebElementFacade addOffenderButton;

    @FindBy(css = "[value='Withdraw']")
    protected WebElementFacade withdrawButton;

    @FindBy(css = "[value='Edit']")
    protected WebElementFacade editButton;

    @FindBy(css = "[value='Reject']")
    protected WebElementFacade rejectButton;

    @FindBy(css = "[value = 'Defaults']")
    protected WebElementFacade defaultsButton;

    @FindBy(className = "select2-search__field")
    protected WebElementFacade spgErrorDetailsAssignToSearchField;

    @FindBy(linkText = "Assign to me")
    protected WebElementFacade spgErrorDetailsAssignToMe;

    @FindBy(css = "[id*=incidentNumber]")
    protected WebElementFacade incidentNumberField;

    @FindBy(css = "[id*=ResultsTable]")
    protected WebElementFacade resultsTable;

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

    @FindBy(id = "offenderTransferRequestListForm:offenderTransferRequestTable:0:TransferReason")
    protected WebElementFacade transferOffenderReasonDropdown;

    @FindBy(id = "offenderTransferRequestListForm:offenderTransferRequestTable:1:TransferReason")
    protected WebElementFacade transferEventReasonDropdown;

    @FindBy(id = "offenderTransferRequestListForm:offenderTransferRequestTable:2:TransferReason")
    protected WebElementFacade transferLicenceReasonDropdown;

    @FindBy(id = "acceptOffenderTransferListForm:acceptTransferTable:0:checkbox")
    protected WebElementFacade offenderTransferCheckbox;

    @FindBy(id = "acceptOffenderTransferListForm:acceptTransferTable:0:TargetTeam")
    protected WebElementFacade offenderTransferTargetTeam;

    @FindBy(id = "acceptOffenderTransferListForm:acceptTransferTable:0:TargetStaff")
    protected WebElementFacade offenderTransferTargetOfficer;

    @FindBy(className = "noRecordsFound")
    protected WebElementFacade noRecordsFound;

    @FindBy(css = "[id*=StartDate]")
    protected WebElementFacade startDate;

    @FindBy(css = "[id*=EndDate]")
    protected WebElementFacade endDate;

    @FindBy(css = "[id*=searchResults]")
    protected WebElementFacade searchResults;

    @FindBy(id = "ReleaseDate")
    protected WebElementFacade actualReleaseDateField;

    @FindBy(css = "[id*=ReleaseType]")
    protected WebElementFacade releaseTypeDropdown;

    @FindBy(css = "[id*=Institution]")
    protected WebElementFacade releasedFromDropdown;

    @FindBy(css = "[id*=ThroughcareDateType]")
    protected WebElementFacade throughcareDateTypeDropdown;

    @FindBy(css = "[id*=ThroughcareDate]")
    protected WebElementFacade throughcareDateField;

    @FindBy(id = "linkNavigation3OffenderTransferRequest")
    protected WebElementFacade offenderTransferRequest;

    @FindBy(css = "input[type = 'submit'][value = 'Clear Search Fields']")
    protected WebElementFacade clearSearchFields;

    @FindBy(css = "[value = 'Confirm']")
    protected WebElementFacade confirmButton;

    @FindBy(linkText = "First")
    protected WebElementFacade firstButton;

    @FindBy(linkText = "Previous")
    protected WebElementFacade previousButton;

    @FindBy(linkText = "Next")
    protected WebElementFacade nextButton;

    @FindBy(linkText = "Last")
    protected WebElementFacade lastButton;

    @FindBy(className = "highlight")
    protected WebElementFacade noTableResults;

    @FindBy(css = "[value = 'Cancel']")
    protected WebElementFacade cancelButton;

    @FindBy(linkText = "delete")
    protected WebElementFacade deleteButton;

    @FindBy(css = "[title = 'View event']")
    protected WebElementFacade viewEvent;

    @FindBy(css = "[title = 'Add New Event']")
    protected WebElementFacade addNewEvent;

    @FindBy(css = "[value = 'Add']")
    protected WebElementFacade addButton;

    @FindBy(css = "[value = 'View Documents']")
    protected WebElementFacade viewDocumentsButton;

    @FindBy(css = "[value = 'Update']")
    protected WebElementFacade updateButton;

    @FindBy(css = "[id*=FirstName]")
    protected WebElementFacade firstNameBox;

    @FindBy(css = "[id*=MiddleName]")
    protected WebElementFacade middleNameBox;

    @FindBy(css = "[id*=LastName]")
    protected WebElementFacade lastNameBox;

    @FindBy(css = "[id*=Surname]")
    protected WebElementFacade surnameBox;

    @FindBy(css = "[id*=Team]")
    protected WebElementFacade teamDropdown;

    @FindBy(css = "[id*='Title']")
    protected WebElementFacade titleDropdown;

    @FindBy(xpath = "//select[contains(@id, 'Title')]/option[2]")
    protected WebElementFacade titleDropdownSecondOption;

    @FindBy(css = "[id*='Relationship']")
    private WebElementFacade relationshipTypeDropdown;

    @FindBy(xpath = "//select[contains(@id, 'Relationship')]/option[2]")
    protected WebElementFacade relationshipTypeDropdownSecondOption;

    @FindBy(css = "[id*=Trust]")
    protected WebElementFacade trustDropdown;

    @FindBy(css = "[id*=FromDate]")
    protected WebElementFacade fromDate;

    @FindBy(css = "[id*=ToDate]")
    protected WebElementFacade toDate;

    @FindBy(css = "[id*=Gender]")
    protected WebElementFacade genderDropdown;

    @FindBy(xpath = "//select[contains(@id, 'Gender')]/option[2]")
    private WebElementFacade genderDropdownSecondOption;

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

    @FindBy(css = "[id*=addPregnancyMaternity]")
    protected WebElementFacade addPregnancyAndMaternityLink;

    @FindBy(css = "[id*=pregnancyMaternity]")
    protected WebElementFacade pregnancyMaternityLink;

    @FindBy(css = "[id*=NationalSearch]")
    protected WebElementFacade searchTypeDropdown;


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

    public void clickViewOffenderLink() {
        viewOffenderLink.click();
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

        double maxNumber = (Math.pow(10,digits)-1);
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

    public void clickAddOffenderButton() {
        addOffenderButton.click();
    }

    public void clickOffenderTransferRequest() {
        offenderTransferRequest.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void clickClearSearchFields() {
        clearSearchFields.click();
    }

    public void clickViewEvent() {
        viewEvent.click();
    }

    public void clickAddNewEvent() {
        addNewEvent.click();
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

    public void selectProvider(String provider) {
        providerDropdown.selectByVisibleText(provider);
        Serenity.setSessionVariable("provider").to(provider);
    }

    public void selectTrust(String trust) {
        trustDropdown.selectByVisibleText(trust);
        Serenity.setSessionVariable("provider").to(trust);
    }

    public void selectFirstProvider() {
        providerDropdown.selectByIndex(1);
        String provider = providerDropdown.selectByIndex(1).getText().split("\n ")[1];
        Serenity.setSessionVariable("provider").to(provider);
    }

    public void selectCourt(String court) {
        courtDropdown.selectByVisibleText(court);
        Serenity.setSessionVariable("court").to(court);
    }

    public void selectFirstCourt() {
        courtDropdown.selectByIndex(1);
        String court = courtDropdown.selectByIndex(1).getText().split("\n ")[1];
        Serenity.setSessionVariable("court").to(court);
    }

    public void selectRelationshipTypeDropdown(String relationshipType) {
        waitFor(relationshipTypeDropdownSecondOption);
        releaseTypeDropdown.selectByVisibleText(relationshipType);
        setSessionVariable("relationshipType").to(relationshipType);
    }

    public void selectTitleDropdown(String title) {
        waitFor(titleDropdownSecondOption);
        titleDropdown.selectByVisibleText(title);
        setSessionVariable("title").to(title);
    }

    public void selectType(String type) {
        typeDropdown.selectByVisibleText(type);
        Serenity.setSessionVariable("type").to(type);
    }

    public void selectFirstType() {
        typeDropdown.selectByIndex(1);
        String type = typeDropdown.selectByIndex(1).getText().split("\n ")[1];
        Serenity.setSessionVariable("type").to(type);
    }

    public void clickDefaultsButton() {
        defaultsButton.click();
    }

    public void assertCircumstanceTypeDropdownIs(String expectedValue) {
        assertThat(circumstanceTypeDropdown.getAttribute("title"), is(expectedValue));
    }

    public void assertCircumstanceSubTypeDropdownIs(String expectedValue) {
        assertThat(circumstanceSubtypeDropdown.getText(), is(expectedValue));
    }

    public void selectCircumstanceType(String circumstanceType) {
        circumstanceTypeDropdown.selectByVisibleText(circumstanceType);
        Serenity.setSessionVariable("circumstanceType").to(circumstanceType);
    }

    public void selectCircumstanceSubType(String circumstanceSubType) {
        circumstanceSubtypeDropdown.selectByVisibleText(circumstanceSubType);
        Serenity.setSessionVariable("circumstanceSubType").to(circumstanceSubType);
    }

    public void modifyHeadersAndNavigateTo(String page){
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("C:\\Users\\dom.barnett\\Downloads\\ModHeader_v2_2_3_0.crx"));

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
                        "    {enabled: true, name: 'X-Auth-Roles', value: 'CREATE,BULK,DOCUMENT,DCU,UKVI,FOI', comment: ''},  " +
                        "    {enabled: true, name: 'X-Auth-UserId', value: 'Test', comment: ''}, " +
                        "    {enabled: true, name: 'X-Auth-Username', value: 'Test', comment: ''}  " +
                        "  ],                                                              " +
                        "  respHeaders: [],                                                " +
                        "  filters: []                                                     " +
                        "}]));                                                             ");
        driver.get(page);
    }

}
