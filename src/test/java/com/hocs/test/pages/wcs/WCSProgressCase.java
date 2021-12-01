package com.hocs.test.pages.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;

public class WCSProgressCase extends BasePage {

    CreateCase createCase;
    
    Dashboard dashboard;

    ClaimSchema claimSchema;

    Registration registration;

    Rejected rejected;

    Eligibility eligibility;

    Triage triage;

    Casework casework;

    QAOffer qaOffer;

    PaymentPreOfferChecklist paymentPreOfferChecklist;

    OfferApproval offerApproval;

    SendOffer sendOffer;

    OfferAcceptance offerAcceptance;

    Tier1Review tier1Review;

    Tier1AwaitingResponse tier1AwaitingResponse;

    PaymentPreparation paymentPreparation;

    PaymentApproval paymentApproval;

    SendPayment sendPayment;

    AwaitingPaymentConfirmation awaitingPaymentConfirmation;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createWCSCase();
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheWCSStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "IDENTITY REJECTED":
            case "ELIGIBILITY":
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "TIER 1 REVIEW (IR)":
            case "ARCHIVED IDENTITY REJECTED":
                precedingStage = "IDENTITY REJECTED";
                break;
            case "ELIGIBILITY REJECTED":
                precedingStage = "ELIGIBILITY";
                break;
            case "TIER 1 REVIEW (ER)":
            case "ARCHIVED ELIGIBILITY REJECTED":
                precedingStage = "ELIGIBILITY REJECTED";
                break;
            case "CASEWORK":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "CASEWORK";
                break;
            case "PAYMENT PRE-OFFER CHECKLIST":
                precedingStage = "QA";
                break;
            case "OFFER APPROVAL":
                precedingStage = "PAYMENT PRE-OFFER CHECKLIST";
                break;
            case "SEND OFFER":
                precedingStage = "OFFER APPROVAL";
                break;
            case "OFFER ACCEPTANCE":
            case "NIL OFFER ACCEPTANCE":
                precedingStage = "SEND OFFER";
                break;
            case "TIER 1":
            case "PAYMENT PREPARATION":
                precedingStage = "OFFER ACCEPTANCE";
                break;
            case "TIER 2":
                precedingStage = "TIER 1";
                break;
            case "PAYMENT APPROVAL":
                precedingStage = "PAYMENT PREPARATION";
                break;
            case "SEND PAYMENT":
                precedingStage = "PAYMENT APPROVAL";
                break;
            case "AWAITING PAYMENT CONFIRMATION":
                precedingStage = "SEND PAYMENT";
                break;
            case "CLOSED":
                precedingStage = "AWAITING PAYMENT CONFIRMATION";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheWCSStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                switch (targetStage.toUpperCase()) {
                    case "TRIAGE":
                    case "HAPPY PATH":
                        moveRegistrationCaseToTriage();
                        break;
                    case "ELIGIBILITY":
                        moveRegistrationCaseToEligibility();
                        break;
                    case "IDENTITY REJECTED":
                        moveRegistrationCaseToIdentityRejected();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "IDENTITY REJECTED":
                switch (targetStage.toUpperCase()) {
                    case "ARCHIVE IDENTITY REJECTED":
                        moveIdentityRejectedCaseToArchivedIdentityRejected();
                        break;
                    case "TIER 1 REVIEW (IR)":
                        moveIdentityRejectedCaseToTier1IR();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "ELIGIBILITY":
                switch (targetStage.toUpperCase()) {
                    case "TRIAGE":
                        moveEligibilityCaseToTriage();
                        break;
                    case "ELIGIBILITY REJECTED":
                        moveEligibilityCaseToEligibilityRejected();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "ELIGIBILITY REJECTED":
                switch (targetStage.toUpperCase()) {
                    case "ARCHIVED ELIGIBILITY REJECTED":
                        moveEligibilityRejectedCaseToArchivedEligibilityRejected();
                        break;
                    case "TIER 1 REVIEW (ER)":
                        moveEligibilityRejectedCaseToTier1ER();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "TRIAGE":
                moveTriageCaseToCasework();
                break;
            case "CASEWORK":
                moveCaseworkCaseToQA();
                break;
            case "QA":
                moveQACaseToPaymentPreOfferChecklist();
                break;
            case "PAYMENT PRE-OFFER CHECKLIST":
                movePaymentPreOfferChecklistCaseToOfferApproval();
                break;
            case "OFFER APPROVAL":
                moveOfferApprovalCaseToSendOffer();
                break;
            case "SEND OFFER":
                switch (targetStage.toUpperCase()) {
                    case "OFFER ACCEPTANCE":
                    case "HAPPY PATH":
                        moveSendOfferCaseToOfferAcceptance();
                        break;
                    case "NIL OFFER ACCEPTANCE":
                        moveSendOfferCaseToNilOfferAcceptance();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "OFFER ACCEPTANCE":
                switch (targetStage.toUpperCase()) {
                    case "PAYMENT PREPARATION":
                    case "HAPPY PATH":
                        moveOfferAcceptanceCaseToPaymentPreparation();
                        break;
                    case "TIER 1":
                        moveOfferAcceptanceCaseToTier1();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "TIER 1":
                moveTier1CaseToTier2();
                break;
            case "PAYMENT PREPARATION":
                movePaymentPreparationCaseToPaymentApproval();
                break;
            case "PAYMENT APPROVAL":
                movePaymentApprovalCaseToSendPayment();
                break;
            case "SEND PAYMENT":
                moveSendPaymentCaseToAwaitingPaymentConfirmation();
                break;
            case "AWAITING PAYMENT CONFIRMATION":
                moveAwaitingPaymentConfirmationCaseToCompleteState();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.resetDataRecords();
    }

    public void moveRegistrationCaseToEligibility() {
        registration.selectIfClaimHasGoneThroughTaskForce("No");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsIdentity();
    }

    public void moveRegistrationCaseToIdentityRejected() {
        registration.selectIfClaimHasGoneThroughTaskForce("No");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.passTheClaimToStage1IdentityCheck();
        registration.failIdentityCheck();
    }

    public void moveRegistrationCaseToTriage() {
        registration.selectIfClaimHasGoneThroughTaskForce("Yes");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsID();
        registration.selectIDAndEligibilityConfirmed();
    }

    public void moveIdentityRejectedCaseToTier1IR() {
        rejected.selectToSendClaimToTier1Review();
    }

    public void moveIdentityRejectedCaseToArchivedIdentityRejected() {
        rejected.selectToArchiveClaim();
    }

    public void moveEligibilityCaseToTriage() {
        eligibility.confirmEligibility();
    }

    public void moveEligibilityCaseToEligibilityRejected() {
        eligibility.cannotConfirmEligibility();
        eligibility.selectRejectionReasonByIndex(1);
        clickOn(confirmButton);
    }

    public void moveEligibilityRejectedCaseToTier1ER() {
        rejected.selectToSendClaimToTier1Review();
    }

    public void moveEligibilityRejectedCaseToArchivedEligibilityRejected() {
        rejected.selectToArchiveClaim();
    }

    public void moveTriageCaseToCasework() {
        triage.selectACaseworkingTeam();
    }

    public void moveCaseworkCaseToQA() {
        casework.selectClaimStatusReadyToQA();
    }

    public void moveQACaseToPaymentPreOfferChecklist() {
        qaOffer.selectClaimStatusApproveOffer();
    }

    public void movePaymentPreOfferChecklistCaseToOfferApproval() {
        paymentPreOfferChecklist.selectPaymentSendForApproval();
    }

    public void moveOfferApprovalCaseToSendOffer() {
        offerApproval.selectPaymentOfferApproved();
    }

    public void moveSendOfferCaseToOfferAcceptance() {
        sendOffer.selectOfferSentToClaimant();
    }

    public void moveSendOfferCaseToNilOfferAcceptance() {
        sendOffer.selectNilOfferSentToClaimant();
    }

    public void moveOfferAcceptanceCaseToPaymentPreparation() {
        offerAcceptance.selectPaymentOfferAccepted();
    }

    public void moveOfferAcceptanceCaseToTier1() {
        offerAcceptance.selectPaymentOfferRejected();
    }

    public void moveTier1CaseToTier2() {
        tier1Review.completeTier1ReviewPage();
        tier1AwaitingResponse.selectClaimantRejectsFinalOffer();
    }

    public void movePaymentPreparationCaseToPaymentApproval() {
        paymentPreparation.selectToSendClaimToOfferApproval();
    }

    public void movePaymentApprovalCaseToSendPayment() {
        paymentApproval.selectPaymentApprovedReadyToSend();
    }

    public void moveSendPaymentCaseToAwaitingPaymentConfirmation() {
        sendPayment.selectFinalPaymentSent();
    }

    public void moveAwaitingPaymentConfirmationCaseToCompleteState() {
        awaitingPaymentConfirmation.selectClaimCompletePaymentConfirmed();
        awaitingPaymentConfirmation.yesCloseClaim();
    }
}
