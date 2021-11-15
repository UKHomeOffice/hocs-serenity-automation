package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.pages.WebElementFacade;

public class FOIDispatch extends BasePage {

    RecordCaseData recordCaseData;

    public void selectDoYouWantToDispatch(String response) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(response, "Are you sure you want to dispatch this case?");
    }

    public void selectASpecificCaseType(String caseType) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading( caseType,"What type of case is this?");
    }

    public void selectACaseType() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("What type of case is this?");
    }

    public void selectASpecificResponseChannel(String responseType) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading( responseType,"How will the response be sent?");
    }

    public void selectAResponseChannel() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading( "How will the response be sent?");
    }

    public void selectOutcomeOfTheCase(String outcome) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading( outcome,"What was the outcome of this case?");
    }

    public void selectRandomExemption() {
        recordCaseData.checkRandomCheckboxUnderHeading("Select Exemptions");
    }

    public void selectRandomException() {
        recordCaseData.checkRandomCheckboxUnderHeading("Select Exceptions");
    }

    public void selectNonDispatchOutcomeOfTheCase() {
        List<String> nonDispatchOutcomeList = new ArrayList<>();
        nonDispatchOutcomeList.add("Request vexatious - section 14 (1)");
        nonDispatchOutcomeList.add("Repeat request section 14 (2)");
        nonDispatchOutcomeList.add("No information found/held");
        nonDispatchOutcomeList.add("Information withheld in full");
        nonDispatchOutcomeList.add("Information released in part");
        nonDispatchOutcomeList.add("Information already in public domain");

        Random rand = new Random();
        String nonDispatchOutcomeItem = nonDispatchOutcomeList.get(rand.nextInt(nonDispatchOutcomeList.size()));
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading( nonDispatchOutcomeItem,"What was the outcome of this case?");
    }

    public void enterFinalResponseDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "What day was the final response sent?");
    }
}
