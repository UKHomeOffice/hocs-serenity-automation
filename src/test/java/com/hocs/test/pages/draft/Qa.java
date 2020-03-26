package com.hocs.test.pages.draft;

import config.Users;
import java.util.concurrent.TimeUnit;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.base_page.Page;

public class Qa extends Page {


    @FindBy(css = "label[for='OfflineQA-TRUE']")
    public WebElementFacade offlineQaYesRadioButton;

    @FindBy(css = "label[for='OfflineQA-FALSE']")
    public WebElementFacade offlineQaNoRadioButton;

    @FindBy(id = "OfflineQaUser")
    public WebElementFacade allocateToOfflineQaDropdown;

    // Multi Step Methods

    public void dontQAOffline() {
        continueButton.withTimeoutOf(1, TimeUnit.MINUTES).waitUntilVisible();
        clickOn(continueButton);
        clickOn(offlineQaNoRadioButton);
        clickOn(continueButton);
    }

    public void selectOfflineQualityAssurer(String userName) {
        allocateToOfflineQaDropdown.selectByVisibleText(userName);
    }
}
