package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.complaints.Registration;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.mpam.Campaign;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.mpam.MTSDataInput;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;

public class CreateCaseStepDefs extends BasePage {

    Documents documents;

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

    DCUProgressCase dcuProgressCase;

    MPAMProgressCase mpamProgressCase;

    Campaign campaign;

    DataInput dataInput;

    MTSDataInput mtsDataInput;

    Dashboard dashboard;

    Markup markup;

    Workstacks workstacks;

    CaseView caseView;

    Creation creation;

    SummaryTab summaryTab;

    Registration registration;

    Correspondents correspondents;

    com.hocs.test.pages.wcs.Registration wcsRegistration;

    @When("I create a single {string} case/claim")
    public void createNewCase(String caseType) {
        if (caseType.equalsIgnoreCase("WCS")) {
            createCase.createWCSCase();
            waitFor(wcsRegistration.registrationSchemeCheckTitle);
        } else {
            createCase.createCSCaseOfType(caseType.toUpperCase());
        }
    }

    @And("I get a new {string} case")
    public void iGetANewCase(String caseType) {
        createNewCase(caseType);
        confirmationScreens.goToCaseFromConfirmationScreen();
        dashboard.claimCurrentCase();
    }

    @Given("I create a single {string} case and return to the dashboard")
    public void createCaseAndReturnToDashboard(String caseType) {
        createNewCase(caseType);
        dashboard.goToDashboard();
    }

    @When("I bulk create {int} {string} cases")
    public void bulkCreateCases(int cases, String caseType) {
        setSessionVariable("bulkCaseNumber").to(cases);
        dashboard.selectCreateBulkCasesLinkFromMenuBar();
        createCase.selectCaseType(caseType);
        safeClickOn(createCase.nextButton);
        documents.bulkUploadDocuments(cases);
        createCase.clickCreateCasesButton();
    }

