package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;

public class DCUProgressCase extends BasePage {

    Correspondents correspondents;

    DataInput dataInput;

    Markup markup;

    InitialDraft initialDraft;

    Documents documents;

    public void moveCaseFromDataInputToMarkup() {
        dataInput.fillAllMandatoryCorrespondenceFields();
        safeClickOn(continueButton);
        correspondents.addAPublicCorrespondentOfType("Constituent");
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
        if (dtenCase()) {
            initialDraft.dtenAcceptAndDraftALetter();
        } else {
            initialDraft.acceptAndDraftALetter();
        }
        documents.addADraftDocumentAtDraftStage();
        waitABit(1000);
        initialDraft.dontQAOffline();
    }

    public void moveCaseFromQAResponseToPrivateOfficeApproval() {
    }
}
