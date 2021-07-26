package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;

public class WCSProgressCase extends BasePage {
    
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

    public void moveRegistrationCaseToEligibility() {
        registration.selectIfClaimHasGoneThroughTaskForce("No");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsIdentity();
        System.out.println("Case moved from Registration to Eligibility");
    }

    public void moveRegistrationCaseToIdentityRejected() {
        registration.selectIfClaimHasGoneThroughTaskForce("No");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.passTheClaimToStage1IdentityCheck();
        registration.failIdentityCheck();
        System.out.println("Case moved from Registration to Identity Rejected");
    }

    public void moveRegistrationCaseToTriage() {
        registration.selectIfClaimHasGoneThroughTaskForce("Yes");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsID();
        registration.selectIDAndEligibilityConfirmed();
        System.out.println("Case moved from Registration to Triage");
    }

    public void moveIdentityRejectedCaseToTier1IR() {
        rejected.selectToSendClaimToTier1Review();
        System.out.println("Case moved from Identity Rejected to Tier 1 (IR)");
    }

    public void moveIdentityRejectedCaseToArchivedIdentityRejected() {
        rejected.selectToArchiveClaim();
        System.out.println("Case moved from Identity Rejected to Archived Identity Rejected");
    }

    public void moveEligibilityCaseToTriage() {
        eligibility.confirmEligibility();
        System.out.println("Case moved from Eligibility to Triage");
    }

    public void moveEligibilityCaseToEligibilityRejected() {
        eligibility.cannotConfirmEligibility();
        eligibility.selectRejectionReasonByIndex(1);
        clickOn(confirmButton);
        System.out.println("Case moved from Eligibility to Eligibility Rejected");
    }

    public void moveEligibilityRejectedCaseToTier1ER() {
        rejected.selectToSendClaimToTier1Review();
        System.out.println("Case moved from Eligibility Rejected to Tier 1 (ER)");
    }

    public void moveEligibilityRejectedCaseToArchivedEligibilityRejected() {
        rejected.selectToArchiveClaim();
        System.out.println("Case moved from Eligibility Rejected to Archived Eligibility Rejected");
    }

    public void moveTriageCaseToCasework() {
        triage.selectACaseworkingTeam();
        System.out.println("Case moved from Triage to Casework");
    }

    public void moveCaseworkCaseToQA() {
        casework.selectClaimStatusReadyToQA();
        System.out.println("Case moved from Casework to QA");
    }

    public void moveQACaseToPaymentPreOfferChecklist() {
        qaOffer.selectClaimStatusApproveOffer();
        System.out.println("Case moved from QA to Payment Pre-Offer Checklist");
    }

    public void moveQACaseToOfferApproval() {
        moveQACaseToPaymentPreOfferChecklist();
        dashboard.getAndClaimCurrentCase();
        movePaymentPreOfferChecklistCaseToOfferApproval();
    }

    public void moveQACaseToSendOffer() {
        moveQACaseToOfferApproval();
        dashboard.getAndClaimCurrentCase();
        moveOfferApprovalCaseToSendOffer();
    }

    public void moveQACaseToOfferAcceptance() {
        moveQACaseToSendOffer();
        dashboard.getAndClaimCurrentCase();
        moveSendOfferCaseToOfferAcceptance();
    }

    public void moveQACaseToNilOfferAcceptance() {
        moveQACaseToSendOffer();
        dashboard.getAndClaimCurrentCase();
        moveSendOfferCaseToNilOfferAcceptance();
    }

    public void moveQACaseToPaymentPreparation() {
        moveQACaseToOfferAcceptance();
        dashboard.getAndClaimCurrentCase();
        moveOfferAcceptanceCaseToPaymentPreparation();
    }

    public void moveQACaseToPaymentApproval() {
        moveQACaseToPaymentPreparation();
        dashboard.getAndClaimCurrentCase();
        movePaymentPreparationCaseToPaymentApproval();
    }

    public void moveQACaseToSendPayment() {
        moveQACaseToPaymentApproval();
        dashboard.getAndClaimCurrentCase();
        movePaymentApprovalCaseToSendPayment();
    }

    public void moveQACaseToAwaitingPaymentConfirmation() {
        moveQACaseToSendPayment();
        dashboard.getAndClaimCurrentCase();
        moveSendPaymentCaseToAwaitingPaymentConfirmation();
    }

    public void movePaymentPreOfferChecklistCaseToOfferApproval() {
        paymentPreOfferChecklist.selectPaymentSendForApproval();
        System.out.println("Case moved from Payment Pre-Offer Checklist to Offer Approval");
    }

    public void moveOfferApprovalCaseToSendOffer() {
        offerApproval.selectPaymentOfferApproved();
        System.out.println("Case moved from Offer Approval to Send Offer");
    }

    public void moveSendOfferCaseToOfferAcceptance() {
        sendOffer.selectOfferSentToClaimant();
        System.out.println("Case moved from Send Offer to Offer Acceptance");
    }

    public void moveSendOfferCaseToNilOfferAcceptance() {
        sendOffer.selectNilOfferSentToClaimant();
        System.out.println("Case moved from Send Offer to Nil Offer Acceptance");
    }

    public void moveOfferAcceptanceCaseToPaymentPreparation() {
        offerAcceptance.selectPaymentOfferAccepted();
        System.out.println("Case moved from Offer Acceptance to Payment Preparation");
    }

    public void moveOfferAcceptanceCaseToTier1() {
        offerAcceptance.selectPaymentOfferRejected();
        System.out.println("Case moved from Offer Acceptance to Tier 1");
    }

    public void moveOfferAcceptanceCaseToTier2() {
        moveOfferAcceptanceCaseToTier1();
        dashboard.getAndClaimCurrentCase();
        moveTier1CaseToTier2();
    }

    public void moveTier1CaseToTier2() {
        tier1Review.completeTier1ReviewPage();
        tier1AwaitingResponse.selectClaimantRejectsFinalOffer();
        System.out.println("Case moved from Tier 1 to Tier 2");
    }

    public void movePaymentPreparationCaseToPaymentApproval() {
        paymentPreparation.selectToSendClaimToOfferApproval();
        System.out.println("Case moved from Payment Preparation to Payment Approval");
    }

    public void movePaymentApprovalCaseToSendPayment() {
        paymentApproval.selectPaymentApprovedReadyToSend();
        System.out.println("Case moved from Payment Approval to Send Payment");
    }

    public void moveSendPaymentCaseToAwaitingPaymentConfirmation() {
        sendPayment.selectFinalPaymentSent();
        System.out.println("Case moved from Send Payment to Awaiting Payment Confirmation");
    }

    public void moveAwaitingPaymentConfirmationCaseToCompleteState() {
        awaitingPaymentConfirmation.selectClaimCompletePaymentConfirmed();
        awaitingPaymentConfirmation.yesCloseClaim();
        System.out.println("Case moved from Awaiting Payment Confirmation to case complete state");
    }

}
