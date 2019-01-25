package com.hocs.test.pages.private_office;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.Page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;

public class PrivateOffice extends Page {

    @Managed
    WebDriver driver;

    @FindBy(css = "label[for='PrivateOfficeDecision-ACCEPT']")
    private WebElementFacade PrivateOfficeAcceptRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-REJECT']")
    private WebElementFacade PrivateOfficeRejectRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-CHANGE']")
    private WebElementFacade PrivateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    private WebElementFacade privaetOfficeRejectNoteField;

    public void clickPrivateOfficeAcceptRadioButton(){
        PrivateOfficeAcceptRadioButton.click();
    }

    public void clickPrivateOfficeRejectRadioButton(){
        PrivateOfficeRejectRadioButton.click();
    }

    public void clickPrivateOfficeChangeMinisterRadioButton(){
        PrivateOfficeChangeMinisterRadioButton.click();
    }

    public void enterPORejectNotes() {
        waitFor(privaetOfficeRejectNoteField);

        String poRejectNote = "Rejection Reason: " + generateRandomString();
        privaetOfficeRejectNoteField.clear();
        privaetOfficeRejectNoteField.sendKeys(poRejectNote);
        setSessionVariable("PORejectNote").to(poRejectNote);
    }
}
