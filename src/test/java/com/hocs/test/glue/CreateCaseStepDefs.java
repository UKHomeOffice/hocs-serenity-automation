package com.hocs.test.glue;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Documents;
import com.hocs.test.pages.DCU_Workflow.Markup_AddTopics;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.DCU_Workflow.DataInput;
import com.hocs.test.pages.Homepage;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateCaseStepDefs extends BasePage {

    Documents documents;

    CreateCase createCase;

    CreateCase_SuccessPage createCaseSuccessPage;

    DataInput dataInput;

    Homepage homepage;

    Markup_AddTopics markupAddTopics;

    Workstacks workstacks;

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
        createCase.createCaseWithoutSelectingCorrespondenceType();
    }

    @Given("I create a single case {string}")
    public void createACaseTypeSpecificCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                createCase.createDCUMinSingleCaseWithID();
                setSessionVariable("caseType").to(caseType);
                safeClickOn(homepage.home);
                break;
            case "DTEN":
                createCase.createDCUTENSingleCaseWithID();
                setSessionVariable("caseType").to(caseType);
                safeClickOn(homepage.home);
                break;
            case "TRO":
                createCase.createDCUTROSingleCaseWithID();
                setSessionVariable("caseType").to(caseType);
                safeClickOn(homepage.home);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I create a {string} case with {string} as the primary topic")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                createCase.createDCUMinSingleCase();
                safeClickOn(homepage.home);
                homepage.waitForPerformanceProcessTeam();
                safeClickOn(homepage.performanceProcessTeam);
                workstacks.clickDCUMINFilterCard();
                dataInput.dataInputFullFlow();
                markupAddTopics.fromMarkupStartSelectATopic(topic);
                setSessionVariable("searchTopic").to(topic);
                break;
            case "DTEN":
                createCase.createDCU10SingleCase();
                safeClickOn(homepage.home);
                homepage.waitForPerformanceProcessTeam();
                safeClickOn(homepage.performanceProcessTeam);
                workstacks.clickDCUTENFilterCard();
                dataInput.dataInputFullFlow();
                markupAddTopics.fromMarkupStartSelectATopic(topic);
                setSessionVariable("searchTopic").to(topic);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I create a single MIN case")
    public void createNewMinCase() {
        createCase.createDCUMinSingleCase();
    }

    @When("I create a single {string} case")
    public void createNewCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                createCase.createDCUMinSingleCase();
                break;
            case "DTEN":
                createCase.createDCU10SingleCase();
                break;
            case "TRO":
                createCase.createDCUTROSingleCase();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I allocate the case to myself via the successful case creation screen")
    public void allocateToMe() {
        createCaseSuccessPage.allocateToMeViaSuccessfulCreationScreen();
    }

    @When("I go to the case from the successful case creation screen")
    public void goToSuccessfullyCreatedCase() {
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
    }

    @Then("the case should be visible in my workstack")
    public void assertThatCaseIsAddedToMyWorkstack() {
        safeClickOn(homepage.home);
        safeClickOn(homepage.myCases);
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("the case should be visible in the Performance and Process Team workstack")
    public void assertThatNewMinCaseIsInPerformanceAndProcessTeam() {
        safeClickOn(homepage.home);
        safeClickOn(homepage.performanceProcessTeam);
        workstacks.assertCaseReferenceIsVisible();
    }

    @When("I navigate to the {string} and select the check box against the newly created"
            + " case and allocate it to myself")
    public void allocateCaseUsingCheckbox(String workstack) {
        safeClickOn(createCase.$("//input[@id='submit']"));
        String newCaseReference = workstacks.$("//h1").getText();
        setSessionVariable("caseReference").to(newCaseReference);
        switch (workstack.toUpperCase()) {
            case "PERFORMANCE AND PROCESS TEAM":
                safeClickOn(homepage.home);
                safeClickOn(homepage.performanceProcessTeam);
                workstacks.clickCheckboxRelevantToCaseReference();
                safeClickOn(workstacks.allocateSelectedToMeButton);
                safeClickOn(workstacks.home);
                break;
            case "TRANSFERS AND N10 TEAM":
                safeClickOn(homepage.home);
                safeClickOn(homepage.transferN10Team);
                workstacks.clickCheckboxRelevantToCaseReference();
                safeClickOn(workstacks.allocateSelectedToMeButton);
                safeClickOn(workstacks.home);
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
    }

    @When("I bulk create {int} {string} cases")
    public void bulkCreateCases(int cases, String caseType) {
        safeClickOn(homepage.createBulkCases);
        switch (caseType.toUpperCase()) {
            case "MIN":
                safeClickOn(createCase.dcuMinRadioButton);
                break;
            case "TRO":
                safeClickOn(createCase.dcuTroRadioButton);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        safeClickOn(createCase.nextButton);
        documents.bulkUploadDocuments(cases);
        safeClickOn(createCase.finishButton);
    }

    @When("I create a {string} case {string} a document")
    public void createCaseWithDocument(String caseType, String document) {
        safeClickOn(homepage.createSingleCase);
        switch (caseType.toUpperCase()) {
            case "MIN":
                safeClickOn(createCase.dcuMinRadioButton);
                safeClickOn(createCase.nextButton);
                break;
            case "TRO":
                safeClickOn(createCase.dcuTroRadioButton);
                safeClickOn(createCase.nextButton);
                break;
            case "DTEN":
                safeClickOn(createCase.dcuDtenRadioButton);
                safeClickOn(createCase.nextButton);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (document.toUpperCase()) {
            case "WITH":
                documents.uploadDocumentOfType("docx");
                safeClickOn(createCase.finishButton);
                break;
            case "WITHOUT":
                safeClickOn(createCase.finishButton);
                break;
            default:
                pendingStep(document + " is not defined within " + getMethodName());
        }
    }

    @Then("A case is created successfully")
    public void aCaseIsCreatedSuccessfully() {
        createCaseSuccessPage.assertCaseCreatedSuccess();
        createCaseSuccessPage.getCaseReference();
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
        safeClickOn(createCase.finishButton);
    }

    @Then("an error message should be displayed as I have not entered the correspondence received date")
    public void assertThatDateReceivedErrorMessageIsShown() {
        createCase.assertDateReceivedNotEnteredErrorMessage();

    }

    @Then("an error message should be displayed as I have entered an invalid date")
    public void assertThatDateReceivedIsInvalidErrorMessageIsShown() {
        createCase.assertDateReceivedIsInvalidErrorMessage();
    }

    @When("I move to the When Was Correspondence Received Page")
    public void getToWhenWasCorrespondenceReceivedPage() {
        createCase.getToWhenWasCorReceived();
    }

    @When("I enter a blank date")
    public void enterInvalidDateOnCaseCreateScreen() {
        createCase.enterNoDate();
    }

    @When("I create a new {string} case and go home")
    public void createNewCaseGoHome(String caseType) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                createCase.createDCUMinSingleCase();
                homepage.goHome();
                break;
            case "DTEN":
                createCase.createDCU10SingleCase();
                homepage.goHome();
                break;
            case "TRO":
                createCase.createDCUTROSingleCase();
                homepage.goHome();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I create a {string} case with {string} as the correspondent")
    public void iCreateACaseWithAsTheCorrespondent(String caseType, String correspondent) {
        createCase.createDCUMinSingleCase();
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        workstacks.clickAllocateToMeButton();
        dataInput.completeDataInputStageWithMPCorrespondent(correspondent);
    }

    @And("I create a single {string} case with the correspondence received date as: {string}-{string}-{string}")
    public void iCreateACaseWithCorrespondenceDate(String caseType, String day, String month, String year) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                createCase.createDCUMinSingleCaseWithCorrespondenceReceivedDate(day, month, year);
                break;
            case "TRO":
                createCase.createDCUTROSingleCaseWithCorrespondenceReceivedDate(day, month, year);
                break;
            case "DTEN":
                createCase.createDCUDTenSingleCaseWithCorrespondenceReceivedDate(day, month, year);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }


}

