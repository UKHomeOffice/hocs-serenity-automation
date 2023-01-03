package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput extends BasePage {

    Correspondents correspondents;

    RecordCaseData recordCaseData;

    // Elements

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Email']")
    public WebElementFacade emailOriginalChannelRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Post']")
    public WebElementFacade postOriginalChannelRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='Phone']")
    public WebElementFacade phoneOriginalChannelRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//label[text()='No. 10']")
    public WebElementFacade numberTenOriginalChannelRadioButton;

    @FindBy(timeoutInSeconds = "10", id = "DateOfCorrespondence-day")
    public WebElementFacade dateCorrespondenceSentDayField;

    @FindBy(timeoutInSeconds = "10", id = "DateOfCorrespondence-month")
    public WebElementFacade dateCorrespondenceSentMonthField;

    @FindBy(timeoutInSeconds = "10", id = "DateOfCorrespondence-year")
    public WebElementFacade dateCorrespondenceSentYearField;

    @FindBy(timeoutInSeconds = "10", id = "DateReceived-day")
    public WebElementFacade dateCorrespondenceReceivedDayField;

    @FindBy(timeoutInSeconds = "10", id = "DateReceived-month")
    public WebElementFacade dateCorrespondenceReceivedMonthField;

    @FindBy(timeoutInSeconds = "10", id = "DateReceived-year")
    public WebElementFacade dateCorrespondenceReceivedYearField;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='CopyNumberTen-radios']//label[text()='Yes']")
    public WebElementFacade shouldResponseBeCopiedN10YesRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='CopyNumberTen-radios']//label[text()='No']")
    public WebElementFacade shouldResponseBeCopiedN10NoRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-day']")
    public WebElementFacade dtenDraftingDeadlineDayField;

    @FindBy(timeoutInSeconds = "10", xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-month']")
    public WebElementFacade dtenDraftingDeadlineMonthField;

    @FindBy(timeoutInSeconds = "10", xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-year']")
    public WebElementFacade dtenDraftingDeadlineYearField;

    @FindBy(timeoutInSeconds = "10", xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-day']")
    public WebElementFacade dtenDispatchDeadlineDayField;

    @FindBy(timeoutInSeconds = "10", xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-month']")
    public WebElementFacade dtenDispatchDeadlineMonthField;

    @FindBy(timeoutInSeconds = "10", xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-year']")
    public WebElementFacade dtenDispatchDeadlineYearField;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='HomeSecInterest-radios']//label[text()='Yes']")
    public WebElementFacade homeSecInterestYesRadioButton;

    @FindBy(timeoutInSeconds = "10", xpath = "//div[@id='HomeSecInterest-radios']//label[text()='No']")
    public WebElementFacade homeSecInterestNoRadioButton;

    // Basic Methods

    public void enterDTENDraftingDeadline(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "What is the drafting deadline?");
        setSessionVariable("dtenInitialDraftDeadline").to(date);
    }

    public void enterDTENDispatchDeadline(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "What is the dispatch deadline?");
        setSessionVariable("dtenDispatchDeadline").to(date);
    }

    public void enterCorrespondenceSentDate(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "When was the correspondence sent?");
    }

    public void clearDateCorrespondenceSent() {
        clearDateFieldsWithHeading("When was the correspondence sent?");
    }

    public void overwriteCorrespondenceReceivedDate(String date) {
        clearDateFieldsWithHeading("When was the correspondence received?");
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "When was the correspondence received?");
    }

    public void clearDateCorrespondenceReceived() {
        clearDateFieldsWithHeading("When was the correspondence received?");
    }

    public void selectACorrespondenceReceivedChannel() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("How was the correspondence received?");
    }

    public void selectASpecificCopyToNoTenOption(String yesOrNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesOrNo, "Should the response be copied to Number 10?");
    }

    public void selectACopyToNoTenOption() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Should the repsonse be copied to Number 10?");
    }

    public void selectASpecificHomeSecInterestOption(String yesOrNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesOrNo, "Does the Home Secretary have an interest in this case?");
    }

    public void selectAHomeSecInterestOption() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Does the Home Secretary have an interest in this case?");
    }

    public void selectASpecificHomeSecReplyOption(String yesOrNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesOrNo, "Is this a potential Home Secretary Reply case?");
    }

    public void selectAHomeSecReplyOption() {
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Is this a potential Home Secretary Reply case?");
    }

    // Multi Step Methods

    public void fillAllMandatoryCorrespondenceFields() {
        if (dtenCase()) {
            enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(+10));
            enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(+20));
            clickContinueButton();
        }
        enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        selectACorrespondenceReceivedChannel();
        if (minCase() | troCase()) {
            selectASpecificCopyToNoTenOption("No");
            selectAHomeSecInterestOption();
        }
        if (minCase()) {
            selectAHomeSecReplyOption();
        }
    }
}
