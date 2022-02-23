package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class BFProgressCase extends BasePage {

    CaseView caseView;

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Dashboard dashboard;

    Documents documents;

    Correspondents correspondents;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsSend complaintsSend;

    ComplaintsQA compQA;


    ComplaintsDraft complaintsDraft;

    String complaintType = "Service";

    public void moveCaseOfTypeFromCurrentStageToTargetStage(String caseType, String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            //TODO: Uncomment below code once the stage 2 is implemented
//            if (caseType.equals("COMP2")) {
//                escalateACOMPCaseToCOMP2();
//            }
            createCase.createCSCaseOfType(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheBFStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(String caseType, String complaintType, String targetStage) {
        this.complaintType = complaintType;
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "DRAFT":
            case "ESCALATED TO WFM":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheBFStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveBFCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
                        moveBFCaseFromTriageToDraft();
                        break;
                    case "ESCALATED TO WFM":
                        moveBFCaseFromTriageToEscalated();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveBFCaseFromDraftToQA();
                break;
            case "QA":
                moveBCaseFromQAToSend();
                break;
            case "SEND":
                moveBFCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.resetDataRecords();
    }

    public void moveBFCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectAComplaintType();
        clickTheButton("Continue");
        registration.selectAChannel();
        registration.enterADescriptionOfTheComplaint();
        registration.enterAPreviousComplaintReference();
        registration.enterAThirdPartyReference();
        clickTheButton("Continue");
        System.out.println("Case moved from Case Registration to Case Triage");
    }

    public void moveBFCaseFromTriageToDraft() {
        complaintsTriage.enterDetailsOnBFTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Case Triage to Draft");
    }

    public void moveBFCaseFromTriageToEscalated() {
        waitForPageWithTitle("Triage Capture Reason");
        complaintsTriage.enterDetailsOnBFTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.escalateCaseToWFM();
        System.out.println("Case moved from Case Triage to Escalated to WFM");
    }

    public void moveBFCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("DRAFT");
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
        System.out.println("Case moved from Draft to QA");
    }

    public void moveBCaseFromQAToSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from QA to Send draft response");
    }

    public void moveBFCaseFromSendToCaseClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send draft response to Case Closed");
    }

    public void generateBFSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("BF");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("BF");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                registration.enterComplainantDOB(infoValue);
                registration.selectAGender();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference("Test entry for Home Office Reference");
                registration.enterAPortReference();
                safeClickOn(continueButton);
                break;
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("BF");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("BF");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                registration.enterComplainantDOB("01/01/2001");
                registration.selectAGender();
                registration.enterACompanyName();
                registration.enterAHomeOfficeReference(infoValue);
                registration.enterAPortReference();
                safeClickOn(continueButton);
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}