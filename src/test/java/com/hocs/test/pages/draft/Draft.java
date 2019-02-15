package com.hocs.test.pages.draft;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.workstacks.Workstacks;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends Page {

    Homepage homepage;

    SuccessfulCaseCreation successfulCaseCreation;

    @FindBy(css = "label[for=InitialDraftDecision-REJECT]")
    private WebElementFacade answeredByMyTeamNoRadioButton;

    @FindBy(css = "label[for=InitialDraftDecision-ACCEPT")
    private WebElementFacade answeredByMyTeamYesRadioButton;

    @FindBy(xpath = "//a[text()='document']")
    private WebElementFacade draftStageAddDocumentsButton;

    @FindBy(xpath = "//a[text()='Can this correspondence be answered by your team? is required']")
    private WebElementFacade correspondenceAnsweredErrorMessage;

    @FindBy(xpath = "//a[text()='Why should this should not be answered by your team? is required']")
    private WebElementFacade shouldBeAnsweredErrorMessage;

    @FindBy(xpath = "//a[text()='How do you intend to respond? is required']")
    private WebElementFacade howDoYouIntendToRespondErrorMessage;

    @FindBy(xpath = "//a[text()='Please summarise your call. is required']")
    private WebElementFacade pleaseSummariseYourCallIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary draft document? is required']")
    private WebElementFacade whichIsThePrimaryDraftDocumentErrorMessage;

    @FindBy(xpath = "//a[text()='Do you want to QA this offline? is required']")
    private WebElementFacade doYouWantToQAThisOfflineErrorMessage;

    @FindBy(xpath = "//a[text()='Who has done the Offline QA for this case? is required']")
    private WebElementFacade whoHadDoneTheOfflineQAErrorMessage;

    @FindBy(id = "document_type")
    private WebElementFacade documentTypeDropDown;

    @FindBy(css = ".govuk-heading-l")
    public WebElementFacade draftAResponseHeader;

    @FindBy(id = "")
    private WebElementFacade draftingDeadline;

    @FindBy(id = "")
    private WebElementFacade rejectNoteField;

    @FindBy(id = "")
    private WebElementFacade emailReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-LETTER]")
    private WebElementFacade letterReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-PHONE]")
    private WebElementFacade phoneReplyRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocationNoteFreeTextField;

    @FindBy(id = "")
    private WebElementFacade downloadTemplateLink;

    @FindBy(id = "")
    private WebElementFacade addResponseButton;

    @FindBy(id = "")
    private WebElementFacade supportingDocumentsNoRadioButton;

    @FindBy(id = "")
    private WebElementFacade supportingDocumentsYesRadioButton;

    @FindBy(id = "")
    private WebElementFacade addSupportingDocumentsButton;

    @FindBy(id = "")
    private WebElementFacade standardLine;

    @FindBy(css = "label[for=OfflineQA-TRUE]")
    private WebElementFacade offlineQaRadioButton;

    @FindBy(css = "label[for=OfflineQA-FALSE]")
    private WebElementFacade onlineQaRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocateToOfflineQaDropdown;

    @FindBy(id = "")
    private WebElementFacade allocateToOnlineQaDropdown;

    // Basic Methods

    public void clickAddDocumentsButton() {
        draftStageAddDocumentsButton.click();
    }

    public void clearAllocationNoteField() {
        allocationNoteFreeTextField.clear();
    }

    public void selectDocumentTypeByIndex(int index) {
        documentTypeDropDown.selectByIndex(index);
    }

    public void clickAddResponseButton() {
        addResponseButton.click();
    }

    public void clickAnsweredByMyTeamNoRadioButton() {
        answeredByMyTeamNoRadioButton.click();
    }

    public void clickAnsweredByMyTeamYesRadioButton() {
        answeredByMyTeamYesRadioButton.click();
    }

    public void clickDownloadTemplateLink() {
        downloadTemplateLink.click();
    }

    public void clickEmailReplyRadioButton() {
        emailReplyRadioButton.click();
    }

    public void clickLetterReplyRadioButton() {
        letterReplyRadioButton.click();
    }

    public void clickNoSupportingDocumentsRadioButton() {
        supportingDocumentsNoRadioButton.click();
    }

    public void clickPhoneReplyRadioButton() {
        phoneReplyRadioButton.click();
    }

    public void clickStandardLine() {
        standardLine.click();
    }

    public void clickYesSupportingDocumentsRAdioButton() {
        supportingDocumentsYesRadioButton.click();
    }

    public Draft() {
    }

    public void enterAllocationNoteField() {
        clearAllocationNoteField();
        allocationNoteFreeTextField.sendKeys(generateRandomString());
    }

    public String getDraftingDeadline() {
        return draftingDeadline.getText();
    }

    public void selectOfflineQualityAssurer() {
        allocateToOfflineQaDropdown.selectByVisibleText("");
    }

    public void selectOnlineQualityAssurer() {
        allocateToOnlineQaDropdown.selectByVisibleText("");
    }

    // Multi Step Methods

    public void findAndAllocateDraftStage() {
        homepage.selectAnimalsInScienceTeam();
        successfulCaseCreation.selectCaseReferenceNumberViaXpath();
        workstacks.clickAllocateToMeButton();
        homepage.goHome();
    }

    // Assertions

    public void assertEnterCallNotesError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterQaMethodError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterRejectionReasonsError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertCorrespondenceAnsweredErrorMessage() {
        assertThat(correspondenceAnsweredErrorMessage.getText(),
                is("Can this correspondence be answered by your team? is required"));
    }

    public void assertShouldBeAnsweredErrorMessage() {
        assertThat(shouldBeAnsweredErrorMessage.getText(),
                is("Why should this should not be answered by your team? is required"));
    }

    public void assertHowDoYouIntendToRespondErrorMessage() {
        assertThat(howDoYouIntendToRespondErrorMessage.getText(),
                is("How do you intend to respond? is required"));
    }

    public void assertPleaseSummariseYourCallErrorMessage() {
        assertThat(pleaseSummariseYourCallIsRequiredErrorMessage.getText(), is ("Please summarise your call. is required"));
    }

    public void assertWhichIsThePrimaryDraftDocumentErrorMessage() {
        assertThat(whichIsThePrimaryDraftDocumentErrorMessage.getText(), is("Which is the primary draft document? is required"));
    }

    public void assertDoYouWantToQAThisOfflineErrorMessage() {
        assertThat(doYouWantToQAThisOfflineErrorMessage.getText(), is ("Do you want to QA this offline? is required"));
    }

    public void assertWhoHasDoneOfflineQAErrorMessage(){
        assertThat(whoHadDoneTheOfflineQAErrorMessage.getText(), is ("Who has done the Offline QA for this case? is required"));
    }

    public void draftingDeadlineIsDisplayed() {
        assertThat(isElementDisplayed(draftingDeadline), is(true));
    }

}
