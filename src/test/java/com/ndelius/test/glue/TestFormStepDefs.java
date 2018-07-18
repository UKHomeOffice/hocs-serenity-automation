package com.ndelius.test.glue;

import com.ndelius.test.pages.Page;
import com.ndelius.test.pages.forms.TestForm;
import cucumber.api.java.en.When;

public class TestFormStepDefs {

    Page page;
    TestForm testForm;

    @When("^I submit valid detail in all fields$")
    public void iSubmitValidDetailInAllFields() {
        testForm.enterName("Dom");
        testForm.enterSurname("Barnett");
        testForm.enterDate("04","03","88");
        testForm.selectCheckbox();
        testForm.enterText();
        testForm.selectRadioButton();
        testForm.selectTestDropdown(4);
        page.clickSubmitButton();

    }
}
