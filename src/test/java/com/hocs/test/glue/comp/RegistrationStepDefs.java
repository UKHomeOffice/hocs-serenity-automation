package com.hocs.test.glue.comp;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.comp.COMPTriage;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.comp.Registration;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.SummaryTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationStepDefs extends BasePage {

    Registration registration;

    CaseView caseView;

    COMPTriage compTriage;

    @And("I enter the Complainant Details")
    public void iEnterTheComplainantDetails() {
        registration.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        registration.selectAGender();
        registration.selectANationality();
        registration.enterACompanyName();
        registration.enterAHomeOfficeReference("Test entry for Home Office Reference");
        registration.enterAPortReference();
        clickTheButton("Continue");
    }

    @And("I select {string} as the Complaint Type")
    public void iSelectAsTheComplaintType(String complaintType) {
        registration.selectComplaintType(complaintType);
    }

    @And("I enter the complaint details on the Complaint Input page")
    public void iEnterTheComplaintDetailsOnTheComplaintInputPage() {
        registration.selectAChannel();
        registration.enterADescriptionOfTheComplaint();
        registration.selectASeverity();
        registration.enterAPreviousUKVIComplaintReference();
        registration.enterAThirdPartyReference();
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
            case "EX-GRATIA":
                compTriage.openExGratiaAccordion();
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

    @Then("the previous COMP case is displayed")
    public void thePreviousCOMPCaseIsDisplayed() {
        String caseRef = sessionVariableCalled("compCaseReference");
        caseView.specificCaseIsLoaded(caseRef);
    }
}
