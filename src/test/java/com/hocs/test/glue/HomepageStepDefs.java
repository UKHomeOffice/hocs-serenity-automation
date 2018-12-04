package com.hocs.test.glue;

import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class HomepageStepDefs {

    private Homepage homepage;


    @And("^I am returned to my home screen$")
    public void returnedToHomeScreen() {
        homepage.assertCreateSingleCaseIsDisplayed();
    }


    @Then("^Case Reference is displayed on the homepage$")
    public void caseReferenceIsDisplayedOnTheHomepage() {
        homepage.assertWorkstackTableContainsCaseReference();
    }

}
