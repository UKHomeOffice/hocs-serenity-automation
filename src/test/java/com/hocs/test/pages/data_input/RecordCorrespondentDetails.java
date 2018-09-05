package com.hocs.test.pages.data_input;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class RecordCorrespondentDetails extends Page {

    @FindBy(id = "Member")
    private WebElementFacade memberDropdown;

    @FindBy(id = "CorrespondenceReference")
    private WebElementFacade correspondenceCaseReference;

    @FindBy(id = "Correspondent Type")
    private WebElementFacade correspondentTypeDropdown;

    @FindBy(id = "CTitle")
    private WebElementFacade correspondentTitleField;

    @FindBy(id = "CFirstName")
    private WebElementFacade correspondentFirstNameField;

    @FindBy(id = "CLastName")
    private WebElementFacade correspondentLastNameField;

    @FindBy(id = "CBuilding")
    private WebElementFacade correspondentBuildingField;

    @FindBy(id = "CStreet")
    private WebElementFacade correspondentStreetField;

    @FindBy(id = "CTownOrCity")
    private WebElementFacade correspondentTownOrCityField;

    @FindBy(id = "CPostcode")
    private WebElementFacade correspondentPostcodeField;

    @FindBy(id = "Country")
    private WebElementFacade correspondentCountryDropdown;

    @FindBy(id = "CPhone")
    private WebElementFacade correspondentTelephoneField;

    @FindBy(id = "CEmail")
    private WebElementFacade correspondentEmailField;

    @FindBy(css = "input[name='Correspondents']")
    private WebElementFacade selectPrimaryCorrespondentRadioButton;

    public void assertPageTitle() {
        assertTitle("Record Correspondent Details");
    }


    public void enterCorrespondentTitle(String title) {
        correspondentTitleField.sendKeys(title);
    }

    public void enterCorrespondentFirstName(String firstName) {
        correspondentFirstNameField.sendKeys(firstName);
    }

    public void enterCorrespondentLastName(String lastName) {
        correspondentLastNameField.sendKeys(lastName);
    }

    public void enterCorrespondentBuilding(String building) {
        correspondentBuildingField.sendKeys(building);
    }

    public void enterCorrespondentStreet(String street) {
        correspondentStreetField.sendKeys(street);
    }

    public void enterCorrespondentTownOrCity(String location) {
        correspondentTownOrCityField.sendKeys(location);
    }

    public void enterCorrespondentPostcode(String postcode) {
        correspondentPostcodeField.sendKeys(postcode);
    }

    public void enterCorrespondentTelephoneNumber(String number) {
        correspondentTelephoneField.sendKeys(number);
    }

    public void enterCorrespondentEmailAddress(String email) {
        correspondentEmailField.sendKeys(email);
    }

    public void enterCorrespondenceReference(String reference) {
        correspondenceCaseReference.sendKeys(reference);
    }

    public void fillMandatoryFields() {
        correspondentFirstNameField.sendKeys("Test");
        correspondentLastNameField.sendKeys("Testing - " + generateRandomString());
        correspondentBuildingField.sendKeys("1");
        correspondentPostcodeField.sendKeys("S1 1AA");
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
