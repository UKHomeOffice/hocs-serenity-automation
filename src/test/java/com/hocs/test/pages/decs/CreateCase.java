package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.managementUI.MUI;

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

    ConfirmationScreens confirmationScreens;

    Dashboard dashboard;

    Workdays workdays;

    RecordCaseData recordCaseData;

    Correspondents correspondents;

    MUI mui;

    LoginPage loginPage;

    Search search;

    String stage1CaseType = "";

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

    @FindBy(xpath = "//label[text()='Border Force Case']")
    public WebElementFacade bfRadioButton;

    @FindBy(xpath = "//label[text()='Treat Official']")
    public WebElementFacade treatOfficialRadioButton;

    @FindBy(xpath = "//label[text()='HMPO/GRO Complaint Case']")
    public WebElementFacade pogrRadioButton;

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

    private void clickSmcRadioButton() {
        selectSpecificRadioButton("Serious Misconduct Case");
    }

    private void clickToRadioButton() {
        safeClickOn(treatOfficialRadioButton);
    }

    private void clickBfRadioButton() {
        selectSpecificRadioButton("Border Force Case");
    }

    private void clickPogrRadioButton() {
        safeClickOn(pogrRadioButton);
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
            case "SMC":
                clickSmcRadioButton();
                break;
            case "BF":
                clickBfRadioButton();
                break;
            case "TO":
                clickToRadioButton();
                break;
            case "POGR":
                clickPogrRadioButton();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("caseType").to(caseType);
    }

    public String getRandomCSCaseType() {
        List<String> list = Arrays.asList("MIN", "TRO", "DTEN", "MPAM", "MTS", "COMP", "IEDET", "SMC", "TO", "BF", "POGR");
        return list.get(new Random().nextInt(list.size()));
    }

    public String getRandomDCUCaseType() {
        List<String> list = Arrays.asList("MIN", "TRO", "DTEN");
        return list.get(new Random().nextInt(list.size()));
    }

    public String getRandomComplaintsCaseType() {
        List<String> list = Arrays.asList("COMP", "COMP2", "SMC", "IEDET", "BF", "POGR");
        return list.get(new Random().nextInt(list.size()));
    }

    public String getRandomMPAMOrMTSCaseType() {
        List<String> list = Arrays.asList("MPAM", "MTS");
        return list.get(new Random().nextInt(list.size()));
    }

    public void editReceivedDate(String date) {
        enterDateIntoDateFieldsWithHeading(date, "When was the correspondence received?");
    }

    // Multi Step Methods

    private void createCSCase(String caseType, boolean addDocument, String receivedDate) {
        if (caseType.equalsIgnoreCase("COMP2")) {
            this.stage1CaseType = "COMP";
            escalateAStage1CaseToStage2();
        }
        if (caseType.equals("BF2")) {
            this.stage1CaseType = "BF";
            escalateAStage1CaseToStage2();
        }
        if (!caseType.equals("COMP2") && !caseType.equalsIgnoreCase("BF2")) {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
            if (!nextButton.isVisible()) {
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
            }
            selectCaseType(caseType);
            safeClickOn(nextButton);
        }
        waitFor(correspondenceReceivedDayField);
        if (!receivedDate.equalsIgnoreCase("N/A")) {
            editReceivedDate(receivedDate);
        }
        if (addDocument) {
            documents.uploadFileOfType("docx");
        }
        storeCorrespondenceReceivedDate();
        if (caseType.equals("FOI")) {
            storeCorrespondenceReceivedInKIMUDate();
            selectCorrespondenceInboundChannel();
            enterCorrespondentDetails();
            selectFOITopic("Animal alternatives (3Rs)");
            enterRequestQuestion();
            clickTheButton("Submit");
        } else {
            clickCreateCaseButton();
        }
        confirmationScreens.storeCaseReference();
    }

    public void createCSCaseOfType(String caseType) {
        if (caseType.equals("CS")) {
            caseType = getRandomCSCaseType();
        }
        createCSCase(caseType, true, "N/A");
    }

    public void createCSCaseOfRandomType() {
        createCSCaseOfTypeWithoutDocument(getRandomCSCaseType());
    }

    public void createDCUCaseOfRandomType() {
        createCSCaseOfTypeWithoutDocument(getRandomDCUCaseType());
    }

    public void createComplaintsCaseOfRandomType() {
        createCSCaseOfType(getRandomComplaintsCaseType());
    }

    public void createMPAMOrMTSCaseOfRandomType() {
        createCSCaseOfTypeWithoutDocument(getRandomMPAMOrMTSCaseType());
    }

    public void createCSCaseOfTypeWithoutDocument(String caseType) {
        createCSCase(caseType, false, "N/A");
    }

    public void createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(String caseType, String date) {
        createCSCase(caseType, true, date);
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
        createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(caseType, date);
    }

    public void createCaseReceivedNWorkdaysAgo(String caseType, int days) {
        createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(caseType, workdays.getDateXWorkdaysAgoForGivenCaseType(days, caseType));
    }

    public void createWCSCase() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        clickTheButton("Create claim");
        setSessionVariable("caseType").to("WCS");
        setCaseReferenceFromAssignedCase();
        System.out.println("WCS Claim " + sessionVariableCalled("caseReference") + " created");
    }

    public void createAndWithDrawACSCaseOfType(String caseType) {
        createCSCaseOfType(caseType);
        mui.withdrawACaseInMUI(getCurrentCaseReference());
        loginPage.navigateToCS();
    }

    public void escalateAStage1CaseToStage2() {
        if (!checkIfRandomStage1CaseEligibleForEscalationCanBeFound()) {
            getStage1CaseEligibleForEscalation();
        }
        escalateEligibleStage1CaseToStage2();
        setSessionVariable("caseType").to(stage1CaseType + "2");
        waitABit(500);
    }

    private boolean checkIfRandomStage1CaseEligibleForEscalationCanBeFound() {
        dashboard.selectSearchLinkFromMenuBar();
        selectStage1CaseTypeSearchCriteriaIfVisible();
        search.enterComplaintsSearchCriteria("Complainant Home Office Reference", getCurrentMonth() + "/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        return search.checkVisibilityOfEscalationHypertext();
    }

    private void getStage1CaseEligibleForEscalation() {
        createAndWithDrawACSCaseOfType(stage1CaseType);
        dashboard.selectSearchLinkFromMenuBar();
        selectStage1CaseTypeSearchCriteriaIfVisible();
        search.searchByCaseReference(getCurrentCaseReference());
        search.waitForResultsPage();
        int retries = 0;
        while ((search.getNumberOfSearchResults() == 0) && (retries < 3)) {
            waitABit(5000);
            dashboard.selectSearchLinkFromMenuBar();
            selectStage1CaseTypeSearchCriteriaIfVisible();
            search.searchByCaseReference(getCurrentCaseReference());
            search.waitForResultsPage();
            retries++;
        }
    }

    private void escalateEligibleStage1CaseToStage2() {
        WebElementFacade stage1CaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
        String stage1CaseRef = stage1CaseRefField.getText();
        setSessionVariable("stage1CaseReference").to(stage1CaseRef);
        System.out.print("Case reference of case being escalated: " + stage1CaseRef + "\n");
        search.clickEscalateComplaintsCaseToStage2();
    }

    private void selectStage1CaseTypeSearchCriteriaIfVisible() {
        if(stage1CaseType.equalsIgnoreCase("COMP")) {
            checkSpecificCheckbox("Complaint Case");
        }
        else if (stage1CaseType.equalsIgnoreCase("BF") && checkboxWithLabelIsCurrentlyVisible("Border Force Case")) {
            checkSpecificCheckbox("Border Force Case");
        }
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
            case "BF_USER":
                if (bfRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "TO_USER":
                if (treatOfficialRadioButton.isVisible()) {
                    correctUser = true;
                }
                break;
            case "POGR_USER":
                if (pogrRadioButton.isVisible()) {
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
        setSessionVariable("correspondenceReceivedByKIMUDate").to(correspondenceDay + "/" + correspondenceMonth + "/" + correspondenceYear);
    }

    public void selectCorrespondenceInboundChannel() {
        String channel = selectRandomRadioButtonFromGroupWithHeading("How was the request received?");
        recordCaseData.addHeadingAndValueRecord("How was the correspondence received?", channel);
        setSessionVariable("foiInboundChannel").to(channel);
    }

    public void enterCorrespondentDetails() {
        String inboundChannel = sessionVariableCalled("foiInboundChannel");
        if (inboundChannel.equalsIgnoreCase("EMAIL")) {
            correspondents.enterCorrespondentFullName("Sam McTester");
            correspondents.enterCorrespondentOrganisation();
            correspondents.selectACorrespondentCountry();
            correspondents.enterCorrespondentEmailAddress("SamMcTester@Test.com");
            correspondents.enterCorrespondenceReference("Ref-ABCD-1234");
        } else if (inboundChannel.equalsIgnoreCase("POST")) {
            correspondents.enterCorrespondentFullName("Sam McTester");
            correspondents.enterCorrespondentOrganisation();
            correspondents.enterCorrespondentBuilding("1 Test House");
            correspondents.enterCorrespondentStreet("Test Road");
            correspondents.enterCorrespondentTownOrCity("Test Town");
            correspondents.enterCorrespondentPostcode("AB1 2CD");
            correspondents.selectACorrespondentCountry();
            correspondents.enterCorrespondentEmailAddress("SamMcTester@Test.com");
            correspondents.enterCorrespondenceReference("Ref-ABCD-1234");
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
