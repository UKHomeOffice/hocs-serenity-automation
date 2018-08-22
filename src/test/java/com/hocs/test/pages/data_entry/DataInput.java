package com.hocs.test.pages.data_entry;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput {

    @FindBy(css = "label[for='OriginalChannel-EMAIL']")
    private WebElementFacade emailOriginalChannelRadioButton;

    @FindBy(id = "label[for='OriginalChannel-POST']")
    private WebElementFacade postOriginalChannelRadioButton;

    @FindBy(id = "label[for='OriginalChannel-PHONE']")
    private WebElementFacade phoneOriginalChannelRadioButton;

    @FindBy(id = "label[for='OriginalChannel-NO10']")
    private WebElementFacade numberTenOriginalChannelRadioButton;

    @FindBy(id = "")
    private WebElementFacade dateOfCorrespondenceField;

    @FindBy(id = "")
    private WebElementFacade correspondentDropdown;

    @FindBy(id = "")
    private WebElementFacade referenceTextField;

    @FindBy(id = "")
    private WebElementFacade addCorrespondentButton;

    @FindBy(id = "")
    private WebElementFacade primaryCorrespondent;

    @FindBy(id = "")
    private WebElementFacade correspondentMemberNoRadioButton;

    @FindBy(id = "")
    private WebElementFacade correspondentMemberYesRadioButton;

    @FindBy(id = "")
    private WebElementFacade correspondentMemberDropdown;

    @FindBy(id = "")
    private WebElementFacade sendCopyToNumberTenTickBox;

    public void clickAddCorrespondentButton() {
        addCorrespondentButton.click();
    }

    public void clickCorrespondentIsAMember() {
        correspondentMemberYesRadioButton.click();
    }

    public void clickCorrespondentIsNotAMember() {
        correspondentMemberNoRadioButton.click();
    }

    public void clickEmailCorrespondenceChannelRadioButton() {
        emailOriginalChannelRadioButton.click();
    }

    public void clickLetterCorrespondenceChannelRadioButton() {
        postOriginalChannelRadioButton.click();
    }

    public void clickNo10CorrespondenceChannelRadioButton() {
        numberTenOriginalChannelRadioButton.click();
    }

    public void clickPhoneCorrespondenceChannelRadioButton() {
        phoneOriginalChannelRadioButton.click();
    }

    public void enterDateOfCorrespondence() {
        dateOfCorrespondenceField.clear();
        dateOfCorrespondenceField.sendKeys();
    }

    public void enterReferenceText() {
        referenceTextField.clear();
        referenceTextField.sendKeys("");
    }

    public void selectFromCorrespondentDropdown() {
        correspondentDropdown.selectByVisibleText("");
    }

    public void selectFromCorrespondentMemberDropdown() {
        correspondentMemberDropdown.selectByVisibleText("");
    }

}
