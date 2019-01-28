package com.hocs.test.pages.draft;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.draft.Qa;
import com.hocs.test.pages.draft.Draft;

public class Qa extends Page {

    Draft draft;

    Qa qa;

    @FindBy(css = "label[for='OfflineQA-TRUE']")
    private WebElementFacade offlineQaYesRadioButton;

    @FindBy(css = "label[for='OfflineQA-FALSE']")
    private WebElementFacade offlineQaNoRadioButton;

    public void clickOfflineQAYesRadioButton(){
        offlineQaYesRadioButton.click();
    }

    public void clickOfflineQANoRadioButton() {
        offlineQaNoRadioButton.click();
    }

    // Multi Step Methods

    public void dontQAOffline() {
        draft.clickContinueButton();
        qa.clickOfflineQANoRadioButton();
        draft.clickContinueButton();
    }

    public void qaOffline() {
        draft.clickContinueButton();
        qa.clickOfflineQAYesRadioButton();
        draft.clickContinueButton();
    }

}
