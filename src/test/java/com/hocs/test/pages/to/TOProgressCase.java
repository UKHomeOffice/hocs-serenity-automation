package com.hocs.test.pages.to;

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

public class TOProgressCase extends BasePage {

    ConfirmationScreens confirmationScreens;

    CreateCase createCase;

    CaseView caseView;

    Dashboard dashboard;

    Correspondents correspondents;

    Documents documents;

    DataInput dataInput;

    Triage triage;

    Draft draft;

    Dispatch dispatch;

    Campaign campaign;

    StopList stopList;

    Boolean homeSecInterest = false;

    String businessArea = null;

    public void moveCaseFromCurrentStageToTargetStage(String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithDocument(CaseType.TO);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseFromCurrentStageToTargetStage(currentStage, precedingStage);
            }
            completeTheTOStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "DATA INPUT":
                precedingStage = "CREATE NEW CASE";
                break;
            case "TRIAGE":
                precedingStage = "DATA INPUT";
                break;
            case "DRAFT":
            case "CCH RETURNS":
            case "CAMPAIGN":
            case "STOP LIST":
                precedingStage = "TRIAGE";
                break;
            case "QA":
                precedingStage = "DRAFT";
                break;
            case "HOME SECRETARY INTEREST":
                homeSecInterest = true;
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

