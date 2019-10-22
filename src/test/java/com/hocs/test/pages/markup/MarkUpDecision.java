package com.hocs.test.pages.markup;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.workstacks.Workstacks;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class MarkUpDecision extends Page {

    @FindBy(css = "label[for='MarkupDecision-PR']")
    public WebElementFacade policyResponseRadioButton;

    @FindBy(css = "label[for='MarkupDecision-OGD']")
    public WebElementFacade referToOgdRadioButton;

    @FindBy(css = "label[for='MarkupDecision-FAQ']")
    public WebElementFacade faqRadioButton;

    @FindBy(css = "label[for='MarkupDecision-NRN']")
    public WebElementFacade noReplyNeededRadioButton;

    @FindBy(id = "")
    public WebElementFacade dateReceivedField;

    @FindBy(id = "")
    public WebElementFacade draftingDeadlineField;

    @FindBy(id = "")
    public WebElementFacade finalDeadlineField;

    @FindBy(xpath = "//a[text()='Add a ']")
    public WebElementFacade addATopicButton;

    @FindBy(id = "")
    public WebElementFacade primaryTopicRadioButton;

    @FindBy(id = "")
    public WebElementFacade answeringUnitDropdown;

    @FindBy(id = "")
    public WebElementFacade answeringUnitTypeFunction;

    @FindBy(id = "")
    public WebElementFacade answeringTeamTypeFunction;

    @FindBy(id = "")
    public WebElementFacade answeringTeamDropdown;

    @FindBy(id = "SignOffMinister")
    public WebElementFacade signOffMinisterDropdown;

    @FindBy(id = "")
    public WebElementFacade signOffMinisterTypeFunction;

    @FindBy(xpath = "//a[text()='What sort of response is required? is required']")
    public WebElementFacade whatSortOfResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary topic? is required']")
    public WebElementFacade whichIsThePrimaryTopicErrorMessage;

    @FindBy(xpath = "//a[text()='Topic is required']")
    public WebElementFacade topicIsRequiredErrorMessage;

    @FindBy(id = "DraftingTeamName")
    public WebElementFacade selectedDraftingTeamName;

    @FindBy(id = "POTeamName")
    public WebElementFacade selectedPrivateOfficeTeamName;

    public void selectFAQRadioButton() {
        clickOn(faqRadioButton);
    }

    public void selectNoReplyNeededRadioButton() {
        clickOn(noReplyNeededRadioButton);
    }

    public void selectPolicyResponseRadioButton() {
        clickOn(policyResponseRadioButton);
    }

    public void selectReferToOGDRadioButton() {
        clickOn(referToOgdRadioButton);
    }

    public void clickPolicyResponseRadioButton() {
        policyResponseRadioButton.click();
    }

    public void enterAnsweringTeamTypeFunction(String team) {
        typeInto(answeringTeamTypeFunction, team);
    }

    public void enterAnsweringUnitTypeFunction(String unit) {
        typeInto(answeringUnitTypeFunction, unit);
    }

    public void enterDateReceived() {
        typeInto(dateReceivedField, getCurrentDay());
    }

    public void enterDraftingDeadline() {
        draftingDeadlineField.sendKeys();
    }

    public void enterFinalDeadline() {
        finalDeadlineField.sendKeys();
    }

    public void enterSignOffMinisterTypeFunction(String minister) {
        typeInto(signOffMinisterTypeFunction, minister);
    }

    public void selectAnsweringTeamFromDropdownByText(String team) {
        answeringTeamDropdown.selectByVisibleText(team);
    }

    public void selectAnsweringUnitFromDropdownByText(String unit) {
        answeringUnitDropdown.selectByVisibleText(unit);
    }

    public void selectSignOffMinisterFromDropdownByText(String minister) {
        signOffMinisterDropdown.selectByVisibleText(minister);
    }

    public void selectSecondAnsweringTeamFromDropdown() {
        answeringTeamDropdown.selectByIndex(1);
    }

    public void selectSecondAnsweringUnitFromDropdown() {
        answeringUnitDropdown.selectByIndex(1);
    }

    public void selectFirstSignOffMinisterFromDropdown() {
        signOffMinisterDropdown.selectByIndex(1);
    }

    public void assertSortOfResponseErrorMessage() {
        assertThat(whatSortOfResponseErrorMessage.getText(), is("What sort of response is required? is required"));
    }

    public void assertAddATopicErrorMessage() {
        assertThat(whichIsThePrimaryTopicErrorMessage.getText(), is("Which is the primary topic? is required"));
    }

    public void assertTopicIsRequiredErrorMessage() {
        assertThat(topicIsRequiredErrorMessage.getText(), is("Topic is required"));
    }

    public void getToMarkupAddATopicScreenPrerequisites() {
        clickOn(policyResponseRadioButton);
        clickOn(continueButton);
        sleep(500);
    }

    public void getToMarkupEnterANewTopicScreenPrerequisites() {
        clickOn(policyResponseRadioButton);
        clickOn(continueButton);
        clickOn(addATopicButton);
    }

    public void chooseResponseTypeAndRecordDecision() {
        clickOn(policyResponseRadioButton);
        String whatSortOfResponseRadioButton = policyResponseRadioButton.getAttribute("for").substring(15);
        setSessionVariable("selectedWhatSortOfResponseRadioButton").to(whatSortOfResponseRadioButton);
    }

    public void recordSelectedDraftingAndPrivateOfficeTeams() {
        String selectedInitialDraftingTeamName = selectedDraftingTeamName.getValue();
        setSessionVariable("selectedDraftingTeamName").to(selectedInitialDraftingTeamName);
        String selectedPOTeamName = selectedPrivateOfficeTeamName.getValue();
        setSessionVariable("selectedPrivateOfficeTeamName").to(selectedPOTeamName);
    }
}
