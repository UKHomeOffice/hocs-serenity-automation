package com.hocs.test.pages.to;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class TOProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Correspondents correspondents;

    Documents documents;

    DataInput dataInput;

    Triage triage;

    Draft draft;

    Campaign campaign;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("TO");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheTOStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "DATA INPUT":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "DATA INPUT";
                break;
            case "DRAFT":
            case "CAMPAIGN":
            case "STOP LIST":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "DISPATCH":
                precedingStage = "QA";
                break;
            case "CASE CLOSED":
                precedingStage = "DISPATCH";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheTOStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                moveCaseFromDataInputToTriage();
                break;
            case "TRIAGE":
                switch ((targetStage.toUpperCase())) {
                    case "DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "CAMPAIGN":
                        moveCaseFromTriageToCampaign();
                        break;
                    case "STOP LIST":
                        moveCaseFromTriageToStopList();
                        break;
                    default:
                        pendingStep(targetStage + " is not a defined target stage from Triage within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveCaseFromDraftToQA();
                break;
            case "QA":
                moveCaseFromQAToDispatch();
                break;
            case "DISPATCH":
                moveCaseFromDispatchToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.resetDataRecords();
    }

    public void moveCaseFromDataInputToTriage() {
        dataInput.selectABusinessArea();
        dataInput.selectAChannelRecieved();
        clickTheButton("Continue");
        correspondents.addANonMemberCorrespondentOfType("Correspondent");
        correspondents.confirmPrimaryCorrespondent();
        dataInput.selectWhetherToAddRecipient("No");
        clickTheButton("Continue");
    }

    private void moveCaseFromTriageToDraft() {
        triage.selectSetEnquirySubjectAndReasonLink();
        triage.selectAnEnquirySubject();
        clickTheButton("Continue");
        triage.selectAnEnquiryReason();
        clickTheButton("Continue");
        triage.selectABusinessUnitType();
        triage.selectABusinessUnit();
        triage.selectTheAction("Ready to draft");
        clickTheButton("Finish");
    }

    private void moveCaseFromTriageToCampaign() {
        triage.selectSetEnquirySubjectAndReasonLink();
        triage.selectAnEnquirySubject();
        clickTheButton("Continue");
        triage.selectAnEnquiryReason();
        clickTheButton("Continue");
        triage.selectABusinessUnitType();
        triage.selectABusinessUnit();
        campaign.selectTheAction("Put case into a campaign");
        clickTheButton("Finish");
        campaign.selectACampaign();
        clickTheButton("Confirm");
    }

    private void moveCaseFromTriageToStopList() {
    }

    private void moveCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("Initial Draft");
        draft.selectTheAction("Move to QA");
        clickTheButton("Finish");
    }

    private void moveCaseFromQAToDispatch() {
        draft.selectTheAction("Approve");
        clickTheButton("Finish");
    }

    private void moveCaseFromDispatchToCaseClosed() {
        clickTheButton("Finish");
    }
}
