package com.hocs.test.pages.dcu;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;

public class DCUProgressCase extends BasePage {

    AddCorrespondent addCorrespondent;

    DataInput dataInput;

    Markup markup;

    public void moveCaseFromDataInputToMarkup() {
        dataInput.fillAllMandatoryCorrespondenceFields();
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToInitialDraft() {
        markup.selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        markup.clickAddTopicLink();
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToNRNConfirmation() {
        selectNoResponseNeededRadioButton();
        safeClickOn(continueButton);
        enterANoResponseNeededReason();
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToTransferConfirmation() {
        selectReferToOGDRadioButton();
        safeClickOn(continueButton);
        enterAOGDDestination();
        enterAOGDReason();
        safeClickOn(finishButton);
    }

    public void moveCaseFromMarkupToInitialDraftWithSpecificTopic(String topic) {
        selectPolicyResponseRadioButton();
        safeClickOn(continueButton);
        markupAddTopics.enterATopic(topic);
        safeClickOn(finishButton);
    }
}
