package com.hocs.test.pages.dispatch;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;

public class Dispatch extends Page {

    @FindBy(css = "label[for='DispatchDecision-ACCEPT']")
    private WebElementFacade dispatchAcceptRadioButton;

    @FindBy(css = "label[for='DispatchDecision-REJECT']")
    private WebElementFacade dispatchRejectRadioButton;

    public void clickDispatchAcceptRadioButton() {
        dispatchAcceptRadioButton.click();
    }

}
