package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.foi.FOICreateCase;
import config.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CreateCase extends BasePage {

    Documents documents;

    CreateCase_SuccessPage createCaseSuccessPage;

    Dashboard dashboard;

    Workdays workdays;

    // Elements

    @FindBy(className = "govuk-radios")
    public WebElementFacade allRadioButtons;

    @FindBy(xpath = "//label[text()='DCU Ministerial']")
    public WebElementFacade dcuMinRadioButton;

    @FindBy(xpath = "//label[text()='DCU Treat Official']")
    public WebElementFacade dcuTroRadioButton;

    @FindBy(xpath = "//label[text()='DCU Number 10']")
    public WebElementFacade dcuDTenRadioButton;

    @FindBy(xpath = "//label[text()='MPAM Case']")
    public WebElementFacade mpamRadioButton;

    @FindBy(xpath = "//label[text()='MTS Case']")
    public WebElementFacade mtsRadioButton;

    @FindBy(xpath = "//label[text()='Complaint Case']")
    public WebElementFacade compRadioButton;

    @FindBy(xpath = "//label[text()='IE Detention Case']")
    public WebElementFacade iedetRadioButton;

    @FindBy(xpath = "//label[text()='Serious Misconduct Case']")
    public WebElementFacade smcRadioButton;

    @FindBy(xpath = "//label[text()='FOI Request']")
    public WebElementFacade foiRadioButton;

    @FindBy(id = "DateReceived-day")
    public WebElementFacade correspondenceReceivedDayField;

    @FindBy(id = "DateReceived-month")
    public WebElementFacade correspondenceReceivedMonthField;

    @FindBy(id = "DateReceived-year")
    public WebElementFacade correspondenceReceivedYearField;

    @FindBy(xpath = "//a[text()='Case type is required']")
    public WebElementFacade caseTypeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Date received is required']")
    public WebElementFacade dateReceivedIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Documents are mandatory when bulk creating a case']")
    public WebElementFacade documentsAreMandatoryErrorMessage;

    @FindBy(xpath = "//a[text()='Date received must be a valid date']")
    public WebElementFacade dateReceivedIsInvalidErrorMessage;

    @FindBy(css = "[value = 'Create case']")
    public WebElementFacade createCaseButton;

    @FindBy(css = "[value = 'Create cases']")
    public WebElementFacade createCasesButton;

    @FindBy(css = "[value = 'Create claim']")
    public WebElementFacade createClaimButton;

    // Basic Methods

    public void assertNoOptionsAvailable() {
        allRadioButtons.shouldContainText("No options available");
    }

    private void clickDcuMinRadioButton() {
        safeClickOn(dcuMinRadioButton);
    }

    private void clickDcuTroRadioButton() {
        safeClickOn(dcuTroRadioButton);
    }

    private void clickDcuDtenRadioButton() {
        safeClickOn(dcuDTenRadioButton);
    }

    private void clickMpamRadioButton() {
        safeClickOn(mpamRadioButton);
    }

    private void clickMtsRadioButton() {
        safeClickOn(mtsRadioButton);
    }

    private void clickCompRadioButton() {
        safeClickOn(compRadioButton);
    }

    private void clickFoiRadioButton() {
        safeClickOn(foiRadioButton);
    }

    private void clickIedetRadioButton() {
        safeClickOn(iedetRadioButton);
    }

    private void clickSmcRadioButton() {
        safeClickOn(smcRadioButton);
    }

    public void clickCreateCaseButton() {safeClickOn(createCaseButton);}

    public void clickCreateCasesButton() {safeClickOn(createCasesButton);}

    public void selectCaseType(String caseType) {
        if (caseType.equalsIgnoreCase("CS")) {
            caseType = selectRandomRadioButtonFromGroupWithHeading("What type of correspondence do you have?");
        } else {
            switch (caseType.toUpperCase()) {
                case "MIN":
                    clickDcuMinRadioButton();
                    break;
                case "TRO":
                    clickDcuTroRadioButton();
                    break;
                case "DTEN":
                    clickDcuDtenRadioButton();
                    break;
                case "MPAM":
                    clickMpamRadioButton();
                    break;
                case "MTS":
                    clickMtsRadioButton();
                    break;
                case "COMP":
                    clickCompRadioButton();
                    break;
                case "FOI":
                    clickFoiRadioButton();
                    break;
                case "IEDET":
                    clickIedetRadioButton();
                    break;
                case "SMC":
                    clickSmcRadioButton();
                    break;
                default:
                    pendingStep(caseType + " is not defined within " + getMethodName());
            }
        }
        setSessionVariable("caseType").to(caseType);
    }

    public void editReceivedDate(String date) {
        enterDateIntoDateFieldsWithHeading(date, "When was the correspondence received?");
    }

    // Multi Step Methods

    public void createCSCaseOfType(String caseType) {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        selectCaseType(caseType);
        safeClickOn(nextButton);
        //TODO : Remove below if statement once the defect HOCS-3933 is closed
        if(caseType!="SMC") {
            documents.uploadDocumentOfType("docx");
        }
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.storeCaseReference();
    }

    public void createCOMP2Case() {
        documents.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        setSessionVariable("caseType").to("COMP2");
        createCaseSuccessPage.storeCaseReference();
    }

    public void createCSCaseOfTypeWithoutDocument(String caseType) {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        selectCaseType(caseType);
        safeClickOn(nextButton);
        clickCreateCaseButton();
        createCaseSuccessPage.storeCaseReference();
    }

    public void createCaseWithSetCorrespondenceReceivedDate(String caseType, String date) {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        selectCaseType(caseType);
        safeClickOn(nextButton);
        waitFor(correspondenceReceivedDayField);
        editReceivedDate(date);
        documents.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.storeCaseReference();
    }

    public void createCaseReceivedFiveDaysBeforeOrAfterDate(String caseType, String beforeAfter, String inputDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        int numberOfDays = 0;
        if (beforeAfter.equalsIgnoreCase("BEFORE")) {
            numberOfDays = (-5);
        } else if (beforeAfter.equalsIgnoreCase("AFTER")) {
            numberOfDays = 5;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(inputDate));
        c.add(Calendar.DAY_OF_WEEK, numberOfDays);
        String date = format.format(c.getTime());
        createCaseWithSetCorrespondenceReceivedDate(caseType, date);
    }

    public void createCaseReceivedNWorkdaysAgo(String caseType, int days) {
        createCaseWithSetCorrespondenceReceivedDate(caseType, workdays.getDateXWorkdaysAgo(days));
    }

    public void createWCSCase() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        clickTheButton("Create claim");
        setSessionVariable("caseType").to("WCS");
        setCaseReferenceFromAssignedCase();
    }

    public void clearCorrespondentReceivedDateFields() {
        correspondenceReceivedDayField.clear();
        correspondenceReceivedMonthField.clear();
        correspondenceReceivedYearField.clear();
    }

    public void storeCorrespondenceReceivedDate() {
        String correspondenceDay = correspondenceReceivedDayField.getValue();
        setSessionVariable("correspondenceReceivedDay").to(correspondenceDay);
        String correspondenceMonth = correspondenceReceivedMonthField.getValue();
        setSessionVariable("correspondenceReceivedMonth").to(correspondenceMonth);
        String correspondenceYear = correspondenceReceivedYearField.getValue();
        setSessionVariable("correspondenceReceivedYear").to(correspondenceYear);
        setSessionVariable("correspondenceReceivedDate").to(correspondenceDay + "/" + correspondenceMonth + "/" +correspondenceYear);
    }

    public boolean checkTargetUserIsLoggedInUsingCreateCasePage(User targetUser) {
        boolean correctUser = false;
        backLink.waitUntilVisible().withTimeoutOf(Duration.ofSeconds(10));
        switch (targetUser.toString()) {
            case "DECS_USER":
                if (mtsRadioButton.isVisible() && dcuMinRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "DCU_USER":
                if (!mtsRadioButton.isVisible() && dcuMinRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "UKVI_USER":
                if (mtsRadioButton.isVisible() && !dcuMinRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "COMP_USER":
                if (compRadioButton.isVisible()) {
                    correctUser = true;
                }
            case "IEDET_USER":
                if (iedetRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "SMC_USER":
                if (smcRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "FOI_USER":
                if (foiRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "WCS_USER":
                if (createClaimButton.isVisible())
                    correctUser = true;
                break;
            default:
                pendingStep(targetUser + " is not defined within " + getMethodName());
        }
        return correctUser;
    }

    //Assertions

    public void assertPageTitle() {
        assertPageTitle("Create case");
    }

    public void assertCaseTypeErrorMessage() {
        caseTypeIsRequiredErrorMessage.shouldContainText("Case type is required");
    }

    public void assertDateReceivedNotEnteredErrorMessage() {
        dateReceivedIsRequiredErrorMessage.shouldContainText("Date received is required");
    }

    public void assertDateReceivedIsInvalidErrorMessage() {
        dateReceivedIsInvalidErrorMessage.shouldContainText("Date received must be a valid date");
    }
}
