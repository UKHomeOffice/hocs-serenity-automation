package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class RegistrationIdentityStage1 extends BasePage {

    @FindBy(css = "label[for='IdentityConfirmed-Yes']")
    public WebElementFacade canYouConfirmClaimantIdentityYesRadioButton;

    @FindBy(css = "label[for='IdentityConfirmed-No']")
    public WebElementFacade canYouConfirmClaimantIdentityNoRadioButton;

    public void failIdentityCheck() {
        clickOn(canYouConfirmClaimantIdentityNoRadioButton);
        clickOn(confirmButton);
    }

    public void passClaimToEligibilityTeam() {
        waitABit(500);
        clickOn(canYouConfirmClaimantIdentityYesRadioButton);
        clickOn(confirmButton);
    }
}
