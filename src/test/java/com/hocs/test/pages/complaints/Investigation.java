package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;


public class Investigation extends BasePage {

    public void acceptCaseAtInvestigation() {
        selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint", "Can your team respond to this complaint?");
    }

    public void rejectCaseAtInvestigation() {
        String rejectionRadioButtonLabelName = null;
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("HMPO")) {
            rejectionRadioButtonLabelName = "No - close and transfer to external team";
        } else if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            rejectionRadioButtonLabelName = "No - transfer the case";
        }
        selectSpecificRadioButtonFromGroupWithHeading(rejectionRadioButtonLabelName, "Can your team respond to this complaint?");
    }

    public void enterTransferReason() {
        if (sessionVariableCalled("businessArea").equals("GRO")) {
            waitForPageWithTitle("Investigation - Transfer Case");
        } else {
            waitABit(500);
        }
        String rejectionReason = enterTextIntoTextAreaWithHeading("Enter the reason for transfer");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void selectInternalOrExternalTransfer(String transferAction) {
        if (transferAction.equalsIgnoreCase("INTERNAL")) {
            selectSpecificRadioButtonFromGroupWithHeading("Internal", "Internal or external transfer");
        } else if (transferAction.equalsIgnoreCase("EXTERNAL")) {
            selectSpecificRadioButtonFromGroupWithHeading("External - close the case", "Internal or external transfer");
        }
    }

    public void selectInvestigatingTeam() {
        String investigatingTeam = selectRandomOptionFromDropdownWithHeading("Investigating Team");
        setSessionVariable("investigatingTeam").to(investigatingTeam);
    }

    public void selectAllInformationCollectedRespondAction() {
        selectSpecificRadioButtonFromGroupWithHeading("All information collected - respond", "Actions");
    }

    public void selectNoResponseCloseCaseAction() {
        selectSpecificRadioButtonFromGroupWithHeading("No response - complete the case", "Actions");
    }
}