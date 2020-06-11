package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccordionDCU extends BasePage {

    @FindBy(xpath = "//button[text()= 'Data Input']")
    public WebElementFacade dataInputAccordionButton;

    @FindBy(xpath = "//button[text()= 'Markup']")
    public WebElementFacade markupAccordionButton;

    @FindBy(xpath = "//button[text()= 'Initial Draft']")
    public WebElementFacade initialDraftAccordionButton;

    @FindBy(xpath = "//button[text()= 'QA Response']")
    public WebElementFacade qAResponseAccordionButton;

    @FindBy(xpath = "//button[text()= 'Private Office Approval']")
    public WebElementFacade privateOfficeApprovalAccordionButton;

    @FindBy(xpath = "//button[text()= 'Ministerial Sign off']")
    public WebElementFacade ministerialSignOffAccordionButton;

    @FindBy(xpath = "//button[text()= 'Dispatch']")
    public WebElementFacade dispatchAccordionButton;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Which is the primary correspondent?']/parent::span")
    public WebElementFacade whichIsThePrimaryCorrespondent;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'When was the correspondence sent?']/parent::span")
    public WebElementFacade whenWasTheCorrespondenceSent;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Should the response be copied to Number 10?']/parent::span")
    public WebElementFacade shouldTheResponseBeCopiedToNumber10;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'How was the correspondence received?']/parent::span")
    public WebElementFacade howWasTheCorrespondenceReceived;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'When was the correspondence received?']/parent::span")
    public WebElementFacade whenWasTheCorrespondenceReceived;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Private Office Team']/parent::span")
    public WebElementFacade privateOfficeTeam;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Initial Draft Team']/parent::span")
    public WebElementFacade initialDraftTeam;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Which is the primary topic?']/parent::span")
    public WebElementFacade whichIsThePrimaryTopic;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'What sort of response is required?']/parent::span")
    public WebElementFacade whatSortOfResponseIsRequired;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Do you want to QA this offline?']/parent::span")
    public WebElementFacade doYouWantToQAThisOffline;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'How do you intend to respond?']/parent::span")
    public WebElementFacade howDoYouIntendToRespond;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Which is the primary draft document?']/parent::span")
    public WebElementFacade whichIsThePrimaryDraftDocument;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Can this correspondence be answered by your team?']/parent::span")
    public WebElementFacade canThisCorrespondenceBeAnsweredByYourTeam;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Do you approve the response?']/parent::span")
    public WebElementFacade doYouApproveTheResponse;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Are you able to dispatch this?']/parent::span")
    public WebElementFacade areYouAbleToDispatchThis;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Override Private Office Team']/parent::span")
    public WebElementFacade OverridePrivateOfficeTeam;

    @FindBy(xpath = "//div[contains(@class ,'govuk-accordion__section--expanded')]/descendant::strong[text() = 'Why should this be approved by this team instead?']/parent::span")
    public WebElementFacade whyShouldThisBeApprovedByThisTeamInstead;

    public void assertAccordionCorrespondenceReceivedDate() {
        String dataInputCorrespondenceReceivedDate =
                sessionVariableCalled("correspondenceReceivedDay") + "/" + sessionVariableCalled(
                        "correspondenceReceivedMonth") + "/" + sessionVariableCalled("correspondenceReceivedYear");
        assertThat(whenWasTheCorrespondenceReceived.getText(), containsText(dataInputCorrespondenceReceivedDate));
    }

    public void assertAccordionDataInputFields() {
        String correspondenceSentDate =
                sessionVariableCalled("currentDay") + "/" + sessionVariableCalled("currentMonth") + "/"
                        + sessionVariableCalled("currentYear");

        String correspondenceReceivedRadioButtonSelection = sessionVariableCalled(
                "selectedCorrespondenceReceivedRadioButton");

        String copiedToN10RadioButtonSelection = sessionVariableCalled("selectedCopiedN10NoRadioButton");

        String memberOfParliamentName = sessionVariableCalled("correspondentFullName");

        whenWasTheCorrespondenceSent.shouldContainText(correspondenceSentDate);
        howWasTheCorrespondenceReceived.shouldContainText(correspondenceReceivedRadioButtonSelection);
        shouldTheResponseBeCopiedToNumber10.shouldContainText(copiedToN10RadioButtonSelection);
        whichIsThePrimaryCorrespondent.shouldContainText(memberOfParliamentName);
    }

    public void assertAccordionMarkupFields() {
        String selectedWhatSortOfResponseRadioButton = sessionVariableCalled("selectedWhatSortOfResponseRadioButton");
        String selectedTopicName = sessionVariableCalled("selectedTopicName");
        String selectedDraftingTeamName = sessionVariableCalled("selectedDraftingTeamName");
        String selectedPrivateOfficeTeamName = sessionVariableCalled("selectedPrivateOfficeTeamName");

        whatSortOfResponseIsRequired.shouldContainText(selectedWhatSortOfResponseRadioButton);
        whichIsThePrimaryTopic.shouldContainText(selectedTopicName);
        initialDraftTeam.shouldContainText(selectedDraftingTeamName);
        privateOfficeTeam.shouldContainText(selectedPrivateOfficeTeamName);

    }

    public void assertAccordionInitialDraftFields() {
        String selectedCanMyTeamAnswerRadioButton = sessionVariableCalled("selectedCanMyTeamAnswerRadioButton");
        String selectedTypeOfResponseRadioButton = sessionVariableCalled("selectedTypeOfResponseRadioButton");
        String uploadedDocumentTitle = sessionVariableCalled("uploadedDocumentTitle");
        String selectedResponseToQAOfflineRadioButton = sessionVariableCalled("selectedResponseToQAOfflineRadioButton");

        canThisCorrespondenceBeAnsweredByYourTeam.shouldContainText(selectedCanMyTeamAnswerRadioButton);
        howDoYouIntendToRespond.shouldContainText(selectedTypeOfResponseRadioButton);
        whichIsThePrimaryDraftDocument.shouldContainText(uploadedDocumentTitle);
        doYouWantToQAThisOffline.shouldContainText(selectedResponseToQAOfflineRadioButton);
    }

    public void assertAccordionQAResponseFields() {
        String chosenQAResponse = sessionVariableCalled("chosenQAResponse");
        doYouApproveTheResponse.shouldContainText(chosenQAResponse);
    }

    public void assertAccordionPrivateOfficeApprovalFields() {
        String privateOfficeAcceptanceDecision = sessionVariableCalled("privateOfficeAcceptanceDecision");
        doYouApproveTheResponse.shouldContainText(privateOfficeAcceptanceDecision);
    }

    public void assertAccordionMinisterialSignOffFields() {
        String ministerialSignOffDecision = sessionVariableCalled("ministerialSignOffDecision");
        doYouApproveTheResponse.shouldContainText(ministerialSignOffDecision);
    }

    public void assertAccordionDispatchFields() {
        String chosenResponseMethod = sessionVariableCalled("chosenResponseMethod");
        String dispatchAbleDecision = sessionVariableCalled("dispatchAbleDecision");
        howDoYouIntendToRespond.shouldContainText(chosenResponseMethod);
        areYouAbleToDispatchThis.shouldContainText(dispatchAbleDecision);
    }

    public void assertThePrimaryContactName(String fullName) {
        safeClickOn(dataInputAccordionButton);
        whichIsThePrimaryCorrespondent.shouldContainText(fullName);
    }

    public void assertAccordionPrivateOfficeApprovalFieldsAfterPOTeamChange() {
        String chosenPOTeam = sessionVariableCalled("chosenPOTeam");
        String reasonForOverridePOTeam = sessionVariableCalled("reasonForOverridePOTeam");

        doYouApproveTheResponse.shouldContainText("Change Minister");
        OverridePrivateOfficeTeam.shouldContainText(chosenPOTeam);
        whyShouldThisBeApprovedByThisTeamInstead.shouldContainText(reasonForOverridePOTeam);
    }

}
