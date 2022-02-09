package com.hocs.test.pages.to;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import java.text.ParseException;

public class TOProgressCase extends BasePage {

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Dashboard dashboard;

    Correspondents correspondents;

    Documents documents;

    DataInput dataInput;

    Triage triage;

    Draft draft;

    Dispatch dispatch;

    Campaign campaign;

    StopList stopList;

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
        selectTheStageAction("Ready to draft");
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
        selectTheStageAction("Put case into a campaign");
        clickTheButton("Finish");
        campaign.selectACampaign();
        clickTheButton("Confirm");
    }

    private void moveCaseFromTriageToStopList() {
        triage.selectSetEnquirySubjectAndReasonLink();
        triage.selectAnEnquirySubject();
        clickTheButton("Continue");
        triage.selectAnEnquiryReason();
        clickTheButton("Continue");
        triage.selectABusinessUnitType();
        triage.selectABusinessUnit();
        selectTheStageAction("Place on a stop list");
        clickTheButton("Finish");
        stopList.selectAStopList();
        clickTheButton("Confirm");
    }

    private void moveCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("Initial Draft");
        selectTheStageAction("Move to QA");
        clickTheButton("Finish");
    }

    private void moveCaseFromQAToDispatch() {
        selectTheStageAction("Approve");
        clickTheButton("Finish");
    }

    private void moveCaseFromDispatchToCaseClosed() {
        documents.addADocumentOfDocumentType("Final response");
        dispatch.enterDateOfDispatch();
        dispatch.selectAFinalDispatchChannel();
        documents.recordFinalResponseDocument();
        selectTheStageAction("Complete case");
        clickTheButton("Finish");
    }

    public void generateTOSearchCaseData(String infoValue, String infoType) throws ParseException {
        switch (infoType.toUpperCase()) {
            case "CASE REFERENCE":
            case "ACTIVE CASES ONLY":
                createCase.createCSCaseOfType("TO");
                break;
            case "RECEIVED ON OR AFTER":
                createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("TO", "After", infoValue);
                break;
            case "RECEIVED ON OR BEFORE":
                createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("TO", "Before", infoValue);
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
            case "CORRESPONDENT REFERENCE NUMBER":
                createCase.createCSCaseOfType("TO");
                confirmationScreens.goToCaseFromConfirmationScreen();
                dashboard.claimCurrentCase();
                dataInput.selectABusinessArea();
                dataInput.selectAChannelRecieved();
                clickTheButton("Continue");
                correspondents.addANonMemberCorrespondentOfType("Correspondent");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "CAMPAIGN":
                createCase.createCSCaseOfType("TO");
                confirmationScreens.goToCaseFromConfirmationScreen();
                dashboard.claimCurrentCase();
                dataInput.selectABusinessArea();
                dataInput.selectAChannelRecieved();
                clickTheButton("Continue");
                correspondents.addANonMemberCorrespondentOfType("Correspondent");
                correspondents.confirmPrimaryCorrespondent();
                dataInput.selectWhetherToAddRecipient("No");
                clickTheButton("Continue");
                triage.selectSetEnquirySubjectAndReasonLink();
                triage.selectAnEnquirySubject();
                clickTheButton("Continue");
                triage.selectAnEnquiryReason();
                clickTheButton("Continue");
                triage.selectABusinessUnitType();
                triage.selectABusinessUnit();
                selectTheStageAction("Put case into a campaign");
                clickTheButton("Finish");
                campaign.selectASpecificCampaign(infoValue);
                clickTheButton("Confirm");
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
