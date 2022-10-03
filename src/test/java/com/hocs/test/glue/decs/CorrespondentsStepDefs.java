package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class CorrespondentsStepDefs extends BasePage {

    Correspondents correspondents;

    @When("I add an additional correspondent")
    public void iAddAnAdditionalCorrespondent() {
        addACorrespondentThatIsOrIsNotAnMP("Is not");
        correspondents.fillMandatoryCorrespondentFieldsForSecondaryContact();
        clickAddButton();
    }

    @When("I select to add a correspondent that {string} a member of parliament")
    public void addACorrespondentThatIsOrIsNotAnMP(String isOrIsNot) {
        waitABit(2000);
        correspondents.selectToAddACorrespondent();
        if (isOrIsNot.equalsIgnoreCase("IS")) {
            correspondents.selectCorrespondentIsMP();
        } else if (isOrIsNot.equalsIgnoreCase("IS NOT")) {
            correspondents.selectCorrespondentIsNotMP();
        }
    }

    @And("I select to add a correspondent to the case")
    public void iSelectToAddACorrespondentToTheCase() {
        correspondents.selectToAddACorrespondent();
    }

    @And("I add a {string} correspondent")
    public void iAddACorrespondent(String correspondentType) {
        if (correspondentType.equalsIgnoreCase("MEMBER")) {
            correspondents.addAMemberCorrespondent();
        } else {
            correspondents.addANonMemberCorrespondentOfType(correspondentType);
        }
    }

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        correspondents.addASpecificMemberCorrespondent(name);
    }

    @And("I confirm the primary correspondent")
    public void IConfirmThePrimaryCorrespondent(){
        correspondents.confirmPrimaryCorrespondent();
    }
}
