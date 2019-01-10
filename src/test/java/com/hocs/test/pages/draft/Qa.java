package com.hocs.test.pages.draft;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;

public class Qa extends Page {

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

}
