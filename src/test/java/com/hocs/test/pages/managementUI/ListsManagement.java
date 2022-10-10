package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Documents;
import config.CaseType;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

public class ListsManagement extends BasePage {

    @FindBy(xpath = "//input[@id='title']")
    private WebElementFacade titleTextField;

    @FindBy(xpath = "//input[@id='simpleName']")
    private WebElementFacade simpleNameTextField;

    @FindBy(xpath = "//h2[text()='Success']/following-sibling::p")
    public WebElementFacade successMessage;

    @FindBy(xpath = "//button[text()='Add new campaign']")
    private WebElementFacade addNewCampaignButton;

    @FindBy(xpath = "//button[contains(text(),'Add new account manager')]")
    public WebElementFacade addNewAccountManagerButton;

    @FindBy(xpath = "//button[contains(text(),'Add new interested party')]")
    public WebElementFacade addNewInterestedPartyButton;

    @FindBy(xpath = "//button[contains(text(),'Add new representative')]")
    public WebElementFacade addNewRepresentativeButton;

    @FindBy(xpath = "//button[text()='Add Business Unit']")
    public WebElementFacade addBusinessUnitButton;

    @FindBy(xpath = "//button[text()='Add Business Area']")
    public WebElementFacade addBusinessAreaButton;

    @FindBy(xpath = "//button[text()='Add Enquiry Reason']")
    public WebElementFacade addEnquiryReasonButton;

    @FindBy(xpath = "//button[text()='Add new enquiry reason']")
    public WebElementFacade addNewEnquiryReasonButton;

    @FindBy(xpath = "//button[text()='Add new recipient']")
    public WebElementFacade addNewRecipientButton;

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

    public void clickAmendLinkFor(String nameOrCode) {
        WebElementFacade amendLink = findBy("//td[text()='" + nameOrCode + "']/following-sibling::td/a[text()='Amend']");
        safeClickOn(amendLink);
    }

    public void clickDeleteLinkFor(String nameOrCode) {
        WebElementFacade amendLink = findBy("//td[text()='" + nameOrCode + "']/following-sibling::td/a[text()='Delete']");
        safeClickOn(amendLink);
    }

    public void clickTheAddNewCampaignButton() {
        safeClickOn(addNewCampaignButton);
    }

    public void clickTheAddNewRepresentativeButton() {
        safeClickOn(addNewRepresentativeButton);
    }

    public void clickTheAddNewBusinessUnitButton() { safeClickOn(addBusinessUnitButton); }

    public void clickAddNewBusinessAreaButton() {
        safeClickOn(addBusinessAreaButton);
    }

    public void clickTheAddNewEnquiryReasonButton(CaseType caseType) {
        if (caseType == CaseType.MPAM) {
            safeClickOn(addEnquiryReasonButton);
        } else if (caseType == CaseType.COMP) {
            safeClickOn(addNewEnquiryReasonButton);
        }
    }

    public void clickTheAddNewAccountManagerButton() {
        safeClickOn(addNewAccountManagerButton);
    }

    public void clickTheAddNewInterestedPartyButton() {
        safeClickOn(addNewInterestedPartyButton);
    }

    public void clickTheAddNewRecipientButton() { safeClickOn(addNewRecipientButton); }

    public void selectABusinessArea() {
        String selectedBusinessArea = selectRandomOptionFromTypeaheadWithHeading("Business Areas");
        setSessionVariable("businessArea").to(selectedBusinessArea);
    }

    public void selectADirectorate() {
        String selectedDirectorate = selectRandomOptionFromTypeaheadWithHeading("Directorate");
        setSessionVariable("directorate").to(selectedDirectorate);
    }

    public void selectSpecificDirectorate(String directorate) {
        selectSpecificOptionFromTypeaheadWithHeading(directorate, "Directorate");
        setSessionVariable("directorate").to(directorate);
    }

    public void selectASpecificBusinessArea(String businessArea) {
        selectSpecificOptionFromTypeaheadWithHeading(businessArea, "Business Areas");
    }

    public void selectAnEnquirySubject() {
        String selectedEnquirySubject = selectRandomOptionFromTypeaheadWithHeading("Enquiry Subjects");
        setSessionVariable("enquirySubject").to(selectedEnquirySubject);
    }

