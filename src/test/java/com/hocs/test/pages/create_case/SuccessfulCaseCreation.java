package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SuccessfulCaseCreation extends Page {

    @FindBy(className = "govuk-panel--confirmation")
    private WebElementFacade confirmationPanel;

    @FindBy(className = "govuk-panel__title")
    private WebElementFacade panelTitle;

    @FindBy(className = "govuk-panel__body")
    private WebElementFacade createdCaseMessage;

    @FindBy(className = "govuk-back-link")
    private WebElementFacade successBackButton;

    public void clickSuccessfulCaseBackButton() {
        successBackButton.click();
    }

    public void assertCaseCreatedSuccess() {
        assertThat(panelTitle.getText(), is("Success"));
    }

    public String getCaseReference() {
        String wholeString = createdCaseMessage.getText();
        String to_remove="Created a new case: ";

        String caseReference = wholeString.replace(to_remove, "");
        Serenity.setSessionVariable("caseReference").to(caseReference);

        return caseReference;
    }

}
