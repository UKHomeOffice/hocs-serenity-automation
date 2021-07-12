package com.hocs.test.glue.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.wcs.Casework;
import com.hocs.test.pages.wcs.ClaimSchema;
import io.cucumber.java.en.And;

public class BroughtForwardDateStepDefs {

    ClaimSchema claimSchema;

    Casework casework;


    @And("I fill in the case info including the Brought Forward Date: {string}")
    public void iFillInTheCaseInfoIncludingTheBroughtForwardDate(String broughtForwardDate) {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.enterBroughtForwardDate(broughtForwardDate);
    }

    @And("I look at the case info to see whether the brought forward date displays: {string}")
    public void iLookAtTheCaseInfoToWhetherTheBroughtForwardDateDisplays(String broughtForwardDate) {
        String actualBroughtForwardDay = claimSchema.broughtForwardDateDayTextbox.getValue();
        String actualBroughtForwardMonth = claimSchema.broughtForwardDateMonthTextbox.getValue();
        String actualBroughtForwardYear = claimSchema.broughtForwardDateYearTextbox.getValue();
        String actualBroughtForwardDate = actualBroughtForwardDay + "/" + actualBroughtForwardMonth + "/" + actualBroughtForwardYear;
        assertThat(broughtForwardDate.equals(actualBroughtForwardDate), is(true));
    }

    @And("I check that the read-only case details accordion displays {string} as the brought forward date")
    public void readOnlyCaseDetailsBroughtForwardDate(String BFDate) {
        String actualBFDay = claimSchema.broughtForwardDateDayTextbox.getValue();
        String actualBFMonth = claimSchema.broughtForwardDateMonthTextbox.getValue();
        String actualBFYear = claimSchema.broughtForwardDateYearTextbox.getValue();
        String actualBFDate = actualBFDay + "/" + actualBFMonth + "/" + actualBFYear;
        assertThat(BFDate.equals(actualBFDate), is(true));
    }

    @And("I send the offer to QA")
    public void iSendTheOfferToQA() {
        casework.selectClaimStatusReadyToQA();
    }
}
