package com.hocs.test.glue.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.CCH;
import io.cucumber.java.en.And;

public class CCHStepDefs extends BasePage {

    CCH cch;

    @And("I select the {string} action at CCHReturns")
    public void iSelectActionAtCCH(String action) {
        cch.selectActionAtCCH(action);
    }

    @And("I enter a completion note at CCHReturns")
    public void iEnterACompletionNoteAtCCH() {
        cch.submitReasonForCaseCompletion();
    }

    @And("I confirm I want to close the case at CCHReturns")
    public void iConfirmIWantToCloseTheCaseAtCCH() {
        cch.selectActionAtCompleteConfirmation("Yes");
    }
}