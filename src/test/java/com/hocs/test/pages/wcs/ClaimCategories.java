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

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesType")
    public WebElementFacade immigrationAndLegalFeePaymentType;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesAward")
    public WebElementFacade immigrationAndLegalFeeAmountAwarded;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesPaid")
    public WebElementFacade immigrationAndLegalFeeAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesPaidDate-day")
    public WebElementFacade immigrationAndLegalFeePaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesPaidDate-month")
    public WebElementFacade immigrationAndLegalFeePaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesPaidDate-year")
    public WebElementFacade immigrationAndLegalFeePaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesAdditionalAmountPaid")
    public WebElementFacade immigrationAndLegalFeeAdditionalAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesAdditionalAmountPaidDate-day")
    public WebElementFacade immigrationAndLegalFeeAdditionalPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesAdditionalAmountPaidDate-month")
    public WebElementFacade immigrationAndLegalFeeAdditionalPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "LegalFeesAdditionalAmountPaidDate-year")
    public WebElementFacade immigrationAndLegalFeeAdditionalPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "RemovalType")
    public WebElementFacade detentionDeportationAndRemovalPaymentType;

    @FindBy(timeoutInSeconds = "10", id = "RemovalAward")
    public WebElementFacade detentionDeportationAndRemovalAmountAwarded;

    @FindBy(timeoutInSeconds = "10", id = "RemovalPaid")
    public WebElementFacade detentionDeportationAndRemovalAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "RemovalPaidDate-day")
    public WebElementFacade detentionDeportationAndRemovalPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "RemovalPaidDate-month")
    public WebElementFacade detentionDeportationAndRemovalPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "RemovalPaidDate-year")
    public WebElementFacade detentionDeportationAndRemovalPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentType")
    public WebElementFacade employmentPaymentType;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentAward")
    public WebElementFacade employmentAmountAwarded;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentPaid")
    public WebElementFacade employmentAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentPaidDate-day")
    public WebElementFacade employmentPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentPaidDate-month")
    public WebElementFacade employmentPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentPaidDate-year")
    public WebElementFacade employmentPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentAdditionalAmountPaid")
    public WebElementFacade employmentAdditionalAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentAdditionalAmountPaidDate-day")
    public WebElementFacade employmentAdditionalPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentAdditionalAmountPaidDate-month")
    public WebElementFacade employmentAdditionalPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "EmploymentAdditionalAmountPaidDate-year")
    public WebElementFacade employmentAdditionalPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "TaxCreditType")
    public WebElementFacade childBenefitAndTaxCreditPaymentType;

    @FindBy(timeoutInSeconds = "10", id = "TaxCreditAward")
    public WebElementFacade childBenefitAndTaxCreditAmountAwarded;

    @FindBy(timeoutInSeconds = "10", id = "TaxCreditPaid")
    public WebElementFacade childBenefitAndTaxCreditAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "TaxCreditPaidDate-day")
    public WebElementFacade childBenefitAndTaxCreditPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "TaxCreditPaidDate-month")
    public WebElementFacade childBenefitAndTaxCreditPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "TaxCreditPaidDate-year")
    public WebElementFacade childBenefitAndTaxCreditPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsType")
    public WebElementFacade benefitsPaymentType;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsDwp")
    public WebElementFacade benefitsPaidDWP;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsHmrc")
    public WebElementFacade benefitsPaidHMRC;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsAward")
    public WebElementFacade benefitsAmountAwarded;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsPaid")
    public WebElementFacade benefitsAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsPaidDate-day")
    public WebElementFacade benefitsPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsPaidDate-month")
    public WebElementFacade benefitsPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "BenefitsPaidDate-year")
    public WebElementFacade benefitsPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "HousingType")
    public WebElementFacade housingPaymentType;

    @FindBy(timeoutInSeconds = "10", id = "HousingAward")
    public WebElementFacade housingAmountAwarded;

    @FindBy(timeoutInSeconds = "10", id = "HousingPaid")
    public WebElementFacade housingAmountPaid;

    @FindBy(timeoutInSeconds = "10", id = "HousingPaidDate-day")
    public WebElementFacade housingPaidDateDay;

    @FindBy(timeoutInSeconds = "10", id = "HousingPaidDate-month")
    public WebElementFacade housingPaidDateMonth;

    @FindBy(timeoutInSeconds = "10", id = "HousingPaidDate-year")
    public WebElementFacade housingPaidDateYear;

    @FindBy(timeoutInSeconds = "10", id = "HealthType")
    public WebElementFacade healthPaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "HealthNhs")
    public WebElementFacade healthNHS;

    @FindBy(timeoutInSeconds = "10",  id = "HealthPrivate")
    public WebElementFacade healthPrivateHealthCost;

    @FindBy(timeoutInSeconds = "10",  id = "HealthAward")
    public WebElementFacade healthAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "HealthPaid")
    public WebElementFacade healthAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "HealthPaidDate-day")
    public WebElementFacade healthPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "HealthPaidDate-month")
    public WebElementFacade healthPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "HealthPaidDate-year")
    public WebElementFacade healthPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "BankingType")
    public WebElementFacade bankingPaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "BankingAward")
    public WebElementFacade bankingAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "BankingPaid")
    private WebElementFacade bankingAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "BankingPaidDate-day")
    public WebElementFacade bankingPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "BankingPaidDate-month")
    public WebElementFacade bankingPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "BankingPaidDate-year")
    public WebElementFacade bankingPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "EducationType")
    public WebElementFacade educationPaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "EducationAward")
    public WebElementFacade educationAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "EducationPaid")
    public WebElementFacade educationAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "EducationPaidDate-day")
    public WebElementFacade educationPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "EducationPaidDate-month")
    public WebElementFacade educationPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "EducationPaidDate-year")
    public WebElementFacade educationPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessType")
    public WebElementFacade homelessnessPaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessAward")
    public WebElementFacade homelessnessAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessPaid")
    public WebElementFacade homelessnessAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessPaidDate-day")
    public WebElementFacade homelessnessPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessPaidDate-month")
    public WebElementFacade homelessnessPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessPaidDate-year")
    public WebElementFacade homelessnessPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessAdditionalAmountPaid")
    public WebElementFacade homelessnessAdditionalAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessAdditionalAmountPaidDate-day")
    public WebElementFacade homelessnessAdditionalPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessAdditionalAmountPaidDate-month")
    public WebElementFacade homelessnessAdditionalPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "HomelessAdditionalAmountPaidDate-year")
    public WebElementFacade homelessnessAdditionalPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimOffer")
    public WebElementFacade impactPreliminaryOfferTextField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimSentDate-day")
    public WebElementFacade impactPreliminarySentDateDayField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimSentDate-month")
    public WebElementFacade impactPreliminarySentDateMonthField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimSentDate-year")
    public WebElementFacade impactPreliminarySentDateYearField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimOfferPaidAmount")
    public WebElementFacade impactPreliminaryOfferPaidAmountTextField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimPaidDate-day")
    public WebElementFacade impactPreliminaryPaidDateDayField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimPaidDate-month")
    public WebElementFacade impactPreliminaryPaidDateMonthField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimPaidDate-year")
    public WebElementFacade impactPreliminaryPaidDateYearField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimConsideredDate-day")
    public WebElementFacade impactPreliminaryConsideredDateDayField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimConsideredDate-month")
    public WebElementFacade impactPreliminaryConsideredDateMonthField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPrelimConsideredDate-year")
    public WebElementFacade impactPreliminaryConsideredDateYearField;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactType")
    public WebElementFacade impactOnDailyLifePaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactAward")
    public WebElementFacade impactOnDailyLifeAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPaid")
    public WebElementFacade impactOnDailyLifeAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPaidDate-day")
    public WebElementFacade impactPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPaidDate-month")
    public WebElementFacade impactPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactPaidDate-year")
    public WebElementFacade impactPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactAdditionalAmountPaid")
    public WebElementFacade impactAdditionalAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactAdditionalAmountPaidDate-day")
    public WebElementFacade impactAdditionalPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactAdditionalAmountPaidDate-month")
    public WebElementFacade impactAdditionalPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "ImpactAdditionalAmountPaidDate-year")
    public WebElementFacade impactAdditionalPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "DiscretionType")
    public WebElementFacade discretionaryPaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "DiscretionAward")
    public WebElementFacade discretionaryAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "DiscretionPaid")
    public WebElementFacade discretionaryAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "DiscretionPaidDate-day")
    public WebElementFacade discretionPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "DiscretionPaidDate-month")
    public WebElementFacade discretionPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "DiscretionPaidDate-year")
    public WebElementFacade discretionPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "DrivingType")
    public WebElementFacade drivingLicencePaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "DrivingAward")
    public WebElementFacade drivingLicenceAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "DrivingPaid")
    public WebElementFacade drivingLicenceAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "DrivingPaidDate-day")
    public WebElementFacade drivingPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "DrivingPaidDate-month")
    public WebElementFacade drivingPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "DrivingPaidDate-year")
    public WebElementFacade drivingPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "UepAward")
    public WebElementFacade urgentExceptionalPaymentAwardAmount;

    @FindBy(timeoutInSeconds = "10",  id = "UepDeduct")
    public WebElementFacade urgentExceptionalPaymentDeductedAmount;

    @FindBy(timeoutInSeconds = "10",  id = "LivingType")
    public WebElementFacade livingCostsPaymentType;

    @FindBy(timeoutInSeconds = "10",  id = "LivingAward")
    public WebElementFacade livingCostsAmountAwarded;

    @FindBy(timeoutInSeconds = "10",  id = "LivingPaid")
    public WebElementFacade livingCostsAmountPaid;

    @FindBy(timeoutInSeconds = "10",  id = "LivingPaidDate-day")
    public WebElementFacade livingCostsPaidDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "LivingPaidDate-month")
    public WebElementFacade livingCostsPaidDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "LivingPaidDate-year")
    public WebElementFacade livingCostsPaidDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "TotalAward")
    public WebElementFacade totalAwardedBox;

    @FindBy(timeoutInSeconds = "10",  id = "TotalPaid")
    public WebElementFacade totalPaidBox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//*[@id='main-content']/div/div[1]/form/input")
    public WebElementFacade saveChangesButton;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Save changes')]")
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
        clickOn(showDetailsHypertext.withTimeoutOf(Duration.ofSeconds(10)));
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
        waitABit(750);
    }
}
