package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.complaints.Registration;
import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.mpam.MTSDataInput;

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

    Registration registration;

    CaseView caseView;

    COMPProgressCase compProgressCase;

    DCUProgressCase dcuProgressCase;

    MPAMProgressCase mpamProgressCase;

    Creation creation;

    com.hocs.test.pages.to.DataInput toDataInput;

    com.hocs.test.pages.dcu.DataInput dcuDataInput;

    MTSDataInput mtsDataInput;

    com.hocs.test.pages.to.Triage toTriage;

    com.hocs.test.pages.mpam.Campaign mpamCampaign;

    com.hocs.test.pages.to.Campaign toCampaign;

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
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        setSessionVariable("caseType").to(caseType);
    }

    // Add BF back to this list once case type has document types.
    public String getRandomCaseType() {
        List<String> list = Arrays.asList("MIN", "TRO", "DTEN", "MPAM", "MTS", "COMP", "IEDET", "SMC", "FOI", "TO");
        return list.get(new Random().nextInt(list.size()));
    }

    public String getRandomDCUCaseType() {
        List<String> list = Arrays.asList("MIN", "TRO", "DTEN");
        return list.get(new Random().nextInt(list.size()));
    }

    public String getRandomComplaintsCaseType() {
        List<String> list = Arrays.asList("COMP", "COMP2", "SMC", "IEDET");
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
        if (!caseType.equals("COMP2")) {
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
        createCSCase(caseType, true, "N/A");
    }

    public void createCSCaseOfRandomType() {
        createCSCaseOfTypeWithoutDocument(getRandomCaseType());
    }

    public void createDCUCaseOfRandomType() {
        createCSCaseOfTypeWithoutDocument(getRandomDCUCaseType());
    }

    public void createComplaintsCaseOfRandomType() {
        createCSCaseOfTypeWithoutDocument(getRandomComplaintsCaseType());
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

    public void generateSearchCaseData(String caseType, String infoValue, String infoType) throws ParseException {
        if (infoType.equalsIgnoreCase("CASE REFERENCE") || infoType.equalsIgnoreCase("CASE TYPE") || infoType.equalsIgnoreCase("ACTIVE CASES ONLY")) {
            if (infoValue.equals("COMP2")) {
                compProgressCase.escalateACOMPCaseToCOMP2();
            } else {
                createCSCaseOfType(infoValue);
            }
        }
        else {
            switch (caseType.toUpperCase()) {
                case "DCU":
                    switch (infoType.toUpperCase()) {
                        case "RECEIVED ON OR AFTER DATE":
                            createCaseReceivedFiveDaysBeforeOrAfterDate("MIN", "After", infoValue);
                            break;
                        case "RECEIVED ON OR BEFORE DATE":
                            createCaseReceivedFiveDaysBeforeOrAfterDate("MIN", "Before", infoValue);
                            break;
                        case "MEMBER OF PARLIAMENT NAME":
                            createCSCaseOfType("MIN");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            dcuDataInput.fillAllMandatoryCorrespondenceFields();
                            clickContinueButton();
                            correspondents.addASpecificMemberCorrespondent(infoValue);
                            safeClickOn(finishButton);
                            break;
                        case "PUBLIC CORRESPONDENT NAME":
                        case "CORRESPONDENT POSTCODE":
                        case "CORRESPONDENT EMAIL ADDRESS":
                            createCSCaseOfType("MIN");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            dcuDataInput.fillAllMandatoryCorrespondenceFields();
                            clickContinueButton();
                            correspondents.addANonMemberCorrespondentOfType("Constituent");
                            correspondents.confirmPrimaryCorrespondent();
                            dashboard.waitForDashboard();
                            break;
                        case "TOPIC":
                            generateSearchCaseData("DCU", "Gordon Freeman", "Public Correspondent Name");
                            dashboard.getAndClaimCurrentCase();
                            dcuProgressCase.moveCaseFromMarkupToInitialDraftWithSpecificTopic(infoValue);
                            break;
                        case "SIGN OFF TEAM":
                            generateSearchCaseData("DCU", "Animal alternatives (3Rs)", "Topic");
                            break;
                        case "HOME SECRETARY INTEREST":
                            createCSCaseOfType("MIN");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            dcuDataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                            dcuDataInput.selectACorrespondenceReceivedChannel();
                            dcuDataInput.selectASpecificCopyToNoTenOption("No");
                            dcuDataInput.selectASpecificHomeSecInterestOption(infoValue);
                            dcuDataInput.selectAHomeSecReplyOption();
                            safeClickOn(continueButton);
                            correspondents.addANonMemberCorrespondentOfType("Constituent");
                            correspondents.confirmPrimaryCorrespondent();
                            break;
                        default:
                            pendingStep(infoType + " is not defined within " + getMethodName());
                    }
                    break;
                case "MPAM":
                    switch (infoType.toUpperCase()) {
                        case "REFERENCE TYPE":
                            mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedReferenceType(infoValue, "Triage");
                            break;
                        case "MINISTERIAL SIGN OFF TEAM":
                            mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedSignOffTeam(infoValue, "Triage");
                            break;
                        case "MEMBER OF PARLIAMENT NAME":
                            createCSCaseOfType("MPAM");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            creation.moveCaseWithSpecifiedMPCorrespondentToTriageStage(infoValue);
                            break;
                        case "CORRESPONDENT REFERENCE NUMBER":
                            createCSCaseOfType("MPAM");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            creation.addCorrespondentWithSpecificReferenceToCase(infoValue);
                            break;
                        case "RECEIVED ON OR BEFORE DATE":
                            createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "Before", infoValue);
                            break;
                        case "RECEIVED ON OR AFTER DATE":
                            createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "After", infoValue);
                            break;
                        case "CAMPAIGN":
                            createCSCaseOfType("MPAM");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            mpamProgressCase.moveCaseFromCreationToTriage();
                            dashboard.getAndClaimCurrentCase();
                            mpamCampaign.moveCaseFromAStageToCampaign(infoValue);
                            break;
                        case "PUBLIC CORRESPONDENT NAME":
                            createCSCaseOfType("MPAM");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            creation.triggerMPCorrespondentIsMandatoryScreen();
                            dashboard.goToDashboard();
                            break;
                        case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                            createCSCaseOfType("MTS");
                            dashboard.goToDashboard();
                            dashboard.getAndClaimCurrentCase();
                            mtsDataInput.completeDataInputStageAndCloseMTSCase();
                            break;
                        default:
                            pendingStep(infoType + " is not defined within " + getMethodName());
                    }
                    break;
                case "COMP":
                    switch (infoType.toUpperCase()) {
                        case "CORRESPONDENT FULL NAME":
                        case "CORRESPONDENT POSTCODE":
                        case "CORRESPONDENT EMAIL ADDRESS":
                            createCSCaseOfType("COMP");
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            caseView.clickAllocateToMeLink();
                            correspondents.addANonMemberCorrespondentOfType("Complainant");
                            correspondents.confirmPrimaryCorrespondent();
                            break;
                        case "COMPLAINANT DATE OF BIRTH":
                            createCSCaseOfType("COMP");
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            caseView.clickAllocateToMeLink();
                            correspondents.addANonMemberCorrespondentOfType("Complainant");
                            correspondents.confirmPrimaryCorrespondent();
                            registration.enterComplainantDOB(infoValue);
                            registration.selectAGender();
                            registration.enterACompanyName();
                            registration.enterAHomeOfficeReference("Test entry for Home Office Reference");
                            registration.enterAPortReference();
                            safeClickOn(continueButton);
                            registration.selectComplaintType("Service");
                            registration.selectAChannel();
                            registration.selectASeverity();
                            safeClickOn(continueButton);
                            registration.openTheServiceComplaintCategoryAccordion();
                            registration.selectAVisibleClaimCategory();
                            registration.selectAnOwningCSU();
                            safeClickOn(finishButton);
                            break;
                        case "COMPLAINANT HOME OFFICE REFERENCE":
                            createCSCaseOfType("COMP");
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            caseView.clickAllocateToMeLink();
                            correspondents.addANonMemberCorrespondentOfType("Complainant");
                            correspondents.confirmPrimaryCorrespondent();
                            registration.enterComplainantDOB("01/01/2001");
                            registration.selectAGender();
                            registration.enterACompanyName();
                            registration.enterAHomeOfficeReference(infoValue);
                            registration.enterAPortReference();
                            safeClickOn(continueButton);
                            break;
                        default:
                            pendingStep(infoType + " is not defined within " + getMethodName());
                    }
                    break;
                case "BF":
                    switch (infoType.toUpperCase()) {
                        case "CORRESPONDENT FULL NAME":
                        case "CORRESPONDENT POSTCODE":
                        case "CORRESPONDENT EMAIL ADDRESS":
                            createCSCaseOfType("BF");
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            caseView.clickAllocateToMeLink();
                            correspondents.addANonMemberCorrespondentOfType("Complainant");
                            correspondents.confirmPrimaryCorrespondent();
                            break;
                        case "COMPLAINANT DATE OF BIRTH":
                            createCSCaseOfType("BF");
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            caseView.clickAllocateToMeLink();
                            correspondents.addANonMemberCorrespondentOfType("Complainant");
                            correspondents.confirmPrimaryCorrespondent();
                            registration.enterComplainantDOB(infoValue);
                            registration.selectAGender();
                            registration.enterACompanyName();
                            registration.enterAHomeOfficeReference("Test entry for Home Office Reference");
                            registration.enterAPortReference();
                            safeClickOn(continueButton);
                            break;
                        case "COMPLAINANT HOME OFFICE REFERENCE":
                            createCSCaseOfType("BF");
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            caseView.clickAllocateToMeLink();
                            correspondents.addANonMemberCorrespondentOfType("Complainant");
                            correspondents.confirmPrimaryCorrespondent();
                            registration.enterComplainantDOB("01/01/2001");
                            registration.selectAGender();
                            registration.enterACompanyName();
                            registration.enterAHomeOfficeReference(infoValue);
                            registration.enterAPortReference();
                            safeClickOn(continueButton);
                            break;
                        default:
                            pendingStep(infoType + " is not defined within " + getMethodName());
                    }
                    break;
                case "FOI":
                    switch (infoType.toUpperCase()) {
                        case "CORRESPONDENT (NON-MP)":
                        case "TOPIC":
                            createCSCaseOfType(caseType);
                            dashboard.goToDashboard();
                            break;
                        case "RECEIVED ON OR AFTER":
                            dashboard.selectCreateSingleCaseLinkFromMenuBar();
                            if (!nextButton.isVisible()) {
                                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                            }
                            selectCaseType("FOI");
                            clickTheButton("Next");
                            editReceivedDate(getTodaysDate());
                            storeCorrespondenceReceivedDate();
                            storeCorrespondenceReceivedInKIMUDate();
                            documents.uploadFileOfType("docx");
                            selectCorrespondenceInboundChannel();
                            enterCorrespondentDetails();
                            selectFOITopic("Animal alternatives (3Rs)");
                            enterRequestQuestion();
                            clickTheButton("Submit");
                            dashboard.goToDashboard();
                            break;
                        case "RECEIVED ON OR BEFORE":
                            dashboard.selectCreateSingleCaseLinkFromMenuBar();
                            if (!nextButton.isVisible()) {
                                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                            }
                            selectCaseType("FOI");
                            clickTheButton("Next");
                            editReceivedDate("01/01/2010");
                            storeCorrespondenceReceivedDate();
                            storeCorrespondenceReceivedInKIMUDate();
                            documents.uploadFileOfType("docx");
                            selectCorrespondenceInboundChannel();
                            enterCorrespondentDetails();
                            selectFOITopic("Animal alternatives (3Rs)");
                            enterRequestQuestion();
                            clickTheButton("Submit");
                            dashboard.goToDashboard();
                            break;
                        default:
                            pendingStep(infoType + " is not defined within " + getMethodName());
                    }
                    break;
                case "TO":
                    switch (infoType) {
                        case "RECEIVED ON OR AFTER":
                            createCaseReceivedFiveDaysBeforeOrAfterDate(caseType, "After", infoValue);
                            break;
                        case "RECEIVED ON OR BEFORE":
                            createCaseReceivedFiveDaysBeforeOrAfterDate(caseType, "Before", infoValue);
                            break;
                        case "CORRESPONDENT FULL NAME":
                        case "CORRESPONDENT POSTCODE":
                        case "CORRESPONDENT EMAIL ADDRESS":
                        case "CORRESPONDENT REFERENCE NUMBER":
                            createCSCaseOfType(caseType.toUpperCase());
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            dashboard.claimCurrentCase();
                            toDataInput.selectABusinessArea();
                            toDataInput.selectAChannelRecieved();
                            clickTheButton("Continue");
                            correspondents.addANonMemberCorrespondentOfType("Correspondent");
                            correspondents.confirmPrimaryCorrespondent();
                            break;
                        case "CAMPAIGN":
                            createCSCaseOfType(caseType.toUpperCase());
                            confirmationScreens.goToCaseFromConfirmationScreen();
                            dashboard.claimCurrentCase();
                            toDataInput.selectABusinessArea();
                            toDataInput.selectAChannelRecieved();
                            clickTheButton("Continue");
                            correspondents.addANonMemberCorrespondentOfType("Correspondent");
                            correspondents.confirmPrimaryCorrespondent();
                            toDataInput.selectWhetherToAddRecipient("No");
                            clickTheButton("Continue");
                            toTriage.selectSetEnquirySubjectAndReasonLink();
                            toTriage.selectAnEnquirySubject();
                            clickTheButton("Continue");
                            toTriage.selectAnEnquiryReason();
                            clickTheButton("Continue");
                            toTriage.selectABusinessUnitType();
                            toTriage.selectABusinessUnit();
                            selectTheStageAction("Put case into a campaign");
                            clickTheButton("Finish");
                            toCampaign.selectASpecificCampaign(infoValue);
                            clickTheButton("Confirm");
                            break;
                        default:
                            pendingStep(infoType + " is not defined within " + getMethodName());
                    }
                    break;
                default:
                    pendingStep(caseType + " is not defined within " + getMethodName());
            }
        }
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
