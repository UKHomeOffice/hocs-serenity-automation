package com.hocs.test.pages.draft;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DraftingTeamDecision extends Page {

    @FindBy(css = "label[for='InitialDraftDecision-ACCEPT']")
    private WebElementFacade initialDraftingDecisionAccept;

    @FindBy(css = "label[for='InitialDraftDecision-REJECT']")
    private WebElementFacade initialDraftingDecisionReject;

    public void clickAcceptInitialDraftDecision() {
        initialDraftingDecisionAccept.click();
    }

    public void clickRejectInitialDraftDecision() {
        initialDraftingDecisionReject.click();
    }

}
