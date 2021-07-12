package com.hocs.test.pages.platform;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import config.CurrentUser;
import config.User;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends PageObject {

    static HashMap<String, String> dataRecords = new HashMap<>();

    public static String currentPlatform = "";

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
    protected WebElementFacade errorMessage;

    @FindBy(linkText = "Correspondence System")
    public WebElementFacade decsDashboardLink;

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

    @FindBy(id = "CaseNote_RejectionNote")
    protected WebElementFacade rejectReasonTextField;

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

    public void openAccordionSection(String accordionLabel) {
        WebElementFacade accordionSectionButton = findBy("//button[text()='" + accordionLabel +"']");
        safeClickOn(accordionSectionButton);
    }

    public void assertErrorMessageText(String text) {
        assertThat(getErrorMessageText(), containsString(text));
    }

    public void assertPageTitle(String title) {
        WebElementFacade pageTitle = find(By.xpath("//h1[@class='govuk-heading-l' and contains(text(), '" + title + "')]"));
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

    private void goToDECSDashboard() {
        safeClickOn(decsDashboardLink);
        waitForDashboard();
    }

    private void goToWCSDashboard() {
        safeClickOn(wcsDashboardLink);
        waitForDashboard();
    }

    public void waitForDashboard() {
        caseReferenceSearchBar.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible();
    }

    private void goToMUIDashboard() {
        safeClickOn(muiDashboardLink);
    }

    public void goToDashboard() {
        switch (currentPlatform.toUpperCase()) {
            case "DECS":
                goToDECSDashboard();
                break;
            case "WCS":
                goToWCSDashboard();
                break;
            case "DECS MANAGEMENT UI":
            case "WCS MANAGEMENT UI":
                goToMUIDashboard();
                break;
            default:
                pendingStep(currentPlatform + " is not defined within " + getMethodName());
        }
    }

    public void clickRejectButton() {
        safeClickOn(rejectButton);
    }

    public void enterRejectionNotes() {
        waitFor(rejectReasonTextField);
        String rejectionReason = "Rejection Reason: " + generateRandomString();
        rejectReasonTextField.sendKeys(rejectionReason);
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

    private String getErrorMessageText() {
        return errorMessage.getText();
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

    public boolean currentCaseIsLoaded() {
        if (header1.isCurrentlyVisible()) {
            try {
                if (header1.getText().equals(sessionVariableCalled("caseReference"))) {
                    return true;
                }
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
                return false;
            }
        }
        if (headerCaption1.isCurrentlyVisible()) {
            try {
                return headerCaption1.getText().equals(sessionVariableCalled("caseReference"));
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
                return false;
            }
        }
        return false;
    }

    public void safeClickOn(WebElementFacade webElementFacade) {
        webElementFacade.withTimeoutOf(Duration.ofSeconds(60)).waitUntilVisible().waitUntilClickable().click();
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

    public void selectRandomRadioButtonFromGroupWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        List<WebElementFacade> radioButtonElements = findAll(
                "//span[contains(@class,'govuk-fieldset__heading')][text() ='" + headingText + "']/ancestor::fieldset//input/following-sibling::label");
        WebElementFacade radioButtonElementToSelect = getRandomElementFromList(radioButtonElements);
        safeClickOn(radioButtonElementToSelect);
        addHeadingAndValueRecord(headingText,radioButtonElementToSelect.getText());
    }

    public void selectSpecificRadioButton(String radioButtonText) {
        WebElementFacade radioButtonElement = getRadioButtonLabelElementWithSpecifiedText(radioButtonText);
        safeClickOn(radioButtonElement);
        String heading = findBy("//label[text()='High']/ancestor::fieldset/legend/span").getText();
        addHeadingAndValueRecord(heading, radioButtonText);
    }

    public void selectSpecificRadioButtonFromGroupWithHeading(String radioButtonText, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade radioButtonElement = findBy("//span[contains(@class,'govuk-fieldset__heading')][text() ='" + headingText + "']/ancestor"
                + "::fieldset//input/following-sibling::label[text()='" + radioButtonText + "']");
        safeClickOn(radioButtonElement);
        addHeadingAndValueRecord(headingText, radioButtonText);
    }

    public void enterDateIntoDateFieldsWithHeading(String date, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade dayField = findBy("//legend[text()='" + headingText + "']/following-sibling::div/div[1]//input");
        WebElementFacade monthField = findBy("//legend[text()='" + headingText + "']/following-sibling::div/div[2]//input");
        WebElementFacade yearField = findBy("//legend[text()='" + headingText + "']/following-sibling::div/div[3]//input");
        typeIntoDateFields(dayField, monthField, yearField, date);
        addHeadingAndValueRecord(headingText, date);
    }

    public void enterRandomTextIntoTextFieldWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade textField = findBy("//label[text()='" + headingText + "']/following-sibling::div/input");
        String textToEnter = "Test entry for " + headingText;
        textField.sendKeys(textToEnter);
        addHeadingAndValueRecord(headingText, textToEnter);
    }

    public void enterRandomTextIntoTextAreaWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade textArea = findBy("//label[text()='" + headingText + "']/following-sibling::textarea");
        String textToEnter = "Test entry for " + headingText;
        textArea.sendKeys(textToEnter);
        addHeadingAndValueRecord(headingText, textToEnter);
    }

    public void selectRandomOptionFromDropdownWithHeading(String headingText) {
        waitForHeadingToBeVisible(headingText);
        List<WebElementFacade> optionElements = findAll("//div[@class='govuk-form-group']//*[text()='" + headingText + "']/following-sibling::select"
                + "/option");
        optionElements.remove(0);
        WebElementFacade optionElementToSelect = getRandomElementFromList(optionElements);
        safeClickOn(optionElementToSelect);
        addHeadingAndValueRecord(headingText,optionElementToSelect.getText());
    }

    public void selectSpecificOptionFromDropdownWithHeading(String optionText, String headingText) {
        waitForHeadingToBeVisible(headingText);
        WebElementFacade optionElement =
                findBy("//div[@class='govuk-form-group']//*[text()='" + headingText + "']/following-sibling::select/option[text()='" + optionText + "']");
        safeClickOn(optionElement);
        addHeadingAndValueRecord(headingText, optionText);
    }

    public void checkRandomCheckboxFromList(List<WebElementFacade> checkboxes) {
        WebElementFacade checkboxToCheck = getRandomElementFromList(checkboxes);
        safeClickOn(checkboxToCheck);
        addValueRecord(checkboxToCheck.getText());
    }

    public void checkSpecificCheckbox(String checkboxLabelText) {
        WebElementFacade checkbox = findBy("//input[@type='checkbox']/following-sibling::label[text()='" + checkboxLabelText + "']");
        safeClickOn(checkbox);
        addValueRecord(checkboxLabelText);
    }

    private void waitForHeadingToBeVisible(String headingText) {
        WebElementFacade headingElement = findBy("//div[contains(@class,'govuk-form-group')]//*[text()='" + headingText + "']");
        headingElement.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
    }

    public WebElementFacade getRandomElementFromList(List<WebElementFacade> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public WebElementFacade getRadioButtonLabelElementWithSpecifiedText(String elementText) {
        return findBy("//input/following-sibling::label[contains(text(),'" + elementText + "')]");
    }

    public void safeClickRadioButtonByVisibleText(String elementText) {
        safeClickOn(getRadioButtonLabelElementWithSpecifiedText(elementText));
    }

    public void addHeadingAndValueRecord(String heading, String value) {
        dataRecords.put(heading, value);
    }

    public void addValueRecord(String value) {
        dataRecords.put(value, "Yes");
    }

    public void assertAllRecordedCaseDataIsDisplayedInTheReadOnlyAccordionSection() {
        for(HashMap.Entry<String, String> entry : dataRecords.entrySet()) {
            WebElementFacade valueDisplayed = findBy("//Strong[contains(text(),'" + entry.getKey() + "')]/parent::span");
            valueDisplayed.shouldContainText(entry.getValue());
        }
    }
}