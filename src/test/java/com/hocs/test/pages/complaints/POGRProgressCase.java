package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

public class POGRProgressCase extends BasePage {

    CreateCase createCase;

    ComplaintsDraft complaintsDraft;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

    Correspondents correspondents;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    ComplaintsDispatchAndSend complaintsDispatchAndSend;

    Dashboard dashboard;

    Documents documents;

    String businessArea;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType("POGR");
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeThePOGRStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    public void createCaseAndMoveItToTargetStageWithSpecificBusinessArea(String businessArea, String targetStage) {
        this.businessArea = businessArea;
        moveCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithPrioritySetTo(boolean pogrPriority, String targetStage) {
        complaintsRegistrationAndDataInput.setPOGRPriority(pogrPriority);
        moveCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithSetBusinessAreaAndPriority(String businessArea, boolean pogrPriority, String targetStage) {
        this.businessArea = businessArea;
        complaintsRegistrationAndDataInput.setPOGRPriority(pogrPriority);
        moveCaseFromCurrentStageToTargetStage("N/A", targetStage);
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        setSessionVariable("targetStage").to(targetStage);
        switch (targetStage.toUpperCase()) {
            case "DATA INPUT":
                precedingStage = "CREATE NEW CASE";
                break;
            case "INVESTIGATION":
                precedingStage = "DATA INPUT";
                break;
            case "DRAFT":
                precedingStage = "INVESTIGATION";
                break;
            case "QA":
            case "DISPATCH":
                precedingStage = "DRAFT";
                break;
            case "CASE CLOSED":
                precedingStage = "DISPATCH";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void completeThePOGRStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                movePOGRCaseFromDataInputToInvestigation();
                break;
            case "INVESTIGATION":
                movePOGRCaseFromInvestigationToDraft();
                break;
            case "DRAFT":
                switch (targetStage.toUpperCase()) {
                    case "QA":
                        movePOGRCaseFromDraftToQA();
                        break;
                    case "DISPATCH":
                        movePOGRCaseFromDraftToDispatch();
                        break;
                }
                break;
            case "DISPATCH":
                movePOGRCaseFromDispatchToClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void movePOGRCaseFromDataInputToInvestigation() {
        if (businessArea == null) {
            complaintsRegistrationAndDataInput.selectBusinessArea();
        } else {
            complaintsRegistrationAndDataInput.selectSpecificBusinessArea(businessArea);
        }
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        safeClickOn(continueButton);
        complaintsRegistrationAndDataInput.completeDataInputScreen();
        safeClickOn(continueButton);
        documents.addADocumentOfDocumentType("Interim Letter");
        complaintsRegistrationAndDataInput.enterDateInterimLetterSent();
        safeClickOn(continueButton);
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            complaintsRegistrationAndDataInput.selectInvestigatingTeam();
            safeClickOn(finishButton);
        }
    }

    public void movePOGRCaseFromInvestigationToDraft() {
        complaintsTriageAndInvestigation.acceptCaseAtInvestigation();
        safeClickOn(continueButton);
        complaintsTriageAndInvestigation.selectAllInformationCollectedRespondAction();
        safeClickOn(finishButton);
    }

    public void movePOGRCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("Draft");
        complaintsDraft.selectActionAtDraft("Send to QA");
    }

    public void movePOGRCaseFromDraftToDispatch() {
        documents.addADocumentOfDocumentType("Draft");
        complaintsDraft.selectActionAtDraft("Send to Dispatch");
    }

    public void movePOGRCaseFromDispatchToClosed() {
        documents.addADocumentOfDocumentType("Final Response");
        complaintsDispatchAndSend.selectACaseOutcome();
        complaintsDispatchAndSend.selectAResponseChannel();
        complaintsDispatchAndSend.enterADateOfResponse();
        clickTheButton("Dispatch and Close case");
    }

    public void generatePOGRSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
            case "CASE REFERENCE":
                createCase.createCSCaseOfType("POGR");
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("POGR");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                complaintsRegistrationAndDataInput.selectBusinessArea();
                clickTheButton("Continue");
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfType("POGR");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                complaintsRegistrationAndDataInput.selectBusinessArea();
                clickTheButton("Continue");
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.completeDataInputScreen();
                complaintsRegistrationAndDataInput.enterComplainantDOB(infoValue);
                clickTheButton("Continue");
                break;
            case "COMPLAINANT HOME OFFICE REFERENCE":
                createCase.createCSCaseOfType("POGR");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                complaintsRegistrationAndDataInput.selectBusinessArea();
                clickTheButton("Continue");
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.completeDataInputScreen();
                complaintsRegistrationAndDataInput.enterAHomeOfficeReference(infoValue);
                clickTheButton("Continue");
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
