package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class RecordCaseData extends BasePage{

    static HashMap<String, String> dataRecords = new HashMap<>();

    static HashMap<String, String> summaryRecords = new HashMap<>();

    CaseView caseView;

    public static void checkIfDataRecordsShouldBeWiped() {
        if (!keepAllCaseData) {
            dataRecords = new HashMap<>();
            summaryRecords = new HashMap<>();
        }
    }

    //Radio buttons

    public String selectRandomRadioButtonFromGroupWithHeading(String headingText) {
        String radioButtonText = super.selectRandomRadioButtonFromGroupWithHeading(headingText);
        if(comp2DirectCase() && headingText.equalsIgnoreCase("External contractor the complaint is about")){
            headingText = "External contractor";
        }
        addHeadingAndValueRecord(headingText, radioButtonText);
        return radioButtonText;
    }

    public String selectSpecificRadioButton(String radioButtonText) {
        String headingText = super.selectSpecificRadioButton(radioButtonText);
        addHeadingAndValueRecord(headingText, radioButtonText);
        return headingText;
    }

    public void selectSpecificRadioButtonFromGroupWithHeading(String radioButtonText, String headingText) {
        super.selectSpecificRadioButtonFromGroupWithHeading(radioButtonText, headingText);
        if(comp2DirectCase()) {
            if(radioButtonText.equalsIgnoreCase("Yes - it’s a complaint about an external contractor")){
                       radioButtonText = "External contractor";
                        headingText = "Complaint origin";
             } else if(radioButtonText.equalsIgnoreCase("No - close the case")){
                    radioButtonText = "Closed";
                headingText = "Complaint origin";
            } else if(radioButtonText.equalsIgnoreCase("Yes - it’s a further stage 2 case")){
                radioButtonText = "Further stage 2 case";
                headingText = "Complaint origin";
            }
        }
        addHeadingAndValueRecord(headingText, radioButtonText);
    }

    public void enterDateIntoDateFieldsWithHeading(String date, String headingText) {
        super.enterDateIntoDateFieldsWithHeading(date, headingText);
        if((compCase() || comp2Case() || comp2DirectCase()) && (headingText.equalsIgnoreCase("Date of acceptance"))){
                headingText = "Date of Acceptance";
        }
        if(headingText.equalsIgnoreCase("Date Letter Sent") && !pogr2Case()){
            addHeadingAndValueRecord(headingText, date);
        }

    }

    //Text fields

    public String enterTextIntoTextFieldWithHeading(String headingText) {
        String textToEnter = super.enterTextIntoTextFieldWithHeading(headingText);
        addHeadingAndValueRecord(headingText, textToEnter);
        return textToEnter;
    }

    public void enterSpecificTextIntoTextFieldWithHeading(String textToEnter, String headingText) {
        super.enterSpecificTextIntoTextFieldWithHeading(textToEnter, headingText);
        addHeadingAndValueRecord(headingText, textToEnter);
   }

    // Text areas

    public String enterTextIntoTextAreaWithHeading(String headingText) {
        String textToEnter = super.enterTextIntoTextAreaWithHeading(headingText);
        addHeadingAndValueRecord(headingText, textToEnter);
        return textToEnter;
    }

    public void enterSpecificTextIntoTextAreaWithHeading(String textToEnter, String headingText) {
        super.enterSpecificTextIntoTextAreaWithHeading(textToEnter, headingText);
        addHeadingAndValueRecord(headingText, textToEnter);
    }

    //Drop downs

    public String selectRandomOptionFromDropdownWithHeading(String headingText) {
        String optionText = super.selectRandomOptionFromDropdownWithHeading(headingText);
        addHeadingAndValueRecord(headingText,optionText);
        return optionText;
    }

    public void selectSpecificOptionFromDropdownWithHeading(String optionText, String headingText) {
        super.selectSpecificOptionFromDropdownWithHeading(optionText, headingText);
        addHeadingAndValueRecord(headingText, optionText);
    }

    //Checkboxes

    public String checkRandomCheckboxFromList(List<WebElementFacade> checkboxes) {
        String checkboxLabelText = super.checkRandomCheckboxFromList(checkboxes);
        if(compCase() || comp2Case() || comp2DirectCase()||bfCase()||bf2Case()){
            String complaintType = sessionVariableCalled("complaintType");
            String complaintCategory = sessionVariableCalled("complaintCategory");
            if(complaintType != null && complaintType.equalsIgnoreCase("Ex-Gratia")){
                complaintCategory = "Ex-gratia";
            }
            addHeadingAndValueRecord( complaintCategory, checkboxLabelText);
        } else {
        addValueRecord(checkboxLabelText);
        }
        return checkboxLabelText;
    }

    public String checkRandomCheckboxUnderHeading(String headingText) {
        String checkboxLabelText = super.checkRandomCheckboxUnderHeading(headingText);
        addValueRecord(checkboxLabelText);
        return checkboxLabelText;
    }

    public void checkSpecificCheckbox(String checkboxLabelText) {
        super.checkSpecificCheckbox(checkboxLabelText);
        addValueRecord(checkboxLabelText);
    }

    // Typeaheads

    public String selectRandomOptionFromTypeaheadWithHeading(String headingText) {
        String optionText = super.selectRandomOptionFromTypeaheadWithHeading(headingText);
        addHeadingAndValueRecord(headingText,optionText);
        return optionText;
    }

    public String selectSpecificOptionFromTypeaheadWithHeading(String optionText, String headingText) {
        String selectedOptionText = super.selectSpecificOptionFromTypeaheadWithHeading(optionText, headingText);
        addHeadingAndValueRecord(headingText, optionText);
        return selectedOptionText;
    }

    // Other methods

    public void addHeadingAndValueRecord(String heading, String value) {
        dataRecords.put(heading, value);
    }

    public void addValueRecord(String value) {
        dataRecords.put(value, "Yes");
    }

    public void assertAllRecordedCaseDataIsCurrentlyVisibleInTheReadOnlyAccordion() {
        for(HashMap.Entry<String, String> entry : dataRecords.entrySet()) {
            String accordionKey = entry.getKey();
            String expectedAccordionValue = entry.getValue();
            caseView.assertExpectedValueIsVisibleInOpenCaseDetailsAccordionForGivenKey(expectedAccordionValue, accordionKey);
        }
    }

    public void assertComplaintCategoryAndComplaintRecordInTheReadOnlyAccordion() {
        String complaintCategoryText = sessionVariableCalled("complaintCategory");
        String complaintReasonText = sessionVariableCalled("complaintReason");
        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Complaint Category").get(0).equalsIgnoreCase(complaintCategoryText),is(true));
        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Complaint Reason").get(0).equalsIgnoreCase(complaintReasonText),
                is(true));
    }

    public void assertComplaintTypeInTheReadOnlyAccordion(String complaintType) {
        assertThat(caseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenKey("Complaint type").get(0).equalsIgnoreCase(complaintType),
                is(true));
    }

    public void assertAllRecordedCaseDataIsVisibleInTheSummaryTab() {
        for(HashMap.Entry<String, String> entry : summaryRecords.entrySet()) {
            String summaryKey = entry.getKey();
            String expectedSummaryValue = entry.getValue();
            System.out.println("Checking "+summaryKey +" against "+expectedSummaryValue);
            caseView.assertExpectedValueIsVisibleInSummaryForGivenKey(expectedSummaryValue, summaryKey);
        }
    }

    public void addValueToAssertSummary(String headingText, String optionValue) {
        summaryRecords.put(headingText, optionValue);
    }
}
