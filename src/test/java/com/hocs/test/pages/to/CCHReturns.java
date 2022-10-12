package com.hocs.test.pages.to;

import com.hocs.test.pages.decs.BasePage;

import static net.serenitybdd.core.Serenity.setSessionVariable;

public class CCHReturns extends BasePage {

    public void selectTransferToBusinessArea() {
        selectSpecificRadioButton("Transfer to a Business Unit");
        //Radio button text to be updated once HOCS-4720 is resolved
        clickContinueButton();
    }

    public void selectNewBusinessArea(String businessArea) {
        selectSpecificRadioButtonFromGroupWithHeading(businessArea, "Business Area");
        setSessionVariable("businessArea").to(businessArea);
        clickContinueButton();
    }

    public void selectCloseTheCase() {
        selectSpecificRadioButton("Close this Case");
        clickContinueButton();
    }

    public void selectWhyCaseShouldBeClosed() {
        selectRandomRadioButtonFromGroupWithHeading("Why should this case be closed?");
    }

    public void enterClosureDetails() {
        String closureDetails = enterTextIntoTextAreaWithHeading("Please enter details of why the case is being closed");
        setSessionVariable("closureReason").to(closureDetails);
    }
}