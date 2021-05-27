package com.hocs.test.pages.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MTSDataInput extends BasePage {

    AddCorrespondent addCorrespondent;

    @FindBy(xpath = "//label[@for='BusArea-UKVI']")
    private WebElementFacade ukviBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='BusArea-BF']")
    private WebElementFacade bfBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='BusArea-IE']")
    private WebElementFacade ieBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='BusArea-EUSS']")
    private WebElementFacade eussBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='BusArea-HMPO']")
    private WebElementFacade hmpoBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='BusArea-Windrush']")
    private WebElementFacade windrushBusinessAreaRadioButton;

    @FindBy(id = "BusUnit")
    private WebElementFacade businessUnitDropdown;

    @FindBy(xpath = "//label[text()='Standard']")
    private WebElementFacade standardUrgencyRadioButton;

    @FindBy(xpath = "//label[text()='Priority']")
    private WebElementFacade priorityUrgencyRadioButton;

    @FindBy(xpath = "//label[text()='Immediate']")
    private WebElementFacade immediateUrgencyRadioButton;

    @FindBy(xpath = "//label[text()='Email']")
    private WebElementFacade emailChannelReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Post']")
    private WebElementFacade postChannelReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Phone - reply given']")
    private WebElementFacade phoneReplyGivenChannelReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Phone - response required")
    private WebElementFacade phoneResponseRequiredChannelReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Private office referral']")
    private WebElementFacade privateOfficeReferralReceivedChannelRadioButton;

    @FindBy(xpath = "//label[text()='Outreach']")
    private WebElementFacade outreachChannelReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Person Specific']")
    private WebElementFacade personSpecificEnquirySubjectRadioButton;

    @FindBy(xpath = "//label[text()='Guidance / Policy']")
    private WebElementFacade guidanceOrPolicyEnquirySubjectRadioButton;

    @FindBy(xpath = "//label[text()='Documentation']")
    private WebElementFacade documentationEnquirySubjectRadioButton;

    @FindBy(xpath = "//label[text()='Technical']")
    private WebElementFacade technicalEnquirySubjectRadioButton;

    @FindBy(xpath = "//label[text()='Detention']")
    private WebElementFacade detentionEnquirySubjectRadioButton;

    @FindBy(xpath = "//label[contains(text(), 'HMPO Specific')]")
    private WebElementFacade hmpoSpecificEnquirySubjectRadioButton;

    @FindBy(xpath = "//label[text()='Other']")
    private WebElementFacade otherEnquirySubjectRadioButton;

    @FindBy(id = "EnquiryReason")
    private WebElementFacade enquiryReasonDropdown;

    @FindBy(xpath = "//label[@for='BusArea-Coronavirus']")
    private WebElementFacade coronavirusBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-UKVI']")
    private WebElementFacade ukviYourBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-BF']")
    private WebElementFacade bfYourBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-IE']")
    private WebElementFacade ieYourBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-EUSS']")
    private WebElementFacade eussYourBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-HMPO']")
    private WebElementFacade hmpoYourBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-Windrush']")
    private WebElementFacade windrushYourBusinessAreaRadioButton;

    @FindBy(xpath = "//label[@for='YourBusArea-Coronavirus']")
    private WebElementFacade coronavirusYourBusinessAreaRadioButton;

    @FindBy(xpath = "//input[@name='DateOfSurgery-day']")
    public WebElementFacade dateOfSurgeryDayField;

    @FindBy(xpath = "//input[@name='DateOfSurgery-month']")
    public WebElementFacade dateOfSurgeryMonthField;

    @FindBy(xpath = "//input[@name='DateOfSurgery-year']")
    public WebElementFacade dateOfSurgeryYearField;

    @FindBy(xpath = "//label[text()='Yes']")
    public WebElementFacade yesOfficialTelephoneEngagementRadioButton;

    @FindBy(xpath = "//label[text()='No']")
    public WebElementFacade noOfficialTelephoneEngagementRadioButton;

    @FindBy(xpath = "//label[text()='Put on hold']")
    private WebElementFacade putOnHoldRadioButton;

    @FindBy(xpath = "//label[text()='Complete and close case']")
    private WebElementFacade completeAndCloseCaseRadioButton;

    public void selectBusinessArea(String businessArea) {
        WebElementFacade radioButton = null;
        switch (businessArea.toUpperCase()) {
            case "UKVI":
                radioButton = ukviBusinessAreaRadioButton;
                break;
            case "BF":
                radioButton = bfBusinessAreaRadioButton;
                break;
            case "IE":
                radioButton = ieBusinessAreaRadioButton;
                break;
            case "EUSS":
                radioButton = eussBusinessAreaRadioButton;
                break;
            case "HMPO":
                radioButton = hmpoBusinessAreaRadioButton;
                break;
            case "WINDRUSH":
                radioButton = windrushBusinessAreaRadioButton;
                break;
            case "CORONAVIRUS (COVID-19)":
                radioButton = coronavirusBusinessAreaRadioButton;
                break;
            default:
                pendingStep(businessArea + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void selectUrgency(String urgency) {
        WebElementFacade radioButton = null;
        switch (urgency.toUpperCase()) {
            case "STANDARD":
                radioButton = standardUrgencyRadioButton;
                break;
            case "PRIORITY":
                radioButton = priorityUrgencyRadioButton;
                break;
            case "IMMEDIATE":
                radioButton = immediateUrgencyRadioButton;
                break;
            default:
                pendingStep(urgency + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void selectChannelReceived(String channelReceived) {
        WebElementFacade radioButton = null;
        switch (channelReceived.toUpperCase()) {
            case "EMAIL":
                radioButton = emailChannelReceivedRadioButton;
                break;
            case "POST":
                radioButton = postChannelReceivedRadioButton;
                break;
            case "PHONE - REPLY GIVEN":
                radioButton = phoneReplyGivenChannelReceivedRadioButton;
                break;
            case "PHONE - RESPONSE REQUIRED":
                radioButton = phoneResponseRequiredChannelReceivedRadioButton;
                break;
            case "PRIVATE OFFICE REFERRAL":
                radioButton = privateOfficeReferralReceivedChannelRadioButton;
                break;
            case "OUTREACH":
                radioButton = outreachChannelReceivedRadioButton;
                break;
            default:
                pendingStep(channelReceived + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void selectEnquirySubject(String enquirySubject) {
        WebElementFacade radioButton = null;
        switch (enquirySubject.toUpperCase()) {
            case "PERSON SPECIFIC":
                radioButton = personSpecificEnquirySubjectRadioButton;
                break;
            case "GUIDANCE/POLICY":
                radioButton = guidanceOrPolicyEnquirySubjectRadioButton;
                break;
            case "DOCUMENTATION":
                radioButton = documentationEnquirySubjectRadioButton;
                break;
            case "TECHNICAL":
                radioButton = technicalEnquirySubjectRadioButton;
                break;
            case "DETENTION":
                radioButton = detentionEnquirySubjectRadioButton;
                break;
            case "HMPO SPECIFIC":
                radioButton = hmpoSpecificEnquirySubjectRadioButton;
                break;
            case "OTHER":
                radioButton = otherEnquirySubjectRadioButton;
                break;
            default:
                pendingStep(enquirySubject + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void selectYourBusinessArea(String businessArea) {
        WebElementFacade radioButton = null;
        switch (businessArea.toUpperCase()) {
            case "UKVI":
                radioButton = ukviYourBusinessAreaRadioButton;
                break;
            case "BF":
                radioButton = bfYourBusinessAreaRadioButton;
                break;
            case "IE":
                radioButton = ieYourBusinessAreaRadioButton;
                break;
            case "EUSS":
                radioButton = eussYourBusinessAreaRadioButton;
                break;
            case "HMPO":
                radioButton = hmpoYourBusinessAreaRadioButton;
                break;
            case "WINDRUSH":
                radioButton = windrushYourBusinessAreaRadioButton;
                break;
            case "CORONAVIRUS (COVID-19)":
                radioButton = coronavirusYourBusinessAreaRadioButton;
                break;
            default:
                pendingStep(businessArea + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
    }

    public void enterDateOfSurgery(String date) {
        typeIntoDateField(dateOfSurgeryDayField, dateOfSurgeryMonthField, dateOfSurgeryYearField, date);
    }

    public void selectTelephoneSurgeryOfficialEngagement(String decision) {
        if (decision.toUpperCase().equals("YES")) {
            safeClickOn(yesOfficialTelephoneEngagementRadioButton);
        } else if (decision.toUpperCase().equals("NO")) {
            safeClickOn(noOfficialTelephoneEngagementRadioButton);
        }
    }

    public void triggerErrorMessage(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "PRIMARY CORRESPONDENT":
                break;
            case "BUSINESS AREA":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectUrgency("Standard");
                selectChannelReceived("Phone - reply given");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            case "BUSINESS UNIT":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                selectUrgency("Standard");
                selectChannelReceived("Phone - reply given");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            case "URGENCY":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectChannelReceived("Phone - reply given");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            case "CHANNEL RECEIVED":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            case "ENQUIRY SUBJECT":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived("Phone - reply given");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            case "ENQUIRY REASON":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived("Phone - reply given");
                selectEnquirySubject("Person Specific");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            case "ACTIONS":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived("Phone - reply given");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(continueButton);
                break;
            case "DATE OF SURGERY":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived("Phone - reply given");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(completeAndCloseCaseRadioButton);
                safeClickOn(continueButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
        safeClickOn(continueButton);
    }

    public void completeDataInputStageAndCloseMTSCase() {
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        safeClickOn(continueButton);
        selectBusinessArea("UKVI");
        businessUnitDropdown.selectByVisibleText("Asylum");
        selectUrgency("Standard");
        selectChannelReceived("Phone - reply given");
        selectEnquirySubject("Person Specific");
        enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
        selectYourBusinessArea("IE");
        enterDateOfSurgery(getDatePlusMinusNDaysAgo(0));
        selectTelephoneSurgeryOfficialEngagement("Yes");
        safeClickOn(completeAndCloseCaseRadioButton);
        safeClickOn(continueButton);
    }

    public void putMTSCaseOnHold() {
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        safeClickOn(continueButton);
        safeClickOn(putOnHoldRadioButton);
        safeClickOn(continueButton);
    }

    public void assertErrorMessageIsDisplayed(String expectedMessage) {
        String expectedText = null;
        switch (expectedMessage.toUpperCase()) {
            case "PRIMARY CORRESPONDENT":
                expectedText = "Which is the primary correspondent? is required";
                break;
            case "BUSINESS AREA":
                expectedText = "Business Area is required";
                break;
            case "BUSINESS UNIT":
                expectedText = "Business unit is required";
                break;
            case "URGENCY":
                expectedText = "Urgency is required";
                break;
            case "CHANNEL RECEIVED":
                expectedText = "Channel received is required";
                break;
            case "ENQUIRY SUBJECT":
                expectedText = "Enquiry subject is required";
                break;
            case "ENQUIRY REASON":
                expectedText = "Enquiry reason is required";
                break;
            case "YOUR BUSINESS AREA":
                expectedText = "Your Business Area is required";
                break;
            case "DATE OF SURGERY":
                expectedText = "Date of Surgery is required";
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                expectedText = "Telephone Surgery Official Engagement is required";
                break;
            case "ACTIONS":
                expectedText = "Actions is required";
                break;
            default:
                pendingStep(expectedMessage + " is not defined within " + getMethodName());
        }
        WebElementFacade errorMessage = findBy("//ul[@class = 'govuk-list govuk-error-summary__list']//a[contains(text(), '" + expectedText +
                "')]");
        errorMessage.shouldBeVisible();
    }
}