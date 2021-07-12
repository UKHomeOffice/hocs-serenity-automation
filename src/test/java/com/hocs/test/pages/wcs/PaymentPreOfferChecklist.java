package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PaymentPreOfferChecklist extends BasePage {

    @FindBy(css = "label[for='PaymentChecklistStatus-Offer")
    public WebElementFacade sendForOfferApprovalRadioButton;

    @FindBy(css = "label[for='PaymentChecklistStatus-QA']")
    public WebElementFacade returnToQAForCorrectionsRadioButton;

    @FindBy(xpath = "//a[@href='#PaymentChecklistStatus-error']")
    public WebElementFacade paymentIsRequiredPreOfferErrorMessage;

    public void assertPaymentIsRequiredPreOfferErrorMessage(){
        paymentIsRequiredPreOfferErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentSendForApproval() {
        clickOn(sendForOfferApprovalRadioButton);
        clickOn(confirmButton);
    }

    public void returnToQAForCorrections() {
        clickOn(returnToQAForCorrectionsRadioButton);
        clickOn(confirmButton);
    }
}
