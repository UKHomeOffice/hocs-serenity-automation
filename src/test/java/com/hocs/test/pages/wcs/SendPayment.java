package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SendPayment extends BasePage {

    @FindBy(css = "label[for='SendPaymentStatus-Interim']")
    public WebElementFacade paymentSentInterimRadioButton;

    @FindBy(css = "label[for='SendPaymentStatus-Final']")
    public WebElementFacade paymentSentFinalRadioButton;

    @FindBy(xpath = "//a[@href='#SendPaymentStatus-error']")
    public WebElementFacade paymentIsRequiredSendPaymentErrorMessage;

    public void assertPaymentIsRequiredSendPaymentErrorMessage() {
        paymentIsRequiredSendPaymentErrorMessage.shouldContainText("Payment is required");
    }

    public void selectFinalPaymentSent() {
        clickOn(paymentSentFinalRadioButton);
        clickOn(confirmButton);
    }

    public void selectInterimPaymentSent() {
        clickOn(paymentSentInterimRadioButton);
        clickOn(confirmButton);
    }
}
