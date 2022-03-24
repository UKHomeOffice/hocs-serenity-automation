package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class POGRProgressCase extends BasePage {

    CreateCase createCase;

    Correspondents correspondents;

    DataInput dataInput;

    Dashboard dashboard;

    Documents documents;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("POGR");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeThePOGRStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        //Unsure on stage names for POGR workflow, will need updating once new stages are developed
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
            case "SEND":
                precedingStage = "DRAFT";
                break;
            case "CASE CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeThePOGRStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        //Unsure on stage names for POGR workflow, will need updating once new stages are developed
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                movePOGRCaseFromDataInputToTriage();
                break;
            case "TRIAGE":
                movePOGRCaseFromTriageToDraft();
                break;
            case "DRAFT":
                movePOGRCaseFromDraftToSend();
                break;
            case "SEND":
                movePOGRCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void movePOGRCaseFromDataInputToTriage() {
        dataInput.selectBusinessArea();
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        safeClickOn(continueButton);
        dataInput.completeComplainantDetails();
        safeClickOn(continueButton);
        documents.addADocumentOfDocumentType("Interim Letter");
        dataInput.enterDateLetterSent();
        safeClickOn(continueButton);
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            dataInput.selectInvestigatingTeam();
            safeClickOn(continueButton);
        }
    }

    //The following methods can be completed once the relevant tickets come through

    public void movePOGRCaseFromTriageToDraft() {

    }

    public void movePOGRCaseFromDraftToSend() {

    }

    public void movePOGRCaseFromSendToCaseClosed() {

    }
}
