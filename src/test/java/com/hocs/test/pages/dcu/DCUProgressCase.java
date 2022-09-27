package com.hocs.test.pages.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.ConfirmationScreens;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;
import java.text.ParseException;

public class DCUProgressCase extends BasePage {

    Dashboard dashboard;

    CreateCase createCase;

    Correspondents correspondents;

    ConfirmationScreens confirmationScreens;

    Documents documents;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    QAResponse qaResponse;

    PrivateOfficeApproval privateOfficeApproval;

    MinisterialSignOff ministerialSignOff;

    Dispatch dispatch;

    Boolean copyToNumber10 = false;

    public void moveCaseOfTypeFromCurrentStageToTargetStage(String caseType, String currentStage, String targetStage) {
        String precedingStage = getStageThatPrecedesTargetStage(caseType, targetStage);
        if (precedingStage.equals("CREATE NEW CASE")) {
            createCase.createCSCaseOfType(caseType);
            dashboard.goToDashboard();
        } else {
            if (!precedingStage.equalsIgnoreCase(currentStage)) {
                moveCaseOfTypeFromCurrentStageToTargetStage(caseType, currentStage, precedingStage);
            }
            completeTheDCUStageSoThatCaseMovesToTargetStage(precedingStage, targetStage);
        }
    }

