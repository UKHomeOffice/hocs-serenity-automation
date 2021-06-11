package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ServiceTriage extends BasePage {

    @FindBy(xpath = "//button[text()='Case Details']")
    public WebElementFacade caseDetailsAccordionButton;

    @FindBy(xpath = "//textarea[@name='CaseSummary']")
    public WebElementFacade caseSummaryTextArea;

    @FindBy(css = "label[for='CctTriageAccept-Yes']")
    public WebElementFacade acceptTheComplaintRadioButton;

    @FindBy(css = "label[for='CctTriageAccept-No']")
    public WebElementFacade transferTheComplaintRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_TriageTransfer']")
    public WebElementFacade transferReasonTextArea;

    @FindBy(css = "label[for='CctCompType-CCH']")
    public WebElementFacade transferToCCHRadioButton;

    @FindBy(css = "label[for='CctCompType-Minor']")
    public WebElementFacade transferToMinorMisconductRadioButton;

    @FindBy(id = "BusArea")
    private WebElementFacade businessAreaDropdown;

    @FindBy(id = "EnqReason")
    private WebElementFacade enquiryReasonDropdown;

    @FindBy(css = "label[for='LoaRequired-Yes']")
    public WebElementFacade loaRequiredYesRadioButton;

    @FindBy(css = "label[for='LoaRequired-No']")
    public WebElementFacade loaRequiredNoRadioButton;

    @FindBy(css = "label[for='LoaReceived_Yes']")
    public WebElementFacade loaReceivedCheckbox;

    @FindBy(xpath = "//input[@name='LoaDate-day']")
    public WebElementFacade loadReceivedDayField;

    @FindBy(xpath = "//input[@name='LoaDate-month']")
    public WebElementFacade loadReceivedMonthField;

    @FindBy(xpath = "//input[@name='LoaDate-year']")
    public WebElementFacade loadReceivedYearField;

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

    public void moveCaseFromServiceTriageToServiceDraft() {
        safeClickOn(acceptTheComplaintRadioButton);
        safeClickOn(continueButton);
        waitABit(1000);
        safeClickOn(continueButton);
        businessAreaDropdown.selectByVisibleText("Asylum");
        enquiryReasonDropdown.selectByVisibleText("Accommodation");
        safeClickOn(loaRequiredYesRadioButton);
        safeClickOn(continueButton);
        safeClickOn(readyForDraftingRadioButton);
        safeClickOn(continueButton);
    }

    public void moveCaseFromServiceTriageToServiceEscalated() {
        safeClickOn(acceptTheComplaintRadioButton);
        safeClickOn(continueButton);
        waitABit(1000);
        safeClickOn(continueButton);
        businessAreaDropdown.selectByVisibleText("Asylum");
        enquiryReasonDropdown.selectByVisibleText("Accommodation");
        safeClickOn(loaRequiredYesRadioButton);
        safeClickOn(continueButton);
        safeClickOn(escalateToWFMRadioButton);
        safeClickOn(continueButton);
        reasonForEscalationTextField.sendKeys("Test Escalation Reason");
        safeClickOn(escalateCaseButton);
    }

    public void moveCaseFromServiceTriageToCCH() {
        safeClickOn(transferTheComplaintRadioButton);
        safeClickOn(continueButton);
        transferReasonTextArea.sendKeys("Test Transfer Reason");
        safeClickOn(transferToCCHRadioButton);
        safeClickOn(continueButton);
    }
}
