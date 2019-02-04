package com.hocs.test.pages.draft;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DraftingTeamDecision extends Page {

    Draft draft;

    AddDocuments addDocuments;

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


    //Basic Methods

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
        phoneCallSummaryNote.sendKeys(generateRandomString());
    }

    // Multi Step Methods

    public void acceptAndDraftALetter(){
        clickAcceptInitialDraftDecision();
        draft.clickContinueButton();
        clickDraftingResponseLetter();
        draft.clickContinueButton();
    }

    public void uploadDraftResponse(){
        draft.clickAddDocumentsButton();
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        draft.clickAddButton();
    }

}
