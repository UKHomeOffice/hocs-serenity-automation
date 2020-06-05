package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

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

    @FindBy(xpath = "//label[text()='Reject Print for Signage']")
    public WebElementFacade rejectPrintForSignageRadioButton;

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
        dispatchedDateInput(1,1,2001);
        safeClickOn(confirmAndCloseCaseButton);
    }

    public void triggerErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE":
                safeClickOn(responseChannelEmailRadioButton);
                safeClickOn(dispatchedRadioButton);
                clickTheButton("Confirm");
                break;
            case "RESPONSE CHANNEL":
                dispatchedDateInput(1, 1, 2001);
                safeClickOn(dispatchedRadioButton);
                clickTheButton("Confirm");
                break;
            case "ACTIONS REQUIRED":
                dispatchedDateInput(1, 1, 2001);
                safeClickOn(responseChannelLetterRadioButton);
                clickTheButton("Confirm");
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
