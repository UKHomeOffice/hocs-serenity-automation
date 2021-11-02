package com.hocs.test.pages.mpam;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MTSDataInput extends BasePage {

    Correspondents correspondents;

    RecordCaseData recordCaseData;

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

    public void selectABusinessArea() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
    }

    public void selectASpecificBusinessArea(String businessArea) {
        recordCaseData.selectSpecificRadioButton(businessArea);
    }

    public void selectABusinessUnit() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Business unit");
    }

    public void selectASpecificBusinessUnit(String businessUnit) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(businessUnit, "Business unit");
    }

    public void selectAnUrgency() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Urgency");
    }

    public void selectASpecificUrgency(String urgency) {
        recordCaseData.selectSpecificRadioButton(urgency);
    }

    public void selectAChannelReceived() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel received");
    }

    public void selectASpecificChannelReceived(String channelReceived) {
        recordCaseData.selectSpecificRadioButton(channelReceived);
    }

    public void selectAnEnquirySubject() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Enquiry subject");
    }

    public void selectASpecificEnquirySubject(String enquirySubject) {
        recordCaseData.selectSpecificRadioButton(enquirySubject);
    }

    public void selectAnEnquiryReason() {
        recordCaseData.selectRandomOptionFromDropdownWithHeading("Enquiry reason");
    }

    public void selectASpecificEnquiryReason(String enquiryReason) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(enquiryReason, "Enquiry reason");
    }

    private void enterASupportNote() {
        String enteredText = recordCaseData.enterTextIntoTextAreaWithHeading("Note to support case");
        setSessionVariable("supportNote").to(enteredText);
    }

    public void selectYourBusinessArea() {
        selectRandomRadioButtonFromGroupWithHeading("Your Business Area");
    }

    public void completeDataInputStageAndCloseMTSCase() {
        correspondents.addAMemberCorrespondent();
        safeClickOn(continueButton);
        selectABusinessArea();
        selectABusinessUnit();
        selectAnUrgency();
        selectAChannelReceived();
        selectAnEnquirySubject();
        selectAnEnquiryReason();
        enterASupportNote();
        selectYourBusinessArea();
        safeClickOn(completeAndCloseCaseButton);
    }
}