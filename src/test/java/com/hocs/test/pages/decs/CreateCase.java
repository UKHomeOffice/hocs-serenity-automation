package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
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
import org.openqa.selenium.Keys;

public class CreateCase extends BasePage {

    Documents documents;

    CreateCaseSuccessPage createCaseSuccessPage;

    Dashboard dashboard;

    Workdays workdays;

    RecordCaseData recordCaseData;

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

    @FindBy(xpath = "//label[contains(text(), 'Topic')]//following-sibling::div//input")
    public WebElementFacade caseTopicTypeahead;

    @FindBy(id = "KimuDateReceived-day")
    public WebElementFacade dateKIMUReceivedDayField;

    @FindBy(id = "KimuDateReceived-month")
    public WebElementFacade dateKIMUReceivedMonthField;

    @FindBy(id = "KimuDateReceived-year")
    public WebElementFacade dateKIMUReceivedYearField;

    @FindBy(id = "fullname")
    public WebElementFacade fullNameTextField;

    @FindBy(id = "RequestQuestion")
    public WebElementFacade requestQuestionTextArea;

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

    private void clickIedetRadioButton() {
        selectSpecificRadioButton("IE Detention Case");
    }

    public void clickCreateCaseButton() {
        safeClickOn(createCaseButton);
    }

    public void clickCreateCasesButton() {
        safeClickOn(createCasesButton);
    }

    public void selectCaseType(String caseType) {
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
            case "IEDET":
                clickIedetRadioButton();
                break;
            case "FOI":
                clickFoiRadioButton();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("caseType").to(caseType);
    }

    public void selectRandomCaseType() {
        List<String> list = Arrays.asList("MIN", "TRO", "DTEN", "MPAM", "MTS", "COMP", "FOI");
        selectCaseType(list.get(new Random().nextInt(list.size())));
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
        documents.uploadDocumentOfType("docx");
        storeCorrespondenceReceivedDate();
        clickCreateCaseButton();
        createCaseSuccessPage.storeCaseReference();
    }

    public void createCSCaseOfRandomType() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        selectRandomCaseType();
        safeClickOn(nextButton);
        if (sessionVariableCalled("caseType").equals("FOI")){
            selectCorrespondenceInboundChannel();
            enterCorrespondentDetails();
            selectFOITopic("Animal alternatives (3Rs)");
            enterRequestQuestion();
            clickTheButton("Submit");
        } else {
            clickCreateCaseButton();
        }
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
        setSessionVariable("correspondenceReceivedDate").to(correspondenceDay + "/" + correspondenceMonth + "/" + correspondenceYear);
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
            case "MPAM_USER":
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
                if (createClaimButton.isVisible()) {
                    correctUser = true;
                }
                break;
            default:
                pendingStep(targetUser + " is not defined within " + getMethodName());
        }
        return correctUser;
    }

    //FOI

    public void storeCorrespondenceReceivedInKIMUDate() {
        String correspondenceDay = dateKIMUReceivedDayField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUDay").to(correspondenceDay);
        String correspondenceMonth = dateKIMUReceivedMonthField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUMonth").to(correspondenceMonth);
        String correspondenceYear = dateKIMUReceivedYearField.getValue();
        setSessionVariable("correspondenceReceivedByKIMUYear").to(correspondenceYear);
        setSessionVariable("correspondenceReceivedByKIMUDate").to(correspondenceDay + "/" + correspondenceMonth + "/" +correspondenceYear);
    }

    public void selectCorrespondenceInboundChannel() {
        String channel = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("How was the request received?");
        setSessionVariable("foiInboundChannel").to(channel);
    }

    public void enterCorrespondentDetails() {
        String inboundChannel = sessionVariableCalled("foiInboundChannel");
        if (inboundChannel.equalsIgnoreCase("EMAIL")) {
            enterSpecificTextIntoTextFieldWithHeading("Test McTester", "Full Name");
            enterTextIntoTextFieldWithHeading("Organisation (Optional)");
            setSessionVariable("requesterFullName").to("Test McTester");
            selectRandomOptionFromDropdownWithHeading("Country");
            enterSpecificTextIntoTextFieldWithHeading("Test.Email@Test.com", "Email Address");
            enterSpecificTextIntoTextFieldWithHeading("TST/REF/123", "Requester's Reference (Optional)");
        } else if (inboundChannel.equalsIgnoreCase("POST")) {
            enterSpecificTextIntoTextFieldWithHeading("Test McTester", "Full Name");
            enterTextIntoTextFieldWithHeading("Organisation (Optional)");
            setSessionVariable("requesterFullName").to("Test McTester");
            enterSpecificTextIntoTextFieldWithHeading("Test Building", "Building");
            enterSpecificTextIntoTextFieldWithHeading("Test Street", "Street");
            enterSpecificTextIntoTextFieldWithHeading("Test Town", "Town or City");
            enterSpecificTextIntoTextFieldWithHeading("TST PSTCD", "Postcode");
            selectRandomOptionFromDropdownWithHeading("Country");
            enterSpecificTextIntoTextFieldWithHeading("Test.Email@Test.com", "Email Address (Optional)");
            enterSpecificTextIntoTextFieldWithHeading("TST/REF/123", "Requester's Reference (Optional)");
        }
    }

    public void selectFOITopic(String topic) {
        caseTopicTypeahead.sendKeys(topic);
        caseTopicTypeahead.sendKeys(Keys.RETURN);
        setSessionVariable("foiTopic").to(topic);
    }

    public void enterRequestQuestion() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Request Question", "Request Question");
        setSessionVariable("requestQuestion").to("Test Request Question");
    }

    public void createFOICase() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        if (!nextButton.isVisible()) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
        }
        selectCaseType("FOI");
        clickTheButton("Next");
        storeCorrespondenceReceivedDate();
        storeCorrespondenceReceivedInKIMUDate();
        documents.uploadDocumentOfType("docx");
        selectCorrespondenceInboundChannel();
        enterCorrespondentDetails();
        selectFOITopic("Animal alternatives (3Rs)");
        enterRequestQuestion();
        clickTheButton("Submit");
        createCaseSuccessPage.storeCaseReference();
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
