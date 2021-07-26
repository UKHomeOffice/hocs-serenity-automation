package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
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
        selectSpecificRadioButton("Offer sent");
        clickTheButton("Confirm");
    }

    public void selectNilOfferSentToClaimant() {
        selectSpecificRadioButton("Nil payment sent");
        clickTheButton("Confirm");
    }

    public void returnToOfferApproval() {
        selectSpecificRadioButton("PNC failed");
        clickTheButton("Confirm");
    }
}
