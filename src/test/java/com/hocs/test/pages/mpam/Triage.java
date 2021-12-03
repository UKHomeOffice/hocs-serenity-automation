package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.SummaryTab;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;


public class Triage extends BasePage {

    RecordCaseData recordCaseData;

    SummaryTab summaryTab;

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

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

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

    @FindBy(xpath = "//label[text()='Close duplicate case']")
    public WebElementFacade closeCaseRadioButton;

    @FindBy(id = "CaseNote_EscalateToWorkFlowManager")
    public WebElementFacade escalationReasonTextArea;

    @FindBy(id = "CaseNote_TriageRequestContribution")
    public WebElementFacade requestContributionTextArea;

    @FindBy(xpath = "//input[@name='RefType'][@checked]")
    public WebElementFacade selectedRefType;

    @FindBy(xpath = "//label[text()='Confirm']")
    public WebElementFacade confirmRadioButton;

    private List<String> recordedBusinessUnitOptions = new ArrayList<>();

    public void selectEnquirySubject(String subject) {
        waitForPageWithTitle("Enquiry subject");
        selectSpecificRadioButton(subject);
        setSessionVariable("enquirySubject").to(subject);
        safeClickOn(continueButton);
    }

    public void selectEnquiryReason(String reason) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(reason, "Enquiry reason");
        setSessionVariable("enquiryReason").to(reason);
        safeClickOn(continueButton);
    }

    public void selectComplianceMeasure(String complianceMeasure) {
        checkSpecificCheckbox(complianceMeasure);
    }

    public void enterComplianceMeasureDetails() {
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Compliance Measure Details", "Compliance measures other details");
        setSessionVariable("complianceMeasureDetails").to("Test Compliance Measure Details");
    }

    public void setBusinessUnit() {
        businessUnitDropdown.waitUntilEnabled();
        businessUnitDropdown.selectByIndex(1);
        setSessionVariable("businessUnit").to(businessUnitDropdown.getValue());
    }

    public void putTriageCaseOnHold() {
        safeClickOn(onHoldRadioButton);
        setSessionVariable("action").to("Put on hold");
        safeClickOn(confirmButton);
    }

    public void takeTriageCaseOffHold() {
        safeClickOn(takeOffHoldRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectEscalateTriageCaseToWorkflowManager() {
        safeClickOn(escalateToWorkflowManagerRadioButton);
        setSessionVariable("action").to("Escalate to workflow manager");
        safeClickOn(confirmButton);
    }

    public void submitReasonToEscalateCase(String escalationReason) {
        escalationReasonTextArea.sendKeys(escalationReason);
        safeClickOn(confirmButton);
        setSessionVariable("escalationReason").to(escalationReason);
    }

    public void assertSetEnquirySubject(String enquirySubject) {
        assertThat(setEnquirySubject.getText(), containsString(enquirySubject));
    }

    public void assertSetEnquiryReason(String enquiryReason) {
        assertThat(setEnquiryReason.getText(), containsString(enquiryReason));
    }

    public void recordCurrentBusinessUnitOptions() {
        List<WebElementFacade> businessUnitDropdownOptions = findAll("//select[@id='BusUnit']/option");
        for (WebElementFacade option: businessUnitDropdownOptions) {
            recordedBusinessUnitOptions.add(option.getText());
        }
    }

    public void assertBusinessUnitOptionsChanged() {
        List<String> currentBusinessAreaOptions = new ArrayList<>();
        List<WebElementFacade> businessAreaDropdownOptions = findAll("//select[@id='BusUnit']/option");
        for (WebElementFacade option: businessAreaDropdownOptions) {
            currentBusinessAreaOptions.add(option.getText());
        }
        assertThat(checkIfBusinessAreaListsDiffer(currentBusinessAreaOptions, recordedBusinessUnitOptions), is(true));
    }

    public boolean checkIfBusinessAreaListsDiffer(List<String> listA, List<String> listB) {
        return !(listA.size() == listB.size() && listA.containsAll(listB) && listB.containsAll(listA));
    }

    public void deescalateTriageCase() {
        safeClickOn(escalationCompleteRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectToCloseEscalatedCase() {
        safeClickOn(setEnquiryHypertext);
        selectEnquirySubject("Person Specific");
        selectEnquiryReason("Allowed appeal enquiry update");
        setBusinessUnit();
        safeClickOn(closeCaseRadioButton);
        safeClickOn(confirmButton);
    }

    public void selectSaveChangesAction() {
        safeClickOn(saveChangesRadioButton);
    }

    public void assertReferenceChangeWillConvertCaseTo(String ministerialOrOfficial) {
        assertThat(header1.getText().contains(ministerialOrOfficial), is(true));
    }

    public void assertCasesCurrentRefTypeIs(String ministerialOrOfficial) {
        assertThat(selectedRefType.getValue().toUpperCase().equals(ministerialOrOfficial), is(true));
    }
}
