package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;

public class MPAMProgressCase extends BasePage {

    Dashboard dashboard;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    public void completeTheMPAMStage(String stage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stage.toUpperCase()) {
            case "CREATION":
                creation.moveCaseFromCreationToTriage();
                break;
            case "TRIAGE":
                triage.moveCaseFromTriageToDraft();
                break;
            case "DRAFT":
                draft.moveCaseFromDraftToQA();
                break;
            case "QA":
                qa.moveCaseFromQAToNextStage();
                break;
            case "PRIVATE OFFICE (TO CASE CLOSED)":
                dispatchStages.moveCaseFromPrivateOfficeToCaseClosed();
                break;
            case "PRIVATE OFFICE (TO AWAITING DISPATCH (LOCAL))":
                dispatchStages.moveCaseFromPrivateOfficeToAwaitingDispatchLocal();
                break;
            case "PRIVATE OFFICE (TO AWAITING DISPATCH (MINISTERIAL))":
                dispatchStages.moveCaseFromPrivateOfficeToAwaitingDispatchMinisterial();
                break;
            case "AWAITING DISPATCH":
                dispatchStages.moveCaseFromAwaitingDispatchToCaseClosed();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        RecordCaseData.resetDataRecords();
    }

    public void CreateMPAMCaseAndMoveItToTheSpecifiedStage(String stage) {
        switch (stage.toUpperCase()) {
            case "CREATION":
                createCase.createCSCaseOfType("MPAM");
                dashboard.goToDashboard();
                break;
            case "TRIAGE":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("CREATION");
                completeTheMPAMStage("CREATION");
                break;
            case "DRAFT":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("TRIAGE");
                completeTheMPAMStage("TRIAGE");
                break;
            case "QA":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("DRAFT");
                completeTheMPAMStage("DRAFT");
                break;
            case "PRIVATE OFFICE":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("QA");
                completeTheMPAMStage("QA");
                break;
            case "AWAITING DISPATCH":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("UKVI", "Official", "QA");
                completeTheMPAMStage("QA");
                break;
            case "AWAITING DISPATCH (LOCAL)":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("PRIVATE OFFICE");
                completeTheMPAMStage("PRIVATE OFFICE (TO AWAITING DISPATCH (LOCAL))");
                break;
            case "AWAITING DISPATCH (MINISTERIAL)":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("PRIVATE OFFICE");
                completeTheMPAMStage("PRIVATE OFFICE (TO AWAITING DISPATCH (MINISTERIAL))");
                break;
            case "CASE CLOSED":
                CreateMPAMCaseAndMoveItToTheSpecifiedStage("PRIVATE OFFICE");
                completeTheMPAMStage("PRIVATE OFFICE (TO CASE CLOSED)");
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }
}
