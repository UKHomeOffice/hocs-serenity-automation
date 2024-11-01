package com.hocs.test.glue.complaints;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.complaints.ComplaintsTriageAndInvestigation;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.complaints.ComplaintsRegistrationAndDataInput;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Dashboard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ComplaintsRegistrationAndDataInputStepDefs extends BasePage {

    ComplaintsRegistrationAndDataInput complaintsRegistrationAndDataInput;

    CaseView caseView;
    Dashboard dashboard;

    ComplaintsTriageAndInvestigation complaintsTriageAndInvestigation;

    @And("I enter the Complainant Details")
    public void iEnterTheComplainantDetails() {
        complaintsRegistrationAndDataInput.enterComplainantDetails();
    }

    @And("I select {string} as the Complaint Type")
    public void iSelectAsTheComplaintType(String complaintType) {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType(complaintType);
    }

    @And("I enter the complaint details on the Complaint Input page")
    public void iEnterTheComplaintDetailsOnTheComplaintInputPage() {
        if (!iedetCase()) {
            complaintsRegistrationAndDataInput.selectAComplaintChannel();
        }
        if (iedetCase()) {
            complaintsRegistrationAndDataInput.selectComplaintOrigin();
        }
        if(iedetCase()){
            complaintsTriageAndInvestigation.selectIEDETBusinessArea();
        }
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        if (!iedetCase() && !bfCase() && !bf2Case()) {
            complaintsRegistrationAndDataInput.selectASeverity();
            complaintsRegistrationAndDataInput.enterAPreviousUKVIComplaintReference();
        }
        if(comp2DirectCase()){

                complaintsRegistrationAndDataInput.selectExternalContractor();


        }
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
    }

    @And("I select a {string} Complaint Category")
    public void iSelectAComplaintCategory(String complaintCategory) {
        if (!(iedetCase()) && !(compCase() || comp2Case() || comp2DirectCase())) {
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
            setSessionVariable("complaintCategory").to(complaintCategory);
            complaintsRegistrationAndDataInput.selectAVisibleClaimCategory();
        } else if (compCase() || comp2Case() || comp2DirectCase()) {
            complaintsTriageAndInvestigation.selectUKVIClaimCategory(complaintCategory);
            clickTheButton("Continue");
        } else {
            complaintsTriageAndInvestigation.selectIEDETClaimCategory(complaintCategory);
            clickTheButton("Continue");
            }
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
        clickContinueButton();
    }

    @And("I enter details on the Data Input screen")
    public void iCompleteTheDataInputScreen() {
        complaintsRegistrationAndDataInput.completeDataInputScreen();
        clickContinueButton();
    }

    @And("I enter the date that the Interim letter was sent")
    public void iEnterTheDateThatTheInterimLetterWasSent() {
        complaintsRegistrationAndDataInput.enterDateInterimLetterSent();
        clickContinueButton();
    }

    @And("I select the investigating team for the case")
    public void iSelectTheInvestigatingTeamForTheCase() {
        complaintsRegistrationAndDataInput.selectInvestigatingTeam();
        clickFinishButton();
    }

    @And("I record that the GRO case is a {string}")
    public void iRecordThatTheCaseIsA(String deadlineDefiningFactor) {
        complaintsRegistrationAndDataInput.enterDateOfCorrespondence();
        complaintsRegistrationAndDataInput.selectComplaintCategory();
        complaintsRegistrationAndDataInput.selectComplaintReason();
        complaintsRegistrationAndDataInput.enterADescriptionOfTheComplaint();
        complaintsRegistrationAndDataInput.selectIsLoARequired();
        switch (deadlineDefiningFactor) {
            case "Priority GRO complaint":
                complaintsRegistrationAndDataInput.checkPriorityCheckbox();
                break;
            case "non-Priority, non-Post GRO complaint":
                complaintsRegistrationAndDataInput.selectASpecificComplaintChannel("Email");
                break;
            case "non-Priority, Post GRO complaint":
                complaintsRegistrationAndDataInput.selectASpecificComplaintChannel("Post");
                break;
            default:
                pendingStep(deadlineDefiningFactor + " is not defined within " + getMethodName());
        }
        clickContinueButton();
        waitABit(1000);
    }

    @And("I escalate the case to PSU")
    public void iEscalateTheCaseToPSU() {
        complaintsRegistrationAndDataInput.selectASpecificComplaintType("Serious misconduct");
        complaintsRegistrationAndDataInput.openTheSeriousComplaintCategoryAccordion();
        complaintsTriageAndInvestigation.selectAVisibleClaimCategory();
        clickContinueButton();
        iEnterTheComplaintDetailsOnTheComplaintInputPage();
        clickTheButton("Finish and escalate to PSU");
    }

    @And("I submit a PSU reference")
    public void iSubmitAPSUReference() {
        complaintsRegistrationAndDataInput.enterAPSUReference();
        clickTheButton("Submit");
    }

    @And("I chose not to upload an interim letter")
    public void iChoseNotToUploadAnInterimLetter() {
        clickContinueButton();
    }

    @Then("When I attempt to continue without selecting a PSU Reference an error message is displayed")
    public void iAttemptToContinueWithoutSelectingAPSUReference() {
        dashboard.ensureCurrentCaseIsLoadedAndAllocatedToCurrentUser();
        clickTheButton("Submit");
        assertExpectedErrorMessageIsDisplayed("PSU reference is required");
    }

    @And("I select Sopra Steria case option {string}")
    public void iSelectSopraSteriaCaseOption(String radioOption) {
        complaintsRegistrationAndDataInput.selectStage2CaseType(radioOption);
         clickTheButton("Submit");

    }

    @And("I select {string} Complaint Category")
    public void iSelectComplaintCategory(String category) {
        complaintsTriageAndInvestigation.selectUKVIClaimCategory(category);
        clickContinueButton();
    }

    @And("I enter the complaint details on the Complaint Input page for Serious Misconduct")
    public void iEnterTheComplaintDetailsOnTheComplaintInputPageForSeriousMisconduct() {
        complaintsRegistrationAndDataInput.selectAComplaintChannel();
        complaintsRegistrationAndDataInput.enterAPreviousUKVIComplaintReference();
        complaintsRegistrationAndDataInput.enterAThirdPartyReference();
        complaintsRegistrationAndDataInput.selectExternalContractor();
    }

    @And("I enter the Additional Details")
    public void iEnterTheAdditionalDetails() {
        complaintsRegistrationAndDataInput.enterAdditionalDetails();
    }
}
