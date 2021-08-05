package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.comp.COMPProgressCase;
import com.hocs.test.pages.dcu.Dispatch;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.dcu.DataInput;
import com.hocs.test.pages.dcu.InitialDraft;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.dcu.PrivateOfficeApproval;
import com.hocs.test.pages.dcu.QAResponse;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.ukvi.Creation;
import com.hocs.test.pages.ukvi.DispatchStages;
import com.hocs.test.pages.ukvi.Draft;
import com.hocs.test.pages.ukvi.QA;
import com.hocs.test.pages.ukvi.Triage;
import com.hocs.test.pages.wcs.WCSProgressCase;
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

    Dispatch dispatch;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    Workdays workdays;

    WCSProgressCase wcsProgressCase;

    COMPProgressCase compProgressCase;

    FOIProgressCase foiProgressCase;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        String caseType = sessionVariableCalled("caseType");
        switch (caseType) {
            case "MIN":
            case "DTEN":
            case "TRO":
                dashboard.getAndClaimCurrentCase();
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
                dashboard.getAndClaimCurrentCase();
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
                dashboard.getAndClaimCurrentCase();
                switch (stage.toUpperCase()) {
                    case "REGISTRATION (TO SERVICE TRIAGE)":
                        compProgressCase.moveCaseFromRegistrationToServiceTriage();
                        break;
                    case "SERVICE TRIAGE (TO SERVICE DRAFT)":
                        compProgressCase.moveCaseFromServiceTriageToServiceDraft();
                        break;
                    case "SERVICE TRIAGE (TO SERVICE ESCALATED)":
                        compProgressCase.moveCaseFromServiceTriageToServiceEscalated();
                        break;
                    case "SERVICE TRIAGE (TO CCH)":
                        compProgressCase.moveCaseFromServiceTriageToCCH();
                        break;
                    case "SERVICE DRAFT":
                        compProgressCase.moveCaseFromServiceDraftToServiceQA();
                        break;
                    case "SERVICE QA":
                        compProgressCase.moveCaseFromServiceQAToServiceSend();
                        break;
                    case "SERVICE SEND":
                        compProgressCase.moveCaseFromServiceSendToComplaintClosed();
                        break;
                    case "COMPLAINT CLOSED (TO CASE CLOSED)":
                        compProgressCase.moveCaseFromComplaintClosedToCaseClosed();
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "FOI":
                switch (stage.toUpperCase()) {
                    case "CASE CREATION":
                        foiProgressCase.moveCaseFromCaseCreationToKIMUAllocation();
                        break;
                    case "KIMU ALLOCATION":
                        foiProgressCase.moveCaseFromKIMUAllocationToAcceptance();
                        break;
                    case "ACCEPTANCE":
                        foiProgressCase.moveCaseFromAcceptanceToConsiderAndDraft();
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
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        waitForDashboard();
        RecordCaseData.resetDataRecords();
    }

    @And("I create a {string} case and move it to (the ){string}( stage)")
    public void iCreateACaseAndMoveItToAStage(String caseType, String stage) {
        switch (caseType.toUpperCase()) {
            case "MIN":
                switch (stage.toUpperCase()) {
                    case "DATA INPUT":
                        createCase.createCSCaseOfType(caseType);
                        goToDashboard();
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
                        createCase.createCSCaseOfType(caseType);
                        goToDashboard();
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
                        goToDashboard();
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
                        goToDashboard();
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
                createCase.createCSCaseOfType(caseType);
                goToDashboard();
                break;
            case "COMP":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        goToDashboard();
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
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE ESCALATED)");
                        break;
                    case "CCH":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO CCH)");
                        break;
                    case "SERVICE QA":
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
            case "FOI":
                switch (stage.toUpperCase()) {
                    case "CASE CREATION":
                        createCase.createFOICase();
                        goToDashboard();
                        break;
                    case "KIMU ALLOCATION":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE CREATION");
                        iCompleteTheStage("CASE CREATION");
                        break;
                    case "ACCEPTANCE":
                        iCreateACaseAndMoveItToAStage(caseType, "KIMU ALLOCATION");
                        iCompleteTheStage("KIMU ALLOCATION");
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
                goToDashboard();
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
