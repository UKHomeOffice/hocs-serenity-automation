package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.SummaryTab;
import java.util.List;
import java.util.ArrayList;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.containsString;


public class Triage extends BasePage {

    SummaryTab summary;

    @FindBy(xpath = "//a[contains(text(), 'Set enquiry subject')]")
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

    @FindBy(xpath = "//label[text()='Keep escalated']")
    public WebElementFacade keepEscalatedRadioButton;

    @FindBy(xpath = "//label[text()='Escalation complete']")
    public WebElementFacade escalationCompleteRadioButton;

    private List<String> recordedBusinessAreaOptions = new ArrayList<>();

    public void moveCaseFromTriageToDraft() {
        safeClickOn(setEnquiryHypertext);
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
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

    public void setBusinessUnit() {
        businessUnitDropdown.selectByIndex(1);
        setSessionVariable("businessUnit").to(businessUnitDropdown.getValue());
    }

    public void putTriageCaseOnHold() {
        safeClickOn(setEnquiryHypertext);
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
        safeClickOn(onHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void takeTriageCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void escalateTriageCaseToWorkflowManager() {
        safeClickOn(setEnquiryHypertext);
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
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
            recordedBusinessAreaOptions.add(option.getText());
        }
    }

    public void assertBusinessAreaOptionsChanged() {
        List<String> currentBusinessAreaOptions = new ArrayList<>();
        List<WebElementFacade> businessAreaDropdownOptions = findAll("//select[@id='BusUnit']/option");
        for (WebElementFacade option: businessAreaDropdownOptions) {
            currentBusinessAreaOptions.add(option.getText());
        }
        assertThat(checkIfBusinessAreaListsDiffer(currentBusinessAreaOptions, recordedBusinessAreaOptions), is(true));
    }

    public boolean checkIfBusinessAreaListsDiffer(List<String> listA, List<String> listB) {
        return !(listA.size() == listB.size() && listA.containsAll(listB) && listB.containsAll(listA));
    }

    public void deescalateTriageCase() {
        safeClickOn(escalationCompleteRadioButton);
        safeClickOn(confirmButton);
    }
}
