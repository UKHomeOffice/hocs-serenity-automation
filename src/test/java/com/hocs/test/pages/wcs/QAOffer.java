package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class QAOffer extends BasePage {

    @FindBy(css = "label[for='QaStatus-Return']")
    public WebElementFacade returnToCaseworkerRadioButton;

    @FindBy(css = "label[for='QaStatus-Approve']")
    public WebElementFacade approveOfferRadioButton;

    @FindBy(xpath = "//a[@href='#QaStatus-error']")
    public WebElementFacade qAOfferClaimStatusIsRequiredErrorMessage;

    public void assertQAOfferClaimStatusErrorMessage() {
        qAOfferClaimStatusIsRequiredErrorMessage.shouldContainText("Claim status is required");
    }

    public void selectClaimStatusApproveOffer() {
        selectSpecificRadioButton("QA complete, send to payments");
        clickTheButton("Confirm");
    }

    public void returnClaimToCaseworkStage() {
        selectSpecificRadioButton("Return to caseworker");
        clickTheButton("Confirm");
    }
}
