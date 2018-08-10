package com.hocs.test.pages.data_entry;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataEntry {

    @FindBy(id = "")
    private WebElementFacade emailCorrespondenceChannelRadioButton;

    @FindBy(id = "")
    private WebElementFacade letterCorrespondenceChannelRadioButton;

    @FindBy(id = "")
    private WebElementFacade phoneCorrespondenceChannelRadioButton;

    @FindBy(id = "")
    private WebElementFacade no10CorrespondenceChannelRadioButton;

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

    public void clickEmailCorrespondenceChannelRadioButton() {
        emailCorrespondenceChannelRadioButton.click();
    }

    public void clickLetterCorrespondenceChannelRadioButton() {
        letterCorrespondenceChannelRadioButton.click();
    }

    public void clickPhoneCorrespondenceChannelRadioButton() {
        phoneCorrespondenceChannelRadioButton.click();
    }

    public void clickNo10CorrespondenceChannelRadioButton() {
        no10CorrespondenceChannelRadioButton.click();
    }

}
