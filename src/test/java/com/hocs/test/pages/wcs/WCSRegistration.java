package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class WCSRegistration extends BasePage {

    @FindBy(xpath = "//h1[text()='Registration Windrush Scheme check']")
    public WebElementFacade registrationSchemeCheckTitle;

    @FindBy(css = "label[for='IdentityConfirmed-Yes']")
    public WebElementFacade canYouConfirmTheClaimantsIDYesRadioButton;

    @FindBy(xpath = "//a[@href='#IdentityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantStatusErrorMessage;

    @FindBy(xpath = "//a[@href='#FormSubmitted-error']")
    public WebElementFacade howWasTheFormSubmittedIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#IsAssistAdviser-error']")
    public WebElementFacade formSentThroughClaimantAdviserIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ClaimedFrom-error']")
    public WebElementFacade whereIsTheClaimFromIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ClaimType-error']")
    public WebElementFacade typeOfClaimIsRequiredErrorMessage;

    @FindBy(css = "label[for='RegistrationNextTeamOutput-SendToEligibility']")
    public WebElementFacade idConfirmedEligibilityNotConfirmedButton;

    @FindBy(css = "label[for='RegistrationNextTeamOutput-SendToTriage']")
    public WebElementFacade idAndEligibilityConfirmedSendToTriageButton;

    @FindBy(xpath = "//a[@href='#RegistrationNextTeamOutput-error']")
    public WebElementFacade whereShouldTheCaseProgressToIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#ReceivedDate-error']")
    public WebElementFacade dateClaimReceivedIsRequiredErrorMessage;

    public void selectConfirmTheClaimantsIDRadioButton() {
        clickOn(canYouConfirmTheClaimantsIDYesRadioButton);
    }

    public void selectIDConfirmedEligibilityNotConfirmed() {
        clickOn(idConfirmedEligibilityNotConfirmedButton);
        clickOn(confirmButton);
    }

    public void selectIDAndEligibilityConfirmed() {
        clickOn(idAndEligibilityConfirmedSendToTriageButton);
        clickOn(confirmButton);
    }

    public void assertCanYouConfirmClaimantStatusErrorMessage() {
        assertThat(canYouConfirmClaimantStatusErrorMessage.isVisible(), is(true));
    }

    public void assertTypeOfClaimErrorMessage() {
        assertThat(typeOfClaimIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertHowWasFormSubmittedErrorMessage() {
        assertThat(howWasTheFormSubmittedIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertFormSentThroughClaimantAssistanceErrorMessage() {
        assertThat(formSentThroughClaimantAdviserIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertWhereIsTheClaimFromErrorMessage() {
        assertThat(whereIsTheClaimFromIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertDateClaimReceivedErrorMessage() {
        assertThat(dateClaimReceivedIsRequiredErrorMessage.isVisible(), is(true));
    }

    public void confirmClaimantsID() {
        selectConfirmTheClaimantsIDRadioButton();
        clickOn(confirmButton);
    }

    public void assertWhereShouldTheCaseProgressToErrorMessage() {
        assertThat(whereShouldTheCaseProgressToIsRequiredErrorMessage.isVisible(), is(true));
    }
}
