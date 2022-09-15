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
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.to.TOProgressCase;
import com.hocs.test.pages.wcs.WCSProgressCase;
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
        String caseType = sessionVariableCalled("caseType");
        switch (caseType) {
            case "MIN":
            case "DTEN":
            case "TRO":
                dcuProgressCase.completeTheDCUStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case "MPAM":
                mpamProgressCase.completeTheMPAMStageSoThatCaseMovesToTargetStage(stage, "N/A");
                break;
            case "COMP":
            case "COMP2":
                compProgressCase.completeTheCOMPStageSoThatCaseMovesToTargetStage(stage, "N/A");
                break;
            case "IEDET":
                iedetProgressCase.completeTheIEDETStage(stage);
                break;
            case "SMC":
                smcProgressCase.completeTheSMCStage(stage);
                break;
            case "FOI":
                foiProgressCase.completeTheFOIStage(stage);
                break;
            case "WCS":
                wcsProgressCase.completeTheWCSStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case "TO":
                toProgressCase.completeTheTOStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            case "POGR":
                pogrProgressCase.completeThePOGRStageSoThatCaseMovesToTargetStage(stage, "Happy Path");
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I move the case from {string} stage to {string} stage")
    public void iMoveTheCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String caseType = sessionVariableCalled("caseType");
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "TRO":
            case "DTEN":
                dcuProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            case "MPAM":
                mpamProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case "COMP":
            case "COMP2":
                compProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            case "IEDET":
                iedetProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case "SMC":
                smcProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case "BF":
            case "BF2":
                bfProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            case "FOI":
                foiProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case "WCS":
                wcsProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case "TO":
                toProgressCase.moveCaseFromCurrentStageToTargetStage(currentStage, targetStage);
                break;
            case "POGR":
            case "POGR2":
                pogrProgressCase.moveCaseFromCurrentStageToTargetStage(caseType, currentStage, targetStage);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I create a {string} case/claim and move it to (the ){string}( stage)")
    public void iCreateACaseAndMoveItToAStage(String caseType, String targetStage) {
        setSessionVariable("caseType").to(caseType);
        iMoveTheCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    @And("I create a {string} case and move it to the {string} stage, recording all case data")
    public void iCreateACaseAndMoveItToTheStageRecordingAllCaseData(String caseType, String targetStage) {
        keepAllCaseDataWhenProgressingCase();
        setSessionVariable("caseType").to(caseType);
        iMoveTheCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    @And("I get a {string} case/claim at (the ){string}( stage)")
    public void iGetACaseAtAStage(String caseType, String stage) {
        iCreateACaseAndMoveItToAStage(caseType, stage);
        if (stage.equalsIgnoreCase("CASE CLOSED") || previousStageWouldHaveAutoAllocated(caseType, stage)) {
            dashboard.getCurrentCase();
        } else {
            dashboard.getAndClaimCurrentCase();
        }
    }

    @And("I get a DCU {string} case at the {string} stage that should be copied to Number 10")
    public void iGetADCUCaseAtTheStageThatShouldBeCopiedToNumber(String caseType, String stage) {
        dcuProgressCase.createCaseOfTypeAndMoveItToTargetStageWithCopyToNo10SetToYes(caseType, stage);
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
        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedReceivedDateAndUrgency(workdays.getDateXWorkdaysAgoForGivenCaseType(20, "MPAM"),
                "Immediate", stage);
    }

    @When("I create a {string} case for a {string} complaint and move it to {string}( stage)")
    public void iCreateACaseForAComplaintAndMoveItToStage(String caseType, String complaintType, String stage) {
        if (caseType.equalsIgnoreCase("BF")) {
            bfProgressCase.createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(caseType, complaintType, stage);
        } else {
            compProgressCase.createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(caseType, complaintType, stage);
        }
    }

    @And("I get a Treat Official case at the {string} stage that has Home Secretary Interest")
    public void iGetATOCaseAtTheStageThatHasHomeOfficeInterest(String stage) {
        toProgressCase.createCaseAndMoveItToTargetStageWithHomeSecInterestSetToYes(stage);
        dashboard.getAndClaimCurrentCase();
    }

    @And("I create a {string} case with {string} as the Business Area and move it to the {string} stage")
    public void iCreateAPOGRCaseWithAsTheBusinessAreaAndMoveItToTheStage(String caseType, String businessArea, String stage) {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithSpecificBusinessArea(caseType, businessArea, stage);
    }

    @And("I get a {string} case with {string} as the Business Area at the {string} stage")
    public void iGetAPOGRCaseWithAsTheBusinessAreaAndMoveItToTheStage(String caseType, String businessArea, String stage) {
        pogrProgressCase.createCaseAndMoveItToTargetStageWithSpecificBusinessArea(caseType, businessArea, stage);
        if (stage.equalsIgnoreCase("CASE CLOSED") || previousStageWouldHaveAutoAllocated("POGR", stage) || stage.equalsIgnoreCase("DISPATCH") && businessArea.equalsIgnoreCase("GRO")) {
            dashboard.getCurrentCase();
        } else {
            dashboard.getAndClaimCurrentCase();
        }
    }

    private boolean previousStageWouldHaveAutoAllocated(String caseType, String stage) {
        switch (caseType) {
            case "FOI":
                return stage.equalsIgnoreCase("CASE CREATION") || stage.equalsIgnoreCase(
                        "ACCEPTANCE") || stage.equalsIgnoreCase("ALLOCATION");
            case "POGR":
                return (stage.equalsIgnoreCase("DRAFT"));
            default:
                return false;
        }
    }
}
