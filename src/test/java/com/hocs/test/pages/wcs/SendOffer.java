package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SendOffer extends BasePage {

    @FindBy(css = "label[for='SendOfferStatus-PNC-Fail']")
    public WebElementFacade pncFailedSendForReapprovalRadioButton;

    @FindBy(css = "label[for='SendOfferStatus-Offer']")
    public WebElementFacade offerSentToClaimantReapprovalRadioButton;

    @FindBy(css = "label[for='SendOfferStatus-Nil-Offer']")
    public WebElementFacade nilPaymentSentToClaimantRadioButton;

    @FindBy(xpath = "//a[@href='#SendOfferStatus-error']")
    public WebElementFacade paymentIsRequiredSendOfferErrorMessage;

    public void assertPaymentIsRequiredSendOfferErrorMessage() {
        paymentIsRequiredSendOfferErrorMessage.shouldContainText("Payment is required");
    }

    public void selectOfferSentToClaimant() {
        clickOn(offerSentToClaimantReapprovalRadioButton);
        clickOn(confirmButton);
    }

    public void selectNilOfferSentToClaimant() {
        clickOn(nilPaymentSentToClaimantRadioButton);
        clickOn(confirmButton);
    }

    public void returnToOfferApproval() {
        clickOn(pncFailedSendForReapprovalRadioButton);
        clickOn(confirmButton);
    }
}
