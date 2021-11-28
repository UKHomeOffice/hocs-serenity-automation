package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.complaints.ComplaintsProgressCase;
import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Workdays;
import com.hocs.test.pages.foi.FOIProgressCase;
import com.hocs.test.pages.mpam.Creation;
import com.hocs.test.pages.mpam.MPAMProgressCase;
import com.hocs.test.pages.wcs.WCSProgressCase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class EndToEndStepDefs extends BasePage {

    Dashboard dashboard;

    CreateCase createCase;

    ConfirmationScreens confirmationScreens;

    Workdays workdays;

    Creation creation;

    DCUProgressCase dcuProgressCase;

    MPAMProgressCase mpamProgressCase;

    ComplaintsProgressCase complaintsProgressCase;

    FOIProgressCase foiProgressCase;

    WCSProgressCase wcsProgressCase;

    @And("I complete the {string} stage")
    public void iCompleteTheStage(String stage) {
        String caseType = sessionVariableCalled("caseType");
        switch (caseType) {
            case "MIN":
            case "DTEN":
            case "TRO":
                dcuProgressCase.completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage(stage, "Happy Path");
                break;
            case "MPAM":
                mpamProgressCase.completeTheMPAMStage(stage);
                break;
            case "COMP":
            case "COMP2":
                complaintsProgressCase.completeTheCOMPStage(stage);
                break;
            case "IEDET":
                complaintsProgressCase.completeTheIEDETStage(stage);
                break;
            case "SMC":
                complaintsProgressCase.completeTheSMCStage(stage);
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
        switch (caseType.toUpperCase()) {
            case "MIN":
            case "TRO":
            case "DTEN":
                dcuProgressCase.createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, stage);
                break;
            case "MPAM":
                mpamProgressCase.CreateMPAMCaseAndMoveItToTheSpecifiedStage(stage);
                break;
            case "COMP":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "SERVICE TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO SERVICE TRIAGE)");
                        break;
                    case "EX-GRATIA TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO EX-GRATIA TRIAGE)");
                        break;
                    case "MINOR MISCONDUCT TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO MINOR MISCONDUCT TRIAGE)");
                        break;
                    case "SERVICE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE DRAFT)");
                        break;
                    case "EX-GRATIA RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO EX-GRATIA RESPONSE DRAFT)");
                        break;
                    case "MINOR MISCONDUCT RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT RESPONSE DRAFT)");
                        break;
                    case "SERVICE ESCALATED":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE ESCALATED)");
                        break;
                    case "EX-GRATIA ESCALATE":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO EX-GRATIA ESCALATE)");
                        break;
                    case "MINOR MISCONDUCT ESCALATE":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT ESCALATE)");
                        break;
                    case "CCH (FROM SERVICE TRIAGE)":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO CCH)");
                        break;
                    case "CCH (FROM EX-GRATIA TRIAGE)":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO CCH)");
                        break;
                    case "CCH (FROM MINOR MISCONDUCT TRIAGE)":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO CCH)");
                        break;
                    case "SERVICE QA":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE DRAFT");
                        iCompleteTheStage("SERVICE DRAFT");
                        break;
                    case "EX-GRATIA QA":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA RESPONSE DRAFT");
                        iCompleteTheStage("EX-GRATIA RESPONSE DRAFT");
                        break;
                    case "MINOR MISCONDUCT QA":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT RESPONSE DRAFT");
                        iCompleteTheStage("MINOR MISCONDUCT RESPONSE DRAFT");
                        break;
                    case "SERVICE SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE QA");
                        iCompleteTheStage("SERVICE QA");
                        break;
                    case "EX-GRATIA SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA QA");
                        iCompleteTheStage("EX-GRATIA QA");
                        break;
                    case "MINOR MISCONDUCT SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT QA");
                        iCompleteTheStage("MINOR MISCONDUCT QA");
                        break;
                    case "COMPLAINT CLOSED (FROM SERVICE SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "SERVICE SEND");
                        iCompleteTheStage("SERVICE SEND");
                        break;
                    case "COMPLAINT CLOSED (FROM EX-GRATIA SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "EX-GRATIA SEND");
                        iCompleteTheStage("EX-GRATIA SEND");
                        break;
                    case "COMPLAINT CLOSED (FROM MINOR MISCONDUCT SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "MINOR MISCONDUCT SEND");
                        iCompleteTheStage("MINOR MISCONDUCT SEND");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "COMP2":
                switch (stage.toUpperCase()) {
                    case "STAGE 2 REGISTRATION":
                        try {
                            complaintsProgressCase.attemptEscalateCOMPCaseToStage2();
                        } catch (Exception a) {
                            iCreateACaseAndMoveItToAStage("COMP", "COMPLAINT CLOSED (FROM SERVICE SEND)");
                            try {
                                complaintsProgressCase.attemptEscalateCOMPCaseToStage2();
                            } catch (Exception e) {
                                Assert.fail("Escalation hypertext not visible on retry");
                            }
                        }
                        waitABit(500);
                        createCase.createCOMP2Case();
                        dashboard.goToDashboard();
                        break;
                    case "STAGE 2 SERVICE TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO SERVICE TRIAGE)");
                        break;
                    case "STAGE 2 EX-GRATIA TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO EX-GRATIA TRIAGE)");
                        break;
                    case "STAGE 2 MM TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO MINOR MISCONDUCT TRIAGE)");
                        break;
                    case "STAGE 2 SERVICE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE TRIAGE");
                        iCompleteTheStage("SERVICE TRIAGE (TO SERVICE DRAFT)");
                        break;
                    case "STAGE 2 EX-GRATIA RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA TRIAGE");
                        iCompleteTheStage("EX-GRATIA TRIAGE (TO EX-GRATIA RESPONSE DRAFT)");
                        break;
                    case "STAGE 2 MM RESPONSE DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM TRIAGE");
                        iCompleteTheStage("MINOR MISCONDUCT TRIAGE (TO MINOR MISCONDUCT RESPONSE DRAFT)");
                        break;
                    case "STAGE 2 SERVICE QA":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE DRAFT");
                        iCompleteTheStage("SERVICE DRAFT");
                        break;
                    case "STAGE 2 EX-GRATIA QA":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA RESPONSE DRAFT");
                        iCompleteTheStage("EX-GRATIA RESPONSE DRAFT");
                        break;
                    case "STAGE 2 MM QA":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM RESPONSE DRAFT");
                        iCompleteTheStage("MINOR MISCONDUCT RESPONSE DRAFT");
                        break;
                    case "STAGE 2 SERVICE SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE QA");
                        iCompleteTheStage("SERVICE QA");
                        break;
                    case "STAGE 2 EX-GRATIA SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA QA");
                        iCompleteTheStage("EX-GRATIA QA");
                        break;
                    case "STAGE 2 MM SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM QA");
                        iCompleteTheStage("MINOR MISCONDUCT QA");
                        break;
                    case "STAGE 2 COMPLAINT CLOSED (FROM STAGE 2 SERVICE SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 SERVICE SEND");
                        iCompleteTheStage("SERVICE SEND");
                        break;
                    case "STAGE 2 COMPLAINT CLOSED (FROM STAGE 2 EX-GRATIA SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 EX-GRATIA SEND");
                        iCompleteTheStage("EX-GRATIA SEND");
                        break;
                    case "STAGE 2 COMPLAINT CLOSED (FROM STAGE 2 MM SEND)":
                        iCreateACaseAndMoveItToAStage(caseType, "STAGE 2 MM SEND");
                        iCompleteTheStage("MINOR MISCONDUCT SEND");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "IEDET":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO TRIAGE)");
                        break;
                    case "DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "TRIAGE");
                        iCompleteTheStage("TRIAGE (TO DRAFT)");
                        break;
                    case "SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "DRAFT");
                        iCompleteTheStage("DRAFT (TO SEND)");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "SEND");
                        iCompleteTheStage("SEND (TO CASE CLOSED)");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
                break;
            case "SMC":
                switch (stage.toUpperCase()) {
                    case "REGISTRATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "TRIAGE":
                        iCreateACaseAndMoveItToAStage(caseType, "REGISTRATION");
                        iCompleteTheStage("REGISTRATION (TO TRIAGE)");
                        break;
                    case "DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "TRIAGE");
                        iCompleteTheStage("TRIAGE (TO DRAFT)");
                        break;
                    case "SEND":
                        iCreateACaseAndMoveItToAStage(caseType, "DRAFT");
                        iCompleteTheStage("DRAFT (TO SEND)");
                        break;
                    case "CASE CLOSED":
                        iCreateACaseAndMoveItToAStage(caseType, "SEND");
                        iCompleteTheStage("SEND (TO CASE CLOSED)");
                        break;
                }
                break;
            case "FOI":
                switch (stage.toUpperCase()) {
                    case "CASE CREATION":
                        createCase.createCSCaseOfType(caseType);
                        dashboard.goToDashboard();
                        break;
                    case "ALLOCATION":
                        iCreateACaseAndMoveItToAStage(caseType, "CASE CREATION");
                        iCompleteTheStage("CASE CREATION");
                        break;
                    case "ACCEPTANCE":
                        iCreateACaseAndMoveItToAStage(caseType, "ALLOCATION");
                        iCompleteTheStage("ALLOCATION");
                        break;
                    case "CONSIDER AND DRAFT":
                        iCreateACaseAndMoveItToAStage(caseType, "ACCEPTANCE");
                        iCompleteTheStage("ACCEPTANCE");
                        break;
                    case "APPROVAL":
                        iCreateACaseAndMoveItToAStage(caseType, "CONSIDER AND DRAFT");
                        iCompleteTheStage("CONSIDER AND DRAFT");
                        break;
                    case "DISPATCH":
                        iCreateACaseAndMoveItToAStage(caseType, "APPROVAL");
                        iCompleteTheStage("APPROVAL");
                        break;
                    case "SOFT CLOSE":
                        iCreateACaseAndMoveItToAStage(caseType, "DISPATCH");
                        iCompleteTheStage("DISPATCH");
                        break;
                    default:
                        pendingStep(stage + " is not defined within " + getMethodName());
                }
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
    public void iGetAMINCaseAtTheDisptachStageThatShouldBeCopiedToNumber(String caseType, String stage) {
        dcuProgressCase.createDCUCaseOfTypeAndMoveItToTheSpecifiedStageWithCopyToNo10SetToYes(caseType, stage);
        dashboard.getAndClaimCurrentCase();
    }

    @When("I create a MPAM case with {string} as the Business Area and {string} as the Reference Type and move it to the "
            + "{string} stage")
    public void moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(String businessArea, String refType,
            String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                iCreateACaseAndMoveItToAStage("MPAM", "CREATION");
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedValuesToTriageStage(businessArea, refType, "Standard", "Home Secretary");
                dashboard.waitForDashboard();
                break;
            case "DRAFT":
                moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "TRIAGE");
                iCompleteTheStage("TRIAGE");
                break;
            case "QA":
                moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "DRAFT");
                iCompleteTheStage("DRAFT");
                break;
            case "PRIVATE OFFICE":
            case "AWAITING DISPATCH":
                moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "QA");
                iCompleteTheStage("QA");
                break;
            case "CASE CLOSED":
                if (refType.equalsIgnoreCase("MINISTERIAL")) {
                    moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType, "PRIVATE OFFICE");
                    iCompleteTheStage("PRIVATE OFFICE (TO CASE CLOSED)");
                } else {
                    moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage(businessArea, refType,
                            "AWAITING DISPATCH");
                    iCompleteTheStage("AWAITING DISPATCH");
                }
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    @When("I create a MPAM case with {string} as the Reference Type and move it to the {string} stage")
    public void moveNewMPAMCaseWithSpecifiedReferenceTypeToStage(String refType,
            String stage) {
        moveNewMPAMCaseWithSpecifiedBusinessAreaAndReferenceTypeToStage("UKVI", refType,
                stage);

    }

    @When("I create a high priority MPAM case and move it to the {string} stage")
    public void moveHighPriorityNewMPAMCaseToStage(String stage) {
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                createCase.createCSCaseOfTypeWithSetCorrespondenceReceivedDate("MPAM", workdays.getDateXWorkdaysAgo(20));
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedValuesToTriageStage("Windrush", "Ministerial", "Immediate", "Home Secretary");
                dashboard.waitForDashboard();
                break;
            case "DRAFT":
                moveHighPriorityNewMPAMCaseToStage("TRIAGE");
                iCompleteTheStage("TRIAGE");
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
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