    private String getStageThatPrecedesTargetStage(String caseType, String targetStage) {
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
                    case "MIN":
                        precedingStage = "MINISTERIAL SIGN OFF";
                        break;
                    case "DTEN":
                        precedingStage = "PRIVATE OFFICE APPROVAL";
                        break;
                    case "TRO":
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

    public void createCaseOfTypeAndMoveItToTargetStageWithCopyToNo10SetToYes(String caseType, String targetStage) {
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
            safeClickOn(continueButton);
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
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Constituent");
        correspondents.confirmPrimaryCorrespondent();
    }

    public void moveCaseFromMarkupToInitialDraft() {
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        markup.addTopicToCase("Animal alternatives (3Rs)");
        markup.confirmPrimaryTopic();
        markup.confirmInitialDraftAndOrPrivateOfficeTeam();
    }

    public void moveCaseFromMarkupToInitialDraftWithSpecificTopic(String topic) {
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        markup.addTopicToCase(topic);
        markup.confirmPrimaryTopic();
        markup.confirmInitialDraftAndOrPrivateOfficeTeam();
    }

    public void moveCaseFromMarkupToNRNConfirmation() {
        markup.selectNoResponseNeededRadioButton();
        safeClickOn(continueButton);
        markup.enterANoResponseNeededReason();
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToTransferConfirmation() {
        markup.selectReferToOGDRadioButton();
        safeClickOn(continueButton);
        markup.enterAOGDDestination();
        markup.enterAOGDReason();
        safeClickOn(finishButton);
    }

    public void moveCaseFromInitialDraftToQaResponse() {
        initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
        safeClickOn(continueButton);
        if (!dtenCase()) {
            initialDraft.selectSpecificResponseChannel("Letter");
            safeClickOn(continueButton);
        }
        documents.addADocumentOfDocumentType("DRAFT");
        waitABit(1000);
        documents.recordPrimaryDraftDocument();
        clickTheButton("Continue");
        initialDraft.selectQAOfflineDecision("No");
        clickTheButton("Continue");
    }

    public void moveCaseFromInitialDraftToPrivateOfficeApprovalOrDispatch() {
        initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
        safeClickOn(continueButton);
        if (!dtenCase()) {
            initialDraft.selectSpecificResponseChannel("Letter");
            safeClickOn(continueButton);
        }
        documents.addADocumentOfDocumentType("DRAFT");
        waitABit(1000);
        documents.recordPrimaryDraftDocument();
        clickTheButton("Continue");
        initialDraft.selectQAOfflineDecision("Yes");
        safeClickOn(continueButton);
        initialDraft.selectAOfflineQAIndividual();
        safeClickOn(finishButton);
    }

    public void moveCaseFromQAResponseToPrivateOfficeApprovalOrDispatch() {
        qaResponse.selectApprovePrimaryDraftRadioButton();
        clickTheButton("Continue");
    }

    public void moveCaseFromPrivateOfficeApprovalToMinisterialSignOffOrDispatch() {
        privateOfficeApproval.selectIfApproveResponse("Yes");
        safeClickOn(continueButton);
    }

    public void moveCaseFromMinisterialSignOffToDispatch() {
        ministerialSignOff.selectToApproveResponse("Yes");
        safeClickOn(continueButton);
    }

    public void moveCaseFromDispatchToCaseClosedOrCopyToNumber10() {
        dispatch.selectAbleToDispatch("Yes");
        safeClickOn(continueButton);
    }

    public void generateDCUSearchCaseData(String infoValue, String infoType) throws ParseException {
        switch (infoType.toUpperCase()) {
            case "CASE TYPE":
                createCase.createCSCaseOfType(infoValue);
                break;
            case "RECEIVED ON OR AFTER DATE":
            case "RECEIVED ON OR BEFORE DATE":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                if (!nextButton.isVisible()) {
                    dashboard.selectCreateSingleCaseLinkFromMenuBar();
                }
                createCase.selectCaseType("MIN");
                clickTheButton("Next");
                createCase.editReceivedDate(infoValue);
                createCase.storeCorrespondenceReceivedDate();
                documents.uploadFileOfType("docx");
                clickTheButton("Create case");
                confirmationScreens.storeCaseReference();
                dashboard.goToDashboard();
                break;
            case "MEMBER OF PARLIAMENT NAME":
                createCase.createCSCaseOfType("MIN");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                dataInput.fillAllMandatoryCorrespondenceFields();
                clickContinueButton();
                correspondents.addASpecificMemberCorrespondent(infoValue);
                safeClickOn(finishButton);
                break;
            case "PUBLIC CORRESPONDENT NAME":
            case "CORRESPONDENT POSTCODE":
            case "CORRESPONDENT EMAIL ADDRESS":
                createCase.createCSCaseOfType("MIN");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
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
                createCase.createCSCaseOfType("MIN");
                break;
            case "HOME SECRETARY INTEREST":
                createCase.createCSCaseOfType("MIN");
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                dataInput.selectACorrespondenceReceivedChannel();
                dataInput.selectASpecificCopyToNoTenOption("No");
                dataInput.selectASpecificHomeSecInterestOption(infoValue);
                dataInput.selectAHomeSecReplyOption();
                safeClickOn(continueButton);
                correspondents.addANonMemberCorrespondentOfType("Constituent");
                correspondents.confirmPrimaryCorrespondent();
                break;
            case "ALL":
                dashboard.selectCreateSingleCaseLinkFromMenuBar();
                if (!nextButton.isVisible()) {
                    dashboard.selectCreateSingleCaseLinkFromMenuBar();
                }
                createCase.selectCaseType("MIN");
                clickTheButton("Next");
                createCase.editReceivedDate("01/01/2022");
                createCase.storeCorrespondenceReceivedDate();
                documents.uploadFileOfType("docx");
                clickTheButton("Create case");
                confirmationScreens.storeCaseReference();
                dashboard.goToDashboard();
                dashboard.getAndClaimCurrentCase();
                dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
                dataInput.selectACorrespondenceReceivedChannel();
                dataInput.selectASpecificCopyToNoTenOption("No");
                dataInput.selectASpecificHomeSecInterestOption("Yes");
                dataInput.selectAHomeSecReplyOption();
                safeClickOn(continueButton);
                correspondents.addASpecificMemberCorrespondent("Boris Johnson");
                correspondents.addANonMemberCorrespondentOfType("Constituent");
                correspondents.confirmPrimaryCorrespondent();
                dashboard.getAndClaimCurrentCase();
                moveCaseFromMarkupToInitialDraftWithSpecificTopic("Animal alternatives (3Rs)");
                break;
            default:
                pendingStep(infoType + " is not defined within " + getMethodName());
        }
    }
}
