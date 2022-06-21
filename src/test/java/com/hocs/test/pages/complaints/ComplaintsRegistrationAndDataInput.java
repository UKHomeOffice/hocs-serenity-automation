package com.hocs.test.pages.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ComplaintsRegistrationAndDataInput extends BasePage {

    RecordCaseData recordCaseData;

    public void enterComplainantDOB(String complainantDOB) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(complainantDOB, "Date of Birth");
    }

    public void selectAGender() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    public void selectANationality() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Nationality");
    }

    public void enterACompanyName() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Company Name");
    }

    public void enterAHomeOfficeReference(String HOReference) {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(HOReference, "Home Office Reference");
    }

    public void enterAPortReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Port Reference");
    }

    public void enterComplainantDetails() {
        enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        selectAGender();
        selectANationality();
        enterACompanyName();
        enterAHomeOfficeReference(getCurrentMonth() +"/" + getCurrentYear());
        enterAPortReference();
        clickTheButton("Continue");
    }

    public void selectASpecificComplaintType(String complaintType) {
        switch (complaintType.toUpperCase()) {
            case "SERVICE":
                recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Service", "Complaint Type");
                setSessionVariable("complaintType").to("Service");
                break;
            case "MINOR MISCONDUCT":
                recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Minor Misconduct", "Complaint Type");
                setSessionVariable("complaintType").to("Minor Misconduct");
                break;
            case "EX-GRATIA":
                recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Ex-Gratia", "Complaint Type");
                setSessionVariable("complaintType").to("Ex-Gratia");
                break;
            default:
                pendingStep(complaintType + " is not defined within " + getMethodName());
        }
        clickTheButton("Continue");
        System.out.println("Complaint type: " + complaintType);
    }

    public void selectAComplaintType() {
        String complaintType = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Complaint Type");
        setSessionVariable("complaintType").to(complaintType);
    }

    public void selectAChannel() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel");
    }

    public void selectAdditionalInformation() {
        List<String> additionalInfoList = Arrays.asList("Safe Guarding","Vulnerable","Case is made by / on behalf of a minor");
        Random rand = new Random();
        String additionalInfo = additionalInfoList.get(rand.nextInt(additionalInfoList.size()));
        recordCaseData.checkSpecificCheckbox(additionalInfo);
    }

    public void selectComplaintOrigin() {
        String selectedComplaintOrigin = recordCaseData.selectRandomOptionFromDropdownWithHeading("Complaint Origin");
        if (selectedComplaintOrigin.equalsIgnoreCase("Other")) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Other Complaint Origin");
        }
    }

    public void enterADescriptionOfTheComplaint() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Case Summary");
    }

    public void selectASeverity() {
        String selectedSeverity = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Severity");
        if (selectedSeverity.equalsIgnoreCase("Very high")| selectedSeverity.equalsIgnoreCase("High")) {
            recordCaseData.checkSpecificCheckbox("Safe Guarding");
            recordCaseData.checkSpecificCheckbox("Vulnerable");
        }
    }

    public void enterAPreviousUKVIComplaintReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous UKVI Complaint Ref");
    }

    public void enterAPreviousComplaintReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous Complaint Reference");
    }

    public void enterAThirdPartyReference() { recordCaseData.enterTextIntoTextFieldWithHeading("Third Party Reference");
    }

    public void enterComplaintDetails() {
        selectAChannel();
        enterADescriptionOfTheComplaint();
        selectASeverity();
        enterAPreviousUKVIComplaintReference();
        enterAThirdPartyReference();
    }

    public void openTheServiceComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Service");
    }

    public void openTheSeriousAndMinorComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious and Minor");
    }

    public void openTheSeriousComplaintCategoryAccordion() {
        openOrCloseAccordionSection("Serious");
    }

    public void selectAVisibleClaimCategory() {
        List<WebElementFacade> claimCategories = findAll("//input[not(@checked)]/following-sibling::label[contains(@for,'Cat')]");
        List<WebElementFacade> visibleClaimCategories = new ArrayList<>();
        for (WebElementFacade claimCategory: claimCategories) {
            if (claimCategory.isCurrentlyVisible()) {
                visibleClaimCategories.add(claimCategory);
            }
        }
        recordCaseData.checkRandomCheckboxFromList(visibleClaimCategories);
    }

    public void selectAnOwningCSU() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Owning CSU");
    }

    public void selectSpecificOwningCSU(String owningCSU) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(owningCSU, "Owning CSU");
    }

    //POGR

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

    public void completeComplainantDetails() {
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
    }

    public void enterDateLetterSent() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date Letter Sent");
    }

    public void selectInvestigatingTeam() {
        String investigatingTeam = selectRandomOptionFromDropdownWithHeading("Investigating Team");
        setSessionVariable("investigatingTeam").to(investigatingTeam);
    }
}
