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

    ComplaintsDraft complaintsDraft;

    Correspondents correspondents;

    DataInput dataInput;

    Dashboard dashboard;

    Documents documents;

    Investigation investigation;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage, String businessArea) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("POGR");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage, businessArea);
            }
            completeThePOGRStageSoThatCaseMovesToTargetStage(precedingStage, businessArea);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        setSessionVariable("targetStage").to(targetStage);
        //Unsure on stage names for POGR workflow, will need updating once new stages are developed
        switch (targetStage.toUpperCase()) {
            case "DATA INPUT":
                precedingStage = "CREATE NEW CASE";
                break;
            case "INVESTIGATION":
                precedingStage = "DATA INPUT";
                break;
            case "DRAFT":
                precedingStage = "INVESTIGATION";
                break;
            case "CASE CLOSED":
                precedingStage = "DRAFT";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeThePOGRStageSoThatCaseMovesToTargetStage(String stageToComplete, String businessArea) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        //Unsure on stage names for POGR workflow, will need updating once new stages are developed
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                if (businessArea == null) {
                    movePOGRCaseFromDataInputToInvestigation();
                } else {
                    movePOGRCaseWithSpecificBusinessAreaFromDataInputToInvestigation(businessArea);
                }
                break;
            case "INVESTIGATION":
                movePOGRCaseFromInvestigationToDraft();
                break;
            case "DRAFT":
                movePOGRCaseFromDraftToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void movePOGRCaseFromDataInputToInvestigation() {
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
            safeClickOn(finishButton);
        }
    }

    public void movePOGRCaseWithSpecificBusinessAreaFromDataInputToInvestigation(String businessArea) {
        dataInput.selectSpecificBusinessArea(businessArea);
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        safeClickOn(continueButton);
        dataInput.completeComplainantDetails();
        safeClickOn(continueButton);
        documents.addADocumentOfDocumentType("Interim Letter");
        dataInput.enterDateLetterSent();
        safeClickOn(continueButton);
        if (businessArea.equalsIgnoreCase("GRO")) {
            dataInput.selectInvestigatingTeam();
            safeClickOn(finishButton);
        }
    }

    public void movePOGRCaseFromInvestigationToDraft() {
        investigation.acceptCaseAtInvestigation();
        safeClickOn(continueButton);
        investigation.selectAllInformationCollectedRespondAction();
        safeClickOn(finishButton);
    }

    public void movePOGRCaseFromDraftToCaseClosed() {
        documents.addADocumentOfDocumentType("Draft");
        complaintsDraft.selectActionAtDraft("Respond by Phone");
        safeClickOn(finishButton);
        complaintsDraft.completePOGRComplaintTelephoneResponseScreen();
    }
}