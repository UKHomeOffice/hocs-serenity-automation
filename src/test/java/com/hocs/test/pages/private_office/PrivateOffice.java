package com.hocs.test.pages.private_office;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOffice extends Page {

    @FindBy(css = "label[for='PrivateOfficeDecision-ACCEPT']")
    public WebElementFacade privateOfficeAcceptRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-REJECT']")
    public WebElementFacade privateOfficeRejectRadioButton;

    @FindBy(css = "label[for='PrivateOfficeDecision-CHANGE']")
    public WebElementFacade privateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    public WebElementFacade privateOfficeRejectNoteField;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade doYouApproveTheResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Override Private Office Team is required']")
    public WebElementFacade overridePrivateOfficeTeamIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Why should this be approved by this team instead? is required']")
    public WebElementFacade whyShouldThisBeApprovedErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade whatIsYourFeedbackResponseErrorMessage;

    public void enterPORejectNotes() {
        waitFor(privateOfficeRejectNoteField);

        String poRejectNote = "Rejection Reason: " + generateRandomString();
        privateOfficeRejectNoteField.clear();
        privateOfficeRejectNoteField.sendKeys(poRejectNote);
        setSessionVariable("PORejectNote").to(poRejectNote);
    }

    public void getToChangeMinisterScreenPrerequisites() {
        clickOn(privateOfficeChangeMinisterRadioButton);
        clickOn(continueButton);
    }

    public void getToPOFeedbackResponseScreenPrerequisites() {
        clickOn(privateOfficeRejectRadioButton);
        clickOn(continueButton);
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        assertThat(doYouApproveTheResponseErrorMessage.getText(), is("Do you approve the response? is required"));
    }

    public void assertChangeMinisterErrorMessages() {
        assertThat(overridePrivateOfficeTeamIsRequiredErrorMessage.getText(), is ("Override Private Office Team is required"));
        assertThat(whyShouldThisBeApprovedErrorMessage.getText(), is("Why should this be approved by this team instead? is required"));
    }

    public void assertWhatIsYourFeedbackResponse() {
        assertThat(whatIsYourFeedbackResponseErrorMessage.getText(), is("What is your feedback about the response? is required"));
    }

    public void moveCaseFromPrivateOfficeToMinisterSignOff() {
        privateOfficeAcceptRadioButton.click();
        clickContinueButton();
    }

    public void moveDTENCaseFromPrivateOfficeToDispatch() {
        clickOn(privateOfficeAcceptRadioButton);
        clickOn(continueButton);
    }
}
