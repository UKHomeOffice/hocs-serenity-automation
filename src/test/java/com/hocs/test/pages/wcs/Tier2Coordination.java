package com.hocs.test.pages.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Tier2Coordination extends BasePage {

    ClaimSchema claimSchema;

    @FindBy(css = "label[for='Tier2Coord-Revise-Accept']")
    public WebElementFacade revisedOfferAcceptedByTheClaimantRadioButton;

    @FindBy(css = "label[for='Tier2Coord-Revise-Reject']")
    public WebElementFacade revisedOfferRejectedByTheClaimantRadioButton;

    @FindBy(css = "label[for='Tier2Coord-Upheld-Accept']")
    public WebElementFacade upheldOfferIsAcceptedByTheClaimantRadioButton;

    @FindBy(css = "label[for='Tier2Coord-Upheld-Reject']")
    public WebElementFacade upheldOfferIsRejectedByTheClaimantRadioButton;

    @FindBy(xpath = "//a[@href='#Tier2Coord-error']")
    public WebElementFacade tier2ReviewResultIsRequiredErrorMessage;

    @FindBy(xpath = "//label[text()='Upheld']")
    public WebElementFacade tier2AdjudicatorDecisionUpheldRadioButton;

    @FindBy(xpath = "//label[text()='Revised']")
    public WebElementFacade tier2AdjudicatorDecisionRevisedRadioButton;

    @FindBy(id = "Tier2AdjudicatorsOfficeDecisionDate-day")
    public WebElementFacade tier2AdjudicatorDecisionDateDayTextField;

    @FindBy(id = "Tier2AdjudicatorsOfficeDecisionDate-month")
    public WebElementFacade tier2AdjudicatorDecisionDateMonthTextField;

    @FindBy(id = "Tier2AdjudicatorsOfficeDecisionDate-year")
    public WebElementFacade tier2AdjudicatorDecisionYearTextField;

    @FindBy(css = "label[for='Tier2Withdrawn-NA']")
    public WebElementFacade reviewOfferNARadioButton;

    @FindBy(css = "label[for='Tier2Withdrawn-Withdrawn']")
    public WebElementFacade reviewOfferWithdrawnRadioButton;

    @FindBy(xpath = "//input[@id='Tier2WithdrawnDate-day']")
    public WebElementFacade reviewOfferWithdrawnDateDayTextbox;

    @FindBy(xpath = "//input[@id='Tier2WithdrawnDate-month']")
    public WebElementFacade reviewOfferWithdrawnDateMonthTextbox;

    @FindBy(xpath = "//input[@id='Tier2WithdrawnDate-year']")
    public WebElementFacade reviewOfferWithdrawnDateYearTextbox;


    public void assertTier2ReviewResultIsRequiredErrorMessage() {
        tier2ReviewResultIsRequiredErrorMessage.shouldContainText("Tier 2 review result is required");
    }

    public void selectClaimantAcceptsRevisedOffer() {
        clickOn(revisedOfferAcceptedByTheClaimantRadioButton);
        clickOn(confirmButton);
    }

    public void selectClaimantRejectsRevisedOffer() {
        clickOn(revisedOfferRejectedByTheClaimantRadioButton);
        clickOn(confirmButton);
    }

    public void selectClaimantAcceptsUpheldOffer() {
        clickOn(upheldOfferIsAcceptedByTheClaimantRadioButton);
        clickOn(confirmButton);
    }

    public void selectClaimantRejectsUpheldOffer() {
        clickOn(upheldOfferIsRejectedByTheClaimantRadioButton);
        clickOn(confirmButton);
    }

    public void enterTier2AdjudicatorOfficeDecisionDate(String date) {
        typeIntoDateFields(tier2AdjudicatorDecisionDateDayTextField, tier2AdjudicatorDecisionDateMonthTextField, tier2AdjudicatorDecisionYearTextField, date);
        setSessionVariable("adjudicatorsOfficeDecisionDate").to(date);
    }

    public void enterAdjudicatorOfficeDecisionInformation(String date, String decision) {
        enterTier2AdjudicatorOfficeDecisionDate(date);
        switch (decision.toUpperCase()) {
            case "UPHELD":
                clickOn(tier2AdjudicatorDecisionUpheldRadioButton);
                selectClaimantAcceptsUpheldOffer();
                break;
            case "REVISED":
                clickOn(tier2AdjudicatorDecisionRevisedRadioButton);
                selectClaimantAcceptsRevisedOffer();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        setSessionVariable("adjudicatorsOfficeDecision").to(decision);
    }

    public void assertTier2AdjudicatorsOfficeDecisionInformationIsCorrectInAccordion() {
        String decision = claimSchema.tier2AdjudicatorsOfficeDecision.getValue();
        String decisionDateDay = claimSchema.tier2AdjudicatorsOfficeDecisionDateDay.getValue();
        String decisionDateMonth = claimSchema.tier2AdjudicatorOfficeDecisionDateMonth.getValue();
        String decisionDateYear = claimSchema.tier2AdjudicatorOfficeDecisionDateYear.getValue();
        String decisionDate = decisionDateDay + "/" + decisionDateMonth + "/" + decisionDateYear;
        assertThat(decisionDate.equals(sessionVariableCalled("adjudicatorsOfficeDecisionDate")), is(true));
        assertThat(decision.equals(sessionVariableCalled("adjudicatorsOfficeDecision")), is(true));
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
