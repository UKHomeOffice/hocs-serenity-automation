package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ActionsTab extends BasePage {

    @FindBy(xpath = "//a[text()='Actions']")
    public WebElementFacade actionsTab;

    public void selectActionsTab() {
        if(!actionsTabIsActiveTab()) {
            safeClickOn(actionsTab);
        }
    }

    public boolean actionsTabIsActiveTab() {
        return actionsTab.getAttribute("class").contains("active");
    }

    // Extensions

    public void clickExtendThisCase() {
        clickTheLink("Extend this case");
    }

    public void selectAnExtensionType() {
        selectRandomOptionFromDropdownWithHeading("What type of extension do you want to apply?");
    }

    public void selectHowManyDayToExtendDeadlineBy() {
        String numberOfDays = selectRandomOptionFromDropdownWithHeading("How many working days do you want to extend the case by?");
        setSessionVariable("numberOfDays").to(Integer.parseInt(numberOfDays));
    }

    public void selectASpecificAmountOfDaysToExtendDeadlineBy(String numberOfDays) {
        selectSpecificOptionFromDropdownWithHeading(numberOfDays,"How many days do you want to extend the case deadline by?");
        setSessionVariable("numberOfDays").to(Integer.parseInt(numberOfDays));
    }

    public void selectWhenToExtendDeadlineFrom() {
        selectRandomOptionFromDropdownWithHeading("Case will be extended from:");
    }

    public void selectASpecificStartPointToExtendDeadlineFrom(String extensionStartPoint) {
        selectSpecificOptionFromDropdownWithHeading(extensionStartPoint,"Case will be extended from:");
    }

    public void assertThatNoSelectableOptionsPresentInAmountOfWorkingsDaysDropdown() {
        if (checkSelectableOptionsPresentInDropdownWithHeading("How many days do you want to extend the case deadline by?")) {
            Assert.fail("Selectable options present in dropdown");
        }
    }

    // Appeals

    public void clickAddAnAppeal() {
        clickTheLink("Add an appeal");
    }

    public void selectApplyExtensionToTheCaseOption(String option) {
        selectSpecificRadioButton(option);
        if (option.equalsIgnoreCase("YES")) {
            enterTextIntoTextAreaWithHeading("Reason");
        }
        safeClickOn(continueButton);
    }

    public void addAnAppealToTheCase() {
        String appealType = selectRandomOptionFromDropdownWithHeading("Which type of appeal needs to be applied?");
        setSessionVariable("appealType").to(appealType);
        if (appealType.equalsIgnoreCase("Internal Review")) {
            String appealOfficerDirectorate = selectRandomOptionFromDropdownWithHeading("Directorate");
            setSessionVariable("appealOfficerDirectorate").to(appealOfficerDirectorate);
            String appealOfficerName = selectRandomOptionFromDropdownWithHeading("Officer");
            setSessionVariable("appealOfficerName").to(appealOfficerName);
        }
        clickTheButton("Add Appeal");
    }

    public void completeAppeal() {
        selectSpecificRadioButtonFromGroupWithHeading("Yes", "Has this been completed?");
        setSessionVariable("appealComplete").to("Yes");
        enterDateIntoDateFieldsWithHeading(getTodaysDate(), "When was this completed?");
        setSessionVariable("appealCompletionDate").to(getTodaysDate());
        String appealOutcome = selectRandomRadioButtonFromGroupWithHeading("What was the outcome?");
        if (appealOutcome.equalsIgnoreCase("Decision Part Upheld")) {
            appealOutcome = "Decision upheld in part";
        }
        setSessionVariable("appealOutcome").to(appealOutcome);
        String appealComplexity = selectRandomRadioButtonFromGroupWithHeading("Was the case complex?");
        setSessionVariable("appealComplexity").to(appealComplexity);
        enterSpecificTextIntoTextAreaWithHeading("Test Details","Details");
        setSessionVariable("appealDetails").to("Test Details");
        clickTheButton("Update");
    }

    public void clickUpdateLinkForAppeal() {
        WebElementFacade updateHypertextOfSpecificAppeal = findBy("//td[text()='" + sessionVariableCalled("appealType") + "']/following-sibling::td/a");
        safeClickOn(updateHypertextOfSpecificAppeal);
    }

    public void enterAReasonForTheExtension() {
        String extensionReason = enterTextIntoTextAreaWithHeading("Please enter a reason for the extension.");
        setSessionVariable("extensionReason").to(extensionReason);
    }

    public void assertStatusOfAppealIs(String appealStatus) {
        selectActionsTab();
        String registeredAppealType = sessionVariableCalled("appealType");
        String displayedAppealStatus = findBy("//td[text()='" + registeredAppealType + "']/following-sibling::td").getText();
        if (!displayedAppealStatus.equalsIgnoreCase(appealStatus)) {
            Assert.fail("Expected appeal status was " + appealStatus + " but actual appeal status was " + displayedAppealStatus);
        }
    }

    // Record Interest

    public void clickRecordInterest() {
        clickTheLink("Record Interest");
    }

    public void selectSpecificTypeOfInterest(String typeOfInterest) {
        selectSpecificOptionFromDropdownWithHeading(typeOfInterest, "What type of interest do you want to record?");
        setSessionVariable("typeOfInterest").to(typeOfInterest);
    }

    public void selectAInterestedParty() {
        String interestedParty = selectRandomOptionFromDropdownWithHeading("Interested party");
        setSessionVariable("interestedParty").to(interestedParty);
    }

    public void enterDetailsOfInterest(String detailsOfInterest) {
        enterSpecificTextIntoTextAreaWithHeading(detailsOfInterest, "Details of Interest");
        setSessionVariable("detailsOfInterest").to(detailsOfInterest);
    }

    public void assertDetailsOfRecordedInterestVisible() {
        String interestedParty = sessionVariableCalled("interestedParty");
        String expectedDetailsOfInterest = sessionVariableCalled("detailsOfInterest");
        String displayedDetailsOfInterest = findBy("//td[text()='" + interestedParty + "']/following-sibling::td").getText();
        if (!expectedDetailsOfInterest.equals(displayedDetailsOfInterest)) {
            Assert.fail("Expected details of interest to be '" + expectedDetailsOfInterest + "' but displayed details of interest were '" + displayedDetailsOfInterest + "'");
        }
    }

    public void selectToUpdateRecordedInterest() {
        selectActionsTab();
        String interestedParty = sessionVariableCalled("interestedParty");
        WebElementFacade updateLink = findBy("//td[text()='" + interestedParty + "']/following-sibling::td/a");
        safeClickOn(updateLink);
    }
}