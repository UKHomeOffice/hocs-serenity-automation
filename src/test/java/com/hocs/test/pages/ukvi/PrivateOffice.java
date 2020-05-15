package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;
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
    public WebElementFacade outboundChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade outboundChannelLetterRadioButton;

    @FindBy(xpath = "//label[text()='Reject Print for Signage']")
    public WebElementFacade rejectPrintForSignageRadioButton;

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
        typeInto(dispatchedDateDayTextField, day);
        typeInto(dispatchedDateMonthTextField, month);
        typeInto(dispatchedDateYearTextField, year);
    }

    public void moveCaseFromPrivateOfficeToCaseClosed() {
        dispatchedDateInput(1,1,2001);
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
