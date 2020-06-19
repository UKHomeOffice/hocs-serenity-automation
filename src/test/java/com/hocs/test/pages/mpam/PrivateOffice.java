package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOffice extends BasePage {

    @FindBy(id = "DateDispatched-day")
    public WebElementFacade dispatchedDateDayTextField;

    @FindBy(id = "DateDispatched-month")
    public WebElementFacade dispatchedDateMonthTextField;

    @FindBy(id = "DateDispatched-year")
    public WebElementFacade dispatchedDateYearTextField;

    @FindBy(css = "label[for='ChannelOut-Email']")
    public WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade responseChannelLetterRadioButton;

    @FindBy(xpath = "//label[text()='Draft rejected by private office']")
    public WebElementFacade draftRejectedRadioButton;

    @FindBy(xpath = "//label[text()='Dispatched']")
    public WebElementFacade dispatchedRadioButton;

    @FindBy(xpath = "//a[text()='Dispatched date is required']")
    public WebElementFacade dispatchedDateRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Response channel is required']")
    public WebElementFacade responseChannelRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(css = "input[value='Confirm and close case']")
    public WebElementFacade confirmAndCloseCaseButton;

    @FindBy(id = "CaseNote_RejectPfs")
    public WebElementFacade rejectionReasonTextArea;

    @FindBy(xpath = "//input[@name='ChannelOut'][@checked]/following-sibling::label")
    public WebElementFacade selectedResponseChannel;

    public void dispatchedDateInput(int dd, int mm, int yyyy) {
        String day = Integer.toString(dd);
        String month = Integer.toString(mm);
        String year = Integer.toString(yyyy);
        typeInto(dispatchedDateDayTextField, day);
        typeInto(dispatchedDateMonthTextField, month);
        typeInto(dispatchedDateYearTextField, year);
    }

    public void moveCaseFromPrivateOfficeToCaseClosed() {
        safeClickOn(responseChannelEmailRadioButton);
        safeClickOn(dispatchedRadioButton);
        safeClickOn(confirmButton);
        dispatchedDateInput(1, 1, 2001);
        safeClickOn(confirmAndCloseCaseButton);
    }

    public void triggerErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE":
                safeClickOn(dispatchedRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(confirmAndCloseCaseButton);
                break;
            case "ACTIONS REQUIRED":
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    public void assertDispatchedDateErrorMessageDisplayed() {
        dispatchedDateRequiredErrorMessage.shouldContainText("Dispatched date is required");
    }

    public void assertResponseChannelErrorMessageDisplayed() {
        responseChannelRequiredErrorMessage.shouldContainText("Response channel is required");
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        actionsRequiredErrorMessage.shouldContainText("Actions is required");
    }

    public void submitReasonToRejectToDraft(String rejectionReason) {
        typeInto(rejectionReasonTextArea, rejectionReason);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void assertThatRejectionReasonTextAreaVisible() {
        assertThat(rejectionReasonTextArea.isVisible(), is(true));
    }

    public void assertThatResponseChannelIsPreSelected() {
        String previouslySelectedResponseChannel = sessionVariableCalled("responseChannel").toString().toUpperCase();
        String currentlySelectedResponseChannel = selectedResponseChannel.getText().toUpperCase();
        assertThat(currentlySelectedResponseChannel.equals(previouslySelectedResponseChannel), is(true));
    }
}
