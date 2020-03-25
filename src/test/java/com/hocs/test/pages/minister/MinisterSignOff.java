package com.hocs.test.pages.minister;

import com.hocs.test.pages.base_page.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinisterSignOff extends Page {

    @FindBy(css = "label[for='MinisterSignOffDecision-ACCEPT']")
    public WebElementFacade ministerSignOffAcceptRadioButton;

    @FindBy(css = "label[for='MinisterSignOffDecision-REJECT']")
    public WebElementFacade ministerSignOffRejectRadioButton;

    @FindBy(id = "CaseNote_MinisterReject")
    public WebElementFacade ministerRejectionNote;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade doYouApproveTheResponseErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade whatIsYourFeedbackMinisterSignOffErrorMessage;

    // Basic Methods

    public void enterMinisterRejectionNote() {
        typeInto(ministerRejectionNote, generateRandomString());
    }

    public void getToMinisterFeedbackResponseScreenPrerequisites() {
        clickOn(ministerSignOffRejectRadioButton);
        clickOn(continueButton);
        waitABit(500);
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        doYouApproveTheResponseErrorMessage.shouldContainText("Do you approve the response? is required");
    }

    public void assertFeedbackResponseMinisterSignOffErrorMessage() {
        whatIsYourFeedbackMinisterSignOffErrorMessage.shouldContainText("What is your feedback about the response? is required");
    }

    public void moveCaseFromMinisterToDispatch() {
        clickOn(ministerSignOffAcceptRadioButton);
        clickOn(continueButton);
    }

    public void completeMinisterialSignOffStageAndStoreEnteredInformation() {
        clickOn(ministerSignOffAcceptRadioButton);
        String ministerialSignOffDecision = ministerSignOffAcceptRadioButton.getAttribute("for").substring(24);
        setSessionVariable("ministerialSignOffDecision").to(ministerialSignOffDecision);
        clickOn(continueButton);

    }
}
