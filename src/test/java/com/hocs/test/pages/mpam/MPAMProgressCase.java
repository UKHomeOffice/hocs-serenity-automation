package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import config.CaseType;
import java.text.ParseException;

public class MPAMProgressCase extends BasePage {

    CreateCase createCase;

    Dashboard dashboard;

    Documents documents;

    Campaign campaign;

    Correspondents correspondents;

    Creation creation;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    MTSDataInput mtsDataInput;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

    String businessArea = "UKVI";

    String refType = "Ministerial";

    String urgency = "Standard";

    String receivedDate = "N/A";

    String signOffTeam = "Home Secretary";

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(CaseType.MPAM, receivedDate);
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
        creation.selectASpecificBusinessArea(businessArea);
        creation.selectASpecificRefType(refType);
        if (refType.equalsIgnoreCase("MINISTERIAL")) {
            creation.selectASpecificMinisterialSignOffTeam(signOffTeam);
            creation.selectASpecificAddressee(signOffTeam);
        }
        creation.selectASpecificUrgency(urgency);
        creation.selectASpecificInboundChannel("Email");
        clickContinueButton();
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
        clickConfirmButton();
    }

    public void moveCaseFromDraftToQA() {
        safeClickOn(draft.moveToQARadioButton);
        setSessionVariable("action").to("Move to QA");
        clickConfirmButton();
    }

    public void moveCaseFromQAToNextStage() {
        safeClickOn(qa.approvedAtQARadioButton);
        clickConfirmButton();
    }

    public void moveCaseFromPrivateOfficeToCaseClosed() {
        dispatchStages.selectAResponseChannel();
        safeClickOn(dispatchStages.dispatchedRadioButtonAtPrivateOffice);
        clickConfirmButton();
        dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(dispatchStages.confirmAndCloseCaseButton);
    }

    public void moveCaseFromPrivateOfficeToAwaitingDispatchLocal() {
        dispatchStages.selectAResponseChannel();
        selectSpecificRadioButtonFromGroupWithHeading("Approved (local dispatch)", "Actions");
        clickConfirmButton();
    }

    public void moveCaseFromPrivateOfficeToAwaitingDispatchMinisterial() {
        dispatchStages.selectAResponseChannel();
        selectSpecificRadioButtonFromGroupWithHeading("Approved (ministerial dispatch)", "Actions");
        clickConfirmButton();
    }

    public void moveCaseFromAwaitingDispatchToCaseClosed() {
        dispatchStages.selectAResponseChannel();
        dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(dispatchStages.dispatchedRadioButtonAtDispatch);
        clickConfirmButton();
    }

    public void generateMPAMSearchCaseData(String infoValue, String infoType) throws ParseException {
        switch (infoType.toUpperCase()) {
            case "CASE REFERENCE":
            case "ACTIVE CASES ONLY":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MPAM);
                dashboard.goToDashboard();
                break;
            case "REFERENCE TYPE":
                createCaseAndMoveItToTargetStageWithSpecifiedReferenceType(infoValue, "Triage");
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                createCaseAndMoveItToTargetStageWithSpecifiedSignOffTeam(infoValue, "Triage");
                break;
            case "MEMBER OF PARLIAMENT NAME":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MPAM);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                creation.moveCaseWithSpecifiedMPCorrespondentToTriageStage(infoValue);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MPAM);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                creation.addCorrespondentWithSpecificReferenceToCase(infoValue);
                break;
            case "RECEIVED ON OR BEFORE DATE":
            case "RECEIVED ON OR AFTER DATE":
                createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(CaseType.MPAM, infoValue);
                dashboard.goToDashboard();
                break;
            case "CAMPAIGN":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MPAM);
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                moveCaseFromCreationToTriage();
                dashboard.getAndClaimCurrentCase();
                campaign.moveCaseFromAStageToCampaign(infoValue);
                break;
            case "CORRESPONDENT FULL NAME (APPLICANT OR CONSTITUENT)":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MPAM);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                creation.triggerMPCorrespondentIsMandatoryScreen();
                dashboard.goToDashboard();
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MTS);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                mtsDataInput.completeDataInputStageAndCloseMTSCase();
                break;
            case "ALL":
                createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(CaseType.MPAM, infoValue);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                creation.selectASpecificBusinessArea(businessArea);
                creation.selectASpecificRefType("Yes (Ministerial)");
                creation.selectASpecificMinisterialSignOffTeam("Home Secretary");
                creation.selectASpecificAddressee("Home Secretary");
                creation.selectASpecificUrgency(urgency);
                creation.selectASpecificInboundChannel("Email");
                clickContinueButton();
                correspondents.addASpecificMemberCorrespondent("Boris Johnson");
                correspondents.addANonMemberCorrespondentOfType("Constituent");
                correspondents.confirmPrimaryCorrespondent();
                dashboard.getAndClaimCurrentCase();
                campaign.moveCaseFromAStageToCampaign("Small boats");
                dashboard.goToDashboard();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }

    public void getMPAMCaseToPointOfAddingCorrespondents() {
        creation.completeRequiredQuestions();
        clickContinueButton();
    }
}
