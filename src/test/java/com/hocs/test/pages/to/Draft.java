package com.hocs.test.pages.to;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;

public class Draft extends BasePage {

    public void selectTheAction(String action) {
        selectSpecificRadioButtonFromGroupWithHeading(action, "Actions");
    }

    public void selectADifferentBusinessUnitType() {
        String newBusinessUnitType = selectDifferentOptionFromDropdownWithHeading("Business Unit Type");
        setSessionVariable("businessUnitType").to(newBusinessUnitType);
    }
}
