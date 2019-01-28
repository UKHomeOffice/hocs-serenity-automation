package com.hocs.test.glue;

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
import net.serenitybdd.core.Serenity;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

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
                System.out.println(userView
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                userView = null;
                assumeNotNull(userView);
        }
    }

    @Given("^I create a single case \"([^\"]*)\"$")
    public void createACaseTypeSpecificCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.createDCUMinSingleCase();
                setSessionVariable("caseType").to(caseType);
                break;
            case "DCU N10":
                createCase.createDC10SingleCase();
                setSessionVariable("caseType").to(caseType);
                break;
            case "DCU TRO":
                createCase.createDCTROSingleCase();
                setSessionVariable("caseType").to(caseType);
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
        createCase.clickSubmitButton();
        page.sleep(500);
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
                System.out.println(ordinal
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                ordinal = null;
                assumeNotNull(ordinal);
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
                System.out.println(ordinal
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                ordinal = null;
                assumeNotNull(ordinal);
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
                System.out.println(ordinal
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                ordinal = null;
                assumeNotNull(ordinal);
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
                System.out.println(caseType
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                caseType = null;
                assumeNotNull(caseType);
        }

        createCase.clickNextButton();
        addDocuments.bulkUploadDocuments(cases);
        createCase.clickFinishButton();

    }

    @When("^I create a \"([^\"]*)\" case \"([^\"]*)\" a document$")
    public void createCaseWithDocument(String caseType, String document) {
        homepage.clickCreateSingleCase();

        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.clickDcuMinRadioButton();
                createCase.clickNextButton();
                break;
            case "DCU TRO":
                createCase.clickDcuTroRadioButton();
                createCase.clickNextButton();
                break;
            case "DCU TEN":
                createCase.clickDcuDtenRadioButton();
                createCase.clickNextButton();
                addDocuments.enterDispatchDeadlineDay(10);
                addDocuments.enterDispatchDeadlineMonth(10);
                addDocuments.enterDispatchDeadlineYear(10);
                addDocuments.enterDraftDeadlineDay(10);
                addDocuments.enterDraftDeadlineMonth(10);
                addDocuments.enterDraftDeadlineYear(10);
                break;
            default:
                System.out.println(caseType
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                caseType = null;
                assumeNotNull(caseType);
        }

        switch (document.toUpperCase()) {
            case "WITH":
                addDocuments.uploadDocument();
                createCase.clickFinishButton();
                break;
            case "WITHOUT":
                createCase.clickFinishButton();
                break;
            default:
                System.out.println(document
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                document = null;
                assumeNotNull(document);
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
        createCase.clickNextButton();
    }
}
