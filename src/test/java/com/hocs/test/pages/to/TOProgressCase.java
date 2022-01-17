package com.hocs.test.pages.to;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
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

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("TO");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheTOStage(precedingStage);
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

    public void completeTheTOStage(String stageToComplete) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                moveCaseFromDataInputToTriage();
                break;
            case "TRIAGE":
                moveCaseFromTriageToDraft();
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
