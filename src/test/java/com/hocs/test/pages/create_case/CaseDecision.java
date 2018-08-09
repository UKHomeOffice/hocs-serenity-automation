package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CaseDecision extends Page {

    @FindBy(id = "")
    private WebElementFacade policyResponseRadioButton;

    @FindBy(id = "")
    private WebElementFacade referToOgdRadioButton;

    @FindBy(id = "")
    private WebElementFacade faqRadioButton;

    @FindBy(id = "")
    private WebElementFacade noReplyNeededRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocationNoteFreeTextField;

    @FindBy(id = "")
    private WebElementFacade dateReceivedField;

    @FindBy(id = "")
    private WebElementFacade draftingDeadlineField;

    @FindBy(id = "")
    private WebElementFacade finalDeadlineField;

    @FindBy(id = "")
    private WebElementFacade addATopicButton;

    @FindBy(id = "")
    private WebElementFacade primaryTopicRadioButton;

    @FindBy(id = "")
    private WebElementFacade answeringUnitDropdown;

    @FindBy(id = "")
    private WebElementFacade answeringTeamDropdown;


    @FindBy(id = "")
    private WebElementFacade signOffMinisterDropdown;

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

    public void enterAllocationNoteFreeText() {
        allocationNoteFreeTextField.sendKeys(generateRandomString());
    }

    public void enterDateReceived() {
        dateReceivedField.sendKeys(today());
    }

    public void enterDraftingDeadline() {
        draftingDeadlineField.sendKeys();
    }

    public void enterFinalDeadline() {
        finalDeadlineField.sendKeys();
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

    public void selectSecondSignOffMinisterFromDropdown() {
        signOffMinisterDropdown.selectByIndex(1);
    }


}
