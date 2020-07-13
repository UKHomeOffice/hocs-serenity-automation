package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.Workstacks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DCUCaseDetailsAccordionStepDefs extends BasePage {

    Homepage homepage;

    Workstacks workstacks;

    AccordionDCU accordionDCU;

    DataInput dataInput;

    Markup markup;

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
                markup.completeMarkupStageAndStoreEnteredInformation();
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
                safeClickOn(accordionDCU.dataInputAccordionButton);
                break;
            case "Markup":
                safeClickOn(accordionDCU.markupAccordionButton);
                break;
            case "Initial Draft":
                safeClickOn(accordionDCU.initialDraftAccordionButton);
                break;
            case "QA Response":
                safeClickOn(accordionDCU.qAResponseAccordionButton);
                break;
            case "Private Office Approval":
                safeClickOn(accordionDCU.privateOfficeApprovalAccordionButton);
                break;
            case "Ministerial Sign Off":
                safeClickOn(accordionDCU.ministerialSignOffAccordionButton);
                break;
            case "Dispatch":
                safeClickOn(accordionDCU.dispatchAccordionButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @Then("the information shown should match what I entered at the {string} stage")
    public void assertAccordionFieldsMatchInformationEnteredAt(String stage) {
        switch (stage) {
            case "Case Creation":
                accordionDCU.assertAccordionCorrespondenceReceivedDate();
                break;
            case "Data Input":
                accordionDCU.assertAccordionDataInputFields();
                break;
            case "Markup":
                accordionDCU.assertAccordionMarkupFields();
                break;
            case "Initial Draft":
                accordionDCU.assertAccordionInitialDraftFields();
                break;
            case "QA Response":
                accordionDCU.assertAccordionQAResponseFields();
                break;
            case "Private Office Approval":
                accordionDCU.assertAccordionPrivateOfficeApprovalFields();
                break;
            case "Ministerial Sign Off":
                accordionDCU.assertAccordionMinisterialSignOffFields();
                break;
            case "Dispatch":
                accordionDCU.assertAccordionDispatchFields();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }
}