package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

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

    @FindBy(xpath = "//label[text()='FOI Request']/preceding-sibling::input")
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

    @FindBy(id = "KimuDateReceived-day")
    public WebElementFacade dateKIMUReceivedDayField;

    @FindBy(id = "KimuDateReceived-month")
    public WebElementFacade dateKIMUReceivedMonthField;

    @FindBy(id = "KimuDateReceived-year")
    public WebElementFacade dateKIMUReceivedYearField;

    @FindBy(id = "RequestQuestion")
    public WebElementFacade requestQuestionTextField;

    @FindBy(id = "Fullname")
    public WebElementFacade fullNameTextField;

    @FindBy(id = "Address1")
    public WebElementFacade buildingTextField;

    @FindBy(id = "Address2")
    public WebElementFacade streetTextField;

    @FindBy(id = "Address3")
    public WebElementFacade townOrCityTextField;

    @FindBy(id = "Postcode")
    public WebElementFacade postcodeTextField;

    @FindBy(id = "Email")
    public WebElementFacade emailTextField;

    @FindBy(id = "Reference")
    public WebElementFacade requesterReferenceTextField;

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
        selectSpecificRadioButton("FOI Request");
    }

    public void clickCreateCaseButton() {safeClickOn(createCaseButton);}

    public void clickCreateCasesButton() {safeClickOn(createCasesButton);}

    public void selectCaseType(String caseType) {
        if (caseType.equalsIgnoreCase("ANY")) {
            caseType = getRandomCaseType();
        }
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
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("caseType").to(caseType);
    }

    public String getRandomCaseType() {
        List<String> caseTypes = Arrays.asList("MIN", "DTEN", "TRO", "MPAM", "MTS", "COMP");
        Random rand = new Random();
        return caseTypes.get(rand.nextInt(caseTypes.size()));
    }

    // Multi Step Methods

    public void createCSCaseOfType(String caseType) {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        selectCaseType(caseType);
        safeClickOn(nextButton);
        documents.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.storeCaseReference();
    }

    public void createFOICase() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        selectCaseType("FOI");
        clickTheButton("Next");
        documents.uploadDocumentOfType("docx");
        selectSpecificRadioButtonFromGroupWithHeading("Email", "How was the request received?");
        enterSpecificTextIntoTextAreaWithHeading("Test McTester", "Full Name");
        selectRandomOptionFromDropdownWithHeading("Country");
        enterSpecificTextIntoTextAreaWithHeading("test.email@test.com", "Email Address");
        enterSpecificTextIntoTextAreaWithHeading("TEST/REF/123", "Requester's Reference (Optional)");
        selectRandomOptionFromDropdownWithHeading("Case Topic");
        enterSpecificTextIntoTextAreaWithHeading("Test Request Question", "Request Question");
        storeCorrespondenceReceivedDate();
        storeCorrespondenceReceivedInKIMUDate();
        clickTheButton("Submit");
        createCaseSuccessPage.storeCaseReference();
    }

    public void createCaseOfTypeWithoutDocument(String caseType) {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
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
        typeIntoDateFields(correspondenceReceivedDayField, correspondenceReceivedMonthField, correspondenceReceivedYearField, date);
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

    public void storeCorrespondenceReceivedInKIMUDate() {
        String correspondenceDay = dateKIMUReceivedDayField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUDay").to(correspondenceDay);
        String correspondenceMonth = dateKIMUReceivedMonthField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUMonth").to(correspondenceMonth);
        String correspondenceYear = dateKIMUReceivedYearField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUYear").to(correspondenceYear);
        setSessionVariable("correspondenceReceivedByKIMUDate").to(correspondenceDay + "/" + correspondenceMonth + "/" +correspondenceYear);
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
