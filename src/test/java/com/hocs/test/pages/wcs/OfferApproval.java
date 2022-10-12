package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
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
        selectSpecificRadioButton("Offer approved");
        clickConfirmButton();
    }

    public void returnToCaseworkTeam() {
        selectSpecificRadioButton("Return to casework");
        clickConfirmButton();
    }

    public void returnToQa() {
        selectSpecificRadioButton("Return to QA for corrections");
        clickConfirmButton();
    }
}
