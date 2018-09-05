package com.hocs.test.glue;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.forms.TestForm;
import cucumber.api.java.en.When;

public class TestFormStepDefs {

    private Page page;

    private TestForm testForm;

    @When("^I submit valid detail in all fields$")
    public void iSubmitValidDetailInAllFields() {
        testForm.enterName("Dom");
        testForm.enterSurname("Barnett");
        testForm.enterCurrentDate();
        testForm.selectCheckbox();
        testForm.enterText();
        testForm.selectRadioButton();
        testForm.selectTestDropdown(4);
        page.clickSubmitButton();
    }
}
