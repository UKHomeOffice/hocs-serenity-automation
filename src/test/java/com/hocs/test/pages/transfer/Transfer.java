package com.hocs.test.pages.transfer;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Transfer {

    @FindBy(id = "")
    private WebElementFacade transferNameField;

    public void enterTransferName(String department) {
        transferNameField.clear();
        transferNameField.sendKeys(department);
    }

}
