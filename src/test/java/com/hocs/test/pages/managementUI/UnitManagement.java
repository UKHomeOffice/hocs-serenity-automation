package com.hocs.test.pages.managementUI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UnitManagement extends Page {

    @FindBy(xpath = "//input[@id='displayName']")
    public WebElementFacade displayNameTextField;

    @FindBy(xpath = "//input[@id='shortCode']")
    public WebElementFacade shortCodeNameTextField;

    @FindBy(xpath = "//input[@value='Submit']")
    public WebElementFacade submitButton;

    @FindBy(xpath = "//a[@href='#displayName-error']")
    public WebElementFacade displayNameRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#shortCode-error']")
    public WebElementFacade shortCodeRequiredErrorMessage;

    public void assertAddUnitPageTitle() {
        assertThat(managementUIPageTitle.getText(), is("Add Unit"));
    }

    public void assertViewUnitPageTitle() {
        assertThat(managementUIPageTitle.getText(), is("Unit List"));
    }

    public void assertDisplayNameAndShortCodeErrorMessages() {
        assertThat(displayNameRequiredErrorMessage.getText(), is("The Display Name is required"));
        assertThat(shortCodeRequiredErrorMessage.getText(), is("The Short Code is required"));
    }

}
