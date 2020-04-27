package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.DCUCaseDetailsAccordion;
import com.hocs.test.pages.DCU_Workflow.DataInput;
import com.hocs.test.pages.DCU_Workflow.Dispatch;
import com.hocs.test.pages.DCU_Workflow.InitialDraft;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.DCU_Workflow.Markup_FullFlow;
import com.hocs.test.pages.DCU_Workflow.MinisterialSignOff;
import com.hocs.test.pages.DCU_Workflow.PrivateOfficeApproval;
import com.hocs.test.pages.DCU_Workflow.QAResponse;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CaseDetailsAccordionStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    DCUCaseDetailsAccordion DCUCaseDetailsAccordion;

    DataInput dataInput;

    Markup_FullFlow markupFullFlow;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff ministerialSignOff;

    Dispatch dispatch;


    @When("I move that case to the {string} stage and record all entered information")
    public void moveCaseToNextStage(String stage) {
        switch (stage) {
            case "Markup":
                dataInput.completeDataInputStageAndStoreEnteredInformation();
                break;
            case "Initial Draft":
                moveCaseToNextStage("Markup");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                markupFullFlow.completeMarkupStageAndStoreEnteredInformation();
                break;
            case "QA Response":
                moveCaseToNextStage("Initial Draft");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                initialDraft.completeInitialDraftStageAndStoreEnteredInformation();
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
                privateOfficeApproval.completePrivateOfficeApprovalStageAndStoreEnteredInformation();
                break;
            case "Dispatch":
                moveCaseToNextStage("Ministerial Sign Off");
                homepage.getCurrentCase();
                workstacks.clickAllocateToMeButton();
                ministerialSignOff.completeMinisterialSignOffStageAndStoreEnteredInformation();
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

    @When("I select the {string} button of the accordion")
    public void selectAccordionButton(String stage) {
        switch (stage) {
            case "Data Input":
                safeClickOn(DCUCaseDetailsAccordion.dataInputAccordionButton);
                break;
            case "Markup":
                safeClickOn(DCUCaseDetailsAccordion.markupAccordionButton);
                break;
            case "Initial Draft":
                safeClickOn(DCUCaseDetailsAccordion.initialDraftAccordionButton);
                break;
            case "QA Response":
                safeClickOn(DCUCaseDetailsAccordion.qAResponseAccordionButton);
                break;
            case "Private Office Approval":
                safeClickOn(DCUCaseDetailsAccordion.privateOfficeApprovalAccordionButton);
                break;
            case "Ministerial Sign Off":
                safeClickOn(DCUCaseDetailsAccordion.ministerialSignOffAccordionButton);
                break;
            case "Dispatch":
                safeClickOn(DCUCaseDetailsAccordion.dispatchAccordionButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @Then("the information shown should match what I entered at the {string} stage")
    public void assertAccordionFieldsMatchInformationEnteredAt(String stage) {
        switch (stage) {
            case "Case Creation":
                DCUCaseDetailsAccordion.assertAccordionCorrespondenceReceivedDate();
                break;
            case "Data Input":
                DCUCaseDetailsAccordion.assertAccordionDataInputFields();
                break;
            case "Markup":
                DCUCaseDetailsAccordion.assertAccordionMarkupFields();
                break;
            case "Initial Draft":
                DCUCaseDetailsAccordion.assertAccordionInitialDraftFields();
                break;
            case "QA Response":
                DCUCaseDetailsAccordion.assertAccordionQAResponseFields();
                break;
            case "Private Office Approval":
                DCUCaseDetailsAccordion.assertAccordionPrivateOfficeApprovalFields();
                break;
            case "Ministerial Sign Off":
                DCUCaseDetailsAccordion.assertAccordionMinisterialSignOffFields();
                break;
            case "Dispatch":
                DCUCaseDetailsAccordion.assertAccordionDispatchFields();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }
}
