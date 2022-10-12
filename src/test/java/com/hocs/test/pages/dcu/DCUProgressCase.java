package com.hocs.test.pages.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import config.CaseType;

public class DCUProgressCase extends BasePage {

    Dashboard dashboard;

    CreateCase createCase;

    Correspondents correspondents;

    Documents documents;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff ministerialSignOff;

    Dispatch dispatch;

    ConfirmationScreens confirmationScreens;

    CaseView caseView;

    Boolean copyToNumber10 = false;

    public void moveCaseOfTypeFromCurrentStageToTargetStage(CaseType caseType, String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(caseType, targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfTypeWithDocument(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheDCUStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(CaseType caseType, String targetStage) {
        String precedingStage = "";
        switch (targetStage.toUpperCase()) {
            case "DATA INPUT":
                precedingStage = "CREATE NEW CASE";
                break;
            case "MARKUP":
                precedingStage = "DATA INPUT";
                break;
            case "INITIAL DRAFT":
            case "TRANSFER CONFIRMATION":
            case "NO RESPONSE NEEDED CONFIRMATION":
                precedingStage = "MARKUP";
                break;
            case "QA RESPONSE":
                precedingStage = "INITIAL DRAFT";
                break;
            case "PRIVATE OFFICE APPROVAL":
                precedingStage = "QA RESPONSE";
                break;
            case "MINISTERIAL SIGN OFF":
                precedingStage = "PRIVATE OFFICE APPROVAL";
                break;
            case "DISPATCH":
                switch (caseType) {
                    case MIN:
                        precedingStage = "MINISTERIAL SIGN OFF";
                        break;
                    case DTEN:
                        precedingStage = "PRIVATE OFFICE APPROVAL";
                        break;
                    case TRO:
                        precedingStage = "QA RESPONSE";
                        break;
                    default:
                        pendingStep(caseType + " is not defined within " + getMethodName());
                }
                break;
            case "COPY TO NUMBER 10":
                copyToNumber10 = true;
                precedingStage = "DISPATCH";
                break;
            case "CASE CLOSED":
                precedingStage = "DISPATCH";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        return precedingStage;
    }

    public void createCaseOfTypeAndMoveItToTargetStageWithCopyToNo10SetToYes(CaseType caseType, String targetStage) {
        copyToNumber10 = true;
        moveCaseOfTypeFromCurrentStageToTargetStage(caseType, "CREATE NEW CASE", targetStage);
    }

    public void completeTheDCUStageSoThatCaseMovesToTargetStage(String stageToComplete, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (stageToComplete.toUpperCase()) {
            case "DATA INPUT":
                moveCaseFromDataInputToMarkup();
                break;
            case "MARKUP":
                switch (targetStage.toUpperCase()) {
                    case "INITIAL DRAFT":
                    case "HAPPY PATH":
                        moveCaseFromMarkupToInitialDraft();
                        break;
                    case "NO RESPONSE NEEDED CONFIRMATION":
                        moveCaseFromMarkupToNRNConfirmation();
                        break;
                    case "TRANSFER CONFIRMATION":
                        moveCaseFromMarkupToTransferConfirmation();
                        break;
                    default:
                        pendingStep(targetStage + " is not defined within " + getMethodName());
                }
                break;
            case "INITIAL DRAFT":
                moveCaseFromInitialDraftToQaResponse();
                break;
            case "QA RESPONSE":
                moveCaseFromQAResponseToPrivateOfficeApprovalOrDispatch();
                break;
            case "PRIVATE OFFICE APPROVAL":
                moveCaseFromPrivateOfficeApprovalToMinisterialSignOffOrDispatch();
                break;
            case "MINISTERIAL SIGN OFF":
                moveCaseFromMinisterialSignOffToDispatch();
                break;
            case "DISPATCH":
                moveCaseFromDispatchToCaseClosedOrCopyToNumber10();
                break;
            default:
                pendingStep(stageToComplete + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        System.out.println("Case moved from " + stageToComplete + " to " + targetStage);
        RecordCaseData.checkIfDataRecordsShouldBeWiped();
    }

    public void moveCaseFromDataInputToMarkup() {
        if (dtenCase()) {
            dataInput.enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(+10));
            dataInput.enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(+20));
            clickContinueButton();
        }
        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        dataInput.selectACorrespondenceReceivedChannel();
        if (minCase() || troCase()) {
            if (copyToNumber10) {
                dataInput.selectASpecificCopyToNoTenOption("Yes");
            } else {
                dataInput.selectASpecificCopyToNoTenOption("No");
            }
            dataInput.selectAHomeSecInterestOption();
        }
        if (minCase()) {
            dataInput.selectAHomeSecReplyOption();
        }
        clickContinueButton();
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    public void moveCaseFromMarkupToInitialDraft() {
        markup.selectPolicyResponseRadioButton();
        clickContinueButton();
        markup.addTopicToCase("Animal alternatives (3Rs)");
        markup.confirmPrimaryTopic();
        markup.confirmInitialDraftAndOrPrivateOfficeTeam();
    }

    public void moveCaseFromMarkupToInitialDraftWithSpecificTopic(String topic) {
        markup.selectPolicyResponseRadioButton();
        clickContinueButton();
        markup.addTopicToCase(topic);
        markup.confirmPrimaryTopic();
        markup.confirmInitialDraftAndOrPrivateOfficeTeam();
    }

    public void moveCaseFromMarkupToNRNConfirmation() {
        markup.selectNoResponseNeededRadioButton();
        clickContinueButton();
        markup.enterANoResponseNeededReason();
        clickFinishButton();
    }

    public void moveCaseFromMarkupToTransferConfirmation() {
        markup.selectReferToOGDRadioButton();
        clickContinueButton();
        markup.enterAOGDDestination();
        markup.enterAOGDReason();
        clickFinishButton();
    }

    public void moveCaseFromInitialDraftToQaResponse() {
        initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
        clickContinueButton();
        if (!dtenCase()) {
            initialDraft.selectSpecificResponseChannel("Letter");
            clickContinueButton();
        }
        documents.addADocumentOfDocumentType("DRAFT");
        waitABit(1000);
        documents.recordPrimaryDraftDocument();
        clickContinueButton();
        initialDraft.selectQAOfflineDecision("No");
        clickContinueButton();
    }

    public void moveCaseFromInitialDraftToPrivateOfficeApprovalOrDispatch() {
        initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
        clickContinueButton();
        if (!dtenCase()) {
            initialDraft.selectSpecificResponseChannel("Letter");
            clickContinueButton();
        }
        documents.addADocumentOfDocumentType("DRAFT");
        waitABit(1000);
        documents.recordPrimaryDraftDocument();
        clickContinueButton();
        initialDraft.selectQAOfflineDecision("Yes");
        clickContinueButton();
        initialDraft.selectAOfflineQAIndividual();
        clickFinishButton();
    }

    public void moveCaseFromQAResponseToPrivateOfficeApprovalOrDispatch() {
        qaResponse.selectApprovePrimaryDraftRadioButton();
        clickContinueButton();
    }

    public void moveCaseFromPrivateOfficeApprovalToMinisterialSignOffOrDispatch() {
        privateOfficeApproval.selectIfApproveResponse("Yes");
        clickContinueButton();
    }

    public void moveCaseFromMinisterialSignOffToDispatch() {
        ministerialSignOff.selectToApproveResponse("Yes");
        clickContinueButton();
    }

    public void moveCaseFromDispatchToCaseClosedOrCopyToNumber10() {
        dispatch.selectAbleToDispatch("Yes");
        clickContinueButton();
    }

    public void generateDCUSearchCaseData(String infoValue, String infoType) {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
                createCase.createCSCaseOfTypeWithDocument(CaseType.valueOf(infoValue));
                break;
            case "RECEIVED ON OR AFTER DATE":
            case "RECEIVED ON OR BEFORE DATE":
                createCase.createCSCase(CaseType.MIN, false, infoValue);
                dashboard.goToDashboard();
                break;
            case "MEMBER OF PARLIAMENT NAME":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                dataInput.fillAllMandatoryCorrespondenceFields();
                clickContinueButton();
                correspondents.addASpecificMemberCorrespondent(infoValue);
                clickFinishButton();
                break;
            case "PUBLIC CORRESPONDENT NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                dataInput.fillAllMandatoryCorrespondenceFields();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Constituent");
                correspondents.confirmPrimaryCorrespondent();
                dashboard.waitForDashboard();
                break;
            case "TOPIC":
                generateDCUSearchCaseData("Gordon Freeman", "Public Correspondent Name");
                dashboard.getAndClaimCurrentCase();
                moveCaseFromMarkupToInitialDraftWithSpecificTopic(infoValue);
                break;
            case "SIGN OFF TEAM":
                generateDCUSearchCaseData("Animal alternatives (3Rs)", "Topic");
                break;
            case "ACTIVE CASES ONLY":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
                break;
            case "HOME SECRETARY INTEREST":
                createCase.createCSCaseOfTypeWithDocument(CaseType.MIN);
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                dataInput.selectACorrespondenceReceivedChannel();
                dataInput.selectASpecificCopyToNoTenOption("No");
                dataInput.selectASpecificHomeSecInterestOption(infoValue);
                dataInput.selectAHomeSecReplyOption();
                clickContinueButton();
                correspondents.addANonMemberCorrespondentOfType("Constituent");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "ALL":
                createCase.createCSCase(CaseType.MIN, false, "01/01/2001");
                confirmationScreens.goToCaseFromConfirmationScreen();
                caseView.clickAllocateToMeLink();
                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                dataInput.selectACorrespondenceReceivedChannel();
                dataInput.selectASpecificCopyToNoTenOption("No");
                dataInput.selectASpecificHomeSecInterestOption("Yes");
                dataInput.selectAHomeSecReplyOption();
                clickContinueButton();
                correspondents.addASpecificMemberCorrespondent("Boris Johnson");
                correspondents.addANonMemberCorrespondentOfType("Constituent");
                correspondents.confirmPrimaryCorrespondent();
                dashboard.getAndClaimCurrentCase();
                moveCaseFromMarkupToInitialDraftWithSpecificTopic("Animal alternatives (3Rs)");
                dashboard.goToDashboard();
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }

    public void getDCUCaseToPointOfAddingCorrespondents() {
                dataInput.fillAllMandatoryCorrespondenceFields();
                clickContinueButton();
    }
}
