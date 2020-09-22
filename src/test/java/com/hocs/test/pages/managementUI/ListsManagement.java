package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ListsManagement extends BasePage {

    Dashboard dashboard;

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

    public void addANewCampaign() {
        String campaignName = generateRandomString();
        String campaignCode = generateRandomString();
        safeClickOn(addNewCampaignButton);
        typeInto(campaignNameTextBox, campaignName);
        setSessionVariable("newCampaign").to(campaignName);
        typeInto(campaignCodeTextBox, campaignCode);
        safeClickOn(submitButton);
    }

    public void amendACampaign() {
        String campaignName = generateRandomString();
        WebElementFacade amendLink = findBy("//td[text()='" + sessionVariableCalled("newCampaign") + "']/following-sibling::td/a[text()='Amend']");
        safeClickOn(amendLink);
        newCampaignNameTextBox.clear();
        typeInto(newCampaignNameTextBox, campaignName);
        setSessionVariable("newCampaign").to(campaignName);
        safeClickOn(submitButton);
    }

    public void assertCampaignAddedToCampaignTable() {
        if (!viewAndEditCampaignsHeader.isVisible()) {
            safeClickOn(dashboard.manageMPAMCampaignsHypertext);
        }
        WebElementFacade newCampaignInList = findBy("//td[text()='" + sessionVariableCalled("newCampaign") + "']");
        newCampaignInList.shouldBeVisible();
    }
}
