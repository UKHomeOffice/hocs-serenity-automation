package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;


public class Triage extends BasePage {

    @FindBy(xpath = "//label[text()='Ready to draft']")
    public WebElementFacade readyToDraftRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='On Hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changed']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    //Triage (On Hold) Elements
    @FindBy(xpath = "//label[text()='Keep on hold']")
    public WebElementFacade keepOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    public void moveCaseFromTriageToDraft() {
        safeClickOn(readyToDraftRadioButton);
        clickTheButton("Confirm");
    }

    public void putTriageCaseOnHold() {
        safeClickOn(onHoldRadioButton);
        clickTheButton("Confirm");
    }

    public void escalateTriageCaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        clickTheButton("Confirm");
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        clickTheButton("Confirm");
        actionsRequiredErrorMessage.shouldContainText("Actions is required");
    }
}
