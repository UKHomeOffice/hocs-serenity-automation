package com.hocs.test.pages.draft;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DraftDecisionPage extends Page {

    @FindBy(css = "label[for='InitialDraftDecision-ACCEPT']")
    private WebElementFacade acceptInitialDraftDecisionRadioButton;

    @FindBy(css = "label[for='InitialDraftDecision-REJEPT']")
    private WebElementFacade rejectInitialDraftDecisionRadioButton;

}
