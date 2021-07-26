package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NilOfferAcceptance extends BasePage {

    @FindBy(css = "label[for='OfferReplyStatus-Nil-Offer']")
    public WebElementFacade noChallengeRadioButton;

    @FindBy(css = "label[for='OfferReplyStatus-Reject']")
    public WebElementFacade nilPaymentRejectedRadioButton;

    @FindBy(xpath = "//a[@href='#OfferReplyStatus-error']")
    public WebElementFacade paymentIsRequiredNilOfferErrorMessage;

    public void assertPaymentIsRequiredNilOfferErrorMessage() {
        paymentIsRequiredNilOfferErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentNoChallenge() {
        selectSpecificRadioButton("No response after 2 months - close case");
        clickTheButton("Confirm");
    }

    public void selectPaymentNilOfferRejected() {
        selectSpecificRadioButton("nil payment rejected");
        clickTheButton("Confirm");
    }

}
