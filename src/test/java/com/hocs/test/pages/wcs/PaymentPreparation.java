package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PaymentPreparation extends BasePage {

    @FindBy(css = "label[for='PaymentPrepStatus-Offer']")
    public WebElementFacade sendToOfferApprovalRadioButton;

    @FindBy(xpath = "//a[@href='#PaymentPrepStatus-error']")
    public WebElementFacade qAOfferClaimStatusIsRequiredErrorMessage;

    public void assertPaymentPrepStatusErrorMessage() {
        qAOfferClaimStatusIsRequiredErrorMessage.shouldContainText("Payment is required");
    }

    public void selectToSendClaimToOfferApproval() {
        selectSpecificRadioButton("Send for payment approval");
        clickConfirmButton();
    }

}
