package com.hocs.test.glue.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.CCH;
import io.cucumber.java.en.And;

public class CCHStepDefs extends BasePage {

    CCH cch;

    @And("I select the {string} action at CCH")
    public void iSelectActionAtCCH(String action) {
        cch.selectActionAtCCH(action);
    }

    @And("I enter a completion note at CCH")
    public void iEnterACompletionNoteAtCCH() {
        cch.submitReasonForCaseCompletion();
    }

    @And("I confirm I want to close the case at CCH")
    public void iConfirmIWantToCloseTheCaseAtCCH() {
        cch.selectActionAtCompleteConfirmation("Yes");
    }
}