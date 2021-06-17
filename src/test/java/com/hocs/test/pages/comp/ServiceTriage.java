package com.hocs.test.pages.comp;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    public void selectAcceptCase() {
        safeClickOn(acceptTheComplaintRadioButton);
        safeClickOn(continueButton);
        waitABit(500);
    }

    public void selectTransferComplaint() {
        safeClickOn(transferTheComplaintRadioButton);
        safeClickOn(continueButton);
    }

    public void enterTransferReason() {
        transferReasonTextArea.sendKeys("Test Transfer Reason");
        setSessionVariable("rejectionReason").to("Test Transfer Reason");
    }

    public void selectTransferToCCH() {
        safeClickOn(transferToCCHRadioButton);
        safeClickOn(continueButton);
    }

    public void enterDetailsOnTriageCaptureReasonPage() {
        businessAreaDropdown.selectByVisibleText("Asylum");
        enquiryReasonDropdown.selectByVisibleText("Accommodation");
        safeClickOn(loaRequiredYesRadioButton);
    }

    public void selectLOAReceived() {
        safeClickOn(loaReceivedCheckbox);
        setSessionVariable("loaReceived").to("Yes");
        typeIntoDateField(loaReceivedDayField, loaReceivedMonthField, loaReceivedYearField, "01/01/2021");
        setSessionVariable("loaReceivedDate").to("01/01/2021");
    }

    public void selectReadyForDrafting() {
        safeClickOn(readyForDraftingRadioButton);
        safeClickOn(continueButton);
    }

    public void escalateCaseToWFM() {
        safeClickOn(escalateToWFMRadioButton);
        safeClickOn(continueButton);
        reasonForEscalationTextField.sendKeys("Test Escalation Reason");
        setSessionVariable("escalationReason").to("Test Escalation Reason");
        safeClickOn(escalateCaseButton);
    }

    public void selectCompleteTheCase() {
        safeClickOn(noResponseCloseCaseRadioButton);
        safeClickOn(continueButton);
    }

    public void enterCompletionReason() {
        completionReasonTextField.sendKeys("Test Completion Reason");
        setSessionVariable("closureReason").to("Test Completion Reason");
    }

    public void selectPermanentlyCloseCase(String yesNo) {
        if (yesNo.equalsIgnoreCase("Yes")) {
            safeClickOn(permanentlyCloseCaseYesRadioButton);
        } else if (yesNo.equalsIgnoreCase("No")) {
            safeClickOn(permanentlyCloseCaseNoRadioButton);
        }
        safeClickOn(confirmButton);
    }

    public void moveCaseFromServiceTriageToServiceDraft() {
        selectAcceptCase();
        safeClickOn(continueButton);
        enterDetailsOnTriageCaptureReasonPage();
        safeClickOn(continueButton);
        selectReadyForDrafting();
    }

    public void moveCaseFromServiceTriageToServiceEscalated() {
        selectAcceptCase();
        safeClickOn(continueButton);
        enterDetailsOnTriageCaptureReasonPage();
        safeClickOn(continueButton);
        escalateCaseToWFM();
    }

    public void moveCaseFromServiceTriageToCCH() {
        selectTransferComplaint();
        enterTransferReason();
        selectTransferToCCH();
    }

    public void assertOverdueContributionRequestIsHighlighted() {
        WebElement label = find(By.xpath("//label[text()='Overdue " + sessionVariableCalled("contributionDueDate") + "']"));
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(212, 53, 28, 1)"), is(true));
    }
}
