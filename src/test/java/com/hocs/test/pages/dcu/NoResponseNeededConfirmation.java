package com.hocs.test.pages.dcu;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NoResponseNeededConfirmation extends BasePage {

    @FindBy(css = "label[for='NoReplyNeededConfirmation-ACCEPT']")
    public WebElementFacade noResponseNeededYesRadioButton;

    @FindBy(css = "label[for='NoReplyNeededConfirmation-REJECT']")
    public WebElementFacade noResponseNeededNoRadioButton;

    @FindBy(xpath = "//a[@href='#NoReplyNeededConfirmation-error']")
    public WebElementFacade doYouAgreeIsRequiredErrorMessage;

    public void assertDoYouAgreeNRNErrorMessage() {
        doYouAgreeIsRequiredErrorMessage.shouldContainText("Do you agree that no response is needed? is required");
    }
}
