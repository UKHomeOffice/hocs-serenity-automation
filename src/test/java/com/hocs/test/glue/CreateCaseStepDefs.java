package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.Keys;
import org.yecht.Data.Str;

public class CreateCaseStepDefs extends Page {

    AddDocuments addDocuments;

    CreateCase createCase;

    SuccessfulCaseCreation successfulCaseCreation;

    DataInput dataInput;

    Homepage homepage;

    Topics topics;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    @Given("^I am presented with \"([^\"]*)\"$")
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


    @Given("^I create a single case \"([^\"]*)\"$")
    public void createACaseTypeSpecificCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCaseWithID();
                setSessionVariable("caseType").to(caseType);
                clickOn(homepage.home);
                clickOn(homepage.performanceProcessTeam);
                prepareCaseIdAssertion();
                break;
            case "DCU N10":
                createCase.createDCUTENSingleCaseWithID();
                setSessionVariable("caseType").to(caseType);
                clickOn(homepage.home);
                clickOn(homepage.transferN10Team);
                prepareCaseIdAssertion();
                break;
            case "DCU TRO":
                createCase.createDCUTROSingleCaseWithID();
                setSessionVariable("caseType").to(caseType);
                clickOn(homepage.home);
                clickOn(homepage.performanceProcessTeam);
                prepareCaseIdAssertion();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I create a case$")
    public void aCaseIsCreated() {
        clickOn(createCase.dcuMinRadioButton);
        clickOn(createCase.nextButton);
        addDocuments.uploadDocument();
        clickOn(createCase.submitButton);
        waitABit(500);
    }

    @When("^I create a \"([^\"]*)\" case with \"([^\"]*)\"$")
    public void aCaseWithSpecificTopicIsCreated(String caseType, String topic) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                clickOn(homepage.home);
                homepage.waitForPerformanceProcessTeam();
                clickOn(homepage.performanceProcessTeam);
                dataInput.dataInputFullFlow();
                topics.fromMarkupStartSelectATopic(topic);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

//    @When("^I create a \"([^\"]*)\" case with \"([^\"]*)\" and view the primary topics$")
//    public void aCaseWithSpecificTopic(String caseType, String topic) {
//        switch (caseType.toUpperCase()) {
//            case "DCU MIN":
//                createCase.createDCUMinSingleCase();
//                setSessionVariable("caseType").to(caseType);
//                dataInput.dataInputFullFlow();
//                topics.fromMarkupStartSelectATopicAndStayOnPrimaryTopicsPage(topic);
//                break;
//            default:
//                pendingStep(caseType + " is not defined within " + getMethodName());
//        }
//    }

    @When("^I create a case with a <Primary Correspondent>$")
    public void aCaseWithSpecifiedPrimaryCorrespondentIsCreated() {
    }

    @When("^I create a single MIN case$")
    public void createNewMinCase() {
        createCase.createDCUMinSingleCase();
    }

    @When("^I create a single \"([^\"]*)\" case$")
    public void createNewCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                break;
            case "DCU DTEN":
                createCase.createDCU10SingleCase();
                break;
            case "DCU TRO":
                createCase.createDCUTROSingleCase();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I allocate the case to myself via the successful case creation screen$")
    public void allocateToMe() {
        successfulCaseCreation.allocateToMeViaSuccessfulCreationScreen();
    }

    @When("^I go to the case from the successful case creation screen$")
    public void goToSuccessfullyCreatedCase() {
        successfulCaseCreation.goToCaseFromSuccessfulCreationScreen();
    }

    @Then("^the case should be visible in my workstack$")
    public void assertThatCaseIsAddedToMyWorkstack() {
        clickOn(homepage.home);
        clickOn(homepage.myCases);
        workstacks.assertCaseReferenceIsVisible();
    }

    @Then("^the case should be visible in the Performance and Process Team workstack$")
    public void assertThatNewMinCaseIsInPerformanceAndProcessTeam() {
        clickOn(homepage.home);
        clickOn(homepage.performanceProcessTeam);
        workstacks.assertCaseReferenceIsVisible();
    }

    @When("^I navigate to the \"([^\"]*)\" and select the check box against the newly created"
            + " case and allocate it to myself$")
    public void allocateCaseUsingCheckbox(String workstack) {
        clickOn(createCase.$("//input[@id='submit']"));
        String newCaseReference = workstacks.$("//h1").getText();
        setSessionVariable("caseReference").to(newCaseReference);
        switch (workstack.toUpperCase()) {
            case "PERFORMANCE AND PROCESS TEAM":
                clickOn(homepage.home);
                clickOn(homepage.performanceProcessTeam);
                workstacks.clickCheckboxRelevantToCaseReference();
                clickOn(workstacks.allocateCheckboxCaseToMeButton);
                clickOn(workstacks.home);
                break;
            case "TRANSFERS AND N10 TEAM":
                clickOn(homepage.home);
                clickOn(homepage.transferN10Team);
                workstacks.clickCheckboxRelevantToCaseReference();
                clickOn(workstacks.allocateCheckboxCaseToMeButton);
                clickOn(workstacks.home);
                break;
            default:
                pendingStep(workstack + " is not defined within " + getMethodName());
        }
    }

