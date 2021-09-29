package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class AddCorrespondent extends BasePage {

    @FindBy(linkText = "Add a correspondent")
    public WebElementFacade addACorrespondentLink;

    @FindBy(xpath = "//div[@id='isMember-radios']//label[text()='No']")
    public WebElementFacade correspondentMemberNoRadioButton;

    @FindBy(xpath = "//div[@id='isMember-radios']//label[text()='Yes']")
    public WebElementFacade correspondentMemberYesRadioButton;

    @FindBy(xpath = "//label[text()='Member']/following-sibling::div//input")
    private WebElementFacade selectMPDropdown;

    @FindBy(id = "reference")
    private WebElementFacade correspondenceCaseReference;

    @FindBy(id = "type")
    private WebElementFacade correspondentTypeDropdown;

    @FindBy(id = "fullname")
    public WebElementFacade correspondentFullNameField;

    @FindBy(id = "address1")
    public WebElementFacade correspondentBuildingField;

    @FindBy(id = "address2")
    public WebElementFacade correspondentStreetField;

    @FindBy(id = "address3")
    public WebElementFacade correspondentTownOrCityField;

    @FindBy(id = "postcode")
    public WebElementFacade correspondentPostcodeField;

    @FindBy(id = "country")
    public WebElementFacade correspondentCountryDropdown;

    @FindBy(id = "telephone")
    public WebElementFacade correspondentTelephoneField;

    @FindBy(id = "email")
    public WebElementFacade correspondentEmailField;

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
        selectSpecificRadioButton("Yes");
    }

    public void selectCorrespondentNotMPRadioButton() {
        selectSpecificRadioButton("No");;
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
        correspondentFullNameField.sendKeys(fullName);
        setSessionVariable("correspondentFullName").to(fullName);
    }

    public void enterSecondaryCorrespondentFullName(String fullName) {
        correspondentFullNameField.sendKeys(fullName);
        setSessionVariable("secondCorrespondentFullName").to(fullName);
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

    public void fillCorrespondentFields() {
        enterCorrespondentFullName("Sam McTester");
        enterCorrespondentBuilding("1 Test House");
        enterCorrespondentStreet("Test Road");
        enterCorrespondentTownOrCity("Test Town");
        enterCorrespondentPostcode("AB1 2CD");
        selectCorrespondentCountry("United Kingdom");
        enterCorrespondentTelephoneNumber("01234 567890");
        enterCorrespondentEmailAddress("SamMcTester@Test.com");
        enterCorrespondenceReference("Ref-ABCD-1234");
    }

    public void fillMandatoryCorrespondentFieldsForSecondaryContact() {
        selectCorrespondentTypeFromDropdown("Correspondent");
        enterSecondaryCorrespondentFullName("Sam McTester");
        enterCorrespondentBuilding("1 Test House");
        enterCorrespondentStreet("Test Road");
        enterCorrespondentTownOrCity("Test Town");
        enterCorrespondentPostcode("AB1 2CD");
    }

    public void selectCorrespondentCountry(String country) {
        correspondentCountryDropdown.selectByVisibleText(country);
    }

    public void selectCorrespondentTypeFromDropdown(String correspondentType) {
        correspondentTypeDropdown.selectByVisibleText(correspondentType);
    }

    public void selectMemberOfParliament(String member) {
        safeClickOn(selectMPDropdown);
        waitABit(200);
        selectMPDropdown.sendKeys(member);
        waitABit(1000);
        selectMPDropdown.sendKeys(Keys.RETURN);
        setSessionVariable("correspondentFullName").to(memberOfParliamentName.getText());
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
        correspondentTypeDropdown.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        clickAddButton();
    }

    public void addAPublicCorrespondentOfType(String correspondentType) {
        selectToAddACorrespondent();
        if (!sessionVariableCalled("caseType").toString().contains("COMP")) {
            selectCorrespondentIsNotMP();
        }
        selectCorrespondentTypeFromDropdown(correspondentType);
        fillCorrespondentFields();
        clickAddButton();
    }

    public void addAPublicCorrespondentWithAReferenceNumber(String refNumber) {
        setSessionVariable("correspondentReferenceNumber").to(refNumber);
        selectToAddACorrespondent();
        selectCorrespondentIsNotMP();
        selectCorrespondentTypeFromDropdown("Constituent");
        fillCorrespondentFields();
        enterCorrespondenceReference(refNumber);
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
        assert (addACorrespondentLink.isDisplayed());
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

    public void assertNoPrimaryCorrespondentDisplayed() {
        assertThat(removeCorrespondentHyperText.isVisible(), is(false));
    }

}
