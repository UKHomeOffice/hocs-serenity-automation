package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Documents;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ServiceDraft extends BasePage {

    Documents documents;

    @FindBy(xpath = "//a[contains(text(),'Add a')]")
    public WebElementFacade addADocumentHypertext;

    @FindBy(xpath = "//label[text()='Response is ready to send']")
    public WebElementFacade responseIsReadyToSendRadioButton;

    @FindBy(xpath = "//label[text()='Send case to QA']")
    public WebElementFacade sendCaseToQARadioButton;

    @FindBy(xpath = "//label[text()='Escalate case to WFM']")
    public WebElementFacade escalateCaseToWFMRadioButton;

    public void moveCaseFromServiceDraftToServiceQA() {
        safeClickOn(addADocumentHypertext);
        documents.documentTypeDropDown.selectByVisibleText("DRAFT");
        documents.uploadDocumentOfType("docx");
        safeClickOn(addButton);
        safeClickOn(sendCaseToQARadioButton);
        safeClickOn(continueButton);
    }
}
