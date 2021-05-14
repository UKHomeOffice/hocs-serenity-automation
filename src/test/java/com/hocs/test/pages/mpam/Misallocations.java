package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Misallocations extends BasePage {

    AccordionMPAM accordionMPAM;

    AddCorrespondent addCorrespondent;

    Creation creation;

    Triage triage;

    @FindBy(id = "TransferToOgdText")
    public WebElementFacade reasonForTransferToOGDTextField;

    @FindBy(id = "TransferToOtherText")
    public WebElementFacade reasonForTransferToOtherTextField;

    @FindBy(id = "TransferDueDate-day")
    public WebElementFacade transferDueDateDayField;

    @FindBy(id = "TransferDueDate-month")
    public WebElementFacade transferDueDateMonthField;

    @FindBy(id = "TransferDueDate-year")
    public WebElementFacade transferDueDateYearField;

    @FindBy(xpath = "//label[text()='Save Deadline for Transfer']")
    public WebElementFacade saveDeadlineForTransferRadioButton;

    @FindBy(xpath = "//label[text()='Transfer Accepted (Close case)']")
    public WebElementFacade transferAcceptedCloseCaseRadioButton;

    @FindBy(xpath = "//label[text()='Transfer Rejected (Move to Triage)']")
    public WebElementFacade transferRejectedMoveToTriageRadioButton;

    @FindBy(xpath = "//label[text()='Confirm']")
    public WebElementFacade confirmRadioButton;

    public void transferCaseFromStageTo(String stage, String transferTo) {
        if (stage.toUpperCase().equals("TRIAGE") || stage.toUpperCase().equals("DRAFT")) {
            accordionMPAM.openCaseDetailsAccordion();
            safeClickOn(triage.changeBusinessAreaLink);
            waitABit(500);
        }
        creation.selectBusinessArea("Transfer to " + transferTo);
        if (transferTo.toUpperCase().equals("OGD")) {
            reasonForTransferToOGDTextField.sendKeys("Test - Transfer to OGD reason");
            setSessionVariable("inputReasonForTransfer").to("Test - Transfer to OGD reason");
        } else if (transferTo.toUpperCase().equals("OTHER")) {
            reasonForTransferToOtherTextField.sendKeys("Test - Transfer to Other reason");
            setSessionVariable("inputReasonForTransfer").to("Test - Transfer to Other reason");
        }
        switch (stage.toUpperCase()) {
            case "CREATION":
                creation.selectRefType("Ministerial");
                creation.selectMinisterialSignOffTeam("Home Secretary");
                creation.selectAddressee("Home Secretary");
                creation.selectInboundChannel("Email");
                safeClickOn(continueButton);
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                clickTheButton("Move to Transfer");
                break;
            case "TRIAGE":
            case "DRAFT":
                safeClickOn(continueButton);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }


    public void updateTransferDueDate(String date) {
        typeIntoDateField(transferDueDateDayField, transferDueDateMonthField, transferDueDateYearField, date);
        setSessionVariable("transferDueDate").to(date);
    }

    public void selectActionAtTransferStage(String action) {
        switch (action.toUpperCase()) {
            case "SAVE DEADLINE FOR TRANSFER":
                safeClickOn(saveDeadlineForTransferRadioButton);
                break;
            case "TRANSFER ACCEPTED (CLOSE CASE)":
                safeClickOn(transferAcceptedCloseCaseRadioButton);
                break;
            case "TRANSFER REJECTED (MOVE TO TRIAGE)":
                safeClickOn(transferRejectedMoveToTriageRadioButton);
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(confirmButton);
    }

    public void completeRequiredFieldsForTriage() {
        waitABit(500);
        creation.selectBusinessArea("UKVI");
        creation.selectRefType("Ministerial");
        creation.selectMinisterialSignOffTeam("Home Secretary");
        creation.selectAddressee("Home Secretary");
        creation.selectUrgency("Standard");
        clickTheButton("Move to Triage");
    }

    public void assertDueDateHasBeenUpdated() {
        String displayedDate = transferDueDateDayField.getValue() + "/" + transferDueDateMonthField.getValue() + "/" + transferDueDateYearField.getValue();
        assertThat(displayedDate.equals(sessionVariableCalled("transferDueDate")), is(true));
    }
}
