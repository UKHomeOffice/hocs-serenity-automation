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
        clickOn(markUpDecision.policyResponseRadioButton);
        clickOn(continueButton);
        clickOn(addTopicButton);
        topics.enterRealTopic();
//        sleep(1000);
        clickOn(addButton);
//        sleep(1000);
        clickOn(continueButton);
//        sleep(1000);
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

}
