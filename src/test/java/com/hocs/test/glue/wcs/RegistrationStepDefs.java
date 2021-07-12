package com.hocs.test.glue.wcs;

import com.hocs.test.pages.platform.CreateCase;
import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.wcs.ClaimSchema;
import com.hocs.test.pages.wcs.WCSRegistration;
import com.hocs.test.pages.wcs.RegistrationIdentityConfirmation;
import com.hocs.test.pages.wcs.RegistrationIdentityStage1;
import com.hocs.test.pages.wcs.RegistrationTaskForceCheck;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationStepDefs extends BasePage {

    RegistrationTaskForceCheck registrationTaskForceCheck;

    WCSRegistration wcsRegistration;

    RegistrationIdentityConfirmation registrationIdentityConfirmation;

    RegistrationIdentityStage1 registrationIdentityStage1;

    ClaimSchema claimSchema;

    CreateCase createCase;

    @And("I complete the Registration stage and send the claim to Triage")
    public void theRegistrationStageIsCompletedSendToTriage() {
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        wcsRegistration.confirmClaimantsID();
        wcsRegistration.selectIDAndEligibilityConfirmed();
    }

    @And("I complete the Registration stage and send the claim to Eligibility")
    public void theRegistrationStageIsCompletedSendToEligibility() {
        iProgressToTheRegistrationIdentityConfirmationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registrationIdentityConfirmation.confirmClaimantsIdentity();
    }

    @Given("I select {string} for has Task Force confirmed status question")
    public void iSelectForHasTaskForceConfirmedStatusQuestion(String option) {
        if (option.toUpperCase().equals("YES")){
           registrationTaskForceCheck.selectClaimHasGoneThroughTaskForce();
        } else {
            registrationTaskForceCheck.selectClaimHasNotGoneTroughTaskForce();
        }
    }

    @When("I progress to the Registration page")
    public void iProgressToTheRegistrationPage() {
        registrationTaskForceCheck.selectClaimHasGoneThroughTaskForce();
    }

    @Then("an error message for each mandatory claim details field should be displayed as I have not completed them")
    public void anErrorMessageForEachMandatoryClaimDetailsFieldShouldBeDisplayedAsIHaveNotCompletedThem() {
        wcsRegistration.assertDateClaimReceivedErrorMessage();
        wcsRegistration.assertTypeOfClaimErrorMessage();
        wcsRegistration.assertHowWasFormSubmittedErrorMessage();
        wcsRegistration.assertFormSentThroughClaimantAssistanceErrorMessage();
        wcsRegistration.assertWhereIsTheClaimFromErrorMessage();
    }

    @Then("an error message is displayed as I have not confirmed the claimants status")
    public void anErrorMessageIsDisplayedAsIHaveNotConfirmedTheClaimantsStatus() {
        wcsRegistration.assertCanYouConfirmClaimantStatusErrorMessage();
    }

    @And("I complete the required case details fields")
    public void iCompleteTheRequiredCaseDetailsFields() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
    }

    @Then("an error message should be displayed as I have not confirmed if the status has been confirmed by the Task Force")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotConfirmedIfTheStatusHasBeenConfirmedByTheTaskForce() {
        registrationTaskForceCheck.assertClaimGoneThroughTaskForceIsRequiredErrorMessage();
    }

    @When("I progress to the Registration Identity Confirmation page")
    public void iProgressToTheRegistrationIdentityConfirmationPage() {
        registrationTaskForceCheck.selectClaimHasNotGoneTroughTaskForce();
    }

    @And("I select that I can confirm the claimants identity")
    public void iSelectThatICanConfirmTheClaimantsIdentity() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registrationIdentityConfirmation.confirmClaimantsIdentity();
    }

    @Then("an error message is displayed as I have not selected an answer to the confirm claimants identity question")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnAnswerToTheConfirmClaimantsIdentityQuestion() {
        registrationIdentityConfirmation.assertCanYouConfirmClaimantIdentityErrorMessage();
    }

    @And("I put the claim on hold at the Registration stage")
    public void iPutTheClaimOnHoldAtTheRegistrationStage() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        registrationIdentityConfirmation.putTheClaimOnHold();
    }

    @When("I pass the claim to stage 1 identity check")
    public void iPassTheClaimToStageIdentityCheck() {
        registrationIdentityConfirmation.passTheClaimToStage1IdentityCheck();
    }

    @And("I select that identity cannot be confirmed")
    public void iSelectThatIdentityCannotBeConfirmed() {
        registrationIdentityStage1.failIdentityCheck();
    }

    @And("I complete the Registration page")
    public void iCompleteTheRegistrationPage() {
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        wcsRegistration.confirmClaimantsID();
    }

    @When("I pass the claim to the Eligibility team")
    public void iPassTheClaimToTheEligibilityTeam() {
        registrationIdentityStage1.passClaimToEligibilityTeam();
    }

    @Then("an error message is displayed as I have not selected an answer to the cannot identify close claim question")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedAnAnswerToTheCannotIdentifyCloseClaimQuestion() {
        registrationIdentityConfirmation.assertCannotIdentifyCloseClaimErrorMessage();
    }

    @And("I create a WCS claim and enter the claimant name {string}")
    public void iCreateAWCSClaimAndEnterTheClaimantName(String name) {
        createCase.createWCSCase();
        waitFor(wcsRegistration.registrationSchemeCheckTitle);;
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.enterNameIntoPersonalDetailsFullName(name);
        wcsRegistration.confirmClaimantsID();
    }

    @And("I create a WCS claim and enter the claimant DOB as today's date")
    public void iCreateAWCSClaimAndEnterTheClaimantDobAsTodaysDate() {
        createCase.createWCSCase();
        waitFor(wcsRegistration.registrationSchemeCheckTitle);
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.enterTodaysDateInPersonalDetailsDOB();
        wcsRegistration.confirmClaimantsID();
    }

    @And("I create a WCS claim and enter {string} as the National Insurance No")
    public void iCreateAWCSClaimAndEnterAsTheNationalInsuranceNo(String niNo) throws Throwable {
        createCase.createWCSCase();
        waitFor(wcsRegistration.registrationSchemeCheckTitle);
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.enterCustomeNationalInsuranceNo(niNo);
        wcsRegistration.confirmClaimantsID();
    }

    @And("I create a WCS claim and enter {string} as the previous HOCS reference")
    public void iCreateAWCSClaimAndEnterAsThePreviousHOCSReference(String ref) {
        createCase.createWCSCase();
        waitFor(wcsRegistration.registrationSchemeCheckTitle);
        iProgressToTheRegistrationPage();
        claimSchema.completeRequiredFieldsInCaseInfoSection();
        claimSchema.expandCollapseGovernmentRecordsSection();
        claimSchema.enterCustomPreviousHOCSReference(ref);
        wcsRegistration.confirmClaimantsID();
    }

    @When("I select the primary claimants nationality dropdown")
    public void iTypeIntoThePrimaryClaimantsNationalityField() {
        claimSchema.expandCollapsePersonalDetailsSection();
        claimSchema.selectPersonalDetailsNationalityDropdown();
    }

    @Then("{string} should be visible as an option to select from the dropdown list")
    public void shouldBeVisibleAsAnOptionToSelectFromTheDropdownList(String selection) {
        claimSchema.selectFromPersonalDetailsNationalityDropDown(selection);
    }

    @And("I choose to send the claim to Eligibility")
    public void iChooseToSendTheClaimToEligibility() {
        wcsRegistration.selectIDConfirmedEligibilityNotConfirmed();
    }

    @And("I choose to send the claim to Triage")
    public void iChooseToSendTheClaimToTriage() {
        wcsRegistration.selectIDAndEligibilityConfirmed();
    }

    @Then("an error message is displayed as I have not selected a team to send the claim on to")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedATeamToSendTheClaimOnTo() {
        wcsRegistration.assertWhereShouldTheCaseProgressToErrorMessage();
    }
}
