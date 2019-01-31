package com.hocs.test.pages.minister;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.pages.WebElementFacade;

public class MinisterSignOff extends Page {

    @Managed
    WebDriver driver;

    @FindBy(css = "label[for='MinisterSignOffDecision-ACCEPT']")
    private WebElementFacade minsterSignOffAcceptRadioButton;

    @FindBy(css = "label[for='MinisterSignOffDecision-REJECT']")
    private WebElementFacade ministerSignOffRejectRadioButton;

    @FindBy(id = "CaseNote_MinisterReject")
    public WebElementFacade ministerRejectionNote;

    // Basic Methods

    public void clickMinisterSignOffAcceptRadioButton(){
        minsterSignOffAcceptRadioButton.click();
    }

    public void clickMinisterSignOffRejectRadioButton(){
        ministerSignOffRejectRadioButton.click();
    }

    public void clearRejectionNoteField() {
        ministerRejectionNote.clear();
    }

    public void enterMinisterRejectionNote() {
        clearRejectionNoteField();
        ministerRejectionNote.sendKeys(generateRandomString());
    }

}
