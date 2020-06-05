package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AwaitingDispatch extends BasePage {

    @FindBy(id = "DateDispatched-day")
    public WebElementFacade dispatchedDayTextField;

    @FindBy(id = "DateDispatched-month")
    public WebElementFacade dispatchedMonthTextField;

    @FindBy(id = "DateDispatched-year")
    public WebElementFacade dispatchedYearTextField;

    @FindBy(css = "label[for='ChannelOut-Email']")
    public WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade responseChannelLetterRadioButton;

    @FindBy(css = "label[for='ChannelOut-Phone']")
    public WebElementFacade responseChannelPhoneRadioButton;

    @FindBy(css = "label[for='ChannelOut-Outreach']")
    public WebElementFacade responseChannelOutreachRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesButton;

    @FindBy(xpath = "//label[text()='Dispatch, close case']")
    public WebElementFacade dispatchedRadioButton;

    @FindBy(xpath = "//a[text()='Dispatched date is required']")
    public WebElementFacade dispatchedDateRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Response channel is required']")
    public WebElementFacade responseChannelRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    public void dispatchedDateInput(int dd, int mm, int yyyy) {
        String day = Integer.toString(dd);
        String month = Integer.toString(mm);
        String year = Integer.toString(yyyy);
        typeInto(dispatchedDayTextField, day);
        typeInto(dispatchedMonthTextField, month);
        typeInto(dispatchedYearTextField, year);
    }

    public void moveCaseFromAwaitingDispatchToCaseClosed() {
        dispatchedDateInput(1, 1, 2001);
        safeClickOn(responseChannelEmailRadioButton);
        safeClickOn(dispatchedRadioButton);
        clickTheButton("Confirm");
    }

    public void triggerErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE":
                safeClickOn(responseChannelEmailRadioButton);
                safeClickOn(dispatchedRadioButton);
                safeClickOn(confirmButton);
                break;
            case "RESPONSE CHANNEL":
                dispatchedDateInput(1, 1, 2001);
                safeClickOn(dispatchedRadioButton);
                safeClickOn(confirmButton);
                break;
            case "ACTIONS REQUIRED":
                dispatchedDateInput(1, 1, 2001);
                safeClickOn(responseChannelLetterRadioButton);
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
}
