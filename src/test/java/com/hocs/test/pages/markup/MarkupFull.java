package com.hocs.test.pages.markup;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.markup.MarkUpDecision;

public class MarkupFull extends Page {

    Topics topics;

    MarkUpDecision markUpDecision;

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
}
