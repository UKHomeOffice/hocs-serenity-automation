package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class MUIDashboard extends BasePage {

    Dashboard dashboard;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/team-search']")
    public WebElementFacade manageATeamButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/add-child-topic']")
    public WebElementFacade addChildTopicButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/add-unit']")
    public WebElementFacade addUnitButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/unit-search']")
    public WebElementFacade viewUnitsButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/add-standard-line']")
    public WebElementFacade addStandardLineButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/topic-to-team']")
    public WebElementFacade linkTopicToTeamButton;

    @FindBy(timeoutInSeconds = "10", xpath = ".//h1[contains(text(), 'Choose an area to manage')]")
    public WebElementFacade subheading;

    @FindBy(timeoutInSeconds = "10", xpath = ".//h2[contains(text(), 'Success')]")
    public WebElementFacade successMessage;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Manage a user']")
    public WebElementFacade manageAUserHypertext;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Add a user']")
    public WebElementFacade addAUserHypertext;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Manage MPAM campaigns']")
    public WebElementFacade manageMPAMCampaignsHypertext;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Manage standard lines']")
    public WebElementFacade manageStandardLinesHypertext;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[text()='Create a DCU drafting team']")
    public WebElementFacade createDCUDraftingTeamHypertext;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[@href='/case-withdraw']")
    public WebElementFacade withdrawACaseHypertext;

    @FindBy(timeoutInSeconds = "10", xpath = "//a[contains(text(),'Log out')]")
    public WebElementFacade logoutButton;

    public void assertSuccessMessageDisplayed() {
        assertElementIsDisplayed(successMessage);
    }

    public void selectDashboardLinkWithText(String linkText) {
        WebElementFacade link = findBy("//a[text()='" + linkText + "']").withTimeoutOf(Duration.ofSeconds(10));
        if(!link.isCurrentlyVisible()) {
            dashboard.goToMUIDashboard();
        }
        safeClickOn(link);
    }
}
