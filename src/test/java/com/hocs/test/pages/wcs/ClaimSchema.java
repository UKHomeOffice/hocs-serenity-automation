package com.hocs.test.pages.wcs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ClaimSchema extends BasePage {

    Casework casework;

    @FindBy(xpath = "//input[@id='ReceivedDate-day']")
    public WebElementFacade receivedDateDayTextbox;

    @FindBy(xpath = "//input[@id='ReceivedDate-month']")
    public WebElementFacade receivedDateMonthTextbox;

    @FindBy(xpath = "//input[@id='ReceivedDate-year']")
    public WebElementFacade receivedDateYearTextbox;

    @FindBy(css = "label[for='ClaimType-Primary']")
    public WebElementFacade typeOfClaimPrimaryRadioButton;

    @FindBy(css = "label[for='ClaimType-Family']")
    public WebElementFacade typeOfClaimFamilyRadioButton;

    @FindBy(css = "label[for='ClaimType-Estate']")
    public WebElementFacade typeOfClaimEstateRadioButton;

    @FindBy(css = "label[for='FormSubmitted-Post']")
    public WebElementFacade howWasFormSubmittedPostRadioButton;

    @FindBy(css = "label[for='FormSubmitted-Email']")
    public WebElementFacade howWasFormSubmittedEmailRadioButton;

    @FindBy(css = "label[for='IsCitizensAdvice-Yes']")
    public WebElementFacade formSentThroughCitizensAdviceYesRadioButton;

    @FindBy(css = "label[for='IsCitizensAdvice-No']")
    public WebElementFacade formSentThroughCitizensAdviceNoRadioButton;

    @FindBy(css = "label[for='ClaimedFrom-In-Country']")
    public WebElementFacade whereIsClaimFromInCountryRadioButton;

    @FindBy(css = "label[for='ClaimedFrom-Out-Country']")
    public WebElementFacade whereIsClaimFromOutCountryRadioButton;

    @FindBy(id = "LinkedClaims")
    public WebElementFacade linkedClaimsCaseInfoTextbox;

    @FindBy(xpath = "//input[@id='BFDate-day']")
    public WebElementFacade broughtForwardDateDayTextbox;

    @FindBy(xpath = "//input[@id='BFDate-month']")
    public WebElementFacade broughtForwardDateMonthTextbox;

    @FindBy(xpath = "//input[@id='BFDate-year']")
    public WebElementFacade broughtForwardDateYearTextbox;

    @FindBy(css = "input[id='FullName']")
    public WebElementFacade fullNameTextbox;

    @FindBy(css = "input[id='PreviousNames']")
    public WebElementFacade previousNamesTextbox;

    @FindBy(css = "label[for='ClaimantGender-Male']")
    public WebElementFacade claimantGenderMaleRadioButton;

    @FindBy(css = "label[for='ClaimantGender-Female']")
    public WebElementFacade claimantGenderFemaleRadioButton;

    @FindBy(css = "input[id='DateOfBirth-day']")
    public WebElementFacade dateOfBirthDayTextbox;

    @FindBy(css = "input[id='DateOfBirth-month']")
    public WebElementFacade dateOfBirthMonthTextbox;

    @FindBy(css = "input[id='DateOfBirth-year']")
    public WebElementFacade dateOfBirthYearTextbox;

    @FindBy(css = "input[id='Addr1']")
    public WebElementFacade buildingAndStreetTextbox;

    @FindBy(css = "input[id='Addr2']")
    public WebElementFacade townOrCityTextbox;

    @FindBy(css = "input[id='Addr3']")
    public WebElementFacade countyTextbox;

    @FindBy(id = "Country")
    public WebElementFacade countryDropdown;

    @FindBy(css = "input[id='Postcode']")
    public WebElementFacade postcodeTextbox;

    @FindBy(id = "BirthCountry")
    public WebElementFacade countryOfBirthDropdown;

    @FindBy(id = "Nationality")
    public WebElementFacade nationalityDropdown;

    @FindBy(css = "label[for='BirthNationality']")
    public WebElementFacade birthNationalityTextbox;

    @FindBy(id = "Phone")
    public WebElementFacade phoneNumberTextbox;

    @FindBy(id = "Email")
    public WebElementFacade emailTextbox;

    @FindBy(id = "Passport")
    public WebElementFacade passportNumberTextbox;

    @FindBy(id = "PassportsPrev")
    public WebElementFacade expiredPassportNumberTextbox;

    @FindBy(xpath = "(//input[@id='NI'])[1]")
    public WebElementFacade nINumberTextbox;

    @FindBy(id = "Cohort")
    public WebElementFacade eligibilityCohortDropdown;

    @FindBy(id = "ImmigrationStatus")
    public WebElementFacade eligibilityStatus;

    @FindBy(id = "ImmigrationStatusOutcome")
    public WebElementFacade eligibilityStatusOutcome;

    @FindBy(id = "DateOfStatusDecision-day")
    public WebElementFacade dateOfEligibilityStatusDecisionDay;

    @FindBy(id = "DateOfStatusDecision-month")
    public WebElementFacade dateOfEligibilityStatusDecisionMonth;

    @FindBy(id = "DateOfStatusDecision-year")
    public WebElementFacade dateOfEligibilityStatusDecisionYear;

    @FindBy(id = "LinkedFullName")
    public WebElementFacade linkedPersonFullNameTextbox;

    @FindBy(id = "LinkedPreviousNames")
    public WebElementFacade linkedPersonPreviousNameTextbox;

    @FindBy(css = "label[for='LinkedPersonGender-Male']")
    public WebElementFacade linkedPersonGenderMale;

    @FindBy(id = "LinkedPersonGender-Female")
    public WebElementFacade linkedPersonGenderFemale;

    @FindBy(xpath = "//input[@id='LinkedDateOfBirth-day']")
    public WebElementFacade linkedPersonDateOfBirthDayTextbox;

    @FindBy(xpath = "//input[@id='LinkedDateOfBirth-month']")
    public WebElementFacade linkedPersonDateOfBirthMonthTextbox;

    @FindBy(xpath = "//input[@id='LinkedDateOfBirth-year']")
    public WebElementFacade linkedPersonDateOfBirthYearTextbox;

    @FindBy(xpath = "//input[@id='LinkedAddr1']")
    public WebElementFacade linkedPersonBuildingAndStreetTextbox;

    @FindBy(xpath = "//input[@id='LinkedAddr2']")
    public WebElementFacade linkedPersonTownOrCityTextbox;

    @FindBy(xpath = "//input[@id='LinkedAddr3']")
    public WebElementFacade linkedPersonCountyTextbox;

    @FindBy(id = "LinkedCountry")
    public WebElementFacade linkedPersonCountryDropdown;

    @FindBy(xpath = "//input[@id='LinkedPostcode']")
    public WebElementFacade linkedPersonPostcodeTextbox;

    @FindBy(id = "LinkedBirthCountry")
    public WebElementFacade linkedPersonCountryOfBirthDropdown;

    @FindBy(id = "LinkedNationality")
    public WebElementFacade linkedPersonNationalityDropdown;

    @FindBy(xpath = "//input[@id='LinkedPhone']")
    public WebElementFacade linkedPersonPhoneNumberTextbox;

    @FindBy(xpath = "//input[@id='LinkedEmail']")
    public WebElementFacade linkedPersonEmailTextbox;

    @FindBy(xpath = "//input[@id='LinkedPassport']")
    public WebElementFacade linkedPersonPassportNumberTextbox;

    @FindBy(id = "LinkedPassportsPrev")
    public WebElementFacade linkedPersonExpiredPassportNumberTextbox;

    @FindBy(id = "LinkedNI")
    public WebElementFacade linkedPersonNINumberTextbox;

    @FindBy(css = "label[for='IsLinkedDeceased_Yes']")
    public WebElementFacade isLinkedPersonDeceasedCheckbox;

    @FindBy(id = "DeceasedCapacity")
    public WebElementFacade capacityPersonIsActingInTextbox;

    @FindBy(id = "DeceasedActDate-day")
    public WebElementFacade dateBeganActingOnBehalfOfEstateDayTextbox;

    @FindBy(id = "DeceasedActDate-month")
    public WebElementFacade dateBeganActingOnBehalfOfEstateMonthTextbox;

    @FindBy(id = "DeceasedActDate-year")
    public WebElementFacade dateBeganActingOnBehalfOfEstateYearTextbox;

    @FindBy(id = "DeceasedDate-day")
    public WebElementFacade dateOfDeathDayTextbox;

    @FindBy(id = "DeceasedDate-month")
    public WebElementFacade dateOfDeathMonthTextbox;

    @FindBy(id = "DeceasedDate-year")
    public WebElementFacade dateOfDeathYearTextbox;

    @FindBy(xpath = "//input[@id='DeceasedCertificate']")
    public WebElementFacade deathCertificateTextbox;

    @FindBy(xpath = "//input[@id='DeceasedInfoReason']")
    public WebElementFacade reasonForNoDeathCertificateTextbox;

    @FindBy(xpath = "//input[@id='RepName']")
    public WebElementFacade representativeNameTextbox;

    @FindBy(id = "RepFromDate-day")
    public WebElementFacade representedFromDateDayTextbox;

    @FindBy(id = "RepFromDate-month")
    public WebElementFacade representedFromDateMonthTextbox;

    @FindBy(id = "RepFromDate-year")
    public WebElementFacade representedFromDateYearTextbox;

    @FindBy(xpath = "//input[@id='RepOrg']")
    public WebElementFacade representativeOrganisationTextbox;

    @FindBy(xpath = "//input[@id='RepEmail']")
    public WebElementFacade representativeEmailTextbox;

    @FindBy(xpath = "//input[@id='RepPhone']")
    public WebElementFacade representativePhoneTextbox;

    @FindBy(id = "RepStreet")
    public WebElementFacade representativeStreetTextbox;

    @FindBy(id = "RepCity")
    public WebElementFacade representativeCityTextbox;

    @FindBy(id = "RepCounty")
    public WebElementFacade representativeCountyTextbox;

    @FindBy(id = "RepCountry")
    public WebElementFacade representativeCountryDropdown;

    @FindBy(id = "RepPostcode")
    public WebElementFacade representativePostcodeTextbox;

    @FindBy(id = "RepDetails")
    public WebElementFacade representativeOtherDetailsTextbox;

    @FindBy(id = "ConsentPersonWhoPaid")
    public WebElementFacade consentPersonWhoPaidTextbox;

    @FindBy(id = "ConsentAwardToBePaidTo")
    public WebElementFacade consentAwardToBePaidToTextbox;

    @FindBy(id = "ConsentSignedBy")
    public WebElementFacade consentSignedByTextbox;

    @FindBy(id = "ConsentFormSignedDate-day")
    public WebElementFacade consentSignedByDateDay;

    @FindBy(id = "ConsentFormSignedDate-month")
    public WebElementFacade consentSignedByDateMonth;

    @FindBy(id = "ConsentFormSignedDate-year")
    public WebElementFacade consentSignedByDateYear;

    @FindBy(xpath = "//input[@id='PrevHocsRef']")
    public WebElementFacade previousHOCSReferenceTextbox;

    @FindBy(xpath = "//input[@id='NHS']")
    public WebElementFacade nhsNumberTextbox;

    @FindBy(xpath = "//input[@id='DrivingLicence']")
    public WebElementFacade drivingLicenceNumberTextbox;

    @FindBy(xpath = "//input[@id='CID']")
    public WebElementFacade cidTextbox;

    @FindBy(xpath = "//input[@id='CRS']")
    public WebElementFacade crsTextbox;

    @FindBy(xpath = "//input[@id='Warehouse']")
    public WebElementFacade warehouseTextbox;

    @FindBy(xpath = "//input[@id='OtherRef']")
    public WebElementFacade otherTextbox;

    @FindBy(css = "label[for='details-checkbox-IsVulnerable']")
    public WebElementFacade claimantIsVulnerableCheckbox;

    @FindBy(id = "VulnerableClaimReceivedDate-day")
    public WebElementFacade vulnerableReferralDateDay;

    @FindBy(id = "VulnerableClaimReceivedDate-month")
    public WebElementFacade vulnerableReferralDateMonth;

    @FindBy(id = "VulnerableClaimReceivedDate-year")
    public WebElementFacade vulnerableReferralDateYear;

    @FindBy(id = "VulnerableDetails")
    public WebElementFacade vulnerableClaimantDetailsTextbox;

    @FindBy(css = "label[for='details-checkbox-IsHighProfile'")
    public WebElementFacade claimIsHighProfileCheckbox;

    @FindBy(css = "label[for='IsHighProfileMP_Yes']")
    public WebElementFacade highProfileMPCheckbox;

    @FindBy(css = "label[for='IsHighProfileMedia_Yes']")
    public WebElementFacade highProfileMediaCheckbox;

    @FindBy(id = "HighProfileDetails")
    public WebElementFacade highProfileClaimDetailsTextbox;

    @FindBy(css = "label[for='details-checkbox-IsJudicialReview']")
    public WebElementFacade vulnerableJudicialReviewCheckbox;

    @FindBy(id = "JudicialReviewDetails")
    public WebElementFacade vulnerableJudicialReviewDetailsTextbox;

    @FindBy(xpath = ".//label[contains(text(), 'Immigration and legal fees')]")
    public WebElementFacade immigrationAndLegalFeesCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Detention deportation and removal')]")
    public WebElementFacade detentionDeportationAndRemovalCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Employment')]")
    public WebElementFacade employmentCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Child benefit')]")
    public WebElementFacade childBenefitTaxCreditsCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Benefits')]")
    public WebElementFacade benefitsCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Housing')]")
    public WebElementFacade housingCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Health')]")
    public WebElementFacade healthCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Banking')]")
    public WebElementFacade bankingCheckbox;

    @FindBy(xpath = ".//label[text() = 'Driving licence']")
    public WebElementFacade drivingLicenceCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Homelessness')]")
    public WebElementFacade homelessCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Impact on daily life')]")
    public WebElementFacade impactOnDailyLifeCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Discretionary')]")
    public WebElementFacade discretionaryCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Education')]")
    public WebElementFacade educationCheckbox;

    @FindBy(xpath = ".//label[contains(text(), 'Urgent Exceptional Payment')]")
    public WebElementFacade urgentExceptonalPaymentCheckbox;

    @FindBy(xpath = ".//div/div[position()=6]//label[contains(text(), 'Yes')]")
    public WebElementFacade formSentThroughClaimantAssistanceAdviserYesRadioButton;

    @FindBy(xpath = "//button[contains(text(), 'Case info')]")
    public WebElementFacade caseInfoAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Personal details')]")
    public WebElementFacade personalDetailsAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Eligibility and status')]")
    public WebElementFacade eligibilityAndStatusAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Linked person details')]")
    public WebElementFacade linkedPersonDetailsAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Representative')]")
    public WebElementFacade representativeAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Consent details')]")
    public WebElementFacade consentDetailsAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Government records')]")
    public WebElementFacade governmentRecordsAccordionButton;

    @FindBy(xpath = "//button[contains(text(), 'Vulnerable or high profile')]")
    public WebElementFacade vulnerableOrHighProfileAccordionButton;

    @FindBy(xpath = ".//button[contains(text(), 'Claim categories')]")
    public WebElementFacade claimCategoriesAccordionButton;

    @FindBy(xpath = ".//button[contains(text(), 'Case Details')]")
    public WebElementFacade caseDetailsAccordion;

    @FindBy(xpath = "//p[text()='Tier 2 review']/following-sibling::div[1]//input")
    public WebElementFacade tier2AdjudicatorsOfficeDecision;

    @FindBy(xpath = "//fieldset[@id='Tier2AdjudicatorsOfficeDecisionDate']//label[text()='Day']/following-sibling::input")
    public WebElementFacade tier2AdjudicatorsOfficeDecisionDateDay;

    @FindBy(xpath = "//fieldset[@id='Tier2AdjudicatorsOfficeDecisionDate']//label[text()='Month']/following-sibling::input")
    public WebElementFacade tier2AdjudicatorOfficeDecisionDateMonth;

    @FindBy(xpath = "//fieldset[@id='Tier2AdjudicatorsOfficeDecisionDate']//label[text()='Year']/following-sibling::input")
    public WebElementFacade tier2AdjudicatorOfficeDecisionDateYear;


    public void expandCollapseCaseInfoSection() {
        clickOn(caseInfoAccordionButton);
    }

    public void expandCollapsePersonalDetailsSection() {
        clickOn(personalDetailsAccordionButton);
    }

    public void expandCollapseLinkedPersonSection() {
        clickOn(linkedPersonDetailsAccordionButton);
    }

    public void expandCollapseRepresentativeSection() {
        clickOn(representativeAccordionButton);
    }

    public void expandConsentDetailsAccordion() {
        clickOn(consentDetailsAccordionButton);
    }

    public void expandCollapseGovernmentRecordsSection() {
        clickOn(governmentRecordsAccordionButton);
    }

    public void expandCollapseVulnerableOrHighProfileSection() {
        clickOn(vulnerableOrHighProfileAccordionButton);
    }

    public void expandCollapseClaimCategoriesSection() {
        waitABit(500);
        clickOn(claimCategoriesAccordionButton);
        waitABit(500);
    }

    public void showAndHideDetailsInClaimCategories(int claimCategoryType) {
        WebElementFacade showHideDetailsButton = findBy("div:nth-child(" + claimCategoryType + ") > div > div"
                + ".selectable-details-header > div"
                + ".selectable-details-link > "
                + "span");

        showHideDetailsButton.click();
    }

    public void selectPrimaryAsTypeOfClaim(){
        clickOn(typeOfClaimPrimaryRadioButton);
    }

    public void selectFamilyAsTypeOfClaim(){
        clickOn(typeOfClaimFamilyRadioButton);
    }

    public void selectEstateAsTypeOfClaim(){
        clickOn(typeOfClaimEstateRadioButton);
    }

    public void selectEmailAsSubmissionType(){
        clickOn(howWasFormSubmittedEmailRadioButton);
    }

    public void selectPostAsSubmissionType(){
        clickOn(howWasFormSubmittedPostRadioButton);
    }

    public void selectFormWasSentThroughCitizensAdvice(){
        clickOn(formSentThroughCitizensAdviceYesRadioButton);
    }

    public void selectFormWasSentThroughClaimantAssistanceAdviser() {
        clickOn(formSentThroughClaimantAssistanceAdviserYesRadioButton);
    }

    public void selectFormWasNotSentThroughCitizensAdvice(){
        clickOn(formSentThroughCitizensAdviceNoRadioButton);
    }

    public void selectClaimCameFromWithinTheCountry(){
        clickOn(whereIsClaimFromInCountryRadioButton);
    }

    public void selectClaimCameFromOutsideTheCountry(){
        clickOn(whereIsClaimFromOutCountryRadioButton);
    }

    public void enterTextIntoLinkedClaimsTextBox(){
        typeInto(linkedClaimsCaseInfoTextbox, "Test - Linked Claims");
    }

    private void enterReceivedDate() {
        typeInto(receivedDateDayTextbox, getCurrentDay());
        typeInto(receivedDateMonthTextbox, getCurrentMonth());
        typeInto(receivedDateYearTextbox, getCurrentYear());
    }

    public void enterBroughtForwardDate(String date) {
        typeIntoDateFields(broughtForwardDateDayTextbox, broughtForwardDateMonthTextbox, broughtForwardDateYearTextbox, date);
    }

    public void completeRequiredFieldsInCaseInfoSection(){
        expandCollapseCaseInfoSection();
        enterReceivedDate();
        selectPrimaryAsTypeOfClaim();
        selectEmailAsSubmissionType();
        selectFormWasSentThroughClaimantAssistanceAdviser();
        selectClaimCameFromWithinTheCountry();
    }

    public void completeCaseInfoSection(){
        expandCollapseCaseInfoSection();
        enterReceivedDate();
        selectPrimaryAsTypeOfClaim();
        selectEmailAsSubmissionType();
        selectClaimCameFromWithinTheCountry();
        selectFormWasSentThroughClaimantAssistanceAdviser();
        enterTextIntoLinkedClaimsTextBox();
        enterBroughtForwardDate(getTodaysDate());
    }

    public void assertCaseInfoFieldsPopulated() {
        clickOn(caseDetailsAccordion);
        assertThat(receivedDateDayTextbox.getValue().equals(""), is(false));
        assertThat(receivedDateMonthTextbox.getValue().equals(""), is(false));
        assertThat(receivedDateYearTextbox.getValue().equals(""), is(false));
        assertThat(linkedClaimsCaseInfoTextbox.getValue().equals(""), is(false));
    }

    public void enterNameIntoPersonalDetailsFullName(String name){
        typeInto(fullNameTextbox, name);
    }

    private void enterPersonalDetailsPreviousName() {
        typeInto(previousNamesTextbox, "Test - Alias");
    }

    public void selectAGenderRadioButton() {
        clickOn(claimantGenderMaleRadioButton);
    }

    public void enterPersonalDetailsDOB(String date) {
        typeIntoDateFields(dateOfBirthDayTextbox, dateOfBirthMonthTextbox, dateOfBirthYearTextbox, date);

    }

    public void enterTodaysDateInPersonalDetailsDOB() {
        enterPersonalDetailsDOB(getTodaysDate());
    }

    private void enterPersonalDetailsAddress(){
        typeInto(buildingAndStreetTextbox, "Test - Street");
        typeInto(townOrCityTextbox, "Test - City");
        typeInto(countyTextbox, "Test - County");
        clickOn(countryDropdown);
        countryDropdown.selectByVisibleText("USSR");
        typeInto(postcodeTextbox, "TE1 1ST");
    }

    private void selectPersonalDetailsCountryOfBirth(){
        clickOn(countryOfBirthDropdown);
        countryOfBirthDropdown.selectByVisibleText("Wake Island");
    }

    public void selectPersonalDetailsNationalityDropdown(){
        clickOn(nationalityDropdown);
    }

    public void selectFromPersonalDetailsNationalityDropDown(String selection){
        nationalityDropdown.selectByVisibleText(selection);
    }

    private void enterPersonalDetailsNationalities(){
        nationalityDropdown.selectByIndex(15);
        waitABit(500);
        countryOfBirthDropdown.selectByIndex(10);
        waitABit(500);
    }

    private void enterPersonalDetailsContactDetails(){
        typeInto(phoneNumberTextbox, "123456789");
        typeInto(emailTextbox, "Tester@QA.com");
    }

    private void enterPersonalDetailsPassportNumbers(){
        typeInto(passportNumberTextbox, "Test - Passport number");
        typeInto(expiredPassportNumberTextbox, "Test - Expired Passport number");
    }

    private void enterPersonalDetailsNationalInsuranceNumber(){
        typeInto(nINumberTextbox, "Test - National Insurance number");
    }

    public void enterCustomeNationalInsuranceNo(String niNo) {
        typeInto(nINumberTextbox, niNo);
    }

    public void completePersonalDetailsAccordion() {
        clickOn(personalDetailsAccordionButton);
        enterNameIntoPersonalDetailsFullName("Test User");
        enterPersonalDetailsPreviousName();
        selectAGenderRadioButton();
        enterTodaysDateInPersonalDetailsDOB();
        enterPersonalDetailsAddress();
        selectPersonalDetailsCountryOfBirth();
        enterPersonalDetailsNationalities();
        enterPersonalDetailsContactDetails();
        enterPersonalDetailsPassportNumbers();
        enterPersonalDetailsNationalInsuranceNumber();
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
        eligibilityCohortDropdown.selectByIndex(2);
    }

    public void selectEligibilityStatus() {
        eligibilityStatus.selectByIndex(2);
    }

    public void selectEligibilityStatusOutcome() {
        eligibilityStatusOutcome.selectByIndex(2);
    }

    public void selectDateOfEligibilityStatusDecision() {
        typeIntoDateFields(dateOfEligibilityStatusDecisionDay, dateOfEligibilityStatusDecisionMonth, dateOfEligibilityStatusDecisionYear, getTodaysDate());
    }

    public void completeEligibilityAccordion() {
        clickOn(eligibilityAndStatusAccordionButton);
        selectAnEligibilityCohort();
        selectEligibilityStatus();
        selectEligibilityStatusOutcome();
        selectDateOfEligibilityStatusDecision();
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
        typeInto(linkedPersonFullNameTextbox, "Test - LP Full name");
    }

    private void enterLinkedPersonPreviousName() {
        typeInto(linkedPersonPreviousNameTextbox, "Test - LP Alias");
    }

    private void enterLinkedPersonDOB() {
        typeIntoDateFields(linkedPersonDateOfBirthDayTextbox, linkedPersonDateOfBirthMonthTextbox, linkedPersonDateOfBirthYearTextbox,"02/02/2002");
    }

    public void enterLinkedPersonGender() {
        clickOn(linkedPersonGenderMale);
    }

    private void enterLinkedPersonAddress(){
        typeInto(linkedPersonBuildingAndStreetTextbox, "Test - LP Street");
        typeInto(linkedPersonTownOrCityTextbox, "Test - LP City");
        typeInto(linkedPersonCountyTextbox, "Test - LP County");
        clickOn(linkedPersonCountryDropdown);
        linkedPersonCountryDropdown.selectByVisibleText("British Antarctic Territory");
        typeInto(linkedPersonPostcodeTextbox, "TE2 2ST");
    }

    private void selectLinkedPersonCountryOfBirth(){
        clickOn(linkedPersonCountryOfBirthDropdown);
        linkedPersonCountryOfBirthDropdown.selectByVisibleText("Cook Islands");
    }

    private void enterLinkedPersonNationality(){
        linkedPersonNationalityDropdown.selectByIndex(10);
    }

    private void enterLinkedPersonContactDetails(){
        typeInto(linkedPersonPhoneNumberTextbox, "987654321");
        typeInto(linkedPersonEmailTextbox, "LP.Tester@QA.com");
    }

    private void enterLinkedPersonPassportNumbers(){
        typeInto(linkedPersonPassportNumberTextbox, "Test- LP Passport number");
        typeInto(linkedPersonExpiredPassportNumberTextbox, "Test - LP Expired Passport number");
    }

    private void enterLinkedPersonNationalInsuranceNumber(){
        typeInto(linkedPersonNINumberTextbox, "Test - LP National Insurance number");
    }

    private void selectLinkedPersonIsDeceased(){
        clickOn(isLinkedPersonDeceasedCheckbox);
    }

    private void enterLinkedPersonCapacity(){
        typeInto(capacityPersonIsActingInTextbox, "Test - LP Capacity");
    }

    private void enterDateLinkedPersonBeganActingOnBehalf(){
        typeIntoDateFields(dateBeganActingOnBehalfOfEstateDayTextbox, dateBeganActingOnBehalfOfEstateMonthTextbox, dateBeganActingOnBehalfOfEstateYearTextbox, "03/03/2003");
    }

    private void enterLinkedPersonDateOfDeath() {
        typeIntoDateFields(dateOfDeathDayTextbox, dateOfDeathMonthTextbox, dateOfDeathYearTextbox, "04/04/2004");
    }

    private void enterLinkedPersonDeathCertificateNumber(){
        typeInto(deathCertificateTextbox, "Test - LP Death Certificate number");
    }

    private void enterLinkedPersonReasonForNoDeathCertificate(){
        typeInto(reasonForNoDeathCertificateTextbox, "Test - LP Reason");
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
        typeInto(representativeNameTextbox, "Test - Rep full name");
    }

    public void enterRepresentedFromDate() {
        typeIntoDateFields(representedFromDateDayTextbox, representedFromDateMonthTextbox, representedFromDateYearTextbox, getTodaysDate());
    }
    
    private void enterOrganisation() {
        typeInto(representativeOrganisationTextbox, "Test - Rep Organisation");
    }
    
    private void enterRepresentativeContactDetails(){
        typeInto(representativeEmailTextbox, "RepTest@QA.com");
        typeInto(representativePhoneTextbox, "135792468");
    }

    public void enterRepresentativeAddress() {
        typeInto(representativeStreetTextbox, "1 Test Street");
        typeInto(representativeCityTextbox, "Test");
        typeInto(representativeCountyTextbox, "Test");
        representativeCountryDropdown.selectByIndex(10);
        typeInto(representativePostcodeTextbox, "TES7 USR");
    }
    
    private void enterOtherRepresentativeDetails(){
        typeInto(representativeOtherDetailsTextbox, "Test - Rep other details");
    }
    
    public void completeRepresentativeSection(){
        expandCollapseRepresentativeSection();
        enterNameOfRepresentative();
        enterRepresentedFromDate();
        enterOrganisation();
        enterRepresentativeContactDetails();
        enterRepresentativeAddress();
        enterOtherRepresentativeDetails();
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
        typeInto(consentPersonWhoPaidTextbox, "Test 1");
    }

    public void enterAwardToBePaid() {
        typeInto(consentAwardToBePaidToTextbox, "User 2");
    }

    public void enterConsentFormSignedBy() {
        typeInto(consentSignedByTextbox, "User 2");
    }

    public void enterConsentFormSignedDate() {
        typeIntoDateFields(consentSignedByDateDay, consentSignedByDateMonth, consentSignedByDateYear, getTodaysDate());
    }

    public void completeConsentAccordion() {
        expandConsentDetailsAccordion();
        enterConsentPersonWhoPaid();
        enterAwardToBePaid();
        enterConsentFormSignedBy();
        enterConsentFormSignedDate();
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
        typeInto(previousHOCSReferenceTextbox, "Test - Previous HOCS reference");
    }

    public void enterCustomPreviousHOCSReference(String ref) {
        typeInto(previousHOCSReferenceTextbox, ref);
    }

    private void enterNHSNumber() {
        typeInto(nhsNumberTextbox, "Test- NHS number");
    }

    private void enterDrivingLicenceNumber(){
        typeInto(drivingLicenceNumberTextbox, "Test - Driving Licence number");
    }

    private void enterCID(){
        typeInto(cidTextbox, "Test - CID");
    }

    private void enterCRS(){
        typeInto(crsTextbox, "Test - CRS");
    }

    private void enterWarehouse(){
        typeInto(warehouseTextbox, "Test - Warehouse");
    }

    private void enterOther(){
        typeInto(otherTextbox, "Test - Other");
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
        clickOn(claimantIsVulnerableCheckbox);
    }

    public void enterDateReferredToVulnerableTeam() {
        typeIntoDateFields(vulnerableReferralDateDay, vulnerableReferralDateMonth, vulnerableReferralDateYear, getTodaysDate());
    }

    private void enterVulnerableClaimantDetails(){
        typeInto(vulnerableClaimantDetailsTextbox, "Test - Vulnerable claimant details");
    }

    public void selectHighProfileClaim(){
        clickOn(claimIsHighProfileCheckbox);
    }

    public void selectHighProfileMP() {
        clickOn(highProfileMPCheckbox);
    }

    public void selectHighProfileMedia() {
        clickOn(highProfileMediaCheckbox);
    }

    private void enterHighProfileClaimDetails(){
        typeInto(highProfileClaimDetailsTextbox, "Test - High profile claim details");
    }

    public void selectJudicialReview() {
        clickOn(vulnerableJudicialReviewCheckbox);
    }

    public void enterJudicialReviewDetails() {
        typeInto(vulnerableJudicialReviewDetailsTextbox, "test 1");
    }

    public void completeVulnerableOrHighProfileSection(){
        expandCollapseVulnerableOrHighProfileSection();
        selectClaimantIsVulnerable();
        enterDateReferredToVulnerableTeam();
        enterVulnerableClaimantDetails();
        selectHighProfileClaim();
        selectHighProfileMP();
        selectHighProfileMedia();
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

    public void selectAllClaimCategories(){
        expandCollapseClaimCategoriesSection();
        clickOn(immigrationAndLegalFeesCheckbox);
        clickOn(detentionDeportationAndRemovalCheckbox);
        clickOn(employmentCheckbox);
        clickOn(childBenefitTaxCreditsCheckbox);
        clickOn(benefitsCheckbox);
        clickOn(housingCheckbox);
        clickOn(healthCheckbox);
        clickOn(bankingCheckbox);
        clickOn(educationCheckbox);
        clickOn(homelessCheckbox);
        clickOn(impactOnDailyLifeCheckbox);
        clickOn(discretionaryCheckbox);
        clickOn(drivingLicenceCheckbox);
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

    public void clickOnImmigrationAndLegalFeesCheckbox() {
        clickOn(immigrationAndLegalFeesCheckbox);
    }

    public void clickOnDetentionAndDeportationAndRemovalCheckbox() {
        clickOn(detentionDeportationAndRemovalCheckbox);
    }

    public void clickOnEmploymentCheckbox() {
        clickOn(employmentCheckbox);
    }

    public void clickOnChildBenefitCheckbox() {
        clickOn(childBenefitTaxCreditsCheckbox);
    }

    public void clickOnBenefitsCheckbox() {
        clickOn(benefitsCheckbox);
    }

    public void clickOnHousingCheckbox() {
        clickOn(housingCheckbox);
    }

    public void clickOnHealthCheckbox() {
        clickOn(healthCheckbox);
    }

    public void clickOnBankingCheckbox() {
        clickOn(bankingCheckbox);
    }

    public void clickOnEducationCheckbox() {
        clickOn(educationCheckbox);
    }

    public void clickOnHomelessnessCheckbox() {
        clickOn(homelessCheckbox);
    }

    public void clickOnImpactOnDailyLifeCheckbox() {
        clickOn(impactOnDailyLifeCheckbox);
    }

    public void clickOnDiscretionaryCheckbox() {
        clickOn(discretionaryCheckbox);
    }

    public void clickOnDrivingLicenceCheckbox() {
        clickOn(drivingLicenceCheckbox);
    }

    public void clickOnUrgentExceptionalPaymentCheckbox() {
        clickOn(urgentExceptonalPaymentCheckbox);
    }
}
