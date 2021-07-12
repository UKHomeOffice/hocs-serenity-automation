package com.hocs.test.glue.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.comp.COMPRegistration;
import io.cucumber.java.en.And;

public class RegistrationStepDefs extends BasePage {

    COMPRegistration COMPRegistration;

    @And("I enter the Complainant Details")
    public void iEnterTheComplainantDetails() {
        COMPRegistration.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        COMPRegistration.selectAGender();
        COMPRegistration.selectANationality();
        COMPRegistration.enterACompanyName();
        COMPRegistration.enterAHomeOfficeReference();
        COMPRegistration.enterAPortReference();
        clickTheButton("Continue");
    }

    @And("I select {string} as the Complaint Type")
    public void iSelectAsTheComplaintType(String complaintType) {
        COMPRegistration.selectComplaintType(complaintType);
        clickTheButton("Continue");
    }

    @And("I enter the complaint details on the Complaint Input page")
    public void iEnterTheComplaintDetailsOnTheComplaintInputPage() {
        COMPRegistration.selectAChannel();
        COMPRegistration.enterADescriptionOfTheComplaint();
        COMPRegistration.selectASeverity();
        COMPRegistration.selectSafeGuardingAndVulnerableIfPossible();
        COMPRegistration.enterAPreviousUKVIComplaintReference();
        COMPRegistration.enterAThirdPartyReference();
        clickTheButton("Continue");
    }

    @And("I select a {string} Complaint Category")
    public void iSelectAComplaintCategory(String complaintCategory) {
        switch (complaintCategory.toUpperCase()) {
            case "SERVICE":
                COMPRegistration.openTheServiceComplaintCategoryAccordion();
                break;
            case "SERIOUS AND MINOR":
                COMPRegistration.openTheSeriousAndMinorComplaintCategoryAccordion();
                break;
            case "SERIOUS":
                COMPRegistration.openTheSeriousComplaintCategoryAccordion();
                break;
            default:
                pendingStep(complaintCategory + " is not defined within " + getMethodName());
        }
        waitABit(1000);
        COMPRegistration.selectAVisibleClaimCategory();
    }

    @And("I select a Owning CSU")
    public void iSelectAOwningCSU() {
        COMPRegistration.selectAnOwningCSU();
    }
}
