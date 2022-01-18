package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;

public class Draft extends BasePage {

    public void selectTheAction(String action) {
        selectSpecificRadioButtonFromGroupWithHeading(action, "Actions");
    }

    public void selectADifferentBusinessUnit() {
        String newBusinessUnit = selectDifferentOptionFromDropdownWithHeading("Business Unit");
        setSessionVariable("businessUnit").to(newBusinessUnit);
    }
}
