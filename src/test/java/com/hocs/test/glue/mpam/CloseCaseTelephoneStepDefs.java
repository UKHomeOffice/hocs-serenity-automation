package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.CloseCaseTelephone;
import com.hocs.test.pages.mpam.DispatchStages;
import com.hocs.test.pages.mpam.Draft;
import com.hocs.test.pages.mpam.QA;
import com.hocs.test.pages.mpam.Triage;
import io.cucumber.java.en.And;

public class CloseCaseTelephoneStepDefs extends BasePage {

    CloseCaseTelephone closeCaseTelephone;

    Triage triage;

    Draft draft;

    QA qa;

    DispatchStages dispatchStages;

    @And("I select the Close Case Telephone radio button at the {string} stage and confirm")
    public void iSelectTheCloseCaseTelephoneRadioButton(String stage) {
        setSessionVariable("closeCaseTelephoneStage").to(stage.toUpperCase());
        switch (stage.toUpperCase()) {
            case "TRIAGE":
                safeClickOn(triage.setEnquiryHypertext);
                triage.selectEnquirySubject("Person Specific");
                triage.selectEnquiryReason("Allowed appeal enquiry update");
                triage.setBusinessUnit();
                break;
            case "DRAFT":
            case "QA":
                break;
            case "AWAITING DISPATCH":
                dispatchStages.inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
        closeCaseTelephone.selectCloseCaseTelephone();
    }

    @And("I enter the mandatory information at the Close Case (Telephone) screen and close the case")
    public void iEnterMandatoryInformationAtCloseCaseScreen() {
        closeCaseTelephone.enterExplanationForClosingCase("Test");
        closeCaseTelephone.selectTelephoneContactRoute("Telephone Surgery");
        clickTheButton("Close Case");
    }

    @And("I check the validation at the Close Case Telephone screen")
    public void iCheckValidationAtCloseCaseScreen() {
        clickTheButton("Close Case");
        closeCaseTelephone.assertErrorMessageIsDisplayed("Explanation for closing case");
        closeCaseTelephone.assertErrorMessageIsDisplayed("Telephone contact route");
    }
}