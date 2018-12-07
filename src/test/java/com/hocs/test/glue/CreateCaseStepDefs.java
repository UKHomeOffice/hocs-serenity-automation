package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkUpDecision;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assume.assumeNotNull;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateCaseStepDefs {

    AddDocuments addDocuments;

    CreateCase createCase;

    SuccessfulCaseCreation successfulCaseCreation;

    DataInput dataInput;

    Homepage homepage;

    MarkUpDecision markUpDecision;

    Page page;

    RecordCorrespondentDetails recordCorrespondentDetails;


    @Given("^I am presented with \"([^\"]*)\"$")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                createCase.assertNoOptionsAvailable();
                break;
            default:
                fail(userView + " is not defined with CreateCaseStepDefs.iAmPresentedWith");
        }
    }


   /* public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                createCase.assertNoOptionsAvailable();
                break;
            default:
                fail(userView + " is not defined with CreateCaseStepDefs.iAmPresentedWith");
        }
    }*/



    @Given("^I create a single case \"([^\"]*)\"$")
    public void iCreateACaseTypeSpecificCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                break;
            case "DCU N10":
                createCase.createDC10SingleCase();
                break;
            case "DCU TRO":
                createCase.createDCTROSingleCase();
                break;
            default:
                System.out.println(caseType
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                caseType = null;
                assumeNotNull(caseType);
        }
    }


    @When("^I create a case$")
    public void aCaseIsCreated() {
        createCase.clickDcuMinRadioButton();
        createCase.clickNextButton();
        addDocuments.uploadDocument();
        page.clickSubmitButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^I captured the case reference number$")
    public void caseReferenceCaptured() {
        createCase.capturedCaseReferenceTest();
    }

    @When("^I create a case with <Topic>$")
    public void aCaseWithSpecificTopicIsCreated() {}

    @When("^I create a case with a <Primary Correspondent>$")
    public void aCaseWithSpecifiedPrimaryCorrespondantIsCreated() {}

    @Then("^the correspondence type is the \"([^\"]*)\" correspondent$")
    public void theCorrespondenceTypeIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                recordCorrespondentDetails.assertPrimaryCorrespondent();
                break;
            case "SECONDARY":
                break;
            default:
                fail("Please select PRIMARY or SECONDARY");
        }
    }

    @And("^a case has a \"([^\"]*)\" correspondent$")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                fail("Please select PRIMARY or SECONDARY");
        }
    }

    @Then("^the member is the \"([^\"]*)\" correspondent$")
    public void theMemberIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":

                break;
            case "SECONDARY":
                break;
            default:
                fail("Please select PRIMARY or SECONDARY");
        }

    }

    @When("^I bulk create (\\d+) \"([^\"]*)\" cases$")
    public void bulkCreateCases(int cases, String caseType) {
        homepage.clickCreateBulkCases();

        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.clickDcuMinRadioButton();
                break;
            case "DCU TRO":
                createCase.clickDcuTroRadioButton();
                break;
            default:
                fail(caseType + " is not defined in CreateCaseStepDefs.iCreateACaseADocument.");
        }

        page.clickNextButton();
        addDocuments.bulkUploadDocuments(cases);
        page.clickFinishButton();

    }

    @When("^I create a \"([^\"]*)\" case \"([^\"]*)\" a document$")
    public void createCaseWithDocument(String caseType, String document) {
        homepage.clickCreateSingleCase();

        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.clickDcuMinRadioButton();
                page.clickNextButton();
                break;
            case "DCU TRO":
                createCase.clickDcuTroRadioButton();
                page.clickNextButton();
                break;
            case "DCU TEN":
                createCase.clickDcuDtenRadioButton();
                page.clickNextButton();
                addDocuments.enterDispatchDeadlineDay(10);
                addDocuments.enterDispatchDeadlineMonth(10);
                addDocuments.enterDispatchDeadlineYear(10);
                addDocuments.enterDraftDeadlineDay(10);
                addDocuments.enterDraftDeadlineMonth(10);
                addDocuments.enterDraftDeadlineYear(10);
                break;
            default:
                fail(caseType + " is not defined in CreateCaseStepDefs.iCreateACaseADocument.");
        }

        switch (document.toUpperCase()) {
            case "WITH":
                addDocuments.uploadDocument();
                page.clickFinishButton();
                break;
            case "WITHOUT":
                page.clickFinishButton();
                break;
            default:
                fail("Please set " + document + " to be either WITH OR WITHOUT");
        }
    }

    @Then("^A case is created successfully$")
    public void aCaseIsCreatedSuccessfully() {
        successfulCaseCreation.assertCaseCreatedSuccess();
        successfulCaseCreation.getCaseReference();
    }

    @When("^I do not select a type of correspondence when creating a case$")
    public void correspondentTypeNotSelectedDuringCaseCreation() {
        homepage.clickCreateSingleCase();
        page.clickNextButton();
    }
}
