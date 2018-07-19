package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.forms.TestForm;
import cucumber.api.java.en.When;

public class TestFormStepDefs {

    Page page;
    TestForm testForm;

    @When("^I submit valid detail in all fields$")
    public void iSubmitValidDetailInAllFields() throws InterruptedException {
        testForm.enterName("Dom");
        testForm.enterSurname("Barnett");
        testForm.enterDate("04","03","1988");
       // testForm.selectCheckbox();
        testForm.enterText();
//        testForm.selectRadioButton();
        testForm.selectTestDropdown(4);
        page.clickSubmitButton();
    }
}
