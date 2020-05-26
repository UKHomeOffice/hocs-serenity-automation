package com.hocs.test.pages.ukvi;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import java.util.List;
import java.util.ArrayList;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.containsString;

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

    @FindBy(xpath = "//a[text()='Action menu - select one option is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Business unit is required']")
    public WebElementFacade businessUnitRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Enquiry subject is required']")
    public WebElementFacade enquirySubjectRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Enquiry reason is required']")
    public WebElementFacade enquiryReasonRequiredErrorMessage;

    @FindBy(xpath = "//label[@id='EnquirySubject']")
    public WebElementFacade setEnquirySubject;

    @FindBy(xpath = "//label[@id='EnquiryReason']")
    public WebElementFacade setEnquiryReason;

    //Triage (On Hold) Elements
    @FindBy(xpath = "//label[text()='Keep on hold']")
    public WebElementFacade keepOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Take off hold']")
    public WebElementFacade takeOffHoldRadioButton;

    @FindBy(css = "[value='Confirm']")
    public WebElementFacade confirmButton;

    private ArrayList<String> businessAreaOptions = new ArrayList<>();

    public void moveCaseFromTriageToDraft() {
        safeClickOn(setEnquiryHypertext);
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        businessUnitDropdown.selectByIndex(1);
        safeClickOn(readyToDraftRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectEnquirySubject(String subject) {
        WebElementFacade enquirySubjectRadioButton = findBy("//label[text()='" + subject + "']");
        safeClickOn(enquirySubjectRadioButton);
        setSessionVariable("enquirySubject").to(subject);
        safeClickOn(continueButton);
    }

    public void selectEnquiryReason(String reason) {
        WebElementFacade enquiryReasonRadioButton = findBy("//label[text()='" + reason + "']");
        safeClickOn(enquiryReasonRadioButton);
        setSessionVariable("enquiryReason").to(reason);
        safeClickOn(continueButton);
    }

    public void putTriageCaseOnHold() {
        safeClickOn(onHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void takeTriageCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void escalateTriageCaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        safeClickOn(confirmButton);
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        assertThat(actionsRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertSetEnquirySubject(String enquirySubject) {
        assertThat(setEnquirySubject.getText(), containsString(enquirySubject));
    }

    public void assertSetEnquiryReason(String enquiryReason) {
        assertThat(setEnquiryReason.getText(), containsString(enquiryReason));
    }

    public void assertBusinessUnitRequiredErrorMessageDisplayed() {
        assertThat(businessUnitRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertEnquirySubjectRequiredErrorMessageDisplayed() {
        assertThat(enquirySubjectRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertEnquiryReasonRequiredErrorMessageDisplayed() {
        assertThat(enquiryReasonRequiredErrorMessage.isVisible(), is(true));
    }

    public void recordCurrentBusinessAreaOptions() {
        List<WebElementFacade> businessAreaDropdownOptions = findAll("//select[@id='BusUnit']/option");
        for (WebElementFacade option: businessAreaDropdownOptions) {
            businessAreaOptions.add(option.getText());
        }
        for (String option: businessAreaOptions) {
            System.out.println(option);
        }
    }

    public void assertBusinessAreaOptionsChanged() {
        Boolean changed = false;
        List<WebElementFacade> businessAreaDropdownOptions = findAll("//select[@id='BusUnit']/option");
        for (WebElementFacade option: businessAreaDropdownOptions) {
            if (!businessAreaOptions.contains(option.getText())) {
                changed = true;
                break;
            }
        }
        assertThat(changed, is(true));
    }
}
