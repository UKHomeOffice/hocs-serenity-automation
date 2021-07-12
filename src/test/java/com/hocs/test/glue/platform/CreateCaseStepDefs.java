package com.hocs.test.glue.platform;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.platform.AddCorrespondent;
import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.CreateCase;
import com.hocs.test.pages.platform.CreateCase_SuccessPage;
import com.hocs.test.pages.platform.Dashboard;
import com.hocs.test.pages.platform.Documents;
import com.hocs.test.pages.platform.SummaryTab;
import com.hocs.test.pages.platform.UnallocatedCaseView;
import com.hocs.test.pages.platform.Workstacks;
import com.hocs.test.pages.comp.AccordionCOMP;
import com.hocs.test.pages.comp.COMPRegistration;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.ukvi.Campaign;
import com.hocs.test.pages.ukvi.Creation;
import com.hocs.test.pages.ukvi.MTSDataInput;
import com.hocs.test.pages.wcs.WCSRegistration;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;

public class CreateCaseStepDefs extends BasePage {

    AccordionCOMP accordionCOMP;

    Documents documents;

    CreateCase createCase;

    CreateCase_SuccessPage createCaseSuccessPage;

    Campaign campaign;

    DataInput dataInput;

    MTSDataInput mtsDataInput;

    Dashboard dashboard;

    Markup markup;

    Markup_AddTopics markupAddTopics;

    Workstacks workstacks;

    UnallocatedCaseView unallocatedCaseView;

    Creation creation;

    SummaryTab summaryTab;

    COMPRegistration compRegistration;

    WCSRegistration wcsRegistration;

    AddCorrespondent addCorrespondent;

    @When("I create a single {string} case/claim")
    public void createNewCase(String caseType) {
        if (caseType.equalsIgnoreCase("WCS")) {
            createCase.createWCSCase();
            waitFor(wcsRegistration.registrationSchemeCheckTitle);
        } else {
            createCase.createDECSCaseOfType(caseType.toUpperCase());
        }
    }

    @Given("I create a single {string} case and return to the dashboard")
    public void createACaseTypeSpecificCase(String caseType) {
        createCase.createDECSCaseOfType(caseType.toUpperCase());
        goToDashboard();
    }

    @And("I create a WCS claim")
    public void iCreateAWCSClaim() {
        createCase.createWCSCase();
        waitFor(wcsRegistration.registrationSchemeCheckTitle);
    }

    @When("I create a {string} case {string} a document")
    public void createCaseWithDocument(String caseType, String document) {
        safeClickOn(dashboard.createSingleCaseLink);
        createCase.selectCaseType(caseType);
        safeClickOn(createCase.nextButton);
        switch (document.toUpperCase()) {
            case "WITH":
                documents.uploadDocumentOfType("docx");
                createCase.clickCreateCaseButton();
                break;
            case "WITHOUT":
                createCase.clickCreateCaseButton();
                break;
            default:
                pendingStep(document + " is not defined within " + getMethodName());
        }
    }

    @When("I bulk create {int} {string} cases")
    public void bulkCreateCases(int cases, String caseType) {
        setSessionVariable("bulkCaseNumber").to(cases);
        safeClickOn(dashboard.createBulkCasesLink);
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
        safeClickOn(dashboard.createSingleCaseLink);
        clickTheButton("Next");
    }

