package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import config.CaseType;
import java.util.Random;

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

     String[] validChoices = new String[]{"Service", "Minor misconduct", "Ex-Gratia"};
    int rnd = new Random().nextInt(validChoices.length);
    String complaintType = validChoices[rnd];

    public void moveCaseOfTypeFromCurrentStageToTargetStage(CaseType caseType, String currentStage, String targetStage) {
        setComplaintTypeFromStageName(targetStage);
        targetStage = getSimplifiedStageName(targetStage);
        currentStage = getSimplifiedStageName(currentStage);
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithDocument(caseType);
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
        if (stringContainsCheckIgnoringCase(targetStage, "Service")) {
            complaintType = "Service";
        } else if (stringContainsCheckIgnoringCase(targetStage, "Ex-Gratia")) {
            complaintType = "Ex-Gratia";
        } else if (stringContainsCheckIgnoringCase(targetStage, "Minor Misconduct") || stringContainsCheckIgnoringCase(targetStage, "MM")) {
                complaintType = "Minor misconduct";
        } else if (stringContainsCheckIgnoringCase(targetStage, "Serious Misconduct") ) {
            complaintType = "Serious misconduct";

        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "REGISTRATION":
            case "STAGE_2_REGISTRATION":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
            case "PSU_REGISTRATION":
               if(compCase()){
                    precedingStage = "REGISTRATION";
                } else if (comp2Case() || comp2DirectCase()) {
               precedingStage = "STAGE_2_REGISTRATION";
                }
                break;
            case "DRAFT":
            case "ESCALATED":
            case "CCH":
            case "TRANSFER_PSU":
            case "TRIAGE_PSU_ESCALATED":
                precedingStage = "TRIAGE";
                break;
            case "ESCALATED_PSU":
                precedingStage = "ESCALATED";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "SEND":
            case "QA_ESCALATED_PSU":
                precedingStage = "QA";
                break;
            case "CLOSED":
                precedingStage = "SEND";
                break;
            case "PSU_TRIAGE":
                precedingStage = "PSU_REGISTRATION";
                break;
            case "PSU_COMPLAINT_OUTCOME":
                precedingStage = "PSU_TRIAGE";
                break;
            case "PSU_CLOSED":
                precedingStage = "PSU_COMPLAINT_OUTCOME";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithSpecifiedComplaintType(CaseType caseType, String complaintType, String targetStage) {
        this.complaintType = complaintType;
        setCurrentCaseType(caseType);
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    public void completeTheCOMPStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "REGISTRATION":
                switch (targetStage.toUpperCase()){
                    case "TRIAGE":
                        moveCaseFromRegistrationToTriage();
                        break;
                    case "PSU_REGISTRATION":
                        moveCaseFromRegistrationToPSURegistration();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "STAGE_2_REGISTRATION":
                switch (targetStage.toUpperCase()){
                    case "PSU_REGISTRATION":
                        moveCaseFromStage2RegistrationToPSURegistration();
                        break;
                    case "STAGE_2_CCT_TRIAGE_TEAM":
                        //placeholder
                        break;
                    case "TRIAGE":
                        moveCaseFromRegistrationToTriage();
                        break;
                    case "TRIAGE_PSU_ESCALATED":
                        moveCaseFromTriageToPSUEscalated();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
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
                    case "TRANSFER_PSU":
                        transferCaseFromRegistrationToPSURegistration();
                        break;
                    case "TRIAGE_PSU_ESCALATED":
                        moveCaseFromTriageToPSUEscalated();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "ESCALATED":
                switch (targetStage.toUpperCase()) {
                    case "ESCALATED_PSU":
                        moveCaseFromEscalatedToPSU();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "DRAFT":
                moveCaseFromDraftToQA();
                break;
            case "QA":
               switch (targetStage.toUpperCase()) {
                   case "QA":
                   case "SEND":
                       moveCaseFromQAToSend();
                       break;
                   case "QA_ESCALATED_PSU":
                       moveCaseFromQAToEscalateTOPSU();
                       break;
                default:
                    pendingStep(targetStage + " is not defined within " + getMethodName());
            }
            break;
            case "SEND":
                moveCaseFromSendToClosed();
                break;
            case "PSU_REGISTRATION":
                moveUKVICaseFromPSURegistrationToPSUTriage();
                break;
            case "PSU_TRIAGE":
                moveUKVICaseFromPSUTriageToPSUComplaintOutcome();
                break;
            case "PSU_COMPLAINT_OUTCOME":
                moveUKVICaseFromPSUComplaintOutcomeToPSUCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void transferCaseFromRegistrationToPSURegistration() {
        complaintsTriageAndInvestigation.transferCaseToPSU();
    }

    public void moveCaseFromQAToEscalateTOPSU() {
        complaintsTriageAndInvestigation.escalateToPSUFromQA();
    }

    public void moveCaseFromEscalatedToPSU() {
        complaintsTriageAndInvestigation.escalateToPSUFromEscalated();
    }

    public void moveCaseFromTriageToPSUEscalated() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriageAndInvestigation.enterDateOfAcceptance();
        }
        clickContinueButton();
        waitForDECSPageWithTitle("Complaint category");
        complaintsTriageAndInvestigation.enterDetailsOnComplaintCategoryPage();
        clickContinueButton();
        waitForDECSPageWithTitle("Triage case details");
        clickContinueButton();
        waitForDECSPageWithTitle("Triage capture reason");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickContinueButton();
        complaintsTriageAndInvestigation.escalateToPSUFromTriage();
    }

    private void moveUKVICaseFromPSUComplaintOutcomeToPSUCaseClosed() {
        complaintsRegistrationAndDataInput.selectRandomCaseOutcomeToProgress();
        clickTheButton("Submit");
        documents.addADocumentOfDocumentType("Final Response");
        complaintsDispatchAndSend.enterFinalResponseSentDate();
        clickTheButton("Close case");
        setCurrentCaseType(CaseType.valueOf("PSU"));
    }

    public void moveUKVICaseFromPSUTriageToPSUComplaintOutcome() {
        clickTheButton("Submit");
        assertExpectedErrorMessageIsDisplayed("Is this serious misconduct case for PSU to investigate? is required");
        complaintsRegistrationAndDataInput.selectYesForSeriousCase();
        clickTheButton("Submit");
        waitABit(1000);
        clickTheButton("Finish");
    }

    public void moveUKVICaseFromPSURegistrationToPSUTriage() {
        complaintsRegistrationAndDataInput.switchToPSUUser();
        complaintsRegistrationAndDataInput.enterAPSUReference();
         clickTheButton("Submit");
    }

    public void moveCaseFromRegistrationToTriage() {
        if(comp2DirectCase()){
           complaintsRegistrationAndDataInput.selectCOMP2CaseOption();
           clickTheButton("Submit");
        }
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        complaintsRegistrationAndDataInput.selectASpecificComplaintType(complaintType);
        if (complaintType.equalsIgnoreCase("SERVICE")) {
            waitABit(1000);
            complaintsTriageAndInvestigation.selectUKVIClaimCategory("Service");
            clickContinueButton();
        }
        complaintsRegistrationAndDataInput.enterComplaintDetails();
        if (complaintType.equalsIgnoreCase("Serious Misconduct")) {
            clickContinueButton();
            complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
            waitABit(1000);
            complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
            complaintsRegistrationAndDataInput.selectAnOwningCSU();
        }
            clickFinishButton();

    }

    public void moveCaseFromRegistrationToPSURegistration() {
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Serious misconduct");
        complaintsTriageAndInvestigation.selectUKVIClaimCategory("Serious misconduct");
        clickContinueButton();
        selectRandomRadioButtonFromGroupWithHeading("Channel");
        complaintsRegistrationAndDataInput.enterAPreviousUKVIPSUComplaintReference();
        complaintsRegistrationAndDataInput.enterAThirdPartyReferencePSU();
        clickTheButton("Finish and escalate to PSU");
        }
    public void moveCaseFromStage2RegistrationToPSURegistration() {

        if(comp2DirectCase()){
            complaintsRegistrationAndDataInput.selectCOMP2CaseOption();
            clickTheButton("Submit");
        }
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        correspondents.confirmPrimaryCorrespondent();
        complaintsRegistrationAndDataInput.enterComplainantDetails();
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Serious misconduct");
        complaintsTriageAndInvestigation.selectUKVIClaimCategory("Serious misconduct");
        clickContinueButton();
        selectRandomRadioButtonFromGroupWithHeading("Channel");
        complaintsRegistrationAndDataInput.enterAPreviousUKVIPSUComplaintReference();
        complaintsRegistrationAndDataInput.enterAThirdPartyReferencePSU();
        if(comp2DirectCase()){
            complaintsRegistrationAndDataInput.selectExternalContractor();
        }
        clickTheButton("Finish and escalate to PSU");
    }


    public void moveCaseFromTriageToDraft() {
        complaintsTriageAndInvestigation.selectAcceptCase();
        if (!complaintType.equalsIgnoreCase("SERVICE")) {
            complaintsTriageAndInvestigation.enterDateOfAcceptance();
        }
        clickContinueButton();
        waitForDECSPageWithTitle("Complaint category");
        complaintsTriageAndInvestigation.enterDetailsOnComplaintCategoryPage();
        clickContinueButton();
        waitForDECSPageWithTitle("Triage case details");
        clickContinueButton();
        waitForDECSPageWithTitle("Triage capture reason");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickContinueButton();
        waitForDECSPageWithTitle("Triage contributions");
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
        clickContinueButton();
        waitForDECSPageWithTitle("Complaint category");
        complaintsTriageAndInvestigation.enterDetailsOnComplaintCategoryPage();
        clickContinueButton();
        waitForDECSPageWithTitle("Triage case details");
        clickContinueButton();
        waitForDECSPageWithTitle("Triage capture reason");
        complaintsTriageAndInvestigation.enterDetailsOnTriageCaptureReasonPage();
        clickContinueButton();
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
                createCase.createCSCaseOfTypeWithDocument(CaseType.valueOf(infoValue));
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
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
                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Service");
                complaintsRegistrationAndDataInput.selectAComplaintChannel();
                complaintsRegistrationAndDataInput.selectASeverity();
                clickContinueButton();
                complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
                complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
                complaintsRegistrationAndDataInput.selectAnOwningCSU();
                clickFinishButton();
                break;
            case "CASE REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
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
            case "PSU REFERENCE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDetails();
                complaintsRegistrationAndDataInput.selectASpecificComplaintType("Serious misconduct");
                complaintsTriageAndInvestigation.selectUKVIClaimCategory("Serious misconduct");
                clickContinueButton();
                selectRandomRadioButtonFromGroupWithHeading("Channel");
                complaintsRegistrationAndDataInput.enterAPreviousUKVIPSUComplaintReference();
                complaintsRegistrationAndDataInput.enterAThirdPartyReferencePSU();
                clickTheButton("Finish and escalate to PSU");
                dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
                complaintsRegistrationAndDataInput.enterASpecificPSUReference("123456789");
                clickTheButton("Submit");
                break;
            case "ALL":
                createCase.createCSCaseOfTypeWithDocument(CaseType.COMP);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.enterComplainantDOB("01/01/2001");
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
