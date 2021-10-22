package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.Search;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ComplaintsProgressCase extends BasePage {

    Correspondents correspondents;

    Documents documents;

    Dashboard dashboard;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsDraft complaintsDraft;

    ComplaintsQA compQA;

    ComplaintsSend complaintsSend;

    Search search;

// COMP Case Methods:

//      Service Complaint Type:


    public void moveCaseFromCOMPRegistrationToServiceTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        registration.enterComplainantDetails();
        registration.selectComplaintType("Service");
        registration.enterComplaintDetails();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from COMP Registration to Service Triage");
    }

    public void moveCaseFromServiceTriageToServiceDraft() {
        complaintsTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Service Draft");
    }

    public void moveCaseFromServiceTriageToServiceEscalated() {
        complaintsTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.escalateCaseToWFM();
        System.out.println("Case moved from Service Triage to Service Escalated");
    }

    public void moveCaseFromServiceTriageToCCH() {
        complaintsTriage.selectTransferComplaint();
        complaintsTriage.enterTransferReason();
        complaintsTriage.selectTransferToCCH();
        System.out.println("Case moved from Service Triage to CCH");
    }

    public void moveCaseFromServiceDraftToServiceQA() {
        documents.addADraftDocumentAtDraftStage();
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Service Draft to Service QA");
    }

    public void moveCaseFromServiceDraftToServiceEscalated() {
        complaintsDraft.selectActionAtServiceDraft("Escalate case to WFM");
        complaintsDraft.submitEscalationReason();
        System.out.println("Case moved from Service Draft to Service Escalated");
    }

    public void moveCaseFromServiceQAToServiceSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Service QA to Service Send");
    }

    public void moveCaseFromServiceQAToServiceDraft() {
        compQA.selectActionAtServiceQA("REJECT");
        compQA.submitRejectionReason();
        System.out.println("Case returned from Service QA to Service Draft");
    }

    public void moveCaseFromServiceSendToComplaintClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Service Send to Complaint Closed");
    }


//      Ex-Gratia Complaint Type:


    public void moveCaseFromRegistrationToExGratiaTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        registration.enterComplainantDetails();
        registration.selectComplaintType("Ex-Gratia");
        registration.enterComplaintDetails();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Ex-Gratia Triage");
    }

    public void moveCaseFromExGratiaTriageToExGratiaResponseDraft() {
        complaintsTriage.selectAcceptCase();
        complaintsTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        complaintsTriage.openExGratiaAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Ex-Gratia Triage to Ex-Gratia Response Draft");
    }

    public void moveCaseFromExGratiaTriageToExGratiaEscalate() {
        complaintsTriage.selectAcceptCase();
        complaintsTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        complaintsTriage.openExGratiaAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        clickTheButton("Continue");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.escalateCaseToWFM();
        System.out.println("Case moved from Ex-Gratia Triage to Ex-Gratia Escalate");
    }

    public void moveCaseFromExGratiaTriageToCCH() {
        complaintsTriage.selectTransferComplaint();
        complaintsTriage.enterTransferReason();
        complaintsTriage.selectTransferToCCH();
        System.out.println("Case moved from Ex-Gratia Triage to CCH");
    }

    public void moveCaseFromExGratiaResponseDraftToExGratiaQA() {
        documents.addADraftDocumentAtDraftStage();
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Ex-Gratia Response Draft to Ex-Gratia QA");
    }

    public void moveCaseFromExGratiaQAToExGratiaSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Ex-Gratia QA to Ex-Gratia Send");
    }

    public void moveCaseFromExGratiaSendToComplaintClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Ex-Gratia Send to Complaint Closed");
    }


