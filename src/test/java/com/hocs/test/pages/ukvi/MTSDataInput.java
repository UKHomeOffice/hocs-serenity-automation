package com.hocs.test.pages.ukvi;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MTSDataInput extends BasePage {

    Correspondents correspondents;

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
        selectRandomRadioButtonFromGroupWithHeading("Business Area");
    }

    public void selectBusinessArea(String businessArea) {
        selectSpecificRadioButton(businessArea);
    }

    public void selectUrgency() {
        selectRandomRadioButtonFromGroupWithHeading("Urgency");
    }

    public void selectUrgency(String urgency) {
        selectSpecificRadioButton(urgency);
    }

    public void selectChannelReceived() {
        selectRandomRadioButtonFromGroupWithHeading("Channel received");
    }

    public void selectChannelReceived(String channelReceived) {
        selectSpecificRadioButton(channelReceived);
    }

    public void selectEnquirySubject() {
        selectRandomRadioButtonFromGroupWithHeading("Enquiry subject");
    }

    public void selectEnquirySubject(String enquirySubject) {
        selectSpecificRadioButton(enquirySubject);
    }

    private void enterASupportNote(String supportNoteText) {
        supportNoteTextArea.sendKeys(supportNoteText);
        setSessionVariable("supportNote").to(supportNoteText);
    }

    public void selectYourBusinessArea() {
        selectRandomRadioButtonFromGroupWithHeading("Your Business Area");
    }

    public void completeDataInputStageAndCloseMTSCase() {
        correspondents.addAMemberCorrespondent();
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
}