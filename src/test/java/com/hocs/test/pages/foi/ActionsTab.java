package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ActionsTab extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[text()='Actions']")
    public WebElementFacade actionsTabButton;

    @FindBy(xpath = "//a[text()='Extend this case']")
    public WebElementFacade extendThisCaseHypertext;

    @FindBy(xpath = "//a[text()='Record an appeal']")
    public WebElementFacade recordAnAppealHypertext;

    public void selectActionsTab() {
        safeClickOn(actionsTabButton);
    }

    //Extensions

    public void clickExtendCaseHypertext() {
        safeClickOn(extendThisCaseHypertext);
    }

    public void selectAnExtensionType() {
        selectRandomOptionFromDropdownWithHeading("What type of extension do you want to apply?");
    }

    public void selectHowManyDayToExtendDeadlineBy() {
        String numberOfDays = selectRandomOptionFromDropdownWithHeading("How many days do you want to extend the case deadline by?");
        setSessionVariable("numberOfDays").to(Integer.parseInt(numberOfDays));
    }

    public void selectASpecificAmountOfDaysToExtendDeadlineBy(String numberOfDays) {
        selectSpecificOptionFromDropdownWithHeading(numberOfDays,"How many days do you want to extend the case deadline by?");
        setSessionVariable("numberOfDays").to(Integer.parseInt(numberOfDays));
    }

    public void selectWhenToExtendDeadlineFrom() {
        selectRandomOptionFromDropdownWithHeading("Case will be extended from:");
    }

    public void selectASpecificStartPointToExtendDeadlineFrom(String startPoint) {
        selectSpecificOptionFromDropdownWithHeading(startPoint,"Case will be extended from:");
    }

    //Appeals

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
        String appealType = recordCaseData.selectRandomOptionFromDropdownWithHeading("Which type of appeal needs to be applied?");
        setSessionVariable("appealType").to(appealType);
        if (appealType.equalsIgnoreCase("Internal Review")) {
            String appealOfficerDirectorate = recordCaseData.selectRandomOptionFromDropdownWithHeading("Internal review officer directorate");
            setSessionVariable("appealOfficerDirectorate").to(appealOfficerDirectorate);
            String appealOfficerName = recordCaseData.selectRandomOptionFromDropdownWithHeading("Internal review officer name");
            setSessionVariable("appealOfficerName").to(appealOfficerName);
        }
        clickTheButton("Add Appeal");
    }

    public void completeAppeal() {
        WebElementFacade updateHypertextOfSpecificAppeal = findBy("//td[text()='" + sessionVariableCalled("appealType") + "']/following-sibling::td/a");
        safeClickOn(updateHypertextOfSpecificAppeal);
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Has this been completed?");
        setSessionVariable("appealComplete").to("Yes");
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "When was this completed?");
        setSessionVariable("appealCompletionDate").to(getTodaysDate());
        String appealOutcome = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("What was the outcome?");
        setSessionVariable("appealOutcome").to(appealOutcome);
        String appealComplexity = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Was the case complex?");
        setSessionVariable("appealComplexity").to(appealComplexity);
        recordCaseData.enterSpecificTextIntoTextAreaWithHeading("Test Details","Details");
        setSessionVariable("appealDetails").to("Test Details");
        clickTheButton("Update");
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
}