    public void selectASpecificEnquirySubject(String enquirySubject) {
        selectSpecificOptionFromTypeaheadWithHeading(enquirySubject, "Enquiry Subjects");
    }

    public void enterCampaignName() {
        setSessionVariable("campaignName").to(enterName());
    }

    public void enterCampaignCode() {
        setSessionVariable("campaignCode").to(enterCode());
    }

    public void enterRepresentativeName() {
        setSessionVariable("representativeName").to(enterName());
    }

    public void enterBusinessUnitName() {
        setSessionVariable("businessUnitName").to(enterName());
    }

    public void enterEnquiryReasonName() {
        setSessionVariable("enquiryReasonName").to(enterName());
    }

    public void enterBusinessAreaName() {
        setSessionVariable("businessAreaName").to(enterName());
    }

    public void enterEnquiryReasonCode() {
        setSessionVariable("enquiryReasonCode").to(enterCode());
    }

    public void enterRecipientName() {
        setSessionVariable("recipientName").to(enterName());
    }

    public void enterRecipientCode() {
        setSessionVariable("recipientCode").to(enterCode());
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

    public void assertSuccessMessageForAddingRepresentativeVisible() {
        successMessage.shouldContainText("The representative was added successfully");
    }

    public void assertSuccessMessageForDeletingRepresentativeVisible() {
        successMessage.shouldContainText("The representative was deleted successfully");
    }

    public void assertSuccessMessageForAddingBusinessUnitVisible() {
        successMessage.shouldContainText("The business unit was added successfully");
    }

    public void assertSuccessMessageForAmendingBusinessUnitVisible() {
        successMessage.shouldContainText("The business unit was amended successfully");
    }

    public void assertSuccessMessageForAddingBusinessAreaVisible() {
        successMessage.shouldContainText("The business area was added successfully");
    }

    public void assertSuccessMessageForAmendingBusinessAreaVisible() {
        successMessage.shouldContainText("The business unit was amended successfully");
    }

    public void assertSuccessMessageForAddingEnquiryReasonVisible() {
        successMessage.shouldContainText("The enquiry reason was added successfully");
    }

    public void assertSuccessMessageForAmendingEnquiryReasonVisible() {
        successMessage.shouldContainText("The enquiry reason was amended successfully");
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
        String representativeName = sessionVariableCalled("representativeName");
        WebElementFacade representativeInTable = findBy("//td[text()='" + representativeName + "']");
        if (!representativeInTable.isCurrentlyVisible()) {
            Assert.fail(representativeName + " is not visible in table");
        }
    }

    public void assertRepresentativeNotVisibleInRepresentativeTable() {
        waitForMUIPageWithTitle("View and edit Ex-Gratia Business Representatives");
        String representativeName = sessionVariableCalled("representativeName");
        WebElementFacade representativeInTable = findBy("//td[text()='" + representativeName + "']");
        if (representativeInTable.isCurrentlyVisible()) {
            Assert.fail(representativeName + " is still visible in table");
        }
    }

    public void assertVisibilityOfBusinessUnitInBusinessUnitTable() {
        waitForMUIPageWithTitle("View and Edit Business Units for ");
        String businessUnitName = sessionVariableCalled("businessUnitName");
        WebElementFacade businessUnitInTable = findBy("//tr/td[1][text()='" + businessUnitName + "']");
        if (!businessUnitInTable.isCurrentlyVisible()) {
            Assert.fail(businessUnitName + " is not visible in table");
        }
    }

    public void assertVisibilityOfBusinessAreaInBusinessAreaTable() {
        waitForMUIPageWithTitle("View and Edit Business Areas for");
        String businessAreaName = sessionVariableCalled("businessAreaName");
        WebElementFacade businessAreaInTable = findBy("//tr/td[1][text()='" + businessAreaName + "']");
        if (!businessAreaInTable.isCurrentlyVisible()) {
            Assert.fail(businessAreaName + " is not visible in table");
        }
    }

    public void assertVisibilityOfEnquiryReasonInEnquiryReasonTable() {
        waitForMUIPageWithTitle("View");
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
