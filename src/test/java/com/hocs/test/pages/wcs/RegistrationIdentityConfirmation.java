package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class RegistrationIdentityConfirmation extends BasePage {

    @FindBy(css = "label[for='IdentityConfirmed-Yes']")
    public WebElementFacade canYouConfirmClaimantIdentityYesRadioButton;

    @FindBy(css = "label[for='IdentityConfirmed-OnHold']")
    public WebElementFacade claimantOnHoldRadioButton;

    @FindBy(css = "label[for='IdentityConfirmed-No']")
    public WebElementFacade passToStageOneIdentityCheckRadioButton;

    @FindBy(xpath = "//a[@href='#IdentityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantIdentityErrorMessage;

    @FindBy(xpath = "//a[@href='#IdentityFailConfirm-error']")
    public WebElementFacade cannotIdentifyCloseClaimErrorMessage;

    public void confirmClaimantsIdentity(){
        clickOn(canYouConfirmClaimantIdentityYesRadioButton);
        clickOn(confirmButton);
    }

    public void assertCanYouConfirmClaimantIdentityErrorMessage() {
        assertThat(canYouConfirmClaimantIdentityErrorMessage.isVisible(), is(true));
    }

    public void assertCannotIdentifyCloseClaimErrorMessage() {
        assertThat(cannotIdentifyCloseClaimErrorMessage.isVisible(), is(true));
    }

    public void putTheClaimOnHold(){
        clickOn(claimantOnHoldRadioButton);
        clickOn(confirmButton);
    }

    public void passTheClaimToStage1IdentityCheck() {
        clickOn(passToStageOneIdentityCheckRadioButton);
        clickOn(confirmButton);
        waitABit(1000);
    }
}
