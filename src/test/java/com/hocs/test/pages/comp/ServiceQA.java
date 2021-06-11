package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ServiceQA extends BasePage {

    @FindBy(xpath = "//label[text()='Accept - send response to complainant']")
    public WebElementFacade sendResponseToComplainantRadioButton;

    @FindBy(xpath = "//label[text()='Reject - return response to draft']")
    public WebElementFacade returnResponseToDraftRadioButton;

    public void moveCaseFromServiceQAToServiceSend() {
        safeClickOn(sendResponseToComplainantRadioButton);
        safeClickOn(continueButton);
    }

}
