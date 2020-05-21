package com.hocs.test.pages.ukvi;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;


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

    public void moveCaseFromCaseTriageToCaseDraft() {
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        businessUnitDropdown.selectByIndex(0);
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
