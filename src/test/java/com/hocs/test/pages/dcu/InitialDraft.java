package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import config.User;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class InitialDraft extends BasePage {

    Documents documents;

    @FindBy(xpath = "//div[@id='InitialDraftDecision-radios']//label[text()='No']")
    public WebElementFacade answeredByMyTeamNoRadioButton;

    @FindBy(xpath = "//div[@id='InitialDraftDecision-radios']//label[text()='Yes']")
    public WebElementFacade answeredByMyTeamYesRadioButton;

    @FindBy(xpath = "//div[@id='ReturnToResponseChannelDecision-radios']//label[text()='No']")
    public WebElementFacade chooseAnotherResponseTypeNoButton;

    @FindBy(xpath = "//div[@id='ReturnToResponseChannelDecision-radios']//label[text()='Yes']")
    public WebElementFacade chooseAnotherResponseTypeYesButton;

    @FindBy(xpath = "//label[text()='Email']")
    public WebElementFacade emailReplyRadioButton;

    @FindBy(xpath = "//label[text()='Letter']")
    public WebElementFacade letterReplyRadioButton;

    @FindBy(xpath = "//label[text()='Phone']")
    public WebElementFacade phoneReplyRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_PhonecallNote']")
    public WebElementFacade summariseCallTextBox;

    @FindBy(xpath = "//div[@id='OfflineQA-radios']//label[text()='Yes']")
    public WebElementFacade offlineQaYesRadioButton;

    @FindBy(xpath = "//div[@id='OfflineQA-radios']//label[text()='No']")
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
        waitABit(1000);
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
            waitABit(1000);
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

    public void moveCaseFromInitialDraftToPrivateOffice() {
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
        letterReplyRadioButton.waitUntilVisible();
        safeClickOn(letterReplyRadioButton);
        jsClickOn(continueButton);
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
        waitABit(1000);
        dontQAOffline();
    }

    public void completeInitialDraftStageAndStoreEnteredInformation() {
        safeClickOn(answeredByMyTeamYesRadioButton);
        setSessionVariable("selectedCanMyTeamAnswerRadioButton").to(answeredByMyTeamYesRadioButton.getTextContent());
        safeClickOn(continueButton);
        safeClickOn(letterReplyRadioButton);
        setSessionVariable("selectedTypeOfResponseRadioButton").to(letterReplyRadioButton.getTextContent());
        waitABit(1000);
        jsClickOn(continueButton);
        documents.addADraftDocumentAtDraftStage();
        setSessionVariable("uploadedDocumentTitle").to("test.docx");
        continueButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().click();
        safeClickOn(offlineQaNoRadioButton);
        setSessionVariable("selectedResponseToQAOfflineRadioButton").to(offlineQaNoRadioButton.getTextContent());
        safeClickOn(continueButton);
    }

    // Assertions

    public void selectPrimaryDraft(String fileIdentifier) {
        WebElementFacade documentToSelect = find(By.xpath("//label[contains(text(),'"+ fileIdentifier +"')]"));
        safeClickOn(documentToSelect);
    }

    public void assertThatPrimaryDraftIs(String fileIdentifier) {
        primaryDraftDocumentName.shouldContainText(fileIdentifier);
    }
}
