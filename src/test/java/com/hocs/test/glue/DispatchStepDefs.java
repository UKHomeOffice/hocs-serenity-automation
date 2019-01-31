package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.dispatch.Dispatch;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs {

    @Managed

    Page page;

    Dispatch dispatch;

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    @When("^I complete the dispatch stage$")
    public void completeTheDispatchStage() {
        homepage.selectPerformanceProcessTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
        homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaLinkTextAndStoreResultingElement();
        dispatch.clickDispatchAcceptRadioButton();
        dispatch.clickContinueButton();
    }

}
