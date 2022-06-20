package com.hocs.test.pages.complaints;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class DataInput extends BasePage {

    RecordCaseData recordCaseData;

    public void selectSpecificBusinessArea(String businessArea) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(businessArea, "Business Area");
        setSessionVariable("businessArea").to(businessArea);
    }

    public void selectBusinessArea() {
        String businessArea = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
        setSessionVariable("businessArea").to(businessArea);
    }

    public void enterComplainantDOB() {
        recordCaseData.enterDateIntoDateFieldsWithHeading("01/01/2001", "Date of Birth");
    }

    public void selectComplainantGender() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    public void selectNationComplaintWasMadeFrom() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Nation from which complaint is being made");
    }

    public void enterComplainantCompanyName() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Company Name");
    }

    public void enterApplicationReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Application Reference");
    }

    public void enterPassportNumber() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Passport Number");
    }

    public void enterAccountNumber() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Case/Account Number");
    }

    public void selectCategory() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Category");
    }

    public void selectNRO() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("NRO");
    }

    public void selectLocation() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Location");
    }

    public void enterDescriptionOfComplaint() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Description of Complaint");
    }

    public void selectComplaintChannel() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Complaint Channel");
    }

    public void selectASpecificComplaintChannel(String channel) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(channel,"Complaint Channel");
    }

    public void checkPriorityCheckbox() {
        checkSpecificCheckbox("Yes");
    }

    public void enterThirdPartyReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Third Party Reference");
    }

    public void selectIsLoARequired() {
        String selectedOption = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Is a Letter of Authority required?");
        setSessionVariable("isLoARequired").to(selectedOption);
    }

    public void completeDataInputScreen() {
        enterComplainantDOB();
        selectComplainantGender();
        selectNationComplaintWasMadeFrom();
        enterComplainantCompanyName();
        if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("HMPO")) {
            enterApplicationReference();
            enterPassportNumber();
        } else if (sessionVariableCalled("businessArea").toString().equalsIgnoreCase("GRO")) {
            enterAccountNumber();
        }
        selectCategory();
        selectNRO();
        selectLocation();
        enterDescriptionOfComplaint();
        selectComplaintChannel();
        checkPriorityCheckbox();
        enterThirdPartyReference();
        selectIsLoARequired();
    }

    public void enterDateLetterSent() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date Letter Sent");
    }

    public void selectInvestigatingTeam() {
        String investigatingTeam = selectRandomOptionFromDropdownWithHeading("Investigating Team");
        setSessionVariable("investigatingTeam").to(investigatingTeam);
    }
}
