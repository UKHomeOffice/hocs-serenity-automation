package com.hocs.test.pages.transfer;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

class Transfer {

    @FindBy(id = "")
    private WebElementFacade transferNameField;

    public void enterTransferName(String department) {
        transferNameField.clear();
        transferNameField.sendKeys(department);
    }

}
