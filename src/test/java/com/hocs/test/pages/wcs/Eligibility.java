package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Eligibility extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10",  xpath = "//a[@href='#EligibilityConfirmed-error']")
    public WebElementFacade canYouConfirmClaimantEligibilityErrorMessage;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//a[contains(text(), 'Why is the claim not eligible')]")
    public WebElementFacade eligibilityRejectionReasonErrorMessage;

    @FindBy(timeoutInSeconds = "10",  id = "EligibilityRejReason")
    public WebElementFacade rejectionReasonDropdown;

    public void confirmEligibility() {
        selectSpecificRadioButton("Eligible, send to next team");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's eligibility", "Yes");
        clickConfirmButton();
    }

    public void cannotConfirmEligibility() {
        selectSpecificRadioButton("Not eligible");
        recordCaseData.addHeadingAndValueRecord("Can you confirm the claimant's eligibility", "No");
        clickConfirmButton();
        waitFor(rejectionReasonDropdown);
    }

    public void putTheClaimOnHold() {
        selectSpecificRadioButton("On hold");
        clickConfirmButton();
    }

    public void takeTheClaimOffHold() {
        selectSpecificRadioButton("Off hold");
        clickConfirmButton();
    }

    public void noResponseFromClaimantCloseClaim() {
        selectSpecificRadioButton("No response from claimant - close claim");
        clickConfirmButton();
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
        if (buttonIsCurrentlyVisible("Continue")) {
            clickContinueButton();
        } else {
            clickConfirmButton();
        }
    }

    public void selectToNotCloseTheClaim() {
        selectSpecificRadioButtonFromGroupWithHeading("No", "No response from the claimant. Close the claim?");
        if (buttonIsCurrentlyVisible("Continue")) {
            clickContinueButton();
        } else {
            clickConfirmButton();
        }
    }
}
