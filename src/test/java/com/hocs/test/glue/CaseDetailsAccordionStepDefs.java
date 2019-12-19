package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.accordion.CaseDetailsAccordion;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.draft.Draft;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkupFull;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CaseDetailsAccordionStepDefs extends Page {

    Homepage homepage;

    Workstacks workstacks;

    CaseDetailsAccordion caseDetailsAccordion;

    DataInput dataInput;

    MarkupFull markupFull;

    Draft draft;

    QAResponse qaResponse;

    PrivateOffice privateOffice;

    MinisterSignOff ministerSignOff;

    Dispatch dispatch;


    @When("^I move that case to the \"([^\"]*)\" stage$")
    public void moveCaseToNextStage(String stage) {

        switch (stage) {
            case "Markup":
                dataInput.completeDataInputStageAndStoreEnteredInformation();
                break;
            case "Initial Draft":
                moveCaseToNextStage("Markup");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                markupFull.completeMarkupStageAndStoreEnteredInformation();
                break;
            case "QA Response":
                moveCaseToNextStage("Initial Draft");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                draft.completeInitialDraftStageAndStoreEnteredInformation();
                break;
            case "Private Office Approval":
                moveCaseToNextStage("QA Response");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                qaResponse.completeQAResponseStageAndStoreEnteredInformation();
                break;
            case "Ministerial Sign Off":
                moveCaseToNextStage("Private Office Approval");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                privateOffice.completePrivateOfficeApprovalStageAndStoreEnteredInformation();
                break;
            case "Dispatch":
                moveCaseToNextStage("Ministerial Sign Off");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                ministerSignOff.completeMinisterialSignOffStageAndStoreEnteredInformation();
                break;
            case "Transfer to No10":
                moveCaseToNextStage("Dispatch");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                dispatch.completeDispatchStageAndStoreEnteredInformation();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @When("^I select the \"([^\"]*)\" button of the accordion$")
    public void selectAccordionButton(String stage) {
        switch (stage) {
            case "Data Input":
                clickOn(caseDetailsAccordion.dataInputAccordionButton);
                break;
            case "Markup":
                clickOn(caseDetailsAccordion.markupAccordionButton);
                break;
            case "Initial Draft":
                clickOn(caseDetailsAccordion.initialDraftAccordionButton);
                break;
            case "QA Response":
                clickOn(caseDetailsAccordion.qAResponseAccordionButton);
                break;
            case "Private Office Approval":
                clickOn(caseDetailsAccordion.privateOfficeApprovalAccordionButton);
                break;
            case "Ministerial Sign Off":
                clickOn(caseDetailsAccordion.ministerialSignOffAccordionButton);
                break;
            case "Dispatch":
                clickOn(caseDetailsAccordion.dispatchAccordionButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @Then("^the information shown should match what I entered at the \"([^\"]*)\" stage$")
    public void assertAccordionFieldsMatchInformationEnteredAt(String stage) {
        switch (stage) {
            case "Case Creation":
                caseDetailsAccordion.assertAccordionCorrespondenceReceivedDate();
                break;
            case "Data Input":
                caseDetailsAccordion.assertAccordionDataInputFields();
                break;
            case "Markup":
                caseDetailsAccordion.assertAccordionMarkupFields();
                break;
            case "Initial Draft":
                caseDetailsAccordion.assertAccordionInitialDraftFields();
                break;
            case "QA Response":
                caseDetailsAccordion.assertAccordionQAResponseFields();
                break;
            case "Private Office Approval":
                caseDetailsAccordion.assertAccordionPrivateOfficeApprovalFields();
                break;
            case "Ministerial Sign Off":
                caseDetailsAccordion.assertAccordionMinisterialSignOffFields();
                break;
            case "Dispatch":
                caseDetailsAccordion.assertAccordionDispatchFields();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }
}
