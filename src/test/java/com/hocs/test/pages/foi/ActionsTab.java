package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class ActionsTab extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//a[text()='Actions']")
    public WebElementFacade actionsTabButton;

    @FindBy(xpath = "//a[text()='Extend this case']")
    public WebElementFacade extendThisCaseHypertext;

    @FindBy(xpath = "//a[text()='Manage appeals']")
    public WebElementFacade manageAppealsHypertext;

    @FindBy(xpath = "//a[text()='Record an appeal']")
    public WebElementFacade recordAnAppealHypertext;

    public void selectActionsTab() {
        safeClickOn(actionsTabButton);
    }

    public void clickExtendCaseHypertext() {
        safeClickOn(extendThisCaseHypertext);
    }

    public void clickManageAppealsHypertext() {
        safeClickOn(manageAppealsHypertext);
    }

    public void selectApplyExtensionToTheCaseOption(String option) {
        selectSpecificRadioButton(option);
        if (option.equalsIgnoreCase("YES")) {
            enterTextIntoTextAreaWithHeading("Reason");
        }
        safeClickOn(continueButton);
    }

    public void addAnAppealToTheCase() {
        safeClickOn(recordAnAppealHypertext);
        String appealType = recordCaseData.selectRandomOptionFromDropdownWithHeading("Which type of appeal needs to be applied?");
        setSessionVariable("appealType").to(appealType);
        if (appealType.equalsIgnoreCase("Internal Review")) {
            String appealOfficerDirectorate = recordCaseData.selectRandomOptionFromDropdownWithHeading("Internal review officer directorate");
            setSessionVariable("appealOfficerDirectorate").to(appealOfficerDirectorate);
            String appealOfficerName = recordCaseData.selectRandomOptionFromDropdownWithHeading("Internal review officer name");
            setSessionVariable("appealOfficerName").to(appealOfficerName);
        }
        clickTheButton("Log Appeal");
    }

    public void completeAppeal() {
        WebElementFacade updateHypertextOfSpecificAppeal = findBy("//label[text()='" + sessionVariableCalled("appealType") + "']/parent::td/following-sibling::td/a");
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
}