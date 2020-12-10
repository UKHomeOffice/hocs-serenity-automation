package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CloseCaseTelephone extends BasePage {

    @FindBy(xpath = "//label[text()='Close Case (Telephone)']")
    public WebElementFacade closeCaseTelephoneRadioButton;

    @FindBy(xpath = "//label[@for='ChannelOut-Email']")
    public WebElementFacade emailResponseChannelRadioButton;

    @FindBy(xpath = "//label[@for='ChannelOut-Letter']")
    public WebElementFacade letterResponseChannelRadioButton;

    @FindBy(xpath = "//label[@for='ChannelOut-Phone']")
    public WebElementFacade phoneResponseChannelRadioButton;

    @FindBy(xpath = "//label[@for='ChannelOut-Outreach']")
    public WebElementFacade outreachResponseChannelRadioButton;

    @FindBy(id = "CaseNote_CaseClose")
    public WebElementFacade explanationForClosingCaseTelephoneTextBox;

    @FindBy(xpath = "//label[text()='Telephone Surgery']")
    public WebElementFacade telephoneSurgeryContactRouteRadioButton;

    @FindBy(xpath = "//label[text()='MP Helpline']")
    public WebElementFacade mpHelplineContactRouteRadioButton;

    @FindBy(xpath = "//a[text()='Response channel is required']")
    public WebElementFacade responseChannelIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Explanation for closing case (Telephone) is required']")
    public WebElementFacade explanationForClosingCaseIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Telephone Contact Route is required']")
    public WebElementFacade telephoneContactRouteIsRequiredErrorMessage;

    public void selectCloseCaseTelephone() {
        safeClickOn(closeCaseTelephoneRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectResponseChannel(String channel) {
        WebElementFacade radioButton = null;
        switch (channel.toUpperCase()) {
            case "EMAIL":
                radioButton = emailResponseChannelRadioButton;
                break;
            case "LETTER":
                radioButton = letterResponseChannelRadioButton;
                break;
            case "PHONE":
                radioButton = phoneResponseChannelRadioButton;
                break;
            case "OUTREACH":
                radioButton = outreachResponseChannelRadioButton;
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void enterExplanationForClosingCase(String text) {
        typeInto(explanationForClosingCaseTelephoneTextBox, text);
    }

    public void selectTelephoneContactRoute(String contactRoute) {
        WebElementFacade radioButton = null;
        switch (contactRoute.toUpperCase()) {
            case "TELEPHONE SURGERY":
                radioButton = telephoneSurgeryContactRouteRadioButton;
                break;
            case "MP HELPLINE":
                radioButton = mpHelplineContactRouteRadioButton;
                break;
            default:
                pendingStep(contactRoute + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void assertErrorMessageIsDisplayed(String errorMessage) {
        WebElementFacade error = null;
        String message = null;
        switch (errorMessage.toUpperCase()) {
            case "RESPONSE CHANNEL":
                error = responseChannelIsRequiredErrorMessage;
                message = "Response channel is required";
                break;
            case "EXPLANATION FOR CLOSING CASE":
                error = explanationForClosingCaseIsRequiredErrorMessage;
                message = "Explanation for closing case (Telephone) is required";
                break;
            case "TELEPHONE CONTACT ROUTE":
                error = telephoneContactRouteIsRequiredErrorMessage;
                message = "Telephone Contact Route is required";
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
        error.shouldContainText(message);
    }
}
