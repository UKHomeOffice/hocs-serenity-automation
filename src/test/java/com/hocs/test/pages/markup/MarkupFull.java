package com.hocs.test.pages.markup;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.Topics;
import com.hocs.test.pages.markup.MarkUpDecision;

public class MarkupFull extends Page {

    Topics topics;

    MarkUpDecision markUpDecision;

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

}
