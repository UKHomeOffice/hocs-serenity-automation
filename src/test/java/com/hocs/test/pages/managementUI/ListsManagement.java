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

    @FindBy(xpath = "//button[text()='Add new campaign']")
    private WebElementFacade addNewCampaignButton;

    @FindBy(css = "[value='Submit']")
    private WebElementFacade submitButton;

    @FindBy(xpath = "//input[@id='title']")
    private WebElementFacade titleTextField;

    @FindBy(xpath = "//input[@id='simpleName']")
    private WebElementFacade simpleNameTextField;

    @FindBy(xpath = "//h2[text()='Success']/following-sibling::p")
    public WebElementFacade successMessage;

    @FindBy(xpath = "//button[contains(text(),'Add new account manager')]")
    public WebElementFacade addNewAccountManagerButton;

    @FindBy(xpath = "//button[contains(text(),'Add new interested party')]")
    public WebElementFacade addNewInterestedPartyButton;

    @FindBy(xpath = "//button[contains(text(),'Add new representative')]")
    public WebElementFacade addNewRepresentativeButton;

    @FindBy(xpath = "//input[@id='business-areas-input']")
    public WebElementFacade businessAreaTypeahead;

    @FindBy(xpath = "//input[@id='enquiry-subject-input']")
    public WebElementFacade enquirySubjectTypeahead;

    @FindBy(xpath = "//label[text()='Business Unit name']/following-sibling::input")
    public WebElementFacade businessUnitNameTextField;

    @FindBy(xpath = "//button[text()='Add Business Unit']")
    public WebElementFacade addBusinessUnitButton;

    @FindBy(xpath = "//button[text()='Add Enquiry Reason']")
    public WebElementFacade addEnquiryReasonButton;

    @FindBy(xpath = "//label[text()='Enquiry Reason']/following-sibling::input")
    public WebElementFacade enquiryReasonTextField;

    @FindBy(xpath = "//button[text()='Add new recipient']")
    public WebElementFacade addNewRecipientButton;

    @FindBy(xpath = "//label[contains(text(), 'ecipient name')]/following-sibling::input")
    public WebElementFacade recipientNameTextField;

    @FindBy(xpath = "//label[contains(text(), 'Recipient code')]/following-sibling::input")
    public WebElementFacade recipientCodeTextField;

    private String enterName() {
        String name = "Automated test name " + generateRandomString();
        titleTextField.clear();
        titleTextField.sendKeys(name);
        return name;
    }

    private String enterCode() {
        String code = "Automated test code " + generateRandomString();
        simpleNameTextField.sendKeys(code);
        return code;
    }

    public void addANewCampaign() {
        safeClickOn(addNewCampaignButton);
        setSessionVariable("campaignName").to(enterName());
        setSessionVariable("campaignCode").to(enterCode());
        safeClickOn(submitButton);
    }

    public void amendTheCampaign() {
        WebElementFacade amendLink = findBy("//td[text()='" + sessionVariableCalled("campaignName") + "']/following-sibling::td/a[text()"
                + "='Amend']");
        safeClickOn(amendLink);
        setSessionVariable("campaignName").to(enterName());
        safeClickOn(submitButton);
    }

    public void addANewRecipient() {
        safeClickOn(addNewRecipientButton);
        setSessionVariable("recipientName").to(enterName());
        setSessionVariable("recipientCode").to(enterCode());
        safeClickOn(submitButton);
    }

    public void amendARecipientName() {
        WebElementFacade amendLink = findBy("//td[text()='" + sessionVariableCalled("recipientName") + "']/following-sibling::td/a");
        safeClickOn(amendLink);
        setSessionVariable("recipientName").to(enterName());
        safeClickOn(submitButton);
    }

    public void clickTheAddNewAccountManagerButton() {
        safeClickOn(addNewAccountManagerButton);
    }

    public void clickTheAddNewInterestedPartyButton() {
        safeClickOn(addNewInterestedPartyButton);
    }

    public void clickTheAddNewRepresentativeButton() {
        safeClickOn(addNewRepresentativeButton);
    }

    public void enterAccountManagerName() {
        setSessionVariable("accountManagerName").to(enterName());
    }

    public void enterAccountManagerCode() {
        setSessionVariable("accountManagerCode").to(enterCode());
    }

    public void enterInterestedPartyName() {
        setSessionVariable("interestedPartyName").to(enterName());
    }

    public void enterInterestedPartyCode() {
        setSessionVariable("interestedPartyCode").to(enterCode());
    }

    public void enterRepresentativeName() {
        setSessionVariable("accountManagerName").to(enterName());
    }

    public void selectABusinessArea() {
        String selectedBusinessArea = selectRandomOptionFromTypeaheadWithHeading("Business Area");
        setSessionVariable("businessArea").to(selectedBusinessArea);
    }

    public void selectASpecificBusinessArea(String businessArea) {
        selectSpecificOptionFromTypeaheadWithHeading(businessArea, "Business Area");
    }

    public void selectAnEnquirySubject(String enquirySubject) {
        waitFor(enquirySubjectTypeahead);
        enquirySubjectTypeahead.sendKeys(enquirySubject);
        enquirySubjectTypeahead.sendKeys(Keys.RETURN);
        setSessionVariable("enquirySubject").to(enquirySubject);
        clickTheButton("Submit");
    }

    public void addABusinessUnit() {
        safeClickOn(addBusinessUnitButton);
        setSessionVariable("businessUnitName").to(enterName());
        clickTheButton("Submit");
    }

    public void addAnEnquiryReason() {
        safeClickOn(addEnquiryReasonButton);
        setSessionVariable("enquiryReasonName").to(enterName());
        clickTheButton("Add");
    }

    public void selectToAmendAnAccountManager() {
        clickTheLink("Amend");
    }

    public void selectToAmendAnInterestedParty() {
        clickTheLink("Amend");
    }

    public void assertSuccessMessageForAddingAccountManagerVisible() {
        successMessage.shouldContainText("The account manager was added successfully");
    }

    public void assertSuccessMessageForAddingInterestedPartyVisible() {
        successMessage.shouldContainText("The interested party was added successfully");
    }

    public void assertSuccessMessageForAmendingAccountManagerVisible() {
        successMessage.shouldContainText("The account manager was amended successfully");
    }

    public void assertSuccessMessageForAmendingInterestedPartyVisible() {
        successMessage.shouldContainText("The interested party was amended successfully");
    }

    public void assertSuccessMessageForAddingCampaignVisible() {
        successMessage.shouldContainText("The campaign was added successfully");
    }

    public void assertSuccessMessageForAmendingCampaignVisible() {
        successMessage.shouldContainText("The campaign was amended successfully");
    }

    public void assertSuccessMessageForAddingRecipientVisible() {
        successMessage.shouldContainText("The recipient was added successfully");
    }

    public void assertSuccessMessageForAmendingRecipientVisible() {
        successMessage.shouldContainText("The recipient was amended successfully");
    }

    public void assertVisibilityOfCampaignInCampaignTable() {
        waitForMUIPageWithTitle("View and edit campaigns");
        String campaignName = sessionVariableCalled("campaignName");
        String campaignCode = sessionVariableCalled("campaignCode");
        WebElementFacade campaignInTable = findBy("//td[text()='" + campaignName + "']/following-sibling::td[text()='" + campaignCode + "']");
        if (!campaignInTable.isCurrentlyVisible()) {
            Assert.fail(campaignName + " is not visible in table");
        }
    }

    public void assertVisibilityOfRepresentativeInRepresentativeTable() {
        waitForMUIPageWithTitle("View and edit Ex-Gratia Business Representatives");
        String representativeName = sessionVariableCalled("newRepresentativeName");
        WebElementFacade representativeInTable = findBy("//td[text()='" + representativeName + "']");
        if (!representativeInTable.isCurrentlyVisible()) {
            Assert.fail(representativeName + " is not visible in table");
        }
    }

    public void assertVisibilityOfBusinessUnitInBusinessUnitTable() {
        waitForMUIPageWithTitle("View and Edit Business Units for " + sessionVariableCalled("businessArea"));
        String businessUnitName = sessionVariableCalled("businessUnitName");
        WebElementFacade businessUnitInTable = findBy("//tr/td[1][text()='" + businessUnitName + "']");
        if (!businessUnitInTable.isCurrentlyVisible()) {
            Assert.fail(businessUnitName + " is not visible in table");
        }
    }

    public void assertVisibilityOfEnquiryReasonInEnquiryReasonTable() {
        waitForMUIPageWithTitle("View and Edit Enquiry Reasons for " + sessionVariableCalled("enquirySubject"));
        String enquiryReasonName = sessionVariableCalled("enquiryReasonName");
        WebElementFacade enquiryReasonInTable = findBy("//tr/td[1][text()='" + enquiryReasonName + "']");
        if (!enquiryReasonInTable.isCurrentlyVisible()) {
            Assert.fail(enquiryReasonName + " is not visible in table");
        }
    }

    public void assertVisibilityOfAccountManagerInAccountManagerTable() {
        waitForMUIPageWithTitle("View and edit account managers");
        String accountManagerName = sessionVariableCalled("accountManagerName");
        String accountManagerCode = sessionVariableCalled("accountManagerCode");
        WebElementFacade accountManagerInTable = findBy(
                "//td[text()='" + accountManagerName + "']/following-sibling::td[text()='" + accountManagerCode + "']");
        if (!accountManagerInTable.isCurrentlyVisible()) {
            Assert.fail(accountManagerName + " is not visible in table");
        }
    }

    public void assertVisibilityOfInterestedPartyInInterestedPartyTable() {
        waitForMUIPageWithTitle("View and edit interested parties");
        String interestedPartyName = sessionVariableCalled("interestedPartyName");
        String interestedPartyCode = sessionVariableCalled("interestedPartyCode");
        WebElementFacade interestedPartyInTable = findBy(
                "//td[text()='" + interestedPartyName + "']/following-sibling::td[text()='" + interestedPartyCode + "']");
        if (!interestedPartyInTable.isCurrentlyVisible()) {
            Assert.fail(interestedPartyName + " is not visible in table");
        }
    }

    public void assertVisibilityOfRecipientInRecipientTable() {
        waitForMUIPageWithTitle("View and edit recipients");
        String recipientName = sessionVariableCalled("recipientName");
        WebElementFacade recipientInTable = findBy("//td[text()='" + recipientName + "']");
        if (!recipientInTable.isCurrentlyVisible()) {
            Assert.fail(recipientName + " is not visible in table");
        }
    }
}
