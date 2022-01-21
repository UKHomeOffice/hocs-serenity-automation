package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class BFProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Documents documents;

    Correspondents correspondents;

    Registration registration;

    ComplaintsTriage complaintsTriage;

    ComplaintsSend complaintsSend;

    ComplaintsDraft complaintsDraft;

    String complaintType = "Service";

    public void moveCaseOfTypeFromCurrentStageToTargetStage(String caseType, String currentStage, String targetStage) {
        setComplaintTypeFromStageName(targetStage);
        targetStage = getSimplifiedStageName(targetStage);
        currentStage = getSimplifiedStageName(currentStage);
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


    private String getSimplifiedStageName(String targetStage) {
        String[] words = targetStage.split(" ");
        String simplifiedStage = words[words.length - 1];
        if (simplifiedStage.equalsIgnoreCase("Escalate")) {
            simplifiedStage = "Escalated";
        }
        return simplifiedStage;
    }

    private void setComplaintTypeFromStageName(String targetStage) {
        if (containsIgnoreCase(targetStage, "Service")) {
            complaintType = "Service";
        } else if (containsIgnoreCase(targetStage, "Minor Misconduct") || containsIgnoreCase(targetStage, "MM")) {
            complaintType = "Minor Misconduct";
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
                        //TODO check and implement the below stages
/*                    case "ESCALATED":
                        moveCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;*/
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveBFCaseFromDraftToQA();
                break;
            case "QA":
                moveCaseFromQAToSend();
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

    public void completeTheBFStage(String stageToComplete) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveBFCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                moveBFCaseFromTriageToDraft();
                break;
            case "DRAFT":
                moveBFCaseFromDraftToQA();
                break;
            case "SEND":
                moveBFCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.resetDataRecords();
    }

    public void moveBFCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        clickTheButton("Continue");
        registration.enterComplainantDetails();
        registration.selectComplaintType("Service");
        registration.selectAChannel();
        registration.enterADescriptionOfTheComplaint();
        //TODO remove the below commented line
//        registration.enterAPreviousUKVIComplaintReference();
        registration.enterAThirdPartyReference();
        clickTheButton("Continue");
        registration.openTheServiceComplaintCategoryAccordion();
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
        registration.selectAnOwningCSU();
        clickTheButton("Finish");
        System.out.println("Case moved from Registration to Triage");

    }

    public void moveBFCaseFromTriageToDraft() {
        complaintsTriage.enterDetailsOnBFTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Draft");
    }

    public void moveBFCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("DRAFT");
        complaintsDraft.selectActionAtServiceDraft("Send Case to QA");
    }

    //TODO: Remove below method at the time of refactoring
/*    public void moveBFCaseFromDraftToSend() {
        documents.addADocumentOfDocumentType("DRAFT");
        clickTheButton("Response Ready");
        System.out.println("Case moved from Draft to Send");
    }*/

    public void moveBFCaseFromSendToCaseClosed() {
        complaintsSend.selectACaseOutcome();
        complaintsSend.selectAResponseChannel();
        //TODO raise a defect as the date is prepopulated
//        complaintsSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send to Closed");
    }

    public void moveCaseFromQAToSend() {
        //TODO: Add the Accept and Reject options once the QA stage is developed
//        compQA.selectActionAtServiceQA("ACCEPT");
        clickTheButton("Continue");
    }
}