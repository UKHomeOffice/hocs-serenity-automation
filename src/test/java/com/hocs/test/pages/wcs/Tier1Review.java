package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Tier1Review extends BasePage {

    RecordCaseData recordCaseData;

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

    @FindBy(xpath = "//a[contains(@href,'Outcome-error')]")
    public WebElementFacade actionIsRequired;

    @FindBy(xpath = "//a[@href='#Tier1Status-error']")
    public WebElementFacade resultOfReviewIsRequired;

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
        selectAWithdrawDecision();
        enterOfferWithdrawnDate(getTodaysDate());
        selectAInterimFirstOutcome();
        enterInterimFirstDecisionDate(getTodaysDate());
        selectAInterimSecondOutcome();
        enterInterimSecondDecisionDate(getTodaysDate());
        selectAFinalFirstOutcome();
        enterFinalFirstDecisionDate(getTodaysDate());
        selectAFinalSecondOutcome();
        enterFinalSecondDecisionDate(getTodaysDate());
        selectToProgressClaim();
    }

    public void selectAWithdrawDecision() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Review/Offer withdrawn at Tier 1");
    }

    public void enterOfferWithdrawnDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Review/Offer withdrawn at Tier 1 date");
    }

    public void selectAInterimFirstOutcome() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Tier 1 interim first outcome");
    }

    public void enterInterimFirstDecisionDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Tier 1 interim first decision date");
    }

    public void selectAInterimSecondOutcome() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Tier 1 interim second outcome");
    }

    public void enterInterimSecondDecisionDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Tier 1 interim second decision date");
    }

    public void selectAFinalFirstOutcome() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Tier 1 full & final first outcome");
    }

    public void enterFinalFirstDecisionDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Tier 1 full & final first decision date");
    }

    public void selectAFinalSecondOutcome() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Tier 1 full & final second outcome");
    }

    public void enterFinalSecondDecisionDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date,"Tier 1 full & final second decision date");
    }

    public void selectToProgressClaim() {
        selectSpecificRadioButton("Save changes and progress the case");
        clickConfirmButton();
    }

    public void selectToReturnClaimToEligibility() {
        selectSpecificRadioButton("Decision revised, send to eligibility");
        clickConfirmButton();
    }

    public void selectToReturnClaimToRegistration() {
        selectSpecificRadioButton("Decision revised, send to registration");
        clickConfirmButton();
    }

    //Assertions

    public void assertActionIsRequiredErrorMessage() {
        actionIsRequired.shouldContainText("Action is required");
    }

    public void assertResultOfReviewIsRequiredErrorMessage() {
        actionIsRequired.shouldContainText("Result of review is required");
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
}
