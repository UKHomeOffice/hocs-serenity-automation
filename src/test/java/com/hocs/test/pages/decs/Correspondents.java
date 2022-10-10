package com.hocs.test.pages.decs;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

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

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElementFacade editCorrespondentHyperText;

    String correspondenceReference = "Ref-ABCD-1234";

    private void selectAddACorrespondentLink() {
        clickTheLink("Add a");
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
        clickContinueButton();
    }

    public void selectCorrespondentIsNotMP() {
        try {
            selectCorrespondentNotMPRadioButton();
        } catch (Exception e) {
            waitABit(2000);
            selectCorrespondentNotMPRadioButton();
        }
        clickContinueButton();
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

    public void changePrimaryCorrespondent() {
        String newPrimaryCorrespondent;
        if (getDECSCurrentPageTitle().equals("Manage People")) {
            newPrimaryCorrespondent = selectDifferentRadioButtonFromGroupWithHeading("Person we will write back to");
        } else {
            newPrimaryCorrespondent = selectDifferentRadioButtonFromGroupWithHeading("Which is the primary correspondent?");
        }
        setSessionVariable("primaryCorrespondent").to(newPrimaryCorrespondent);
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
        enterCorrespondenceReference(correspondenceReference);
    }

    public void fillMandatoryCorrespondentFieldsForSecondaryContact() {
        selectASpecificCorrespondentType("Correspondent");
        enterCorrespondentFullName("Sam McTester");
        setSessionVariable("secondCorrespondentFullName").to("Sam McTester");
        enterCorrespondentBuilding("1 Test House");
        enterCorrespondentStreet("Test Road");
        enterCorrespondentTownOrCity("Test Town");
        enterCorrespondentPostcode("AB1 2CD");
    }

    public void selectACorrespondentType() {
        selectRandomOptionFromDropdownWithHeading("Correspondent Type");
    }

    public void selectASpecificCorrespondentType(String correspondentType) {
        selectSpecificOptionFromDropdownWithHeading(correspondentType, "Correspondent Type");
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
        waitForDECSPageWithTitle("Member Details");
        clickAddButton();
    }

    public void addAMemberCorrespondent() {
        selectToAddACorrespondent();
        selectCorrespondentIsMP();
        selectRandomMemberOfParliament();
        waitForDECSPageWithTitle("Member Details");
        clickAddButton();
    }

    public void addANonMemberCorrespondentOfType(String correspondentType) {
        selectToAddACorrespondent();
        if (!complaintCase() && !toCase()) {
            selectCorrespondentIsNotMP();
        }
        if (correspondentType.equalsIgnoreCase("NON-MEMBER")) {
            selectACorrespondentType();
        } else {
            selectASpecificCorrespondentType(correspondentType);
        }
        fillCorrespondentFields();
        clickAddButton();
    }

    public void addANonMemberCorrespondentWithASpecificReferenceNumber(String refNumber) {
        this.correspondenceReference = refNumber;
        addANonMemberCorrespondentOfType("Non-member");
        setSessionVariable("correspondentReferenceNumber").to(refNumber);
    }

    public void removeACorrespondent() {
        clickTheLink("Remove");
        clickTheButton("Remove");
    }

    public void removeASpecificCorrespondent(String correspondent) {
        WebElementFacade correspondentSpecificRemoveLink = findBy("//label[contains(text(), '" + correspondent + "')]/ancestor::tr//a[text()='Remove']");
        safeClickOn(correspondentSpecificRemoveLink);
        clickTheButton("Remove");
    }

    public void editPrimaryCorrespondent() {
        clickTheLink("Edit");
        enterCorrespondentFullName("Edited Correspondent-" + generateRandomString());
        clickTheButton("Save");
    }

    public void confirmPrimaryCorrespondent() {
        WebElementFacade selectedPrimaryCorrespondent = findBy("//input[@name='Correspondents'][@checked]/following-sibling::label");
        selectedPrimaryCorrespondent.waitUntilVisible();
        String primaryCorrespondentsName = selectedPrimaryCorrespondent.getText();
        recordCaseData.addHeadingAndValueRecord("Which is the primary correspondent?", primaryCorrespondentsName);
        setSessionVariable("primaryCorrespondent").to(primaryCorrespondentsName);
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
        assertThat(linkIsCurrentlyVisible("Edit"), is(false));
    }
}
