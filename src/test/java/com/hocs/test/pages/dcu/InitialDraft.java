package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.Documents;
import com.hocs.test.pages.Workstacks;
import config.User;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class InitialDraft extends BasePage {

    Documents documents;

    Dashboard dashboard;

    Workstacks workstacks;

    CreateCase_SuccessPage createCaseSuccessPage;

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

    @FindBy(xpath = "//a[text()='Primary draft document is required']")
    public WebElementFacade whichIsThePrimaryDraftDocumentErrorMessage;

    @FindBy(xpath = "//a[text()='Do you want to QA this offline? is required']")
    public WebElementFacade doYouWantToQAThisOfflineErrorMessage;

    @FindBy(xpath = "//a[text()='Who has done the Offline QA for this case? is required']")
    public WebElementFacade whoHadDoneTheOfflineQAErrorMessage;

    @FindBy(css = "label[for=ResponseChannel-EMAIL]")
    public WebElementFacade emailReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-LETTER]")
    public WebElementFacade letterReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-PHONE]")
    public WebElementFacade phoneReplyRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_PhonecallNote']")
    public WebElementFacade summariseCallTextBox;

    @FindBy(css = "label[for='OfflineQA-TRUE']")
    public WebElementFacade offlineQaYesRadioButton;

    @FindBy(css = "label[for='OfflineQA-FALSE']")
    public WebElementFacade offlineQaNoRadioButton;

    @FindBy(id = "OfflineQaUser")
    public WebElementFacade allocateToOfflineQaDropdown;

    @FindBy(xpath = "//strong[text()='Primary Draft']/parent::td/preceding-sibling::td")
    public WebElementFacade primaryDraftDocumentName;

    //Basic Methods

    public void enterTextInSummariseCallTextbox() {
        summariseCallTextBox.sendKeys(generateRandomString());
    }

    // Multi Step Methods

    public void getToDraftCaseRejectionScreenPrerequisites() {
        safeClickOn(answeredByMyTeamNoRadioButton);
        safeClickOn(continueButton);
    }

    public void getToHowDoYouIntendToRespondScreenPrerequisites() {
        safeClickOn(answeredByMyTeamYesRadioButton);
        safeClickOn(continueButton);
        waitABit(500);
    }

    public void getToSummariseYouCallScreenPrerequisites() {
        safeClickOn(answeredByMyTeamYesRadioButton);
        safeClickOn(continueButton);
        safeClickOn(phoneReplyRadioButton);
        safeClickOn(continueButton);

    }

    public void dontQAOffline() {
        continueButton.withTimeoutOf(Duration.ofMinutes(1)).waitUntilVisible();
        safeClickOn(continueButton);
        safeClickOn(offlineQaNoRadioButton);
        safeClickOn(continueButton);
    }

    public void selectOfflineQualityAssurer(String userName) {
        allocateToOfflineQaDropdown.selectByVisibleText(userName);
    }

    public void getToPrimaryDraftDocumentScreenPrerequisites() {
        if (sessionVariableCalled("caseType").equals("DTEN")) {
            dtenAcceptAndDraftALetter();
        } else {
            acceptAndDraftALetter();
        }
    }

    public void getToAddDocumentScreenPrerequisites() {
        if (sessionVariableCalled("caseType").equals("DTEN")) {
            dtenAcceptAndDraftALetter();
        } else {
            acceptAndDraftALetter();
        }
            documents.addDocumentsButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().click();
    }

    public void getToDoYouWantToQAOfflineScreenPrerequisites() {
        if (sessionVariableCalled("caseType").equals("DTEN")) {
            dtenAcceptAndDraftALetter();
        } else {
            acceptAndDraftALetter();
        }
            documents.addADraftDocumentAtDraftStage();
            continueButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().click();
            waitABit(500);
    }

    public void getToWhoDidTheQAOfflineScreenPrerequisites() {
        if (sessionVariableCalled("caseType").equals("DTEN")) {
            dtenAcceptAndDraftALetter();
        } else {
            acceptAndDraftALetter();
        }
            documents.addADraftDocumentAtDraftStage();
            continueButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().click();
            safeClickOn(offlineQaYesRadioButton);
            safeClickOn(continueButton);
            safeClickOn(finishButton);
    }

    public void moveCaseFromDraftToPrivateOffice() {
        if (sessionVariableCalled("caseType").equals("DTEN")) {
            dtenAcceptAndDraftALetter();
        } else {
            acceptAndDraftALetter();
        }
        documents.addADraftDocumentAtDraftStage();
        safeClickOn(continueButton);
        safeClickOn(offlineQaYesRadioButton);
        safeClickOn(continueButton);
        selectOfflineQualityAssurer(User.CAMERON.getAllocationText());
        safeClickOn(finishButton);
    }

    public void acceptAndDraftALetter() {
        safeClickOn(answeredByMyTeamYesRadioButton);
        safeClickOn(continueButton);
        waitFor(letterReplyRadioButton);
        safeClickOn(letterReplyRadioButton);
        safeClickOn(continueButton);
    }

    public void dtenAcceptAndDraftALetter() {
        safeClickOn(answeredByMyTeamYesRadioButton);
        safeClickOn(continueButton);
    }

    public void moveCaseFromInitialDraftToQaResponse() {
        if (sessionVariableCalled("caseType").equals("DTEN")) {
            dtenAcceptAndDraftALetter();
        } else {
            acceptAndDraftALetter();
        }
        documents.addADraftDocumentAtDraftStage();
        waitABit(500);
        dontQAOffline();
    }

    public void completeInitialDraftStageAndStoreEnteredInformation() {
        safeClickOn(answeredByMyTeamYesRadioButton);
        setSessionVariable("selectedCanMyTeamAnswerRadioButton").to(answeredByMyTeamYesRadioButton.getTextContent());
        safeClickOn(continueButton);
        safeClickOn(letterReplyRadioButton);
        setSessionVariable("selectedTypeOfResponseRadioButton").to(letterReplyRadioButton.getTextContent());
        safeClickOn(continueButton);
        documents.addADraftDocumentAtDraftStage();
        setSessionVariable("uploadedDocumentTitle").to("test.docx");
        continueButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().click();
        safeClickOn(offlineQaNoRadioButton);
        setSessionVariable("selectedResponseToQAOfflineRadioButton").to(offlineQaNoRadioButton.getTextContent());
        safeClickOn(continueButton);
    }

    // Assertions

    public void assertEnterCallNotesError() {
        pleaseSummariseYourCallIsRequiredErrorMessage.shouldContainText("Please summarise your call. is required");
    }

    public void assertEnterRejectionReasonsError() {
        shouldBeAnsweredErrorMessage.shouldContainText("Why should this not be answered by your team? is required");
    }

    public void assertCorrespondenceAnsweredErrorMessage() {
        correspondenceAnsweredErrorMessage.shouldContainText("Can this correspondence be answered by your team? is required");
    }

    public void assertShouldBeAnsweredErrorMessage() {
        shouldBeAnsweredErrorMessage.shouldContainText("Why should this not be answered by your team? is required");
    }

    public void assertHowDoYouIntendToRespondErrorMessage() {
       howDoYouIntendToRespondErrorMessage.shouldContainText("How do you intend to respond? is required");
    }

    public void assertPleaseSummariseYourCallErrorMessage() {
        try {
            pleaseSummariseYourCallIsRequiredErrorMessage.shouldContainText("Please summarise your call. is required");
        } catch (Exception e) {
            safeClickOn(continueButton);
            pleaseSummariseYourCallIsRequiredErrorMessage.shouldContainText("Please summarise your call. is required");
        }
    }

    public void assertWhichIsThePrimaryDraftDocumentErrorMessage() {
        whichIsThePrimaryDraftDocumentErrorMessage.shouldContainText("Primary draft document is required");
    }

    public void assertDoYouWantToQAThisOfflineErrorMessage() {
        doYouWantToQAThisOfflineErrorMessage.shouldContainText("Do you want to QA this offline? is required");
    }

    public void assertWhoHasDoneOfflineQAErrorMessage() {
        whoHadDoneTheOfflineQAErrorMessage.shouldContainText("Who has done the Offline QA for this case? is required");
    }

    public void selectPrimaryDraft(String fileIdentifier) {
        WebElementFacade documentToSelect = find(By.xpath("//label[contains(text(),'"+ fileIdentifier +"')]"));
        safeClickOn(documentToSelect);
    }

    public void assertThatPrimaryDraftIs(String fileIdentifier) {
        primaryDraftDocumentName.shouldContainText(fileIdentifier);
    }
}
