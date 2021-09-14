package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput extends BasePage {

    AddCorrespondent addCorrespondent;

    RecordCaseData recordCaseData;

    // Elements

    @FindBy(xpath = "//label[text()='Email']")
    public WebElementFacade emailOriginalChannelRadioButton;

    @FindBy(xpath = "//label[text()='Post']")
    public WebElementFacade postOriginalChannelRadioButton;

    @FindBy(xpath = "//label[text()='Phone']")
    public WebElementFacade phoneOriginalChannelRadioButton;

    @FindBy(xpath = "//label[text()='No. 10']")
    public WebElementFacade numberTenOriginalChannelRadioButton;

    @FindBy(id = "DateOfCorrespondence-day")
    public WebElementFacade dateCorrespondenceSentDayField;

    @FindBy(id = "DateOfCorrespondence-month")
    public WebElementFacade dateCorrespondenceSentMonthField;

    @FindBy(id = "DateOfCorrespondence-year")
    public WebElementFacade dateCorrespondenceSentYearField;

    @FindBy(id = "DateReceived-day")
    public WebElementFacade dateCorrespondenceReceivedDayField;

    @FindBy(id = "DateReceived-month")
    public WebElementFacade dateCorrespondenceReceivedMonthField;

    @FindBy(id = "DateReceived-year")
    public WebElementFacade dateCorrespondenceReceivedYearField;

    @FindBy(xpath = "//div[@id='CopyNumberTen-radios']//label[text()='Yes']")
    public WebElementFacade shouldResponseBeCopiedN10YesRadioButton;

    @FindBy(xpath = "//div[@id='CopyNumberTen-radios']//label[text()='No']")
    public WebElementFacade shouldResponseBeCopiedN10NoRadioButton;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-day']")
    public WebElementFacade dtenDraftingDeadlineDayField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-month']")
    public WebElementFacade dtenDraftingDeadlineMonthField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-year']")
    public WebElementFacade dtenDraftingDeadlineYearField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-day']")
    public WebElementFacade dtenDispatchDeadlineDayField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-month']")
    public WebElementFacade dtenDispatchDeadlineMonthField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-year']")
    public WebElementFacade dtenDispatchDeadlineYearField;

    @FindBy(xpath = "//div[@id='HomeSecInterest-radios']//label[text()='Yes']")
    public WebElementFacade homeSecInterestYesRadioButton;

    @FindBy(xpath = "//div[@id='HomeSecInterest-radios']//label[text()='No']")
    public WebElementFacade homeSecInterestNoRadioButton;

    // Basic Methods

    public void enterDTENDraftingDeadline(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "What is the drafting deadline?");
    }

    public void enterDTENDispatchDeadline(String date) {
        recordCaseData.enterDateIntoDateFieldsWithHeading(date, "What is the dispatch deadline?");
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
        String caseType = sessionVariableCalled("caseType");
        if (caseType.equals("DTEN")) {
            enterDTENDraftingDeadline(getDatePlusMinusNDaysAgo(+10));
            enterDTENDispatchDeadline(getDatePlusMinusNDaysAgo(+20));
            safeClickOn(continueButton);
        }
        enterCorrespondenceSentDate(getDatePlusMinusNDaysAgo(-2));
        selectACorrespondenceReceivedChannel();
        if (caseType.equals("MIN") | caseType.equals("TRO")) {
            selectASpecificCopyToNoTenOption("No");
            selectAHomeSecInterestOption();
        }
        if (caseType.equals("MIN")) {
            selectAHomeSecReplyOption();
        }
    }

    // Assertions

    public void assertPageTitle() {
        assertPageTitle("Data Input");
    }
}
