package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UnitManagement extends BasePage {

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

    @FindBy(xpath = "//button[contains(text(),'Edit Team')]")
    public WebElementFacade editTeamButton;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/main[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]")
    public WebElementFacade unitTypeBox;

    @FindBy(xpath = "//div[contains(text(),'Border Force')]")
    public WebElementFacade unitBorderForceOption;

    @FindBy(xpath = "//div[contains(text(),'Communication')]")
    public WebElementFacade unitCommunicationOption;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/main[1]/div[2]/div[1]/form[1]/div[3]/button[1]")
    public WebElementFacade updateUnitButton;

    @FindBy(xpath = "//h2[contains(text(),'Success')]")
    public WebElementFacade commtoBfSuccess;

    @FindBy(xpath = "//p[contains(text(),'Team unit changed from Border Force to Communicati')]")
    public WebElementFacade bfToCommSuccess;

    public void assertAddUnitPageTitle() {
        managementUIPageTitle.shouldContainText("Add Unit");
    }

    public void assertViewUnitPageTitle() {
        managementUIPageTitle.shouldContainText("Unit List");
    }

    public void assertDisplayNameAndShortCodeErrorMessages() {
        displayNameRequiredErrorMessage.shouldContainText("The Display Name is required");
        shortCodeRequiredErrorMessage.shouldContainText("The Short Code is required");
    }

    public void inputUnitDisplayName(String unitDisplayName) {
        displayNameTextField.sendKeys(unitDisplayName);
        setSessionVariable("unitDisplayName").to(unitDisplayName);
    }

    public void inputUnitShortCode(String unitShortCode) {
        shortCodeNameTextField.sendKeys(unitShortCode);
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
        waitForAnyTextToAppear("A unit with those details already exists");
        errorMessageContents.shouldContainText("A unit with those details already exists");
    }

    public void assertListOfUnitsVisible() {
        assertElementIsDisplayed(listOfUnits);
    }

    public void assertListContainsCreatedUnit() {
        WebElementFacade desiredUnit = findBy("(//th[contains(text(), '" + sessionVariableCalled("unitDisplayName") + "')"
                + "])");
        assertElementIsDisplayed(desiredUnit);
    }

    public void clickEditTeamButton() {
        editTeamButton.click();
    }

    public boolean selectRandomUnitName() throws InterruptedException {
        Thread.sleep(3000);
        if (unitTypeBox.containsText("Communication")) {
            unitTypeBox.click();
            unitBorderForceOption.click();
            updateUnitButton.click();
            try {
                commtoBfSuccess.isDisplayed();
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        } else if (unitTypeBox.containsText("Border Force")) {
            unitTypeBox.click();
            unitCommunicationOption.click();
            updateUnitButton.click();
            try {
                bfToCommSuccess.isDisplayed();
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return false;
    }
}
