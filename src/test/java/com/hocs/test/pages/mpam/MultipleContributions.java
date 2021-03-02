package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MultipleContributions extends BasePage {

    Dashboard dashboard;

    Campaign campaign;

    @FindBy(xpath = "//label[text()='Request contributions']")
    public WebElementFacade requestContributionsRadioButton;

    @FindBy(xpath = "//a[text()='Add a Contribution']")
    public WebElementFacade addAContributionHypertext;

    @FindBy(id = "contributionBusinessArea")
    public WebElementFacade contributionRequestBusinessArea;

    @FindBy(id = "contributionBusinessUnit")
    public WebElementFacade contributionRequestBusinessUnit;

    @FindBy(id = "contributionRequestDate-day")
    public WebElementFacade contributionRequestDateDayField;

    @FindBy(id = "contributionRequestDate-month")
    public WebElementFacade contributionRequestDateMonthField;

    @FindBy(id = "contributionRequestDate-year")
    public WebElementFacade contributionRequestDateYearField;

    @FindBy(id = "contributionDueDate-day")
    public WebElementFacade contributionDueDateDayField;

    @FindBy(id = "contributionDueDate-month")
    public WebElementFacade contributionDueDateMonthField;

    @FindBy(id = "contributionDueDate-year")
    public WebElementFacade contributionDueDateYearField;

    @FindBy(id = "contributionRequestNote")
    public WebElementFacade whatYouAreRequestingTextField;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElementFacade editHypertext;

    @FindBy(xpath = "//label[text()='Complete']")
    public WebElementFacade completeRadioButton;

    @FindBy(xpath = "//label[text()='Cancelled']")
    public WebElementFacade cancelledRadioButton;

    @FindBy(id = "contributionReceivedDate-day")
    public WebElementFacade contributionReceivedDateDayField;

    @FindBy(id = "contributionReceivedDate-month")
    public WebElementFacade contributionReceivedDateMonthField;

    @FindBy(id = "contributionReceivedDate-year")
    public WebElementFacade contributionReceivedDateYearField;

    @FindBy(id = "contributionReceivedNote")
    public WebElementFacade contributionReceivedDetailsTextField;

    @FindBy(id = "contributionCancellationNote")
    public WebElementFacade reasonForCancellingTextField;

    @FindBy(xpath = "//input[@value='Update']")
    public WebElementFacade updateButton;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='Unallocate Case']")
    public WebElementFacade unallocateCaseRadioButton;

    @FindBy(xpath = "//label[text()='Retain Case']")
    public WebElementFacade retainCaseRadioButton;

    private void addAContribution() {
        safeClickOn(addAContributionHypertext);
        contributionRequestBusinessArea.selectByIndex(1);
        contributionRequestBusinessUnit.selectByIndex(1);
        typeIntoDateField(contributionRequestDateDayField, contributionRequestDateMonthField, contributionRequestDateYearField,
                getDatePlusMinusNDaysAgo(-1));
        typeIntoDateField(contributionDueDateDayField, contributionDueDateMonthField, contributionDueDateYearField, getDatePlusMinusNDaysAgo(5));
        setSessionVariable("contributionDueDate").to(getDatePlusMinusNDaysAgo(5));
        typeInto(whatYouAreRequestingTextField, "Test");
        safeClickOn(addButton);
    }

    public void editContributionDueDate() {
        safeClickOn(editHypertext);
        typeIntoDateField(contributionDueDateDayField, contributionDueDateMonthField, contributionDueDateYearField, getDatePlusMinusNDaysAgo(1));
        setSessionVariable("contributionDueDate").to(getDatePlusMinusNDaysAgo(1));
        safeClickOn(updateButton);
    }

    public void sendCaseToContributionRequest() {
        safeClickOn(requestContributionsRadioButton);
        safeClickOn(confirmButton);
        addAContribution();
        safeClickOn(continueButton);
    }

    public void addMultipleContributionRequests(int numberOfContributions) {
        int count = 0;
        safeClickOn(requestContributionsRadioButton);
        safeClickOn(confirmButton);
        while (count < numberOfContributions) {
            addAContribution();
            count++;
        }
        safeClickOn(continueButton);
        setSessionVariable("numberOfContributions").to(numberOfContributions);
    }

    public void selectActionForIndividualContributionRequest(String action) {
        safeClickOn(editHypertext);
        switch (action.toUpperCase()) {
            case "COMPLETE":
                safeClickOn(completeRadioButton);
                typeIntoDateField(contributionReceivedDateDayField, contributionReceivedDateMonthField, contributionReceivedDateYearField,
                        getDatePlusMinusNDaysAgo(-1));
                typeInto(contributionReceivedDetailsTextField, "Test");
                break;
            case "CANCEL":
                safeClickOn(cancelledRadioButton);
                typeInto(reasonForCancellingTextField, "Test");
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(updateButton);
    }

    public void selectActionAtContributionRequestedStage(String action) {
        switch (action.toUpperCase()) {
            case "ESCALATE TO WORKFLOW MANAGER":
                safeClickOn(escalateToWorkflowManagerRadioButton);
                safeClickOn(confirmButton);
                break;
            case "PUT CASE INTO CAMPAIGN":
                campaign.moveCaseFromAStageToCampaign("Jamaican charter flight");
                break;
            case "CONTRIBUTIONS RECEIVED":
                safeClickOn(contributionsReceivedRadioButton);
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
    }

    public void retainOrUnallocateDraftContributionRequestedCase(String action) {
        WebElementFacade radioButton = null;
        switch (action.toUpperCase()) {
            case "RETAIN":
                radioButton = retainCaseRadioButton;
                break;
            case "UNALLOCATE":
                radioButton = unallocateCaseRadioButton;
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
        safeClickOn(confirmButton);
    }

    public void triggerValidationAtContributionRequestScreens(String screen) {
        switch (screen.toUpperCase()) {
            case "ADD CONTRIBUTION REQUEST":
                safeClickOn(requestContributionsRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(addAContributionHypertext);
                safeClickOn(addButton);
                break;
            case "CONTRIBUTIONS REQUESTED":
                sendCaseToContributionRequest();
                dashboard.getAndClaimCurrentCase();
                safeClickOn(confirmButton);
                break;
            case "CONTRIBUTION REQUEST FULFILLMENT":
                sendCaseToContributionRequest();
                dashboard.getAndClaimCurrentCase();
                safeClickOn(editHypertext);
                safeClickOn(cancelledRadioButton);
                safeClickOn(updateButton);
                assertRequiredErrorMessageIsDisplayed("Contribution Cancellation reason");
                safeClickOn(completeRadioButton);
                safeClickOn(updateButton);
                break;
            case "UNALLOCATE CASE":
                sendCaseToContributionRequest();
                dashboard.getAndClaimCurrentCase();
                safeClickOn(editHypertext);
                safeClickOn(completeRadioButton);
                typeIntoDateField(contributionReceivedDateDayField, contributionReceivedDateMonthField, contributionReceivedDateYearField,
                        getDatePlusMinusNDaysAgo(-1));
                typeInto(contributionReceivedDetailsTextField, "Test");
                safeClickOn(updateButton);
                safeClickOn(contributionsReceivedRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(screen + " is not defined within " + getMethodName());
        }
    }

    public void assertThatContributionRequestHasBeen(String action) {
        WebElementFacade contributionRequestStatus = findBy("//span[text()='Case contributions']/parent::legend/following-sibling::table//tr/td[2]");
        assertThat(contributionRequestStatus.getText().toUpperCase().contains(action.toUpperCase()), is(true));
    }

    public void assertRequiredErrorMessageIsDisplayed(String error) {
        boolean isItContained = false;
        List<WebElementFacade> listOfErrorMessages = findAll("//h2[contains(text(), 'a problem')]/following-sibling::div//a");
        int n = 0;
        String errorText = null;
        switch (error.toUpperCase()) {
            case "BUSINESS AREA":
                errorText = "BUSINESS AREA IS REQUIRED";
                break;
            case "BUSINESS UNIT":
                errorText = "BUSINESS UNIT IS REQUIRED";
                break;
            case "CONTRIBUTION REQUEST DATE":
                errorText = "CONTRIBUTION REQUEST DATE IS REQUIRED";
                break;
            case "CONTRIBUTION DUE DATE":
                errorText = "CONTRIBUTION DUE DATE IS REQUIRED";
                break;
            case "WHAT YOU ARE REQUESTING":
                errorText = "WHAT YOU ARE REQUESTING IS REQUIRED";
                break;
            case "ACTIONS":
                errorText = "ACTIONS IS REQUIRED";
                break;
            case "CASE CONTRIBUTIONS MUST BE COMPLETED OR CANCELLED":
                errorText = "CASE CONTRIBUTIONS HAVE TO BE COMPLETED OR CANCELLED";
                break;
            case "CONTRIBUTION RECEIVED DATE":
                errorText = "CONTRIBUTION RECEIVED DATE IS REQUIRED";
                break;
            case "CONTRIBUTION COMPLETION NOTES":
                errorText = "CONTRIBUTION COMPLETION NOTES IS REQUIRED";
                break;
            case "CONTRIBUTION CANCELLATION REASON":
                errorText = "CONTRIBUTION CANCELLATION REASON IS REQUIRED";
                break;
            case "CASE ACTIONS":
                errorText = "CASE ACTIONS IS REQUIRED";
                break;
            default:
                pendingStep(error + " is not defined within " + getMethodName());
        }
        while (n < listOfErrorMessages.size()) {
            if (listOfErrorMessages.get(n).getText().toUpperCase().contains(errorText)) {
                isItContained = true;
                break;
            }
            n++;
        }
        assertThat(isItContained, is(true));
    }

    public void assertNumberOfContributionsAddedToCase(int expectedNumber) {
        List<WebElementFacade> listOfContributionsDisplayed = findAll("//span[text()='Case contributions']/parent::legend/following-sibling"
                + "::table//tr");
        assertThat(expectedNumber == listOfContributionsDisplayed.size(), is(true));
    }
}