package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ClaimSchema extends BasePage {

    Casework casework;

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='ReceivedDate-day']")
    public WebElementFacade receivedDateDayTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='ReceivedDate-month']")
    public WebElementFacade receivedDateMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='ReceivedDate-year']")
    public WebElementFacade receivedDateYearTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimType-Primary']")
    public WebElementFacade typeOfClaimPrimaryRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimType-Family']")
    public WebElementFacade typeOfClaimFamilyRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimType-Estate']")
    public WebElementFacade typeOfClaimEstateRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='FormSubmitted-Post']")
    public WebElementFacade howWasFormSubmittedPostRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='FormSubmitted-Email']")
    public WebElementFacade howWasFormSubmittedEmailRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='IsCitizensAdvice-Yes']")
    public WebElementFacade formSentThroughCitizensAdviceYesRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='IsCitizensAdvice-No']")
    public WebElementFacade formSentThroughCitizensAdviceNoRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimedFrom-In-Country']")
    public WebElementFacade whereIsClaimFromInCountryRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimedFrom-Out-Country']")
    public WebElementFacade whereIsClaimFromOutCountryRadioButton;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedClaims")
    public WebElementFacade linkedClaimsCaseInfoTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='BFDate-day']")
    public WebElementFacade broughtForwardDateDayTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='BFDate-month']")
    public WebElementFacade broughtForwardDateMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='BFDate-year']")
    public WebElementFacade broughtForwardDateYearTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='FullName']")
    public WebElementFacade fullNameTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='PreviousNames']")
    public WebElementFacade previousNamesTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimantGender-Male']")
    public WebElementFacade claimantGenderMaleRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='ClaimantGender-Female']")
    public WebElementFacade claimantGenderFemaleRadioButton;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='DateOfBirth-day']")
    public WebElementFacade dateOfBirthDayTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='DateOfBirth-month']")
    public WebElementFacade dateOfBirthMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='DateOfBirth-year']")
    public WebElementFacade dateOfBirthYearTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='Addr1']")
    public WebElementFacade buildingAndStreetTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='Addr2']")
    public WebElementFacade townOrCityTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='Addr3']")
    public WebElementFacade countyTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "Country")
    public WebElementFacade countryDropdown;

    @FindBy(timeoutInSeconds = "10",  css = "input[id='Postcode']")
    public WebElementFacade postcodeTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "BirthCountry")
    public WebElementFacade countryOfBirthDropdown;

    @FindBy(timeoutInSeconds = "10",  id = "Nationality")
    public WebElementFacade nationalityDropdown;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='BirthNationality']")
    public WebElementFacade birthNationalityTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "Phone")
    public WebElementFacade phoneNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "Email")
    public WebElementFacade emailTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "Passport")
    public WebElementFacade passportNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "PassportsPrev")
    public WebElementFacade expiredPassportNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "(//input[@id='NI'])[1]")
    public WebElementFacade nINumberTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "Cohort")
    public WebElementFacade eligibilityCohortDropdown;

    @FindBy(timeoutInSeconds = "10",  id = "ImmigrationStatus")
    public WebElementFacade eligibilityStatus;

    @FindBy(timeoutInSeconds = "10",  id = "ImmigrationStatusOutcome")
    public WebElementFacade eligibilityStatusOutcome;

    @FindBy(timeoutInSeconds = "10",  id = "DateOfStatusDecision-day")
    public WebElementFacade dateOfEligibilityStatusDecisionDay;

    @FindBy(timeoutInSeconds = "10",  id = "DateOfStatusDecision-month")
    public WebElementFacade dateOfEligibilityStatusDecisionMonth;

    @FindBy(timeoutInSeconds = "10",  id = "DateOfStatusDecision-year")
    public WebElementFacade dateOfEligibilityStatusDecisionYear;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedFullName")
    public WebElementFacade linkedPersonFullNameTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedPreviousNames")
    public WebElementFacade linkedPersonPreviousNameTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='LinkedPersonGender-Male']")
    public WebElementFacade linkedPersonGenderMale;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedPersonGender-Female")
    public WebElementFacade linkedPersonGenderFemale;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedDateOfBirth-day']")
    public WebElementFacade linkedPersonDateOfBirthDayTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedDateOfBirth-month']")
    public WebElementFacade linkedPersonDateOfBirthMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedDateOfBirth-year']")
    public WebElementFacade linkedPersonDateOfBirthYearTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedAddr1']")
    public WebElementFacade linkedPersonBuildingAndStreetTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedAddr2']")
    public WebElementFacade linkedPersonTownOrCityTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedAddr3']")
    public WebElementFacade linkedPersonCountyTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedCountry")
    public WebElementFacade linkedPersonCountryDropdown;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedPostcode']")
    public WebElementFacade linkedPersonPostcodeTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedBirthCountry")
    public WebElementFacade linkedPersonCountryOfBirthDropdown;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedNationality")
    public WebElementFacade linkedPersonNationalityDropdown;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedPhone']")
    public WebElementFacade linkedPersonPhoneNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedEmail']")
    public WebElementFacade linkedPersonEmailTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='LinkedPassport']")
    public WebElementFacade linkedPersonPassportNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedPassportsPrev")
    public WebElementFacade linkedPersonExpiredPassportNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "LinkedNI")
    public WebElementFacade linkedPersonNINumberTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='IsLinkedDeceased_Yes']")
    public WebElementFacade isLinkedPersonDeceasedCheckbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedCapacity")
    public WebElementFacade capacityPersonIsActingInTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedActDate-day")
    public WebElementFacade dateBeganActingOnBehalfOfEstateDayTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedActDate-month")
    public WebElementFacade dateBeganActingOnBehalfOfEstateMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedActDate-year")
    public WebElementFacade dateBeganActingOnBehalfOfEstateYearTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedDate-day")
    public WebElementFacade dateOfDeathDayTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedDate-month")
    public WebElementFacade dateOfDeathMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "DeceasedDate-year")
    public WebElementFacade dateOfDeathYearTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='DeceasedCertificate']")
    public WebElementFacade deathCertificateTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='DeceasedInfoReason']")
    public WebElementFacade reasonForNoDeathCertificateTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='RepName']")
    public WebElementFacade representativeNameTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepFromDate-day")
    public WebElementFacade representedFromDateDayTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepFromDate-month")
    public WebElementFacade representedFromDateMonthTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepFromDate-year")
    public WebElementFacade representedFromDateYearTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='RepOrg']")
    public WebElementFacade representativeOrganisationTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='RepEmail']")
    public WebElementFacade representativeEmailTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='RepPhone']")
    public WebElementFacade representativePhoneTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepStreet")
    public WebElementFacade representativeStreetTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepCity")
    public WebElementFacade representativeCityTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepCounty")
    public WebElementFacade representativeCountyTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepCountry")
    public WebElementFacade representativeCountryDropdown;

    @FindBy(timeoutInSeconds = "10",  id = "RepPostcode")
    public WebElementFacade representativePostcodeTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "RepDetails")
    public WebElementFacade representativeOtherDetailsTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "ConsentPersonWhoPaid")
    public WebElementFacade consentPersonWhoPaidTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "ConsentAwardToBePaidTo")
    public WebElementFacade consentAwardToBePaidToTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "ConsentSignedBy")
    public WebElementFacade consentSignedByTextbox;

    @FindBy(timeoutInSeconds = "10",  id = "ConsentFormSignedDate-day")
    public WebElementFacade consentSignedByDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "ConsentFormSignedDate-month")
    public WebElementFacade consentSignedByDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "ConsentFormSignedDate-year")
    public WebElementFacade consentSignedByDateYear;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='PrevHocsRef']")
    public WebElementFacade previousHOCSReferenceTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='NHS']")
    public WebElementFacade nhsNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='DrivingLicence']")
    public WebElementFacade drivingLicenceNumberTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='CID']")
    public WebElementFacade cidTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='CRS']")
    public WebElementFacade crsTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='Warehouse']")
    public WebElementFacade warehouseTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='OtherRef']")
    public WebElementFacade otherTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='details-checkbox-IsVulnerable']")
    public WebElementFacade claimantIsVulnerableCheckbox;

    @FindBy(timeoutInSeconds = "10",  id = "VulnerableClaimReceivedDate-day")
    public WebElementFacade vulnerableReferralDateDay;

    @FindBy(timeoutInSeconds = "10",  id = "VulnerableClaimReceivedDate-month")
    public WebElementFacade vulnerableReferralDateMonth;

    @FindBy(timeoutInSeconds = "10",  id = "VulnerableClaimReceivedDate-year")
    public WebElementFacade vulnerableReferralDateYear;

    @FindBy(timeoutInSeconds = "10",  id = "VulnerableDetails")
    public WebElementFacade vulnerableClaimantDetailsTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='details-checkbox-IsHighProfile'")
    public WebElementFacade claimIsHighProfileCheckbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='IsHighProfileMP_Yes']")
    public WebElementFacade highProfileMPCheckbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='IsHighProfileMedia_Yes']")
    public WebElementFacade highProfileMediaCheckbox;

    @FindBy(timeoutInSeconds = "10",  id = "HighProfileDetails")
    public WebElementFacade highProfileClaimDetailsTextbox;

    @FindBy(timeoutInSeconds = "10",  css = "label[for='details-checkbox-IsJudicialReview']")
    public WebElementFacade vulnerableJudicialReviewCheckbox;

    @FindBy(timeoutInSeconds = "10",  id = "JudicialReviewDetails")
    public WebElementFacade vulnerableJudicialReviewDetailsTextbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Immigration and legal fees')]")
    public WebElementFacade immigrationAndLegalFeesCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Detention deportation and removal')]")
    public WebElementFacade detentionDeportationAndRemovalCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Employment')]")
    public WebElementFacade employmentCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Child benefit')]")
    public WebElementFacade childBenefitTaxCreditsCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Benefits')]")
    public WebElementFacade benefitsCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Housing')]")
    public WebElementFacade housingCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Health')]")
    public WebElementFacade healthCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Banking')]")
    public WebElementFacade bankingCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[text() = 'Driving licence']")
    public WebElementFacade drivingLicenceCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Homelessness')]")
    public WebElementFacade homelessCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Impact on daily life')]")
    public WebElementFacade impactOnDailyLifeCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Discretionary')]")
    public WebElementFacade discretionaryCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Education')]")
    public WebElementFacade educationCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//label[contains(text(), 'Urgent Exceptional Payment')]")
    public WebElementFacade urgentExceptonalPaymentCheckbox;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//div/div[position()=6]//label[contains(text(), 'Yes')]")
    public WebElementFacade formSentThroughClaimantAssistanceAdviserYesRadioButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Case info')]")
    public WebElementFacade caseInfoAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Personal details')]")
    public WebElementFacade personalDetailsAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Eligibility and status')]")
    public WebElementFacade eligibilityAndStatusAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Linked person details')]")
    public WebElementFacade linkedPersonDetailsAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Representative')]")
    public WebElementFacade representativeAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Consent details')]")
    public WebElementFacade consentDetailsAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Government records')]")
    public WebElementFacade governmentRecordsAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//button[contains(text(), 'Vulnerable or high profile')]")
    public WebElementFacade vulnerableOrHighProfileAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = ".//button[contains(text(), 'Claim categories')]")
    public WebElementFacade claimCategoriesAccordionButton;

    @FindBy(timeoutInSeconds = "10",  xpath = "//input[@id='Tier2AdjudicatorsOfficeDecision']")
    public WebElementFacade tier2AdjudicatorsOfficeDecision;

    @FindBy(timeoutInSeconds = "10",  xpath = "//fieldset[@id='Tier2AdjudicatorsOfficeDecisionDate']//label[text()='Day']/following-sibling::input")
    public WebElementFacade tier2AdjudicatorsOfficeDecisionDateDay;

    @FindBy(timeoutInSeconds = "10",  xpath = "//fieldset[@id='Tier2AdjudicatorsOfficeDecisionDate']//label[text()='Month']/following-sibling::input")
    public WebElementFacade tier2AdjudicatorOfficeDecisionDateMonth;

    @FindBy(timeoutInSeconds = "10",  xpath = "//fieldset[@id='Tier2AdjudicatorsOfficeDecisionDate']//label[text()='Year']/following-sibling::input")
    public WebElementFacade tier2AdjudicatorOfficeDecisionDateYear;


    public void expandCollapseCaseInfoSection() {
        openOrCloseAccordionSection("Case info");
    }

    public void expandCollapsePersonalDetailsSection() {
        openOrCloseAccordionSection("Personal details");
    }

    public void expandCollapseEligibilityAndStatusSection() {
        openOrCloseAccordionSection("Eligibility and status");
    }

    public void expandCollapseLinkedPersonSection() {
        openOrCloseAccordionSection("Linked person details");
    }

    public void expandCollapseRepresentativeSection() {
        openOrCloseAccordionSection("Representative");
    }

    public void expandConsentDetailsAccordion() {
        openOrCloseAccordionSection("Consent details");
    }

    public void expandCollapseGovernmentRecordsSection() {
        openOrCloseAccordionSection("Government records");
    }

    public void expandCollapseVulnerableOrHighProfileSection() {
        openOrCloseAccordionSection("Vulnerable or high profile");
    }

    public void expandCollapseClaimCategoriesSection() {
        waitABit(500);
        openOrCloseAccordionSection("Claim categories");
        waitABit(500);
    }

    private void enterReceivedDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(),"Date claim was received");
    }

    private void enterATaskForceReference() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Windrush Scheme (Task Force) reference");
    }

    public void selectASpecificTypeOfClaim(String typeOfClaim){
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(typeOfClaim,"Type of claim");
        if (typeOfClaim.equalsIgnoreCase("Estate of deceased")) {
            String selectedOption = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Has the probate pack been sent?");
            if (selectedOption.equalsIgnoreCase("Yes")) {
                recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "When was the probate pack sent?");
            }
            selectedOption = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Has the probate pack been received?");
            if (selectedOption.equalsIgnoreCase("Yes")) {
                recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "When was the probate pack received?");
            }
        }
    }

    public void selectASubmissionType(){
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("How was the form submitted?");
    }

    public void selectOptionForWasFormSentThroughClaimantAssistanceAdviser() {
        String selectedOption = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Was the form sent through Claimant Assistance Adviser?");
        if (selectedOption.equalsIgnoreCase("Yes")) {
            recordCaseData.enterTextIntoTextFieldWithHeading("Associated reference");
        }
    }

    public void selectIfClaimCameFromInOrOutOfTheCountry(){
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Where is the claim from?");
    }

    public void enterTextIntoLinkedClaimsTextBox(){
        recordCaseData.enterTextIntoTextAreaWithHeading("Linked claims");
    }

    public void enterBroughtForwardDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Brought forward");
    }

    private void selectWithClaimant(){
        recordCaseData.checkSpecificCheckbox("With claimant");
    }

    private void selectOGDsThirdParties(){
        recordCaseData.checkSpecificCheckbox("OGDs and/or third parties");
    }

    private void selectPolicy(){
        recordCaseData.checkSpecificCheckbox("Policy");
    }

    public void selectUnworkable() {
        expandCollapseCaseInfoSection();
        recordCaseData.checkSpecificCheckbox("Unworkable");
        expandCollapseCaseInfoSection();
    }

    public void completeRequiredFieldsInCaseInfoSection(){
        expandCollapseCaseInfoSection();
        enterReceivedDate();
        selectASpecificTypeOfClaim("Primary");
        selectASubmissionType();
        selectOptionForWasFormSentThroughClaimantAssistanceAdviser();
        selectIfClaimCameFromInOrOutOfTheCountry();
        expandCollapseCaseInfoSection();
    }

    public void completeCaseInfoSection(){
        expandCollapseCaseInfoSection();
        enterATaskForceReference();
        selectASpecificTypeOfClaim("Estate of deceased");
        enterTextIntoLinkedClaimsTextBox();
        enterBroughtForwardDate(getTodaysDate());
        selectWithClaimant();
        selectOGDsThirdParties();
        selectPolicy();
        expandCollapseCaseInfoSection();
    }

    public void assertCaseInfoFieldsPopulated() {
        openOrCloseAccordionSection("Case Details");
        assertThat(receivedDateDayTextbox.getValue().equals(""), is(false));
        assertThat(receivedDateMonthTextbox.getValue().equals(""), is(false));
        assertThat(receivedDateYearTextbox.getValue().equals(""), is(false));
        assertThat(linkedClaimsCaseInfoTextbox.getValue().equals(""), is(false));
    }

    public void enterNameIntoPersonalDetailsFullName(String name){
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(name, "Full name");
    }

    private void enterPersonalDetailsPreviousName() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Other/previous name or alias");
    }

    public void selectAGenderRadioButton() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    public void enterPersonalDetailsDOB(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "Date of birth");
    }

    public void enterTodaysDateInPersonalDetailsDOB() {
        enterPersonalDetailsDOB(getTodaysDate());
    }

    private void enterPersonalDetailsAddress(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Building and street");
        recordCaseData.enterTextIntoTextFieldWithHeading("Town or city");
        recordCaseData.enterTextIntoTextFieldWithHeading("County");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Country");
        recordCaseData.enterTextIntoTextFieldWithHeading("Postcode");
    }

    private void selectPersonalDetailsCountryOfBirth(){
        selectRandomOptionFromDropdownWithHeading("Country of birth");
    }

    private void selectPersonalDetailsNationality(){
        selectRandomOptionFromDropdownWithHeading("Nationality");
    }

    private void enterPersonalDetailsContactDetails(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Telephone number");
        recordCaseData.enterTextIntoTextFieldWithHeading("Email address");
    }

    private void enterPersonalDetailsPassportNumbers(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Passport number");
        recordCaseData.enterTextIntoTextAreaWithHeading("Expired passport numbers");
    }

    private void enterPersonalDetailsNationalInsuranceNumber(){
        recordCaseData.enterTextIntoTextFieldWithHeading("National Insurance number");
    }

    public void enterSpecificNationalInsuranceNumber(String niNo) {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(niNo, "National Insurance number");
    }

    public void completePersonalDetailsAccordion() {
        expandCollapsePersonalDetailsSection();
        enterNameIntoPersonalDetailsFullName("Test User");
        enterPersonalDetailsPreviousName();
        selectAGenderRadioButton();
        enterTodaysDateInPersonalDetailsDOB();
        enterPersonalDetailsAddress();
        selectPersonalDetailsCountryOfBirth();
        selectPersonalDetailsNationality();
        enterPersonalDetailsContactDetails();
        enterPersonalDetailsPassportNumbers();
        enterPersonalDetailsNationalInsuranceNumber();
        expandCollapsePersonalDetailsSection();
    }

    public void assertThatPersonalDetailsFieldsPopulated() {
        assertThat(fullNameTextbox.getValue().equals(""), is(false));
        assertThat(previousNamesTextbox.getValue().equals(""), is(false));
        assertThat(dateOfBirthDayTextbox.getValue().equals(""), is(false));
        assertThat(dateOfBirthMonthTextbox.getValue().equals(""), is(false));
        assertThat(dateOfBirthYearTextbox.getValue().equals(""), is(false));
        assertThat(buildingAndStreetTextbox.getValue().equals(""), is(false));
        assertThat(townOrCityTextbox.getValue().equals(""), is(false));
        assertThat(countyTextbox.getValue().equals(""), is(false));
        assertThat(countryDropdown.getValue().equals(""), is(false));
        assertThat(postcodeTextbox.getValue().equals(""), is(false));
    }

    private void selectAnEligibilityCohort(){
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Eligibility cohort");
    }

    public void selectEligibilityStatus() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Status");
    }

    public void selectEligibilityStatusOutcome() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Status outcome");
    }

    public void selectDateOfEligibilityStatusDecision() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of status decision");
    }

    public void completeEligibilityAccordion() {
        expandCollapseEligibilityAndStatusSection();
        selectAnEligibilityCohort();
        selectEligibilityStatus();
        selectEligibilityStatusOutcome();
        selectDateOfEligibilityStatusDecision();
        expandCollapseEligibilityAndStatusSection();
    }

    public void assertThatEligibilityFieldsPopulated() {
        assertThat(eligibilityCohortDropdown.getText().equals(""), is(false));
        assertThat(eligibilityStatus.getText().equals(""), is(false));
        assertThat(eligibilityStatusOutcome.getText().equals(""), is(false));
        assertThat(dateOfEligibilityStatusDecisionDay.getValue().equals(""), is(false));
        assertThat(dateOfEligibilityStatusDecisionMonth.getValue().equals(""), is(false));
        assertThat(dateOfEligibilityStatusDecisionYear.getValue().equals(""), is(false));
    }

    private void enterNameIntoLinkedPersonFullName(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Full name");
    }

    private void enterLinkedPersonPreviousName() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous name or alias");
    }

    public void enterLinkedPersonGender() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Gender");
    }

    private void enterLinkedPersonDOB() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of birth");
    }

    private void enterLinkedPersonAddress(){
        enterTextIntoTextFieldWithHeading("Building and street");
        enterTextIntoTextFieldWithHeading("Town or city");
        enterTextIntoTextFieldWithHeading("County");
        selectRandomOptionFromDropdownWithHeading("Country");
        enterTextIntoTextFieldWithHeading("Postcode");
    }

    private void selectLinkedPersonCountryOfBirth(){
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Country of birth");
    }

    private void enterLinkedPersonNationality(){
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Nationality");
    }

    private void enterLinkedPersonContactDetails(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Telephone number");
        recordCaseData.enterTextIntoTextFieldWithHeading("Email address");
    }

    private void enterLinkedPersonPassportNumbers(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Passport number");
        recordCaseData.enterTextIntoTextAreaWithHeading("Previous passport numbers");
    }

    private void enterLinkedPersonNationalInsuranceNumber(){
        recordCaseData.enterTextIntoTextFieldWithHeading("National Insurance number");
    }

    private void selectLinkedPersonIsDeceased(){
        recordCaseData.checkSpecificCheckbox("Linked person is deceased");
    }

    private void enterLinkedPersonCapacity(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Capacity person is acting in");
    }

    private void enterDateLinkedPersonBeganActingOnBehalf(){
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date began acting on behalf of estate");
    }

    private void enterLinkedPersonDateOfDeath() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date of death");
    }

    private void enterLinkedPersonDeathCertificateNumber(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Death Certificate number");
    }

    private void enterLinkedPersonReasonForNoDeathCertificate(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Reason for no death certificate details");
    }

    public void completeLinkedPersonSection(){
        expandCollapseLinkedPersonSection();
        enterNameIntoLinkedPersonFullName();
        enterLinkedPersonPreviousName();
        enterLinkedPersonGender();
        enterLinkedPersonDOB();
        enterLinkedPersonAddress();
        selectLinkedPersonCountryOfBirth();
        enterLinkedPersonNationality();
        enterLinkedPersonContactDetails();
        enterLinkedPersonPassportNumbers();
        enterLinkedPersonNationalInsuranceNumber();
        selectLinkedPersonIsDeceased();
        enterLinkedPersonCapacity();
        enterDateLinkedPersonBeganActingOnBehalf();
        enterLinkedPersonDateOfDeath();
        enterLinkedPersonDeathCertificateNumber();
        enterLinkedPersonReasonForNoDeathCertificate();
        expandCollapseLinkedPersonSection();
    }

    public void assertLinkedPersonFieldsPopulated() {
        assertThat(linkedPersonFullNameTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonPreviousNameTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonDateOfBirthDayTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonDateOfBirthMonthTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonDateOfBirthYearTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonBuildingAndStreetTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonTownOrCityTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonCountyTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonCountryDropdown.getValue().equals(""), is(false));
        assertThat(linkedPersonPostcodeTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonCountryOfBirthDropdown.getValue().equals(""), is(false));
        assertThat(linkedPersonNationalityDropdown.getValue().equals(""), is(false));
        assertThat(linkedPersonPhoneNumberTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonEmailTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonPassportNumberTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonExpiredPassportNumberTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonPreviousNameTextbox.getValue().equals(""), is(false));
        assertThat(linkedPersonNINumberTextbox.getValue().equals(""), is(false));
        assertThat(capacityPersonIsActingInTextbox.getValue().equals(""), is(false));
        assertThat(dateBeganActingOnBehalfOfEstateDayTextbox.getValue().equals(""), is(false));
        assertThat(dateBeganActingOnBehalfOfEstateMonthTextbox.getValue().equals(""), is(false));
        assertThat(dateBeganActingOnBehalfOfEstateYearTextbox.getValue().equals(""), is(false));
        assertThat(dateOfDeathDayTextbox.getValue().equals(""), is(false));
        assertThat(dateOfDeathMonthTextbox.getValue().equals(""), is(false));
        assertThat(dateOfDeathYearTextbox.getValue().equals(""), is(false));
        assertThat(deathCertificateTextbox.getValue().equals(""), is(false));
        assertThat(reasonForNoDeathCertificateTextbox.getValue().equals(""), is(false));
    }

    private void enterNameOfRepresentative(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Name of representative");
    }

    public void enterRepresentedFromDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Represented from date");
    }
    
    private void enterOrganisation() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Organisation");
    }
    
    private void enterRepresentativeContactDetails(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Representative email address");
        recordCaseData.enterTextIntoTextFieldWithHeading("Representative phone number");
    }

    public void enterRepresentativeAddress() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Building and street");
        recordCaseData.enterTextIntoTextFieldWithHeading("Town or city");
        recordCaseData.enterTextIntoTextFieldWithHeading("County");
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Country");
        recordCaseData.enterTextIntoTextFieldWithHeading("Postcode");
    }
    
    private void enterOtherRepresentativeDetails(){
        recordCaseData.enterTextIntoTextAreaWithHeading("Other representative details");
    }
    
    public void completeRepresentativeSection(){
        expandCollapseRepresentativeSection();
        enterNameOfRepresentative();
        enterRepresentedFromDate();
        enterOrganisation();
        enterRepresentativeContactDetails();
        enterRepresentativeAddress();
        enterOtherRepresentativeDetails();
        expandCollapseRepresentativeSection();
    }

    public void assertThatRepresentativeFieldsPopulated() {
        assertThat(representativeNameTextbox.getValue().equals(""), is(false));
        assertThat(representedFromDateDayTextbox.getValue().equals(""), is(false));
        assertThat(representedFromDateMonthTextbox.getValue().equals(""), is(false));
        assertThat(representedFromDateYearTextbox.getValue().equals(""), is(false));
        assertThat(representativeOrganisationTextbox.getValue().equals(""), is(false));
        assertThat(representativePhoneTextbox.getValue().equals(""), is(false));
        assertThat(representativeEmailTextbox.getValue().equals(""), is(false));
        assertThat(representativeStreetTextbox.getValue().equals(""), is(false));
        assertThat(representativeCityTextbox.getValue().equals(""), is(false));
        assertThat(representativeCountyTextbox.getValue().equals(""), is(false));
        assertThat(representativeCountryDropdown.getValue().equals(""), is(false));
        assertThat(representativePostcodeTextbox.getValue().equals(""), is(false));
        assertThat(representativeOtherDetailsTextbox.getValue().equals(""), is(false));
    }

    public void enterConsentPersonWhoPaid() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Person who paid");
    }

    public void enterAwardToBePaid() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Award to be paid to");
    }

    public void enterConsentFormSignedBy() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Consent form signed by");
    }

    public void enterConsentFormSignedDate() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Consent form signed date");
    }

    public void completeConsentAccordion() {
        expandConsentDetailsAccordion();
        enterConsentPersonWhoPaid();
        enterAwardToBePaid();
        enterConsentFormSignedBy();
        enterConsentFormSignedDate();
        expandConsentDetailsAccordion();
    }

    public void assertThatConsentFieldsPopulated() {
        assertThat(consentPersonWhoPaidTextbox.getValue().equals(""), is(false));
        assertThat(consentAwardToBePaidToTextbox.getValue().equals(""), is(false));
        assertThat(consentSignedByTextbox.getValue().equals(""), is(false));
        assertThat(consentSignedByDateDay.getValue().equals(""), is(false));
        assertThat(consentSignedByDateMonth.getValue().equals(""), is(false));
        assertThat(consentSignedByDateYear.getValue().equals(""), is(false));
    }

    private void enterPreviousHOCSReference(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Previous HOCS reference");
    }

    public void enterSpecificPreviousHOCSReference(String ref) {
        recordCaseData.enterSpecificTextIntoTextFieldWithHeading(ref, "Previous HOCS reference");
    }

    private void enterNHSNumber() {
        recordCaseData.enterTextIntoTextFieldWithHeading("NHS number");
    }

    private void enterDrivingLicenceNumber(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Driving licence number");
    }

    private void enterCID(){
        recordCaseData.enterTextIntoTextFieldWithHeading("CID");
    }

    private void enterCRS(){
        recordCaseData.enterTextIntoTextFieldWithHeading("CRS");
    }

    private void enterWarehouse(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Warehouse");
    }

    private void enterOther(){
        recordCaseData.enterTextIntoTextFieldWithHeading("Other");
    }

    public void completeGovernmentRecordsSection(){
        expandCollapseGovernmentRecordsSection();
        enterPreviousHOCSReference();
        enterNHSNumber();
        enterDrivingLicenceNumber();
        enterCID();
        enterCRS();
        enterWarehouse();
        enterOther();
        expandCollapseGovernmentRecordsSection();
    }

    public void assertThatGovernmentRecordFieldsPopulated() {
        assertThat(previousHOCSReferenceTextbox.getValue().equals(""), is(false));
        assertThat(nhsNumberTextbox.getValue().equals(""), is(false));
        assertThat(drivingLicenceNumberTextbox.getValue().equals(""), is(false));
        assertThat(cidTextbox.getValue().equals(""), is(false));
        assertThat(crsTextbox.getValue().equals(""), is(false));
        assertThat(warehouseTextbox.getValue().equals(""), is(false));
        assertThat(otherTextbox.getValue().equals(""), is(false));
    }

    public void selectClaimantIsVulnerable(){
        recordCaseData.checkSpecificCheckbox("Claimant is vulnerable");
    }

    public void enterDateReferredToVulnerableTeam() {
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "Date referred to vulnerable person team");
    }

    private void enterVulnerableClaimantDetails(){
        recordCaseData.enterTextIntoTextAreaWithHeading("Vulnerable claimant details (including any exceptional payments)");
    }

    public void selectHighProfileClaim(){
        recordCaseData.checkSpecificCheckbox("Claim is high profile");
    }

    public void selectHighProfileMP() {
        recordCaseData.checkSpecificCheckbox("MP");
    }

    public void selectHighProfileMedia() {
        recordCaseData.checkSpecificCheckbox("Media");
    }

    public void selectHighProfileDetainedAndDeported() {
        recordCaseData.checkSpecificCheckbox("Detained and deported");
    }

    public void selectHighProfileDataBreach() {
        recordCaseData.checkSpecificCheckbox("Data Breach");
    }

    public void selectHighProfileApologyFromHomeSec() {
        recordCaseData.checkSpecificCheckbox("Apology from Home Secretary");
    }

    private void enterHighProfileClaimDetails(){
        enterTextIntoTextAreaWithHeading("High profile claim details (For example, MP name and contact details, media outlets)");
    }

    public void selectJudicialReview() {
        recordCaseData.checkSpecificCheckbox("Judicial review");
    }

    public void enterJudicialReviewDetails() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Judicial review details");
    }

    public void completeVulnerableOrHighProfileSection(){
        expandCollapseVulnerableOrHighProfileSection();
        selectClaimantIsVulnerable();
        enterDateReferredToVulnerableTeam();
        enterVulnerableClaimantDetails();
        selectHighProfileClaim();
        selectHighProfileMP();
        selectHighProfileMedia();
        selectHighProfileDetainedAndDeported();
        selectHighProfileDataBreach();
        selectHighProfileApologyFromHomeSec();
        enterHighProfileClaimDetails();
        selectJudicialReview();
        enterJudicialReviewDetails();
    }

    public void assertThatClaimantIsVulnerableFieldsPopulated() {
        assertThat(vulnerableReferralDateDay.getValue().equals(""), is(false));
        assertThat(vulnerableReferralDateMonth.getValue().equals(""), is(false));
        assertThat(vulnerableReferralDateYear.getValue().equals(""), is(false));
        assertThat(vulnerableClaimantDetailsTextbox.getValue().equals(""), is(false));
        assertThat(vulnerableReferralDateDay.getValue().equals(""), is(false));
        assertThat(highProfileClaimDetailsTextbox.getValue().equals(""), is(false));
        assertThat(vulnerableJudicialReviewDetailsTextbox.getValue().equals(""), is(false));
    }

    public void clickOnImmigrationAndLegalFeesCheckbox() {
        recordCaseData.checkSpecificCheckbox("Immigration and legal fees");
    }

    public void clickOnDetentionAndDeportationAndRemovalCheckbox() {
        recordCaseData.checkSpecificCheckbox("Detention deportation and removal");
    }

    public void clickOnEmploymentCheckbox() {
        recordCaseData.checkSpecificCheckbox("Employment");
    }

    public void clickOnChildBenefitCheckbox() {
        recordCaseData.checkSpecificCheckbox("Child benefit/tax credits");
    }

    public void clickOnBenefitsCheckbox() {
        recordCaseData.checkSpecificCheckbox("Benefits");
    }

    public void clickOnHousingCheckbox() {
        recordCaseData.checkSpecificCheckbox("Housing");
    }

    public void clickOnHealthCheckbox() {
        recordCaseData.checkSpecificCheckbox("Health");
    }

    public void clickOnBankingCheckbox() {
        recordCaseData.checkSpecificCheckbox("Banking");
    }

    public void clickOnEducationCheckbox() {
        recordCaseData.checkSpecificCheckbox("Education");
    }

    public void clickOnHomelessnessCheckbox() {
        recordCaseData.checkSpecificCheckbox("Homelessness");
    }

    public void clickOnImpactOnDailyLifeCheckbox() {
        recordCaseData.checkSpecificCheckbox("Impact on daily life");
    }

    public void clickOnDiscretionaryCheckbox() {
        recordCaseData.checkSpecificCheckbox("Discretionary");
    }

    public void clickOnDrivingLicenceCheckbox() {
        recordCaseData.checkSpecificCheckbox("Driving licence");
    }

    public void clickOnUrgentExceptionalPaymentCheckbox() {
        recordCaseData.checkSpecificCheckbox("Urgent Exceptional Payment (UEP)");
    }

    public void clickOnLivingCostsCheckbox() {
        recordCaseData.checkSpecificCheckbox("Living Costs");
    }

    public void selectAllClaimCategories(){
        expandCollapseClaimCategoriesSection();
        clickOnImmigrationAndLegalFeesCheckbox();
        clickOnDetentionAndDeportationAndRemovalCheckbox();
        clickOnEmploymentCheckbox();
        clickOnChildBenefitCheckbox();
        clickOnBenefitsCheckbox();
        clickOnHousingCheckbox();
        clickOnHealthCheckbox();
        clickOnBankingCheckbox();
        clickOnEducationCheckbox();
        clickOnHomelessnessCheckbox();
        clickOnImpactOnDailyLifeCheckbox();
        clickOnDiscretionaryCheckbox();
        clickOnDrivingLicenceCheckbox();
        clickOnUrgentExceptionalPaymentCheckbox();
        clickOnLivingCostsCheckbox();
        expandCollapseClaimCategoriesSection();
    }

    public void completeAllFieldsInClaimSchema(){
        completeCaseInfoSection();
        completePersonalDetailsAccordion();
        completeEligibilityAccordion();
        completeLinkedPersonSection();
        completeRepresentativeSection();
        completeConsentAccordion();
        completeGovernmentRecordsSection();
        completeVulnerableOrHighProfileSection();
        selectAllClaimCategories();
        casework.selectClaimStatusReadyToQA();
    }

    public void assertAllFieldsPopulated() {
        assertCaseInfoFieldsPopulated();
        assertThatPersonalDetailsFieldsPopulated();
        assertThatEligibilityFieldsPopulated();
        assertLinkedPersonFieldsPopulated();
        assertThatRepresentativeFieldsPopulated();
        assertThatConsentFieldsPopulated();
        assertThatGovernmentRecordFieldsPopulated();
        assertThatClaimantIsVulnerableFieldsPopulated();
    }
}
