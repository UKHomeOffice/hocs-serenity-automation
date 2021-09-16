package com.hocs.test.pages.dcu;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.AddCorrespondent;
import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput extends BasePage {

    AddCorrespondent addCorrespondent;

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

    // Multi Step Methods

    public void moveCaseFromDataInputToMarkup() {
        fillAllMandatoryCorrespondenceFields();
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        safeClickOn(finishButton);
    }

    public void selectSpecificHomeSecReplyOption(String yesOrNo) {
        selectSpecificRadioButtonFromGroupWithHeading(yesOrNo, "Is this a potential Home Secretary Reply case?");
    }

    public void selectAHomeSecReplyOption() {
        selectRandomRadioButtonFromGroupWithHeading("Is this a potential Home Secretary Reply case?");
    }

    public void dataInputFullFlowWithCopyToN10() {
        fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes();
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        safeClickOn(finishButton);
    }

    public void clearDateCorrespondenceReceived() {
        dateCorrespondenceReceivedDayField.clear();
        dateCorrespondenceReceivedMonthField.clear();
        dateCorrespondenceReceivedYearField.clear();
    }

    public void clearDateCorrespondenceSent() {
        dateCorrespondenceSentDayField.clear();
        dateCorrespondenceSentMonthField.clear();
        dateCorrespondenceSentYearField.clear();
    }

    public void fillAllMandatoryCorrespondenceFields() {
        String caseType = sessionVariableCalled("caseType");
        if (caseType.equals("DTEN")) {
            typeIntoDateFields(dtenDraftingDeadlineDayField, dtenDraftingDeadlineMonthField, dtenDraftingDeadlineYearField, "01/01/2019");
            setSessionVariable("dtenInitialDraftDeadline").to("01/01/2019");
            typeIntoDateFields(dtenDispatchDeadlineDayField, dtenDispatchDeadlineMonthField, dtenDispatchDeadlineYearField, "01/01/2019");
            setSessionVariable("dtenDispatchDeadline").to("01/01/2019");
            safeClickOn(continueButton);
        }
        typeIntoDateFields(dateCorrespondenceSentDayField, dateCorrespondenceSentMonthField, dateCorrespondenceSentYearField,
                getDatePlusMinusNDaysAgo(-2));
        safeClickOn(emailOriginalChannelRadioButton);
        if (caseType.equals("MIN") | caseType.equals("TRO")) {
            safeClickOn(shouldResponseBeCopiedN10NoRadioButton);
            safeClickOn(homeSecInterestYesRadioButton);
        }
        if (caseType.equals("MIN")) {
            selectAHomeSecReplyOption();
        }
    }

    public void fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes() {
        typeIntoDateFields(dateCorrespondenceSentDayField, dateCorrespondenceSentMonthField, dateCorrespondenceSentYearField,
                getDatePlusMinusNDaysAgo(-2));
        safeClickOn(emailOriginalChannelRadioButton);
        safeClickOn(shouldResponseBeCopiedN10YesRadioButton);
        safeClickOn(homeSecInterestYesRadioButton);
        selectAHomeSecReplyOption();
    }

    public void invalidCorrespondenceReceivedDate() {
        typeIntoDateFields(dateCorrespondenceReceivedDayField, dateCorrespondenceReceivedMonthField, dateCorrespondenceReceivedYearField,
                getDatePlusMinusNDaysAgo(1));
    }

    public void invalidCorrespondenceSentDate() {
        typeIntoDateFields(dateCorrespondenceSentDayField, dateCorrespondenceSentMonthField, dateCorrespondenceSentYearField,
                getDatePlusMinusNDaysAgo(1));
    }

    public void completeDataInputStageWithMPCorrespondent(String correspondent) {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAMemberCorrespondent(correspondent);
        safeClickOn(finishButton);
    }

    public void completeDataInputStageWithPublicCorrespondent() {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        safeClickOn(finishButton);
    }

    public void completeDataInputStageAndStoreEnteredInformation() {
        typeIntoDateFields(dateCorrespondenceSentDayField, dateCorrespondenceSentMonthField, dateCorrespondenceSentYearField,
                getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear());
        String currentDay = dateCorrespondenceSentDayField.getValue();
        setSessionVariable("currentDay").to(currentDay);
        String currentMonth = dateCorrespondenceSentMonthField.getValue();
        setSessionVariable("currentMonth").to(currentMonth);
        String currentYear = dateCorrespondenceSentYearField.getValue();
        setSessionVariable("currentYear").to(currentYear);

        safeClickOn(emailOriginalChannelRadioButton);
        String selectedCorrespondenceReceivedRadioButton = emailOriginalChannelRadioButton.getText();
        setSessionVariable("selectedCorrespondenceReceivedRadioButton").to(selectedCorrespondenceReceivedRadioButton);

        safeClickOn(shouldResponseBeCopiedN10NoRadioButton);
        String selectedCopiedN10NoRadioButton = shouldResponseBeCopiedN10NoRadioButton.getTextContent();
        setSessionVariable("selectedCopiedN10NoRadioButton").to(selectedCopiedN10NoRadioButton);
        safeClickOn(homeSecInterestYesRadioButton);
        String selectedHomeSecInterest = homeSecInterestYesRadioButton.getText();
        setSessionVariable("selectedHomeSecInterestRadioButton").to(selectedHomeSecInterest);
        safeClickOn(continueButton);
        waitABit(1000);
        addCorrespondent.addAMemberCorrespondent("Nicola Sturgeon MSP");
        safeClickOn(finishButton);
    }

    public void completeDataInputStageWithThreeMPCorrespondents() {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        addCorrespondent.addAMemberCorrespondent("Nicola Sturgeon");
        addCorrespondent.addAMemberCorrespondent("Theresa May");
        safeClickOn(finishButton);
    }

    public void completeDataInputWithThreePublicCorrespondents() {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        safeClickOn(finishButton);
    }

    public void completeDataInputStageSpecifyingHomeSecInterest(boolean interest) {
        String caseType = sessionVariableCalled("caseType");
        typeIntoDateFields(dateCorrespondenceSentDayField, dateCorrespondenceSentMonthField, dateCorrespondenceSentYearField,
                getDatePlusMinusNDaysAgo(-2));
        safeClickOn(emailOriginalChannelRadioButton);
        safeClickOn(shouldResponseBeCopiedN10NoRadioButton);
        if (interest) {
            safeClickOn(homeSecInterestYesRadioButton);
        } else {
            safeClickOn(homeSecInterestNoRadioButton);
        }
        if (caseType.equals("MIN")) {
            selectAHomeSecReplyOption();
        }
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondentOfType("Constituent");
        safeClickOn(finishButton);
    }

    // Assertions

    public void assertPageTitle() {
        assertPageTitle("Data Input");
    }
}
