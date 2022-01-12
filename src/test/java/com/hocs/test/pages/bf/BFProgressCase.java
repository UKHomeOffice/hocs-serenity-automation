package com.hocs.test.pages.bf;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.complaints.ComplaintsSend;
import com.hocs.test.pages.complaints.ComplaintsTriage;
import com.hocs.test.pages.complaints.Registration;
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

    //TODO replace with complaintsTriage with BFTriage
    ComplaintsTriage complaintsTriage;
    ComplaintsSend complaintsSend;

    BFTriage bfTriage;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("BF");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheBFStage(precedingStage);
        }
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
            //TODO remove the commented code once the DRAFT/QA stage is implemented
/*          case "DRAFT":
                precedingStage = "TRIAGE";
                break;*/
            case "SEND":
                precedingStage = "TRIAGE";
                break;
            case "CASE CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheBFStage(String stageToComplete) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveBFCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                moveBFCaseFromTriageToSend();
                break;
                //TODO remove the commented code once the DRAFT/QA stage is implemented
/*            case "DRAFT":
                moveBFCaseFromDraftToSend();
                break;*/
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

    public void moveBFCaseFromTriageToSend() {
        bfTriage.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriage.selectReadyForDrafting();
        System.out.println("Case moved from Service Triage to Send");
    }

    //TODO Uncomment below method once DRAFT/QA stage is implemented
/*    public void moveBFCaseFromTriageToSend() {
        documents.addADraftDocumentAtDraftStage();
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
}