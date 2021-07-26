package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
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
        selectSpecificRadioButton("Claimant accepts offer");
        clickTheButton("Confirm");
    }

    public void selectClaimantRejectsNonFinalOffer() {
        selectSpecificRadioButton("Rejected interim offer");
        clickTheButton("Confirm");
    }

    public void selectClaimantRejectsFinalOffer() {
        selectSpecificRadioButton("Rejected final offer");
        clickTheButton("Confirm");
    }
}
