package com.hocs.test.pages.create_case;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SuccessfulCaseCreation {

    @FindBy(className = "govuk-panel--confirmation")
    private WebElementFacade confirmationPanel;

    @FindBy(css = "h1")
    private WebElementFacade panelTitle;

    @FindBy(className = "govuk-panel__body")
    private WebElementFacade createdCaseMessage;

    public String getCaseReference() {
        String wholeString = createdCaseMessage.getText();
        String to_remove="Created case ";

        String caseReference = wholeString.replace(to_remove, "");
        Serenity.setSessionVariable("caseReference").to(caseReference);

        return caseReference;
    }

}
