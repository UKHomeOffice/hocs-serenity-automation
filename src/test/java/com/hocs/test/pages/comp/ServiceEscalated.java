package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ServiceEscalated extends BasePage {

    @FindBy(xpath = "//label[text()='Return case to Triage']")
    public WebElementFacade returnCaseToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Case ready for drafting']")
    public WebElementFacade caseReadyForDraftingRadioButton;
}
