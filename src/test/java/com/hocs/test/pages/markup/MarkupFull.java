package com.hocs.test.pages.markup;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.markup.MarkUpDecision;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.homepage.Homepage;

public class MarkupFull extends Page {

    Topics topics;

    MarkUpDecision markUpDecision;

    Workstacks workstacks;

    Homepage homepage;

    public void moveCaseFromMarkupToInitialDraft() {
        markUpDecision.clickPolicyResponseRadioButton();
        clickContinueButton();
        topics.clickAddTopicButton();
        topics.enterRealTopic();
        sleep(1000);
        clickAddButton();
        sleep(1000);
        clickContinueButton();
        sleep(1000);
        clickFinishButton();
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

}
