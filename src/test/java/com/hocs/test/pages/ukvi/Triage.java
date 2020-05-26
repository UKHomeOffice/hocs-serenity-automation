package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;


public class Triage extends BasePage {

    @FindBy(xpath = "//a[contains(text(), 'enquiry subject')]")
    public WebElementFacade setEnquiryHypertext;

    @FindBy(id = "BusUnit")
    public WebElementFacade businessUnitDropdown;

    @FindBy(xpath = "//label[text()='Ready to draft']")
    public WebElementFacade readyToDraftRadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    public WebElementFacade onHoldRadioButton;

    @FindBy(xpath = "//label[text()='Save changes']")
    public WebElementFacade saveChangesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Business unit is required']")
    public WebElementFacade businessUnitRequiredErrorMessage;

    //Triage (On Hold) Elements
    @FindBy(xpath = "//label[text()='Keep on hold']")
    public WebElementFacade keepOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    public void moveCaseFromTriageToDraft() {
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
        safeClickOn(readyToDraftRadioButton);
        clickTheButton("Confirm");
    }

    public void selectEnquirySubject(String subject) {
        safeClickOn(setEnquiryHypertext);
        WebElementFacade enquirySubjectRadioButton = findBy("//label[text()='" + subject + "']");
        safeClickOn(enquirySubjectRadioButton);
        clickTheButton("Continue");
    }

    public void selectEnquiryReason(String reason) {
        WebElementFacade enquiryReasonRadioButton = findBy("//label[text()='" + reason + "']");
        safeClickOn(enquiryReasonRadioButton);
        clickTheButton("Continue");
    }

    public void setBusinessUnit() {
        businessUnitDropdown.selectByIndex(1);
    }

    public void putTriageCaseOnHold() {
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
        safeClickOn(onHoldRadioButton);
        clickTheButton("Confirm");
    }

    public void escalateTriageCaseToWorkflowManager() {
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
        safeClickOn(escalateToWorkflowManagerRadioButton);
        clickTheButton("Confirm");
    }

    public void triggerErrorMessage(String error) {
        switch (error.toUpperCase()) {
            case "ACTIONS REQUIRED":
                businessUnitDropdown.selectByIndex(1);
                clickTheButton("Confirm");
                break;
            case "BUSINESS UNIT":
                clickOn(readyToDraftRadioButton);
                clickTheButton("Confirm");
                break;
            default:
                pendingStep(error + " is not defined within " + getMethodName());
        }
    }

    public void assertErrorMessageDisplayed(String error) {
        switch (error.toUpperCase()) {
            case "ACTIONS REQUIRED":
                actionsRequiredErrorMessage.shouldContainText("Actions is required");
                break;
            case "BUSINESS UNIT":
                businessUnitRequiredErrorMessage.shouldContainText("Business unit is required");
                break;
            default:
                pendingStep(error + " is not defined within " + getMethodName());
        }
    }
}
