package com.hocs.test.glue.dcu;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.Dashboard;
import com.hocs.test.pages.platform.UnallocatedCaseView;
import com.hocs.test.pages.dcu.Dispatch;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs extends BasePage {

    @Managed

    Dispatch dispatch;

    Dashboard dashboard;

    UnallocatedCaseView unallocatedCaseView;

    @When("I complete the dispatch stage")
    public void completeTheDispatchStage() {
        if (!dispatch.dispatchAcceptRadioButton.isVisible()) {
            dashboard.getCurrentCase();
            safeClickOn(unallocatedCaseView.allocateToMeLink);
        }
        safeClickOn(dispatch.dispatchAcceptRadioButton);
        safeClickOn(continueButton);
    }
}
