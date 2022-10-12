package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.CCHReturns;
import io.cucumber.java.en.And;

public class CCHStepDefs extends BasePage {

    CCHReturns cchReturns;

    @And("I select to transfer the case to a new business area at CCH Returns")
    public void iSelectTheTransferToABusinessAreaRadioButtonAtCCH() {
        cchReturns.selectTransferToBusinessArea();
    }
    @And("I select {string} as the new business area of the case")
    public void iSelectToTransferTheCaseToANewBusinessArea(String businessArea) {
        cchReturns.selectNewBusinessArea(businessArea);
    }

    @And("I select to close the case at CCH Returns")
    public void iSelectToCloseTheCaseAtCCH() {
        cchReturns.selectCloseTheCase();
    }

    @And("I complete the required information on the Close Case screen at CCH Returns")
    public void iCompleteTheRequiredInformationOnTheCloseCaseScreenAtCCH() {
        cchReturns.selectWhyCaseShouldBeClosed();
        cchReturns.enterClosureDetails();
        clickConfirmButton();
    }
}