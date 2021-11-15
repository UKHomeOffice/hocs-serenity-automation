package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.complaints.ComplaintsProgressCase;
import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.DispatchStages;
import com.hocs.test.pages.mpam.Draft;
import com.hocs.test.pages.mpam.QA;
import com.hocs.test.pages.mpam.Triage;
import com.hocs.test.pages.wcs.WCSProgressCase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class EndToEndStepDefs extends BasePage {

    Dashboard dashboard;

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

    Workdays workdays;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    DCUProgressCase dcuProgressCase;

    WCSProgressCase wcsProgressCase;

    ComplaintsProgressCase complaintsProgressCase;

    FOIProgressCase foiProgressCase;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        if (!caseView.currentCaseIsLoaded()) {
            dashboard.goToDashboard();
            dashboard.waitForDashboard();
            dashboard.getCurrentCase();
        }
        if (caseView.caseCanBeAllocated()) {
            dashboard.claimCurrentCase();
            caseView.waitForCaseToLoad();
        }
        String caseType = sessionVariableCalled("caseType");
        switch (caseType) {
            case "MIN":
            case "DTEN":
            case "TRO":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        dcuProgressCase.moveCaseFromDataInputToMarkup();
                        break;
                    case "MARKUP":
                        dcuProgressCase.moveCaseFromMarkupToInitialDraft();
                        break;
                    case "MARKUP TO NRN CONFIRMATION":
                        dcuProgressCase.moveCaseFromMarkupToNRNConfirmation();
                        break;
                    case "MARKUP TO TRANSFER CONFIRMATION":
                        dcuProgressCase.moveCaseFromMarkupToTransferConfirmation();
                        break;
                    case "INITIAL DRAFT":
                        dcuProgressCase.moveCaseFromInitialDraftToQaResponse();
                        break;
                    case "QA RESPONSE":
                        dcuProgressCase.moveCaseFromQAResponseToPrivateOfficeApprovalOrDispatch();
                        break;
                    case "PRIVATE OFFICE APPROVAL":
                        dcuProgressCase.moveCaseFromPrivateOfficeApprovalToMinisterialSignOffOrDispatch();
                        break;
                    case "MINISTERIAL SIGN OFF":
                        dcuProgressCase.moveCaseFromMinisterialSignOffToDispatch();
                        break;
                    case "DISPATCH":
                        dcuProgressCase.moveCaseFromDispatchToCaseClosedOrCopyToNumber10();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                dashboard.waitForDashboard();
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
                    case "PRIVATE OFFICE (TO CASE CLOSED)":
                        dispatchStages.moveCaseFromPrivateOfficeToCaseClosed();
                        break;
                    case "PRIVATE OFFICE (TO AWAITING DISPATCH (LOCAL))":
                        dispatchStages.moveCaseFromPrivateOfficeToAwaitingDispatchLocal();
                        break;
                    case "PRIVATE OFFICE (TO AWAITING DISPATCH (MINISTERIAL))":
                        dispatchStages.moveCaseFromPrivateOfficeToAwaitingDispatchMinisterial();
                        break;
                    case "AWAITING DISPATCH":
                        dispatchStages.moveCaseFromAwaitingDispatchToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                dashboard.waitForDashboard();
                break;
            case "COMP":
            case "COMP2":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION (TO SERVICE TRIAGE)":
                        complaintsProgressCase.moveCaseFromCOMPRegistrationToServiceTriage();
                        break;
                    case "REGISTRATION (TO EX-GRATIA TRIAGE)":
                        complaintsProgressCase.moveCaseFromRegistrationToExGratiaTriage();
                        break;
                    case "REGISTRATION (TO MINOR MISCONDUCT TRIAGE)":
                        complaintsProgressCase.moveCaseFromRegistrationToMinorMisconductTriage();
                        break;
                    case "SERVICE TRIAGE (TO SERVICE DRAFT)":
                        complaintsProgressCase.moveCaseFromServiceTriageToServiceDraft();
                        break;
                    case "EX-GRATIA TRIAGE (TO EX-GRATIA RESPONSE DRAFT)":
                        complaintsProgressCase.moveCaseFromExGratiaTriageToExGratiaResponseDraft();
                        break;
                    case "MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT RESPONSE DRAFT)":
                        complaintsProgressCase.moveCaseFromMinorMisconductTriageToMinorMisconductResponseDraft();
                        break;
                    case "SERVICE TRIAGE (TO SERVICE ESCALATED)":
                        complaintsProgressCase.moveCaseFromServiceTriageToServiceEscalated();
                        break;
                    case "EX-GRATIA TRIAGE (TO EX-GRATIA ESCALATE)":
                        complaintsProgressCase.moveCaseFromExGratiaTriageToExGratiaEscalate();
                        break;
                    case "MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT ESCALATE)":
                        complaintsProgressCase.moveCaseFromMinorMisconductTriageToMinorMisconductEscalate();
                        break;
                    case "SERVICE TRIAGE (TO CCH)":
                        complaintsProgressCase.moveCaseFromServiceTriageToCCH();
                        break;
                    case "EX-GRATIA TRIAGE (TO CCH)":
                        complaintsProgressCase.moveCaseFromExGratiaTriageToCCH();
                        break;
                    case "MINOR MISCONDUCT TRIAGE (TO CCH)":
                        complaintsProgressCase.moveCaseFromMinorMisconductTriageToCCH();
                        break;
                    case "SERVICE DRAFT":
                        complaintsProgressCase.moveCaseFromServiceDraftToServiceQA();
                        break;
                    case "EX-GRATIA RESPONSE DRAFT":
                        complaintsProgressCase.moveCaseFromExGratiaResponseDraftToExGratiaQA();
                        break;
                    case "MINOR MISCONDUCT RESPONSE DRAFT":
                        complaintsProgressCase.moveCaseFromMinorMisconductResponseDraftToMinorMisconductQA();
                        break;
                    case "SERVICE QA":
                        complaintsProgressCase.moveCaseFromServiceQAToServiceSend();
                        break;
                    case "EX-GRATIA QA":
                        complaintsProgressCase.moveCaseFromExGratiaQAToExGratiaSend();
                        break;
                    case "MINOR MISCONDUCT QA":
                        complaintsProgressCase.moveCaseFromMinorMisconductQAToMinorMisconductSend();
                        break;
                    case "SERVICE SEND":
                        complaintsProgressCase.moveCaseFromServiceSendToComplaintClosed();
                        break;
                    case "EX-GRATIA SEND":
                        complaintsProgressCase.moveCaseFromExGratiaSendToComplaintClosed();
                        break;
                    case "MINOR MISCONDUCT SEND":
                        complaintsProgressCase.moveCaseFromMinorMisconductSendToComplaintClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                dashboard.waitForDashboard();
                break;
            case "IEDET":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION (TO TRIAGE)":
                        complaintsProgressCase.moveIEDETCaseFromRegistrationToTriage();
                        break;
                    case "TRIAGE (TO DRAFT)":
                        complaintsProgressCase.moveIEDETCaseFromTriageToDraft();
                        break;
                    case "DRAFT (TO SEND)":
                        complaintsProgressCase.moveIEDETCaseFromDraftToSend();
                        break;
                    case "SEND (TO CASE CLOSED)":
                        complaintsProgressCase.moveIEDETCaseFromSendToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                dashboard.waitForDashboard();
                break;
            case "SMC":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION (TO TRIAGE)":
                        complaintsProgressCase.moveSMCCaseFromRegistrationToTriage();
                        break;
                    case "TRIAGE (TO DRAFT)":
                        complaintsProgressCase.moveSMCCaseFromTriageToDraft();
                        break;
                    case "DRAFT (TO SEND)":
                        complaintsProgressCase.moveSMCCaseFromDraftToSend();
                        break;
                    case "SEND (TO CASE CLOSED)":
                        complaintsProgressCase.moveSMCCaseFromSendToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                dashboard.waitForDashboard();
                break;
            case "FOI":
                switch (stage.toUpperCase()) {
                    case "CASE CREATION":
                        foiProgressCase.moveCaseFromCaseCreationToAllocation();
                        break;
                    case "ALLOCATION":
                        foiProgressCase.moveCaseFromAllocationToAcceptance();
                        dashboard.waitForDashboard();
                        break;
                    case "ACCEPTANCE":
                        foiProgressCase.moveCaseFromAcceptanceToConsiderAndDraft();
                        dashboard.waitForDashboard();
                        break;
                    case "CONSIDER AND DRAFT":
                        foiProgressCase.moveCaseFromConsiderAndDraftToApproval();
                        break;
                    case "APPROVAL":
                        foiProgressCase.moveCaseFromApprovalToDispatch();
                        break;
                    case "DISPATCH":
                        foiProgressCase.moveCaseFromDispatchToSoftClose();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "WCS":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION (TO TRIAGE)":
                        wcsProgressCase.moveRegistrationCaseToTriage();
                        break;
                    case "REGISTRATION (TO ELIGIBILITY)":
                        wcsProgressCase.moveRegistrationCaseToEligibility();
                        break;
                    case "REGISTRATION (TO IDENTITY REJECTED)":
                        wcsProgressCase.moveRegistrationCaseToIdentityRejected();
                        break;
                    case "IDENTITY REJECTED (TO ARCHIVED)":
                        wcsProgressCase.moveIdentityRejectedCaseToArchivedIdentityRejected();
                        break;
                    case "IDENTITY REJECTED (TO TIER 1)":
                        wcsProgressCase.moveIdentityRejectedCaseToTier1IR();
                        break;
                    case "ELIGIBILITY (TO TRIAGE)":
                        wcsProgressCase.moveEligibilityCaseToTriage();
                        break;
                    case "ELIGIBILITY (TO ELIGIBILITY REJECTED)":
                        wcsProgressCase.moveEligibilityCaseToEligibilityRejected();
                        break;
                    case "ELIGIBILITY REJECTED (TO ARCHIVED)":
                        wcsProgressCase.moveEligibilityRejectedCaseToArchivedEligibilityRejected();
                        break;
                    case "ELIGIBILITY REJECTED (TO TIER 1)":
                        wcsProgressCase.moveEligibilityRejectedCaseToTier1ER();
                        break;
                    case "TRIAGE":
                        wcsProgressCase.moveTriageCaseToCasework();
                        break;
                    case "CASEWORK":
                        wcsProgressCase.moveCaseworkCaseToQA();
                        break;
                    case "QA":
                        wcsProgressCase.moveQACaseToPaymentPreOfferChecklist();
                        break;
                    case "PAYMENT PRE-OFFER CHECKLIST":
                        wcsProgressCase.movePaymentPreOfferChecklistCaseToOfferApproval();
                        break;
                    case "OFFER APPROVAL":
                        wcsProgressCase.moveOfferApprovalCaseToSendOffer();
                        break;
                    case "SEND OFFER (TO OFFER ACCEPTANCE)":
                        wcsProgressCase.moveSendOfferCaseToOfferAcceptance();
                        break;
                    case "SEND OFFER (TO NIL OFFER ACCEPTANCE)":
                        wcsProgressCase.moveSendOfferCaseToNilOfferAcceptance();
                        break;
                    case "OFFER ACCEPTANCE (TO PAYMENT PREPARATION)":
                        wcsProgressCase.moveOfferAcceptanceCaseToPaymentPreparation();
                        break;
                    case "OFFER ACCEPTANCE (TO TIER 1)":
                        wcsProgressCase.moveOfferAcceptanceCaseToTier1();
                        break;
                    case "PAYMENT PREPARATION":
                        wcsProgressCase.movePaymentPreparationCaseToPaymentApproval();
                        break;
                    case "PAYMENT APPROVAL":
                        wcsProgressCase.movePaymentApprovalCaseToSendPayment();
                        break;
                    case "SEND PAYMENT":
                        wcsProgressCase.moveSendPaymentCaseToAwaitingPaymentConfirmation();
                        break;
                    case "AWAITING PAYMENT CONFIRMATION":
                        wcsProgressCase.moveAwaitingPaymentConfirmationCaseToCompleteState();
                        break;
                    case "TIER 1":
                        wcsProgressCase.moveTier1CaseToTier2();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                dashboard.waitForDashboard();
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        RecordCaseData.resetDataRecords();
    }

    @And("I create a {string} case and move it to (the ){string}( stage)")
    public void iCreateACaseAndMoveItToAStage(String caseType, String stage) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
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
                    case "COPY TO NUMBER 10":
                        iGetAMINCaseAtTheDisptachStageThatShouldBeCopiedToNumber();
                        iCompleteTheStage("DISPATCH");
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
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
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
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
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
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
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
                    case "AWAITING DISPATCH (LOCAL)":
                        iCreateACaseAndMoveItToAStage(caseType, "PRIVATE OFFICE");
                        iCompleteTheStage("PRIVATE OFFICE (TO AWAITING DISPATCH (LOCAL))");
                        break;
                    case "AWAITING DISPATCH (MINISTERIAL)":
                        iCreateACaseAndMoveItToAStage(caseType, "PRIVATE OFFICE");
                        iCompleteTheStage("PRIVATE OFFICE (TO AWAITING DISPATCH (MINISTERIAL))");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "PRIVATE OFFICE");
                        iCompleteTheStage("PRIVATE OFFICE (TO CASE CLOSED)");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "MTS":
                createCase.createCSCaseOfType(caseType);
                dashboard.goToDashboard();
                break;
            case "COMP":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "SERVICE TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO SERVICE TRIAGE)");
                        break;
                    case "EX-GRATIA TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO EX-GRATIA TRIAGE)");
                        break;
                    case "MINOR MISCONDUCT TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO MINOR MISCONDUCT TRIAGE)");
                        break;
                    case "SERVICE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE DRAFT)");
                        break;
                    case "EX-GRATIA RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO EX-GRATIA RESPONSE DRAFT)");
                        break;
                    case "MINOR MISCONDUCT RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT RESPONSE DRAFT)");
                        break;
                    case "SERVICE ESCALATED":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE ESCALATED)");
                        break;
                    case "EX-GRATIA ESCALATE":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO EX-GRATIA ESCALATE)");
                        break;
                    case "MINOR MISCONDUCT ESCALATE":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT ESCALATE)");
                        break;
                    case "CCH (FROM SERVICE TRIAGE)":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO CCH)");
                        break;
                    case "CCH (FROM EX-GRATIA TRIAGE)":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO CCH)");
                        break;
                    case "CCH (FROM MINOR MISCONDUCT TRIAGE)":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO CCH)");
                        break;
                    case "SERVICE QA":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE DRAFT");
                        iCompleteTheStage("SERVICE DRAFT");
                        break;
                    case "EX-GRATIA QA":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA RESPONSE DRAFT");
                        iCompleteTheStage("EX-GRATIA RESPONSE DRAFT");
                        break;
                    case "MINOR MISCONDUCT QA":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT RESPONSE DRAFT");
                        iCompleteTheStage("MINOR MISCONDUCT RESPONSE DRAFT");
                        break;
                    case "SERVICE SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE QA");
                        iCompleteTheStage("SERVICE QA");
                        break;
                    case "EX-GRATIA SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA QA");
                        iCompleteTheStage("EX-GRATIA QA");
                        break;
                    case "MINOR MISCONDUCT SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT QA");
                        iCompleteTheStage("MINOR MISCONDUCT QA");
                        break;
                    case "COMPLAINT CLOSED (FROM SERVICE SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE SEND");
                        iCompleteTheStage("SERVICE SEND");
                        break;
                    case "COMPLAINT CLOSED (FROM EX-GRATIA SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA SEND");
                        iCompleteTheStage("EX-GRATIA SEND");
                        break;
                    case "COMPLAINT CLOSED (FROM MINOR MISCONDUCT SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT SEND");
                        iCompleteTheStage("MINOR MISCONDUCT SEND");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "COMP2":
                switch (stage.toUpperCase()) {
                    case "STAGE 2 REGISTRATION":
                        try {
                            complaintsProgressCase.attemptEscalateCOMPCaseToStage2();
                        } catch (Exception a) {
                            iCreateACaseAndMoveItToAStage("COMP", "COMPLAINT CLOSED (FROM SERVICE SEND)");
                            try {
                                complaintsProgressCase.attemptEscalateCOMPCaseToStage2();
                            } catch (Exception e) {
                                Assert.fail("Escalation hypertext not visible on retry");
                            }
                        }
                        waitABit(500);
                        createCase.createCOMP2Case();
                        dashboard.goToDashboard();
                        break;
                    case "STAGE 2 SERVICE TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO SERVICE TRIAGE)");
                        break;
                    case "STAGE 2 EX-GRATIA TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO EX-GRATIA TRIAGE)");
                        break;
                    case "STAGE 2 MM TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO MINOR MISCONDUCT TRIAGE)");
                        break;
                    case "STAGE 2 SERVICE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE DRAFT)");
                        break;
                    case "STAGE 2 EX-GRATIA RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO EX-GRATIA RESPONSE DRAFT)");
                        break;
                    case "STAGE 2 MM RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT RESPONSE DRAFT)");
                        break;
                    case "STAGE 2 SERVICE QA":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE DRAFT");
                        iCompleteTheStage("SERVICE DRAFT");
                        break;
                    case "STAGE 2 EX-GRATIA QA":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA RESPONSE DRAFT");
                        iCompleteTheStage("EX-GRATIA RESPONSE DRAFT");
                        break;
                    case "STAGE 2 MM QA":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM RESPONSE DRAFT");
                        iCompleteTheStage("MINOR MISCONDUCT RESPONSE DRAFT");
                        break;
                    case "STAGE 2 SERVICE SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE QA");
                        iCompleteTheStage("SERVICE QA");
                        break;
                    case "STAGE 2 EX-GRATIA SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA QA");
                        iCompleteTheStage("EX-GRATIA QA");
                        break;
                    case "STAGE 2 MM SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM QA");
                        iCompleteTheStage("MINOR MISCONDUCT QA");
                        break;
                    case "STAGE 2 COMPLAINT CLOSED (FROM STAGE 2 SERVICE SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE SEND");
                        iCompleteTheStage("SERVICE SEND");
                        break;
                    case "STAGE 2 COMPLAINT CLOSED (FROM STAGE 2 EX-GRATIA SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA SEND");
                        iCompleteTheStage("EX-GRATIA SEND");
                        break;
                    case "STAGE 2 COMPLAINT CLOSED (FROM STAGE 2 MM SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM SEND");
                        iCompleteTheStage("MINOR MISCONDUCT SEND");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "IEDET":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO TRIAGE)");
                        break;
                    case "DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "TRIAGE");
                        iCompleteTheStage("TRIAGE (TO DRAFT)");
                        break;
                    case "SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "DRAFT");
                        iCompleteTheStage("DRAFT (TO SEND)");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "SEND");
                        iCompleteTheStage("SEND (TO CASE CLOSED)");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "SMC":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO TRIAGE)");
                        break;
                    case "DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "TRIAGE");
                        iCompleteTheStage("TRIAGE (TO DRAFT)");
                        break;
                    case "SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "DRAFT");
                        iCompleteTheStage("DRAFT (TO SEND)");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "SEND");
                        iCompleteTheStage("SEND (TO CASE CLOSED)");
                        break;
                }
                break;
            case "FOI":
                switch (stage.toUpperCase()) {
                    case "CASE CREATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "ALLOCATION":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE CREATION");
                        iCompleteTheStage("CASE CREATION");
                        break;
                    case "ACCEPTANCE":
                        iCreateACaseAndMoveItToAStage(caseType, "ALLOCATION");
                        iCompleteTheStage("ALLOCATION");
                        break;
                    case "CONSIDER AND DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "ACCEPTANCE");
                        iCompleteTheStage("ACCEPTANCE");
                        break;
                    case "APPROVAL":
                        iCreateACaseAndMoveItToAStage(caseType, "CONSIDER AND DRAFT");
                        iCompleteTheStage("CONSIDER AND DRAFT");
                        break;
                    case "DISPATCH":
                        iCreateACaseAndMoveItToAStage(caseType, "APPROVAL");
                        iCompleteTheStage("APPROVAL");
                        break;
                    case "SOFT CLOSE":
                        iCreateACaseAndMoveItToAStage(caseType, "DISPATCH");
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

    @And("I get a {string} case at (the ){string}( stage)")
    public void iGetACaseAtAStage(String caseType, String stage) {
        iCreateACaseAndMoveItToAStage(caseType, stage);
        if (!stage.equalsIgnoreCase("CASE CLOSED")) {
            dashboard.getAndClaimCurrentCase();
        } else {
            dashboard.getCurrentCase();
        }
    }

    @And("I get a MIN case at the Dispatch stage that should be copied to Number 10")
    public void iGetAMINCaseAtTheDisptachStageThatShouldBeCopiedToNumber() {
        createCase.createCSCaseOfType("MIN");
        confirmationScreens.goToCaseFromSuccessfulCreationScreen();
        dashboard.claimCurrentCase();
        dcuProgressCase.moveCaseFromDataInputToMarkupWithCopyToNumber10();
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromMarkupToInitialDraft();
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromInitialDraftToPrivateOfficeApproval();
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromPrivateOfficeApprovalToMinisterialSignOffOrDispatch();
        dashboard.getAndClaimCurrentCase();
        dcuProgressCase.moveCaseFromMinisterialSignOffToDispatch();
        dashboard.getAndClaimCurrentCase();
        RecordCaseData.resetDataRecords();
    }

    @When("I create a MPAM case with {string} as the Business Area and {string} as the Reference Type and move it to the "
            + "{string} stage")
    public void moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(String businessArea, String refType,
            String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                iCreateACaseAndMoveItToAStage("MPAM", "CREATION");
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedValuesToTriageStage(businessArea, refType, "Standard", "Home Secretary");
                dashboard.waitForDashboard();
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
                if (refType.equalsIgnoreCase("MINISTERIAL")) {
                    moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "PRIVATE OFFICE");
                    iCompleteTheStage("PRIVATE OFFICE (TO CASE CLOSED)");
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
                createCase.createCSCaseOfTypeWithSetCorrespondenceReceivedDate("MPAM", workdays.getDateXWorkdaysAgo(20));
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedValuesToTriageStage("Windrush", "Ministerial", "Immediate", "Home Secretary");
                dashboard.waitForDashboard();
                break;
            case "DRAFT":
                moveHighPriorityNewMPAMCaseToStage("TRIAGE");
                iCompleteTheStage("TRIAGE");
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @And("I move the case/claim to the {string} stage")
    public void getCaseToSpecifiedStage(String stage) {
        switch (stage.toUpperCase()) {
            case "IDENTITY REJECTED":
                iCompleteTheStage("Registration (to Identity Rejected)");
                break;
            case "TIER 1 REVIEW (IR)":
                getCaseToSpecifiedStage("Identity Rejected");
                iCompleteTheStage("Identity Rejected (to Tier 1)");
                break;
            case "ARCHIVED IDENTITY REJECTED":
                getCaseToSpecifiedStage("Identity Rejected");
                iCompleteTheStage("Identity Rejected (to Archived)");
                break;
            case "ELIGIBILITY":
                iCompleteTheStage("Registration (to Eligibility)");
                break;
            case "ELIGIBILITY REJECTED":
                getCaseToSpecifiedStage("Eligibility");
                iCompleteTheStage("Eligibility (to Eligibility Rejected)");
                break;
            case "TIER 1 REVIEW (ER)":
                getCaseToSpecifiedStage("Eligibility Rejected");
                iCompleteTheStage("Eligibility Rejected (to Tier 1)");
                break;
            case "ARCHIVED ELIGIBILITY REJECTED":
                getCaseToSpecifiedStage("Eligibility Rejected");
                iCompleteTheStage("Eligibility Rejected (to Archived)");
                break;
            case "TRIAGE":
                iCompleteTheStage("Registration (to Triage)");
                break;
            case "CASEWORK":
                getCaseToSpecifiedStage("Triage");
                iCompleteTheStage("Triage");
                break;
            case "QA":
                getCaseToSpecifiedStage("Casework");
                iCompleteTheStage("Casework");
                break;
            case "PAYMENT PRE-OFFER CHECKLIST":
                getCaseToSpecifiedStage("QA");
                iCompleteTheStage("QA");
                break;
            case "OFFER APPROVAL":
                getCaseToSpecifiedStage("Payment Pre-offer Checklist");
                iCompleteTheStage("Payment Pre-offer Checklist");
                break;
            case "SEND OFFER":
                getCaseToSpecifiedStage("Offer Approval");
                iCompleteTheStage("Offer Approval");
                break;
            case "OFFER ACCEPTANCE":
                getCaseToSpecifiedStage("Send Offer");
                iCompleteTheStage("Send Offer (to Offer Acceptance)");
                break;
            case "NIL OFFER ACCEPTANCE":
                getCaseToSpecifiedStage("Send Offer");
                iCompleteTheStage("Send Offer (to Nil Offer Acceptance)");
                break;
            case "TIER 1":
                getCaseToSpecifiedStage("Offer Acceptance");
                iCompleteTheStage("Offer Acceptance (to Tier 1)");
                break;
            case "TIER 2":
                getCaseToSpecifiedStage("Tier 1");
                iCompleteTheStage("Tier 1");
                break;
            case "PAYMENT PREPARATION":
                getCaseToSpecifiedStage("Offer Acceptance");
                iCompleteTheStage("Offer Acceptance (to Payment Preparation)");
                break;
            case "PAYMENT APPROVAL":
                getCaseToSpecifiedStage("Payment Preparation");
                iCompleteTheStage("Payment Preparation");
                break;
            case "SEND PAYMENT":
                getCaseToSpecifiedStage("Payment Approval");
                iCompleteTheStage("Payment Approval");
                break;
            case "AWAITING PAYMENT CONFIRMATION":
                getCaseToSpecifiedStage("Send Payment");
                iCompleteTheStage("Send Payment");
                break;
            case "CLOSED":
                getCaseToSpecifiedStage("Awaiting Payment Confirmation");
                iCompleteTheStage("Awaiting Payment Confirmation");
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        if (!stage.equalsIgnoreCase("CLOSED")) {
            dashboard.getAndClaimCurrentCase();
        }
    }
}
