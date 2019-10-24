package com.hocs.test.pages.accordion;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class CaseDetailsAccordion extends PageObject {

    //Accordion extender buttons
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

    //Accordion information fields
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

    //assertions

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

        String memberOfParliamentName = sessionVariableCalled("memberOfParliamentName");

        assertThat(whenWasTheCorrespondenceSent.getText(), containsText(correspondenceSentDate));
        assertThat(howWasTheCorrespondenceReceived.getText(), containsText(correspondenceReceivedRadioButtonSelection));
        assertThat(shouldTheResponseBeCopiedToNumber10.getText(), containsText(copiedToN10RadioButtonSelection));
        assertThat(whichIsThePrimaryCorrespondent.getText(), containsText(memberOfParliamentName));
    }

    public void assertAccordionMarkupFields() {
        String selectedWhatSortOfResponseRadioButton = sessionVariableCalled("selectedWhatSortOfResponseRadioButton");
        String selectedTopicName = sessionVariableCalled("selectedTopicName");
        String selectedDraftingTeamName = sessionVariableCalled("selectedDraftingTeamName");
        String selectedPrivateOfficeTeamName = sessionVariableCalled("selectedPrivateOfficeTeamName");

        assertThat(whatSortOfResponseIsRequired.getText(), containsText(selectedWhatSortOfResponseRadioButton));
        assertThat(whichIsThePrimaryTopic.getText(), containsText(selectedTopicName));
        assertThat(initialDraftTeam.getText(), containsText(selectedDraftingTeamName));
        assertThat(privateOfficeTeam.getText(), containsText(selectedPrivateOfficeTeamName));

    }

    public void assertAccordionInitialDraftFields() {
        String selectedCanMyTeamAnswerRadioButton = sessionVariableCalled("selectedCanMyTeamAnswerRadioButton");
        String selectedTypeOfResponseRadioButton = sessionVariableCalled("selectedTypeOfResponseRadioButton");
        String uploadedDocumentTitle = sessionVariableCalled("uploadedDocumentTitle");
        String selectedResponseToQAOfflineRadioButton = sessionVariableCalled("selectedResponseToQAOfflineRadioButton");

        assertThat(canThisCorrespondenceBeAnsweredByYourTeam.getText(), containsText(selectedCanMyTeamAnswerRadioButton));
        assertThat(howDoYouIntendToRespond.getText(), containsText(selectedTypeOfResponseRadioButton.toUpperCase()));
        assertThat(whichIsThePrimaryDraftDocument.getText(), containsText(uploadedDocumentTitle));
        assertThat(doYouWantToQAThisOffline.getText(), containsText(selectedResponseToQAOfflineRadioButton));
    }

    public void assertAccordionQAResponseFields() {
        String chosenQAResponse = sessionVariableCalled("chosenQAResponse");
        assertThat(doYouApproveTheResponse.getText(), containsText(chosenQAResponse));
    }

    public void assertAccordionPrivateOfficeApprovalFields() {
        String privateOfficeAcceptanceDecision = sessionVariableCalled("privateOfficeAcceptanceDecision");
        assertThat(doYouApproveTheResponse.getText(), containsText(privateOfficeAcceptanceDecision));
    }

    public void assertAccordionMinisterialSignOffFields() {
        String ministerialSignOffDecision = sessionVariableCalled("ministerialSignOffDecision");
        assertThat(doYouApproveTheResponse.getText(), containsText(ministerialSignOffDecision));
    }

    public void assertAccordionDispatchFields() {
        String chosenResponseMethod = sessionVariableCalled("chosenResponseMethod");
        String dispatchAbleDecision = sessionVariableCalled("dispatchAbleDecision");
        assertThat(howDoYouIntendToRespond.getText(), containsText(chosenResponseMethod));
        assertThat(areYouAbleToDispatchThis.getText(), containsText(dispatchAbleDecision));
    }

    public void assertThePrimaryContactName(String fullName) {
        clickOn(dataInputAccordionButton);
        assertThat(whichIsThePrimaryCorrespondent.getText(), containsText(fullName));
    }
}
