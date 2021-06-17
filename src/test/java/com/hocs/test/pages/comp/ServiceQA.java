package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ServiceQA extends BasePage {

    @FindBy(xpath = "//label[text()='Accept - send response to complainant']")
    public WebElementFacade sendResponseToComplainantRadioButton;

    @FindBy(xpath = "//label[text()='Reject - return response to draft']")
    public WebElementFacade returnResponseToDraftRadioButton;

    @FindBy(id = "CaseNote_QaReject")
    public WebElementFacade rejectionReasonTextField;

    public void moveCaseFromServiceQAToServiceSend() {
        safeClickOn(sendResponseToComplainantRadioButton);
        safeClickOn(continueButton);
    }

    public void moveCaseFromServiceQAToServiceDraft() {
        safeClickOn(returnResponseToDraftRadioButton);
        safeClickOn(continueButton);
        rejectionReasonTextField.sendKeys("Test Rejection Reason");
        setSessionVariable("rejectionReason").to("Test Rejection Reason");
        clickTheButton("Reject");
    }
}