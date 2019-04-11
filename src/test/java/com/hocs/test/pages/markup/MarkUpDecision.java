package com.hocs.test.pages.markup;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MarkUpDecision extends Page {

    @FindBy(css = "label[for='MarkupDecision-PR']")
    private WebElementFacade policyResponseRadioButton;

    @FindBy(css = "label[for='MarkupDecision-OGD']")
    private WebElementFacade referToOgdRadioButton;

    @FindBy(css = "label[for='MarkupDecision-FAQ']")
    private WebElementFacade faqRadioButton;

    @FindBy(css = "label[for='MarkupDecision-NRN']")
    private WebElementFacade noReplyNeededRadioButton;

    @FindBy(id = "")
    private WebElementFacade dateReceivedField;

    @FindBy(id = "")
    private WebElementFacade draftingDeadlineField;

    @FindBy(id = "")
    private WebElementFacade finalDeadlineField;

    @FindBy(xpath = "//a[text()='Add a ']")
    private WebElementFacade addATopicButton;

    @FindBy(id = "")
    private WebElementFacade primaryTopicRadioButton;

    @FindBy(id = "")
    private WebElementFacade answeringUnitDropdown;

    @FindBy(id = "")
    private WebElementFacade answeringUnitTypeFunction;

    @FindBy(id = "")
    private WebElementFacade answeringTeamTypeFunction;

    @FindBy(id = "")
    private WebElementFacade answeringTeamDropdown;

    @FindBy(id = "SignOffMinister")
    private WebElementFacade signOffMinisterDropdown;

    @FindBy(id = "")
    private WebElementFacade signOffMinisterTypeFunction;

    @FindBy(xpath = "//a[text()='What sort of response is required? is required']")
    private WebElementFacade whatSortOfResponseErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary topic? is required']")
    private WebElementFacade whichIsThePrimaryTopicErrorMessage;

    @FindBy(xpath = "//a[text()='Topic is required']")
    private WebElementFacade topicIsRequiredErrorMessage;

    public void clickAddTopic() {
        addATopicButton.click();
    }

    public void clickFaqRadioButton() {
        faqRadioButton.click();
    }

    public void clickNoReplyNeededRadioButton() {
        noReplyNeededRadioButton.click();
    }

    public void clickPolicyResponseRadioButton() {
        policyResponseRadioButton.click();
    }

    public void clickReferToOgdRadioButton() {
        referToOgdRadioButton.click();
    }

    public void enterAnsweringTeamTypeFunction(String team) {
        answeringTeamTypeFunction.sendKeys(team);
    }

    public void enterAnsweringUnitTypeFunction(String unit) {
        answeringUnitTypeFunction.sendKeys(unit);
    }

    public void enterDateReceived() {
        dateReceivedField.sendKeys(getCurrentDay());
    }

    public void enterDraftingDeadline() {
        draftingDeadlineField.sendKeys();
    }

    public void enterFinalDeadline() {
        finalDeadlineField.sendKeys();
    }

    public void enterSignOffMinisterTypeFunction(String minister) {
        signOffMinisterTypeFunction.sendKeys(minister);
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



}
