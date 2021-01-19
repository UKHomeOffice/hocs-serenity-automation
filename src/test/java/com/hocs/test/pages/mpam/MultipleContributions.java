package com.hocs.test.pages.mpam;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MultipleContributions extends BasePage {

    Homepage homepage;

    Campaign campaign;

    Triage triage;

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
        WebElementFacade radioButton = null;
        switch (action.toUpperCase()) {
            case "ESCALATE TO WORKFLOW MANAGER":
                radioButton = escalateToWorkflowManagerRadioButton;
                break;
            case "PUT CASE INTO CAMPAIGN":
                radioButton = campaign.putCaseIntoCampaignRadioButton;
                break;
            case "CONTRIBUTIONS RECEIVED":
                radioButton = contributionsReceivedRadioButton;
                break;
            default:
                pendingStep(action + " is not defined within " + getMethodName());
        }
        safeClickOn(radioButton);
        safeClickOn(confirmButton);
        if (action.toUpperCase().equals("PUT CASE INTO CAMPAIGN")) {
            campaign.campaignSelectionTypeahead.sendKeys( "Jamaican charter flight");
            campaign.campaignSelectionTypeahead.sendKeys(Keys.RETURN);
            safeClickOn(confirmButton);
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
        safeClickOn(triage.setEnquiryHypertext);
        triage.selectEnquirySubject("Person Specific");
        triage.selectEnquiryReason("Allowed appeal enquiry update");
        triage.setBusinessUnit();
        switch (screen.toUpperCase()) {
            case "ADD CONTRIBUTION REQUEST":
                safeClickOn(requestContributionsRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(addAContributionHypertext);
                safeClickOn(addButton);
                break;
            case "CONTRIBUTIONS REQUESTED":
                sendCaseToContributionRequest();
                homepage.getAndClaimCurrentCase();
                safeClickOn(confirmButton);
                break;
            case "CONTRIBUTION REQUEST FULFILLMENT":
                sendCaseToContributionRequest();
                homepage.getAndClaimCurrentCase();
                safeClickOn(editHypertext);
                safeClickOn(cancelledRadioButton);
                safeClickOn(updateButton);
                assertRequiredErrorMessageIsDisplayed("Contribution Cancellation reason");
                safeClickOn(completeRadioButton);
                safeClickOn(updateButton);
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
                errorText = "Business area is required";
                break;
            case "BUSINESS UNIT":
                errorText = "Business unit is required";
                break;
            case "CONTRIBUTION REQUEST DATE":
                errorText = "Contribution request date is required";
                break;
            case "CONTRIBUTION DUE DATE":
                errorText = "Contribution due date is required";
                break;
            case "WHAT YOU ARE REQUESTING":
                errorText = "What you are requesting is required";
                break;
            case "ACTIONS":
                errorText = "Actions is required";
                break;
            case "CASE CONTRIBUTIONS MUST BE COMPLETED OR CANCELLED":
                errorText = "Case contributions have to be completed or cancelled";
                break;
            case "CONTRIBUTION RECEIVED DATE":
                errorText = "Contribution received date is required";
                break;
            case "CONTRIBUTION COMPLETION NOTES":
                errorText = "Contribution completion notes reason required";
                break;
            case "CONTRIBUTION CANCELLATION REASON":
                errorText = "Contribution cancellation reason required";
                break;
            default:
                pendingStep(error + " is not defined within " + getMethodName());
        }
        while (n < listOfErrorMessages.size()) {
            if (listOfErrorMessages.get(n).getText().contains(errorText)) {
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