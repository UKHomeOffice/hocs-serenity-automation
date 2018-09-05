package com.hocs.test.pages.dispatch;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

class Dispatch {

    @FindBy(id = "")
    private WebElementFacade dispatchOffline;

    public void clickDispatchOffline() {
        dispatchOffline.click();
    }

}
