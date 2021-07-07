package com.hocs.test.pages.dcu;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class NoResponseNeededConfirmation extends BasePage {

    @FindBy(xpath = "//div[@id='NoReplyNeededConfirmation-radios']//label[text()='Yes']")
    public WebElementFacade noResponseNeededYesRadioButton;

    @FindBy(xpath = "//div[@id='NoReplyNeededConfirmation-radios']//label[text()='No']")
    public WebElementFacade noResponseNeededNoRadioButton;
}