//      Minor Misconduct Complaint Type


    public void moveCaseFromRegistrationToMinorMisconductTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        registration.enterComplainantDetails();
        registration.selectComplaintType("Minor Misconduct");
        registration.enterComplaintDetails();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Minor Misconduct Triage");
    }

    public void moveCaseFromMinorMisconductTriageToMinorMisconductResponseDraft() {
        complaintsTriage.selectAcceptCase();
        complaintsTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Minor Misconduct Triage to Minor Misconduct Response Draft");
    }

    public void moveCaseFromMinorMisconductTriageToMinorMisconductEscalate() {
        complaintsTriage.selectAcceptCase();
        complaintsTriage.enterDateOfAcceptance();
        clickTheButton("Continue");
        waitABit(500);
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.escalateCaseToWFM();
        System.out.println("Case moved from Minor Misconduct Triage to Minor Misconduct Escalate");
    }

    public void moveCaseFromMinorMisconductTriageToCCH() {
        complaintsTriage.selectTransferComplaint();
        complaintsTriage.enterTransferReason();
        complaintsTriage.selectTransferToCCH();
        System.out.println("Case moved from Minor Misconduct Triage to CCH");
    }

    public void moveCaseFromMinorMisconductResponseDraftToMinorMisconductQA() {
        documents.addADraftDocumentAtDraftStage();
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Minor Misconduct Response Draft to Minor Misconduct QA");
    }

    public void moveCaseFromMinorMisconductQAToMinorMisconductSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from Minor Misconduct QA to Minor Misconduct Send");
    }

    public void moveCaseFromMinorMisconductSendToComplaintClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Minor Misconduct Send to Complaint Closed");
    }


//  COMP2 Case Methods:

    public void attemptEscalateCOMPCaseToStage2() throws Exception {
        dashboard.selectSearchLinkFromMenuBar();
        search.enterCOMPSearchCriteria("Complainant Home Office Reference", getCurrentMonth() +"/" + getCurrentYear());
        search.clickTheButton("Search");
        search.waitForResultsPage();
        if (search.checkVisibilityOfEscalationHypertext()) {
            WebElementFacade compCaseRefField = findBy("//a[contains(text(), 'Escalate case')]/parent::td/preceding-sibling::td/a");
            String compCaseRef = compCaseRefField.getText();
            setSessionVariable("compCaseReference").to(compCaseRef);
            System.out.print("Case reference of case being escalated: " + compCaseRef);
            search.clickEscalateCOMPCaseToCOMP2();
        } else {
            throw new Exception("Escalation hypertext not visible");
        }
    }


//  IEDET Case Methods:


    public void moveIEDETCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        registration.enterComplainantDetails();
        registration.selectComplaintType("Service");
        registration.selectAChannel();
        registration.selectComplaintOrigin();
        registration.enterADescriptionOfTheComplaint();
        registration.enterAThirdPartyReference();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Triage");
    }

    public void moveIEDETCaseFromTriageToDraft() {
        complaintsTriage.selectTransferredToIEDetentionComplianceTeam();
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        safeClickOn(continueButton);
        System.out.println("Case moved from Triage to Draft");
    }

    public void moveIEDETCaseFromDraftToSend() {
        clickTheButton("Proceed to recording outcome");
        System.out.println("Case moved from Draft to Send");
    }

    public void moveIEDETCaseFromSendToCaseClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send to Closed");
    }

//  SMC Case Methods:


    public void moveSMCCaseFromRegistrationToTriage() {
            correspondents.addANonMemberCorrespondentOfType("Complainant");
            clickTheButton("Continue");
            registration.enterComplainantDetails();
            registration.selectAChannel();
            registration.selectAdditionalInformation();
            registration.enterADescriptionOfTheComplaint();
            registration.enterAPreviousUKVIComplaintReference();
            registration.enterAThirdPartyReference();
            clickTheButton("Continue");
            registration.openTheSeriousComplaintCategoryAccordion();
            waitABit(1000);
            registration.selectAVisibleClaimCategory();
            registration.selectAnOwningCSU();
            clickTheButton("Finish");
            System.out.println("Case moved from Registration to Triage");

    }

    public void moveSMCCaseFromTriageToSend(){
        complaintsTriage.selectAcceptCase();
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        complaintsTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Send");
    }

    public void moveSMCCaseFromSendToCaseClosed() {
        complaintsSend.selectACaseOutcome();
        clickTheButton("Complete");
        System.out.println("Case moved from Send to Closed");
    }

}
