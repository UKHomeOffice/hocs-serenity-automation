package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.complaints.COMPProgressCase;
import com.hocs.test.pages.complaints.IEDETProgressCase;
import com.hocs.test.pages.complaints.SMCProgressCase;
import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.wcs.WCSProgressCase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class EndToEndStepDefs extends BasePage {

    Dashboard dashboard;

    CreateCase createCase;

    Workdays workdays;

    DCUProgressCase dcuProgressCase;

    MPAMProgressCase mpamProgressCase;

    COMPProgressCase compProgressCase;

    IEDETProgressCase iedetProgressCase;

    SMCProgressCase smcProgressCase;

    FOIProgressCase foiProgressCase;

    WCSProgressCase wcsProgressCase;

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
                wcsProgressCase.completeTheWCSStage(stage);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I create a {string} case and move it to (the ){string}( stage)")
    public void iCreateACaseAndMoveItToAStage(String caseType, String stage) {
        String startPoint = "CREATE NEW CASE";
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "TRO":
            case "DTEN":
                dcuProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, startPoint, stage);
                break;
            case "MPAM":
                mpamProgressCase.moveCaseFromCurrentStageToTargetStage(startPoint, stage);
                break;
            case "COMP":
            case "COMP2":
                compProgressCase.moveCaseOfTypeFromCurrentStageToTargetStage(caseType, startPoint, stage);
                break;
            case "IEDET":
                iedetProgressCase.moveCaseFromCurrentStageToTargetStage(startPoint, stage);
                break;
            case "SMC":
                smcProgressCase.moveCaseFromCurrentStageToTargetStage(startPoint, stage);
                break;
            case "FOI":
                foiProgressCase.moveCaseFromCurrentStageToTargetStage(startPoint, stage);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @And("I get a {string} case at (the ){string}( stage)")
    public void iGetACaseAtAStage(String caseType, String stage) {
        iCreateACaseAndMoveItToAStage(caseType, stage);
        if (stage.equalsIgnoreCase("CASE CLOSED")) {
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
        mpamProgressCase.createCaseAndMoveItToTargetStageWithSpecifiedReceivedDateAndUrgency(workdays.getDateXWorkdaysAgo(20), "Immediate", stage);
    }

    @When("I create a {string} case for a {string} complaint and move it to {string}( stage)")
    public void iCreateACOMPCaseForAComplaintAndMoveItToStage(String caseType, String complaintType, String stage) {
        compProgressCase.createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(caseType, complaintType, stage);
    }

    @And("I move the case/claim to the {string} stage")
    public void getCaseToSpecifiedStage(String stage) {
        switch (stage.toUpperCase()) {
            case "IDENTITY REJECTED":
                iCompleteTheStage("Registration (to Identity Rejected)");
                break;
            case "TIER 1 REVIEW (IR)":
                getCaseToSpecifiedStage("Identity Rejected");
                iCompleteTheStage("Identity Rejected (to Tier 1)");
                break;
            case "ARCHIVED IDENTITY REJECTED":
                getCaseToSpecifiedStage("Identity Rejected");
                iCompleteTheStage("Identity Rejected (to Archived)");
                break;
            case "ELIGIBILITY":
                iCompleteTheStage("Registration (to Eligibility)");
                break;
            case "ELIGIBILITY REJECTED":
                getCaseToSpecifiedStage("Eligibility");
                iCompleteTheStage("Eligibility (to Eligibility Rejected)");
                break;
            case "TIER 1 REVIEW (ER)":
                getCaseToSpecifiedStage("Eligibility Rejected");
                iCompleteTheStage("Eligibility Rejected (to Tier 1)");
                break;
            case "ARCHIVED ELIGIBILITY REJECTED":
                getCaseToSpecifiedStage("Eligibility Rejected");
                iCompleteTheStage("Eligibility Rejected (to Archived)");
                break;
            case "TRIAGE":
                iCompleteTheStage("Registration (to Triage)");
                break;
            case "CASEWORK":
                getCaseToSpecifiedStage("Triage");
                iCompleteTheStage("Triage");
                break;
            case "QA":
                getCaseToSpecifiedStage("Casework");
                iCompleteTheStage("Casework");
                break;
            case "PAYMENT PRE-OFFER CHECKLIST":
                getCaseToSpecifiedStage("QA");
                iCompleteTheStage("QA");
                break;
            case "OFFER APPROVAL":
                getCaseToSpecifiedStage("Payment Pre-offer Checklist");
                iCompleteTheStage("Payment Pre-offer Checklist");
                break;
            case "SEND OFFER":
                getCaseToSpecifiedStage("Offer Approval");
                iCompleteTheStage("Offer Approval");
                break;
            case "OFFER ACCEPTANCE":
                getCaseToSpecifiedStage("Send Offer");
                iCompleteTheStage("Send Offer (to Offer Acceptance)");
                break;
            case "NIL OFFER ACCEPTANCE":
                getCaseToSpecifiedStage("Send Offer");
                iCompleteTheStage("Send Offer (to Nil Offer Acceptance)");
                break;
            case "TIER 1":
                getCaseToSpecifiedStage("Offer Acceptance");
                iCompleteTheStage("Offer Acceptance (to Tier 1)");
                break;
            case "TIER 2":
                getCaseToSpecifiedStage("Tier 1");
                iCompleteTheStage("Tier 1");
                break;
            case "PAYMENT PREPARATION":
                getCaseToSpecifiedStage("Offer Acceptance");
                iCompleteTheStage("Offer Acceptance (to Payment Preparation)");
                break;
            case "PAYMENT APPROVAL":
                getCaseToSpecifiedStage("Payment Preparation");
                iCompleteTheStage("Payment Preparation");
                break;
            case "SEND PAYMENT":
                getCaseToSpecifiedStage("Payment Approval");
                iCompleteTheStage("Payment Approval");
                break;
            case "AWAITING PAYMENT CONFIRMATION":
                getCaseToSpecifiedStage("Send Payment");
                iCompleteTheStage("Send Payment");
                break;
            case "CLOSED":
                getCaseToSpecifiedStage("Awaiting Payment Confirmation");
                iCompleteTheStage("Awaiting Payment Confirmation");
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        if (!stage.equalsIgnoreCase("CLOSED")) {
            dashboard.getAndClaimCurrentCase();
        }
    }
}
