package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Misallocations extends BasePage {

    Creation creation;

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
