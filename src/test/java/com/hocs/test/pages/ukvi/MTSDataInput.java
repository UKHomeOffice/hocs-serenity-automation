package com.hocs.test.pages.ukvi;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MTSDataInput extends BasePage {

    AddCorrespondent addCorrespondent;

    @FindBy(id = "BusUnit")
    public WebElementFacade businessUnitDropdown;

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
    public WebElementFacade enquiryReasonDropdown;

    @FindBy(xpath = "//textarea[@name='SupportNote']")
    public WebElementFacade supportNoteTextArea;

    @FindBy(xpath = "//Input[@value='Complete and Close Case']")
    private WebElementFacade completeAndCloseCaseButton;

    public void selectBusinessArea() {
        List<WebElementFacade> businessAreas = findAll("//input[@name='BusArea']/following-sibling::label");
        safeClickOn(getRandomElementFromList(businessAreas));
    }

    public void selectBusinessArea(String businessArea) {
        safeClickRadioButtonByVisibleText(businessArea);
    }

    public void selectUrgency() {
        List<WebElementFacade> urgencies = findAll("//input[@name='Priority']/following-sibling::label");
        safeClickOn(getRandomElementFromList(urgencies));
    }

    public void selectUrgency(String urgency) {
        safeClickRadioButtonByVisibleText(urgency);
    }

    public void selectChannelReceived() {
        List<WebElementFacade> channels = findAll("//input[@name='ChannelIn']/following-sibling::label");
        safeClickOn(getRandomElementFromList(channels));
    }

    public void selectChannelReceived(String channelReceived) {
        safeClickRadioButtonByVisibleText(channelReceived);
    }

    public void selectEnquirySubject() {
        List<WebElementFacade> enquirySubjects = findAll("//input[@name='EnquirySubject']/following-sibling::label");
        safeClickOn(getRandomElementFromList(enquirySubjects));
    }

    public void selectEnquirySubject(String enquirySubject) {
        safeClickRadioButtonByVisibleText(enquirySubject);
    }

    private void enterASupportNote(String supportNoteText) {
        supportNoteTextArea.sendKeys(supportNoteText);
        setSessionVariable("supportNote").to(supportNoteText);
    }

    public void selectYourBusinessArea() {
        List<WebElementFacade> yourBusinessAreas = findAll("//input[@name='YourBusArea']/following-sibling::label");
        safeClickOn(getRandomElementFromList(yourBusinessAreas));
    }

    public void completeDataInputStageAndCloseMTSCase() {
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        safeClickOn(continueButton);
        businessUnitDropdown.waitUntilVisible();
        selectBusinessArea();
        businessUnitDropdown.selectByIndex(1);
        selectUrgency();
        selectChannelReceived();
        selectEnquirySubject();
        enquiryReasonDropdown.selectByIndex(1);
        enterASupportNote("Test support note");
        selectYourBusinessArea();
        safeClickOn(completeAndCloseCaseButton);
    }

    public void triggerErrorMessage(String errorMessage) {
        switch (errorMessage.toUpperCase()) {
            case "PRIMARY CORRESPONDENT":
                safeClickOn(continueButton);
                break;
            case "BUSINESS AREA":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectUrgency("Standard");
                selectChannelReceived();
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                enterASupportNote("Test support note");
                safeClickOn(completeAndCloseCaseButton);
                break;
            case "BUSINESS UNIT":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                selectUrgency("Standard");
                selectChannelReceived();
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                enterASupportNote("Test support note");
                safeClickOn(completeAndCloseCaseButton);
                break;
            case "URGENCY":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectChannelReceived();
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                enterASupportNote("Test support note");
                safeClickOn(completeAndCloseCaseButton);
                break;
            case "CHANNEL RECEIVED":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                enterASupportNote("Test support note");
                safeClickOn(completeAndCloseCaseButton);
                break;
            case "ENQUIRY SUBJECT":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived();
                enterASupportNote("Test support note");
                safeClickOn(completeAndCloseCaseButton);
                break;
            case "ENQUIRY REASON":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived();
                selectEnquirySubject("Person Specific");
                enterASupportNote("Test support note");
                safeClickOn(completeAndCloseCaseButton);
                break;
            case "NOTE TO SUPPORT CASE":
                addCorrespondent.addAMemberCorrespondent("Boris Johnson");
                safeClickOn(continueButton);
                selectBusinessArea("UKVI");
                businessUnitDropdown.selectByVisibleText("Asylum");
                selectUrgency("Standard");
                selectChannelReceived();
                selectEnquirySubject("Person Specific");
                enquiryReasonDropdown.selectByVisibleText("Allowed appeal enquiry update");
                safeClickOn(completeAndCloseCaseButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
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
            case "NOTE TO SUPPORT CASE":
                expectedText = "Note to support case is required";
                break;
            default:
                pendingStep(expectedMessage + " is not defined within " + getMethodName());
        }
        WebElementFacade errorMessage = findBy("//ul[@class = 'govuk-list govuk-error-summary__list']//a[contains(text(), '" + expectedText +
                "')]");
        errorMessage.shouldBeVisible();
    }
}