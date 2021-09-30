package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.CreateCase_SuccessPage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.SummaryTab;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.comp.Registration;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.foi.FOICreateCase;
import com.hocs.test.pages.ukvi.Campaign;
import com.hocs.test.pages.ukvi.Creation;
import com.hocs.test.pages.ukvi.MTSDataInput;
import config.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;

public class CreateCaseStepDefs extends BasePage {

    Documents documents;

    CreateCase createCase;

    FOICreateCase foiCreateCase;

    CreateCase_SuccessPage createCaseSuccessPage;

    Campaign campaign;

    DataInput dataInput;

    MTSDataInput mtsDataInput;

    Dashboard dashboard;

    Markup markup;

    Markup_AddTopics markupAddTopics;

    Workstacks workstacks;

    CaseView caseView;

    Creation creation;

    SummaryTab summaryTab;

    Registration registration;

    AddCorrespondent addCorrespondent;

    com.hocs.test.pages.wcs.Registration wcsRegistration;

    @When("I create a single {string} case/claim")
    public void createNewCase(String caseType) {
        if (caseType.equalsIgnoreCase("WCS")) {
            createCase.createWCSCase();
            waitFor(wcsRegistration.registrationSchemeCheckTitle);
        } else if (caseType.equalsIgnoreCase("FOI")) {
            foiCreateCase.createFOICase();
        } else {
            createCase.createCSCaseOfType(caseType.toUpperCase());
        }
    }

    @Given("I create a single {string} case and return to the dashboard")
    public void createACaseTypeSpecificCase(String caseType) {
        createCase.createCSCaseOfType(caseType.toUpperCase());
        dashboard.goToDashboard();
    }

    @When("I create a {string} case {string} a document")
    public void createCaseWithDocument(String caseType, String document) {
        dashboard.selectCreateSingleCaseLinkFromMenuBar();
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
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        safeClickOn(caseView.allocateToMeLink);
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
        createCase.createCSCaseOfType("MPAM");
        dashboard.goToDashboard();
        dashboard.getAndClaimCurrentCase();
        creation.moveCaseWithSpecifiedUrgencyAndRefTypeToTriageStage(urgency, refType);
        dashboard.waitForDashboard();
    }

    @And("I create a Ministerial MPAM case with {string} as the Ministerial Sign Off Team and move it to the Triage stage")
    public void iCreateAMinisterialMPAMCaseWithAsTheMinisterialSignOffTeam(String signOffTeam) {
        createCase.createCSCaseOfType("MPAM");
        dashboard.goToDashboard();
        dashboard.getAndClaimCurrentCase();
        creation.moveCaseWithSpecificMinisterialSignOffTeamToTriageStage(signOffTeam);
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
                        dataInput.completeDataInputStageWithMPCorrespondent(infoValue);
                        break;
                    case "PUBLIC CORRESPONDENT NAME":
                    case "CORRESPONDENT POSTCODE":
                    case "CORRESPONDENT EMAIL ADDRESS":
                        createCase.createCSCaseOfType("MIN");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        dataInput.completeDataInputStageWithPublicCorrespondent();
                        dashboard.waitForDashboard();
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
                        createCase.createCSCaseOfType("MIN");
                        break;
                    case "HOME SECRETARY INTEREST":
                        createCase.createCSCaseOfType("MIN");
                        dashboard.goToDashboard();
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
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        break;
                    case "REFERENCE TYPE":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithSpecifiedBusinessAreaAndRefTypeToTriageStage("UKVI", infoValue);
                        break;
                    case "MINISTERIAL SIGN OFF TEAM":
                        createCase.createCSCaseOfType("MPAM");
                        dashboard.goToDashboard();
                        dashboard.getAndClaimCurrentCase();
                        creation.moveCaseWithSpecificMinisterialSignOffTeamToTriageStage(infoValue);
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
                        creation.moveCaseWithCorrespondentReferenceNumber(infoValue);
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
                        creation.moveCaseFromCreationToTriage();
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
                        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                        safeClickOn(continueButton);
                        break;
                    case "COMPLAINANT DATE OF BIRTH":
                        createCase.createCSCaseOfType("COMP");
                        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                        safeClickOn(continueButton);
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
                        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
                        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
                        safeClickOn(continueButton);
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
                        foiCreateCase.createFOICase();
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
                        foiCreateCase.storeCorrespondenceReceivedInKIMUDate();
                        documents.uploadDocumentOfType("docx");
                        foiCreateCase.selectCorrespondenceInboundChannel();
                        foiCreateCase.enterCorrespondentDetails();
                        foiCreateCase.selectFOITopic("Animal alternatives (3Rs)");
                        foiCreateCase.enterRequestQuestion();
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
                        foiCreateCase.storeCorrespondenceReceivedInKIMUDate();
                        documents.uploadDocumentOfType("docx");
                        foiCreateCase.selectCorrespondenceInboundChannel();
                        foiCreateCase.enterCorrespondentDetails();
                        foiCreateCase.selectFOITopic("Animal alternatives (3Rs)");
                        foiCreateCase.enterRequestQuestion();
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
        summaryTab.assertAllocatedUserIs(sessionVariableCalled("selectedUser"));
    }
}