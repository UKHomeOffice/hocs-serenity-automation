package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.complaints.BFProgressCase;
import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.complaints.IEDETProgressCase;
import com.hocs.test.pages.complaints.POGRProgressCase;
import com.hocs.test.pages.complaints.SMCProgressCase;
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
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.MPAMProgressCase;

import com.hocs.test.pages.to.TOProgressCase;
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

    COMPProgressCase compProgressCase;

    FOIProgressCase foiProgressCase;

    SMCProgressCase smcProgressCase;

    IEDETProgressCase iedetProgressCase;

    TOProgressCase toProgressCase;

    BFProgressCase bfProgressCase;

    POGRProgressCase pogrProgressCase;

    DataInput dataInput;

    Dashboard dashboard;

    Markup markup;

    Workstacks workstacks;

    CaseView caseView;

    SummaryTab summaryTab;

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
        caseView.waitForCaseToLoad();
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
        clickNextButton();
        documents.bulkUploadDocuments(cases);
        createCase.clickCreateCasesButton();
    }

    @When("I create a {string} case with {string} as the primary topic")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
        createCase.createCSCaseOfType(caseType);
        confirmationScreens.goToCaseFromConfirmationScreen();
        caseView.clickAllocateToMeLink();
        dcuProgressCase.moveCaseFromDataInputToMarkup();
        dashboard.getAndClaimCurrentCase();
        markup.selectPolicyResponseRadioButton();
        clickContinueButton();
        waitABit(1000);
        markup.addTopicToCase(topic);
        markup.confirmPrimaryTopic();
        setSessionVariable("searchTopic").to(topic);
    }

    @And("I get a TO case with {string} as the business area and move the case to the {string} stage")
    public void iCreateATOCaseWithAsTheBusinessAreaAndMoveTheCaseToTheStage(String businessArea, String targetStage) {
        toProgressCase.createCaseWithSpecificBusinessAreaAndMoveItToTargetStage(businessArea, targetStage);
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
            documents.assertFileIsVisible(sessionVariableCalled("fileType"));
        }
        else {
            documents.assertFileIsNotVisible(sessionVariableCalled("fileType"));
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
        clickNextButton();
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
        clickNextButton();
        waitABit(100);
    }
    @When("I select the FOI case type and continue")
    public void iSelectFOICaseTypeAndContinue() {
        createCase.selectCaseType("FOI");
        clickNextButton();
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
        clickFinishButton();
    }

    @And("I create a single {string} case with the correspondence received date as: {string}")
    public void iCreateACaseWithCorrespondenceDate(String caseType, String date) {
        createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(caseType, date);
    }

    @And("I create a single {string} case with the correspondence received date set {int} workdays ago")
    public void iCreateACaseReceivedNWorkdaysAgo(String caseType, int days) {
        createCase.createCaseReceivedNWorkdaysAgo(caseType, days);
    }

    @And("I create an SMC case received {int} workdays in the past and move it to the {string} stage")
    public void iCreateAnSMCCaseReceivedNWorkdaysAgo(int days, String stage) {
        createCase.createCaseReceivedNWorkdaysAgo("SMC", days);
        if (!stage.equalsIgnoreCase("REGISTRATION")) {
            smcProgressCase.moveCaseFromCurrentStageToTargetStage("Registration", stage);
        }
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
            case "IEDET":
                user = User.IEDET_USER;
                break;
            case "SMC":
                user = User.SMC_USER;
                break;
            case "FOI":
                user = User.FOI_USER;
                break;
            case "TO":
                user = User.TO_USER;
                break;
            case "BF":
                user = User.BF_USER;
                break;
            case "POGR":
                user = User.POGR_USER;
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

    @Then("the case should be allocated to the previously selected user in the summary")
    public void theCaseShouldBeAllocatedToThePreviouslySelectedUser() {
        summaryTab.selectSummaryTab();
        summaryTab.assertAllocatedUserIs(sessionVariableCalled("selectedUser"));
    }

    @And("I create and claim a Priority POGR case")
    public void iCreateAndClaimAPriorityPOGRCase() {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithPrioritySetTo("POGR", true, "Investigation");
        dashboard.waitForDashboard();
        dashboard.getAndClaimCurrentCase();
    }

    @And("I create and claim a non-Priority POGR case")
    public void iCreateAndClaimANonPriorityPOGRCase() {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithPrioritySetTo("POGR", false, "Investigation");
        dashboard.waitForDashboard();
        dashboard.getAndClaimCurrentCase();
    }

    @And("I create a {string} Priority POGR case")
    public void iCreateAPriorityPOGRCase(String businessArea) {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithSetBusinessAreaAndPriority("POGR", businessArea, true, "Investigation");
        dashboard.waitForDashboard();
    }

    @And("I create a {string} non-Priority POGR case")
    public void iCreateANonPriorityPOGRCase(String businessArea) {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithSetBusinessAreaAndPriority("POGR", businessArea, false, "Investigation");
        dashboard.waitForDashboard();
    }

    @And("I escalate the closed case to Stage 2")
    public void iEscalateTheClosedCaseToStage() {
        createCase.createAStage2CaseFromASpecificClosedStage1Case(getCurrentCaseReference());
        confirmationScreens.goToCaseFromConfirmationScreen();
    }

    @And("I get a new case that allows adding a Member correspondent")
    public void iGetANewCaseThatAllowsAddingAMemberCorrespondent() {
        createCase.createCSCaseOfType(createCase.getRandomCaseTypeThatAllowsMemberCorrespondents());
        confirmationScreens.goToCaseFromConfirmationScreen();
        caseView.clickAllocateToMeLink();
    }

    @And("I get a new case that requires correspondents to be added as part of a stage")
    public void iGetANewCaseThatRequiresCorrespondentsToBeAddedAsPartOfAStage() {
        createCase.createCSCaseOfType(createCase.getRandomCaseTypeThatAllowsMultipleCorrespondents());
        confirmationScreens.goToCaseFromConfirmationScreen();
        caseView.clickAllocateToMeLink();
    }

    @And("I generate a {string} case to validate search functionality")
    public void iGenerateACaseToValidateSearchFunctionality(String caseType) throws ParseException {
        switch (caseType.toUpperCase()) {
            case "DCU":
                dcuProgressCase.generateDCUSearchCaseData("N/A", "ALL");
                break;
            case "MPAM":
                mpamProgressCase.generateMPAMSearchCaseData("N/A", "ALL");
                break;
            case "COMP":
                compProgressCase.generateCOMPSearchCaseData("N/A", "ALL");
                break;
            case "BF":
                bfProgressCase.generateBFSearchCaseData("N/A", "ALL");
                break;
            case "IEDET":
                iedetProgressCase.generateIEDETSearchCaseData("N/A", "ALL");
                break;
            case "SMC":
                smcProgressCase.generateSMCSearchCaseData("N/A", "ALL");
                break;
            case "POGR":
                pogrProgressCase.generatePOGRSearchCaseData("N/A", "ALL");
                break;
            case "FOI":
                foiProgressCase.generateFOISearchCaseData("N/A", "ALL");
                break;
            case "TO":
                toProgressCase.generateTOSearchCaseData("N/A", "ALL");
                break;
            default:
                pendingStep(caseType + " is not contained within " + getMethodName());
        }
    }
}