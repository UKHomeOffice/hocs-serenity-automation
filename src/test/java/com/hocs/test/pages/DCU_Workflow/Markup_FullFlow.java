package com.hocs.test.pages.DCU_Workflow;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.Homepage;

public class Markup_FullFlow extends BasePage {

    Markup_AddTopics markupAddTopics;

    Markup_Decision markupDecision;

    public void moveCaseFromMarkupToInitialDraft() {
        safeClickOn(markupDecision.policyResponseRadioButton);
        safeClickOn(continueButton);
        safeClickOn(addTopicButton);
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }

    public void markupStageFullFlow() {
        safeClickOn(markupDecision.policyResponseRadioButton);
        safeClickOn(continueButton);
        safeClickOn(markupAddTopics.addTopicButton);
        markupAddTopics.enterRealTopic();
        safeClickOn(addButton);
        safeClickOn(continueButton);
        safeClickOn(finishButton);
    }

    public void completeMarkupStageAndStoreEnteredInformation() {
        markupDecision.chooseResponseTypeAndRecordDecision();
        safeClickOn(continueButton);
        markupAddTopics.clickAddTopicButton();
        markupAddTopics.enterRealTopic();
        safeClickOn(markupAddTopics.addButton);
        safeClickOn(continueButton);
        markupAddTopics.recordSelectedDraftingAndPrivateOfficeTeams();
        safeClickOn(finishButton);
    }
}
