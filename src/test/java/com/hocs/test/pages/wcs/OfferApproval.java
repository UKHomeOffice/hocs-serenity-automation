package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OfferApproval extends BasePage {

    @FindBy(css = "label[for='OfferApprovalStatus-Offer']")
    public WebElementFacade offerApprovedRadioButton;

    @FindBy(css = "label[for='OfferApprovalStatus-QA")
    public WebElementFacade returnToQARadioButton;

    @FindBy(css = "label[for='OfferApprovalStatus-PNC-Fail")
    public WebElementFacade returnToCaseworkDueToPNCResultRadioButton;

    @FindBy(xpath = "//a[@href='#OfferApprovalStatus-error']")
    public WebElementFacade paymentIsRequiredOfferApprovalErrorMessage;

    public void assertPaymentIsRequiredOfferApprovalErrorMessage() {
        paymentIsRequiredOfferApprovalErrorMessage.shouldContainText("Payment is required");
    }

    public void selectPaymentOfferApproved(){
        clickOn(offerApprovedRadioButton);
        clickOn(confirmButton);
    }

    public void returnToCaseworkTeam() {
        clickOn(returnToCaseworkDueToPNCResultRadioButton);
        clickOn(confirmButton);
    }
    public void returnToQa() {
        clickOn(returnToQARadioButton);
        clickOn(confirmButton);
    }
}
