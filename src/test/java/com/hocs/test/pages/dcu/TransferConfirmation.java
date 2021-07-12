package com.hocs.test.pages.dcu;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TransferConfirmation extends BasePage {

    @FindBy(xpath = "//div[@id='TransferConfirmation-radios']//label[text()='Yes']")
    public WebElementFacade transferCaseYesRadioButton;

    @FindBy(xpath = "//div[@id='TransferConfirmation-radios']//label[text()='No']")
    public WebElementFacade transferCaseNoRadioButton;
}
