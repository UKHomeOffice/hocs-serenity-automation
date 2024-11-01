package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.ContributionRequests;
import com.hocs.test.pages.decs.Dashboard;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class MPAMMultipleContributions extends BasePage {

    Dashboard dashboard;

    Campaign campaign;

    ContributionRequests contributionRequests;

    @FindBy(xpath = "//label[text()='Request contributions']")
    public WebElementFacade requestContributionsRadioButton;

    @FindBy(xpath = "//label[text()='Contributions received']")
    public WebElementFacade contributionsReceivedRadioButton;

    @FindBy(xpath = "//label[text()='Escalate to workflow manager']")
    public WebElementFacade escalateToWorkflowManagerRadioButton;

    @FindBy(xpath = "//label[text()='Unallocate Case']")
    public WebElementFacade unallocateCaseRadioButton;

    @FindBy(xpath = "//label[text()='Retain Case']")
    public WebElementFacade retainCaseRadioButton;

    public void selectToAddContributionsToAnMPAMCase(){
        safeClickOn(requestContributionsRadioButton);
        clickConfirmButton();
    }

    public void sendMPAMCaseToContributionRequest() {
        selectToAddContributionsToAnMPAMCase();
        contributionRequests.addAContribution("CASE", getDatePlusMinusNDaysAgo(-1), getDatePlusMinusNDaysAgo(5));
        if (buttonIsCurrentlyVisible("Continue")) {
            clickContinueButton();
        } else if (buttonIsCurrentlyVisible("Confirm")) {
            clickConfirmButton();
        }
    }

    public void addMultipleContributionRequests(int numberOfContributions) {
        String contributionType = "";
        if (mpamCase()) {
            selectToAddContributionsToAnMPAMCase();
            contributionType = "CASE";
        } else if (foiCase()) {
            contributionType = "FOI";
        }
        contributionRequests.addMultipleContributionRequests(numberOfContributions, contributionType);
    }

    public void selectActionAtContributionRequestedStage(String action) {
        switch (action.toUpperCase()) {
            case "ESCALATE TO WORKFLOW MANAGER":
                safeClickOn(escalateToWorkflowManagerRadioButton);
                clickConfirmButton();
                break;
            case "PUT CASE INTO CAMPAIGN":
                campaign.moveCaseFromAStageToCampaign("Jamaican charter flight");
                break;
            case "CONTRIBUTIONS RECEIVED":
                safeClickOn(contributionsReceivedRadioButton);
                clickConfirmButton();
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
        clickConfirmButton();
    }

    public void triggerValidationAtContributionRequestScreens(String screen) {
        switch (screen.toUpperCase()) {
            case "ADD CONTRIBUTION REQUEST":
                selectToAddContributionsToAnMPAMCase();
                safeClickOn(contributionRequests.addAContributionHypertext);
                clickAddButton();
                break;
            case "CONTRIBUTIONS REQUESTED":
                sendMPAMCaseToContributionRequest();
                dashboard.getAndClaimCurrentCase();
                clickConfirmButton();
                break;
            case "CONTRIBUTION REQUEST FULFILLMENT":
                sendMPAMCaseToContributionRequest();
                dashboard.getAndClaimCurrentCase();
                safeClickOn(contributionRequests.editHypertext);
                safeClickOn(contributionRequests.cancelledRadioButton);
                safeClickOn(contributionRequests.updateButton);
                assertRequiredErrorMessageIsDisplayed("Contribution Cancellation reason");
                safeClickOn(contributionRequests.completeRadioButton);
                safeClickOn(contributionRequests.updateButton);
                break;
            case "UNALLOCATE CASE":
                sendMPAMCaseToContributionRequest();
                dashboard.getAndClaimCurrentCase();
                safeClickOn(contributionRequests.editHypertext);
                safeClickOn(contributionRequests.completeRadioButton);
                typeIntoDateFields(contributionRequests.contributionReceivedDateDayField, contributionRequests.contributionReceivedDateMonthField,
                        contributionRequests.contributionReceivedDateYearField,
                        getDatePlusMinusNDaysAgo(-1));
                contributionRequests.contributionReceivedDetailsTextField.sendKeys("Test - contribution received details");
                safeClickOn(contributionRequests.updateButton);
                safeClickOn(contributionsReceivedRadioButton);
                clickConfirmButton();
                clickConfirmButton();
                break;
            default:
                pendingStep(screen + " is not defined within " + getMethodName());
        }
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