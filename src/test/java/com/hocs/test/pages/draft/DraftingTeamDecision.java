package com.hocs.test.pages.draft;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.create_case.AddDocuments;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class DraftingTeamDecision extends Page {

    Draft draft;

    AddDocuments addDocuments;

    Homepage homepage;

    Workstacks workstacks;

    Qa qa;

    SuccessfulCaseCreation successfulCaseCreation;

    @FindBy(css = "label[for='InitialDraftDecision-ACCEPT']")
    public WebElementFacade initialDraftingDecisionAccept;

    @FindBy(css = "label[for='InitialDraftDecision-REJECT']")
    public WebElementFacade initialDraftingDecisionReject;

    @FindBy(css = "label[for='ResponseChannel-LETTER']")
    private WebElementFacade draftingResponseLetter;

    @FindBy(css = "label[for='ResponseChannel-PHONE']")
    private WebElementFacade draftingResponsePost;

    @FindBy(id = "CaseNote_PhonecallNote")
    private WebElementFacade phoneCallSummaryNote;

    //Basic Methods

    public void clickAcceptInitialDraftDecision() {
        initialDraftingDecisionAccept.click();
    }

    public void clickDraftingResponseLetter() {
        draftingResponseLetter.click();
    }

    public void enterPhoneCallSummaryNote() {
        phoneCallSummaryNote.sendKeys(generateRandomString());
    }

    // Multi Step Methods

    public void acceptAndDraftALetter() {
        clickOn(initialDraftingDecisionAccept);
        clickOn(draft.continueButton);
        clickOn(draftingResponseLetter);
        clickOn(draft.continueButton);
    }

    public void dtenAcceptAndDraftALetter() {
        clickOn(initialDraftingDecisionAccept);
        clickOn(continueButton);
    }


    public void uploadDraftResponse() {
        clickOn(draft.draftStageAddDocumentsButton);
        draft.selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        clickOn(draft.addButton);
    }

    public void initialDraftFullFlow() {
        sleep(3500);
        WebElementFacade thisDraftTeam = findAll("//span[text()='" + sessionVariableCalled("draftTeam")
                + "']").get(0);
        thisDraftTeam.click();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        clickOn(workstacks.allocateToMeButton);
        clickOn(homepage.home);
        clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        acceptAndDraftALetter();
        uploadDraftResponse();
        qa.dontQAOffline();
    }

    public void moveCaseFromInitialDraftToQaResponse() {
        acceptAndDraftALetter();
        uploadDraftResponse();
        qa.sleep(500);
        qa.dontQAOffline();
    }

    public void moveTROCaseFromInitialDraftToQaResponse() {
        acceptAndDraftALetter();
        uploadDraftResponse();
        qa.sleep(500);
        clickOn(continueButton);
    }

    public void moveDTENCaseFromInitialDraftToQaResponse() {
        dtenAcceptAndDraftALetter();
        uploadDraftResponse();
        qa.sleep(500);
        qa.dontQAOffline();
    }
}
