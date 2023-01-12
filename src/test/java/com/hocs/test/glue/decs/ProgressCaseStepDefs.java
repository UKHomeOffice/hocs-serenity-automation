package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.complaints.BFProgressCase;
import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.complaints.IEDETProgressCase;
import com.hocs.test.pages.complaints.POGRProgressCase;
import com.hocs.test.pages.complaints.SMCProgressCase;
import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.to.TOProgressCase;
import com.hocs.test.pages.wcs.WCSProgressCase;
import config.CaseType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class ProgressCaseStepDefs extends BasePage {

    Dashboard dashboard;

    Workdays workdays;

    DCUProgressCase dcuProgressCase;

    MPAMProgressCase mpamProgressCase;

    COMPProgressCase compProgressCase;

    IEDETProgressCase iedetProgressCase;

    SMCProgressCase smcProgressCase;

    BFProgressCase bfProgressCase;

    FOIProgressCase foiProgressCase;

    WCSProgressCase wcsProgressCase;

    TOProgressCase toProgressCase;

    POGRProgressCase pogrProgressCase;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        switch (getCurrentCaseType()) {
            case MIN:
            case DTEN:
            case TRO:
                dcuProgressCase.completeTheDCUStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case MPAM:
                mpamProgressCase.completeTheMPAMStageSoThatCaseMovesToTargetStage(stage, "N/A");
                break;
            case COMP:
            case COMP2:
                compProgressCase.completeTheCOMPStageSoThatCaseMovesToTargetStage(stage, "N/A");
                break;
            case IEDET:
                iedetProgressCase.completeTheIEDETStage(stage,  "Happy Path");
                break;
            case SMC:
                smcProgressCase.completeTheSMCStage(stage);
                break;
            case BF:
            case BF2:
                bfProgressCase.completeTheBFStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case FOI:
                foiProgressCase.completeTheFOIStage(stage);
                break;
            case WCS:
                wcsProgressCase.completeTheWCSStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case TO:
                toProgressCase.completeTheTOStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case POGR:
            case POGR2:
                pogrProgressCase.completeThePOGRStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            default:
                pendingStep(getCurrentCaseType().toString() + " is not defined within " + getMethodName());
        }
    }

    @And("I move the case from {string} stage to {string} stage")
    public void iMoveTheCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        CaseType caseType = getCurrentCaseType();
        switch (caseType) {
            case MIN:
            case TRO:
            case DTEN:
                dcuProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            case MPAM:
                mpamProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case COMP:
            case COMP2:
            case COMP2DIRECT:
                compProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            case IEDET:
                iedetProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case SMC:
                smcProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case BF:
            case BF2:
                bfProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            case FOI:
                foiProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case WCS:
                wcsProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case TO:
                toProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case POGR:
            case POGR2:
                pogrProgressCase.moveCaseFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I create a {string} case/claim and move it to (the ){string}( stage)")
    public void iCreateACaseAndMoveItToAStage(String caseTypeString, String targetStage) {
        setCurrentCaseType(CaseType.valueOf(caseTypeString));
        iMoveTheCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    @And("I create a {string} case and move it to the {string} stage, recording all case data")
    public void iCreateACaseAndMoveItToTheStageRecordingAllCaseData(String caseTypeString, String targetStage) {
        setCurrentCaseType(CaseType.valueOf(caseTypeString));
        keepAllCaseDataWhenProgressingCase();
        iMoveTheCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    @And("I get a/an {string} case/claim at (the ){string}( stage)")
    public void iGetACaseAtAStage(String caseTypeString, String stage) {
        iCreateACaseAndMoveItToAStage(caseTypeString, stage);
        if (stage.equalsIgnoreCase("CASE CLOSED") || previousStageWouldHaveAutoAllocated(CaseType.valueOf(caseTypeString), stage)) {
            dashboard.getCurrentCase();
        } else {
            dashboard.getAndClaimCurrentCase();
        }
    }

    @And("I get a DCU {string} case at the {string} stage that should be copied to Number 10")
    public void iGetADCUCaseAtTheStageThatShouldBeCopiedToNumber(String caseTypeString, String stage) {
        dcuProgressCase.createCaseOfTypeAndMoveItToTargetStageWithCopyToNo10SetToYes(CaseType.valueOf(caseTypeString), stage);
        dashboard.getAndClaimCurrentCase();
    }

    @When("I create a MPAM case with {string} as the Business Area and {string} as the Reference Type and move it to the {string} stage")
    public void moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(String businessArea, String refType, String stage) {
        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedBusinessAreaAndReferenceType(businessArea, refType, stage);
    }

    @When("I create a MPAM case with {string} as the Reference Type and move it to the {string} stage")
    public void moveNewMPAMCaseWithSpecifiedReferenceTypeToStage(String refType, String stage) {
        moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage("UKVI", refType, stage);
    }

    @When("I create a high priority MPAM case and move it to the {string} stage")
    public void moveHighPriorityNewMPAMCaseToStage(String stage) {
        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedReceivedDateAndUrgency(workdays.getDateXWorkdaysAgoForGivenCaseType(20, CaseType.MPAM),
                "Immediate", stage);
    }

    @When("I create a {string} case for a {string} complaint and move it to {string}( stage)")
    public void iCreateACaseForAComplaintAndMoveItToStage(String caseTypeString, String complaintType, String stage) {
        if (caseTypeString.equalsIgnoreCase("BF")) {
            bfProgressCase.createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(CaseType.valueOf(caseTypeString), complaintType, stage);
        } else {
            compProgressCase.createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(CaseType.valueOf(caseTypeString), complaintType, stage);
        }
    }

    @And("I get a Treat Official case at the {string} stage that has Home Secretary Interest")
    public void iGetATOCaseAtTheStageThatHasHomeOfficeInterest(String stage) {
        toProgressCase.createCaseAndMoveItToTargetStageWithHomeSecInterestSetToYes(stage);
        dashboard.getAndClaimCurrentCase();
    }

    @And("I create a {string} case with {string} as the Business Area and move it to the {string} stage")
    public void iCreateAPOGRCaseWithAsTheBusinessAreaAndMoveItToTheStage(CaseType caseType, String businessArea, String stage) {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithSpecificBusinessArea(caseType, businessArea, stage);
    }

    @And("I get a {string} case with {string} as the Business Area at the {string} stage")
    public void iGetAPOGRCaseWithAsTheBusinessAreaAndMoveItToTheStage(String caseTypeString, String businessArea, String stage) {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithSpecificBusinessArea(CaseType.valueOf(caseTypeString), businessArea, stage);
        if (stage.equalsIgnoreCase("CASE CLOSED") || previousStageWouldHaveAutoAllocated(CaseType.valueOf(caseTypeString), stage) || stage.equalsIgnoreCase(
                "DISPATCH") && businessArea.equalsIgnoreCase("GRO")) {
            dashboard.getCurrentCase();
        } else {
            dashboard.getAndClaimCurrentCase();
        }
    }

    @And("I progress to the point of adding correspondents")
    public void iProgressToThePointOfAddingCorrespondents() {
        switch(getCurrentCaseType()) {
            case MIN:
            case DTEN:
            case TRO:
                dcuProgressCase.getDCUCaseToPointOfAddingCorrespondents();
                break;
            case MPAM:
                mpamProgressCase.getMPAMCaseToPointOfAddingCorrespondents();
                break;
            case POGR:
                pogrProgressCase.getPOGRCaseToPointOfAddingCorrespondents();
                break;
            case TO:
                toProgressCase.getTOCaseToPointOfAddingCorrespondents();
        }
    }

    private boolean previousStageWouldHaveAutoAllocated(CaseType caseType, String stage) {
        switch (caseType) {
            case FOI:
                return stage.equalsIgnoreCase("CASE CREATION") || stage.equalsIgnoreCase(
                        "ACCEPTANCE") || stage.equalsIgnoreCase("ALLOCATION");
            case POGR:
            case POGR2:
                return (stage.equalsIgnoreCase("DRAFT"));
            default:
                return false;
        }
    }
}
