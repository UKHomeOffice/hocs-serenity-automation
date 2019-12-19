package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.base_page.Page;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @FindBy(css = ".govuk-error-summary__body")
    public WebElementFacade errorMessageContents;

    @FindBy(xpath = "//caption[contains(text(), 'Units')]/following-sibling::tbody")
    public WebElementFacade listOfUnits;

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

    public void inputUnitDisplayName(String unitDisplayName) {
        typeInto(displayNameTextField, unitDisplayName);
        setSessionVariable("unitDisplayName").to(unitDisplayName);
    }

    public void inputUnitShortCode(String unitShortCode) {
        typeInto(shortCodeNameTextField, unitShortCode);
        setSessionVariable("unitShortCode").to(unitShortCode);
    }

    public void inputNewUnitDisplayName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String newUnitDisplayName = "UNIT DISPLAY NAME " + formatter.format(LocalDateTime.now());
        inputUnitDisplayName(newUnitDisplayName);
    }

    public void inputNewUnitShortCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String newUnitShortCode = "UNIT SHORT CODE " + formatter.format(LocalDateTime.now());
        inputUnitShortCode(newUnitShortCode);
    }

    public void assertUnitAlreadyExistsErrorMessage() {
        assertThat(errorMessageContents.getText(), is("A unit with those details already exists"));
    }

    public void assertListOfUnitsVisible() {
        assertElementIsDisplayed(listOfUnits);
    }

    public void assertListContainsCreatedUnit() {
        WebElementFacade desiredUnit = findBy("(//th[contains(text(), '" + sessionVariableCalled("unitDisplayName") + "')"
                + "])");
        assertElementIsDisplayed(desiredUnit);
    }
}
