package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;

public class COMPProgressCase extends BasePage {

    AddCorrespondent addCorrespondent;

    Documents documents;

    Registration registration;

    ServiceTriage serviceTriage;

    ServiceDraft serviceDraft;

    ServiceQA serviceQA;

    ServiceSend serviceSend;

    ComplaintClosed complaintClosed;

    public void moveCaseFromRegistrationToServiceTriage() {
        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectComplaintType("Service");
        registration.enterComplaintDetails();
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Service Triage");

    }

    public void moveCaseFromServiceTriageToServiceDraft() {
        serviceTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        serviceTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        serviceTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Service Draft");

    }

    public void moveCaseFromServiceTriageToServiceEscalated() {
        serviceTriage.selectAcceptCase();
        waitABit(500);
        clickTheButton("Continue");
        clickTheButton("Continue");
        serviceTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        serviceTriage.escalateCaseToWFM();
        System.out.println("Case moved from Service Triage to Service Escalated");
    }

    public void moveCaseFromServiceTriageToCCH() {
        serviceTriage.selectTransferComplaint();
        serviceTriage.enterTransferReason();
        serviceTriage.selectTransferToCCH();
        System.out.println("Case moved from Service Triage to CCH");
    }

    public void moveCaseFromServiceDraftToServiceQA() {
        documents.addADraftDocumentAtDraftStage();
        serviceDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Service Draft to Service QA");
    }

    public void moveCaseFromServiceDraftToServiceEscalated() {
        serviceDraft.selectActionAtServiceDraft("Escalate case to WFM");
        serviceDraft.submitEscalationReason();
        System.out.println("Case moved from Service Draft to Service Escalated");
    }

    public void moveCaseFromServiceQAToServiceSend() {
        serviceQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Service QA to Service Send");
    }

    public void moveCaseFromServiceQAToServiceDraft() {
        serviceQA.selectActionAtServiceQA("REJECT");
        serviceQA.submitRejectionReason();
        System.out.println("Case moved from Service QA to Service Escalated");
    }

    public void moveCaseFromServiceSendToComplaintClosed() {
        serviceSend.submitASelectedOutcome();
        System.out.println("Case moved from Service Send to Complaint Closed");
    }

    public void moveCaseFromComplaintClosedToCaseClosed() {
        complaintClosed.selectActionAtComplaintClosed("Complete the case");
        complaintClosed.submitReasonForCaseCompletion();
        complaintClosed.selectActionAtCompleteConfirmation("Yes");
        System.out.println("Case moved from Complaint Closed to Closed");
    }
}