    @Given("I am presented with {string}")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                createCase.assertNoOptionsAvailable();
                break;
            default:
                pendingStep(userView + " is not defined within " + getMethodName());
        }
    }

    @When("I do not select a type of correspondence when creating a case")
    public void doNotSelectCorrespondenceWhenCreatingCase() {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
        clickTheButton("Next");
    }

    @When("I create a {string} case with {string} as the primary topic")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
        createCase.createCSCaseOfType(caseType);
        safeClickOn(dashboard.csDashboardLink);
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromDataInputToMarkup();
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        waitABit(1000);
        markup.addTopicToCase(topic);
        markup.confirmPrimaryTopic();
        setSessionVariable("searchTopic").to(topic);
    }

    @When("I allocate the case to myself via the successful case creation screen")
    public void allocateToMe() {
        confirmationScreens.goToCaseFromConfirmationScreen();
        caseView.clickAllocateToMeLink();
    }

    @When("I go to the case from the successful case creation screen")
    public void goToSuccessfullyCreatedCase() {
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    @Then("the case should be visible in the Performance and Process Team workstack")
    public void assertThatNewMinCaseIsInPerformanceAndProcessTeam() {
        safeClickOn(dashboard.csDashboardLink);
        safeClickOn(dashboard.performanceProcessTeam);
        workstacks.assertVisibilityOfCaseReference(true);
    }

    @When("I navigate to the {string} and select the check box against the newly created"
            + " case and allocate it to myself")
    public void allocateCaseUsingCheckbox(String workstack) {
        safeClickOn(createCase.$("//input[@id='submit']"));
        String newCaseReference = workstacks.$("//h1").getText();
        setSessionVariable("caseReference").to(newCaseReference);
        switch (workstack.toUpperCase()) {
            case "PERFORMANCE AND PROCESS TEAM":
                safeClickOn(dashboard.csDashboardLink);
                safeClickOn(dashboard.performanceProcessTeam);
                workstacks.clickCheckboxRelevantToCaseReference();
                safeClickOn(workstacks.allocateSelectedToMeButton);
                safeClickOn(workstacks.csDashboardLink);
                break;
            case "TRANSFERS AND N10 TEAM":
                safeClickOn(dashboard.csDashboardLink);
                safeClickOn(dashboard.transferN10Team);
                workstacks.clickCheckboxRelevantToCaseReference();
                safeClickOn(workstacks.allocateSelectedToMeButton);
                safeClickOn(workstacks.csDashboardLink);
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
    }

    @Then("a case is created successfully")
    public void aCaseIsCreatedSuccessfully() {
        confirmationScreens.assertCaseCreatedConfirmationDisplayed();
        confirmationScreens.storeCaseReference();
    }

    @Then("A case is created successfully {string} a document")
    public void aCaseIsCreatedSuccessfullyWithWithoutADocument(String withWithout) {
        confirmationScreens.assertCaseCreatedConfirmationDisplayed();
        confirmationScreens.storeCaseReference();
        confirmationScreens.goToCaseFromConfirmationScreen();
        if (withWithout.equals("with")) {
            documents.assertFileIsVisible(sessionVariableCalled("docType"));
        }
        else {
            documents.assertFileIsNotVisible(sessionVariableCalled("docType"));
        }
    }

    @Then("bulk cases are created successfully")
    public void BulkCasesAreCreatedSuccessfully() {
        confirmationScreens.assertBulkCasesCreatedConfirmationDisplayed();
    }

    @Then("an error message should be displayed as I have not selected the case type")
    public void assertThatCaseTypeErrorMessageIsDisplayed() {
        createCase.assertCaseTypeErrorMessage();
    }

    @When("they create the bulk cases without adding a document")
    public void userCreatesBulkCasesWithoutAddingADocument() {
        safeClickOn(createCase.dcuMinRadioButton);
        safeClickOn(createCase.nextButton);
        createCase.clickCreateCaseButton();
    }

    @Then("an error message should be displayed as I have not entered the correspondence received date")
    public void assertThatDateReceivedErrorMessageIsShown() {
        createCase.assertDateReceivedNotEnteredErrorMessage();

    }

    @Then("an error message should be displayed as I have entered an invalid date")
    public void assertThatDateReceivedIsInvalidErrorMessageIsShown() {
        createCase.assertDateReceivedIsInvalidErrorMessage();
    }

    @When("I select a case type and continue")
    public void getToWhenWasCorrespondenceReceivedPage() {
        createCase.selectCaseType("CS");
        safeClickOn(nextButton);
        waitABit(100);
    }
    @When("I select the FOI case type and continue")
    public void iSelectFOICaseTypeAndContinue() {
        createCase.selectCaseType("FOI");
        safeClickOn(nextButton);
        waitABit(100);
    }

    @When("I enter a blank date")
    public void enterInvalidDateOnCaseCreateScreen() {
        createCase.clearCorrespondentReceivedDateFields();
    }

    @And("I create a {string} case with {string} as the correspondent")
    public void iCreateACaseWithAsTheCorrespondent(String caseType, String correspondent) {
        createCase.createCSCaseOfType(caseType.toUpperCase());
        confirmationScreens.goToCaseFromConfirmationScreen();
        safeClickOn(caseView.allocateToMeLink);
        dataInput.fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        correspondents.addASpecificMemberCorrespondent(correspondent);
        safeClickOn(finishButton);
    }

    @And("I create a single {string} case with the correspondence received date as: {string}")
    public void iCreateACaseWithCorrespondenceDate(String caseType, String date) {
        createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(caseType, date);
    }

    @And("I create a single {string} case with the correspondence received date set {int} workdays ago")
    public void iCreateACaseReceivedNWorkdaysAgo(String caseType, int days) {
        createCase.createCaseReceivedNWorkdaysAgo(caseType, days);
    }

    @And("I create a single {string} case with the correspondence received date set as today")
    public void iCreateACaseReceivedToday(String caseType) {
        createCase.createCSCaseOfType(caseType.toUpperCase());
    }

    @When("I allocate the case to another user on the case details accordion screen")
    public void iAllocateToAnotherUserOnTheCaseDetailsAccordionScreen() {
        User user = null;
        String caseType = sessionVariableCalled("caseType");
        switch (caseType) {
            case "MIN":
            case "DTEN":
            case "TRO":
                user = User.DCU_USER;
                break;
            case "MPAM":
            case "MTS":
                user = User.MPAM_USER;
                break;
            case "COMP":
                user = User.COMP_USER;
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        caseView.allocateToUserByVisibleText(user.getAllocationText());
        setSessionVariable("selectedUser").to(user);
    }

    @And("I create and claim a MPAM case with {string} as the Urgency level and {string} as the Reference Type")
    public void iCreateAndClaimAMPAMCaseWithAsTheUrgencyLevelAndAsTheReferenceType(String urgency, String refType) {
        iCreateAMPAMCaseWithAsTheUrgencyLevelAndAsTheReferenceType(urgency, refType);
        dashboard.waitForDashboard();
        dashboard.getAndClaimCurrentCase();
    }

    @And("I create a MPAM case with {string} as the Urgency level and {string} as the Reference Type")
    public void iCreateAMPAMCaseWithAsTheUrgencyLevelAndAsTheReferenceType(String urgency, String refType) {
        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedUrgencyAndReferenceType(urgency, refType, "Triage");
        dashboard.waitForDashboard();
    }

    @And("I create a Ministerial MPAM case with {string} as the Ministerial Sign Off Team and move it to the {string} stage")
    public void iCreateAMinisterialMPAMCaseWithAsTheMinisterialSignOffTeam(String signOffTeam, String stage) {
        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedSignOffTeam(signOffTeam, stage);
        dashboard.waitForDashboard();
    }

    @And("I create a {string} case with {string} as its {string}")
    public void iCreateACaseWithAsIts(String caseType, String infoValue, String infoType) throws ParseException {
        switch (caseType.toUpperCase()) {
            case "DCU":
                switch (infoType.toUpperCase()) {
                    case "CASE TYPE":
                        createCase.createCSCaseOfType(infoValue);
                        break;
                    case "RECEIVED ON OR AFTER DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MIN", "After", infoValue);
                        break;
                    case "RECEIVED ON OR BEFORE DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MIN", "Before", infoValue);
                        break;
                    case "MEMBER OF PARLIAMENT NAME":
                        createCase.createCSCaseOfType("MIN");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        dataInput.fillAllMandatoryCorrespondenceFields();
                        clickContinueButton();
                        correspondents.addASpecificMemberCorrespondent(infoValue);
                        safeClickOn(finishButton);
                        break;
                    case "PUBLIC CORRESPONDENT NAME":
                    case "CORRESPONDENT POSTCODE":
                    case "CORRESPONDENT EMAIL ADDRESS":
                        createCase.createCSCaseOfType("MIN");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        dataInput.fillAllMandatoryCorrespondenceFields();
                        clickContinueButton();
                        correspondents.addANonMemberCorrespondentOfType("Constituent");
                        correspondents.confirmPrimaryCorrespondent();
                        dashboard.waitForDashboard();
                        break;
                    case "TOPIC":
                        iCreateACaseWithAsIts("DCU", "Gordon Freeman", "Public Correspondent Name");
                        dashboard.getAndClaimCurrentCase();
                        dcuProgressCase.moveCaseFromMarkupToInitialDraftWithSpecificTopic(infoValue);
                        break;
                    case "SIGN OFF TEAM":
                        iCreateACaseWithAsIts("DCU", "Animal alternatives (3Rs)", "Topic");
                        break;
                    case "ACTIVE CASES ONLY":
                        createCase.createCSCaseOfType("MIN");
                        break;
                    case "HOME SECRETARY INTEREST":
                        createCase.createCSCaseOfType("MIN");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                        dataInput.selectACorrespondenceReceivedChannel();
                        dataInput.selectASpecificCopyToNoTenOption("No");
                        dataInput.selectASpecificHomeSecInterestOption(infoValue);
                        dataInput.selectAHomeSecReplyOption();
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
                    case "CASE REFERENCE":
                    case "ACTIVE CASES ONLY":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        break;
                    case "REFERENCE TYPE":
                        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedReferenceType(infoType, "Triage");
                        break;
                    case "MINISTERIAL SIGN OFF TEAM":
                        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedSignOffTeam(infoValue, "Triage");
                        break;
                    case "MEMBER OF PARLIAMENT NAME":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithSpecifiedMPCorrespondentToTriageStage(infoValue);
                        break;
                    case "CORRESPONDENT REFERENCE NUMBER":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.addCorrespondentWithSpecificReferenceToCase(infoValue);
                        break;
                    case "RECEIVED ON OR BEFORE DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "Before", infoValue);
                        break;
                    case "RECEIVED ON OR AFTER DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "After", infoValue);
                        break;
                    case "CAMPAIGN":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        mpamProgressCase.moveCaseFromCreationToTriage();
                        dashboard.getAndClaimCurrentCase();
                        campaign.moveCaseFromAStageToCampaign(infoValue);
                        break;
                    case "PUBLIC CORRESPONDENT NAME":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.triggerMPCorrespondentIsMandatoryScreen();
                        dashboard.goToDashboard();
                        break;
                    case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                        createCase.createCSCaseOfType("MTS");
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
                    case "CASE TYPE":
                        createCase.createCSCaseOfType(infoValue);
                        break;
                    case "CORRESPONDENT FULL NAME":
                    case "CORRESPONDENT POSTCODE":
                    case "CORRESPONDENT EMAIL ADDRESS":
                        createCase.createCSCaseOfType("COMP");
                        confirmationScreens.goToCaseFromConfirmationScreen();
                        caseView.clickAllocateToMeLink();
                        correspondents.addANonMemberCorrespondentOfType("Complainant");
                        correspondents.confirmPrimaryCorrespondent();
                        break;
                    case "COMPLAINANT DATE OF BIRTH":
                        createCase.createCSCaseOfType("COMP");
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
                    case "CASE REFERENCE":
                        createCase.createCSCaseOfType("COMP");
                        break;
                    case "COMPLAINANT HOME OFFICE REFERENCE":
                        createCase.createCSCaseOfType("COMP");
                        confirmationScreens.goToCaseFromConfirmationScreen();
                        caseView.clickAllocateToMeLink();
                        correspondents.addANonMemberCorrespondentOfType("Complainant");
                        correspondents.confirmPrimaryCorrespondent();
                        registration.enterComplainantDOB("01/01/2001");
                        registration.selectAGender();
                        registration.enterACompanyName();
                        registration.enterAHomeOfficeReference("Test entry for Home Office Reference");
                        registration.enterAPortReference();
                        safeClickOn(continueButton);
                        break;
                    default:
                        pendingStep(infoType + " is not defined within " + getMethodName());
                }
                break;
            case "FOI":
                switch (infoType.toUpperCase()) {
                    case "CASE TYPE":
                    case "CORRESPONDENT (NON-MP)":
                    case "TOPIC":
                    case "ACTIVE CASES ONLY":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "RECEIVED ON OR AFTER":
                        dashboard.selectCreateSingleCaseLinkFromMenuBar();
                        if (!nextButton.isVisible()) {
                            dashboard.selectCreateSingleCaseLinkFromMenuBar();
                        }
                        createCase.selectCaseType("FOI");
                        clickTheButton("Next");
                        createCase.editReceivedDate(getTodaysDate());
                        createCase.storeCorrespondenceReceivedDate();
                        createCase.storeCorrespondenceReceivedInKIMUDate();
                        documents.uploadDocumentOfType("docx");
                        createCase.selectCorrespondenceInboundChannel();
                        createCase.enterCorrespondentDetails();
                        createCase.selectFOITopic("Animal alternatives (3Rs)");
                        createCase.enterRequestQuestion();
                        clickTheButton("Submit");
                        dashboard.goToDashboard();
                        break;
                    case "RECEIVED ON OR BEFORE":
                        dashboard.selectCreateSingleCaseLinkFromMenuBar();
                        if (!nextButton.isVisible()) {
                            dashboard.selectCreateSingleCaseLinkFromMenuBar();
                        }
                        createCase.selectCaseType("FOI");
                        clickTheButton("Next");
                        createCase.editReceivedDate("01/01/2010");
                        createCase.storeCorrespondenceReceivedDate();
                        createCase.storeCorrespondenceReceivedInKIMUDate();
                        documents.uploadDocumentOfType("docx");
                        createCase.selectCorrespondenceInboundChannel();
                        createCase.enterCorrespondentDetails();
                        createCase.selectFOITopic("Animal alternatives (3Rs)");
                        createCase.enterRequestQuestion();
                        clickTheButton("Submit");
                        dashboard.goToDashboard();
                        break;
                    default:
                        pendingStep(infoType + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("the case should be allocated to the previously selected user in the summary")
    public void theCaseShouldBeAllocatedToThePreviouslySelectedUser() {
        summaryTab.selectSummaryTab();
        summaryTab.assertAllocatedUserIs(sessionVariableCalled("selectedUser"));
    }
}