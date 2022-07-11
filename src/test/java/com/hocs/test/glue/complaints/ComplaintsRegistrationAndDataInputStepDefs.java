package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.complaints.ComplaintsTriageAndInvestigation;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsRegistrationAndDataInput;
import com.hocs.test.pages.decs.CaseView;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ComplaintsRegistrationAndDataInputStepDefs extends BasePage {

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    CaseView caseView;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    @And("I enter the Complainant Details")
    public void iEnterTheComplainantDetails() {
        complaintsRegistrationAndDataInput.enterComplainantDOB(getDatePlusMinusNDaysAgo(-14600));
        complaintsRegistrationAndDataInput.selectAGender();
        complaintsRegistrationAndDataInput.selectANationality();
        complaintsRegistrationAndDataInput.enterACompanyName();
        complaintsRegistrationAndDataInput.enterAHomeOfficeReference("Test entry for Home Office Reference");
        complaintsRegistrationAndDataInput.enterAPortReference();
        clickTheButton("Continue");
    }

    @And("I select {string} as the Complaint Type")
    public void iSelectAsTheComplaintType(String complaintType) {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType(complaintType);
    }

    @And("I enter the complaint details on the Complaint Input page")
    public void iEnterTheComplaintDetailsOnTheComplaintInputPage() {
        complaintsRegistrationAndDataInput.selectAComplaintChannel();
        if (iedetCase() | smcCase()) {
            complaintsRegistrationAndDataInput.selectComplaintOrigin();
        }
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        if (!iedetCase() && !bfCase() && !smcCase() && !bf2Case()) {
            complaintsRegistrationAndDataInput.selectASeverity();
            complaintsRegistrationAndDataInput.enterAPreviousUKVIComplaintReference();
        } else if (smcCase()) {
            complaintsRegistrationAndDataInput.selectAdditionalInformation();
            complaintsRegistrationAndDataInput.enterAPreviousUKVIComplaintReference();
        }
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
    }

    @And("I select a {string} Complaint Category")
    public void iSelectAComplaintCategory(String complaintCategory) {
        switch (complaintCategory.toUpperCase()) {
            case "SERVICE":
                complaintsRegistrationAndDataInput.openTheServiceComplaintCategoryAccordion();
                break;
            case "SERIOUS AND MINOR":
                complaintsTriageAndInvestigation.openTheSeriousAndMinorComplaintCategoryAccordion();
                break;
            case "SERIOUS":
                complaintsRegistrationAndDataInput.openTheSeriousComplaintCategoryAccordion();
                break;
            case "EX-GRATIA":
                complaintsTriageAndInvestigation.openExGratiaAccordion();
                break;
            default:
                pendingStep(complaintCategory + " is not defined within " + getMethodName());
        }
        waitABit(1000);
        complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
    }

    @And("I select a Owning CSU")
    public void iSelectAOwningCSU() {
        complaintsRegistrationAndDataInput.selectAnOwningCSU();
    }

    @And("I select {string} as the Owning CSU")
    public void iSelectAsTheOwningCSU(String owningCSU) {
        complaintsRegistrationAndDataInput.selectSpecificOwningCSU(owningCSU);
    }

    @Then("the previous COMP case is displayed")
    public void thePreviousCOMPCaseIsDisplayed() {
        String caseRef = sessionVariableCalled("compCaseReference");
        caseView.specificCaseIsLoaded(caseRef);
    }

    @And("I select {string} as the business area for the POGR case")
    public void iSelectAsTheBusinessAreaForThePOGRCase(String businessArea) {
        complaintsRegistrationAndDataInput.selectSpecificBusinessArea(businessArea);
        safeClickOn(continueButton);
    }

    @And("I enter details on the Data Input screen")
    public void iCompleteTheDataInputScreen() {
        complaintsRegistrationAndDataInput.completeDataInputScreen();
        safeClickOn(continueButton);
    }

    @And("I enter the date that the Interim letter was sent")
    public void iEnterTheDateThatTheInterimLetterWasSent() {
        complaintsRegistrationAndDataInput.enterDateInterimLetterSent();
        safeClickOn(continueButton);
    }

    @And("I select the investigating team for the case")
    public void iSelectTheInvestigatingTeamForTheCase() {
        complaintsRegistrationAndDataInput.selectInvestigatingTeam();
        safeClickOn(finishButton);
    }

    @And("I record that the case is a Priority case")
    public void iRecordThatTheCaseIsAPriorityCase() {
        complaintsRegistrationAndDataInput.selectPOGRCategory();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.selectIsLoARequired();
        complaintsRegistrationAndDataInput.checkPriorityCheckbox();
        safeClickOn(continueButton);
        waitABit(1000);
    }

    @And("I record that the case was not received by post")
    public void iRecordThatTheCaseWasNotReceivedByPost() {
        complaintsRegistrationAndDataInput.selectPOGRCategory();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.selectIsLoARequired();
        complaintsRegistrationAndDataInput.selectASpecificComplaintChannel("Email");
        safeClickOn(continueButton);
        waitABit(1000);
    }

    @And("I record that the case was received by post")
    public void iRecordThatTheCaseWasReceivedByPost() {
        complaintsRegistrationAndDataInput.selectPOGRCategory();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.selectASpecificComplaintChannel("Post");
        complaintsRegistrationAndDataInput.selectIsLoARequired();
        safeClickOn(continueButton);
        waitABit(1000);
    }
}
