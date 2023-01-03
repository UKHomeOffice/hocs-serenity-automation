package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SendPayment extends BasePage {

    @FindBy(timeoutInSeconds = "10",  css = "label[for='SendPaymentStatus-Interim']")
    public WebElementFacade paymentSentInterimRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='SendPaymentStatus-Final']")
    public WebElementFacade paymentSentFinalRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[@href='#SendPaymentStatus-error']")
    public WebElementFacade paymentIsRequiredSendPaymentErrorMessage;

    public void assertPaymentIsRequiredSendPaymentErrorMessage() {
        paymentIsRequiredSendPaymentErrorMessage.shouldContainText("Payment is required");
    }

    public void selectFinalPaymentSent() {
        selectSpecificRadioButton("Payment sent (full & final)");
        clickConfirmButton();
    }

    public void selectInterimPaymentSent() {
        selectSpecificRadioButton("Payment sent (preliminary, or interim)");
        clickConfirmButton();
    }
}
