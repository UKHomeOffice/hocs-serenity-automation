package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class COMPProgressCase extends BasePage {

    CaseView caseView;

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    Correspondents correspondents;

    Documents documents;

    Dashboard dashboard;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsDraft complaintsDraft;

    ComplaintsQA compQA;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    String complaintType = "Service";

    public void moveCaseOfTypeFromCurrentStageToTargetStage(String caseType, String currentStage, String targetStage) {
        setComplaintTypeFromStageName(targetStage);
        targetStage = getSimplifiedStageName(targetStage);
        currentStage = getSimplifiedStageName(currentStage);
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheCOMPStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getSimplifiedStageName(String targetStage) {
        String[] words = targetStage.split(" ");
        String simplifiedStage = words[words.length - 1];
        if (simplifiedStage.equalsIgnoreCase("Escalate")) {
            simplifiedStage = "Escalated";
        }
        return simplifiedStage;
    }

    private void setComplaintTypeFromStageName(String targetStage) {
        if (containsIgnoreCase(targetStage, "Service")) {
            complaintType = "Service";
        } else if (containsIgnoreCase(targetStage, "Ex-Gratia")) {
            complaintType = "Ex-Gratia";
        } else if (containsIgnoreCase(targetStage, "Minor Misconduct") || containsIgnoreCase(targetStage, "MM")) {
            complaintType = "Minor Misconduct";
        }
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
            case "ESCALATED":
            case "CCH":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
                precedingStage = "QA";
                break;
            case "CLOSED":
                precedingStage = "SEND";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(String caseType, String complaintType, String targetStage) {
        this.complaintType = complaintType;
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    public void completeTheCOMPStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                moveCaseFromRegistrationToTriage();
                break;
            case "TRIAGE":
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "ESCALATED":
                        moveCaseFromTriageToEscalated();
                        break;
                    case "CCH":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveCaseFromDraftToQA();
                break;
            case "QA":
                moveCaseFromQAToSend();
                break;
            case "SEND":
                moveCaseFromSendToClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromRegistrationToTriage() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        complaintsRegistrationAndDataInput.selectASpecificComplaintType(complaintType);
        complaintsRegistrationAndDataInput.enterComplaintDetails();
        if (complaintType.equalsIgnoreCase("SERVICE")) {
            clickTheButton("Continue");
            complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
            waitABit(1000);
            complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
            complaintsRegistrationAndDataInput.selectAnOwningCSU();
        }
        clickTheButton("Finish");
    }

    public void moveCaseFromTriageToDraft() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriageAndInvestigation.enterDateOfAcceptance();
        }
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        complaintsTriageAndInvestigation.enterDetailsOnComplaintCategoryPage();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Capture Reason");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Contributions");
        if(sessionVariableCalled("isLoARequired").equals("Yes")) {
            complaintsTriageAndInvestigation.enterLoAReceivedDetails();
        }
        complaintsTriageAndInvestigation.selectReadyForDrafting();
    }

    public void moveCaseFromTriageToEscalated() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriageAndInvestigation.enterDateOfAcceptance();
        }
        clickTheButton("Continue");
        waitForPageWithTitle("Complaint Category");
        complaintsTriageAndInvestigation.enterDetailsOnComplaintCategoryPage();
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Case Details");
        clickTheButton("Continue");
        waitForPageWithTitle("Triage Capture Reason");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickTheButton("Continue");
        complaintsTriageAndInvestigation.escalateCaseToWFM();
    }

    public void moveCaseFromTriageToCCH() {
        complaintsTriageAndInvestigation.selectTransferComplaint();
        complaintsTriageAndInvestigation.enterTransferReason();
        complaintsTriageAndInvestigation.selectTransferToCCH();
    }

    public void moveCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("DRAFT");
        complaintsDraft.selectActionAtDraft("Send case to QA");
    }

    public void moveCaseFromQAToSend() {
        compQA.selectActionAtServiceQA("ACCEPT");
    }

    public void moveCaseFromSendToClosed() {
        documents.addADocumentOfDocumentType("Final Response");
        complaintsDispatchAndSend.selectACaseOutcome();
        complaintsDispatchAndSend.selectAResponseChannel();
        complaintsDispatchAndSend.enterADateOfResponse();
        clickTheButton("Complete");
    }

    public void generateCOMPSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
                createCase.createCSCaseOfType(infoValue);
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("COMP");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("COMP");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB(infoValue);
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference("Test entry for Home Office Reference");
                complaintsRegistrationAndDataInput.enterAPortReference();
                safeClickOn(continueButton);
                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                complaintsRegistrationAndDataInput.selectAComplaintChannel();
                complaintsRegistrationAndDataInput.selectASeverity();
                safeClickOn(continueButton);
                complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
                complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
                complaintsRegistrationAndDataInput.selectAnOwningCSU();
                safeClickOn(finishButton);
                break;
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("COMP");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("COMP");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB("01/01/2001");
                complaintsRegistrationAndDataInput.selectAGender();
                complaintsRegistrationAndDataInput.enterACompanyName();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(infoValue);
                complaintsRegistrationAndDataInput.enterAPortReference();
                safeClickOn(continueButton);
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
