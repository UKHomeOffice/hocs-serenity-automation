package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.Search;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class COMPProgressCase extends BasePage {

    AddCorrespondent addCorrespondent;

    Documents documents;

    Dashboard dashboard;

    Registration registration;

    COMPTriage compTriage;

    COMPDraft compDraft;

    COMPQA compQA;

    COMPSend compSend;

    Search search;

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

    public void moveCaseFromRegistrationToMinorMisconductTriage() {
        addCorrespondent.addAPublicCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectComplaintType("Minor Misconduct");
        registration.enterComplaintDetails();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Minor Misconduct Triage");
    }

    public void moveCaseFromServiceTriageToServiceDraft() {
        compTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        compTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        compTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Service Draft");
    }

    public void moveCaseFromExGratiaTriageToExGratiaResponseDraft() {
        compTriage.selectAcceptCase();
        compTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        compTriage.openExGratiaAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        compTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        compTriage.selectReadyForDrafting();
        System.out.println("Case moved from Ex-Gratia Triage to Ex-Gratia Response Draft");
    }

    public void moveCaseFromMinorMisconductTriageToMinorMisconductResponseDraft() {
        compTriage.selectAcceptCase();
        compTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        compTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        compTriage.selectReadyForDrafting();
        System.out.println("Case moved from Minor Misconduct Triage to Minor Misconduct Response Draft");
    }

    public void moveCaseFromServiceTriageToServiceEscalated() {
        compTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        clickTheButton("Continue");
        compTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        compTriage.escalateCaseToWFM();
        System.out.println("Case moved from Service Triage to Service Escalated");
    }

    public void moveCaseFromExGratiaTriageToExGratiaEscalate() {
        compTriage.selectAcceptCase();
        compTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        compTriage.openExGratiaAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        clickTheButton("Continue");
        clickTheButton("Continue");
        compTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        compTriage.escalateCaseToWFM();
        System.out.println("Case moved from Ex-Gratia Triage to Ex-Gratia Escalate");
    }

    public void moveCaseFromMinorMisconductTriageToMinorMisconductEscalate() {
        compTriage.selectAcceptCase();
        compTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        compTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        compTriage.escalateCaseToWFM();
        System.out.println("Case moved from Minor Misconduct Triage to Minor Misconduct Escalate");
    }

    public void moveCaseFromServiceTriageToCCH() {
        compTriage.selectTransferComplaint();
        compTriage.enterTransferReason();
        compTriage.selectTransferToCCH();
        System.out.println("Case moved from Service Triage to CCH");
    }

    public void moveCaseFromExGratiaTriageToCCH() {
        compTriage.selectTransferComplaint();
        compTriage.enterTransferReason();
        compTriage.selectTransferToCCH();
        System.out.println("Case moved from Ex-Gratia Triage to CCH");
    }

    public void moveCaseFromMinorMisconductTriageToCCH() {
        compTriage.selectTransferComplaint();
        compTriage.enterTransferReason();
        compTriage.selectTransferToCCH();
        System.out.println("Case moved from Minor Misconduct Triage to CCH");
    }

    public void moveCaseFromServiceDraftToServiceQA() {
        documents.addADraftDocumentAtDraftStage();
        compDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Service Draft to Service QA");
    }

    public void moveCaseFromExGratiaResponseDraftToExGratiaQA() {
        documents.addADraftDocumentAtDraftStage();
        compDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Ex-Gratia Response Draft to Ex-Gratia QA");
    }

    public void moveCaseFromMinorMisconductResponseDraftToMinorMisconductQA() {
        documents.addADraftDocumentAtDraftStage();
        compDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Minor Misconduct Response Draft to Minor Misconduct QA");
    }

    public void moveCaseFromServiceDraftToServiceEscalated() {
        compDraft.selectActionAtServiceDraft("Escalate case to WFM");
        compDraft.submitEscalationReason();
        System.out.println("Case moved from Service Draft to Service Escalated");
    }

    public void moveCaseFromServiceQAToServiceSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Service QA to Service Send");
    }

    public void moveCaseFromExGratiaQAToExGratiaSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Ex-Gratia QA to Ex-Gratia Send");
    }

    public void moveCaseFromMinorMisconductQAToMinorMisconductSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Minor Misconduct QA to Minor Misconduct Send");
    }

    public void moveCaseFromServiceQAToServiceDraft() {
        compQA.selectActionAtServiceQA("REJECT");
        compQA.submitRejectionReason();
        System.out.println("Case moved from Service QA to Service Escalated");
    }

    public void moveCaseFromServiceSendToComplaintClosed() {
        compSend.selectACaseOutcome();
        compSend.selectAResponseChannel();
        compSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Service Send to Complaint Closed");
    }

    public void moveCaseFromExGratiaSendToComplaintClosed() {
        compSend.selectACaseOutcome();
        compSend.selectAResponseChannel();
        compSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Ex-Gratia Send to Complaint Closed");
    }

    public void moveCaseFromMinorMisconductSendToComplaintClosed() {
        compSend.selectACaseOutcome();
        compSend.selectAResponseChannel();
        compSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Minor Misconduct Send to Complaint Closed");
    }

    public void attemptEscalateCOMPCaseToStage2() throws Exception {
        dashboard.selectSearchLinkFromMenuBar();
        search.enterCOMPSearchCriteria("Complainant Home Office Reference", getCurrentMonth() +"/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        if (search.checkVisibilityOfEscalationHypertext()) {
            WebElementFacade compCaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
            String compCaseRef = compCaseRefField.getText();
            setSessionVariable("compCaseReference").to(compCaseRef);
            System.out.print(compCaseRef);
            search.clickEscalateCOMPCaseToCOMP2();
        } else {
            throw new Exception("Escalation hypertext not visible");
        }
    }
}