    @When("I create a {string} case with {string} as the primary topic")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
        createCase.createDECSCaseOfType(caseType);
        safeClickOn(dashboard.decsDashboardLink);
        dashboard.getAndClaimCurrentCase();
        dataInput.moveCaseFromDataInputToMarkup();
        dashboard.getAndClaimCurrentCase();
        markup.getToMarkupAddATopicScreenPrerequisites();
        markupAddTopics.enterATopic(topic);
        setSessionVariable("searchTopic").to(topic);
    }

    @When("I allocate the case to myself via the successful case creation screen")
    public void allocateToMe() {
        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
    }

    @When("I go to the case from the successful case creation screen")
    public void goToSuccessfullyCreatedCase() {
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
    }

    @Then("the case should be visible in the Performance and Process Team workstack")
    public void assertThatNewMinCaseIsInPerformanceAndProcessTeam() {
        safeClickOn(dashboard.decsDashboardLink);
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
                safeClickOn(dashboard.decsDashboardLink);
                safeClickOn(dashboard.performanceProcessTeam);
                workstacks.clickCheckboxRelevantToCaseReference();
                safeClickOn(workstacks.allocateSelectedToMeButton);
                safeClickOn(workstacks.decsDashboardLink);
                break;
            case "TRANSFERS AND N10 TEAM":
                safeClickOn(dashboard.decsDashboardLink);
                safeClickOn(dashboard.transferN10Team);
                workstacks.clickCheckboxRelevantToCaseReference();
                safeClickOn(workstacks.allocateSelectedToMeButton);
                safeClickOn(workstacks.decsDashboardLink);
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
    }

    @Then("A case is created successfully")
    public void aCaseIsCreatedSuccessfully() {
        createCaseSuccessPage.assertCaseCreatedSuccess();
        createCaseSuccessPage.storeCaseReference();
    }

    @Then("A case is created successfully {string} a document")
    public void aCaseIsCreatedSuccessfullyWithWithoutADocument(String withWithout) {
        createCaseSuccessPage.assertCaseCreatedSuccess();
        createCaseSuccessPage.storeCaseReference();
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        if (withWithout.equals("with")) {
            documents.assertFileIsVisible(sessionVariableCalled("docType"));
        }
        else {
            documents.assertFileIsNotVisible(sessionVariableCalled("docType"));
        }
    }

    @Then("bulk cases are created successfully")
    public void BulkCasesAreCreatedSuccessfully() {
        createCaseSuccessPage.assertBulkCasesCreatedSuccess();
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

    @When("I select {string} case type and continue")
    public void getToWhenWasCorrespondenceReceivedPage(String caseType) {
        createCase.selectCaseType(caseType);
        safeClickOn(nextButton);
        waitABit(100);
    }

    @When("I enter a blank date")
    public void enterInvalidDateOnCaseCreateScreen() {
        createCase.clearCorrespondentReceivedDateFields();
    }

    @And("I create a {string} case with {string} as the correspondent")
    public void iCreateACaseWithAsTheCorrespondent(String caseType, String correspondent) {
        createCase.createDECSCaseOfType(caseType.toUpperCase());
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        safeClickOn(unallocatedCaseView.allocateToMeLink);
        dataInput.completeDataInputStageWithMPCorrespondent(correspondent);
    }

    @And("I create a single {string} case with the correspondence received date as: {string}")
    public void iCreateACaseWithCorrespondenceDate(String caseType, String date) {
        createCase.createCaseWithSetCorrespondenceReceivedDate(caseType, date);
    }

    @And("I create a single {string} case with the correspondence received date set {int} workdays ago")
    public void iCreateACaseReceivedNWorkdaysAgo(String caseType, int days) {
        createCase.createCaseReceivedNWorkdaysAgo(caseType, days);
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
                user = User.UKVI_USER;
                break;
            case "COMP":
                user = User.COMP_USER;
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        unallocatedCaseView.allocateToUserByVisibleText(user.getAllocationText());
        setSessionVariable("selectedUser").to(user);
    }

    @And("I create and claim a MPAM case with {string} as the Urgency level and {string} as the Reference Type")
    public void iCreateAndClaimAMPAMCaseWithAsTheUrgencyLevelAndAsTheReferenceType(String urgency, String refType) {
        iCreateAMPAMCaseWithAsTheUrgencyLevelAndAsTheReferenceType(urgency, refType);
        waitForDashboard();
        dashboard.getAndClaimCurrentCase();
    }

    @And("I create a MPAM case with {string} as the Urgency level and {string} as the Reference Type")
    public void iCreateAMPAMCaseWithAsTheUrgencyLevelAndAsTheReferenceType(String urgency, String refType) {
        createCase.createDECSCaseOfType("MPAM");
        goToDashboard();
        dashboard.getAndClaimCurrentCase();
        creation.moveCaseWithSpecifiedUrgencyAndRefTypeToTriageStage(urgency, refType);
        waitForDashboard();
    }

    @And("I create a Ministerial MPAM case with {string} as the Ministerial Sign Off Team and move it to the Triage stage")
    public void iCreateAMinisterialMPAMCaseWithAsTheMinisterialSignOffTeam(String signOffTeam) {
        createCase.createDECSCaseOfType("MPAM");
        goToDashboard();
        dashboard.getAndClaimCurrentCase();
        creation.moveCaseWithSpecificMinisterialSignOffTeamToTriageStage(signOffTeam);
        waitForDashboard();
    }

    @And("I create a {string} case with {string} as its {string}")
    public void iCreateACaseWithAsIts(String caseType, String infoValue, String infoType) throws ParseException {
        switch (caseType.toUpperCase()) {
            case "DCU":
                switch (infoType.toUpperCase()) {
                    case "CASE TYPE":
                        createCase.createDECSCaseOfType(infoValue);
                        break;
                    case "RECEIVED ON OR AFTER DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MIN", "After", infoValue);
                        break;
                    case "RECEIVED ON OR BEFORE DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MIN", "Before", infoValue);
                        break;
                    case "MEMBER OF PARLIAMENT NAME":
                        createCase.createDECSCaseOfType("MIN");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        dataInput.completeDataInputStageWithMPCorrespondent(infoValue);
                        break;
                    case "PUBLIC CORRESPONDENT NAME":
                        createCase.createDECSCaseOfType("MIN");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        dataInput.completeDataInputStageWithPublicCorrespondent();
                        waitForDashboard();
                        break;
                    case "TOPIC":
                        iCreateACaseWithAsIts("DCU", "Gordon Freeman", "Public Correspondent Name");
                        dashboard.getAndClaimCurrentCase();
                        markup.moveCaseFromMarkupToInitialDraftWithSpecificTopic(infoValue);
                        break;
                    case "SIGN OFF TEAM":
                        iCreateACaseWithAsIts("DCU", "Animal alternatives (3Rs)", "Topic");
                        break;
                    case "ACTIVE CASES ONLY":
                        createCase.createDECSCaseOfType("MIN");
                        break;
                    case "HOME SECRETARY INTEREST":
                        createCase.createDECSCaseOfType("MIN");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        if (infoValue.equalsIgnoreCase("YES")) {
                            dataInput.completeDataInputStageSpecifyingHomeSecInterest(true);
                        } else if (infoValue.equalsIgnoreCase("NO")) {
                            dataInput.completeDataInputStageSpecifyingHomeSecInterest(false);
                        }
                        break;
                    default:
                        pendingStep(infoType + " is not defined within " + getMethodName());
                }
                break;
            case "UKVI":
                switch (infoType.toUpperCase()) {
                    case "CASE REFERENCE":
                    case "ACTIVE CASES ONLY":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        break;
                    case "REFERENCE TYPE":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithSpecifiedBusinessAreaAndRefTypeToTriageStage("UKVI", infoValue);
                        break;
                    case "MINISTERIAL SIGN OFF TEAM":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithSpecificMinisterialSignOffTeamToTriageStage(infoValue);
                        break;
                    case "MEMBER OF PARLIAMENT NAME":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithSpecifiedMPCorrespondentToTriageStage(infoValue);
                        break;
                    case "CORRESPONDENT REFERENCE NUMBER":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithCorrespondentReferenceNumber(infoValue);
                        break;
                    case "RECEIVED ON OR BEFORE DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "Before", infoValue);
                        break;
                    case "RECEIVED ON OR AFTER DATE":
                        createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "After", infoValue);
                        break;
                    case "CAMPAIGN":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseFromCreationToTriage();
                        dashboard.getAndClaimCurrentCase();
                        campaign.moveCaseFromAStageToCampaign(infoValue);
                        break;
                    case "PUBLIC CORRESPONDENT NAME":
                        createCase.createDECSCaseOfType("MPAM");
                        goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.triggerMPCorrespondentIsMandatoryScreen();
                        goToDashboard();
                        break;
                    case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                        createCase.createDECSCaseOfType("MTS");
                        goToDashboard();
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
                        createCase.createDECSCaseOfType("COMP");
                        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                        safeClickOn(continueButton);
                        break;
                    case "COMPLAINANT DATE OF BIRTH":
                        createCase.createDECSCaseOfType("COMP");
                        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                        safeClickOn(continueButton);
                        compRegistration.enterComplainantDOB(infoValue);
                        compRegistration.selectAGender();
                        compRegistration.enterACompanyName();
                        compRegistration.enterAHomeOfficeReference();
                        compRegistration.enterAPortReference();
                        safeClickOn(continueButton);
                        compRegistration.selectComplaintType("Service");
                        safeClickOn(continueButton);
                        compRegistration.selectAChannel();
                        compRegistration.selectASeverity();
                        safeClickOn(continueButton);
                        safeClickOn(accordionCOMP.serviceAccordionButton);
                        safeClickOn(accordionCOMP.delayCheckbox);
                        compRegistration.selectAnOwningCSU();
                        safeClickOn(finishButton);
                        break;
                    case "CASE REFERENCE":
                        createCase.createDECSCaseOfType("COMP");
                        break;
                    case "COMPLAINANT HOME OFFICE REFERENCE":
                        createCase.createDECSCaseOfType("COMP");
                        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                        safeClickOn(continueButton);
                        compRegistration.enterComplainantDOB("01/01/2001");
                        compRegistration.selectAGender();
                        compRegistration.enterACompanyName();
                        compRegistration.enterAHomeOfficeReference();
                        compRegistration.enterAPortReference();
                        safeClickOn(continueButton);
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
        summaryTab.assertAllocatedUserIs(sessionVariableCalled("selectedUser"));
    }
}