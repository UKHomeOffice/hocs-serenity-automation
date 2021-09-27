package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import config.User;

public class DCUProgressCase extends BasePage {

    Correspondents correspondents;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    Documents documents;

    public void moveCaseFromDataInputToMarkup() {
        dataInput.fillAllMandatoryCorrespondenceFields();
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

    public void moveCaseFromInitialDraftToPrivateOffice() {
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
        safeClickOn(continueButton);
        initialDraft.selectAOfflineQAIndividual();
        safeClickOn(finishButton);
    }

    public void moveCaseFromQAResponseToPrivateOfficeApproval() {
    }
}
