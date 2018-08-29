package com.hocs.test.pages.data_input;

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

    @FindBy(id = "DateOfCorrespondence-day")
    private WebElementFacade dateOfCorrespondenceDayField;

    @FindBy(id = "DateOfCorrespondence-month")
    private WebElementFacade dateOfCorrespondenceMonthField;

    @FindBy(id = "DateOfCorrespondence-year")
    private WebElementFacade dateOfCorrespondenceYearField;

    @FindBy(id = "")
    private WebElementFacade correspondentDropdown;

    @FindBy(id = "")
    private WebElementFacade referenceTextField;

    @FindBy(linkText = "Add a Correspondent")
    private WebElementFacade addCorrespondentLink;

    @FindBy(id = "")
    private WebElementFacade primaryCorrespondent;

    @FindBy(css = "input[id='CorrespondentIsMember-FALSE']")
    private WebElementFacade correspondentMemberNoRadioButton;

    @FindBy(css = "input[id='CorrespondentIsMember-TRUE']")
    private WebElementFacade correspondentMemberYesRadioButton;

    @FindBy(id = "NumberTen-SendCopy")
    private WebElementFacade sendCopyToNumberTenTickBox;

    @FindBy(css = "label[for='AdditionalCorrespondent-FALSE']")
    private WebElementFacade addCorrespondentNoRadioButton;

    @FindBy(css = "label[for='AdditionalCorrespondent-TRUE']")
    private WebElementFacade addCorrespondentYesRadioButton;


    public void clickAddCorrespondentButton() {
        addCorrespondentLink.click();
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

    public void enterDayOfCorrespondence() {
        dateOfCorrespondenceDayField.clear();
        dateOfCorrespondenceDayField.sendKeys();
    }

    public void enterMonthOfCorrespondence() {
        dateOfCorrespondenceMonthField.clear();
        dateOfCorrespondenceMonthField.sendKeys();
    }

    public void enterYearOfCorrespondence() {
        dateOfCorrespondenceYearField.clear();
        dateOfCorrespondenceYearField.sendKeys();
    }

    public void enterReferenceText() {
        referenceTextField.clear();
        referenceTextField.sendKeys("");
    }

    public void tickSendCopyToNumber10() {
        sendCopyToNumberTenTickBox.click();
    }

}
