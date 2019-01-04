package com.hocs.test.pages.private_office;

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

    public void clickPrivateOfficeAcceptRadioButton(){
        PrivateOfficeAcceptRadioButton.click();
    }

    public void clickPrivateOfficeRejectRadioButton(){
        PrivateOfficeRejectRadioButton.click();
    }

    public void clickPrivateOfficeChangeMinisterRadioButton(){
        PrivateOfficeChangeMinisterRadioButton.click();
    }


}
