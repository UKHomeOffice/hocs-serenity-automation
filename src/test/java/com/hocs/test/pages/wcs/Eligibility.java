package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;

public class Eligibility extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[@href='#EligibilityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantEligibilityErrorMessage;

    @FindBy(xpath = ".//a[contains(text(), 'Why is the claim not eligible')]")
    public WebElementFacade eligibilityRejectionReasonErrorMessage;

    @FindBy(id = "EligibilityRejReason")
    public WebElementFacade rejectionReasonDropdown;

    public void confirmEligibility() {
        selectSpecificRadioButton("Eligible, send to next team");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's eligibility", "Yes");
        clickOn(confirmButton);
    }

    public void cannotConfirmEligibility() {
        selectSpecificRadioButton("Not eligible");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's eligibility", "No");
        clickOn(confirmButton);
        waitFor(rejectionReasonDropdown);
    }

    public void putTheClaimOnHold() {
        selectSpecificRadioButton("On hold");
        clickOn(confirmButton);
    }

    public void takeTheClaimOffHold() {
        selectSpecificRadioButton("Off hold");
        clickOn(confirmButton);
    }

    public void noResponseFromClaimantCloseClaim() {
        selectSpecificRadioButton("No response from claimant - close claim");
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
        selectSpecificRadioButton("Yes - close the claim");
        try {
            clickOn(confirmButton);
        } catch (ElementShouldBeEnabledException e) {
            clickOn(continueButton);
        }
    }

    public void selectToNotCloseTheClaim() {
        selectSpecificRadioButtonFromGroupWithHeading("No", "No response from the claimant. Close the claim?");
        try {
            clickOn(confirmButton);
        } catch (ElementShouldBeEnabledException e) {
            clickOn(continueButton);
        }
    }
}
