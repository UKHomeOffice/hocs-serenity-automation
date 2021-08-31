package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.wcs.ClaimSchema;
import com.hocs.test.pages.wcs.Registration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationStepDefs extends BasePage {

    Registration registration;

    ClaimSchema claimSchema;

    CreateCase createCase;

    @And("I complete the Registration stage and send the claim to Triage")
    public void theRegistrationStageIsCompletedSendToTriage() {
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsID();
        registration.selectIDAndEligibilityConfirmed();
    }

    @And("I complete the Registration stage and send the claim to Eligibility")
    public void theRegistrationStageIsCompletedSendToEligibility() {
        iProgressToTheRegistrationIdentityConfirmationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsIdentity();
    }

    @Given("I select {string} for has Task Force confirmed status question")
    public void iSelectForHasTaskForceConfirmedStatusQuestion(String option) {
        registration.selectIfClaimHasGoneThroughTaskForce(option);
    }

    @When("I progress to the Registration page")
    public void iProgressToTheRegistrationPage() {
        registration.selectIfClaimHasGoneThroughTaskForce("Yes");
    }

    @Then("an error message for each mandatory claim details field should be displayed as I have not completed them")
    public void anErrorMessageForEachMandatoryClaimDetailsFieldShouldBeDisplayedAsIHaveNotCompletedThem() {
        registration.assertDateClaimReceivedErrorMessage();
        registration.assertTypeOfClaimErrorMessage();
        registration.assertHowWasFormSubmittedErrorMessage();
        registration.assertFormSentThroughClaimantAssistanceErrorMessage();
        registration.assertWhereIsTheClaimFromErrorMessage();
    }

    @Then("an error message is displayed as I have not confirmed the claimants status")
    public void anErrorMessageIsDisplayedAsIHaveNotConfirmedTheClaimantsStatus() {
        registration.assertCanYouConfirmClaimantStatusErrorMessage();
    }

    @And("I complete the required case details fields")
    public void iCompleteTheRequiredCaseDetailsFields() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
    }

    @Then("an error message should be displayed as I have not confirmed if the status has been confirmed by the Task Force")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotConfirmedIfTheStatusHasBeenConfirmedByTheTaskForce() {
        registration.assertClaimGoneThroughTaskForceIsRequiredErrorMessage();
    }

    @When("I progress to the Registration Identity Confirmation page")
    public void iProgressToTheRegistrationIdentityConfirmationPage() {
        registration.selectIfClaimHasGoneThroughTaskForce("No");
    }

    @And("I select that I can confirm the claimants identity")
    public void iSelectThatICanConfirmTheClaimantsIdentity() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsIdentity();
    }

    @Then("an error message is displayed as I have not selected an answer to the confirm claimants identity question")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnAnswerToTheConfirmClaimantsIdentityQuestion() {
        registration.assertCanYouConfirmClaimantIdentityErrorMessage();
    }

    @And("I put the claim on hold at the Registration stage")
    public void iPutTheClaimOnHoldAtTheRegistrationStage() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.putTheClaimOnHold();
    }

    @When("I pass the claim to stage 1 identity check")
    public void iPassTheClaimToStageIdentityCheck() {
        registration.passTheClaimToStage1IdentityCheck();
    }

    @And("I select that identity cannot be confirmed")
    public void iSelectThatIdentityCannotBeConfirmed() {
        registration.failIdentityCheck();
    }

    @And("I complete the Registration page")
    public void iCompleteTheRegistrationPage() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registration.confirmClaimantsID();
    }

    @When("I pass the claim to the Eligibility team")
    public void iPassTheClaimToTheEligibilityTeam() {
        registration.passClaimToEligibilityTeam();
    }

    @Then("an error message is displayed as I have not selected an answer to the cannot identify close claim question")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnAnswerToTheCannotIdentifyCloseClaimQuestion() {
        registration.assertCannotIdentifyCloseClaimErrorMessage();
    }

    @And("I create a WCS claim and enter the claimant name {string}")
    public void iCreateAWCSClaimAndEnterTheClaimantName(String name) {
        createCase.createWCSCase();
        waitFor(registration.registrationSchemeCheckTitle);;
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.enterNameIntoPersonalDetailsFullName(name);
        registration.confirmClaimantsID();
    }

    @And("I create a WCS claim and enter the claimant DOB as today's date")
    public void iCreateAWCSClaimAndEnterTheClaimantDobAsTodaysDate() {
        createCase.createWCSCase();
        waitFor(registration.registrationSchemeCheckTitle);
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.enterTodaysDateInPersonalDetailsDOB();
        registration.confirmClaimantsID();
    }

    @And("I create a WCS claim and enter {string} as the National Insurance No")
    public void iCreateAWCSClaimAndEnterAsTheNationalInsuranceNo(String niNo) throws Throwable {
        createCase.createWCSCase();
        waitFor(registration.registrationSchemeCheckTitle);
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.enterSpecificNationalInsuranceNumber(niNo);
        registration.confirmClaimantsID();
    }

    @And("I create a WCS claim and enter {string} as the previous HOCS reference")
    public void iCreateAWCSClaimAndEnterAsThePreviousHOCSReference(String ref) {
        createCase.createWCSCase();
        waitFor(registration.registrationSchemeCheckTitle);
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapseGovernmentRecordsSection();
        claimSchema.enterSpecificPreviousHOCSReference(ref);
        registration.confirmClaimantsID();
    }

    @And("I choose to send the claim to Eligibility")
    public void iChooseToSendTheClaimToEligibility() {
        registration.selectIDConfirmedEligibilityNotConfirmed();
    }

    @And("I choose to send the claim to Triage")
    public void iChooseToSendTheClaimToTriage() {
        registration.selectIDAndEligibilityConfirmed();
    }

    @Then("an error message is displayed as I have not selected a team to send the claim on to")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedATeamToSendTheClaimOnTo() {
        registration.assertWhereShouldTheCaseProgressToErrorMessage();
    }

    @And("I select that I can confirm the claimants ID")
    public void iSelectThatICanConfirmTheClaimantsID() {
        registration.selectConfirmTheClaimantsIDRadioButton();
    }

    @When("I mark the case as unworkable")
    public void iMarkTheCaseAsUnworkable() {
        registration.selectIfClaimHasGoneThroughTaskForce("Yes");
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.selectUnworkable();
    }

    @And("I send the unworkable case to Triage stage")
    public void iSendTheUnworkableCaseToTriageStage() {
        registration.confirmClaimantsID();
        registration.selectIDAndEligibilityConfirmed();
    }
}
