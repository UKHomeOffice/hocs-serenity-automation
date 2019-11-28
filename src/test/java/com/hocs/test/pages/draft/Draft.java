package com.hocs.test.pages.draft;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.workstacks.Workstacks;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends Page {

    AddDocuments addDocuments;

    Homepage homepage;

    Workstacks workstacks;

    SuccessfulCaseCreation successfulCaseCreation;

    Qa qa;

    @FindBy(css = "label[for=InitialDraftDecision-REJECT]")
    public WebElementFacade answeredByMyTeamNoRadioButton;

    @FindBy(css = "label[for=InitialDraftDecision-ACCEPT")
    public WebElementFacade answeredByMyTeamYesRadioButton;

    @FindBy(css = "label[for=ReturnToResponseChannelDecision-REJECT]")
    public WebElementFacade chooseAnotherResponseTypeNoButton;

    @FindBy(css = "label[for=ReturnToResponseChannelDecision-ACCEPT")
    public WebElementFacade chooseAnotherResponseTypeYesButton;

    @FindBy(xpath = "//a[text()='Can this correspondence be answered by your team? is required']")
    public WebElementFacade correspondenceAnsweredErrorMessage;

    @FindBy(xpath = "//a[text()='Why should this not be answered by your team? is required']")
    public WebElementFacade shouldBeAnsweredErrorMessage;

    @FindBy(xpath = "//a[text()='How do you intend to respond? is required']")
    public WebElementFacade howDoYouIntendToRespondErrorMessage;

    @FindBy(xpath = "//a[text()='Please summarise your call. is required']")
    public WebElementFacade pleaseSummariseYourCallIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary draft document? is required']")
    public WebElementFacade whichIsThePrimaryDraftDocumentErrorMessage;

    @FindBy(xpath = "//a[text()='Do you want to QA this offline? is required']")
    public WebElementFacade doYouWantToQAThisOfflineErrorMessage;

    @FindBy(xpath = "//a[text()='Who has done the Offline QA for this case? is required']")
    public WebElementFacade whoHadDoneTheOfflineQAErrorMessage;

    @FindBy(id = "")
    public WebElementFacade draftingDeadline;

    @FindBy(css = "label[for=ResponseChannel-EMAIL]")
    public WebElementFacade emailReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-LETTER]")
    public WebElementFacade letterReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-PHONE]")
    public WebElementFacade phoneReplyRadioButton;

    @FindBy(xpath = "//caption[contains(text(), 'Available Template')]/parent::table/descendant::a")
    public WebElementFacade downloadStandardLineLink;

    @FindBy(xpath = "//caption[contains(text(), 'Available Template')]/parent::table/descendant::a")
    public WebElementFacade downloadTemplateLink;

    @FindBy(xpath = "//textarea[@name='CaseNote_PhonecallNote']")
    public WebElementFacade summariseCallTextBox;

    //Basic Methods

    public void enterTextInSummariseCallTextbox() {
        typeInto(summariseCallTextBox, generateRandomString());
    }

    // Multi Step Methods

    public void getToDraftCaseRejectionScreenPrerequisites() {
        clickOn(answeredByMyTeamNoRadioButton);
        clickOn(continueButton);
    }

    public void getToHowDoYouIntendToRespondScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        waitABit(500);
    }

    public void getToSummariseYouCallScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(phoneReplyRadioButton);
        clickOn(continueButton);

    }

    public void getToPrimaryDraftDocumentScreenPrerequisites() {
        if (isElementDisplayed($("//span[contains(text(), 'DTEN')]"))) {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            waitABit(500);
        } else {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            clickOn(letterReplyRadioButton);
            clickOn(continueButton);
        }
    }

    public void getToAddDocumentScreenPrerequisites() {
        if (isElementDisplayed($("//span[contains(text(), 'DTEN')]"))) {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            clickOn(addDocuments.addDocumentsButton);
        } else {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            clickOn(letterReplyRadioButton);
            clickOn(continueButton);
            clickOn(addDocuments.addDocumentsButton);
        }
    }

    public void getToDoYouWantToQAOfflineScreenPrerequisites() {
        if (isElementDisplayed($("//span[contains(text(), 'DTEN')]"))) {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
//            waitABit(500);
            addDocuments.addADraftDocument();
            clickOn(continueButton);
            waitABit(500);
        } else {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            clickOn(letterReplyRadioButton);
            clickOn(continueButton);
            addDocuments.addADraftDocument();
            clickOn(continueButton);
            waitABit(500);
        }
    }


    public void getToWhoDidTheQAOfflineScreenPrerequisites() {
        if (isElementDisplayed($("//span[contains(text(), 'DTEN')]"))) {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            addDocuments.addADraftDocument();
            clickOn(continueButton);
            clickOn(qa.offlineQaYesRadioButton);
            clickOn(continueButton);
            clickOn(finishButton);
        } else {
            clickOn(answeredByMyTeamYesRadioButton);
            clickOn(continueButton);
            clickOn(letterReplyRadioButton);
            clickOn(continueButton);
            addDocuments.addADraftDocument();
            clickOn(continueButton);
            clickOn(qa.offlineQaYesRadioButton);
            clickOn(continueButton);
            clickOn(finishButton);
        }
    }

    public void moveDTENCaseFromDraftToPrivateOffice() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        addDocuments.addADraftDocument();
        clickOn(continueButton);
        clickOn(qa.offlineQaYesRadioButton);
        clickOn(continueButton);
        qa.selectOfflineQualityAssurer("Eamon Droko (eamon.droko@homeoffice.gov.uk)");
        clickOn(finishButton);
    }

    public void acceptAndDraftALetter() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(letterReplyRadioButton);
        clickOn(continueButton);
    }

    public void dtenAcceptAndDraftALetter() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
    }

    public void initialDraftFullFlow() {
        waitABit(3500);
        WebElementFacade thisDraftTeam = findAll("//span[text()='" + sessionVariableCalled("draftTeam")
                + "']").get(0);
        thisDraftTeam.click();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        clickOn(workstacks.allocateToMeButton);
        clickOn(homepage.home);
        clickOn(homepage.myCases);
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        acceptAndDraftALetter();
        addDocuments.addADraftDocument();
        qa.dontQAOffline();
    }

    public void moveCaseFromInitialDraftToQaResponse() {
        acceptAndDraftALetter();
        addDocuments.addADraftDocument();
        qa.waitABit(500);
        qa.dontQAOffline();
    }

    public void moveTROCaseFromInitialDraftToQaResponse() {
        acceptAndDraftALetter();
        addDocuments.addADraftDocument();
        qa.waitABit(500);
        clickOn(continueButton);
    }

    public void moveDTENCaseFromInitialDraftToQaResponse() {
        dtenAcceptAndDraftALetter();
        addDocuments.addADraftDocument();
        qa.waitABit(500);
        qa.dontQAOffline();
    }

    public void completeInitialDraftStageAndStoreEnteredInformation() {
        clickOn(answeredByMyTeamYesRadioButton);
        setSessionVariable("selectedCanMyTeamAnswerRadioButton").to("ACCEPT");
        clickOn(continueButton);
        clickOn(letterReplyRadioButton);
        String typeOfResponseRadioButton = letterReplyRadioButton.getText();
        setSessionVariable("selectedTypeOfResponseRadioButton").to(typeOfResponseRadioButton);
        clickOn(continueButton);
        addDocuments.addADraftDocument();
        setSessionVariable("uploadedDocumentTitle").to("test1.docx");
        clickOn(continueButton);
        clickOn(qa.offlineQaNoRadioButton);
        String responseToQAOfflineRadioButton = qa.offlineQaNoRadioButton.getAttribute("for").substring(10);
        setSessionVariable("selectedResponseToQAOfflineRadioButton").to(responseToQAOfflineRadioButton);
        clickOn(continueButton);
    }

    // Assertions

    public void draftingDeadlineIsDisplayed() {
        assertThat(isElementDisplayed(draftingDeadline), is(true));
    }

    public void assertEnterCallNotesError() {
        assertThat(pleaseSummariseYourCallIsRequiredErrorMessage.getText(), is("Please summarise your call. is required"));
    }

    public void assertEnterQaMethodError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterRejectionReasonsError() {
        assertThat(shouldBeAnsweredErrorMessage.getText(), is("Why should this not be answered by your team? is required"));
    }

    public void assertCorrespondenceAnsweredErrorMessage() {
        assertThat(correspondenceAnsweredErrorMessage.getText(),
                is("Can this correspondence be answered by your team? is required"));
    }

    public void assertShouldBeAnsweredErrorMessage() {
        assertThat(shouldBeAnsweredErrorMessage.getText(),
                is("Why should this not be answered by your team? is required"));
    }

    public void assertHowDoYouIntendToRespondErrorMessage() {
        assertThat(howDoYouIntendToRespondErrorMessage.getText(),
                is("How do you intend to respond? is required"));
    }

    public void assertPleaseSummariseYourCallErrorMessage() {
        try {
            assertThat(pleaseSummariseYourCallIsRequiredErrorMessage.getText(),
                    is("Please summarise your call. is required"));
        } catch (Exception e) {
            clickOn(continueButton);
            assertThat(pleaseSummariseYourCallIsRequiredErrorMessage.getText(),
                    is("Please summarise your call. is required"));
        }
    }

    public void assertWhichIsThePrimaryDraftDocumentErrorMessage() {
        assertThat(whichIsThePrimaryDraftDocumentErrorMessage.getText(),
                is("Which is the primary draft document? is required"));
    }

    public void assertDoYouWantToQAThisOfflineErrorMessage() {
        assertThat(doYouWantToQAThisOfflineErrorMessage.getText(), is("Do you want to QA this offline? is required"));
    }

    public void assertWhoHasDoneOfflineQAErrorMessage() {
        assertThat(whoHadDoneTheOfflineQAErrorMessage.getText(),
                is("Who has done the Offline QA for this case? is required"));
    }
}
