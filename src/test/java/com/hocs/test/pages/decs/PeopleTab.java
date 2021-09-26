package com.hocs.test.pages.decs;

import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PeopleTab extends BasePage {

    Correspondents correspondents;

    @FindBy(xpath = "//a[text()='People']")
    public WebElementFacade peopleTab;

    @FindBy(xpath = "//a[text()='Manage People']")
    public WebElementFacade managePeopleHypertext;

    @FindBy(xpath = "//a[text()='correspondent']")
    public WebElementFacade addACorrespondentHypertext;

    @FindBy(xpath = "//h2[contains(text(), '(primary)')]/following-sibling::table[1]//th[text()='Name']/following-sibling::td")
    public WebElementFacade primaryCorrespondentName;

    @FindBy(xpath = "//h2[contains(text(), '(primary)')]/following-sibling::table[1]//th[text()='Address']/following-sibling::td")
    public WebElementFacade primaryCorrespondentAddress;

    @FindBy(xpath = "//h2[contains(text(), '(primary)')]/following-sibling::table[1]//th[text()='Email address']/following-sibling::td")
    public WebElementFacade primaryCorrespondentEmailAddress;

    @FindBy(xpath = "//input[@value='Remove']")
    public WebElementFacade removeButton;

    public void selectPeopleTab() {
        safeClickOn(peopleTab);
    }

    public void addAMemberCorrespondent(String newCorrespondent) {
        safeClickOn(managePeopleHypertext);
        correspondents.addAMemberCorrespondent(newCorrespondent);
        setSessionVariable("correspondentFullName").to(newCorrespondent);
    }

    public void addAPublicCorrespondent() {
        safeClickOn(managePeopleHypertext);
        correspondents.addAPublicCorrespondentOfType("Constituent");
    }

    public void editCorrespondent(String detail, String correspondent) {
        safeClickOn(managePeopleHypertext);
        WebElementFacade editHypertext = findBy("//label[contains(text(), '" + correspondent + "')]/ancestor::tr//a[text()='Edit']");
        safeClickOn(editHypertext);
        switch (detail.toUpperCase()) {
            case "FULL NAME":
                this.correspondents.correspondentFullNameField.clear();
                this.correspondents.correspondentFullNameField.sendKeys("Test - correspondent name");
                break;
            case "BUILDING":
                this.correspondents.correspondentBuildingField.clear();
                this.correspondents.correspondentBuildingField.sendKeys("Test - correspondent building");
                break;
            case "STREET":
                this.correspondents.correspondentStreetField.clear();
                this.correspondents.correspondentStreetField.sendKeys("Test - correspondent street");
                break;
            case "TOWN OR CITY":
                this.correspondents.correspondentTownOrCityField.clear();
                this.correspondents.correspondentTownOrCityField.sendKeys("Test - correspondent town/city");
                break;
            case "POSTCODE":
                this.correspondents.correspondentPostcodeField.clear();
                this.correspondents.correspondentPostcodeField.sendKeys("Test - correspondent postcode");
                break;
            case "TELEPHONE":
                this.correspondents.correspondentTelephoneField.clear();
                this.correspondents.correspondentTelephoneField.sendKeys("Test - correspondent telephone");
                break;
            case "EMAIL ADDRESS":
                this.correspondents.correspondentEmailField.clear();
                this.correspondents.correspondentEmailField.sendKeys("Test - correspondent email address");
                break;
            default:
                pendingStep(detail + " is not defined within " + getMethodName());
        }
        safeClickOn(saveButton);
    }

    public void removeCorrespondent(String correspondent) {
        safeClickOn(managePeopleHypertext);
        WebElementFacade removeHypertext = findBy("//label[contains(text(), '" + correspondent + "')]/ancestor::tr//a[text()='Remove']");
        safeClickOn(removeHypertext);
        safeClickOn(removeButton);
    }

    public void changePrimaryCorrespondent(String newPrimaryCorrespondent) {
        safeClickOn(managePeopleHypertext);
        WebElementFacade radioButtonOfNewPrimaryCorrespondent = findBy("//label[contains(text(), '"+ newPrimaryCorrespondent + "')]");
        safeClickOn(radioButtonOfNewPrimaryCorrespondent);
        safeClickOn(finishButton);
    }

    public void assertNewCorrespondentIsDisplayed() {
        int n = 0;
        if (!managePeopleHypertext.isVisible()) {
            safeClickOn(peopleTab);
        }
        List<WebElementFacade> correspondentNames = findAll("//th[text()='Name']/following-sibling::td");
        int listSize = correspondentNames.size();
        boolean isCorrespondentDisplayed = false;
        while (n <= (listSize - 1) && !isCorrespondentDisplayed) {
            if (correspondentNames.get(n).getText().contains(sessionVariableCalled("correspondentFullName"))) {
                isCorrespondentDisplayed = true;
            }
            n++;
        }
        assertThat(isCorrespondentDisplayed, is(true));
    }

    public void assertNewPrimaryCorrespondent(String newPrimaryCorrespondent) {
        if (!managePeopleHypertext.isVisible()) {
            safeClickOn(peopleTab);
        }
        assertThat(primaryCorrespondentName.getText().contains(newPrimaryCorrespondent), is(true));
    }

    public void assertCorrespondentHasBeenRemoved(String correspondent) {
        continueButton.waitUntilVisible();
        safeClickOn(peopleTab);
        List<WebElementFacade> correspondentNames = findAll("//th[text()='Name']/following-sibling::td");
        int n = 0;
        while (n < (correspondentNames.size())) {
            assertThat(correspondentNames.get(n).getText().contains(correspondent), is(false));
            n++;
        }
    }

    public void assertCorrespondentIsAttachedToCase(String correspondentName) {
        boolean correspondentPresent = false;
        List<WebElementFacade> correspondentsNameElements = findAll("//th[text()='Name']/following-sibling::td");
        for(WebElementFacade correspondentNameElement: correspondentsNameElements) {
            if(correspondentNameElement.getText().toUpperCase().contains(correspondentName.toUpperCase())){
                correspondentPresent = true;
            }
        }
        assertThat(correspondentPresent, is(true));
    }

    public void assertMPCorrespondentIsAddedToTheCase(String mpName) {
        boolean mpPresent = false;
        int n = 0;
        List<WebElementFacade> listOfMemberCorrespondents = findAll("//h2[contains(text(), 'Member')]/following-sibling::table//th[text()"
                + "='Name']/following-sibling::td");
        while (n <= listOfMemberCorrespondents.size()) {
            if (listOfMemberCorrespondents.get(n).getText().toUpperCase().contains(mpName.toUpperCase())) {
                mpPresent = true;
                break;
            }
            n++;
        }
        assertThat(mpPresent, is(true));
    }

    public void assertPublicCorrespondentAddedToTheCase(String correspondentName) {
        boolean correspondentPresent = false;
        int n = 0;
        List<WebElementFacade> listOfPublicCorrespondents = findAll("//h2[not(contains(text(), 'Member'))]/following-sibling::table//th[text()"
                + "='Name']/following-sibling::td");
        while (n < listOfPublicCorrespondents.size()) {
            if (listOfPublicCorrespondents.get(n).getText().toUpperCase().contains(correspondentName.toUpperCase())) {
                correspondentPresent = true;
                break;
            }
            n++;
        }
        assertThat(correspondentPresent, is(true));

    }

    public void assertCorrespondentEmailAddress(String emailAddress) {
        assertThat(primaryCorrespondentEmailAddress.getText().equalsIgnoreCase(emailAddress), is(true));
    }

    public void assertCorrespondentPostcode(String postcode) {
        primaryCorrespondentAddress.shouldContainText(postcode);
    }
}
