package com.hocs.test.pages.draft;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

class Qa {

    @FindBy(css = "label[for='OfflineQA-TRUE']")
    private WebElementFacade offlineQaYesRadioButton;

    @FindBy(css = "label[for='OfflineQA-FALSE']")
    private WebElementFacade offlineQaNoRadioButton;

}
