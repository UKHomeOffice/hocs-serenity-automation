package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class Markup extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Policy Response']")
    public WebElementFacade policyResponseRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Refer To OGD']")
    public WebElementFacade referToOgdRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='FAQ Response']")
    public WebElementFacade faqRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='No Response Needed']")
    public WebElementFacade noReplyNeededRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Reject To Data Input']")
    public WebElementFacade rejectToDataInputRadioButton;

    @FindBy(timeoutInSeconds = "10", id = "OGDDept")
    public WebElementFacade OGDDestinationTextBox;

    @FindBy(timeoutInSeconds = "10", id = "CaseNote_OGD")
    public WebElementFacade OGDReasonTextBox;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Add a ']")
    public WebElementFacade addATopicLink;

    @FindBy(timeoutInSeconds = "10", id = "CaseNote_NRN")
    public WebElementFacade noResponseNeededTextField;

    @FindBy(timeoutInSeconds = "10", id = "CaseNote_REJ")
    public WebElementFacade rejectToDataInputTextField;

    @FindBy(timeoutInSeconds = "10", id = "POTeamName")
    public WebElementFacade privateOfficeTeamTextField;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Add a ']")
    public WebElementFacade addTopicLink;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@class='govuk-typeahead__input']/input")
    public WebElementFacade topicsTextField;

    @FindBy(timeoutInSeconds = "10", id = "DraftingTeamName")
    public WebElementFacade defaultDraftTeam;

    @FindBy(timeoutInSeconds = "10", id = "POTeamName")
    public WebElementFacade defaultPrivateOfficeTeam;

    //Basic Methods

    public void selectASpecificResponseType(String responseType) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(responseType, "What sort of response is required?");
    }

    public void selectFAQRadioButton() {
        selectASpecificResponseType("FAQ Response");
    }

    public void selectNoResponseNeededRadioButton() {
        selectASpecificResponseType("No Response Needed");
    }

    public void selectPolicyResponseRadioButton() {
        selectASpecificResponseType("Policy Response");
    }

    public void selectReferToOGDRadioButton() {
        selectASpecificResponseType("Refer To OGD");
    }

    public void selectRejectToDataInput() {
        selectASpecificResponseType("Reject To Data Input");;
    }

    public void enterAOGDDestination() {
        recordCaseData.enterTextIntoTextFieldWithHeading("Where should this case be transferred to?");
    }

    public void enterAOGDReason() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Why should this case be transferred here?");
    }

    public void enterANoResponseNeededReason() {
        recordCaseData.enterTextIntoTextAreaWithHeading("Why is no response needed?");
    }

    public void enterARejectionReason() {
        String rejectionReason = recordCaseData.enterTextIntoTextAreaWithHeading("Why should this case be rejected?");
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void clickAddTopicLink() {
        safeClickOn(addTopicLink);
    }


    public void selectSpecificOverridePrivateOfficeTeam(String newPrivateOfficeTeam) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(newPrivateOfficeTeam, "Override Private Office Team");
    }

    public void selectSpecificOverrideInitialDraftTeam(String newInitialDraftTeam) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(newInitialDraftTeam, "Override Initial Draft Team");
    }

    // Multi Step Methods

    public void addTopicToCase(String topic) {
        clickAddTopicLink();
        selectSpecificOptionFromTypeaheadWithHeading(topic, "Topic");
        waitABit(1000);
        clickAddButton();
    }

    public void selectPrimaryTopic(String topic) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(topic, "Which is the primary topic?");
        clickContinueButton();
    }

    public void confirmPrimaryTopic() {
        WebElementFacade selectedPrimaryTopic = findBy("//input[@name='Topics'][@checked]/following-sibling::label");
        selectedPrimaryTopic.waitUntilVisible();
        recordCaseData.addHeadingAndValueRecord("Which is the primary topic?", selectedPrimaryTopic.getText());
        clickContinueButton();
    }

    public void confirmInitialDraftAndOrPrivateOfficeTeam() {
        if(defaultDraftTeam.isCurrentlyVisible()) {
            recordCaseData.addHeadingAndValueRecord("Initial Draft Team", defaultDraftTeam.getValue());
        }
        if(defaultPrivateOfficeTeam.isCurrentlyVisible()) {
            recordCaseData.addHeadingAndValueRecord("Private Office Team", defaultPrivateOfficeTeam.getValue());
        }
        clickFinishButton();
    }

    public void recordDefaultTeamsForTopic() {
        waitABit(2000);
        setSessionVariable("defaultDraftTeam").to(defaultDraftTeam.getValue());
        setSessionVariable("defaultPrivateOfficeTeam").to(defaultPrivateOfficeTeam.getValue());
    }

    // Assertions

    public void assertNRNTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(noResponseNeededTextField), is(true));
    }

    public void assertRejectTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(rejectToDataInputTextField), is(true));
    }

    public void assertOGDDestinationTextBoxIsDisplayed() {
        assertThat(isElementDisplayed(OGDDestinationTextBox), is(true));
    }

    public void assertTopicsTextFieldDisplayed() {
        isElementDisplayed(topicsTextField);
    }
}
