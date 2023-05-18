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
import com.hocs.test.pages.decs.SummaryTab;
import config.CaseType;

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

    SummaryTab summaryTab;

    String businessArea;

    public void moveCaseFromCurrentStageToTargetStage(CaseType caseType, String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithDocument(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeThePOGRStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    public void createCaseAndMoveItToTargetStageWithSpecificBusinessArea(CaseType caseType, String businessArea, String targetStage) {
        this.businessArea = businessArea;
        setSessionVariable("businessArea").to(businessArea);
        moveCaseFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithPrioritySetTo(CaseType caseType, boolean pogrPriority, String targetStage) {
        complaintsRegistrationAndDataInput.setPOGRPriority(pogrPriority);
        moveCaseFromCurrentStageToTargetStage(caseType,"N/A", targetStage);
    }

    public void createCaseAndMoveItToTargetStageWithSetBusinessAreaAndPriority(CaseType caseType, String businessArea, boolean pogrPriority,
            String targetStage) {
        this.businessArea = businessArea;
        complaintsRegistrationAndDataInput.setPOGRPriority(pogrPriority);
        moveCaseFromCurrentStageToTargetStage(caseType, "N/A", targetStage);
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
            case "ESCALATED":
                precedingStage = "INVESTIGATION";
                break;
            case "QA":
           // case "DISPATCH":
                precedingStage = "DRAFT";
                break;
            case "DISPATCH":
                precedingStage = "QA";
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
                switch (targetStage.toUpperCase()) {
                    case "DRAFT":
                        movePOGRCaseFromInvestigationToDraft();
                        break;
                    case "ESCALATED":
                        movePOGRCaseFromInvestigationToEscalated();
                        break;
                }
                break;
            case "DRAFT":
                movePOGRCaseFromDraftToQA();
              /*  switch (targetStage.toUpperCase()) {
                    case "QA":
                        movePOGRCaseFromDraftToQA();
                        break;
                    case "DISPATCH":
                        movePOGRCaseFromDraftToDispatch();
                        break;
                } */
                break;
            case "QA":
                movePOGRCaseFromQAToDispatch();
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
        if (!pogr2Case()) {
            if (businessArea == null) {
                complaintsRegistrationAndDataInput.selectBusinessArea();
            } else {
                complaintsRegistrationAndDataInput.selectSpecificBusinessArea(businessArea);
            }
            clickContinueButton();
        } else {
            summaryTab.selectSummaryTab();
            String businessArea = summaryTab.getSummaryTabValueForGivenHeader("Business Area");
            setSessionVariable("businessArea").to(businessArea);
        }
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        clickContinueButton();
        complaintsRegistrationAndDataInput.completeDataInputScreen();
        clickContinueButton();
        documents.addADocumentOfDocumentType("Interim Letter");
        complaintsRegistrationAndDataInput.enterDateInterimLetterSent();
        clickContinueButton();
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            complaintsRegistrationAndDataInput.selectInvestigatingTeam();
            clickFinishButton();
        }
    }

    public void movePOGRCaseFromInvestigationToDraft() {
        complaintsTriageAndInvestigation.acceptCaseAtInvestigation();
        if(sessionVariableCalled("isLoARequired").equals("Yes")) {
            complaintsTriageAndInvestigation.enterLoAReceivedDetails();
        }
        complaintsTriageAndInvestigation.selectAllInformationCollectedRespondAction();
        clickFinishButton();
    }

    private void movePOGRCaseFromInvestigationToEscalated() {
        complaintsTriageAndInvestigation.acceptCaseAtInvestigation();
        complaintsTriageAndInvestigation.escalateCaseToWFM();
    }

    public void movePOGRCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("Draft");
        complaintsDraft.selectActionAtDraft("Send to QA");
    }
 public void movePOGRCaseFromQAToDispatch() {
       complaintsDraft.selectActionAtQA("Accept");
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
                createCase.createCSCaseOfTypeWithDocument(CaseType.POGR);
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfTypeWithDocument(CaseType.POGR);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                complaintsRegistrationAndDataInput.selectBusinessArea();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "COMPLAINANT DATE OF BIRTH":
                createCase.createCSCaseOfTypeWithDocument(CaseType.POGR);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                complaintsRegistrationAndDataInput.selectBusinessArea();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.completeDataInputScreen();
                complaintsRegistrationAndDataInput.enterComplainantDOB(infoValue);
                clickContinueButton();
                break;
            case "ALL":
                createCase.createCSCaseOfTypeWithDocument(CaseType.POGR);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                complaintsRegistrationAndDataInput.selectBusinessArea();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Complainant");
                correspondents.confirmPrimaryCorrespondent();
                complaintsRegistrationAndDataInput.completeDataInputScreen();
                complaintsRegistrationAndDataInput.enterComplainantDOB("01/01/2001");
                clickContinueButton();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }

    public void getPOGRCaseToPointOfAddingCorrespondents() {
        complaintsRegistrationAndDataInput.selectBusinessArea();
        clickContinueButton();
    }
}
