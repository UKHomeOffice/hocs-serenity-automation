package com.hocs.test.pages.markup;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.homepage.Homepage;

public class MarkupFull extends Page {

    Topics topics;

    MarkUpDecision markUpDecision;

    Workstacks workstacks;

    Homepage homepage;

    public void moveCaseFromMarkupToInitialDraft() {
        clickOn(markUpDecision.policyResponseRadioButton);
        clickOn(continueButton);
        clickOn(addTopicButton);
        topics.enterRealTopic();
        clickOn(addButton);
        clickOn(continueButton);
        clickOn(finishButton);
    }

    public void markupStageFullFlow() {
        clickOn(markUpDecision.policyResponseRadioButton);
        clickOn(continueButton);
        clickOn(topics.addTopicButton);
        topics.enterRealTopic();
        clickOn(addButton);
        clickOn(continueButton);
        clickOn(finishButton);
    }

    public void completeMarkupStageAndStoreEnteredInformation() {
        markUpDecision.chooseResponseTypeAndRecordDecision();
        clickOn(continueButton);
        topics.clickAddTopicButton();
        topics.enterRealTopic();
        clickOn(topics.addButton);
        clickOn(continueButton);
        topics.recordSelectedDraftingAndPrivateOfficeTeams();
        clickOn(finishButton);
    }
}
