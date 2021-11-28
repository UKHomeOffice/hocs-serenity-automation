package com.hocs.test.pages.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Documents;
import com.hocs.test.pages.decs.RecordCaseData;

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

    Boolean copyToNumber10 = false;

    String preceedingStage;

    public void createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(String caseType, String stage) {
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                createCase.createCSCaseOfType(caseType);
                dashboard.goToDashboard();
                break;
            case "MARKUP":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "DATA INPUT");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("DATA INPUT", stage);
                break;
            case "INITIAL DRAFT":
            case "TRANSFER CONFIRMATION":
            case "NO RESPONSE NEEDED CONFIRMATION":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "MARKUP");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("MARKUP", stage);
                break;
            case "QA RESPONSE":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "INITIAL DRAFT");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("INITIAL DRAFT", stage);
                break;
            case "PRIVATE OFFICE APPROVAL":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "QA RESPONSE");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("QA RESPONSE", stage);
                break;
            case "MINISTERIAL SIGN OFF":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "PRIVATE OFFICE APPROVAL");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("PRIVATE OFFICE APPROVAL", stage);
                break;
            case "DISPATCH":
                switch (caseType) {
                    case "MIN":
                        createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "MINISTERIAL SIGN OFF");
                        completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("MINISTERIAL SIGN OFF", stage);
                        break;
                    case"DTEN":
                        createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "PRIVATE OFFICE APPROVAL");
                        completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("PRIVATE OFFICE APPROVAL", stage);
                        break;
                    case "TRO":
                        createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "QA RESPONSE");
                        completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("QA RESPONSE", stage);
                        break;
                }
                break;
            case "COPY TO NUMBER 10":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStageWithCopyToNo10SetToYes(caseType, "DISPATCH");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("DISPATCH", stage);
                break;
            case "CASE CLOSED":
                createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, "DISPATCH");
                completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage("DISPATCH", stage);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }


    public void moveDCUCaseFromStartingStageToTargetStage(String caseType, String startingStage, String targetStage) {
        switch (targetStage.toUpperCase()) {
            case "DATA INPUT":
                preceedingStage = "CASE CREATION";
                break;
            case "MARKUP":
                preceedingStage = "DATA INPUT";
                break;
            case "INITIAL DRAFT":
            case "TRANSFER CONFIRMATION":
            case "NO RESPONSE NEEDED CONFIRMATION":
                preceedingStage = "MARKUP";
                break;
            case "QA RESPONSE":
                preceedingStage = "INITIAL DRAFT";
                break;
            case "PRIVATE OFFICE APPROVAL":
                preceedingStage = "QA RESPONSE";
                break;
            case "MINISTERIAL SIGN OFF":
                preceedingStage = "PRIVATE OFFICE APPROVAL";
                break;
            case "DISPATCH":
                switch (caseType) {
                    case "MIN":
                        preceedingStage = "MINISTERIAL SIGN OFF";
                        break;
                    case"DTEN":
                        preceedingStage = "PRIVATE OFFICE APPROVAL";
                        break;
                    case "TRO":
                        preceedingStage = "QA RESPONSE";
                        break;
                }
                break;
            case "COPY TO NUMBER 10":
                copyToNumber10 = true;
                preceedingStage = "DISPATCH";
                break;
            case "CASE CLOSED":
                preceedingStage = "DISPATCH";
                break;
            default:
                pendingStep(targetStage + " is not defined within " + getMethodName());
        }
        if (preceedingStage.equals("CASE CREATION")) {
            createCase.createCSCaseOfType(caseType);
            dashboard.goToDashboard();
        } else {
            if (!startingStage.equalsIgnoreCase(preceedingStage)) {
                moveDCUCaseFromStartingStageToTargetStage(caseType, startingStage, preceedingStage);
            }
            completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage(preceedingStage, targetStage);
        }
    }

    public void createDCUCaseOfTypeAndMoveItToTheSpecifiedStageWithCopyToNo10SetToYes(String caseType, String stage) {
        copyToNumber10 = true;
        createDCUCaseOfTypeAndMoveItToTheSpecifiedStage(caseType, stage);
    }

    public void completeSpecifiedStageSoThatCaseMovesToSpecifiedTargetStage(String currentStage, String targetStage) {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        switch (currentStage.toUpperCase()) {
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
                pendingStep(currentStage + " is not defined within " + getMethodName());
        }
        dashboard.waitForDashboard();
        RecordCaseData.resetDataRecords();
    }

    public void moveCaseFromDataInputToMarkup() {
        dataInput.enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        dataInput.selectACorrespondenceReceivedChannel();
        if (copyToNumber10) {
            dataInput.selectASpecificCopyToNoTenOption("Yes");
        } else {
            dataInput.selectASpecificCopyToNoTenOption("No");
        }
        dataInput.selectAHomeSecInterestOption();
        dataInput.selectAHomeSecReplyOption();
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
        if (!dtenCase()){
            initialDraft.selectSpecificResponseChannel("Letter");
            safeClickOn(continueButton);
        }
        documents.addADraftDocumentAtDraftStage();
        waitABit(1000);
        documents.confirmOrApprovePrimaryDraft();
        initialDraft.selectQAOfflineDecision("No");
        clickTheButton("Continue");
    }

    public void moveCaseFromInitialDraftToPrivateOfficeApprovalOrDispatch() {
        initialDraft.selectIfCaseCanBeAnsweredByTeam("Yes");
        safeClickOn(continueButton);
        if (!dtenCase()){
            initialDraft.selectSpecificResponseChannel("Letter");
            safeClickOn(continueButton);
        }
        documents.addADraftDocumentAtDraftStage();
        waitABit(1000);
        documents.confirmOrApprovePrimaryDraft();
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
}
