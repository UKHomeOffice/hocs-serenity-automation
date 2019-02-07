package com.hocs.test.glue;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import junit.framework.TestCase;
import net.thucydides.core.annotations.Managed;

import org.jruby.RubyProcess.Sys;
import org.openqa.selenium.WebDriver;


public class PrivateOfficeSignOffStepDefs {

    @Managed
    WebDriver driver;

    Homepage homepage;

    DataInput dataInput;

    PrivateOffice privateOffice;

    SuccessfulCaseCreation successfulCaseCreation;

    Workstacks workstacks;

    Page page;

    @When("^I complete the Private Office stage$")
    public void completePrivateOfficeStage() {
        homepage.selectMinisterForLordsTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpathDoubleClick();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        privateOffice.clickPrivateOfficeAcceptRadioButton();
        privateOffice.clickContinueButton();
    }

    @When("^the case is rejected at the Private Office stage$")
    public void rejectAtPrivateOffice() {
        homepage.selectMinisterForLordsTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpathDoubleClick();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
        homepage.selectMyCases();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        privateOffice.clickPrivateOfficeRejectRadioButton();
        privateOffice.clickContinueButton();
        privateOffice.enterPORejectNotes();
        privateOffice.clickFinishButton();
    }

}
