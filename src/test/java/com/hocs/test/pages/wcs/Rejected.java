package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Rejected extends BasePage {

    @FindBy(css = "label[for*='Outcome-SendToReview']")
    public WebElementFacade sendToTier1ReviewRadioButton;

    @FindBy(css = "label[for*='Outcome-Archive']")
    public WebElementFacade archiveClaimRadioButton;

    @FindBy(xpath = "//a[@href='#IdentityRejectedOutcome-error']")
    public WebElementFacade reviewIdentityDecisionIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#EligibilityRejectedOutcome-error']")
    public WebElementFacade reviewEligibilityDecisionIsRequiredErrorMessage;

    public void selectToSendClaimToTier1Review() {
        selectSpecificRadioButton("Yes, send to Tier 1 review ");
        //Remove the space at the end when HOCS-5831 is done, this is a temp fix to avoid time spent investigating
        clickConfirmButton();
    }

    public void selectToArchiveClaim() {
        selectSpecificRadioButton("No, archive claim");
        clickConfirmButton();
    }

    public void assertReviewIdentityDecisionIsRequiredErrorMessage() {
        reviewIdentityDecisionIsRequiredErrorMessage
                .shouldContainText("Has the claimant requested a review of the identity rejection? is required");
    }

    public void assertReviewEligibilityDecisionIsRequiredErrorMessage() {
        reviewEligibilityDecisionIsRequiredErrorMessage
                .shouldContainText("Has the claimant requested a review of the eligibility rejection? is required");
    }
}
