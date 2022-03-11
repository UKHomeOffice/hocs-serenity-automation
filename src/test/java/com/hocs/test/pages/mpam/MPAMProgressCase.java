package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import java.text.ParseException;

public class MPAMProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Campaign campaign;

    Correspondents correspondents;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    MTSDataInput mtsDataInput;

    String businessArea = "UKVI";

    String refType = "Ministerial";

    String urgency = "Standard";

    String receivedDate = "N/A";

    String signOffTeam = "Home Secretary";

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate("MPAM", receivedDate);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheMPAMStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "CREATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "CREATION";
                break;
            case "DRAFT":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "PRIVATE OFFICE":
                precedingStage = "QA";
                break;
            case "AWAITING DISPATCH":
                refType = "Official";
                precedingStage = "QA";
                break;
            case "AWAITING DISPATCH (LOCAL)":
            case "AWAITING DISPATCH (MINISTERIAL)":
                precedingStage = "PRIVATE OFFICE";
                break;
            case "CASE CLOSED":
                switch (refType.toUpperCase()) {
                    case "MINISTERIAL":
                        precedingStage = "PRIVATE OFFICE";
                        break;
                    case "OFFICIAL":
                        precedingStage = "AWAITING DISPATCH";
                        break;
                }
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void createCaseAndMoveItToTargetStageWithSpecifiedBusinessAreaAndReferenceType(String businessArea, String refType, String targetStage) {
        this.businessArea = businessArea;
        this.refType = refType;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithSpecifiedReferenceType(String refType, String targetStage) {
        this.refType = refType;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithSpecifiedUrgencyAndReferenceType(String urgency, String refType, String targetStage) {
        this.urgency = urgency;
        this.refType = refType;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithSpecifiedReceivedDateAndUrgency(String receivedDate, String urgency, String targetStage) {
        this.urgency = urgency;
        this.receivedDate = receivedDate;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithSpecifiedSignOffTeam(String signOffTeam, String targetStage) {
        this.signOffTeam = signOffTeam;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void completeTheMPAMStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "CREATION":
                moveCaseFromCreationToTriage();
                break;
            case "TRIAGE":
                moveCaseFromTriageToDraft();
                break;
            case "DRAFT":
                moveCaseFromDraftToQA();
                break;
            case "QA":
                moveCaseFromQAToNextStage();
                break;
            case "PRIVATE OFFICE":
                switch (targetStage.toUpperCase()) {
                    case "AWAITING DISPATCH (LOCAL)":
                        moveCaseFromPrivateOfficeToAwaitingDispatchLocal();
                        break;
                    case "AWAITING DISPATCH (MINISTERIAL)":
                        moveCaseFromPrivateOfficeToAwaitingDispatchMinisterial();
                        break;
                    case "CASE CLOSED":
                        moveCaseFromPrivateOfficeToCaseClosed();
                        break;
                }
                break;
            case "AWAITING DISPATCH":
                moveCaseFromAwaitingDispatchToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromCreationToTriage() {
        creation.selectBusinessArea(businessArea);
        creation.selectRefType(refType);
        if (refType.equalsIgnoreCase("MINISTERIAL")) {
            creation.selectMinisterialSignOffTeam(signOffTeam);
            creation.selectAddressee(signOffTeam);
        }
        creation.selectUrgency(urgency);
        creation.selectInboundChannel("Email");
        clickTheButton("Continue");
        correspondents.addAMemberCorrespondent();
        clickTheButton("Move to Triage");
    }

    public void moveCaseFromTriageToDraft() {
        safeClickOn(triage.setEnquiryHypertext);
        triage.selectEnquirySubject("Person Specific");
        triage.selectEnquiryReason("Allowed appeal enquiry update");
        triage.setBusinessUnit();
        safeClickOn(triage.readyToDraftRadioButton);
        setSessionVariable("action").to("Ready to draft");
        safeClickOn(confirmButton);
    }

    public void moveCaseFromDraftToQA() {
        safeClickOn(draft.moveToQARadioButton);
        setSessionVariable("action").to("Move to QA");
        safeClickOn(confirmButton);
    }

    public void moveCaseFromQAToNextStage() {
        safeClickOn(qa.approvedAtQARadioButton);
        safeClickOn(confirmButton);
    }

    public void moveCaseFromPrivateOfficeToCaseClosed() {
        dispatchStages.selectAResponseChannel();
        safeClickOn(dispatchStages.dispatchedRadioButtonAtPrivateOffice);
        safeClickOn(confirmButton);
        dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(dispatchStages.confirmAndCloseCaseButton);
    }

    public void moveCaseFromPrivateOfficeToAwaitingDispatchLocal() {
        dispatchStages.selectAResponseChannel();
        selectSpecificRadioButtonFromGroupWithHeading("Approved (local dispatch)", "Actions");
        safeClickOn(confirmButton);
    }

    public void moveCaseFromPrivateOfficeToAwaitingDispatchMinisterial() {
        dispatchStages.selectAResponseChannel();
        selectSpecificRadioButtonFromGroupWithHeading("Approved (ministerial dispatch)", "Actions");
        safeClickOn(confirmButton);
    }

    public void moveCaseFromAwaitingDispatchToCaseClosed() {
        dispatchStages.selectAResponseChannel();
        dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(dispatchStages.dispatchedRadioButtonAtDispatch);
        safeClickOn(confirmButton);
    }

    public void generateMPAMSearchCaseData(String infoValue, String infoType) throws ParseException {
        switch (infoType.toUpperCase()) {
            case "CASE REFERENCE":
            case "ACTIVE CASES ONLY":
                createCase.createCSCaseOfType("MPAM");
                dashboard.goToDashboard();
                break;
            case "REFERENCE TYPE":
                createCaseAndMoveItToTargetStageWithSpecifiedReferenceType(infoValue, "Triage");
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                createCaseAndMoveItToTargetStageWithSpecifiedSignOffTeam(infoValue, "Triage");
                break;
            case "MEMBER OF PARLIAMENT NAME":
                createCase.createCSCaseOfType("MPAM");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                creation.moveCaseWithSpecifiedMPCorrespondentToTriageStage(infoValue);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                createCase.createCSCaseOfType("MPAM");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                creation.addCorrespondentWithSpecificReferenceToCase(infoValue);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "Before", infoValue);
                break;
            case "RECEIVED ON OR AFTER DATE":
                createCase.createCaseReceivedFiveDaysBeforeOrAfterDate("MPAM", "After", infoValue);
                break;
            case "CAMPAIGN":
                createCase.createCSCaseOfType("MPAM");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                moveCaseFromCreationToTriage();
                dashboard.getAndClaimCurrentCase();
                campaign.moveCaseFromAStageToCampaign(infoValue);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                createCase.createCSCaseOfType("MPAM");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                creation.triggerMPCorrespondentIsMandatoryScreen();
                dashboard.goToDashboard();
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                createCase.createCSCaseOfType("MTS");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                mtsDataInput.completeDataInputStageAndCloseMTSCase();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
