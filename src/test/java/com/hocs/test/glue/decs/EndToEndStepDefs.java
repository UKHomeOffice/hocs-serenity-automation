package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.Workdays;
import com.hocs.test.pages.comp.Registration;
import com.hocs.test.pages.comp.ServiceDraft;
import com.hocs.test.pages.comp.ServiceQA;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.ukvi.Creation;
import com.hocs.test.pages.ukvi.DispatchStages;
import com.hocs.test.pages.ukvi.Draft;
import com.hocs.test.pages.ukvi.QA;
import com.hocs.test.pages.ukvi.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class EndToEndStepDefs extends BasePage {

    Dashboard dashboard;

    CreateCase createCase;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff ministerialSignOff;

    com.hocs.test.pages.dcu.Dispatch dispatch;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    Workdays workdays;

    Registration registration;

    ServiceDraft serviceDraft;

    ServiceQA serviceQA;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        dashboard.getAndClaimCurrentCase();
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
                    case "MARKUP TO NRN CONFIRMATION":
                        markup.moveCaseFromMarkupToNRNConfirmation();
                        break;
                    case "MARKUP TO TRANSFER CONFIRMATION":
                        markup.moveCaseFromMarkupToTransferConfirmation();
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
            case "MPAM":
                switch (stage.toUpperCase()) {
                    case "CREATION":
                        creation.moveCaseFromCreationToTriage();
                        break;
                    case "TRIAGE":
                        triage.moveCaseFromTriageToDraft();
                        break;
                    case "DRAFT":
                        draft.moveCaseFromDraftToQA();
                        break;
                    case "QA":
                        qa.moveCaseFromQAToNextStage();
                        break;
                    case "PRIVATE OFFICE":
                        dispatchStages.moveCaseFromPrivateOfficeToCaseClosed();
                        break;
                    case "AWAITING DISPATCH":
                        dispatchStages.moveCaseFromAwaitingDispatchToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "COMP":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION (TO SERVICE TRIAGE)":
                        registration.moveCaseFromRegistrationToServiceTriage();
                        break;
                    case "SERVICE TRIAGE (TO SERVICE DRAFT)":
                        serviceTriage.moveCaseFromServiceTriageToServiceDraft();
                        break;
                    case "SERVICE TRIAGE (TO SERVICE ESCALATED)":
                        serviceTriage.moveCaseFromServiceTriageToServiceEscalated();
                        break;
                    case "SERVICE TRIAGE (TO CCH)":
                        serviceTriage.moveCaseFromServiceTriageToCCH();
                        break;
                    case "SERVICE DRAFT":
                        serviceDraft.moveCaseFromServiceDraftToServiceQA();
                        break;
                    case "SERVICE QA":
                        serviceQA.moveCaseFromServiceQAToServiceSend();
                        break;
                    case "SERVICE SEND":
                        serviceSend.moveCaseFromServiceSendToComplaintClosed();
                        break;
                    case "COMPLAINT CLOSED (TO CASE CLOSED)":
                        complaintClosed.moveCaseFromComplaintClosedToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        waitForDashboard();
    }

    @And("I create a {string} case and move it to the {string} stage")
    public void iCreateACaseAndMoveItToAStage(String caseType, String stage) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createCaseOfType(caseType);
                        goToDECSDashboard();
                        break;
                    case "MARKUP":
                        iCreateACaseAndMoveItToAStage(caseType, "DATA INPUT");
                        iCompleteTheStage("DATA INPUT");
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP TO NRN CONFIRMATION");
                        break;
                    case "TRANSFER CONFIRMATION":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP TO TRANSFER CONFIRMATION");
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
                        goToDECSDashboard();
                        break;
                    case "MARKUP":
                        iCreateACaseAndMoveItToAStage(caseType, "DATA INPUT");
                        iCompleteTheStage("DATA INPUT");
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP TO NRN CONFIRMATION");
                        break;
                    case "TRANSFER CONFIRMATION":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP TO TRANSFER CONFIRMATION");
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
                        goToDECSDashboard();
                        break;
                    case "MARKUP":
                        iCreateACaseAndMoveItToAStage(caseType, "DATA INPUT");
                        iCompleteTheStage("DATA INPUT");
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP TO NRN CONFIRMATION");
                        break;
                    case "TRANSFER CONFIRMATION":
                        iCreateACaseAndMoveItToAStage(caseType, "MARKUP");
                        iCompleteTheStage("MARKUP TO TRANSFER CONFIRMATION");
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
            case "MPAM":
                switch (stage.toUpperCase()) {
                    case "CREATION":
                        createCase.createCaseOfType(caseType);
                        goToDECSDashboard();
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
                    case "AWAITING DISPATCH":
                        moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage("UKVI", "Official", "QA");
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
            case "MTS":
                createCase.createCaseOfType(caseType);
                goToDECSDashboard();
                break;
            case "COMP":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCaseOfType(caseType);
                        goToDECSDashboard();
                        break;
                    case "SERVICE TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO SERVICE TRIAGE)");
                        break;
                    case "SERVICE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE DRAFT)");
                        break;
                    case "SERVICE ESCALATED":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE ");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE ESCALATED)");
                        break;
                    case "CCH":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE ");
                        iCompleteTheStage("SERVICE TRIAGE (TO CCH)");
                        break;
                    case "SERIVCE QA":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE DRAFT");
                        iCompleteTheStage("SERVICE DRAFT");
                        break;
                    case "SERVICE SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE QA");
                        iCompleteTheStage("SERVICE QA");
                        break;
                    case "COMPLAINT CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE SEND");
                        iCompleteTheStage("SERVICE SEND");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "COMPLAINT CLOSED");
                        iCompleteTheStage("COMPLAINT CLOSED (TO CASE CLOSED)");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("I create a MPAM case with {string} as the Business Area and {string} as the Reference Type and move it to the "
            + "{string} stage")
    public void moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(String businessArea, String refType,
            String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                iCreateACaseAndMoveItToAStage("MPAM", "CREATION");
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedBusinessAreaAndRefTypeToTriageStage(businessArea, refType);
                waitForDashboard();
                break;
            case "DRAFT":
                moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "TRIAGE");
                iCompleteTheStage("TRIAGE");
                break;
            case "QA":
                moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "DRAFT");
                iCompleteTheStage("DRAFT");
                break;
            case "PRIVATE OFFICE":
            case "AWAITING DISPATCH":
                moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "QA");
                iCompleteTheStage("QA");
                break;
            case "CASE CLOSED":
                if (refType.toUpperCase().equals("MINISTERIAL")) {
                    moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "PRIVATE OFFICE");
                    iCompleteTheStage("PRIVATE OFFICE");
                } else {
                    moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType,
                            "AWAITING DISPATCH");
                    iCompleteTheStage("AWAITING DISPATCH");
                }
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @When("I create a MPAM case with {string} as the Reference Type and move it to the {string} stage")
    public void moveNewMPAMCaseWithSpecifiedReferenceTypeToStage(String refType,
            String stage) {
        moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage("UKVI", refType,
                stage);

    }

    @When("I create a high priority MPAM case and move it to the {string} stage")
    public void moveHighPriorityNewMPAMCaseToStage(String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                createCase.createCaseWithSetCorrespondenceReceivedDate("MPAM", workdays.getDateXWorkdaysAgo(20));
                goToDECSDashboard();
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedUrgencyAndRefTypeToTriageStage("Immediate", "Ministerial");
                waitForDashboard();
                break;
            case "DRAFT":
                moveHighPriorityNewMPAMCaseToStage("TRIAGE");
                iCompleteTheStage("TRIAGE");
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }
}
