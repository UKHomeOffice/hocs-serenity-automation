package com.hocs.test.glue.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.wcs.ClaimCategories;
import com.hocs.test.pages.wcs.ClaimSchema;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ClaimCategoriesStepDefs {

    ClaimSchema claimSchema;

    ClaimCategories claimCategories;

    @And("I open the case details accordion")
    public void iOpenTheCaseDetails() {
        claimSchema.caseDetailsAccordion.click();
    }

    @And("I open the case info accordion")
    public void iOpenTheCaseInfoAccordion() {
        claimSchema.caseInfoAccordionButton.click();
    }

    @And("I open the claim categories accordion")
    public void iOpenClaimCategoriesAccordion() {
        claimSchema.expandCollapseClaimCategoriesSection();
    }

    @And("I select the {string} checkbox")
    public void iSelectAClaimCategoryCheckbox(String ClaimCategory) {
        switch (ClaimCategory.toUpperCase()) {
            case "IMMIGRATION AND LEGAL FEES":
                claimSchema.clickOnImmigrationAndLegalFeesCheckbox();
                break;
            case "DETENTION DEPORTATION AND REMOVAL":
                claimSchema.clickOnDetentionAndDeportationAndRemovalCheckbox();
                break;
            case "EMPLOYMENT":
                claimSchema.clickOnEmploymentCheckbox();
                break;
            case "CHILD BENEFIT AND TAX CREDIT":
                claimSchema.clickOnChildBenefitCheckbox();
                break;
            case "BENEFITS":
                claimSchema.clickOnBenefitsCheckbox();
                break;
            case "HOUSING":
                claimSchema.clickOnHousingCheckbox();
                break;
            case "HEALTH":
                claimSchema.clickOnHealthCheckbox();
                break;
            case "BANKING":
                claimSchema.clickOnBankingCheckbox();
                break;
            case "EDUCATION":
                claimSchema.clickOnEducationCheckbox();
                break;
            case "HOMELESSNESS":
                claimSchema.clickOnHomelessnessCheckbox();
                break;
            case "IMPACT ON DAILY LIFE":
                claimSchema.clickOnImpactOnDailyLifeCheckbox();
                break;
            case "DISCRETIONARY":
                claimSchema.clickOnDiscretionaryCheckbox();
                break;
            case "DRIVING LICENCE":
                claimSchema.clickOnDrivingLicenceCheckbox();
                break;
            case "URGENT EXCEPTIONAL PAYMENT":
                claimSchema.clickOnUrgentExceptionalPaymentCheckbox();
                break;
            case "LIVING COSTS":
                claimSchema.clickOnLivingCostsCheckbox();
                break;
            default:
                pendingStep(ClaimCategory + " is not defined within " + getMethodName());
        }

    }

    @And("I choose a full payment for the {string} and enter {int} into the Amount Awarded field, and {int} into the Amount Paid field")
    public void iChooseAFullPaymentForTheAndEnterIntoTheAmountAwardedFieldAndIntoTheAmountPaidField(String ClaimCategory,
            int Award, int Paid) {
        switch (ClaimCategory) {
            case "DETENTION DEPORTATION AND REMOVAL":
                claimCategories.detentionDeportationAndRemovalInputs(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded2").to(Award);
                break;
            case "CHILD BENEFIT AND TAX CREDIT":
                claimCategories.childBenefitAndTaxCreditPaymentType(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded4").to(Award);
                break;
            case "BENEFITS":
                claimCategories.benefitsInputs(2, 0, 0, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded5").to(Award);
                break;
            case "HOUSING":
                claimCategories.housingInputs(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded6").to(Award);
                break;
            case "HEALTH":
                claimCategories.healthInputs(2, 0, 0, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded7").to(Award);
                break;
            case "BANKING":
                claimCategories.bankingInput(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded8").to(Award);
                break;
            case "EDUCATION":
                claimCategories.educationInput(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded9").to(Award);
                break;
            case "HOMELESSNESS":
                claimCategories.homelessnessInput(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded10").to(Award);
                break;
            case "DISCRETIONARY":
                claimCategories.discretionaryInput(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded12").to(Award);
                break;
            case "DRIVING LICENCE":
                claimCategories.drivingLicenceInputs(2, Award, Paid, "1/1/2021");
                setSessionVariable("amountAwarded13").to(Award);
                break;
            default:
                pendingStep(ClaimCategory + " is not defined within " + getMethodName());
        }
    }

    @And("I choose a full payment for the {string} and enter {int} into the Amount Awarded field, {int} into the Amount "
            + "Paid field, and {int} into the Additional Amount Paid field")
    public void iChooseAFullPaymentForTheAndEnterIntoTheAmountAwardedFieldIntoTheAmountPaidFieldAndIntoTheAdditionalAmountPaidField(
            String ClaimCategory, int Award, int Paid, int AdditionalPaid) {
        switch (ClaimCategory) {
            case "IMMIGRATION AND LEGAL FEES":
                claimCategories.immigrationAndLegalFeeInputs(2, Award, Paid, AdditionalPaid, "1/1/2021", "1/1/2021");
                setSessionVariable("amountAwarded1").to(Award);
                break;
            case "EMPLOYMENT":
                claimCategories.employmentInputs(2, Award, Paid, AdditionalPaid, "1/1/2021", "1/1/2021");
                setSessionVariable("amountAwarded3").to(Award);
                break;
            case "IMPACT ON DAILY LIFE":
                claimCategories.impactOnDailyLifeInput(2, Award, Paid, AdditionalPaid, "1/1/2021", "1/1/2021");
                setSessionVariable("amountAwarded11").to(Award);
                break;
            default:
                pendingStep(ClaimCategory + " is not defined within " + getMethodName());
        }
    }

    @And("I enter {int} into the Preliminary Offer field, and {int} into the Preliminary Offer Paid Amount field")
    public void iEnterIntoThePreliminaryOfferField(int PreliminaryAwarded, int PreliminaryPaid) {
        claimCategories.preliminaryOfferInputs(PreliminaryAwarded, PreliminaryPaid, "1/1/2021", "1/1/2021", "1/1/2021");
    }

    @And("I enter {int} into amount awarded and {int} into amount deducted for the Urgent Exceptional Payment")
    public void urgentExceptionalPaymentAwardedAndDeductedAmounts(int Award, int Deducted) {
        claimCategories.urgentExceptionalPaymentInputs(Award, Deducted);
    }

    @And("I delete the {string} amount awarded")
    public void iDeleteTheAmountAwardedForAClaimCategory(String claimCategory) {
        switch (claimCategory.toUpperCase()) {
            case "IMMIGRATION AND LEGAL FEES":
                claimCategories.iClickShowHideDetails("IMMIGRATION AND LEGAL FEES");
                claimCategories.immigrationAndLegalFeeAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded1"));
                break;
            case "DETENTION DEPORTATION AND REMOVAL":
                claimCategories.iClickShowHideDetails("DETENTION DEPORTATION AND REMOVAL");
                claimCategories.detentionDeportationAndRemovalAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded2"));
                break;
            case "EMPLOYMENT":
                claimCategories.iClickShowHideDetails("EMPLOYMENT");
                claimCategories.employmentAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded3"));
                break;
            case "CHILD BENEFIT AND TAX CREDIT":
                claimCategories.iClickShowHideDetails("CHILD BENEFIT AND TAX CREDIT");
                claimCategories.childBenefitAndTaxCreditAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded4"));
                break;
            case "BENEFITS":
                claimCategories.iClickShowHideDetails("BENEFITS");
                claimCategories.benefitsAmountAwarded.clear();
                claimCategories.benefitsAmountAwarded.sendKeys("0");
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded5"));
                break;
            case "HOUSING":
                claimCategories.iClickShowHideDetails("HOUSING");
                claimCategories.housingAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded6"));
                break;
            case "HEALTH":
                claimCategories.iClickShowHideDetails("HEALTH");
                claimCategories.healthAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded7"));
                break;
            case "BANKING":
                claimCategories.iClickShowHideDetails("BANKING");
                claimCategories.bankingAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded8"));
                break;
            case "EDUCATION":
                claimCategories.iClickShowHideDetails("EDUCATION");
                claimCategories.educationAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded9"));
                break;
            case "HOMELESSNESS":
                claimCategories.iClickShowHideDetails("HOMELESSNESS");
                claimCategories.homelessnessAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded10"));
                break;
            case "IMPACT ON DAILY LIFE":
                claimCategories.iClickShowHideDetails("IMPACT ON DAILY LIFE");
                claimCategories.impactOnDailyLifeAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded11"));
                break;
            case "DISCRETIONARY":
                claimCategories.iClickShowHideDetails("DISCRETIONARY");
                claimCategories.discretionaryAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded12"));
                break;
            case "DRIVING LICENCE":
                claimCategories.iClickShowHideDetails("DRIVING LICENCE");
                claimCategories.drivingLicenceAmountAwarded.clear();
                claimCategories.subtractFromAmountAwardedTotal(sessionVariableCalled("amountAwarded13"));
                break;
            default:
                pendingStep(claimCategory + " is not defined within " + getMethodName());
        }
    }

    @And("I save changes to the claim")
    public void iSaveChanges() {
        claimCategories.saveChanges();
    }

    @Then("the displayed totals should match the values I have recorded")
    public void theTotalAwardedFieldShouldDisplayTheCorrectAmount() {
        claimCategories.assertThatCorrectTotalsAreDisplayed();
    }

    @And("I enter some value into each possible field during the Casework stage")
    public void enterAValueIntoAllFields() {
        claimSchema.completeAllFieldsInClaimSchema();
    }

    @And("All fields should be populated in the read-only case info accordion")
    public void readOnlyFieldsAllPopulated() {
        claimSchema.assertAllFieldsPopulated();
    }

    @And("I record the amounts I enter for each claim category")
    public void iRecordTheAmountsIEnterForEachClaimCategory() {
        setSessionVariable("totalAwarded").to(0);
        setSessionVariable("totalPaid").to(0);
    }
}
