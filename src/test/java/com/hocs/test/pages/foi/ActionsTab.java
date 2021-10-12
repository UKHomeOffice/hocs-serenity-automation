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

    @FindBy(xpath = "//a[text()='Record an Appeal']")
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
        clickTheButton("Log Appeal");
    }

    public void completeSpecificAppeal(String appeal) {
        WebElementFacade updateHypertextOfSpecificAppeal = findBy("//label[text()='" + appeal + "']/parent::td/following-sibling::td/a");
        safeClickOn(updateHypertextOfSpecificAppeal);
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading("Yes", "Has this been completed?");
        recordCaseData.enterDateIntoDateFieldsWithHeading(getTodaysDate(), "When was this completed?");
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("What was the outcome?");
        recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Was the case complex?");
        recordCaseData.enterTextIntoTextAreaWithHeading("Details");
        clickTheButton("Update");
    }
}