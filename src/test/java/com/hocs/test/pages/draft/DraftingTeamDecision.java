package com.hocs.test.pages.draft;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DraftingTeamDecision extends Page {

    @FindBy(css = "label[for='InitialDraftDecision-ACCEPT']")
    private WebElementFacade initialDraftingDecisionAccept;

    @FindBy(css = "label[for='InitialDraftDecision-REJECT']")
    private WebElementFacade initialDraftingDecisionReject;

    @FindBy(css = "label[for='ResponseChannel-LETTER']")
    private WebElementFacade draftingResponseLetter;

    @FindBy(css = "label[for='ResponseChannel-PHONE']")
    private WebElementFacade draftingResponsePost;

    @FindBy(id = "PhonecallNote")
    private WebElementFacade phoneCallSummaryNote;

    public void clickAcceptInitialDraftDecision() {
        initialDraftingDecisionAccept.click();
    }

    public void clickRejectInitialDraftDecision() {
        initialDraftingDecisionReject.click();
    }

    public void clickDraftingResponseLetter() {
        draftingResponseLetter.click();
    }

    public void clickDraftingResponsePhone() { draftingResponsePost.click(); }

    public void enterPhoneCallSummaryNote() {
        generateRandomString();
    }

}
