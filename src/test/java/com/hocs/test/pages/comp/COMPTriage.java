package com.hocs.test.pages.comp;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class COMPTriage extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//button[text()='Case Details']")
    public WebElementFacade caseDetailsAccordionButton;

    @FindBy(xpath = "//textarea[@name='CaseSummary']")
    public WebElementFacade caseSummaryTextArea;

    @FindBy(xpath = "//label[contains(text(),'Yes - accept the complaint')]")
    public WebElementFacade acceptTheComplaintRadioButton;

    @FindBy(xpath = "//label[contains(text(),'No - transfer the complaint')]")
    public WebElementFacade transferTheComplaintRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_TriageTransfer']")
    public WebElementFacade transferReasonTextArea;

    @FindBy(xpath = "//label[contains(text(),'CCH')]")
    public WebElementFacade transferToCCHRadioButton;

    @FindBy(css = "label[for='CctCompType-Minor']")
    public WebElementFacade transferToMinorMisconductRadioButton;

    @FindBy(id = "BusArea")
    public WebElementFacade businessAreaDropdown;

    @FindBy(id = "EnqReason")
    public WebElementFacade enquiryReasonDropdown;

    @FindBy(xpath = "//label[contains(text(),'Yes')]")
    public WebElementFacade loaRequiredYesRadioButton;

    @FindBy(xpath = "//label[contains(text(),'No')]")
    public WebElementFacade loaRequiredNoRadioButton;

    @FindBy(xpath = "//a[text()='Add complainant contribution']")
    public WebElementFacade addComplainantContributionHypertext;

    @FindBy(css = "label[for='LoaReceived_Yes']")
    public WebElementFacade loaReceivedCheckbox;

    @FindBy(xpath = "//a[text()='Add business contribution']")
    public WebElementFacade addBusinessContributionHypertext;

    @FindBy(xpath = "//input[@name='LoaDate-day']")
    public WebElementFacade loaReceivedDayField;

    @FindBy(xpath = "//input[@name='LoaDate-month']")
    public WebElementFacade loaReceivedMonthField;

    @FindBy(xpath = "//input[@name='LoaDate-year']")
    public WebElementFacade loaReceivedYearField;

    @FindBy(xpath = "//label[text()='All information collected - case ready for drafting']")
    public WebElementFacade readyForDraftingRadioButton;

    @FindBy(xpath = "//label[text()='Escalate case to WFM']")
    public WebElementFacade escalateToWFMRadioButton;

    @FindBy(id = "CaseNote_TriageEscalate")
    public WebElementFacade reasonForEscalationTextField;

    @FindBy(xpath = "//input[@value='Escalate case']")
    public WebElementFacade escalateCaseButton;

    @FindBy(xpath = "//label[text()='No response - complete the case (close permanently)']")
    public WebElementFacade noResponseCloseCaseRadioButton;

    @FindBy(id = "CaseNote_CompleteReason")
    public WebElementFacade completionReasonTextField;

    @FindBy(xpath = "//label[text()='Yes']")
    public WebElementFacade permanentlyCloseCaseYesRadioButton;

    @FindBy(xpath = "//label[text()='No']")
    public WebElementFacade permanentlyCloseCaseNoRadioButton;

    @FindBy(xpath = "//legend[text()='Date of Acceptance']")
    public WebElementFacade dateOfAcceptanceLabel;

    public void selectAcceptCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint","Can your team respond to this complaint?");
        waitABit(500);
    }

    public void enterDateOfAcceptance() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-5), "Date of Acceptance");
    }

    public void selectTransferComplaint() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer the complaint","Can your team respond to this complaint?");
        clickTheButton("Continue");
    }

    public void enterTransferReason() {
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter reason for transfer");
        setSessionVariable("rejectionReason").to(enteredText);
    }

    public void selectTransferToCCH() {
        recordCaseData.selectSpecificRadioButton("CCH");
        clickTheButton("Continue");
    }

    public void enterDetailsOnTriageCaptureReasonPage() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Area");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Reason");
        selectIfLOARequired("Yes");
    }

    public void selectIfLOARequired(String yesNo) {
        recordCaseData.selectSpecificRadioButton(yesNo);
    }

    public void selectLOAReceived() {
        recordCaseData.checkSpecificCheckbox("Has Letter of Authority been received?");
        setSessionVariable("loaReceived").to("Yes");
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of Letter of Authority");
        setSessionVariable("loaReceivedDate").to(getTodaysDate());
    }

    public void selectReadyForDrafting() {
        recordCaseData.selectSpecificRadioButton("All information collected - case ready for drafting");
        clickTheButton("Continue");
    }

    public void escalateCaseToWFM() {
        recordCaseData.selectSpecificRadioButton("Escalate case to WFM");
        clickTheButton("Continue");
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter reason for escalation");
        setSessionVariable("escalationReason").to(enteredText);
        clickTheButton("Escalate case");
    }

    public void selectCompleteTheCase() {
        recordCaseData.selectSpecificRadioButton("No response - complete the case (close permanently)");
        clickTheButton("Continue");
    }

    public void enterCompletionReason() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter note for case completion");
        setSessionVariable("closureReason").to(enteredText);
    }

    public void selectPermanentlyCloseCase(String yesNo) {
        selectSpecificRadioButton(yesNo);
        clickTheButton("Confirm");
    }

    public void openExGratiaAccordion() {
        openOrCloseAccordionSection("Ex-Gratia");
    }

    public void assertOverdueContributionRequestIsHighlighted() {
        WebElement label = find(By.xpath("//label[text()='Overdue " + sessionVariableCalled("contributionDueDate") + "']"));
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(212, 53, 28, 1)"), is(true));
    }
}