package com.hocs.test.glue.dcu;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.Dispatch;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs extends BasePage {

    @Managed

    Dispatch dispatch;

    Dashboard dashboard;

    CaseView caseView;

    @When("I complete the dispatch stage")
    public void completeTheDispatchStage() {
        if (!dispatch.dispatchAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(caseView.allocateToMeLink);
        }
        safeClickOn(dispatch.dispatchAcceptRadioButton);
        safeClickOn(continueButton);
    }
}
