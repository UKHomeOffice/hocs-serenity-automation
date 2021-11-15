package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;

public class ListsManagement extends BasePage {

    MUIDashboard MUIDashboard;

    @FindBy(xpath = "//h1[text()='View and edit campaigns']")
    private WebElementFacade viewAndEditCampaignsHeader;

    @FindBy(xpath = "//button[text()='Add new campaign']")
    private WebElementFacade addNewCampaignButton;

    @FindBy(xpath = "//label[text()='Campaign name']/following-sibling::input")
    private WebElementFacade campaignNameTextBox;

    @FindBy(xpath = "//label[text()='Campaign code']/following-sibling::input")
    private WebElementFacade campaignCodeTextBox;

    @FindBy(css = "[value='Submit']")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//label[text()='New campaign name']/following-sibling::input")
    private WebElementFacade newCampaignNameTextBox;

    @FindBy(xpath = "//input[@id='title']")
    private WebElementFacade accountManagerNameTextBox;

    @FindBy(xpath = "//input[@id='simpleName']")
    private WebElementFacade accountManagerCodeTextBox;

    @FindBy(xpath = "//h2[text()='Success']/following-sibling::p")
    public WebElementFacade successMessage;

    @FindBy(xpath = "//button[contains(text(),'Add new account manager')]")
    public WebElementFacade addNewAccountManagerButton;

    public void addANewCampaign() {
        String campaignName = generateRandomString();
        String campaignCode = generateRandomString();
        safeClickOn(addNewCampaignButton);
        campaignNameTextBox.sendKeys(campaignName);
        setSessionVariable("newCampaign").to(campaignName);
        campaignCodeTextBox.sendKeys(campaignCode);
        safeClickOn(submitButton);
    }

    public void amendACampaign() {
        String campaignName = generateRandomString();
        WebElementFacade amendLink = findBy("//td[text()='" + sessionVariableCalled("newCampaign") + "']/following-sibling::td/a[text()='Amend']");
        safeClickOn(amendLink);
        newCampaignNameTextBox.clear();
        newCampaignNameTextBox.sendKeys(campaignName);
        setSessionVariable("newCampaign").to(campaignName);
        safeClickOn(submitButton);
    }

    public void assertCampaignAddedToCampaignTable() {
        if (!viewAndEditCampaignsHeader.isVisible()) {
            safeClickOn(MUIDashboard.manageMPAMCampaignsHypertext);
        }
        WebElementFacade newCampaignInList = findBy("//td[text()='" + sessionVariableCalled("newCampaign") + "']");
        newCampaignInList.shouldBeVisible();
    }

    public void clickTheAddNewAccountManagerButton() {
        safeClickOn(addNewAccountManagerButton);
    }

    public void enterAccountManagerName() {
        String name = "Automated test name " + generateRandomString();
        accountManagerNameTextBox.sendKeys(name);
        setSessionVariable("accountManagerName").to(name);
    }

    public void enterAccountManagerCode() {
        String code = "Automated test code " + generateRandomString();
        accountManagerCodeTextBox.sendKeys(code);
    }


    public void selectToAmendAnAccountManager() {
        clickTheLink("Amend");
    }

    public void assertSuccessMessageForAddingAccountManagerVisible() {
        successMessage.shouldContainText("The account manager was added successfully");
    }

    public void assertSuccessMessageForAmendingAccountManagerVisible() {
        successMessage.shouldContainText("The account manager was amended successfully");
    }

    public void assertAccountManagerIsVisible() {
        waitForMUIPageWithTitle("View and edit account managers");
        String accountManagerName = sessionVariableCalled("accountManagerName");
        WebElementFacade accountManagerInTable = findBy("//td[contains(text(), '" + accountManagerName + "')]");
        if (!accountManagerInTable.isCurrentlyVisible()) {
            Assert.fail(accountManagerName + " is not visible in table");
        }

    }
}
