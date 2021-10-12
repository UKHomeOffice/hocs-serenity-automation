package com.hocs.test.glue.foi;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.foi.ActionsTab;
import io.cucumber.java.en.And;

public class ActionsTabStepDefs extends BasePage {

    ActionsTab actionsTab;

    @And("I select the actions tab")
    public void iSelectTheActionsTab() {
        actionsTab.selectActionsTab();
    }

    @And("I select to extend the deadline of the FOI case")
    public void iSelectToExtendTheDeadlineOfTheFOICase() {
        actionsTab.clickExtendCaseHypertext();
        actionsTab.selectApplyExtensionToTheCaseOption("Yes");
    }

    @And("I select the manage appeals hypertext")
    public void iSelectTheManageAppealsHypertext() {
        actionsTab.clickManageAppealsHypertext();
    }

    @And("I add an appeal to the FOI case")
    public void iAddAnAppealToTheFOICase() {
        actionsTab.addAnAppealToTheCase();
    }

    @And("I enter the appeal information and complete the appeal")
    public void iEnterTheAppealInformationAndCompleteTheAppeal() {
        actionsTab.completeAppeal();
    }

}
