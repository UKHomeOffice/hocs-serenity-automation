package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ComplaintsTriageAndInvestigation extends BasePage {

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

    @FindBy(id = "TotalOfferSentToComplainant")
    public WebElementFacade totalOfferSentToComplainantField;

    public void selectAcceptCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint", "Can your team respond to this complaint?");
        waitABit(500);
    }

    public void enterDateOfAcceptance() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-5), "Date of Acceptance");
    }

    public void selectEnquiryReason(String enquiryReason) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(enquiryReason, "Enquiry Reason");
    }

    public void selectTransferOfflineAndCloseTheCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer offline and close the case",
                "Can your team respond to this complaint?");
        clickTheButton("Continue");
    }

    public void selectTransferComplaint() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer the complaint", "Can your team respond to this complaint?");
        clickTheButton("Continue");
    }

    public void enterTransferReason() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter reason for transfer");
        if (bfCase() | bf2Case()) {
            setSessionVariable("transferReason").to(enteredText);
        } else {
            setSessionVariable("rejectionReason").to(enteredText);
        }
    }

    public void selectTransferToCCH() {
        recordCaseData.selectSpecificRadioButton("CCH");
        clickTheButton("Continue");
    }

    public void selectTransferToPSU() {
        recordCaseData.selectSpecificRadioButton("PSU");
        clickTheButton("Continue");
    }

    public void selectTransferToIEDET() {
        recordCaseData.selectSpecificRadioButton("IE Detention");
        clickTheButton("Continue");
    }

    public void selectComplainantHasRequestedPayment(String yesNo) {
        if (yesNo.equalsIgnoreCase("YES")) {
            selectSpecificRadioButtonFromGroupWithHeading("Yes", "Has the complainant requested a payment?");
        } else if (yesNo.equalsIgnoreCase("NO")) {
            selectSpecificRadioButtonFromGroupWithHeading("No", "Has the complainant requested a payment?");
        }
    }

    public void enterAmountRequestedByComplainant(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Amount requested by complainant");
    }

    public void selectAreWeIssuingOfferForConsolatoryPayment(String yesNo) {
        if (yesNo.equalsIgnoreCase("YES")) {
            selectSpecificRadioButtonFromGroupWithHeading("Yes", "Are we issuing an offer for a Consolatory payment?");
        } else if (yesNo.equalsIgnoreCase("NO")) {
            selectSpecificRadioButtonFromGroupWithHeading("No", "Are we issuing an offer for a Consolatory payment?");
        }
    }

    public void enterConsolatoryPaymentOffer(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Consolatory payment offer sent to the complainant");
        setSessionVariable("consolatoryOfferAmount").to(amount);
    }

    public void selectAreWeIssuingOfferForExGratiaPayment(String yesNo) {
        if (yesNo.equalsIgnoreCase("YES")) {
            selectSpecificRadioButtonFromGroupWithHeading("Yes", "Are we issuing an offer for an Ex-Gratia payment?");
        } else if (yesNo.equalsIgnoreCase("NO")) {
            selectSpecificRadioButtonFromGroupWithHeading("No", "Are we issuing an offer for an Ex-Gratia payment?");
        }
    }

    public void enterExGratiaPaymentOffer(String amount) {
        enterSpecificTextIntoTextFieldWithHeading(amount, "Ex-Gratia payment offer sent to the complainant");
        setSessionVariable("exGratiaOfferAmount").to(amount);
    }

    public void assertTotalPaymentOfferIsCorrect() {
        double consolatoryOffer = Double.parseDouble(sessionVariableCalled("consolatoryOfferAmount").toString());
        double exGratiaOffer = Double.parseDouble(sessionVariableCalled("exGratiaOfferAmount").toString());
        double totalOffer = consolatoryOffer + exGratiaOffer;
        waitABit(1000);
        String totalOfferFieldOffer = totalOfferSentToComplainantField.getValue();
        double displayedTotalOffer = Double.parseDouble(totalOfferFieldOffer);
        assertThat(totalOffer == displayedTotalOffer, is(true));
    }

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious and Minor");
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//input[not(@checked)]/following-sibling::label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory : claimCategories) {
            if (claimCategory.isCurrentlyVisible()) {
                visibleClaimCategories.add(claimCategory);
            }
        }
        recordCaseData.checkRandomCheckboxFromList(visibleClaimCategories);
    }

    public void enterDetailsOnComplaintCategoryPage() {
        String complaintType = sessionVariableCalled("complaintType");
        if (complaintType.equalsIgnoreCase("MINOR MISCONDUCT")) {
            selectOwningCSU();
            openTheSeriousAndMinorComplaintCategoryAccordion();
            selectAVisibleClaimCategory();
        }
        if (complaintType.equalsIgnoreCase("EX-GRATIA")) {
            openExGratiaAccordion();
            selectAVisibleClaimCategory();
        }
    }

    public void enterDetailsOnBFTriageDetailsPage() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Region");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Area");
        selectBFReasonsForComplaint();
        selectIsLoARequired();
        selectComplainantHasRequestedPayment("No");
        selectAreWeIssuingOfferForConsolatoryPayment("No");
        selectAreWeIssuingOfferForExGratiaPayment("No");
    }

    private void selectBFReasonsForComplaint() {
        for (int i = 1; i <= 5; i++) {
            String selectedReasonForComplaint = recordCaseData.selectRandomOptionFromDropdownWithHeading("Reason for Complaint " + i);
            if (selectedReasonForComplaint.equals("Other")) {
                recordCaseData.enterTextIntoTextAreaWithHeading("Other - Details (Complaint Reason " + i + ")");
            }
            setSessionVariable("reasonForComplaint" + i).to(selectedReasonForComplaint);
        }
    }

    public void enterDetailsOnTriageCaptureReasonPage() {
        String complaintType = sessionVariableCalled("complaintType");
        if ((compCase() || comp2Case()) && (complaintType.equals("Service") || complaintType.equals("Minor Misconduct"))) {
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Directorate");
        }
        String businessArea = recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Area");
        if (iedetCase() && businessArea.equalsIgnoreCase("OTHER")) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Other Business Area");
        }
        if (!iedetCase()) {
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Reason");
        }
        selectIsLoARequired();
    }

    public void selectIsLoARequired() {
        String selectedOption = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Is a Letter of Authority required?");
        if ((bfCase() || bf2Case()) && selectedOption.equalsIgnoreCase("YES")) {
            enterLoAReceivedDetails();
        }
        setSessionVariable("isLoARequired").to(selectedOption);
    }

    public void selectSpecificOptionForIsLOARequired(String yesNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesNo, "Is a Letter of Authority required?");
    }

    public void enterLoAReceivedDetails() {
        recordCaseData.checkSpecificCheckbox("Has Letter of Authority been received?");
        setSessionVariable("loaReceived").to("Yes");
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of Letter of Authority");
        setSessionVariable("loaReceivedDate").to(getTodaysDate());
    }

    public void selectReadyForDrafting() {
        recordCaseData.selectSpecificRadioButton("All information collected - case ready for drafting");
        if (bfCase() || bf2Case()) {
            clickTheButton("Finish");
        } else {
            clickTheButton("Continue");
        }
    }

    public void escalateCaseToWFM() {
        recordCaseData.selectSpecificRadioButton("Escalate case to WFM");
        if (bfCase() || bf2Case()) {
            clickTheButton("Finish");
        } else {
            clickTheButton("Continue");
        }
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter reason for escalation");
        setSessionVariable("escalationReason").to(enteredText);
        clickTheButton("Escalate case");
    }

    public void selectCompleteTheCase() {
        recordCaseData.selectSpecificRadioButton("No response - complete the case (close permanently)");
    }

    public void enterCompletionReason() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter note for case completion");
        setSessionVariable("closureReason").to(enteredText);
    }

    public void selectPermanentlyCloseCase(String yesNo) {
        selectSpecificRadioButton(yesNo);
        clickTheButton("Confirm");
    }

    public void selectTransferredToThirdPartySupplier() {
        recordCaseData.selectSpecificRadioButton("Yes - transferred to third party supplier");
    }

    public void selectTransferredToIEDetentionComplianceTeam() {
        recordCaseData.selectSpecificRadioButton("Yes - transferred to IE Detention Compliance Team");
    }

    public void selectNoFurtherConsideration() {
        recordCaseData.selectSpecificRadioButton("No - no further consideration");
        recordCaseData.enterTextIntoTextAreaWithHeading("Please provide further details");
    }

    public void selectTransferComplaintToCCH() {
        recordCaseData.selectSpecificRadioButton("No - transfer the complaint to CCH");
    }

    public void openExGratiaAccordion() {
        openOrCloseAccordionSection("Ex-Gratia");
    }

    public void assertOverdueContributionRequestIsHighlighted() {
        WebElement label = find(By.xpath("//label[text()='Overdue " + sessionVariableCalled("contributionDueDate") + "']"));
        String value = label.getCssValue("background-color");
        assertThat(value.equalsIgnoreCase("rgba(212, 53, 28, 1)"), is(true));
    }

    public void enterPSUReference() {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading("1234", "PSU Reference");
    }

    public void enterSpecificPSUReference(String reference) {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(reference, "PSU Reference");
    }

    public void selectAdditionalInformation(String additionalInformation) {
        recordCaseData.checkSpecificCheckbox(additionalInformation);
    }

    public void selectOwningCSU() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Owning CSU");
    }

    public void selectACloseReason() {
        String closeReason = recordCaseData.selectRandomOptionFromDropdownWithHeading("Close Reason");
        if (closeReason.equals("Other")) {
            recordCaseData.enterTextIntoTextAreaWithHeading("Reason for closing");
        }
        clickTheButton("Continue");
    }
}