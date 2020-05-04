package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.*;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class AddCorrespondent extends BasePage {

    @FindBy(linkText = "Add a correspondent")
    private WebElementFacade addACorrespondentLink;

    @FindBy(css = "label[for='isMember-false']")
    public WebElementFacade correspondentMemberNoRadioButton;

    @FindBy(css = "label[for='isMember-true']")
    public WebElementFacade correspondentMemberYesRadioButton;

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    private WebElementFacade selectMPDropdown;

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

    @FindBy(xpath = "//input[@name='Correspondents'][@checked]/following-sibling::label")
    private WebElementFacade primaryCorrespondentName;

    @FindBy(xpath = "//input[@name='Correspondents'][not(@checked)]/following-sibling::label")
    private WebElementFacade secondaryCorrespondentName;

    @FindBy(xpath = "//div[contains(@class,'govuk-typeahead__single-value')]")
    public WebElementFacade memberOfParliamentName;

    @FindBy(xpath = "//a[text()='The correspondent type must be provided']")
    public WebElementFacade correspondentTypeMustBeProvidedErrorMessage;

    @FindBy(xpath = "//a[text()='Member is required']")
    public WebElementFacade memberIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='The correspondent must have a type']")
    public WebElementFacade correspondentMustHaveATypeErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#fullname-error')]")
    public WebElementFacade correspondentNameMustBeEnteredErrorMessage;

    @FindBy(xpath = "//a[contains(@href, '#Correspondents-error')]")
    public WebElementFacade whichIsThePrimaryCorrespondentIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Remove']")
    public WebElementFacade removeCorrespondentHyperText;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElementFacade editCorrespondentHyperText;

    private void selectAddACorrespondentLink() {
        safeClickOn(addACorrespondentLink);
    }

    public void selectToAddACorrespondent() {
        try {
            selectAddACorrespondentLink();
        } catch (Exception e) {
            waitABit(2000);
            selectAddACorrespondentLink();
        }
    }

    public void selectCorrespondentIsMPRadioButton() {
        safeClickOn(correspondentMemberYesRadioButton);
    }

    public void selectCorrespondentNotMPRadioButton() {
        safeClickOn(correspondentMemberNoRadioButton);
    }

    public void selectCorrespondentIsMP() {
        try {
            selectCorrespondentIsMPRadioButton();
        } catch (Exception e) {
            waitABit(2000);
            selectCorrespondentIsMPRadioButton();
        }
        safeClickOn(continueButton);
    }

    public void selectCorrespondentIsNotMP() {
        try {
            selectCorrespondentNotMPRadioButton();
        } catch (Exception e) {
            waitABit(2000);
            selectCorrespondentNotMPRadioButton();
        }
        safeClickOn(continueButton);
    }

    public void enterCorrespondentFullName(String fullName) {
        typeInto(correspondentFullNameField, fullName);
        setSessionVariable("correspondentFullName").to(fullName);
    }

    public void enterSecondaryCorrespondentFullName(String fullName) {
        typeInto(correspondentFullNameField, fullName);
        setSessionVariable("secondCorrespondentFullName").to(fullName);
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

    public String getSecondaryCorrespondent() {
        return secondaryCorrespondentName.getText();
    }

    public void setSecondCorrespondentAsPrimaryCorrespondent() {
        setSessionVariable("primaryCorrespondent").to(secondaryCorrespondentName.getText());
        safeClickOn(secondaryCorrespondentName);
    }

    public void fillMandatoryPublicCorrespondentFields() {
        selectCorrespondentTypeFromDropdown("Constituent");
        enterCorrespondentFullName("First Correspondent-" + generateRandomString());
        enterCorrespondentBuilding("1");
        enterCorrespondentPostcode("S1 1AA");
    }

    public void fillMandatoryCorrespondentFieldsForSecondaryContact() {
        selectCorrespondentTypeFromDropdown("Correspondent");
        enterSecondaryCorrespondentFullName("Second Correspondent-" + generateRandomString());
        enterCorrespondentBuilding("1");
        enterCorrespondentPostcode("S1 1AA");
    }

    public void selectCorrespondentCountry(String country) {
        correspondentCountryDropdown.selectByVisibleText(country);
    }

    public void selectCorrespondentTypeFromDropdown(String correspondentType) {
        correspondentTypeDropdown.selectByVisibleText(correspondentType);
    }

    public void selectMemberOfParliament(String member) {
        safeClickOn(selectMPDropdown);
        selectMPDropdown.sendKeys(member);
        waitABit(500);
        selectMPDropdown.sendKeys(Keys.RETURN);
        setSessionVariable("memberOfParliamentName").to(memberOfParliamentName.getText());
        clickAddButton();
    }

    public void selectMemberFromDropdownByName(String member) {
        selectMPDropdown.selectByVisibleText(member);
    }

    public void selectMemberFromDropdownByIndex(int index) {
        selectMPDropdown.selectByIndex(index);
    }

    public void addAMemberCorrespondent(String member) {
        selectToAddACorrespondent();
        selectCorrespondentIsMP();
        selectMemberOfParliament(member);
        waitABit(2000);
        clickAddButton();
    }

    public void addAPublicCorrespondent() {
        selectToAddACorrespondent();
        selectCorrespondentIsNotMP();
        fillMandatoryPublicCorrespondentFields();
        clickAddButton();
    }

    public void removePrimaryCorrespondent() {
        safeClickOn(removeCorrespondentHyperText);
        clickTheButton("Remove");
    }

    public void editPrimaryCorrespondent() {
        safeClickOn(editCorrespondentHyperText);
        correspondentFullNameField.clear();
        enterCorrespondentFullName("Edited Correspondent-" + generateRandomString());
        clickTheButton("Save");
    }

    public void assertAddACorrespondentLinkIsDisplayed() {
        waitFor(addACorrespondentLink);
        assert(addACorrespondentLink.isDisplayed());
    }

    public void assertPageTitle() {
        assertPageTitle("Record Correspondent Details");
    }

    public void assertPrimaryCorrespondent() {
        String expectedPrimaryCorrespondent = sessionVariableCalled("correspondentFullName");
        assertThat(getPrimaryCorrespondent(), is(expectedPrimaryCorrespondent));
    }

    public void assertSecondaryCorrespondent() {
        String expectedSecondaryCorrespondent = sessionVariableCalled("secondCorrespondentFullName");
        assertThat(getSecondaryCorrespondent(), is(expectedSecondaryCorrespondent));
    }

    public void assertCorrespondentTypeMustBeSelectedErrorMessage() {
        correspondentTypeMustBeProvidedErrorMessage.shouldContainText("The correspondent type must be provided");
    }

    public void assertMemberIsRequiredErrorMessage() {
        memberIsRequiredErrorMessage.shouldContainText("Member is required");
    }

    public void assertCorrespondentTypeDropDownErrorMessage() {
        correspondentMustHaveATypeErrorMessage.shouldContainText("The correspondent must have a type");
    }

    public void assertCorrespondentFullNameErrorMessage() {
        correspondentNameMustBeEnteredErrorMessage.shouldContainText("The correspondent's full name is required");
    }

    public void assertWhichIsThePrimaryCorrespondentIsRequiredErrorMessage() {
        whichIsThePrimaryCorrespondentIsRequiredErrorMessage.shouldContainText("Which is the primary correspondent? is required");
    }

    public void assertNoPrimaryCorrespondentDisplayed() {
        assertThat(removeCorrespondentHyperText.isVisible(), is(false));
    }
}
