package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MUIDashboard extends BasePage {

    @FindBy (xpath = "//a[@href='/team-search']")
    public WebElementFacade manageATeamButton;

    @FindBy(xpath = "//a[@href='/add-child-topic']")
    public WebElementFacade addChildTopicButton;

    @FindBy(xpath = "//a[@href='/add-unit']")
    public WebElementFacade addUnitButton;

    @FindBy(xpath = "//a[@href='/unit-search']")
    public WebElementFacade viewUnitsButton;

    @FindBy(xpath = "//a[@href='/add-standard-line']")
    public WebElementFacade addStandardLineButton;

    @FindBy(xpath = "//a[@href='/topic-to-team']")
    public WebElementFacade linkTopicToTeamButton;

    @FindBy(xpath = ".//h1[contains(text(), 'Choose an area to manage')]")
    public WebElementFacade subheading;

    @FindBy(xpath = ".//h2[contains(text(), 'Success')]")
    public WebElementFacade successMessage;

    @FindBy(xpath = "//a[text()='Manage a user']")
    public WebElementFacade userManagementHypertext;

    @FindBy(xpath = "//a[text()='Manage MPAM campaigns ']")
    public WebElementFacade manageMPAMCampaignsHypertext;

    @FindBy(xpath = "//a[text()='Manage standard lines ']")
    public WebElementFacade manageStandardLinesHypertext;

    @FindBy(xpath = "//a[text()='Create a DCU drafting team']")
    public WebElementFacade createDCUDraftingTeamHypertext;

    public void assertSuccessMessageDisplayed() {
        assertElementIsDisplayed(successMessage);
    }


}
