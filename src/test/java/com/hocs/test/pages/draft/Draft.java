package com.hocs.test.pages.draft;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends Page {

    @FindBy(id = "")
    private WebElementFacade draftingDeadline;

    @FindBy(id = "")
    private WebElementFacade rejectNoteField;

    @FindBy(id = "")
    private WebElementFacade emailReplyRadioButton;

    @FindBy(id = "")
    private WebElementFacade letterReplyRadioButton;

    @FindBy(id = "")
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

    @FindBy(id = "")
    private WebElementFacade offlineQaRadioButton;

    @FindBy(id = "")
    private WebElementFacade onlineQaRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocateToOfflineQaDropdown;

    @FindBy(id = "")
    private WebElementFacade allocateToOnlineQaDropdown;

    public void assertEnterCallNotesError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterQaMethodError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterRejectionReasonsError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void clearAllocationNoteField() {
        allocationNoteFreeTextField.clear();
    }

    public void clickEmailReplyRadioButton() {
        emailReplyRadioButton.click();
    }

    public void clickLetterReplyRadioButton() {
        letterReplyRadioButton.click();
    }

    public void clickPhoneReplyRadioButton() {
        phoneReplyRadioButton.click();
    }

    public void draftingDeadlineIsDisplayed() {
        assertThat(isElementDisplayed(draftingDeadline), is(true));
    }

    public void enterAllocationNoteField() {
        clearAllocationNoteField();
        allocationNoteFreeTextField.sendKeys(generateRandomString());
    }

    public String getDraftingDeadline() {
        return draftingDeadline.getText();
    }

}
