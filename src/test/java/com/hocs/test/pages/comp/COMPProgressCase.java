package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;

public class COMPProgressCase extends BasePage {

    AddCorrespondent addCorrespondent;

    Documents documents;

    Registration registration;

    COMPTriage COMPTriage;

    COMPDraft COMPDraft;

    COMPQA COMPQA;

    COMPSend COMPSend;

    ComplaintClosed complaintClosed;

    public void moveCaseFromRegistrationToServiceTriage() {
        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectComplaintType("Service");
        registration.enterComplaintDetails();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Service Triage");
    }

    public void moveCaseFromRegistrationToExGratiaTriage() {
        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectComplaintType("Ex-Gratia");
        registration.enterComplaintDetails();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Ex-Gratia Triage");
    }

    public void moveCaseFromServiceTriageToServiceDraft() {
        COMPTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        COMPTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        COMPTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Service Draft");
    }

    public void moveCaseFromExGratiaTriageToExGratiaResponseDraft() {
        COMPTriage.selectAcceptCase();
        COMPTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Continue");
        clickTheButton("Continue");
        COMPTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        COMPTriage.selectReadyForDrafting();
        System.out.println("Case moved from Ex-Gratia Triage to Ex-Gratia Response Draft");
    }

    public void moveCaseFromServiceTriageToServiceEscalated() {
        COMPTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        clickTheButton("Continue");
        COMPTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        COMPTriage.escalateCaseToWFM();
        System.out.println("Case moved from Service Triage to Service Escalated");
    }

    public void moveCaseFromExGratiaTriageToExGratiaEscalate() {
        COMPTriage.selectAcceptCase();
        COMPTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Continue");
        clickTheButton("Continue");
        COMPTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        COMPTriage.escalateCaseToWFM();
        System.out.println("Case moved from Ex-Gratia Triage to Ex-Gratia Escalate");
    }

    public void moveCaseFromServiceTriageToCCH() {
        COMPTriage.selectTransferComplaint();
        COMPTriage.enterTransferReason();
        COMPTriage.selectTransferToCCH();
        System.out.println("Case moved from Service Triage to CCH");
    }

    public void moveCaseFromServiceDraftToServiceQA() {
        documents.addADraftDocumentAtDraftStage();
        COMPDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Service Draft to Service QA");
    }

    public void moveCaseFromExGratiaResponseDraftToExGratiaQA() {
        documents.addADraftDocumentAtDraftStage();
        COMPDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Ex-Gratia Response Draft to Ex-Gratia QA");
    }

    public void moveCaseFromServiceDraftToServiceEscalated() {
        COMPDraft.selectActionAtServiceDraft("Escalate case to WFM");
        COMPDraft.submitEscalationReason();
        System.out.println("Case moved from Service Draft to Service Escalated");
    }

    public void moveCaseFromServiceQAToServiceSend() {
        COMPQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Service QA to Service Send");
    }

    public void moveCaseFromExGratiaQAToExGratiaSend() {
        COMPQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Ex-Gratia QA to Ex-Gratia Send");
    }

    public void moveCaseFromServiceQAToServiceDraft() {
        COMPQA.selectActionAtServiceQA("REJECT");
        COMPQA.submitRejectionReason();
        System.out.println("Case moved from Service QA to Service Escalated");
    }

    public void moveCaseFromServiceSendToComplaintClosed() {
        COMPSend.submitASelectedOutcome();
        System.out.println("Case moved from Service Send to Complaint Closed");
    }

    public void moveCaseFromExGratiaSendToComplaintClosed() {
        COMPSend.submitASelectedOutcome();
        System.out.println("Case moved from Ex-Gratia Send to Complaint Closed");
    }

    public void moveCaseFromComplaintClosedToCaseClosed() {
        complaintClosed.selectActionAtComplaintClosed("Complete the case");
        complaintClosed.submitReasonForCaseCompletion();
        complaintClosed.selectActionAtCompleteConfirmation("Yes");
        System.out.println("Case moved from Complaint Closed to Closed");
    }
}
