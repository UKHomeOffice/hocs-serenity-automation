package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.dispatch.Dispatch;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;

public class DispatchStepDefs {

    @Managed
    WebDriver driver;

    Page page;

    DataInput dataInput;

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
        dispatch.clickFinishButton();
    }

}
