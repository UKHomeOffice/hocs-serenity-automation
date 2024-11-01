package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Misallocations extends BasePage {

    AccordionMPAM accordionMPAM;

    Correspondents correspondents;

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
        if (stage.equalsIgnoreCase("TRIAGE") || stage.equalsIgnoreCase("DRAFT")) {
            accordionMPAM.openCaseDetailsAccordion();
            safeClickOn(triage.changeBusinessAreaLink);
            waitABit(1000);
        }
        creation.selectASpecificBusinessArea("Transfer to " + transferTo);
        if (transferTo.equalsIgnoreCase("OGD")) {
            reasonForTransferToOGDTextField.sendKeys("Test - Transfer to OGD reason");
            setSessionVariable("inputReasonForTransfer").to("Test - Transfer to OGD reason");
        } else if (transferTo.equalsIgnoreCase("OTHER")) {
            reasonForTransferToOtherTextField.sendKeys("Test - Transfer to Other reason");
            setSessionVariable("inputReasonForTransfer").to("Test - Transfer to Other reason");
        }
        switch (stage.toUpperCase()) {
            case "CREATION":
                creation.selectASpecificRefType("Ministerial");
                creation.selectASpecificMinisterialSignOffTeam("Home Secretary");
                creation.selectASpecificAddressee("Home Secretary");
                creation.selectASpecificInboundChannel("Email");
                clickContinueButton();
                correspondents.addAMemberCorrespondent();
                clickTheButton("Move to Transfer");
                break;
            case "TRIAGE":
            case "DRAFT":
                clickContinueButton();
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }


    public void updateTransferDueDate(String date) {
        typeIntoDateFields(transferDueDateDayField, transferDueDateMonthField, transferDueDateYearField, date);
        setSessionVariable("transferDueDate").to(date);
    }

    public void selectActionAtTransferStage(String action) {
        WebElementFacade radioButton = null;
        switch (action.toUpperCase()) {
            case "SAVE DEADLINE FOR TRANSFER":
                radioButton = saveDeadlineForTransferRadioButton;
                break;
            case "TRANSFER ACCEPTED (CLOSE CASE)":
                radioButton = transferAcceptedCloseCaseRadioButton;
                break;
            case "TRANSFER REJECTED (MOVE TO TRIAGE)":
                radioButton = transferRejectedMoveToTriageRadioButton;
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        radioButton.waitUntilClickable().withTimeoutOf(Duration.ofSeconds(30)).click();
        clickConfirmButton();
    }

    public void completeRequiredFieldsForTriage() {
        waitABit(1000);
        creation.selectASpecificBusinessArea("UKVI");
        creation.selectASpecificRefType("Ministerial");
        creation.selectASpecificMinisterialSignOffTeam("Home Secretary");
        creation.selectASpecificAddressee("Home Secretary");
        creation.selectASpecificUrgency("Standard");
        clickTheButton("Move to Triage");
    }

    public void assertDueDateHasBeenUpdated() {
        String displayedDate = transferDueDateDayField.getValue() + "/" + transferDueDateMonthField.getValue() + "/" + transferDueDateYearField.getValue();
        assertThat(displayedDate.equals(sessionVariableCalled("transferDueDate")), is(true));
    }
}
