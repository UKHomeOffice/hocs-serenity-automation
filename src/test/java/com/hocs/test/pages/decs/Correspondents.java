package com.hocs.test.pages.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class Correspondents extends BasePage {

    RecordCaseData recordCaseData;

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
        selectSpecificRadioButton("No");
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
        enterSpecificTextIntoTextFieldWithHeading(fullName, "Full Name");
        setSessionVariable("correspondentFullName").to(fullName);
    }

    public void enterCorrespondentBuilding(String building) {
        enterSpecificTextIntoTextFieldWithHeading(building, "Address line 1");
        setSessionVariable("correspondentBuilding").to(building);
    }

    public void enterCorrespondentStreet(String street) {
        enterSpecificTextIntoTextFieldWithHeading(street, "Address line 2");
        setSessionVariable("correspondentStreet").to(street);
    }

    public void enterCorrespondentTownOrCity(String townOrCity) {
        enterSpecificTextIntoTextFieldWithHeading(townOrCity, "Town or City");
        setSessionVariable("correspondentTownOrCity").to(townOrCity);
    }

    public void enterCorrespondentPostcode(String postcode) {
        enterSpecificTextIntoTextFieldWithHeading(postcode, "Postcode");
        setSessionVariable("correspondentPostcode").to(postcode);
    }

    public void selectACorrespondentCountry() {
        String selectedCountry = selectRandomOptionFromDropdownWithHeading("Country");
        setSessionVariable("correspondentCountry").to(selectedCountry);
    }

    public void enterCorrespondentTelephoneNumber(String telephoneNumber) {
        enterSpecificTextIntoTextFieldWithHeading(telephoneNumber, "Telephone");
        setSessionVariable("correspondentTelephoneNumber").to(telephoneNumber);
    }

    public void enterCorrespondentEmailAddress(String email) {
        String inboundChannel = sessionVariableCalled("foiInboundChannel");
        if (foiCase() && inboundChannel.equalsIgnoreCase("POST")) {
            enterSpecificTextIntoTextFieldWithHeading(email, "Email Address (Optional)");
        } else {
            enterSpecificTextIntoTextFieldWithHeading(email, "Email Address");
        }
        setSessionVariable("correspondentEmail").to(email);
    }

    public void enterCorrespondenceReference(String reference) {
        if (foiCase()) {
            enterSpecificTextIntoTextFieldWithHeading(reference, "Requester's Reference (Optional)");
        } else {
            enterSpecificTextIntoTextAreaWithHeading(reference, "Enter any references given");
        }
        setSessionVariable("correspondentReference").to(reference);
    }

    public void enterCorrespondentOrganisation() {
        String organisation;
        if (foiCase()) {
            organisation = enterTextIntoTextFieldWithHeading("Organisation (Optional)");
        } else {
            organisation = enterTextIntoTextFieldWithHeading("Organisation");
        }
        setSessionVariable("correspondentOrganisation").to(organisation);
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
        if (toCase() || foiCase()) {
            enterCorrespondentOrganisation();
        }
        enterCorrespondentBuilding("1 Test House");
        enterCorrespondentStreet("Test Road");
        enterCorrespondentTownOrCity("Test Town");
        enterCorrespondentPostcode("AB1 2CD");
        selectACorrespondentCountry();
        enterCorrespondentTelephoneNumber("01234 567890");
        enterCorrespondentEmailAddress("SamMcTester@Test.com");
        enterCorrespondenceReference("Ref-ABCD-1234");
    }

    public void fillMandatoryCorrespondentFieldsForSecondaryContact() {
        selectCorrespondentTypeFromDropdown("Correspondent");
        enterCorrespondentFullName("Sam McTester");
        setSessionVariable("secondCorrespondentFullName").to("Sam McTester");
        enterCorrespondentBuilding("1 Test House");
        enterCorrespondentStreet("Test Road");
        enterCorrespondentTownOrCity("Test Town");
        enterCorrespondentPostcode("AB1 2CD");
    }

    public void selectCorrespondentTypeFromDropdown(String correspondentType) {
        correspondentTypeDropdown.waitUntilVisible();
        correspondentTypeDropdown.selectByVisibleText(correspondentType);
    }

    public void selectSpecificMemberOfParliament(String member) {
        String correspondentFullName = selectSpecificOptionFromTypeaheadWithHeading(member, "Member");
        setSessionVariable("correspondentFullName").to(correspondentFullName);
        clickAddButton();
    }

    public void selectRandomMemberOfParliament() {
        String correspondentFullName = selectRandomOptionFromTypeaheadWithHeading("Member");
        setSessionVariable("correspondentFullName").to(correspondentFullName);
        clickAddButton();
    }

    public void addASpecificMemberCorrespondent(String member) {
        selectToAddACorrespondent();
        selectCorrespondentIsMP();
        selectSpecificMemberOfParliament(member);
        correspondentTypeDropdown.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        clickAddButton();
    }

    public void addAMemberCorrespondent() {
        selectToAddACorrespondent();
        selectCorrespondentIsMP();
        selectRandomMemberOfParliament();
        correspondentTypeDropdown.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible();
        clickAddButton();
    }

    public void addANonMemberCorrespondentOfType(String correspondentType) {
        selectToAddACorrespondent();
        if (!complaintCase() && !toCase()) {
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

    public void confirmPrimaryCorrespondent() {
        WebElementFacade selectedPrimaryCorrespondent = findBy("//input[@name='Correspondents'][@checked]/following-sibling::label");
        selectedPrimaryCorrespondent.waitUntilVisible();
        recordCaseData.addHeadingAndValueRecord("Which is the primary correspondent?", selectedPrimaryCorrespondent.getText());
        setSessionVariable("primaryCorrespondent").to(selectedPrimaryCorrespondent.getText());
        if (dcuCase()) {
            clickTheButton("Finish");
        }
        if (mpamCase()) {
            clickTheButton("Move to Triage");
        }
        if (mtsCase() | complaintCase() | toCase()) {
            clickTheButton("Continue");
        }
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
