package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CaseDispatching extends BasePage {

    @FindBy(id = "DateDispatched-day")
    public WebElementFacade dispatchedDayTextField;

    @FindBy(id = "DateDispatched-month")
    public WebElementFacade dispatchedMonthTextField;

    @FindBy(id = "DateDispatched-year")
    public WebElementFacade dispatchedYearTextField;

    @FindBy(css = "label[for='ChannelOut-Email']")
    public WebElementFacade outboundChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade outboundChannelLetterRadioButton;

    @FindBy(css = "label[for='ChannelOut-Phone']")
    public WebElementFacade outboundChannelPhoneRadioButton;

    @FindBy(css = "label[for='ChannelOut-Outreach']")
    public WebElementFacade outboundChannelOutreachRadioButton;

    @FindBy(xpath = "//label[text()='Pending']")
    public WebElementFacade pendingRadioButton;

    @FindBy(xpath = "//label[text()='Dispatched']")
    public WebElementFacade dispatchedRadioButton;

    @FindBy(xpath = "//a[text()='Dispatched date is required']")
    public WebElementFacade dispatchedDateRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Outbound Channel is required']")
    public WebElementFacade outboundChannelRequiredErrorMessage;

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

    public void moveCaseFromCaseDispatchingToCaseClosed() {
        dispatchedDateInput(1, 1, 2001);
        safeClickOn(outboundChannelEmailRadioButton);
        safeClickOn(dispatchedRadioButton);
        clickTheButton("Confirm");
    }

    public void assertDispatchedDateErrorMessageDisplayed() {
        safeClickOn(outboundChannelEmailRadioButton);
        safeClickOn(dispatchedRadioButton);
        clickTheButton("Confirm");
        dispatchedDateRequiredErrorMessage.shouldContainText("Dispatched date is required");
    }

    public void assertOutboundChannelErrorMessageDisplayed() {
        dispatchedDateInput(1, 1, 2001);
        safeClickOn(dispatchedRadioButton);
        clickTheButton("Confirm");
        outboundChannelRequiredErrorMessage.shouldContainText("Outbound Channel is required");
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        dispatchedDateInput(1, 1, 2001);
        safeClickOn(outboundChannelLetterRadioButton);
        clickTheButton("Confirm");
        actionsRequiredErrorMessage.shouldContainText("Actions is required");
    }
}