    @When("^I bulk create (\\d+) \"([^\"]*)\" cases$")
    public void bulkCreateCases(int cases, String caseType) {
        clickOn(homepage.createBulkCases);
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                clickOn(createCase.dcuMinRadioButton);
                break;
            case "DCU TRO":
                clickOn(createCase.dcuTroRadioButton);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        clickOn(createCase.nextButton);
        addDocuments.bulkUploadDocuments(cases);
        clickOn(createCase.finishButton);
    }

    @When("^I create a \"([^\"]*)\" case \"([^\"]*)\" a document$")
    public void createCaseWithDocument(String caseType, String document) {
        clickOn(homepage.createSingleCase);
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                clickOn(createCase.dcuMinRadioButton);
                clickOn(createCase.nextButton);
                break;
            case "DCU TRO":
                clickOn(createCase.dcuTroRadioButton);
                clickOn(createCase.nextButton);
                break;
            case "DCU TEN":
                clickOn(createCase.dcuDtenRadioButton);
                clickOn(createCase.nextButton);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }

        switch (document.toUpperCase()) {
            case "WITH":
                addDocuments.uploadDocument();
                clickOn(createCase.finishButton);
                break;
            case "WITHOUT":
                clickOn(createCase.finishButton);
                break;
            default:
                pendingStep(document + " is not defined within " + getMethodName());
        }
    }

    @Then("^A case is created successfully$")
    public void aCaseIsCreatedSuccessfully() {
        successfulCaseCreation.assertCaseCreatedSuccess();
        successfulCaseCreation.getCaseReference();
    }

    @Then("^bulk cases are created successfully$")
    public void BulkCasesAreCreatedSuccessfully() {
        successfulCaseCreation.assertBulkCasesCreatedSuccess();
    }

    @Then("^an error message should be displayed as I have not selected the case type$")
    public void assertThatCaseTypeErrorMessageIsDisplayed() {
        createCase.assertCaseTypeErrorMessage();
    }

    @When("^they select the case type")
    public void userClicksCaseRadioButtonAndClicksNextButton() {
        clickOn(createCase.dcuMinRadioButton);
        clickOn(createCase.nextButton);
    }

    @When("^I click the finish button on the create single case screen$")
    public void userCreatesCaseWithoutEnteringDateReceived() {
        clickOn(createCase.dcuMinRadioButton);
        clickOn(createCase.nextButton);
        dataInput.clearDateCorrespondenceReceived();
        clickOn(createCase.finishButton);
    }

    @When("^I click the finish button after entering an invalid date on the create single case screen$")
    public void userCreatesCaseWithInvalidDate() {
        clickOn(createCase.dcuMinRadioButton);
        clickOn(createCase.nextButton);
        dataInput.enterDayOfCorrespondenceReceived("29");
        dataInput.enterMonthOfCorrespondenceReceived("02");
        dataInput.enterYearOfCorrespondenceReceived("2019");
        clickOn(createCase.finishButton);
    }

    @When("^they create the bulk cases without adding a document$")
    public void userCreatesBulkCasesWithoutAddingADocument() {
        clickOn(createCase.dcuMinRadioButton);
        clickOn(createCase.nextButton);
        clickOn(createCase.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered the correspondence received date")
    public void assertThatDateReceivedErrorMessageIsShown() {
        createCase.assertDateReceivedNotEnteredErrorMessage();

    }

    @Then("^an error message should be displayed as I have entered an invalid date$")
    public void assertThatDateReceivedIsInvalidErrorMessageIsShown() {
        createCase.assertDateReceivedIsInvalidErrorMessage();
    }

    @Then("^an error message should be displayed informing the user that documents are mandatory$")
    public void assertThatDocumentsAreMandatoryErrorMessageIsShown() {
        createCase.assertDocumentsAreMandatoryErrorMessage();

    }

    @When("^I click the cancel button on the what type of correspondence page$")
    public void clickCancelButtonOnWhatTypeOfCorrespondencePage() {
        createCase.cancelAtWhatTypeOfCor();
    }

    @When("^I move to the When Was Correspondence Received Page$")
    public void clickCancelButtonOnWhenWasCorrespondenceReceivedPage() {
        createCase.getToWhenWasCorReceived();
    }

    @When("^I enter a blank date$")
    public void enterInvalidDateOnCaseCreateScreen() {
        createCase.enterNoDate();
    }

}

