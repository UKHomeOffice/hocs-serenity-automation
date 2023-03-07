package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    Dashboard dashboard;

    CaseView caseView;

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    @FindBy(xpath = "//label[contains(text(),'Yes - accept the complaint')]")
    public WebElementFacade acceptTheComplaintRadioButton;

    @FindBy(xpath = "//label[contains(text(),'No - transfer the complaint')]")
    public WebElementFacade transferTheComplaintRadioButton;

    @FindBy(xpath = "//textarea[@name='CaseNote_TriageTransfer']")
    public WebElementFacade transferReasonTextArea;

    @FindBy(xpath = "//label[contains(text(),'CCH')]")
    public WebElementFacade transferToCCHRadioButton;

    @FindBy(id = "BusArea")
    public WebElementFacade businessAreaDropdown;

    @FindBy(id = "EnqReason")
    public WebElementFacade enquiryReasonDropdown;

    @FindBy(xpath = "//label[contains(text(),'Yes')]")
    public WebElementFacade loaRequiredYesRadioButton;

    @FindBy(xpath = "//label[text()='Escalate case to WFM']")
    public WebElementFacade escalateToWFMRadioButton;

    @FindBy(xpath = "//label[text()='No response - complete the case (close permanently)']")
    public WebElementFacade noResponseCloseCaseRadioButton;

    @FindBy(id = "TotalOfferSentToComplainant")
    public WebElementFacade totalOfferSentToComplainantField;

    public void selectAcceptCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint", "Can your team respond to this complaint?");
        waitABit(500);
    }

    public void enterDateOfAcceptance() {
        if(ukviPsuOffTag){
            recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-5), "Date of Acceptance");
        }else{
            recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-5), "Date of acceptance");
        }
    }

    public void selectEnquiryReason(String enquiryReason) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(enquiryReason, "Enquiry Reason");
    }

    public void selectTransferOfflineAndCloseTheCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer offline and close the case",
                "Can your team respond to this complaint?");
        clickContinueButton();
    }

    public void selectTransferComplaint() {
        if(ukviPsuOffTag) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer the complaint", "Can your team respond to this complaint?");
        }else {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer the complaint to CCH", "Can your team respond to this "
                    + "complaint?");
        }
        clickContinueButton();
    }

    public void enterTransferReason() {
        String enteredText;
        if (pogrCase() || pogr2Case()) {
            if (sessionVariableCalled("businessArea").equals("GRO")) {
                waitForDECSPageWithTitle("Investigation - Transfer Case");
            } else {
                waitABit(500);
            }
            enteredText = enterTextIntoTextAreaWithHeading("Enter the reason for transfer");
        } else {
            enteredText = enterTextIntoTextAreaWithHeading("Enter reason for transfer");
        }

        if (bfCase() | bf2Case()) {
            setSessionVariable("transferReason").to(enteredText);
        } else {
            setSessionVariable("rejectionReason").to(enteredText);
        }
    }

    public void selectTransferToCCH() {
        recordCaseData.selectSpecificRadioButton("CCH");
        clickContinueButton();
    }

    public void selectTransferToPSU() {
        recordCaseData.selectSpecificRadioButton("PSU");
        clickContinueButton();
    }

    public void selectTransferToIEDET() {
        recordCaseData.selectSpecificRadioButton("IE Detention");
        clickContinueButton();
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
        setSessionVariable("complaintCategory").to("Serious and Minor");
    }

    public void selectIEDETClaimCategory(String category) {
        setSessionVariable("complaintCategory").to(category);
        List<WebElementFacade> claimCategories = findAll("//span[text()='" + category + "']/parent::legend/following-sibling::div//label");
        String selectedClaimCategory = checkRandomCheckboxFromList(claimCategories);
        setSessionVariable("claimCategory").to(selectedClaimCategory);
    }

    public void selectUKVIClaimCategory(String category) {
        setSessionVariable("complaintCategory").to(category);
        List<WebElementFacade> claimCategories = findAll("//span[text()='" + category + "']/parent::legend/following-sibling::div//label");
        if(claimCategories.isEmpty()){
            waitABit(4000);
        }
        String selectedClaimCategory = checkRandomCheckboxFromList(claimCategories);
        setSessionVariable("claimCategory").to(selectedClaimCategory);
    }

    public void selectBFClaimCategory(String category) {
        setSessionVariable("complaintCategory").to(category);
        List<WebElementFacade> claimCategories = findAll("//span[text()='" + category + "']/parent::legend/following-sibling::div//label");
        if(claimCategories.isEmpty()){
            waitABit(4000);
        }
        String selectedClaimCategory = checkRandomCheckboxFromList(claimCategories);
        setSessionVariable("claimCategory").to(selectedClaimCategory);
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//input[not(@checked)]/following-sibling::label[contains(@for,'Cat')]");
        if(claimCategories.isEmpty()) {
            waitABit(5000);
        }
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
        if(bfPsuOffTag){
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Area");
        } else if (!bfPsuOffTag) {
            recordCaseData.selectRandomOptionFromDropdownWithHeading("Business area");
        }

        selectBFReasonsForComplaint();
        selectIsLoARequired();
        selectComplainantHasRequestedPayment("No");
        selectAreWeIssuingOfferForConsolatoryPayment("No");
        selectAreWeIssuingOfferForExGratiaPayment("No");
    }

    private void selectBFReasonsForComplaint() {
        for (int i = 1; i <= 5; i++) {
            if (bfPsuOffTag) {
                String selectedReasonForComplaint = recordCaseData.selectRandomOptionFromDropdownWithHeading("Reason for Complaint " + i);
                if (selectedReasonForComplaint.equals("Other")) {
                    recordCaseData.enterTextIntoTextAreaWithHeading("Other - Details (Complaint Reason " + i + ")");
                }
                setSessionVariable("reasonForComplaint" + i).to(selectedReasonForComplaint);
            } else if(!bfPsuOffTag) {
                String selectedReasonForComplaint = recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint reason " + i);

                setSessionVariable("reasonForComplaint" + i).to(selectedReasonForComplaint);
            }
        }
    }

    public void enterDetailsOnTriageCaptureReasonPage() {
        String complaintType = sessionVariableCalled("complaintType");
        if(ukviPsuOffTag){
            if ((compCase() || comp2Case() || comp2DirectCase()) && (complaintType.equals("Service") || complaintType.equals("Minor Misconduct"))) {
                recordCaseData.selectRandomOptionFromDropdownWithHeading("Directorate");
            }
        } else if ((compCase() || comp2Case() || comp2DirectCase()) && (complaintType.equals("Service") || complaintType.equals("Minor misconduct"))) {
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
        setSessionVariable("isLoARequired").to(selectedOption);
        if ((bfCase() || bf2Case()) && selectedOption.equalsIgnoreCase("YES")) {
            enterLoAReceivedDetails();
        }
    }

    public void selectSpecificOptionForIsLOARequired(String yesNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesNo, "Is a Letter of Authority required?");
    }

    public void enterLoAReceivedDetails() {
        if (pogrCase() || pogr2Case()) {
            checkSpecificCheckbox("Yes");
            recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Letter of Authority Date Received");
        } else {
            recordCaseData.checkSpecificCheckbox("Has Letter of Authority been received?");
            recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of Letter of Authority");
        }
        setSessionVariable("loaReceived").to("Yes");
        setSessionVariable("loaReceivedDate").to(getTodaysDate());
    }

    public void selectReadyForDrafting() {
        recordCaseData.selectSpecificRadioButton("All information collected - case ready for drafting");
        if (bfCase() || bf2Case()) {
            clickFinishButton();
        } else {
            clickContinueButton();
        }
    }

    public void escalateCaseToWFM() {
        recordCaseData.selectSpecificRadioButton("Escalate case to WFM");
        if (bfCase() || bf2Case() || pogrCase() || pogr2Case()) {
            clickFinishButton();
        } else {
            clickContinueButton();
        }
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter reason for escalation");
        setSessionVariable("escalationReason").to(enteredText);
        if (pogrCase() || pogr2Case()) {
            clickContinueButton();
        } else {
            clickTheButton("Escalate case");
        }
    }

    public void selectCompleteTheCase() {
        recordCaseData.selectSpecificRadioButton("No response - complete the case (close permanently)");
    }

    public void enterCompletionReason() {
        String enteredText;
        if (pogrCase() || pogr2Case()) {
            enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Enter a note for case closure");

        } else {
            enteredText = enterTextIntoTextAreaWithHeading("Enter note for case completion");

        }
        setSessionVariable("closureReason").to(enteredText);
    }

    public void selectPermanentlyCloseCase(String yesNo) {
        selectSpecificRadioButton(yesNo);
        clickConfirmButton();
    }

    public void selectThirdPartySupplier() {
        recordCaseData.selectSpecificRadioButton("Third party supplier");
    }

    public void selecIEDetentionComplianceTeam() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("IE Detention compliance team","Who will investigate the complaint?");
    }

    public void selectIEDETBusinessArea() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business area");
    }

    public void selectASpecificIEDETBusinessArea(String optionText) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(optionText,"Business area");
    }

    public void selectDEPMU() {
        recordCaseData.selectSpecificRadioButton("DEPMU");
    }

    public void selectSendToCCH() {
        recordCaseData.selectSpecificRadioButton("Send to CCH");
    }

    public void openExGratiaAccordion() {
        openOrCloseAccordionSection("Ex-Gratia");
        setSessionVariable("complaintCategory").to("Ex-Gratia");
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
        clickContinueButton();
    }

    public void selectAClosureReason() {
        String dropdownClosureReason = recordCaseData.selectRandomOptionFromDropdownWithHeading("Closure Reason");
        setSessionVariable("dropdownClosureReason").to(dropdownClosureReason);
    }

    public void acceptCaseAtInvestigation() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint", "Can your team respond to this complaint?");
        clickContinueButton();
    }

    public void rejectCaseAtInvestigation() {
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("HMPO")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - close and transfer to external team", "Can your team respond to this complaint?");
        } else if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer the case", "Can your team respond to this complaint?");
            clickContinueButton();
        }
    }

    public void selectInternalOrExternalTransfer(String transferAction) {
        if (transferAction.equalsIgnoreCase("INTERNAL")) {
            selectSpecificRadioButtonFromGroupWithHeading("Internal", "Internal or external transfer");
        } else if (transferAction.equalsIgnoreCase("EXTERNAL")) {
            selectSpecificRadioButtonFromGroupWithHeading("External - close the case", "Internal or external transfer");
            clickContinueButton();
        }
    }

    public void selectInvestigatingTeam() {
        String investigatingTeam = selectRandomOptionFromDropdownWithHeading("Investigating Team");
        setSessionVariable("investigatingTeam").to(investigatingTeam);
    }

    public void selectAllInformationCollectedRespondAction() {
        selectSpecificRadioButtonFromGroupWithHeading("All information collected - respond", "Actions");
    }

    public void selectCloseCaseAction() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Close the case", "Actions");
    }

    public void enterReasonForTransfer() {
        String enteredReason = recordCaseData.enterTextIntoTextAreaWithHeading("Reason for transfer");
        setSessionVariable("transferReason").to(enteredReason);
    }

    public void selectPSUComplaintOutcome(String psuComplaintOutcome) {
        dashboard.getCurrentCase();
        caseView.clickAllocateToMeLink();
        recordCaseData.selectSpecificRadioButton(psuComplaintOutcome);
        if (psuComplaintOutcome.equalsIgnoreCase("Withdrawn")) {
            clickTheButton("Submit");
            assertExpectedErrorMessageIsDisplayed("Why has the complaint been withdrawn? is required");
            String psuComplaintOutcomeWithdrawnReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why has the complaint been withdrawn?");
            setSessionVariable("psuComplaintOutcomeWithdrawnReason").to(psuComplaintOutcomeWithdrawnReason);
            clickTheButton("Submit");
        } else if (psuComplaintOutcome.equalsIgnoreCase(" IE Detention") ||
                psuComplaintOutcome.equalsIgnoreCase("Substantiated") ||
                psuComplaintOutcome.equalsIgnoreCase("Partially substantiated") ||
                psuComplaintOutcome.equalsIgnoreCase("Unsubstantiated") ||
                psuComplaintOutcome.equalsIgnoreCase("No - send back to UKVI") ||
                psuComplaintOutcome.equalsIgnoreCase("No - send back to Border Force") ||
                psuComplaintOutcome.equalsIgnoreCase("Not serious - send back to IE Detention")) {
                        clickTheButton("Submit");
        } else if (psuComplaintOutcome.equalsIgnoreCase("Not serious - send back to UKVI")) {
            recordCaseData.selectSpecificRadioButton(psuComplaintOutcome);
            setSessionVariable("psuComplaintOutcomeReason").to(psuComplaintOutcome);
            clickTheButton("Submit");
        }

    }

    public void checkPSUComplaintCategories(){

        complaintsRegistrationAndDataInput.selectYesForSeriousCase();
        clickTheButton("Submit");
        waitABit(1000);
        String claimCategory = sessionVariableCalled("claimCategory");
        checkSpecificCheckbox(claimCategory);
        clickTheButton("Finish");
        assertExpectedErrorMessageIsDisplayed("Select at least one complaint category option");
        List<WebElementFacade> claimCategories = findAll("//span[text()='Serious misconduct']/parent::legend/following-sibling::div[2]//label");
        recordCaseData.checkRandomCheckboxFromList(claimCategories);
        clickTheButton("Finish");
    }

    public void selectTransferComplaintToPSU(String transferToPSU, String header) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(transferToPSU, header);
    }

    public void escalateToPSUFromTriage() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Escalate case to PSU", "Action");
       if(compCase() || comp2Case() || comp2DirectCase()){
           clickContinueButton();
       } else if (bfCase() || bf2Case()) {
           clickFinishButton();
       }
        selectAVisibleClaimCategory();
        clickTheButton("Finish and escalate to PSU");

    }
    public void escalateToPSUFromQA() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Escalate case to PSU", "QA result");
        clickContinueButton();
        selectAVisibleClaimCategory();
        clickTheButton("Finish and escalate to PSU");

    }

    public void transferCaseToPSU() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - escalate to PSU", "Can your team respond to this complaint?");
        clickContinueButton();
        waitABit(500);
        selectAVisibleClaimCategory();
        clickTheButton("Finish and escalate to PSU");

    }
    public void escalateToPSUFromEscalated() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Escalate to PSU", "Action");
        String complaintType = sessionVariableCalled("complaintType");
        if(comp2Case() && (complaintType.equalsIgnoreCase("Minor misconduct") || complaintType.equalsIgnoreCase("Service"))){
            clickContinueButton();
        }else{
        clickConfirmButton();
        }
        selectAVisibleClaimCategory();
        clickTheButton("Finish and escalate to PSU");

    }

    public void checkComplaintCategory(String complaintType) {
           selectAVisibleClaimCategory();
           clickTheButton("Finish and escalate to PSU");
    }

    public void selectAComplaintTypeForRecategorisedUKVI() {
        waitABit(3000);
        List<WebElementFacade> claimCategories = findAll("//input/following-sibling::label[contains(@for,'CompType-')]");
        int index =  new Random().nextInt(claimCategories.size());
        String complaintType = String.valueOf(claimCategories.get(index).getText());
        complaintsRegistrationAndDataInput.selectASpecificComplaintType(complaintType);
        if(complaintType.equalsIgnoreCase("Serious misconduct")) {
            checkComplaintCategory(complaintType);
        }

    }

    public void selectAComplaintTypeForRecategorisedBF() {
        waitABit(3000);
        List<WebElementFacade> claimCategories = findAll("//input/following-sibling::label[contains(@for,'CompType-')]");
        int index =  new Random().nextInt(claimCategories.size());
        String complaintType = String.valueOf(claimCategories.get(index).getText());
        complaintsRegistrationAndDataInput.selectASpecificComplaintType(complaintType);
        if(complaintType.equalsIgnoreCase("Serious misconduct")) {
            checkComplaintCategory(complaintType);
        }

    }

}