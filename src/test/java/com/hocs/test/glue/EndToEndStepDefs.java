package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.accordion.CaseDetailsAccordion;
import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.draft.Draft;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.markup.MarkupFull;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.qa_response.QAResponse;
import io.cucumber.java.en.And;

public class EndToEndStepDefs extends Page {

    Homepage homepage;

    CreateCase createCase;

    DataInput dataInput;

    MarkupFull markupFull;

    Draft draft;

    QAResponse qaResponse;

    PrivateOffice privateOffice;

    MinisterSignOff ministerSignOff;

    Dispatch dispatch;

    CaseDetailsAccordion caseDetailsAccordion;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        if (homepage.myCases.isVisible()) {
            homepage.getAndClaimCurrentCase();
        }
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                dataInput.moveCaseFromDataInputToMarkup();
                break;
            case "MARKUP":
                markupFull.moveCaseFromMarkupToInitialDraft();
                break;
            case "INITIAL DRAFT":
                draft.moveCaseFromInitialDraftToQaResponse();
                break;
            case "DTEN INITIAL DRAFT":
                draft.moveDTENCaseFromInitialDraftToQaResponse();
                break;
            case "QA RESPONSE":
                qaResponse.moveCaseFromQaResponseToPrivateOfficeApproval();
                break;
            case "PRIVATE OFFICE APPROVAL":
                privateOffice.moveCaseFromPrivateOfficeToMinisterSignOff();
                break;
            case "MINISTERIAL SIGN OFF":
                ministerSignOff.moveCaseFromMinisterToDispatch();
                break;
            case "DISPATCH":
                dispatch.completeDispatchStageAndMoveToCaseClosed();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @And("I create a {string} case and move it to the {string} stage")
    public void iCreateACaseAndMoveItToAStage(String caseType, String stage) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createDCUMinSingleCase();
                        homepage.goHome();
                        break;
                    case "MARKUP":
                        iCreateACaseAndMoveItToAStage(caseType, "DATA INPUT");
                        iCompleteTheStage("DATA INPUT");
                        break;
                    case "INITIAL DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP");
                        break;
                    case "QA RESPONSE":
                        iCreateACaseAndMoveItToAStage(caseType, "INITIAL DRAFT");
                        iCompleteTheStage("INITIAL DRAFT");
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        iCreateACaseAndMoveItToAStage(caseType, "QA RESPONSE");
                        iCompleteTheStage("QA RESPONSE");
                        break;
                    case "MINISTERIAL SIGN OFF":
                        iCreateACaseAndMoveItToAStage(caseType, "PRIVATE OFFICE APPROVAL");
                        iCompleteTheStage("PRIVATE OFFICE APPROVAL");
                        break;
                    case "DISPATCH":
                        iCreateACaseAndMoveItToAStage(caseType, "MINISTERIAL SIGN OFF");
                        iCompleteTheStage("MINISTERIAL SIGN OFF");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "DISPATCH");
                        iCompleteTheStage("DISPATCH");
                        homepage.getCurrentCase();
                        caseDetailsAccordion.assertThatAllocateToMeNotVisible();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createDCUTROSingleCase();
                        homepage.goHome();
                        break;
                    case "MARKUP":
                        iCreateACaseAndMoveItToAStage(caseType, "DATA INPUT");
                        iCompleteTheStage("DATA INPUT");
                        break;
                    case "INITIAL DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP");
                        break;
                    case "QA RESPONSE":
                        iCreateACaseAndMoveItToAStage(caseType, "INITIAL DRAFT");
                        iCompleteTheStage("INITIAL DRAFT");
                        break;
                    case "DISPATCH":
                        iCreateACaseAndMoveItToAStage(caseType, "QA RESPONSE");
                        iCompleteTheStage("QA RESPONSE");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "DISPATCH");
                        iCompleteTheStage("DISPATCH");
                        homepage.getCurrentCase();
                        caseDetailsAccordion.assertThatAllocateToMeNotVisible();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "DTEN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createDCU10SingleCase();
                        homepage.goHome();
                        break;
                    case "MARKUP":
                        iCreateACaseAndMoveItToAStage(caseType, "DATA INPUT");
                        iCompleteTheStage("DATA INPUT");
                        break;
                    case "INITIAL DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP");
                        break;
                    case "QA RESPONSE":
                        iCreateACaseAndMoveItToAStage(caseType, "INITIAL DRAFT");
                        iCompleteTheStage("DTEN INITIAL DRAFT");
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        iCreateACaseAndMoveItToAStage(caseType, "QA RESPONSE");
                        iCompleteTheStage("QA RESPONSE");
                        break;
                    case "DISPATCH":
                        iCreateACaseAndMoveItToAStage(caseType, "PRIVATE OFFICE APPROVAL");
                        iCompleteTheStage("PRIVATE OFFICE APPROVAL");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "DISPATCH");
                        iCompleteTheStage("DISPATCH");
                        homepage.getCurrentCase();
                        caseDetailsAccordion.assertThatAllocateToMeNotVisible();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

}
