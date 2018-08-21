package com.hocs.test.glue;

import com.hocs.test.pages.homepage.Homepage;
import cucumber.api.java.en.Then;

public class HomepageStepDefs {

    Homepage homepage;

    @Then("^Case Reference is displayed on the homepage$")
    public void caseReferenceIsDisplayedOnTheHomepage() {
        homepage.assertWorkstackTableContainsCaseReference();
    }

}
