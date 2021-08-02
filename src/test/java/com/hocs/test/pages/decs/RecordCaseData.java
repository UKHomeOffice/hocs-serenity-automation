package com.hocs.test.pages.decs;

import java.util.HashMap;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;

public class RecordCaseData extends BasePage{

    static HashMap<String, String> dataRecords = new HashMap<>();

    UnallocatedCaseView unallocatedCaseView;

    public static void resetDataRecords() {
        dataRecords = new HashMap<>();
    }

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
        return textToEnter;
    }

    public void enterSpecificTextIntoTextFieldWithHeading(String textToEnter, String headingText) {
        super.enterSpecificTextIntoTextFieldWithHeading(textToEnter, headingText);
        addHeadingAndValueRecord(headingText, textToEnter);
    }

    // Text areas

    public String enterTextIntoTextAreaWithHeading(String headingText) {
        String textToEnter = super.enterTextIntoTextAreaWithHeading(headingText);
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

    public void checkSpecificCheckbox(String checkboxLabelText) {
        super.checkSpecificCheckbox(checkboxLabelText);
        addValueRecord(checkboxLabelText);
    }

    public void addHeadingAndValueRecord(String heading, String value) {
        dataRecords.put(heading, value);
    }

    public void addValueRecord(String value) {
        dataRecords.put(value, "Yes");
    }

    public void assertAllRecordedCaseDataIsDisplayedInTheReadOnlyAccordionSection() {
        for(HashMap.Entry<String, String> entry : dataRecords.entrySet()) {
            List<String> visibleDisplayValues = unallocatedCaseView.getValuesFromOpenCaseDetailsAccordionSectionForGivenHeading(entry.getKey());
            String recordedValue = entry.getValue();
            String expectedDisplayValue = recordedValue.replace("\n", " ");
            boolean expectedValueIsDisplayed = false;
            for (String visibleDisplayValue : visibleDisplayValues) {
                if (visibleDisplayValue.contains(expectedDisplayValue)) {
                    expectedValueIsDisplayed = true;
                    break;
                }
            }
            if (!expectedValueIsDisplayed) {
                System.out.println("'" + entry.getKey() + ": " + expectedDisplayValue + "' is not visible in accordion");
            }
            assert(expectedValueIsDisplayed);
        }
    }

}
