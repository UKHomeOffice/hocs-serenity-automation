package com.hocs.test.glue.dcu;

import com.hocs.test.pages.dcu.DCUProgressCase;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CreateCase;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.dcu.Dispatch;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

public class DispatchStepDefs extends BasePage {

    Dispatch dispatch;

    @And("I select that I am unable to dispatch the case")
    public void iSelectThatIAmUnableToDispatchTheCase() {
        dispatch.selectAbleToDispatch("No");
        safeClickOn(continueButton);
    }

    @And("I submit a reason why I am unable to dispatch the case")
    public void iSubmitAReasonWhyIAmUnableToDispatchTheCase() {
        dispatch.enterReasonUnableToDispatch();
        safeClickOn(finishButton);
    }

    @When("I submit that I am able to dispatch the case")
    public void iEnterThatIAmAbleToDispatchTheCase() {
        dispatch.selectAbleToDispatch("Yes");
        safeClickOn(continueButton);
    }
}
