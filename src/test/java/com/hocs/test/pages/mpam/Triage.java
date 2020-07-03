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

    @FindBy(xpath = "//a[contains(@href, 'UpdateBusinessArea')]")
    public WebElementFacade changeBusinessAreaLink;

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

    @FindBy(xpath = "//label[text()='Requested contribution']")
    public WebElementFacade requestedContributionRadioButton;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Deadline for contribution request is required']")
    public WebElementFacade contributionRequestDeadlineRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='What you are requesting is required']")
    public WebElementFacade contributionRequestDescriptionRequiredErrorMessage;

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

    @FindBy(xpath = "//label[text()='Close case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(id = "CaseNote_TriageClose")
    public WebElementFacade closureReasonTextArea;

    @FindBy(id = "RequestContributionDeadline-day")
    public WebElementFacade requestContributionDeadlineDayTextField;

    @FindBy(id = "RequestContributionDeadline-month")
    public WebElementFacade requestContributionDeadlineMonthTextField;

    @FindBy(id = "RequestContributionDeadline-year")
    public WebElementFacade requestContributionDeadlineYearTextField;

    @FindBy(id = "CaseNote_TriageRequestContribution")
    public WebElementFacade requestContributionTextArea;

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

    public void selectToCloseEscalatedCase() {
        safeClickOn(closeCaseRadioButton);
        safeClickOn(confirmButton);
    }

    public void submitReasonToCloseEscalatedCase(String closureReason) {
        typeInto(closureReasonTextArea, closureReason);
        safeClickOn(confirmButton);
        setSessionVariable("closureReason").to(closureReason);
    }

    public void enterRequestContributionDeadlineDate(String dd, String mm, String yyyy) {
        typeInto(requestContributionDeadlineDayTextField, dd);
        typeInto(requestContributionDeadlineMonthTextField, mm);
        typeInto(requestContributionDeadlineYearTextField, yyyy);
        setSessionVariable("requestDeadline").to(dd + "/" + mm + "/" + yyyy);
    }

    public void selectRequestContribution() {
        safeClickOn(setEnquiryHypertext);
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
        safeClickOn(requestedContributionRadioButton);
        safeClickOn(confirmButton);
    }

    public void enterRequestDescription(String requestDescription) {
        typeInto(requestContributionTextArea, requestDescription);
        setSessionVariable("requestDescription").to(requestDescription);
    }

    public void assertContributionRequestDeadlineRequiredErrorMessageDisplayed()  {
        assertThat(contributionRequestDeadlineRequiredErrorMessage.isVisible(), is(true));
    }

    public void assertContributionRequestDescriptionRequiredErrorMessageDisplayed()  {
        assertThat(contributionRequestDescriptionRequiredErrorMessage.isVisible(), is(true));
    }
}
