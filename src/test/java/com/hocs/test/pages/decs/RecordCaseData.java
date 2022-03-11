package com.hocs.test.pages.decs;

import java.util.HashMap;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;

public class RecordCaseData extends BasePage{

    static HashMap<String, String> dataRecords = new HashMap<>();

    CaseView caseView;

    public static void checkIfDataRecordsShouldBeWiped() {
        if (!keepAllCaseData) {
            dataRecords = new HashMap<>();
        }
    }

    //Radio buttons

    public String selectRandomRadioButtonFromGroupWithHeading(String headingText) {
        String radioButtonText = super.selectRandomRadioButtonFromGroupWithHeading(headingText);
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
        addHeadingAndValueRecord(headingText, radioButtonText);
    }

    public void enterDateIntoDateFieldsWithHeading(String date, String headingText) {
        super.enterDateIntoDateFieldsWithHeading(date, headingText);
        addHeadingAndValueRecord(headingText, date);
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
        addValueRecord(checkboxLabelText);
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

}
