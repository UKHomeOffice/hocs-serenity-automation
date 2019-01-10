package com.hocs.test.glue;

import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.java.en.Then;

public class HomepageStepDefs {

    private Homepage homepage;

    @Then("^I am returned to my home screen$")
    public void returnedToHomeScreen() {
        homepage.assertElementIsDisplayed(homepage.createSingleCase);
    }

    @Then("^Case Reference is displayed on the homepage$")
    public void caseReferenceIsDisplayedOnTheHomepage() {
        homepage.assertWorkstackTableContainsCaseReference();
    }

}
