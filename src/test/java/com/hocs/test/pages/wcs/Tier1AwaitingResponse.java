package com.hocs.test.pages.wcs;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Tier1AwaitingResponse extends BasePage {

    @FindBy(css = "label[for='Tier1Await-Accept']")
    public WebElementFacade claimantAcceptsOfferRadioButton;

    @FindBy(css = "label[for='Tier1Await-Reject-Interim']")
    public WebElementFacade rejectedInterimOfferRadioButton;

    @FindBy(css = "label[for='Tier1Await-Reject-Final']")
    public WebElementFacade rejectedFinalOfferRadioButton;

    @FindBy(xpath = "//a[@href='#Tier1Await-error']")
    public WebElementFacade responseFromClaimantIsRequiredErrorMessage;

    public void assertResponseFromClaimantIsRequiredErrorMessage() {
        responseFromClaimantIsRequiredErrorMessage.shouldContainText("Response from claimant is required");
    }

    public void selectClaimantAcceptsOffer() {
        clickOn(claimantAcceptsOfferRadioButton);
        clickOn(confirmButton);
    }

    public void selectClaimantRejectsNonFinalOffer() {
        clickOn(rejectedInterimOfferRadioButton);
        clickOn(confirmButton);
    }

    public void selectClaimantRejectsFinalOffer() {
        clickOn(rejectedFinalOfferRadioButton);
        clickOn(confirmButton);
    }
}
