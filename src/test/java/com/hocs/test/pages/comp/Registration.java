package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Registration extends BasePage {

    @FindBy(css = "label[for='MarkupDecision-PR']")
    public WebElementFacade policyResponseRadioButton;

}
