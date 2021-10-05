package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import com.hocs.test.pages.decs.TimelineTab;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class PrivateOfficeApproval extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='Yes']")
    public WebElementFacade privateOfficeAcceptRadioButton;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='No']")
    public WebElementFacade privateOfficeRejectRadioButton;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='Change Minister']")
    public WebElementFacade privateOfficeChangeMinisterRadioButton;

    @FindBy(id = "CaseNote_PrivateOfficeReject")
    public WebElementFacade privateOfficeRejectNoteField;

    @FindBy(id = "CaseNote_PrivateOfficeOverride")
    public WebElementFacade privateOfficeOverrideNoteField;

    @FindBy(id = "PrivateOfficeOverridePOTeamUUID")
    public WebElementFacade privateOfficeTeamDropdown;

    @FindBy(xpath = "//div[@id='PrivateOfficeDecision-radios']//label[text()='Change Topic']")
    public WebElementFacade changeTopicRadioButton;

    @FindBy(id = "PrivateOfficeOverridePOTeamUUID")
    public WebElementFacade privateOfficeOverrideDropdown;

    @FindBy(id = "CaseNote_PrivateOfficeOverride")
    public WebElementFacade privateOfficeOverrideReasonTextField;

    @FindBy(id = "CaseNote_PrivateOfficeTopic")
    public WebElementFacade topicOverrideReasonTextField;


    public void selectIfApproveResponse(String yesNo) {
        recordCaseData.selectSpecificRadioButton(yesNo);
    }

    public void selectToChangeMinister() {
        recordCaseData.selectSpecificRadioButton("Change Minister");
    }

    public void selectToChangeTopic() {
        recordCaseData.selectSpecificRadioButton("Change Topic");
    }

    public void enterRejectionReason() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("What is your feedback about the response?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void selectNewPrivateOfficeTeamFromDropdown(String team) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(team, "Override Private Office Team");
    }

    public void enterAReasonForChangingPOTeam() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Why should this be approved by this team instead?");
    }

    public void enterAReasonForChangingTopic() {
        String topicOverrideReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why is the case topic being changed?");
        setSessionVariable("topicOverrideReason").to(topicOverrideReason);
    }
}
