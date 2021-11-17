package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

public class ListsManagement extends BasePage {

    MUIDashboard MUIDashboard;

    Documents documents;

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

    @FindBy(xpath = "//input[@id='business-areas-input']")
    public WebElementFacade businessAreaTypeahead;

    @FindBy(xpath = "//input[@id='enquiry-subject-input']")
    public WebElementFacade enquirySubjectTypeahead;

    @FindBy(xpath = "//input[@id='case-types-input']")
    public WebElementFacade caseTypeTypeahead;

    @FindBy(xpath = "//label[text()='Business Unit name']/following-sibling::input")
    public WebElementFacade businessUnitNameTextField;

    @FindBy(xpath = "//button[text()='Add Business Unit']")
    public WebElementFacade addBusinessUnitButton;

    @FindBy(xpath = "//button[text()='Add Enquiry Reason']")
    public WebElementFacade addEnquiryReasonButton;

    @FindBy(xpath = "//button[text()='Add template']")
    public WebElementFacade addTemplateButton;

    @FindBy(xpath = "//label[text()='Enquiry Reason']/following-sibling::input")
    public WebElementFacade enquiryReasonTextField;

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

    public void selectABusinessArea(String businessArea) {
        waitFor(businessAreaTypeahead);
        businessAreaTypeahead.sendKeys(businessArea);
        businessAreaTypeahead.sendKeys(Keys.RETURN);
        clickTheButton("Submit");
    }

    public void selectAnEnquirySubject(String enquirySubject) {
        waitFor(enquirySubjectTypeahead);
        enquirySubjectTypeahead.sendKeys(enquirySubject);
        enquirySubjectTypeahead.sendKeys(Keys.RETURN);
        clickTheButton("Submit");
    }

    public void selectACaseType(String caseType) {
        waitFor(caseTypeTypeahead);
        caseTypeTypeahead.sendKeys(caseType);
        caseTypeTypeahead.sendKeys(Keys.RETURN);
        clickTheButton("Submit");
    }

    public void addABusinessUnit() {
        safeClickOn(addBusinessUnitButton);
        String newBusinessUnit = "Business Unit " + generateRandomString();
        setSessionVariable("businessUnitName").to(newBusinessUnit);
        businessUnitNameTextField.sendKeys(newBusinessUnit);
        clickTheButton("Submit");
    }

    public void addAnEnquiryReason() {
        safeClickOn(addEnquiryReasonButton);
        String newEnquiryReason = "Enquiry Reason " + generateRandomString();
        setSessionVariable("enquiryReasonName").to(newEnquiryReason);
        enquiryReasonTextField.sendKeys(newEnquiryReason);
        clickTheButton("Add");
    }

    public void addTemplate() {
        safeClickOn(addTemplateButton);
        documents.uploadDocumentOfType("docx");
        clickTheButton("Submit");
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

    public void assertVisibilityOfNewBusinessUnit() {
        String newBusinessUnitName = sessionVariableCalled("businessUnitName");
        WebElementFacade businessUnit = findBy("//tr/td[1][text()='" + newBusinessUnitName + "']");
        businessUnit.shouldBeVisible();
    }

    public void assertVisibilityOfNewEnquiryReason() {
        String newEnquiryReasonName = sessionVariableCalled("enquiryReasonName");
        WebElementFacade enquiryReason = findBy("//tr/td[1][text()='" + newEnquiryReasonName + "']");
        enquiryReason.shouldBeVisible();
    }
}
