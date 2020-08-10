package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Dashboard extends BasePage {

    @FindBy (xpath = "//a[@href='/team-search']")
    public WebElementFacade addRemoveUsersButton;

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

    @FindBy(linkText = "Correspondence System Management")
    public WebElementFacade dashboardLink;

    @FindBy(xpath = ".//h1[contains(text(), 'Choose an area to manage')]")
    public WebElementFacade subheading;

    @FindBy(xpath = ".//h2[contains(text(), 'Success')]")
    public WebElementFacade successMessage;

    @FindBy(xpath = "//a[text()='Manage a user']")
    public WebElementFacade userManagementHypertext;

    public void goToDashboard() {
        safeClickOn(dashboardLink);
    }

    public void assertSuccessMessageDisplayed() {
        assertElementIsDisplayed(successMessage);
    }


}
