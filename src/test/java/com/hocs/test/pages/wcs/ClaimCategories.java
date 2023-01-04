package com.hocs.test.pages.wcs;

import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ClaimCategories extends BasePage {

    @FindBy(id = "LegalFeesType")
    public WebElementFacade immigrationAndLegalFeePaymentType;

    @FindBy(id = "LegalFeesAward")
    public WebElementFacade immigrationAndLegalFeeAmountAwarded;

    @FindBy(id = "LegalFeesPaid")
    public WebElementFacade immigrationAndLegalFeeAmountPaid;

    @FindBy(id = "LegalFeesPaidDate-day")
    public WebElementFacade immigrationAndLegalFeePaidDateDay;

    @FindBy(id = "LegalFeesPaidDate-month")
    public WebElementFacade immigrationAndLegalFeePaidDateMonth;

    @FindBy(id = "LegalFeesPaidDate-year")
    public WebElementFacade immigrationAndLegalFeePaidDateYear;

    @FindBy(id = "LegalFeesAdditionalAmountPaid")
    public WebElementFacade immigrationAndLegalFeeAdditionalAmountPaid;

    @FindBy(id = "LegalFeesAdditionalAmountPaidDate-day")
    public WebElementFacade immigrationAndLegalFeeAdditionalPaidDateDay;

    @FindBy(id = "LegalFeesAdditionalAmountPaidDate-month")
    public WebElementFacade immigrationAndLegalFeeAdditionalPaidDateMonth;

    @FindBy(id = "LegalFeesAdditionalAmountPaidDate-year")
    public WebElementFacade immigrationAndLegalFeeAdditionalPaidDateYear;

    @FindBy(id = "RemovalType")
    public WebElementFacade detentionDeportationAndRemovalPaymentType;

    @FindBy(id = "RemovalAward")
    public WebElementFacade detentionDeportationAndRemovalAmountAwarded;

    @FindBy(id = "RemovalPaid")
    public WebElementFacade detentionDeportationAndRemovalAmountPaid;

    @FindBy(id = "RemovalPaidDate-day")
    public WebElementFacade detentionDeportationAndRemovalPaidDateDay;

    @FindBy(id = "RemovalPaidDate-month")
    public WebElementFacade detentionDeportationAndRemovalPaidDateMonth;

    @FindBy(id = "RemovalPaidDate-year")
    public WebElementFacade detentionDeportationAndRemovalPaidDateYear;

    @FindBy(id = "EmploymentType")
    public WebElementFacade employmentPaymentType;

    @FindBy(id = "EmploymentAward")
    public WebElementFacade employmentAmountAwarded;

    @FindBy(id = "EmploymentPaid")
    public WebElementFacade employmentAmountPaid;

    @FindBy(id = "EmploymentPaidDate-day")
    public WebElementFacade employmentPaidDateDay;

    @FindBy(id = "EmploymentPaidDate-month")
    public WebElementFacade employmentPaidDateMonth;

    @FindBy(id = "EmploymentPaidDate-year")
    public WebElementFacade employmentPaidDateYear;

    @FindBy(id = "EmploymentAdditionalAmountPaid")
    public WebElementFacade employmentAdditionalAmountPaid;

    @FindBy(id = "EmploymentAdditionalAmountPaidDate-day")
    public WebElementFacade employmentAdditionalPaidDateDay;

    @FindBy(id = "EmploymentAdditionalAmountPaidDate-month")
    public WebElementFacade employmentAdditionalPaidDateMonth;

    @FindBy(id = "EmploymentAdditionalAmountPaidDate-year")
    public WebElementFacade employmentAdditionalPaidDateYear;

    @FindBy(id = "TaxCreditType")
    public WebElementFacade childBenefitAndTaxCreditPaymentType;

    @FindBy(id = "TaxCreditAward")
    public WebElementFacade childBenefitAndTaxCreditAmountAwarded;

    @FindBy(id = "TaxCreditPaid")
    public WebElementFacade childBenefitAndTaxCreditAmountPaid;

    @FindBy(id = "TaxCreditPaidDate-day")
    public WebElementFacade childBenefitAndTaxCreditPaidDateDay;

    @FindBy(id = "TaxCreditPaidDate-month")
    public WebElementFacade childBenefitAndTaxCreditPaidDateMonth;

    @FindBy(id = "TaxCreditPaidDate-year")
    public WebElementFacade childBenefitAndTaxCreditPaidDateYear;

    @FindBy(id = "BenefitsType")
    public WebElementFacade benefitsPaymentType;

    @FindBy(id = "BenefitsDwp")
    public WebElementFacade benefitsPaidDWP;

    @FindBy(id = "BenefitsHmrc")
    public WebElementFacade benefitsPaidHMRC;

    @FindBy(id = "BenefitsAward")
    public WebElementFacade benefitsAmountAwarded;

    @FindBy(id = "BenefitsPaid")
    public WebElementFacade benefitsAmountPaid;

    @FindBy(id = "BenefitsPaidDate-day")
    public WebElementFacade benefitsPaidDateDay;

    @FindBy(id = "BenefitsPaidDate-month")
    public WebElementFacade benefitsPaidDateMonth;

    @FindBy(id = "BenefitsPaidDate-year")
    public WebElementFacade benefitsPaidDateYear;

    @FindBy(id = "HousingType")
    public WebElementFacade housingPaymentType;

    @FindBy(id = "HousingAward")
    public WebElementFacade housingAmountAwarded;

    @FindBy(id = "HousingPaid")
    public WebElementFacade housingAmountPaid;

    @FindBy(id = "HousingPaidDate-day")
    public WebElementFacade housingPaidDateDay;

    @FindBy(id = "HousingPaidDate-month")
    public WebElementFacade housingPaidDateMonth;

    @FindBy(id = "HousingPaidDate-year")
    public WebElementFacade housingPaidDateYear;

    @FindBy(id = "HealthType")
    public WebElementFacade healthPaymentType;

    @FindBy(id = "HealthNhs")
    public WebElementFacade healthNHS;

    @FindBy(id = "HealthPrivate")
    public WebElementFacade healthPrivateHealthCost;

    @FindBy(id = "HealthAward")
    public WebElementFacade healthAmountAwarded;

    @FindBy(id = "HealthPaid")
    public WebElementFacade healthAmountPaid;

    @FindBy(id = "HealthPaidDate-day")
    public WebElementFacade healthPaidDateDay;

    @FindBy(id = "HealthPaidDate-month")
    public WebElementFacade healthPaidDateMonth;

    @FindBy(id = "HealthPaidDate-year")
    public WebElementFacade healthPaidDateYear;

    @FindBy(id = "BankingType")
    public WebElementFacade bankingPaymentType;

    @FindBy(id = "BankingAward")
    public WebElementFacade bankingAmountAwarded;

    @FindBy(id = "BankingPaid")
    private WebElementFacade bankingAmountPaid;

    @FindBy(id = "BankingPaidDate-day")
    public WebElementFacade bankingPaidDateDay;

    @FindBy(id = "BankingPaidDate-month")
    public WebElementFacade bankingPaidDateMonth;

    @FindBy(id = "BankingPaidDate-year")
    public WebElementFacade bankingPaidDateYear;

    @FindBy(id = "EducationType")
    public WebElementFacade educationPaymentType;

    @FindBy(id = "EducationAward")
    public WebElementFacade educationAmountAwarded;

    @FindBy(id = "EducationPaid")
    public WebElementFacade educationAmountPaid;

    @FindBy(id = "EducationPaidDate-day")
    public WebElementFacade educationPaidDateDay;

    @FindBy(id = "EducationPaidDate-month")
    public WebElementFacade educationPaidDateMonth;

    @FindBy(id = "EducationPaidDate-year")
    public WebElementFacade educationPaidDateYear;

    @FindBy(id = "HomelessType")
    public WebElementFacade homelessnessPaymentType;

    @FindBy(id = "HomelessAward")
    public WebElementFacade homelessnessAmountAwarded;

    @FindBy(id = "HomelessPaid")
    public WebElementFacade homelessnessAmountPaid;

    @FindBy(id = "HomelessPaidDate-day")
    public WebElementFacade homelessnessPaidDateDay;

    @FindBy(id = "HomelessPaidDate-month")
    public WebElementFacade homelessnessPaidDateMonth;

    @FindBy(id = "HomelessPaidDate-year")
    public WebElementFacade homelessnessPaidDateYear;

    @FindBy(id = "HomelessAdditionalAmountPaid")
    public WebElementFacade homelessnessAdditionalAmountPaid;

    @FindBy(id = "HomelessAdditionalAmountPaidDate-day")
    public WebElementFacade homelessnessAdditionalPaidDateDay;

    @FindBy(id = "HomelessAdditionalAmountPaidDate-month")
    public WebElementFacade homelessnessAdditionalPaidDateMonth;

    @FindBy(id = "HomelessAdditionalAmountPaidDate-year")
    public WebElementFacade homelessnessAdditionalPaidDateYear;

    @FindBy(id = "ImpactPrelimOffer")
    public WebElementFacade impactPreliminaryOfferTextField;

    @FindBy(id = "ImpactPrelimSentDate-day")
    public WebElementFacade impactPreliminarySentDateDayField;

    @FindBy(id = "ImpactPrelimSentDate-month")
    public WebElementFacade impactPreliminarySentDateMonthField;

    @FindBy(id = "ImpactPrelimSentDate-year")
    public WebElementFacade impactPreliminarySentDateYearField;

    @FindBy(id = "ImpactPrelimOfferPaidAmount")
    public WebElementFacade impactPreliminaryOfferPaidAmountTextField;

    @FindBy(id = "ImpactPrelimPaidDate-day")
    public WebElementFacade impactPreliminaryPaidDateDayField;

    @FindBy(id = "ImpactPrelimPaidDate-month")
    public WebElementFacade impactPreliminaryPaidDateMonthField;

    @FindBy(id = "ImpactPrelimPaidDate-year")
    public WebElementFacade impactPreliminaryPaidDateYearField;

    @FindBy(id = "ImpactPrelimConsideredDate-day")
    public WebElementFacade impactPreliminaryConsideredDateDayField;

    @FindBy(id = "ImpactPrelimConsideredDate-month")
    public WebElementFacade impactPreliminaryConsideredDateMonthField;

    @FindBy(id = "ImpactPrelimConsideredDate-year")
    public WebElementFacade impactPreliminaryConsideredDateYearField;

    @FindBy(id = "ImpactType")
    public WebElementFacade impactOnDailyLifePaymentType;

    @FindBy(id = "ImpactAward")
    public WebElementFacade impactOnDailyLifeAmountAwarded;

    @FindBy(id = "ImpactPaid")
    public WebElementFacade impactOnDailyLifeAmountPaid;

    @FindBy(id = "ImpactPaidDate-day")
    public WebElementFacade impactPaidDateDay;

    @FindBy(id = "ImpactPaidDate-month")
    public WebElementFacade impactPaidDateMonth;

    @FindBy(id = "ImpactPaidDate-year")
    public WebElementFacade impactPaidDateYear;

    @FindBy(id = "ImpactAdditionalAmountPaid")
    public WebElementFacade impactAdditionalAmountPaid;

    @FindBy(id = "ImpactAdditionalAmountPaidDate-day")
    public WebElementFacade impactAdditionalPaidDateDay;

    @FindBy(id = "ImpactAdditionalAmountPaidDate-month")
    public WebElementFacade impactAdditionalPaidDateMonth;

    @FindBy(id = "ImpactAdditionalAmountPaidDate-year")
    public WebElementFacade impactAdditionalPaidDateYear;

    @FindBy(id = "DiscretionType")
    public WebElementFacade discretionaryPaymentType;

    @FindBy(id = "DiscretionAward")
    public WebElementFacade discretionaryAmountAwarded;

    @FindBy(id = "DiscretionPaid")
    public WebElementFacade discretionaryAmountPaid;

    @FindBy(id = "DiscretionPaidDate-day")
    public WebElementFacade discretionPaidDateDay;

    @FindBy(id = "DiscretionPaidDate-month")
    public WebElementFacade discretionPaidDateMonth;

    @FindBy(id = "DiscretionPaidDate-year")
    public WebElementFacade discretionPaidDateYear;

    @FindBy(id = "DrivingType")
    public WebElementFacade drivingLicencePaymentType;

    @FindBy(id = "DrivingAward")
    public WebElementFacade drivingLicenceAmountAwarded;

    @FindBy(id = "DrivingPaid")
    public WebElementFacade drivingLicenceAmountPaid;

    @FindBy(id = "DrivingPaidDate-day")
    public WebElementFacade drivingPaidDateDay;

    @FindBy(id = "DrivingPaidDate-month")
    public WebElementFacade drivingPaidDateMonth;

    @FindBy(id = "DrivingPaidDate-year")
    public WebElementFacade drivingPaidDateYear;

    @FindBy(id = "UepAward")
    public WebElementFacade urgentExceptionalPaymentAwardAmount;

    @FindBy(id = "UepDeduct")
    public WebElementFacade urgentExceptionalPaymentDeductedAmount;

    @FindBy(id = "LivingType")
    public WebElementFacade livingCostsPaymentType;

    @FindBy(id = "LivingAward")
    public WebElementFacade livingCostsAmountAwarded;

    @FindBy(id = "LivingPaid")
    public WebElementFacade livingCostsAmountPaid;

    @FindBy(id = "LivingPaidDate-day")
    public WebElementFacade livingCostsPaidDateDay;

    @FindBy(id = "LivingPaidDate-month")
    public WebElementFacade livingCostsPaidDateMonth;

    @FindBy(id = "LivingPaidDate-year")
    public WebElementFacade livingCostsPaidDateYear;

    @FindBy(id = "TotalAward")
    public WebElementFacade totalAwardedBox;

    @FindBy(id = "TotalPaid")
    public WebElementFacade totalPaidBox;

    @FindBy(xpath = "//*[@id='main-content']/div/div[1]/form/input")
    public WebElementFacade saveChangesButton;

    @FindBy(xpath = ".//label[contains(text(), 'Save changes')]")
    public WebElementFacade saveChangesRadioButton;


    public void iClickShowHideDetails(String caseCategory) {
        WebElementFacade showDetailsHypertext = null;
        switch (caseCategory.toUpperCase()) {
            case "IMMIGRATION AND LEGAL FEES":
                showDetailsHypertext = findBy("//label[contains(text(), 'Immigration and legal fees')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "DETENTION DEPORTATION AND REMOVAL":
                showDetailsHypertext = findBy("//label[contains(text(), 'Detention deportation and removal')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "EMPLOYMENT":
                showDetailsHypertext = findBy("//label[contains(text(), 'Employment')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "CHILD BENEFIT AND TAX CREDIT":
                showDetailsHypertext = findBy("//label[contains(text(), 'Child benefit')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "BENEFITS":
                showDetailsHypertext = findBy("//label[contains(text(), 'Benefits')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "HOUSING":
                showDetailsHypertext = findBy("//label[contains(text(), 'Housing')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "HEALTH":
                showDetailsHypertext = findBy("//label[contains(text(), 'Health')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "BANKING":
                showDetailsHypertext = findBy("//label[contains(text(), 'Banking')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "EDUCATION":
                showDetailsHypertext = findBy("//label[contains(text(), 'Education')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "HOMELESSNESS":
                showDetailsHypertext = findBy("//label[contains(text(), 'Homelessness')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "IMPACT ON DAILY LIFE":
                showDetailsHypertext = findBy("//label[contains(text(), 'Impact on daily life')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "DISCRETIONARY":
                showDetailsHypertext = findBy("//label[contains(text(), 'Discretionary')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "DRIVING LICENCE":
                showDetailsHypertext = findBy("//label[contains(text(), 'Driving licence')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            case "URGENT EXCEPTIONAL PAYMENT":
                showDetailsHypertext = findBy("//label[contains(text(), 'Urgent Exceptional Payment')]//ancestor::div[@class='selectable-details-header']//a");
                break;
            default:
                pendingStep(caseCategory + " is not defined within iClickShowHideDetails");
        }
        clickOn(showDetailsHypertext);
    }

    public void enterPreliminaryOfferValue(int value) {
        typeInto(impactPreliminaryOfferTextField, String.valueOf(value));
        addToAmountAwardedTotal(value);
    }

    public void enterPreliminaryOfferPaidAmount(int value) {
        typeInto(impactPreliminaryOfferPaidAmountTextField, String.valueOf(value));
        addToAmountPaidTotal(value);
    }

    public void enterPreliminaryOfferSentDate(String date) {
        typeIntoDateFields(impactPreliminarySentDateDayField, impactPreliminarySentDateMonthField, impactPreliminarySentDateYearField, date);
    }

    public void enterPreliminaryOfferPaidDate(String date) {
        typeIntoDateFields(impactPreliminaryPaidDateDayField, impactPreliminaryPaidDateMonthField, impactPreliminaryPaidDateYearField, date);
    }

    public void enterPreliminaryOfferConsideredButNotAwardedDate(String date) {
        typeIntoDateFields(impactPreliminaryConsideredDateDayField, impactPreliminaryConsideredDateMonthField, impactPreliminaryConsideredDateYearField, date);
    }

    public void immigrationAndLegalFeeInputs(int paymentType, int amountAwarded, int amountPaid, int
            additionalAmountPaid, String paymentDate, String additionalPaymentDate) {

        immigrationAndLegalFeePaymentType.selectByIndex(paymentType);

        typeInto(immigrationAndLegalFeeAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(immigrationAndLegalFeeAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(immigrationAndLegalFeePaidDateDay, immigrationAndLegalFeePaidDateMonth, immigrationAndLegalFeePaidDateYear, paymentDate);

        typeInto(immigrationAndLegalFeeAdditionalAmountPaid, String.valueOf(additionalAmountPaid));
        addToAmountPaidTotal(additionalAmountPaid);

        typeIntoDateFields(immigrationAndLegalFeeAdditionalPaidDateDay, immigrationAndLegalFeeAdditionalPaidDateMonth, immigrationAndLegalFeePaidDateYear, additionalPaymentDate);
    }

    public void detentionDeportationAndRemovalInputs(int paymentType, int amountAwarded, int amountPaid, String date) {
        detentionDeportationAndRemovalPaymentType.selectByIndex(paymentType);

        typeInto(detentionDeportationAndRemovalAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(detentionDeportationAndRemovalAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(detentionDeportationAndRemovalPaidDateDay, detentionDeportationAndRemovalPaidDateMonth, detentionDeportationAndRemovalPaidDateYear, date);
    }

    public void employmentInputs(int paymentType, int amountAwarded, int amountPaid, int additionalAmountPaid, String paymentDate, String additionalPaymentDate) {
        employmentPaymentType.selectByIndex(paymentType);

        typeInto(employmentAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(employmentAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(employmentPaidDateDay, employmentPaidDateMonth, employmentPaidDateYear, paymentDate);

        typeInto(employmentAdditionalAmountPaid, String.valueOf(additionalAmountPaid));
        addToAmountPaidTotal(additionalAmountPaid);

        typeIntoDateFields(employmentAdditionalPaidDateDay, employmentAdditionalPaidDateMonth, employmentAdditionalPaidDateYear, additionalPaymentDate);
    }

    public void childBenefitAndTaxCreditPaymentType(int paymentType, int amountAwarded, int amountPaid, String date) {
        childBenefitAndTaxCreditPaymentType.selectByIndex(paymentType);

        typeInto(childBenefitAndTaxCreditAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(childBenefitAndTaxCreditAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(childBenefitAndTaxCreditPaidDateDay, childBenefitAndTaxCreditPaidDateMonth, childBenefitAndTaxCreditPaidDateYear, date);
    }

    public void benefitsInputs(int paymentType, int benefitsDWP, int benefitsHMRC, int amountAwarded, int amountPaid, String date) {
        benefitsPaymentType.selectByIndex(paymentType);

        typeInto(benefitsPaidDWP, String.valueOf(benefitsDWP));

        typeInto(benefitsPaidHMRC, String.valueOf(benefitsHMRC));

        benefitsAmountAwarded.sendKeys(String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(benefitsAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(benefitsPaidDateDay, benefitsPaidDateMonth, benefitsPaidDateYear, date);
    }

    public void housingInputs(int paymentType, int amountAwarded, int amountPaid, String date) {
        housingPaymentType.selectByIndex(paymentType);

        typeInto(housingAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(housingAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

       typeIntoDateFields(housingPaidDateDay, housingPaidDateMonth, housingPaidDateYear, date);
    }

    public void healthInputs(int paymentType, int nhsCost, int privateHealthCost, int amountAwarded, int amountPaid, String date) {
        healthPaymentType.selectByIndex(paymentType);

        typeInto(healthNHS, String.valueOf(nhsCost));

        typeInto(healthPrivateHealthCost, String.valueOf(privateHealthCost));

        typeInto(healthAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(healthAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

       typeIntoDateFields(healthPaidDateDay, healthPaidDateMonth, healthPaidDateYear, date);
    }

    public void bankingInput(int paymentType, int amountAwarded, int amountPaid, String date) {
        bankingPaymentType.selectByIndex(paymentType);

        typeInto(bankingAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(bankingAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(bankingPaidDateDay, bankingPaidDateMonth, bankingPaidDateYear, date);
    }

    public void educationInput(int paymentType, int amountAwarded, int amountPaid, String date) {
        educationPaymentType.selectByIndex(paymentType);

        typeInto(educationAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(educationAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(educationPaidDateDay, educationPaidDateMonth, educationPaidDateYear, date);
    }

    public void homelessnessInput(int paymentType, int amountAwarded, int amountPaid, int additionalAmountPaid, String paymentDate, String additionalPaymentDate) {
        homelessnessPaymentType.selectByIndex(paymentType);

        typeInto(homelessnessAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(homelessnessAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(homelessnessPaidDateDay, homelessnessPaidDateMonth, homelessnessPaidDateYear, paymentDate);

        typeInto(homelessnessAdditionalAmountPaid, String.valueOf(additionalAmountPaid));
        addToAmountPaidTotal(additionalAmountPaid);

        typeIntoDateFields(homelessnessAdditionalPaidDateDay, homelessnessAdditionalPaidDateMonth, homelessnessAdditionalPaidDateYear,
                additionalPaymentDate);
    }

    public void impactOnDailyLifeInput(int paymentType, int amountAwarded, int amountPaid, int additionalAmountPaid,
            String paymentDate, String additionalPaymentDate) {
        impactOnDailyLifePaymentType.selectByIndex(paymentType);

        typeInto(impactOnDailyLifeAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(impactOnDailyLifeAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(impactPaidDateDay, impactPaidDateMonth, impactPaidDateYear, paymentDate);

        typeInto(impactAdditionalAmountPaid, String.valueOf(additionalAmountPaid));
        addToAmountPaidTotal(additionalAmountPaid);

        typeIntoDateFields(impactAdditionalPaidDateDay, impactAdditionalPaidDateMonth, impactAdditionalPaidDateYear, additionalPaymentDate);
    }

    public void discretionaryInput(int paymentType, int amountAwarded, int amountPaid, String date) {
        discretionaryPaymentType.selectByIndex(paymentType);

        typeInto(discretionaryAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(discretionaryAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(discretionPaidDateDay, discretionPaidDateMonth, discretionPaidDateYear, date);
    }

    public void drivingLicenceInputs(int paymentType, int amountAwarded, int amountPaid, String date) {
        drivingLicencePaymentType.selectByIndex(paymentType);

        typeInto(drivingLicenceAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(drivingLicenceAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(drivingPaidDateDay, drivingPaidDateMonth, drivingPaidDateYear, date);
    }

    public void urgentExceptionalPaymentInputs(int amountAwarded, int amountDeducted) {
        typeInto(urgentExceptionalPaymentAwardAmount, String.valueOf(amountAwarded));

        typeInto(urgentExceptionalPaymentDeductedAmount, String.valueOf(amountDeducted));
        subtractFromAmountPaidTotal(amountDeducted);
    }

    public void livingCostsInputs(int paymentType, int amountAwarded, int amountPaid, String date) {
        livingCostsPaymentType.selectByIndex(paymentType);

        typeInto(livingCostsAmountAwarded, String.valueOf(amountAwarded));
        addToAmountAwardedTotal(amountAwarded);

        typeInto(livingCostsAmountPaid, String.valueOf(amountPaid));
        addToAmountPaidTotal(amountPaid);

        typeIntoDateFields(livingCostsPaidDateDay, livingCostsPaidDateMonth, livingCostsPaidDateYear, date);
    }

    public void preliminaryOfferInputs(int offerValue, int paidValue, String dateSent, String datePaid, String dateConsidered) {
        enterPreliminaryOfferValue(offerValue);
        enterPreliminaryOfferSentDate(dateSent);
        enterPreliminaryOfferPaidAmount(paidValue);
        enterPreliminaryOfferPaidDate(datePaid);
        enterPreliminaryOfferConsideredButNotAwardedDate(dateConsidered);
    }

    public void assertThatCorrectTotalsAreDisplayed() {
        String displayedAwardValue = totalAwardedBox.getValue();
        String calculatedAwardValue = sessionVariableCalled("totalAwarded").toString();
        String displayedTotalValue = totalPaidBox.getValue();
        String calculatedTotalValue = sessionVariableCalled("totalPaid").toString();
        assertThat(displayedAwardValue.equals(calculatedAwardValue), is(true));
        assertThat(displayedTotalValue.equals(calculatedTotalValue), is(true));
    }

    public void addToAmountAwardedTotal(int addition) {
        if(sessionVariableCalled("totalAwarded") == null) {
            setSessionVariable("totalAwarded").to(0);
        }
        String currentTotal = sessionVariableCalled("totalAwarded").toString();
        int updatedTotal = Integer.parseInt(currentTotal) + addition;
        setSessionVariable("totalAwarded").to(updatedTotal);
    }

    public void subtractFromAmountAwardedTotal(int subtract) {
        String currentTotal = sessionVariableCalled("totalAwarded").toString();
        int updatedTotal = Integer.parseInt(currentTotal) - subtract;
        setSessionVariable("totalAwarded").to(updatedTotal);
    }

    public void addToAmountPaidTotal(int addition) {
        if (sessionVariableCalled("totalPaid") == null) {
            setSessionVariable("totalPaid").to(0);
        }
        String currentTotal = sessionVariableCalled("totalPaid").toString();
        int updatedTotal = Integer.parseInt(currentTotal) + addition;
        setSessionVariable("totalPaid").to(updatedTotal);
    }

    public void subtractFromAmountPaidTotal(int subtract) {
        String currentTotal = sessionVariableCalled("totalPaid").toString();
        int updatedTotal = Integer.parseInt(currentTotal) - subtract;
        setSessionVariable("totalPaid").to(updatedTotal);
    }

    public void saveChanges() {
        saveChangesRadioButton.click();
        saveChangesButton.click();
        
    }
}
