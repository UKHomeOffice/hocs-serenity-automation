package com.hocs.test.pages.bf;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

public class BFRegistration extends BasePage {

    RecordCaseData recordCaseData;

    public void enterComplainantDOB(String dateOfBirth) {
        enterDateIntoDateFieldsWithHeading(dateOfBirth, "Date of Birth");
    }

    public void selectComplainantGender() {
        selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    public void selectComplainantNationality() {
        selectRandomOptionFromDropdownWithHeading("Nationality");
    }

    public void enterComplainantCompanyName() {
        enterTextIntoTextFieldWithHeading("Company Name");
    }

    public void enterComplainantHOReference(String hoReference) {
        enterSpecificTextIntoTextFieldWithHeading(hoReference, "Home Office Reference");
    }

    public void enterComplainantPortReference() {
        enterTextIntoTextFieldWithHeading("Port Reference");
    }

    public void completeComplainantDetails() {
        enterComplainantDOB("01/01/2000");
        selectComplainantGender();
        selectComplainantNationality();
        enterComplainantCompanyName();
        enterComplainantHOReference(getCurrentMonth() + "/" + getCurrentYear());
        enterComplainantPortReference();
        clickTheButton("Continue");
    }
}
