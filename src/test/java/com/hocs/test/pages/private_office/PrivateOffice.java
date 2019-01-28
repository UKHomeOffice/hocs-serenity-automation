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
    private WebElementFacade privateOfficeAcceptRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-REJECT']")
    private WebElementFacade privateOfficeRejectRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-CHANGE']")
    private WebElementFacade privateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    private WebElementFacade privateOfficeRejectNoteField;

    public void clickPrivateOfficeAcceptRadioButton(){
        privateOfficeAcceptRadioButton.click();
    }

    public void clickPrivateOfficeRejectRadioButton(){
        privateOfficeRejectRadioButton.click();
    }

    public void clickPrivateOfficeChangeMinisterRadioButton(){
        privateOfficeChangeMinisterRadioButton.click();
    }

    public void enterPORejectNotes() {
        waitFor(privateOfficeRejectNoteField);

        String poRejectNote = "Rejection Reason: " + generateRandomString();
        privateOfficeRejectNoteField.clear();
        privateOfficeRejectNoteField.sendKeys(poRejectNote);
        setSessionVariable("PORejectNote").to(poRejectNote);
    }
}
