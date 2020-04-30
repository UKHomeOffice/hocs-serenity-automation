package com.hocs.test.glue;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.Documents;
import com.hocs.test.pages.dcu.Markup_AddTopics;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.dcu.DataInput;
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

    Markup markup;

    Markup_AddTopics markupAddTopics;

    Workstacks workstacks;

    @When("I create a single {string} case")
    public void createNewCase(String caseType) {
        createCase.createCaseOfType(caseType.toUpperCase());
    }

    @Given("I create a single {string} case and return to the dashboard")
    public void createACaseTypeSpecificCase(String caseType) {
        createCase.createCaseOfType(caseType.toUpperCase());
        homepage.goHome();
    }

    @When("I create a {string} case {string} a document")
    public void createCaseWithDocument(String caseType, String document) {
        safeClickOn(homepage.createSingleCase);
        createCase.selectCaseType(caseType);
        safeClickOn(createCase.nextButton);
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

    @When("I bulk create {int} {string} cases")
    public void bulkCreateCases(int cases, String caseType) {
        safeClickOn(homepage.createBulkCases);
        createCase.selectCaseType(caseType);
        safeClickOn(createCase.nextButton);
        documents.bulkUploadDocuments(cases);
        safeClickOn(createCase.finishButton);
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
        createCase.createCaseWithoutSelectingCorrespondenceType();
    }

    @When("I create a {string} case with {string} as the primary topic")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
                createCase.createCaseOfType(caseType);
                safeClickOn(homepage.home);
                homepage.getAndClaimCurrentCase();
                dataInput.moveCaseFromDataInputToMarkup();
                homepage.getAndClaimCurrentCase();
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

    @Then("A case is created successfully")
    public void aCaseIsCreatedSuccessfully() {
        createCaseSuccessPage.assertCaseCreatedSuccess();
        createCaseSuccessPage.getCaseReference();
    }

    @Then("A case is created successfully {string} a document")
    public void aCaseIsCreatedSuccessfullyWithWithoutADocument(String withWithout) {
        createCaseSuccessPage.assertCaseCreatedSuccess();
        createCaseSuccessPage.getCaseReference();
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        if (withWithout.equals("with")) {
            documents.assertDocumentPresentIs(true);
        }
        else {
            documents.assertDocumentPresentIs(false);
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

    @And("I create a {string} case with {string} as the correspondent")
    public void iCreateACaseWithAsTheCorrespondent(String caseType, String correspondent) {
        createCase.createCaseOfType(caseType.toUpperCase());
        createCaseSuccessPage.goToCaseFromSuccessfulCreationScreen();
        workstacks.clickAllocateToMeButton();
        dataInput.completeDataInputStageWithMPCorrespondent(correspondent);
    }

    @And("I create a single {string} case with the correspondence received date as: {string}-{string}-{string}")
    public void iCreateACaseWithCorrespondenceDate(String caseType, String day, String month, String year) {
        createCase.createCaseWithSetCorrespondenceReceivedDate(caseType, day, month, year);
    }
}