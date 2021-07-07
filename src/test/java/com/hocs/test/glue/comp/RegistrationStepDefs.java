package com.hocs.test.glue.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.comp.Registration;
import io.cucumber.java.en.And;

public class RegistrationStepDefs extends BasePage {

    Registration registration;

    @And("I enter the Complainant Details")
    public void iEnterTheComplainantDetails() {
        registration.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        registration.selectAGender();
        registration.enterACompanyName(generateRandomString());
        registration.enterAHomeOfficeReference(generateRandomString());
        registration.enterAPortReference(generateRandomString());
        clickTheButton("Continue");
    }

    @And("I select {string} as the Complaint Type")
    public void iSelectAsTheComplaintType(String complaintType) {
        registration.selectComplaintType(complaintType);
        clickTheButton("Continue");
    }

    @And("I enter the complaint details on the Complaint Input page")
    public void iEnterTheComplaintDetailsOnTheComplaintInputPage() {
        registration.selectAChannel();
        registration.enterADescriptionOfTheComplaint("Test complaint description");
        registration.selectASeverity();
        registration.selectSafeGuardingAndVulnerableIfPossible();
        registration.enterAPreviousUKVIComplaintReference(generateRandomString());
        registration.enterAThirdPartyReference(generateRandomString());
        clickTheButton("Continue");
    }

    @And("I select a {string} Complaint Category")
    public void iSelectAComplaintCategory(String complaintCategory) {
        switch (complaintCategory.toUpperCase()) {
            case "SERVICE":
                registration.openTheServiceComplaintCategoryAccordion();
                break;
            case "SERIOUS AND MINOR":
                registration.openTheSeriousAndMinorComplaintCategoryAccordion();
                break;
            case "SERIOUS":
                registration.openTheSeriousComplaintCategoryAccordion();
                break;
            default:
                pendingStep(complaintCategory + " is not defined within " + getMethodName());
        }
        waitABit(1000);
        registration.selectAVisibleClaimCategory();
    }

    @And("I select a Owning CSU")
    public void iSelectAOwningCSU() {
        registration.selectAnOwningCSU();
    }
}
