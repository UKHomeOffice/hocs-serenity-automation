package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;

public class Investigation extends BasePage {

    public void acceptCaseAtInvestigation() {
        selectSpecificRadioButtonFromGroupWithHeading("Yes - accept the complaint", "Can your team respond to this complaint?");
    }

    public void rejectCaseAtInvestigation() {
        selectSpecificRadioButtonFromGroupWithHeading("No - close and transfer to external team", "Can your team respond to this complaint?");
    }

    public void selectAllInformationCollectedRespondAction() {
        selectSpecificRadioButtonFromGroupWithHeading("All information collected - respond", "Actions");
    }

    public void selectNoResponseCloseCaseAction() {
        selectSpecificRadioButtonFromGroupWithHeading("No response - complete the case", "Actions");
    }
}