package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CloseCaseTelephone extends BasePage {

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Close Case (Telephone)']")
    public WebElementFacade closeCaseTelephoneRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "CaseNote_CaseClose")
    public WebElementFacade explanationForClosingCaseTelephoneTextBox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='Telephone Surgery']")
    public WebElementFacade telephoneSurgeryContactRouteRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//label[text()='MP Helpline']")
    public WebElementFacade mpHelplineContactRouteRadioButton;

    public void selectCloseCaseTelephone() {
        safeClickOn(closeCaseTelephoneRadioButton);
        clickConfirmButton();
    }

    public void selectResponseChannel(String responseChannel) {
        selectSpecificRadioButton(responseChannel);
    }

    public void enterExplanationForClosingCase(String text) {
        explanationForClosingCaseTelephoneTextBox.sendKeys(text);
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
}
