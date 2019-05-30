package com.hocs.test.pages.draft;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;

public class Qa extends Page {


    @FindBy(css = "label[for='OfflineQA-TRUE']")
    public WebElementFacade offlineQaYesRadioButton;

    @FindBy(css = "label[for='OfflineQA-FALSE']")
    public WebElementFacade offlineQaNoRadioButton;

    private void clickOfflineQAYesRadioButton(){
        offlineQaYesRadioButton.click();
    }

    private void clickOfflineQANoRadioButton() {
        offlineQaNoRadioButton.click();
    }

    // Multi Step Methods

    public void dontQAOffline() {
        clickContinueButton();
        clickOfflineQANoRadioButton();
        clickContinueButton();
    }

    public void qaOffline() {
        clickContinueButton();
        clickOfflineQAYesRadioButton();
        clickContinueButton();
    }

}
