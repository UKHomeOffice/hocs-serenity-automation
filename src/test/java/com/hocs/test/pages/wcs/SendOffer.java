package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SendOffer extends BasePage {

    @FindBy(timeoutInSeconds = "10",  css = "label[for='SendOfferStatus-PNC-Fail']")
    public WebElementFacade pncFailedSendForReapprovalRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='SendOfferStatus-Offer']")
    public WebElementFacade offerSentToClaimantReapprovalRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='SendOfferStatus-Nil-Offer']")
    public WebElementFacade nilPaymentSentToClaimantRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[@href='#SendOfferStatus-error']")
    public WebElementFacade paymentIsRequiredSendOfferErrorMessage;

    public void assertPaymentIsRequiredSendOfferErrorMessage() {
        paymentIsRequiredSendOfferErrorMessage.shouldContainText("Payment is required");
    }

    public void selectOfferSentToClaimant() {
        selectSpecificRadioButton("Offer sent to claimant and offer documents uploaded");
        clickConfirmButton();
    }

    public void selectNilOfferSentToClaimant() {
        selectSpecificRadioButton("Nil payment sent to claimant and offer documents uploaded");
        clickConfirmButton();
    }

    public void returnToOfferApproval() {
        selectSpecificRadioButton("PNC failed, send for reapproval");
        clickConfirmButton();
    }
}
