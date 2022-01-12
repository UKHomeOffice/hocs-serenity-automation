package com.hocs.test.pages.bf;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BFTriage extends BasePage {

    RecordCaseData recordCaseData;

/*

    public void selectAcceptCase() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint", "Can your team respond to this complaint?");
        waitABit(500);
    }

    public void enterDateOfAcceptance() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getDatePlusMinusNDaysAgo(-5), "Date of Acceptance");
    }

    public void selectTransferComplaint() {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("No - transfer the complaint", "Can your team respond to this complaint?");
        clickTheButton("Continue");
    }

    public void enterTransferReason() {
        String enteredText = enterTextIntoTextAreaWithHeading("Enter reason for transfer");
        setSessionVariable("rejectionReason").to(enteredText);
    }

    public void selectTransferToCCH() {
        recordCaseData.selectSpecificRadioButton("CCH");
        clickTheButton("Continue");
    }

    public void selectTransferToIEDET() {
        recordCaseData.selectSpecificRadioButton("IE Detention");
        clickTheButton("Continue");
    }
*/

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious and Minor");
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//input[not(@checked)]/following-sibling::label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory: claimCategories) {
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

    public void enterDetailsOnTriageCaptureReasonPage() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Region");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business Area");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Reason 1");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Reason 2");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry Reason 3");
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