    public void createCaseAndMoveItToTargetStageWithHomeSecInterestSetToYes(String targetStage) {
        homeSecInterest = true;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void createCaseWithSpecificBusinessAreaAndMoveItToTargetStage(String businessArea, String targetStage) {
        this.businessArea = businessArea;
        moveCaseFromCurrentStageToTargetStage("CREATE NEW CASE", targetStage);
    }

    public void completeTheTOStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                moveCaseFromDataInputToTriage();
                break;
            case "TRIAGE":
                switch ((targetStage.toUpperCase())) {
                    case "DRAFT":
                        moveCaseFromTriageToDraft();
                        break;
                    case "CAMPAIGN":
                        moveCaseFromTriageToCampaign();
                        break;
                    case "STOP LIST":
                        moveCaseFromTriageToStopList();
                        break;
                    case "CCH RETURNS":
                        moveCaseFromTriageToCCH();
                        break;
                    default:
                        pendingStep(targetStage + " is not a defined target stage from Triage within " + getMethodName());
                }
                break;
            case "DRAFT":
                switch (targetStage.toUpperCase()) {
                    case "QA":
                        moveCaseFromDraftToQA();
                        break;
                    case "HOME SECRETARY INTEREST":
                        moveCaseFromDraftToDispatchOrHomeSecretaryInterest();
                        break;
                    default:
                        pendingStep(targetStage + " is not a defined target stage from Draft within " + getMethodName());
                }
                break;
            case "QA":
                moveCaseFromQAToDispatch();
                break;
            case "DISPATCH":
                moveCaseFromDispatchToCaseClosed();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println(stageToComplete + " stage completed");
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromDataInputToTriage() {
        if (this.businessArea == null) {
            dataInput.selectABusinessArea();
        } else {
            dataInput.selectSpecificBusinessArea(businessArea);
        }
        dataInput.selectAChannelRecieved();
        if (homeSecInterest) {
            dataInput.selectASpecificHomeSecInterestOption("Yes");
        } else {
            dataInput.selectASpecificHomeSecInterestOption("No");
        }
        clickContinueButton();
        correspondents.addANonMemberCorrespondentOfType("Correspondent");
        correspondents.confirmPrimaryCorrespondent();
        dataInput.selectWhetherToAddRecipient("No");
        clickContinueButton();
    }

    private void moveCaseFromTriageToDraft() {
        triage.selectAnEnquirySubject();
        triage.selectAnEnquiryReason();
        triage.selectABusinessUnitType();
        triage.selectABusinessUnit();
        selectTheStageAction("Ready to draft");
        clickFinishButton();
    }

    private void moveCaseFromTriageToCampaign() {
        selectTheStageAction("Put case into a campaign");
        clickFinishButton();
        campaign.selectACampaign();
        clickConfirmButton();
    }

    private void moveCaseFromTriageToStopList() {
        selectTheStageAction("Place on a stop list");
        clickFinishButton();
        stopList.selectAStopList();
        clickConfirmButton();
    }

    public void moveCaseFromTriageToCCH() {
        openOrCloseAccordionSection("Case Details");
        clickTheLink("Change business area");
        waitABit(500);
        selectSpecificRadioButtonFromGroupWithHeading("UKVI", "Business Area");
        clickFinishButton();
    }

    private void moveCaseFromDraftToQA() {
        documents.addADocumentOfDocumentType("Initial Draft");
        selectTheStageAction("Move to QA");
        clickFinishButton();
    }

    private void moveCaseFromDraftToDispatchOrHomeSecretaryInterest() {
        documents.addADocumentOfDocumentType("Initial Draft");
        selectTheStageAction("Send to Dispatch");
        clickFinishButton();
    }

    private void moveCaseFromQAToDispatch() {
        selectTheStageAction("Approve");
        clickFinishButton();
    }

    private void moveCaseFromDispatchToCaseClosed() {
        documents.addADocumentOfDocumentType("Final response");
        dispatch.enterDateOfDispatch();
        dispatch.selectAFinalDispatchChannel();
        documents.recordFinalResponseDocument();
        selectTheStageAction("Complete case");
        clickFinishButton();
    }

    public void generateTOSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE REFERENCE":
            case "ACTIVE CASES ONLY":
                createCase.createCSCaseOfTypeWithDocument(CaseType.TO);
                break;
            case "RECEIVED ON OR AFTER":
            case "RECEIVED ON OR BEFORE":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                if (!buttonIsVisible("Next")) {
                    dashboard.selectCreateSingleCaseLinkFromMenuBar();
                }
                createCase.selectCaseType(CaseType.TO);
                clickNextButton();
                createCase.editReceivedDate(infoValue);
                createCase.storeCorrespondenceReceivedDate();
                documents.uploadFileOfType("docx");
                clickTheButton("Create case");
                confirmationScreens.storeCaseReference();
                dashboard.goToDashboard();
                break;
            case "CORRESPONDENT FULL NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
            case "CORRESPONDENT REFERENCE NUMBER":
                createCase.createCSCaseOfTypeWithDocument(CaseType.TO);
                confirmationScreens.goToCaseFromConfirmationScreen();
                dashboard.claimCurrentCase();
                dataInput.selectABusinessArea();
                dataInput.selectAChannelRecieved();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Correspondent");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "CAMPAIGN":
                createCase.createCSCaseOfTypeWithDocument(CaseType.TO);
                confirmationScreens.goToCaseFromConfirmationScreen();
                dashboard.claimCurrentCase();
                dataInput.selectABusinessArea();
                dataInput.selectAChannelRecieved();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Correspondent");
                correspondents.confirmPrimaryCorrespondent();
                dataInput.selectWhetherToAddRecipient("No");
                clickContinueButton();
                dashboard.getAndClaimCurrentCase();
                triage.selectAnEnquirySubject();
                triage.selectAnEnquiryReason();
                triage.selectABusinessUnitType();
                triage.selectABusinessUnit();
                selectTheStageAction("Put case into a campaign");
                clickFinishButton();
                campaign.selectASpecificCampaign(infoValue);
                clickConfirmButton();
                dashboard.goToDashboard();
                break;
            case "ALL":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                if (!buttonIsVisible("Next")) {
                    dashboard.selectCreateSingleCaseLinkFromMenuBar();
                }
                createCase.createCSCaseOfTypeWithSpecificCorrespondenceReceivedDate(CaseType.TO, infoValue);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                dataInput.selectABusinessArea();
                dataInput.selectAChannelRecieved();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Correspondent");
                correspondents.confirmPrimaryCorrespondent();
                dataInput.selectWhetherToAddRecipient("No");
                clickContinueButton();
                dashboard.getAndClaimCurrentCase();
                triage.selectAnEnquirySubject();
                triage.selectAnEnquiryReason();
                triage.selectABusinessUnitType();
                triage.selectABusinessUnit();
                selectTheStageAction("Put case into a campaign");
                clickFinishButton();
                campaign.selectASpecificCampaign("Test campaign 1");
                clickConfirmButton();
                dashboard.goToDashboard();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }

    public void getTOCaseToPointOfAddingCorrespondents() {
        dataInput.selectABusinessArea();
        dataInput.selectAChannelRecieved();
        clickContinueButton();
    }
}
