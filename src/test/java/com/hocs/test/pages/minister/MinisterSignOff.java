package com.hocs.test.pages.minister;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinisterSignOff extends Page {

    @FindBy(css = "label[for='MinisterSignOffDecision-ACCEPT']")
    public WebElementFacade minsterSignOffAcceptRadioButton;

    @FindBy(css = "label[for='MinisterSignOffDecision-REJECT']")
    public WebElementFacade ministerSignOffRejectRadioButton;

    @FindBy(id = "CaseNote_MinisterReject")
    public WebElementFacade ministerRejectionNote;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade doYouApproveTheResponseErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade whatIsYourFeedbackMinisterSignOffErrorMessage;

    // Basic Methods

    public void clearRejectionNoteField() {
        ministerRejectionNote.clear();
    }

    public void enterMinisterRejectionNote() {
        clearRejectionNoteField();
        ministerRejectionNote.sendKeys(generateRandomString());
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        assertThat(doYouApproveTheResponseErrorMessage.getText(), is("Do you approve the response? is required"));
    }

    public void assertFeedbackResponseMinisterSignOffErrorMessage() {
        assertThat(whatIsYourFeedbackMinisterSignOffErrorMessage.getText(), is("What is your feedback about the response? is required"));
    }
}
