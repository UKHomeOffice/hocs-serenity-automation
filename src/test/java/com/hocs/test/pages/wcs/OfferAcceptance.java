package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OfferAcceptance extends BasePage {

    @FindBy(timeoutInSeconds = "10",  css = "label[for='OfferReplyStatus-Offer']")
    public WebElementFacade offerAcceptedReadyForProcessingRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='OfferReplyStatus-Reject']")
    public WebElementFacade offerRejectRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[@href='#OfferReplyStatus-error']")
    public WebElementFacade paymentIsRequiredOfferAcceptanceErrorMessage;

    public void assertPaymentIsRequiredOfferAcceptanceErrorMessage() {
        paymentIsRequiredOfferAcceptanceErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentOfferAccepted() {
        selectSpecificRadioButton("Offer accepted, or preliminary payment");
        clickConfirmButton();
    }

    public void selectPaymentOfferRejected() {
        selectSpecificRadioButton("Offer rejected");
        clickConfirmButton();
    }
}
