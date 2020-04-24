package com.hocs.test.pages.DCU_Workflow;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinisterialSignOff extends BasePage {

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
        safeClickOn(ministerSignOffRejectRadioButton);
        safeClickOn(continueButton);
        waitABit(500);
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        doYouApproveTheResponseErrorMessage.shouldContainText("Do you approve the response? is required");
    }

    public void assertFeedbackResponseMinisterSignOffErrorMessage() {
        whatIsYourFeedbackMinisterSignOffErrorMessage.shouldContainText("What is your feedback about the response? is required");
    }

    public void moveCaseFromMinisterToDispatch() {
        safeClickOn(ministerSignOffAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void completeMinisterialSignOffStageAndStoreEnteredInformation() {
        safeClickOn(ministerSignOffAcceptRadioButton);
        String ministerialSignOffDecision = ministerSignOffAcceptRadioButton.getAttribute("for").substring(24);
        setSessionVariable("ministerialSignOffDecision").to(ministerialSignOffDecision);
        safeClickOn(continueButton);

    }
}
