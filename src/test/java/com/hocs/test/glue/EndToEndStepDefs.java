package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.ukvi.CaseCreation;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import io.cucumber.java.en.And;

public class EndToEndStepDefs extends BasePage {

    Homepage homepage;

    CreateCase createCase;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff ministerialSignOff;

    Dispatch dispatch;

    CaseCreation caseCreation;

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
                markup.moveCaseFromMarkupToInitialDraft();
                break;
            case "INITIAL DRAFT":
                initialDraft.moveCaseFromInitialDraftToQaResponse();
                break;
            case "QA RESPONSE":
                qaResponse.moveCaseFromQaResponseToPrivateOfficeApproval();
                break;
            case "PRIVATE OFFICE APPROVAL":
                privateOfficeApproval.moveCaseFromPrivateOfficeToMinisterSignOff();
                break;
            case "MINISTERIAL SIGN OFF":
                ministerialSignOff.moveCaseFromMinisterToDispatch();
                break;
            case "DISPATCH":
                dispatch.moveCaseFromDispatchToCaseClosed();
                break;
            case "CASE CREATION":
                caseCreation.moveCaseFromCaseCreationToCaseTriage();
                break;
            case "CASE TRIAGE":
                break;
            case "CASE DRAFTING":
                break;
            case "CASE QA":
                break;
            case "CASE PRIVATE OFFICE":
                break;
            case "CASE DISPATCHING":
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
                        createCase.createCaseOfType(caseType);
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
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createCaseOfType(caseType);
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
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "DTEN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createCaseOfType(caseType);
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
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "UKVI":
                switch (stage.toUpperCase()) {
                    case "CASE CREATION":
                        createCase.createCaseOfType(caseType);
                        homepage.goHome();
                        break;
                    case "CASE TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE CREATION");
                        iCompleteTheStage("CASE CREATION");
                        break;
                    case "CASE DRAFTING":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE TRIAGE");
                        iCompleteTheStage("CASE TRIAGE");
                        break;
                    case "CASE QA":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE DRAFTING");
                        iCompleteTheStage("CASE DRAFTING");
                        break;
                    case "CASE PRIVATE OFFICE":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE QA");
                        iCompleteTheStage("QA RESPONSE");
                        break;
                    case "CASE DISPATCHING":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE PRIVATE OFFICE");
                        iCompleteTheStage("PRIVATE OFFICE APPROVAL");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE DISPATCHING");
                        iCompleteTheStage("DISPATCH");
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
