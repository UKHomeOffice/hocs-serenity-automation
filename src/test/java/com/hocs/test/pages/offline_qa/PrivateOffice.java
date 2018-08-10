package com.hocs.test.pages.offline_qa;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOffice {

    @FindBy(id = "")
    private WebElementFacade acceptMarkUpMinisterButton;

    @FindBy(id = "")
    private WebElementFacade rejectMarkUpMinisterButton;

    @FindBy(id = "")
    private WebElementFacade rejectMarkUpNote;

    public void clickAcceptMarkUpMinisterButton() {
        acceptMarkUpMinisterButton.click();
    }

    public void clickRejecttMarkUpMinisterButton() {
        rejectMarkUpMinisterButton.click();
    }

    public void clickRejectMarkUpNote() {
        rejectMarkUpNote.click();
    }

}
