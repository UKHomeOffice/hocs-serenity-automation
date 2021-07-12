package com.hocs.test.pages.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Tier1Review extends BasePage {

    @FindBy(css = "label[for='Tier1Status-Progress']")
    public WebElementFacade progressCaseRadioButton;

    @FindBy(xpath = "//a[@href='#Tier1Withdrawn-error']")
    public WebElementFacade reviewOfferWithdrawnIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#Tier1OutcomeInterim-error']")
    public WebElementFacade interimFirstOutcomeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#Tier1OutcomeInterim2-error']")
    public WebElementFacade interimSecondOutcomeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#Tier1OutcomeFinal-error']")
    public WebElementFacade finalFirstOutcomeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#Tier1OutcomeFinal2-error']")
    public WebElementFacade finalSecondOutcomeIsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#Tier1Status-error']")
    public WebElementFacade actionIsRequired;

    @FindBy(css = "label[for='Tier1Withdrawn-NA']")
    public WebElementFacade reviewOfferNARadioButton;

    @FindBy(css = "label[for='Tier1Withdrawn-Withdrawn']")
    public WebElementFacade reviewOfferWithdrawnRadioButton;

    @FindBy(xpath = "//input[@id='Tier1WithdrawnDate-day']")
    public WebElementFacade reviewOfferWithdrawnDateDayTextbox;

    @FindBy(xpath = "//input[@id='Tier1WithdrawnDate-month']")
    public WebElementFacade reviewOfferWithdrawnDateMonthTextbox;

    @FindBy(xpath = "//input[@id='Tier1WithdrawnDate-year']")
    public WebElementFacade reviewOfferWithdrawnDateYearTextbox;

    @FindBy(css = "label[for='Tier1OutcomeInterim-NA']")
    public WebElementFacade interimDecision1NARadioButton;

    @FindBy(css = "label[for='Tier1OutcomeInterim-Revised']")
    public WebElementFacade interimDecision1RevisedRadioButton;

    @FindBy(css = "label[for='Tier1OutcomeInterim-Upheld']")
    public WebElementFacade interimDecision1UpheldRadioButton;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateInterim-day']")
    public WebElementFacade interimOutcome1DateDayTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateInterim-month']")
    public WebElementFacade interimOutcome1DateMonthTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateInterim-year']")
    public WebElementFacade interimOutcome1DateYearTextbox;

    @FindBy(css = "label[for='Tier1OutcomeInterim2-NA']")
    public WebElementFacade interimDecision2NARadioButton;

    @FindBy(css = "label[for='Tier1OutcomeInterim2-Revised']")
    public WebElementFacade interimDecision2RevisedRadioButton;

    @FindBy(css = "label[for='Tier1OutcomeInterim2-Upheld']")
    public WebElementFacade interimDecision2UpheldRadioButton;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateInterim2-day']")
    public WebElementFacade interimOutcome2DateDayTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateInterim2-month']")
    public WebElementFacade interimOutcome2DateMonthTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateInterim2-year']")
    public WebElementFacade interimOutcome2DateYearTextbox;

    @FindBy(css = "label[for='Tier1OutcomeFinal-NA']")
    public WebElementFacade finalDecision1NARadioButton;

    @FindBy(css = "label[for='Tier1OutcomeFinal-Revised']")
    public WebElementFacade finalDecision1RevisedRadioButton;

    @FindBy(css = "label[for='Tier1OutcomeFinal-Upheld']")
    public WebElementFacade finalDecision1UpheldRadioButton;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateFinal-day']")
    public WebElementFacade finalOutcome1DateDayTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateFinal-month']")
    public WebElementFacade finalOutcome1DateMonthTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateFinal-year']")
    public WebElementFacade finalOutcome1DateYearTextbox;

    @FindBy(css = "label[for='Tier1OutcomeFinal2-NA']")
    public WebElementFacade finalDecision2NARadioButton;

    @FindBy(css = "label[for='Tier1OutcomeFinal2-Revised']")
    public WebElementFacade finalDecision2RevisedRadioButton;

    @FindBy(css = "label[for='Tier1OutcomeFinal2-Upheld']")
    public WebElementFacade finalDecision2UpheldRadioButton;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateFinal2-day']")
    public WebElementFacade finalOutcome2DateDayTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateFinal2-month']")
    public WebElementFacade finalOutcome2DateMonthTextbox;

    @FindBy(xpath = "//input[@id='Tier1OutcomeDateFinal2-year']")
    public WebElementFacade finalOutcome2DateYearTextbox;

    public void completeTier1ReviewPage() {
        selectWithdrawDecision("Offer withdrawn");
        enterOfferWithdrawnDate(getTodaysDate());
        selectInterimFirstOutcome("Decision revised");
        enterInterimFirstDecisionDate(getTodaysDate());
        selectInterimSecondOutcome("Decision revised");
        enterInterimSecondDecisionDate(getTodaysDate());
        selectFinalFirstOutcome("Decision revised");
        enterFinalFirstDecisionDate(getTodaysDate());
        selectFinalSecondOutcome("Decision revised");
        enterFinalSecondDecisionDate(getTodaysDate());
        selectToProgressClaim();
    }

    public void assertResultOfReviewIsRequiredErrorMessage() {
        actionIsRequired.shouldContainText("Action is required");
    }

    public void assertWithdrawnOutcomeIsRequiredErrorMessage() {
        reviewOfferWithdrawnIsRequiredErrorMessage.shouldContainText("Review/Offer withdrawn at Tier 1 is required");
    }

    public void assertInterimFirstOutcomeIsRequiredErrorMessage() {
        interimFirstOutcomeIsRequiredErrorMessage.shouldContainText("Tier 1 interim first outcome is required");
    }

    public void assertInterimSecondOutcomeIsRequiredErrorMessage() {
        interimSecondOutcomeIsRequiredErrorMessage.shouldContainText("Tier 1 interim second outcome is required");
    }

    public void assertFinalFirstOutcomeIsRequiredErrorMessage() {
        finalFirstOutcomeIsRequiredErrorMessage.shouldContainText("Tier 1 full & final first outcome is required");
    }

    public void assertFinalSecondOutcomeIsRequiredErrorMessage() {
        finalSecondOutcomeIsRequiredErrorMessage.shouldContainText("Tier 1 full & final second outcome is required");
    }

    public void selectToProgressClaim() {
        clickOn(progressCaseRadioButton);
        clickOn(confirmButton);
    }

    public void selectInterimFirstOutcome(String outcome) {
        switch (outcome.toUpperCase()) {
            case "N/A":
                clickOn(interimDecision1NARadioButton);
                break;
            case "DECISION REVISED":
                clickOn(interimDecision1RevisedRadioButton);
                break;
            case "DECISION UPHELD":
                clickOn(interimDecision1UpheldRadioButton);
                break;
            default:
                pendingStep(outcome + " is not defined within " + getMethodName());
        }
    }

    public void selectInterimSecondOutcome(String outcome) {
        switch (outcome.toUpperCase()) {
            case "N/A":
                clickOn(interimDecision2NARadioButton);
                break;
            case "DECISION REVISED":
                clickOn(interimDecision2RevisedRadioButton);
                break;
            case "DECISION UPHELD":
                clickOn(interimDecision2UpheldRadioButton);
                break;
            default:
                pendingStep(outcome + " is not defined within " + getMethodName());
        }
    }

    public void enterInterimFirstDecisionDate(String date) {
        typeIntoDateFields(interimOutcome1DateDayTextbox, interimOutcome1DateMonthTextbox, interimOutcome1DateYearTextbox, date);
    }

    public void enterInterimSecondDecisionDate(String date) {
        typeIntoDateFields(interimOutcome2DateDayTextbox, interimOutcome2DateMonthTextbox, interimOutcome2DateYearTextbox, date);
    }

    public void selectFinalFirstOutcome(String outcome) {
        switch (outcome.toUpperCase()) {
            case "N/A":
                clickOn(finalDecision1NARadioButton);
                break;
            case "DECISION REVISED":
                clickOn(finalDecision1RevisedRadioButton);
                break;
            case "DECISION UPHELD":
                clickOn(finalDecision1UpheldRadioButton);
                break;
            default:
                pendingStep(outcome + " is not defined within " + getMethodName());
        }
    }

    public void selectFinalSecondOutcome(String outcome) {
        switch (outcome.toUpperCase()) {
            case "N/A":
                clickOn(finalDecision2NARadioButton);
                break;
            case "DECISION REVISED":
                clickOn(finalDecision2RevisedRadioButton);
                break;
            case "DECISION UPHELD":
                clickOn(finalDecision2UpheldRadioButton);
                break;
            default:
                pendingStep(outcome + " is not defined within " + getMethodName());
        }
    }

    public void enterFinalFirstDecisionDate(String date) {
        typeIntoDateFields(finalOutcome1DateDayTextbox, finalOutcome1DateMonthTextbox, finalOutcome1DateYearTextbox, date);
    }

    public void enterFinalSecondDecisionDate(String date) {
        typeIntoDateFields(finalOutcome2DateDayTextbox, finalOutcome2DateMonthTextbox, finalOutcome2DateYearTextbox, date);
    }

    public void selectWithdrawDecision(String outcome) {
        switch (outcome.toUpperCase()) {
            case "N/A":
                clickOn(reviewOfferNARadioButton);
                break;
            case "OFFER WITHDRAWN":
                clickOn(reviewOfferWithdrawnRadioButton);
                break;
            default:
                pendingStep(outcome + " is not defined within " + getMethodName());
        }
    }

    public void enterOfferWithdrawnDate(String date) {
        typeIntoDateFields(reviewOfferWithdrawnDateDayTextbox, reviewOfferWithdrawnDateMonthTextbox, reviewOfferWithdrawnDateYearTextbox, date);
    }
}
