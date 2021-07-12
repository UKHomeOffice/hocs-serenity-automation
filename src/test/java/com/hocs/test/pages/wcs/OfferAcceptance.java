package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OfferAcceptance extends BasePage {

    @FindBy(css = "label[for='OfferReplyStatus-Offer']")
    public WebElementFacade offerAcceptedReadyForProcessingRadioButton;

    @FindBy(css = "label[for='OfferReplyStatus-Reject']")
    public WebElementFacade offerRejectRadioButton;

    @FindBy(xpath = "//a[@href='#OfferReplyStatus-error']")
    public WebElementFacade paymentIsRequiredOfferAcceptanceErrorMessage;

    public void assertPaymentIsRequiredOfferAcceptanceErrorMessage() {
        paymentIsRequiredOfferAcceptanceErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentOfferAccepted() {
        clickOn(offerAcceptedReadyForProcessingRadioButton);
        clickOn(confirmButton);
    }

    public void selectPaymentOfferRejected() {
        clickOn(offerRejectRadioButton);
        clickOn(confirmButton);
    }
}
