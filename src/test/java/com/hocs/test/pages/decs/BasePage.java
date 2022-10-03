package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import config.CurrentUser;
import config.PreviousUser;
import config.User;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends PageObject {

    public static String currentPlatform = "";



    public static boolean keepAllCaseData = false;

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz";

    private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    public WebElementFacade logoutButton;

    @FindBy(css = "[value='Accept']")
    public WebElementFacade acceptButton;

    @FindBy(css = "[value = 'Add']")
    public WebElementFacade addButton;

    @FindBy(xpath = "//a[@class='govuk-back-link']")
    public WebElementFacade backLink;

    @FindBy(css = "[value='Continue']")
    public WebElementFacade continueButton;

    @FindBy(className = "govuk-error-summary")
    protected WebElementFacade errorMessageList;

    @FindBy(linkText = "Correspondence System")
    public WebElementFacade csDashboardLink;

    @FindBy(linkText = "Windrush Compensation")
    public WebElementFacade wcsDashboardLink;

    @FindBy(linkText = "Correspondence System Management")
    public WebElementFacade muiDashboardLink;

    @FindBy(css = "[value = 'Next']")
    public WebElementFacade nextButton;

    @FindBy(xpath = "//h1[@class='govuk-heading-l']")
    protected WebElementFacade header1;

    @FindBy(xpath = "//span[@class='govuk-caption-l']")
    public WebElementFacade headerCaption1;

    @FindBy(xpath = "//h1")
    protected WebElementFacade managementUIPageTitle;

    @FindBy(css = "[value='Reject']")
    public WebElementFacade rejectButton;

    @FindBy(css = "[value = 'Save']")
    public WebElementFacade saveButton;

    @FindBy(css = "[value = 'Search']")
    public WebElementFacade searchButton;

    @FindBy(css = "[value = 'Finish']")
    public WebElementFacade finishButton;

    @FindBy(css = "[value='Confirm']")
    public WebElementFacade confirmButton;

    @FindBy(xpath = "//a[text()='Accessibility']")
    public WebElementFacade accessibilityLink;

    @FindBy(xpath = "//input[@id='case-reference']")
    public WebElementFacade caseReferenceSearchBar;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//a[@class='tab'][not(@class='tab__active')]")
    public WebElementFacade nonActiveTab;

    public void waitABit(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void typeIntoDateFields(WebElementFacade ddField, WebElementFacade mmField, WebElementFacade yyyyField, String date) {
        String dd = date.split("/")[0];
        String mm = date.split("/")[1];
        String yyyy = date.split("/")[2];
        ddField.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible().clear();
        ddField.sendKeys(dd);
        mmField.clear();
        mmField.sendKeys(mm);
        yyyyField.clear();
        yyyyField.sendKeys(yyyy);
    }

    public void clickTheButton(String buttonLabel) {
        WebElementFacade button = find(By.cssSelector("input[value='" + buttonLabel + "' i]"));
        safeClickOn(button);
    }

    public void clickTheLink(String linkText) {
        WebElementFacade link = findBy("//a[contains(text(), '" + linkText + "')]");
        safeClickOn(link);
    }

    public void selectTheTab(String tabName) {
        WebElementFacade tab = getTabElementUsingTabName(tabName);
        tab.withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
        if (!tab.getAttribute("class").contains("selected")) {
            try {
                tab.click();
            } catch (ElementNotVisibleException | StaleElementReferenceException ex) {
                waitABit(500);
                tab = getTabElementUsingTabName(tabName);
                tab.click();
            }
        }
    }

    private WebElementFacade getTabElementUsingTabName(String tabName) {
        return findBy("//a[text()='" + tabName + "']/parent::li[contains(@class,'govuk-tabs__list-item')]");
    }

    public void refreshTheTab(String tabName) {
        WebElementFacade nonActiveTab = findBy("//li[@class='govuk-tabs__list-item'][not(@class='govuk-tabs__list-item--selected')]");
        nonActiveTab.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
            try {
                nonActiveTab.click();
            } catch (ElementNotVisibleException | StaleElementReferenceException ex) {
                waitABit(500);
                nonActiveTab = findBy("//li[@class='govuk-tabs__list-item'][not(@class='govuk-tabs__list-item--selected')]");
                nonActiveTab.click();
            }
        selectTheTab(tabName);
    }

    public boolean accordionSectionIsVisible(String accordionLabel) {
        WebElementFacade accordionSectionButton = findBy("//button/span/span[text()='" + accordionLabel +"']");
        return accordionSectionButton.isCurrentlyVisible();
    }

    public void openOrCloseAccordionSection(String accordionLabel) {
        WebElementFacade accordionSectionButton = findBy("//button/span/span[text()='" + accordionLabel +"']");
        safeClickOn(accordionSectionButton);
    }

    public void assertErrorMessageText(String text) {
        assertThat(getAllErrorMessageText(), containsString(text));
    }

    public void assertPageTitle(String title) {
        WebElementFacade pageTitle = find(By.xpath("//h1[@class='govuk-heading-l' and contains(text(), '" + title + "')]"));
        pageTitle.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        assert (pageTitle.isVisible());
    }

    public void assertManagementUIPageTitle(String title) {
        WebElementFacade pageTitle = find(By.xpath("//h1[@class='govuk-heading-xl' and contains(text(), '" + title + "')]"));
        pageTitle.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible();
        assert (pageTitle.isVisible());
    }

    public void waitForPageWithTitle(String pageTitle) {
        int retries = 0;
        while (retries < 3) {
            try {
                assertPageTitle(pageTitle);
                break;
            } catch (AssertionError e) {
                retries++;
            }
        }
    }

    public void waitForMUIPageWithTitle(String pageTitle) {
        int retries = 0;
        while (retries < 3) {
            try {
                assertManagementUIPageTitle(pageTitle);
                break;
            } catch (AssertionError e) {
                retries++;
            }
        }
    }

    public void clickAddButton() {
        safeClickOn(addButton);
    }

    public void clickBackButton() {
        safeClickOn(backLink);
    }

    public void clickContinueButton() {
        waitFor(continueButton).waitUntilClickable();
        safeClickOn(continueButton);
    }

    public boolean dcuCase() { return minCase() | dtenCase() | troCase(); }

    public boolean minCase() {
        return sessionVariableCalled("caseType").toString().equals("MIN");
    }

    public boolean dtenCase() {
        return sessionVariableCalled("caseType").toString().equals("DTEN");
    }

    public boolean troCase() {
        return sessionVariableCalled("caseType").toString().equals("TRO");
    }

    public boolean mpamCase() {
        return sessionVariableCalled("caseType").toString().equals("MPAM");
    }

    public boolean mtsCase() { return sessionVariableCalled("caseType").toString().equals("MTS"); }

    public boolean complaintCase() { return compCase() | comp2Case() | iedetCase() | smcCase() | bfCase() | bf2Case() | pogrCase() | pogr2Case();}

    public boolean compCase() { return sessionVariableCalled("caseType").toString().equals("COMP"); }

    public boolean comp2Case() { return sessionVariableCalled("caseType").toString().equals("COMP2"); }

    public boolean iedetCase() { return sessionVariableCalled("caseType").toString().equals("IEDET"); }

    public boolean bfCase() { return sessionVariableCalled("caseType").toString().equals("BF"); }

    public boolean bf2Case() { return sessionVariableCalled("caseType").toString().equals("BF2"); }

    public boolean pogrCase() { return sessionVariableCalled("caseType").toString().equals("POGR"); }

    public boolean pogr2Case() { return sessionVariableCalled("caseType").toString().equals("POGR2"); }

    public boolean smcCase() { return sessionVariableCalled("caseType").toString().equals("SMC"); }

    public boolean foiCase() {
        return sessionVariableCalled("caseType").toString().equals("FOI");
    }

    public boolean toCase() { return sessionVariableCalled("caseType").toString().equals("TO"); }

    public boolean wcsCase() {
        return sessionVariableCalled("caseType").toString().equals("WCS");
    }

    public void javascriptScrollToElem(WebElementFacade element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void assertThatAnErrorMessageIsDisplayed() {
        assertThat(isElementDisplayed(errorMessageList), is(true));
    }

    private String getAllErrorMessageText() {
        return errorMessageList.getText();
    }

    protected String generateRandomString() {
        return generateRandomStringOfLength(8);
    }

    protected String generateRandomStringOfLength(int length) {
        StringBuilder randStr = new StringBuilder();
        Random randomGenerator = new Random();
        for (int i = 1; i <= length; i++) {
            char ch = (char) ('a' + randomGenerator.nextInt(26));
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public Boolean containsIgnoreCase(String stringToCheck, String stringToCheckFor) {
        return Pattern.compile(Pattern.quote(stringToCheckFor), Pattern.CASE_INSENSITIVE).matcher(stringToCheck).find();
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

    public String getTodaysDate() {
        return getDatePlusMinusNDaysAgo(0);
    }

    public String getDatePlusMinusNDaysAgo(int days) {
        return todayPlusMinusNDaysGetDay(days) + "/" + todayPlusMinusNDaysGetMonth(days) + "/" + todayPlusMinusNDaysGetYear(days);
    }

    private String todayPlusMinusNDaysGetDay(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return dayFormat.format(cal.getTime());
    }

    private String todayPlusMinusNDaysGetMonth(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return monthFormat.format(cal.getTime());
    }

    private String todayPlusMinusNDaysGetYear(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        return yearFormat.format(cal.getTime());
    }

    public void assertElementIsDisplayed(WebElementFacade element) {
        assertThat(isElementDisplayed(element), is(true));
    }

    public void assertElementTextIs(WebElementFacade elem, String thisElementsText) {
        System.out.println(thisElementsText);
        assertThat(elem.getValue(), is(thisElementsText));
    }

    public String setCaseReferenceFromAssignedCase() {
        headerCaption1.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10));
        waitFor(ExpectedConditions.textToBePresentInElement(headerCaption1, sessionVariableCalled("caseType")))
                .withTimeoutOf(Duration.ofSeconds(20));
        setSessionVariable("caseReference").to(headerCaption1.getText());
        return headerCaption1.getText();
    }

    public String setCaseReferenceFromUnassignedCase() {
        waitFor(ExpectedConditions.textToBePresentInElement(header1, sessionVariableCalled("caseType"))).withTimeoutOf(Duration.ofSeconds(20));
        setSessionVariable("caseReference").to(header1.getText());
        return header1.getText();
    }

    public String getCurrentCaseReference() {
        return sessionVariableCalled("caseReference");
    }

    public String getCaseTypeFromCaseReference(String caseReference) {
        return caseReference.split("/")[0];
    }

    public String getCurrentCaseReferenceFromTransferredCase(String newCaseType) {
        String newCaseRef = getCurrentCaseReference().replace(sessionVariableCalled("caseType"),newCaseType);
        setSessionVariable("newCaseReference").to(newCaseRef);
        return sessionVariableCalled("newCaseReference");
    }

    public String getCurrentCaseType() {
        String caseType = getCurrentCaseReference().split("/")[0];
        return caseType.toUpperCase();
    }

    public void safeClickOn(WebElementFacade webElementFacade) {
        webElementFacade.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible().waitUntilClickable();
        try {
            javascriptScrollToElem(webElementFacade);
            webElementFacade.click();
        } catch (StaleElementReferenceException e) {
            waitABit(500);
            javascriptScrollToElem(webElementFacade);
            webElementFacade.click();
        }
    }

    public void jsClickOn(WebElementFacade webElementFacade) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("arguments[0].click()", webElementFacade);
    }

    public void setCurrentUser(User user) {
        CurrentUser.getInstance().setUser(user);
        System.out.println("Logged in as user: " + user.getUsername());
    }

    public User getCurrentUser() {
        return CurrentUser.getInstance().getUser();
    }

    public void setPreviousUser(User user) {
        PreviousUser.getInstance().setUser(user);
        System.out.println("Previous user recorded as: " + user.getUsername());
    }

    public User getPreviousUser() {
        return PreviousUser.getInstance().getUser();
    }

    public void keepAllCaseDataWhenProgressingCase() {
        keepAllCaseData = true;
    }

    protected void checkOrderOfHeaderTagsOnCaseView() {
        int n = 2;
        String dom = getDriver().getPageSource();
        while (n <= 6) {
            if (dom.contains("<h" + n)) {
                String pageSourceBeforeHTag = dom.split("<h" + (n - 1))[0];
                assertThat(pageSourceBeforeHTag.contains("<h" + n), is(false));
            }
            n++;
        }
    }

    public void assertVisibilityOfAccessibilityLink() {
        accessibilityLink.shouldBeVisible();
    }

    public void assertExpectedErrorMessageIsDisplayed(String errorMessageText) {
        if (!getAllErrorMessageText().contains(errorMessageText)) {
            Assert.fail("Expected error message '" + errorMessageText + "' is not contained in the displayed list of error messages:\n" + getAllErrorMessageText());
        }
    }

    public void selectTheStageAction(String action) {
        selectSpecificRadioButtonFromGroupWithHeading(action, "Actions");
    }

    //Helper methods

    //Ignore field label case

    public String getValidFieldLabelCase(String fieldLabelText) {
        int n = 0;
        List<WebElementFacade> listOfFieldLabelsOnPage = findAll("//label");
        while (n < listOfFieldLabelsOnPage.size()) {
            String displayedFieldLabel = listOfFieldLabelsOnPage.get(n).getText();
            if (displayedFieldLabel.equalsIgnoreCase(fieldLabelText)) {
                return displayedFieldLabel;
            }
            n++;
        }
        System.out.println("The " + fieldLabelText + " field isn't displayed on this screen");
        return null;
    }

    //Radio buttons

    public String selectRandomRadioButtonFromGroupWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        List<WebElementFacade> radioButtonElements = getRadioButtonElementsInGroupWithHeading(headingText);
        WebElementFacade radioButtonElementToSelect = getRandomCurrentlyVisibleElementFromList(radioButtonElements);
        safeClickOn(radioButtonElementToSelect);
        return radioButtonElementToSelect.getText();
    }

    public String selectSpecificRadioButton(String radioButtonText) {
        WebElementFacade radioButtonElement = getRadioButtonLabelElementWithSpecifiedText(radioButtonText);
        safeClickOn(radioButtonElement);
        String heading =
                findBy("//label[contains(text()," + sanitiseXpathAttributeString(radioButtonText) + ")]/ancestor::fieldset/legend/span").getText();
        return heading;
    }

    public void selectSpecificRadioButtonFromGroupWithHeading(String radioButtonText, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade radioButtonElement =
                findBy("//span[contains(@class,'govuk-fieldset__heading')][text() =" + sanitiseXpathAttributeString(headingText) + "]/ancestor"
                + "::fieldset//input/following-sibling::label[text()=" + sanitiseXpathAttributeString(radioButtonText) + "]");
        safeClickOn(radioButtonElement);
    }

    public String selectDifferentRadioButtonFromGroupWithHeading(String headingText) {
        List<String> radioButtonLabels = getRadioButtonLabelsInGroupWithHeading(headingText);
        WebElementFacade currentlySelectedRadioButton =
                findBy("//span[contains(@class,'govuk-fieldset__heading')][text() =" + sanitiseXpathAttributeString(headingText) + "]/ancestor"
                        + "::fieldset//input[@checked]/following-sibling::label");
        radioButtonLabels.remove(currentlySelectedRadioButton.getText());
        Random random = new Random();
        String radioButtonLabelToSelect = radioButtonLabels.get(random.nextInt(radioButtonLabels.size()));
        selectSpecificRadioButtonFromGroupWithHeading(radioButtonLabelToSelect, headingText);
        return radioButtonLabelToSelect;
    }

    private WebElementFacade getRadioButtonLabelElementWithSpecifiedText(String elementText) {
        return findBy("//input/following-sibling::label[contains(text()," + sanitiseXpathAttributeString(elementText) + ")]");
    }

    private List<WebElementFacade> getRadioButtonElementsInGroupWithHeading(String headingText) {
        return findAll("//span[contains(@class,'govuk-fieldset__heading')][text() =" + sanitiseXpathAttributeString(headingText) + "]/ancestor::fieldset"
                        + "//input/following-sibling::label");
    }

    public List<String> getRadioButtonLabelsInGroupWithHeading(String headingText) {
        List<WebElementFacade> radioButtonElements = getRadioButtonElementsInGroupWithHeading(headingText);
        List<String> radioButtonLabels = new ArrayList<>();
        for (WebElementFacade radioButtonElement : radioButtonElements) {
            radioButtonLabels.add(radioButtonElement.getText());
        }
        return radioButtonLabels;
    }

    //Date fields

    public void enterDateIntoDateFieldsWithHeading(String date, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade dayField = getVisibleDayFieldWithMatchingHeading(headingText);
        WebElementFacade monthField = getVisibleMonthFieldWithMatchingHeading(headingText);
        WebElementFacade yearField = getVisibleYearFieldWithMatchingHeading(headingText);
        typeIntoDateFields(dayField, monthField, yearField, date);
    }

    public void clearDateFieldsWithHeading(String headingText) {
        getVisibleDayFieldWithMatchingHeading(headingText).clear();
        getVisibleMonthFieldWithMatchingHeading(headingText).clear();
        getVisibleYearFieldWithMatchingHeading(headingText).clear();
    }

    private WebElementFacade getVisibleDayFieldWithMatchingHeading(String headingText) {
        List<WebElementFacade> dayFieldsWithMatchingHeading = findAll("//legend[text()=" + sanitiseXpathAttributeString(headingText) + "]/following"
                + "-sibling::div/div[1]//input");
        return getOnlyCurrentlyVisibleElementFromList(dayFieldsWithMatchingHeading);
    }

    private WebElementFacade getVisibleMonthFieldWithMatchingHeading(String headingText) {
        List<WebElementFacade> monthFieldsWithMatchingHeading = findAll("//legend[text()=" + sanitiseXpathAttributeString(headingText) +
                "]/following-sibling::div/div[2]//input");
        return getOnlyCurrentlyVisibleElementFromList(monthFieldsWithMatchingHeading);
    }

    private WebElementFacade getVisibleYearFieldWithMatchingHeading(String headingText) {
        List<WebElementFacade> yearFieldsWithMatchingHeading = findAll("//legend[text()=" + sanitiseXpathAttributeString(headingText) +
                "]/following-sibling::div/div[3]//input");
        return getOnlyCurrentlyVisibleElementFromList(yearFieldsWithMatchingHeading);
    }

    public String getDisplayedDateInDateFieldsWithHeading(String headingText) {
        return getVisibleDayFieldWithMatchingHeading(headingText).getText() + "/" + getVisibleMonthFieldWithMatchingHeading(headingText).getText() + "/" + getVisibleYearFieldWithMatchingHeading(headingText).getText();
    }

    public boolean dateFieldsWithHeadingAreCurrentlyVisible(String headingText) {
        WebElementFacade dayFieldWithMatchingHeading = find("//legend[text()=" + sanitiseXpathAttributeString(headingText) + "]/following"
                + "-sibling::div/div[1]//input");
        return dayFieldWithMatchingHeading.isCurrentlyVisible();
    }

    //Text fields

    public String enterTextIntoTextFieldWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        String textToEnter = "Test entry for " + headingText;
        enterSpecificTextIntoTextFieldWithHeading(textToEnter, headingText);
        return textToEnter;
    }

    public void enterSpecificTextIntoTextFieldWithHeading(String textToEnter, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade textField = getVisibleTextFieldWithMatchingHeading(headingText);
        textField.clear();
        textField.sendKeys(textToEnter);
    }

    private WebElementFacade getVisibleTextFieldWithMatchingHeading(String headingText) {
        List<WebElementFacade> textFieldsWithMatchingHeading = findAll("//label[text()=" + sanitiseXpathAttributeString(headingText) + "]/following"
                + "-sibling::div/input");
        return getOnlyCurrentlyVisibleElementFromList(textFieldsWithMatchingHeading);
    }

    // Text areas

    public String enterTextIntoTextAreaWithHeading(String headingText) {
        String textToEnter = "Test entry for " + headingText +" 1\nTest entry for " + headingText + " 2\nTest entry for " + headingText +
                " 3";
        enterSpecificTextIntoTextAreaWithHeading(textToEnter, headingText);
        String sanitisedText = textToEnter.replace("\n", " ");
        return sanitisedText;
    }

    public void enterSpecificTextIntoTextAreaWithHeading(String textToEnter, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade textArea = getVisibleTextAreaWithMatchingHeading(headingText);
        textArea.clear();
        textArea.sendKeys(textToEnter);
    }

    private WebElementFacade getVisibleTextAreaWithMatchingHeading(String headingText) {
        List<WebElementFacade> textAreasWithMatchingHeading = findAll("//label[text()=" + sanitiseXpathAttributeString(headingText) + "]/following"
                + "-sibling::textarea");
        return getOnlyCurrentlyVisibleElementFromList(textAreasWithMatchingHeading);
    }

    //Drop downs

    public String selectRandomOptionFromDropdownWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        List<WebElementFacade> optionElements = getOptionElementsForDropdownWithHeading(headingText);
        optionElements.remove(0);
        WebElementFacade optionElementToSelect = getRandomCurrentlyVisibleElementFromList(optionElements);
        safeClickOn(optionElementToSelect);
        return optionElementToSelect.getText();
    }

    public void selectSpecificOptionFromDropdownWithHeading(String optionText, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade optionElement =
                findBy("//div[@class='govuk-form-group']//*[text()=" + sanitiseXpathAttributeString(headingText) + "]/following-sibling::select"
                        + "/option[text()='" + optionText + "']");
        safeClickOn(optionElement);
    }

    public String selectDifferentOptionFromDropdownWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        Select dropdown = new Select(findBy("//div[@class='govuk-form-group']//*[text()=" + sanitiseXpathAttributeString(headingText) +
                "]/following-sibling"
                + "::select"));
        List<WebElement> options = dropdown.getOptions();
        options.remove(0);
        options.remove(dropdown.getFirstSelectedOption());
        Random random = new Random();
        String optionToSelect = options.get(random.nextInt(options.size())).getText();
        selectSpecificOptionFromDropdownWithHeading(optionToSelect, headingText);
        return optionToSelect;
    }

    public List<WebElementFacade> getOptionElementsForDropdownWithHeading(String headingText) {
        return findAll("//div[@class='govuk-form-group']//*[text()=" + sanitiseXpathAttributeString(headingText) + "]/following-sibling::select"
                + "/option");
    }

    public List<String> getSelectableOptionsFromDropdownWithHeading(String headingText) {
        List<WebElementFacade> optionElements = findAll("//div[@class='govuk-form-group']//*[text()=" + sanitiseXpathAttributeString(headingText) +
                "]/following-sibling::select/option");
        optionElements.remove(0);
        List<String> selectableOptions = new ArrayList<>();
        for (WebElementFacade optionElement: optionElements) {
            selectableOptions.add(optionElement.getText());
        }
        return selectableOptions;

    }

    public Boolean checkIfSelectableOptionsPresentInDropdownWithHeading(String headingText) {
        return (getOptionElementsForDropdownWithHeading(headingText).size() > 1);
    }

    //Checkboxes

    public String checkRandomCheckboxFromList(List<WebElementFacade> checkboxes) {
        WebElementFacade checkboxToCheck = getRandomCurrentlyVisibleElementFromList(checkboxes);
        safeClickOn(checkboxToCheck);
        return checkboxToCheck.getText();
    }

    public String checkRandomCheckboxUnderHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        List<WebElementFacade> checkboxElements =
                findAll("//h2[contains(text(), " + sanitiseXpathAttributeString(headingText) + ")]/parent::div//label");
        WebElementFacade checkboxToCheck = getRandomCurrentlyVisibleElementFromList(checkboxElements);
        safeClickOn(checkboxToCheck);
        return checkboxToCheck.getText();
    }

    public void checkSpecificCheckbox(String checkboxLabelText) {
        List<WebElementFacade> checkboxElements =
                findAll("//input[@type='checkbox']/following-sibling::label[text()=" + sanitiseXpathAttributeString(checkboxLabelText) + "]");
        safeClickOn(getOnlyCurrentlyVisibleElementFromList(checkboxElements));
    }

    public boolean checkboxWithLabelIsCurrentlyVisible(String checkboxLabelText) {
        WebElementFacade checkbox =
                findBy("//input[@type='checkbox']/following-sibling::label[text()=" + sanitiseXpathAttributeString(checkboxLabelText) + "]");
        return  checkbox.isCurrentlyVisible();
    }

    // Typeaheads

    public String selectRandomOptionFromTypeaheadWithHeading(String headingText) {
        WebElementFacade typeahead = findBy("//label[text()=" + sanitiseXpathAttributeString(headingText) + "]/following-sibling::div//input");
        safeClickOn(typeahead);
        waitABit(200);
        boolean selectableOptionVisibleInTypeahead = false;
        List<WebElementFacade> typeaheadOptions = null;
        int attempts = 0;
        while (!selectableOptionVisibleInTypeahead && attempts < 20) {
            typeahead.clear();
            typeahead.sendKeys(generateRandomStringOfLength(1));
            waitABit(1000);
            typeaheadOptions = findAll("//div[contains(@class,'option')]");
            selectableOptionVisibleInTypeahead = typeaheadOptions.size() > 1;
            attempts++;
        }
        Random random = new Random();
        WebElementFacade optionToSelect = typeaheadOptions.get(random.nextInt(typeaheadOptions.size()));
        safeClickOn(optionToSelect);
        waitABit(500);
        WebElementFacade selectedOption = findBy("//div[contains(@class,'govuk-typeahead__single-value')]");
        return selectedOption.getText();
    }

    public String selectSpecificOptionFromTypeaheadWithHeading(String optionText, String headingText) {
        WebElementFacade typeahead = findBy("//label[text()=" + sanitiseXpathAttributeString(headingText) + "]/following-sibling::div//input");
        safeClickOn(typeahead);
        waitABit(200);
        typeahead.sendKeys(optionText);
        waitABit(500);
        typeahead.sendKeys(Keys.RETURN);
        waitABit(500);
        WebElementFacade selectedOption = findBy("//div[contains(@class,'govuk-typeahead__single-value')]");
        return selectedOption.getText();
    }

    // Other methods

    private void waitForHeadingToBeVisible(String headingText) {
        List<WebElementFacade> matchingHeadings;
        int i = 0;
        while (i<20) { matchingHeadings = findAll("//div[contains(@class,'govuk-form-group')]//*[text()=" + sanitiseXpathAttributeString(headingText) +
                "]");
            if (matchingHeadings.size() > 0) {
                try {
                    getOnlyCurrentlyVisibleElementFromList(matchingHeadings);
                    break;
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
            waitABit(1000);
            i++;
        }
    }

    private WebElementFacade getRandomCurrentlyVisibleElementFromList(List<WebElementFacade> list) {
        Random rand = new Random();
        WebElementFacade randomElement = list.get(rand.nextInt(list.size()));
        while (!randomElement.isCurrentlyVisible()) {
            list.remove(randomElement);
            randomElement = list.get(rand.nextInt(list.size()));
        }
        return randomElement;
    }

    public WebElementFacade getOnlyCurrentlyVisibleElementFromList(List<WebElementFacade> list) throws IndexOutOfBoundsException {
        List<WebElementFacade> optionElements = new ArrayList<>();
        for (WebElementFacade element : list) {
            if (element.isCurrentlyVisible()) {
                optionElements.add(element);
            }
        }
        if (optionElements.size() > 1) {
            Assert.fail("Element identifier not sufficiently unique");
        }
        if (optionElements.size() < 1) {
            Assert.fail("No visible elements matching identifier found");
        }
        return optionElements.get(0);
    }

    private String sanitiseXpathAttributeString(String attribute){
        if (attribute.contains("'")) {
            attribute = attribute.replaceAll("'", "',\"'\",'");
            attribute = "concat('" + attribute + "')";
        } else {
            attribute = "'" + attribute + "'";
        }
        return attribute;
    }
}