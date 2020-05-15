package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

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
import com.hocs.test.pages.ukvi.CaseDispatching;
import com.hocs.test.pages.ukvi.CaseDraft;
import com.hocs.test.pages.ukvi.CasePrivateOffice;
import com.hocs.test.pages.ukvi.CaseQA;
import com.hocs.test.pages.ukvi.CaseTriage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

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

    CaseTriage caseTriage;

    CaseDraft caseDraft;

    CaseQA caseQA;

    CasePrivateOffice casePrivateOffice;

    CaseDispatching caseDispatching;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        if (homepage.myCases.isVisible()) {
            homepage.getAndClaimCurrentCase();
        }
        String caseType = sessionVariableCalled("caseType");
        switch (caseType) {
            case "MIN":
            case "DTEN":
            case "TRO":
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
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "UKVI":
                switch (stage.toUpperCase()) {
                    case "CREATION":
                        caseCreation.moveCaseFromCaseCreationToCaseTriage();
                        break;
                    case "TRIAGE":
                        caseTriage.moveCaseFromCaseTriageToCaseDraft();
                        break;
                    case "DRAFT":
                        caseDraft.moveCaseFromCaseCreationToCaseQA();
                        break;
                    case "QA":
                        caseQA.moveCaseFromCaseQAToNextStage();
                        break;
                    case "PRIVATE OFFICE":
                        casePrivateOffice.moveCaseFromCasePrivateOfficeToCaseClosed();
                        break;
                    case "DISPATCH":
                        caseDispatching.moveCaseFromCaseDispatchingToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
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
                        iCompleteTheStage("INITIAL DRAFT");
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
                    case "CREATION":
                        createCase.createCaseOfType(caseType);
                        homepage.goHome();
                        break;
                    case "TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "CREATION");
                        iCompleteTheStage("CREATION");
                        break;
                    case "DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "TRIAGE");
                        iCompleteTheStage("TRIAGE");
                        break;
                    case "QA":
                        iCreateACaseAndMoveItToAStage(caseType, "DRAFT");
                        iCompleteTheStage("DRAFT");
                        break;
                    case "PRIVATE OFFICE":
                        iCreateACaseAndMoveItToAStage(caseType, "QA");
                        iCompleteTheStage("QA");
                        break;
                    case "DISPATCH":
                        moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage("UKVI", "B:REF","QA");
                        iCompleteTheStage("QA");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "PRIVATE OFFICE");
                        iCompleteTheStage("PRIVATE OFFICE");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I create a UKVI case  with {string} as the Business Area and {string} as the Reference Type and move it to the {string} stage")
    public void moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(String businessArea, String refType,
            String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                iCreateACaseAndMoveItToAStage("UKVI", "CREATION");
                homepage.getAndClaimCurrentCase();
                caseCreation.moveCaseWithSpecifiedBusinessAreaAndRefTypeToCaseTriageStage(businessArea, refType);
                break;
            case "DRAFT":
                moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "TRIAGE");
                iCompleteTheStage("TRIAGE");
                break;
            case "QA":
                moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "DRAFT");
                iCompleteTheStage("DRAFT");
                break;
            case "PRIVATE OFFICE":
            case "AWAITING DISPATCH":
                moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "QA");
                iCompleteTheStage("QA");
                break;
            case "CASE CLOSED":
                if (refType.toUpperCase().equals("M:REF")) {
                    moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "PRIVATE OFFICE");
                    iCompleteTheStage("PRIVATE OFFICE");
                } else {
                    moveNewUKVICaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType,
                            "AWAITING DISPATCH");
                    iCompleteTheStage("DISPATCH");
                }
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }
}
