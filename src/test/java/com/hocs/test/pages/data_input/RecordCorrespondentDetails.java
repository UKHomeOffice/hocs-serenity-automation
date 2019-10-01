package com.hocs.test.pages.data_input;

import static net.serenitybdd.core.Serenity.*;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class RecordCorrespondentDetails extends Page {

    @FindBy(className = "govuk-typeahead__control")
    private WebElementFacade memberDropdownInitialize;

    @FindBy(id = "react-select-2-input")
    private WebElementFacade memberDropdown;

    @FindBy(id = "reference")
    private WebElementFacade correspondenceCaseReference;

    @FindBy(id = "type")
    private WebElementFacade correspondentTypeDropdown;

    @FindBy(id = "fullname")
    private WebElementFacade correspondentFullNameField;

    @FindBy(id = "address1")
    private WebElementFacade correspondentBuildingField;

    @FindBy(id = "address2")
    private WebElementFacade correspondentStreetField;

    @FindBy(id = "address3")
    private WebElementFacade correspondentTownOrCityField;

    @FindBy(id = "postcode")
    private WebElementFacade correspondentPostcodeField;

    @FindBy(id = "country")
    private WebElementFacade correspondentCountryDropdown;

    @FindBy(id = "telephone")
    private WebElementFacade correspondentTelephoneField;

    @FindBy(id = "email")
    private WebElementFacade correspondentEmailField;

    @FindBy(linkText = "Add a correspondent")
    private WebElementFacade addACorrespondentButton;

    @FindBy(css = "input[name='Correspondents']")
    private WebElementFacade selectPrimaryCorrespondentRadioButton;

    @FindBy(className = "govuk-radios__label")
    private WebElementFacade primaryCorrespondentName;

    @FindBy(css = "label[for='AdditionalCorrespondent-FALSE']")
    private WebElementFacade additionalCorrespondentNoRadioButton;

    @FindBy(css = "label[for='AdditionalCorrespondent-TRUE']")
    public WebElementFacade additionalCorrespondentYesRadioButton;

    @FindBy(css = "label[for='isMember-false']")
    private WebElementFacade correspondentNotMPRadioButton;

    @FindBy(css = "label[for='isMember-true']")
    private WebElementFacade correspondentIsMPRadioButton;

    public void assertPageTitle() {
        assertTitle("Record Correspondent Details");
    }

    public void assertPrimaryCorrespondent() {
        String expectedPrimaryCorrespondent = sessionVariableCalled("fullName");
        assertThat(getPrimaryCorrespondent(), is(expectedPrimaryCorrespondent));
    }

    public void clickAdditionalCorrespondentYes() {
        additionalCorrespondentYesRadioButton.click();
    }

    public void selectAddACorrespondent() {
        addACorrespondentButton.click();
    }

    public void selectNotMPRadioButton() {
        correspondentNotMPRadioButton.click();
    }

    public void enterCorrespondentFullName(String fullName) {
        typeInto(correspondentFullNameField, fullName);
        setSessionVariable("fullName").to(fullName);
    }

    public void enterCorrespondentBuilding(String building) {
        typeInto(correspondentBuildingField, building);
    }

    public void enterCorrespondentStreet(String street) {
        typeInto(correspondentStreetField, street);
    }

    public void enterCorrespondentTownOrCity(String location) {
        typeInto(correspondentTownOrCityField, location);
    }

    public void enterCorrespondentPostcode(String postcode) {
        typeInto(correspondentPostcodeField, postcode);
    }

    public void enterCorrespondentTelephoneNumber(String number) {
        typeInto(correspondentTelephoneField, number);
    }

    public void enterCorrespondentEmailAddress(String email) {
        typeInto(correspondentEmailField, email);
    }

    public void enterCorrespondenceReference(String reference) {
        typeInto(correspondenceCaseReference, reference);
    }

    public String getPrimaryCorrespondent() {
        return primaryCorrespondentName.getText();
    }

    public void addAMemberOfParliamentCorrespondent(String member) {
        memberDropdownInitialize.click();
        typeInto(memberDropdown, member);
        waitABit(500);
        memberDropdown.sendKeys(Keys.RETURN);
        clickAddButton();
        waitABit(2000);
        clickAddButton();
    }

    public void addAMemberOfPublicCorrespondent() {
        selectAddACorrespondent();
        selectNotMPRadioButton();
        clickContinueButton();
        fillMandatoryCorrespondentFields();
        clickAddButton();
    }

    public void fillMandatoryCorrespondentFields() {
        selectCorrespondentTypeFromDropdown("Correspondent");
        enterCorrespondentFullName("Test Testing-" + generateRandomString());
        enterCorrespondentBuilding("1");
        enterCorrespondentPostcode("S1 1AA");
    }

    public void selectCorrespondentType() {
        selectCorrespondentTypeFromDropdown("Correspondent");
    }

    public void selectCorrespondentCountry(String country) {
        correspondentCountryDropdown.selectByVisibleText(country);
    }

    public void selectCorrespondentTypeFromDropdown(String correspondentType) {
        correspondentTypeDropdown.selectByVisibleText(correspondentType);
    }

    public void selectMemberFromDropdownByName(String member) {
        memberDropdown.selectByVisibleText(member);
    }

    public void selectMemberFromDropdownByIndex(int index) {
        memberDropdown.selectByIndex(index);
    }

}
