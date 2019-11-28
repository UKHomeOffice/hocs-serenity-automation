package com.hocs.test.pages.markup;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;
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
//        waitABit(1000);
        clickOn(addButton);
//        waitABit(1000);
        clickOn(continueButton);
//        waitABit(1000);
        clickOn(finishButton);
    }

    public void markupStageFullFlow() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
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
