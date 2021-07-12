package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;

public class Eligibility extends BasePage {

    @FindBy(css = "label[for='EligibilityConfirmed-OnHold']")
    public WebElementFacade canYouConfirmTheClaimantsEligibilityOnHoldRadioButton;

    @FindBy(css = "label[for='EligibilityConfirmed-Yes']")
    public WebElementFacade canYouConfirmTheClaimantsEligibilityYesRadioButton;

    @FindBy(css = "label[for='EligibilityConfirmed-No']")
    public WebElementFacade canYouConfirmTheClaimantsEligibilityNoRadioButton;

    @FindBy(css = "label[for='EligibilityConfirmed-Pending']")
    public WebElementFacade canYouConfirmTheClaimantsEligibilityOffHoldRadioButton;

    @FindBy(css = "label[for='EligibilityConfirmed-No-Response']")
    public WebElementFacade canYouConfirmTheClaimantsEligibilityNoResponseRadioButton;

    @FindBy(xpath = "//a[@href='#EligibilityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantEligibilityErrorMessage;

    @FindBy(xpath = ".//a[contains(text(), 'Why is the claim not eligible')]")
    public WebElementFacade eligibilityRejectionReasonErrorMessage;

    @FindBy(id = "EligibilityRejReason")
    public WebElementFacade rejectionReasonDropdown;

    @FindBy(css = "label[for*='Confirm-Yes']")
    public WebElementFacade closeClaimYesRadioButton;

    @FindBy(css = "label[for*='Confirm-No']")
    public WebElementFacade closeClaimNoRadioButton;

    public void confirmEligibility() {
        clickOn(canYouConfirmTheClaimantsEligibilityYesRadioButton);
        clickOn(confirmButton);
    }

    public void cannotConfirmEligibility() {
        clickOn(canYouConfirmTheClaimantsEligibilityNoRadioButton);
        clickOn(confirmButton);
    }

    public void putTheClaimOnHold() {
        clickOn(canYouConfirmTheClaimantsEligibilityOnHoldRadioButton);
        clickOn(confirmButton);
    }

    public void takeTheClaimOffHold() {
        clickOn(canYouConfirmTheClaimantsEligibilityOffHoldRadioButton);
        clickOn(confirmButton);
    }

    public void noResponseFromClaimantCloseClaim() {
        clickOn(canYouConfirmTheClaimantsEligibilityNoResponseRadioButton);
        clickOn(confirmButton);
    }

    public void assertCanYouConfirmClaimantEligibilityErrorMessage() {
        assertThat(canYouConfirmClaimantEligibilityErrorMessage.isVisible(), is(true));
    }

    public void assertEligibilityRejectionReasonErrorMessage() {
        assertThat(eligibilityRejectionReasonErrorMessage.isVisible(), is(true));
    }

    public void selectRejectionReasonByIndex(int index) {
        rejectionReasonDropdown.selectByIndex(index);
    }

    public void confirmClaimShouldBeClosed() {
        clickOn(closeClaimYesRadioButton);
        try {
            clickOn(confirmButton);
        } catch (ElementShouldBeEnabledException e) {
            clickOn(continueButton);
        }
    }

    public void selectToNotCloseTheClaim() {
        clickOn(closeClaimNoRadioButton);
        try {
            clickOn(confirmButton);
        } catch (ElementShouldBeEnabledException e) {
            clickOn(continueButton);
        }
    }
}
