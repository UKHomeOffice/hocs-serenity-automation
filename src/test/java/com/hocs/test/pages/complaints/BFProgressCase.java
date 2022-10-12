package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import config.CaseType;

public class BFProgressCase extends BasePage {

    CaseView caseView;

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Dashboard dashboard;

    Documents documents;

    Correspondents correspondents;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    ComplaintsQA compQA;

    ComplaintsDraft complaintsDraft;

    String complaintType = "Service";

    public void moveCaseOfTypeFromCurrentStageToTargetStage(CaseType caseType, String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithDocument(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheBFStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(CaseType caseType, String complaintType, String targetStage) {
        this.complaintType = complaintType;
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "REGISTRATION";
                break;
            case "DRAFT":
            case "ESCALATED TO WFM":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "CASE CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeTheBFStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveBFCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
                        moveBFCaseFromTriageToDraft();
                        break;
                    case "ESCALATED TO WFM":
                        moveBFCaseFromTriageToEscalated();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveBFCaseFromDraftToQA();
                break;
            case "QA":
                moveBCaseFromQAToSend();
                break;
            case "SEND":
                moveBFCaseFromSendToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveBFCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        clickContinueButton();
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        if (bfCase()) {
            complaintsRegistrationAndDataInput.selectAComplaintType();
            clickContinueButton();
        }
        complaintsRegistrationAndDataInput.selectAComplaintChannel();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.enterAPreviousComplaintReference();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        clickFinishButton();
        System.out.println("Case moved from Case Registration to Case Triage");
    }

    public void moveBFCaseFromTriageToDraft() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        clickContinueButton();
        complaintsTriageAndInvestigation.enterDetailsOnBFTriageDetailsPage();
        complaintsTriageAndInvestigation.selectReadyForDrafting();
        System.out.println("Case moved from Case Triage to Draft");
    }

    public void moveBFCaseFromTriageToEscalated() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        clickContinueButton();
        waitForDECSPageWithTitle("Triage Details");
        complaintsTriageAndInvestigation.enterDetailsOnBFTriageDetailsPage();
        complaintsTriageAndInvestigation.escalateCaseToWFM();
        System.out.println("Case moved from Case Triage to Escalated to WFM");
    }

    public void moveBFCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("DRAFT");
        complaintsDraft.selectActionAtDraft("Send case to QA");
        System.out.println("Case moved from Draft to QA");
    }

    public void moveBCaseFromQAToSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
        System.out.println("Case moved from QA to Send draft response");
    }

    public void moveBFCaseFromSendToCaseClosed() {
        complaintsDispatchAndSend.selectBFCaseOutcomes();
        complaintsDispatchAndSend.selectAResponseChannel();
        complaintsDispatchAndSend.enterADateOfResponse();
        clickTheButton("Complete");
        System.out.println("Case moved from Send draft response to Case Closed");
    }

    public void generateBFSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfTypeWithDocument(CaseType.BF);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfTypeWithDocument(CaseType.BF);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB(infoValue);
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference("Test entry for Home Office Reference");
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickContinueButton();
                break;
            case "CASE REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.BF);
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.BF);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB("01/01/2001");
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(infoValue);
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickContinueButton();
                break;
            case "ALL":
                createCase.createCSCaseOfTypeWithDocument(CaseType.BF);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB("01/01/2001");
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference("Test entry for HO Reference");
                complaintsRegistrationAndDataInput.enterAPortReference();
                clickContinueButton();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}