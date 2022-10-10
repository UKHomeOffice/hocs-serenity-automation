package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.complaints.ComplaintsRegistrationAndDataInput;
import com.hocs.test.pages.managementUI.MUI;

import config.CaseType;
import config.User;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class CreateCase extends BasePage {

    Documents documents;

    ConfirmationScreens confirmationScreens;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    Dashboard dashboard;

    Workdays workdays;

    RecordCaseData recordCaseData;

    Correspondents correspondents;

    MUI mui;

    LoginPage loginPage;

    Search search;

    CaseType stage1CaseType;

    // Elements

    @FindBy(xpath = "//label[contains(text(), 'Topic')]//following-sibling::div//input")
    public WebElementFacade caseTopicTypeahead;

    Boolean specificStage1CaseProvided = false;

    String stage1CaseReference = "";

    // Basic Methods

    public void clickCreateCaseButton() {
        clickTheButton("Create case");
    }

    public void clickCreateCasesButton() {
        clickTheButton("Create cases");
    }

    public void selectCaseType(CaseType caseType) {
        selectSpecificRadioButton(caseType.getCorrespondenceTypeLabel());
    }

    public CaseType getRandomCSCaseType() {
        List<CaseType> csCaseTypes = Arrays
                .asList(CaseType.MIN, CaseType.TRO, CaseType.DTEN, CaseType.MPAM, CaseType.MTS, CaseType.COMP, CaseType.IEDET,
                        CaseType.TO, CaseType.BF, CaseType.POGR);
        return csCaseTypes.get(new Random().nextInt(csCaseTypes.size()));
    }

    public CaseType getRandomCSCaseTypeThatAllowsMemberCorrespondents() {
        List<CaseType> caseTypesWithMemberCorrespondents = Arrays.asList(CaseType.MIN, CaseType.TRO, CaseType.DTEN, CaseType.MPAM, CaseType.MTS);
        return caseTypesWithMemberCorrespondents.get(new Random().nextInt(caseTypesWithMemberCorrespondents.size()));
    }

    public CaseType getStage1CaseTypeFromStage2CaseType(CaseType stage2CaseType) {
        return CaseType.valueOf(stage2CaseType.toString().substring(0, stage2CaseType.toString().indexOf("2")));
    }

    public CaseType getStage2CaseTypeFromStage1CaseType(CaseType stage1CaseType) {
        return CaseType.valueOf(stage1CaseType.toString() + "2");
    }

    public void editReceivedDate(String date) {
        enterDateIntoDateFieldsWithHeading(date, "When was the correspondence received?");
    }

    // Multi Step Methods

    public void createCSCase(CaseType caseType, boolean addDocument, String receivedDate) {
        setCurrentCaseType(caseType);
        if (caseType.isStage2()) {
            this.stage1CaseType = getStage1CaseTypeFromStage2CaseType(caseType);
            escalateAStage1CaseToStage2();
        } else {
            dashboard.selectCreateSingleCaseLinkFromMenuBar();
            if (!buttonIsVisible("Next")) {
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
            }
            selectCaseType(caseType);
            clickNextButton();
        }
        waitForButton("Create case");
        if (!receivedDate.equalsIgnoreCase("N/A")) {
            editReceivedDate(receivedDate);
        }
        if (addDocument) {
            documents.uploadFileOfType("docx");
        }
        storeCorrespondenceReceivedDate();
        if (caseType.equals(CaseType.FOI)) {
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

    public void createCSCaseOfTypeWithDocument(CaseType caseType) {
        createCSCase(caseType, true, "N/A");
    }

    public void createCSCaseOfTypeWithoutDocument(CaseType caseType) {
        createCSCase(caseType, false, "N/A");
    }

    public void createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(CaseType caseType, String date) {
        createCSCase(caseType, true, date);
    }

    public void createCaseReceivedNWorkdaysAgo(CaseType caseType, int days) {
        createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(caseType, workdays.getDateXWorkdaysAgoForGivenCaseType(days, caseType));
    }

    public void createWCSCase() {
        setCurrentCaseType(CaseType.WCS);
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        clickTheButton("Create claim");
        setCaseReferenceFromAssignedCase();
        System.out.println("WCS Claim " + sessionVariableCalled("caseReference") + " created");
    }

    public void createAndWithDrawACSCaseOfType(CaseType caseType) {
        createCSCaseOfTypeWithDocument(caseType);
        if (stage1CaseType.equals(CaseType.POGR)) {
            dashboard.goToDashboard();
            dashboard.getAndClaimCurrentCase();
            complaintsRegistrationAndDataInput.setPOGRCaseBusinessArea();
        }
        mui.withdrawACaseInMUI(getCurrentCaseReference());
        loginPage.navigateToCS();
    }

    public void createAStage2CaseFromASpecificClosedStage1Case(String stage1CaseReference) {
        specificStage1CaseProvided = true;
        this.stage1CaseReference = stage1CaseReference;
        createCSCaseOfTypeWithDocument(getStage2CaseTypeFromStage1CaseType(getCaseTypeFromCaseReference(stage1CaseReference)));
    }

    public void escalateAStage1CaseToStage2() {
        if (specificStage1CaseProvided) {
            searchForClosedStage1Case(stage1CaseReference);
        } else {
            if (!stage1CaseType.equals(CaseType.POGR)) {
                if (!checkIfRandomStage1CaseEligibleForEscalationCanBeFound()) {
                    getStage1CaseEligibleForEscalation();
                }
            } else {
                getStage1CaseEligibleForEscalation();
            }
        }
        escalateEligibleStage1CaseToStage2();
        setSessionVariable("caseType").to(stage1CaseType + "2");
        waitABit(500);
    }

    private boolean checkIfRandomStage1CaseEligibleForEscalationCanBeFound() {
        dashboard.selectSearchLinkFromMenuBar();
        selectStage1CaseTypeSearchCriteriaIfVisible();
        search.enterSearchCriteria("Complainant Home Office Reference", getCurrentMonth() + "/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        return search.checkVisibilityOfEscalationHypertext();
    }

    private void getStage1CaseEligibleForEscalation() {
        createAndWithDrawACSCaseOfType(stage1CaseType);
        searchForClosedStage1Case(getCurrentCaseReference());
    }

    public void searchForClosedStage1Case(String caseReference) {
        dashboard.selectSearchLinkFromMenuBar();
        selectStage1CaseTypeSearchCriteriaIfVisible();
        search.searchByCaseReference(caseReference);
        search.waitForResultsPage();
        int retries = 0;
        while ((search.getNumberOfSearchResults() == 0) && (retries <= 6)) {
            waitABit(5000);
            dashboard.selectSearchLinkFromMenuBar();
            selectStage1CaseTypeSearchCriteriaIfVisible();
            search.searchByCaseReference(caseReference);
            search.waitForResultsPage();
            retries++;
        }
        if (search.getNumberOfSearchResults() == 0) {
            Assert.fail("Search by stage 1 case reference is still returning 0 results after " + String.valueOf(retries * 5) + " seconds");
        }
    }

    public void escalateEligibleStage1CaseToStage2() {
        if (stage1CaseReference.isEmpty()) {
            WebElementFacade stage1CaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
            stage1CaseReference = stage1CaseRefField.getText();
        }
        setSessionVariable("stage1CaseReference").to(stage1CaseReference);
        System.out.print("Case reference of case being escalated: " + stage1CaseReference + "\n");
        search.clickEscalateComplaintsCaseToStage2();
    }

    private void selectStage1CaseTypeSearchCriteriaIfVisible() {
        if (checkboxWithLabelIsCurrentlyVisible(stage1CaseType.getCorrespondenceTypeLabel())) {
            checkSpecificCheckbox(stage1CaseType.getCorrespondenceTypeLabel());
        }
    }

    public void clearCorrespondentReceivedDateFields() {
        clearDateFieldsWithHeading("When was the correspondence received?");
    }

    public void storeCorrespondenceReceivedDate() {
        setSessionVariable("correspondenceReceivedDate").to(getDisplayedDateInDateFieldsWithHeading("When was the correspondence received?"));
    }

    public boolean checkTargetUserIsLoggedInUsingCreateCasePage(User targetUser) {
        boolean correctUser = false;
        if (targetUser.equals(User.WCS_USER)) {
            waitForDECSPageWithTitle("Record a new claim");
            correctUser = buttonIsCurrentlyVisible("Create claim");
        } else {
            waitForDECSPageWithTitle("Create Single Case");
            switch (targetUser.toString()) {
                case "DECS_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.MIN) && caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.MTS)) {
                        correctUser = true;
                    }
                    break;
                case "DCU_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.MIN) && !caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.MTS)) {
                        correctUser = true;
                    }
                    break;
                case "MPAM_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.MTS) && !caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.MIN)) {
                        correctUser = true;
                    }
                    break;
                case "COMP_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.COMP)) {
                        correctUser = true;
                    }
                case "IEDET_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.IEDET)) {
                        correctUser = true;
                    }
                    break;
                case "SMC_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.SMC)) {
                        correctUser = true;
                    }
                    break;
                case "FOI_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.FOI)) {
                        correctUser = true;
                    }
                    break;
                case "BF_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.BF)) {
                        correctUser = true;
                    }
                    break;
                case "TO_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.TO)) {
                        correctUser = true;
                    }
                    break;
                case "POGR_USER":
                    if (caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType.POGR)) {
                        correctUser = true;
                    }
                    break;
                default:
                    pendingStep(targetUser + " is not defined within " + getMethodName());
            }
        }
        return correctUser;
    }

    public Boolean caseTypeIsVisibleOptionOnCreateCaseScreen(CaseType caseType) {
        return radioButtonIsCurrentlyVisible(caseType.getCorrespondenceTypeLabel());
    }

    //FOI

    public void storeCorrespondenceReceivedInKIMUDate() {
        setSessionVariable("correspondenceReceivedByKIMUDate").to(getDisplayedDateInDateFieldsWithHeading("Date correspondence received in KIMU"));
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

    public void assertCaseTypeErrorMessage() {
        assertExpectedErrorMessageIsDisplayed("Case type is required");
    }

    public void assertDateReceivedNotEnteredErrorMessage() {
        assertExpectedErrorMessageIsDisplayed("Date received is required");
    }

    public void assertDateReceivedIsInvalidErrorMessage() {
        assertExpectedErrorMessageIsDisplayed("Date received must be a valid date");
    }
}
