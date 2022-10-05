package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.CaseView;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.PeopleTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CorrespondentsStepDefs extends BasePage {

    Correspondents correspondents;

    CaseView caseView;

    PeopleTab peopleTab;

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

    @And("I add {int} {string} correspondents")
    public void iAddCorrespondents(int amount, String correspondentType) {
        for (int i = 0; i < amount; i++) {
            iAddACorrespondent(correspondentType);
        }
    }

    @And("I add {string} as a correspondent")
    public void IAddMPCorrespondent(String name) {
        correspondents.addASpecificMemberCorrespondent(name);
    }

    @And("I confirm the primary correspondent")
    public void IConfirmThePrimaryCorrespondent(){
        correspondents.confirmPrimaryCorrespondent();
    }

    @When("I manage the correspondents using the People tab")
    public void iManageTheCorrespondentsUsingThePeopleTab() {
        peopleTab.selectPeopleTab();
        peopleTab.selectToManagePeople();
    }

    @And("I add a {string} correspondent using the People tab")
    public void iAddANewCorrespondent(String correspondentType) {
        peopleTab.selectPeopleTab();
        switch (correspondentType.toUpperCase()) {
            case "MEMBER":
                peopleTab.addAMemberCorrespondent();
                break;
            case "PUBLIC":
                peopleTab.addAPublicCorrespondent();
                break;
            default:
                pendingStep(correspondentType + " is not defined within " + getMethodName());
        }
    }

    @And("I edit the {string} detail for {string}")
    public void iEditCorrespondentDetails(String detail, String correspondent) {
        peopleTab.editCorrespondent(detail, correspondent);
    }

    @And("I change the primary correspondent of the case")
    public void iChangeThePrimaryCorrespondentOfTheCase() {
        caseView.waitForCaseToLoad();
        peopleTab.refreshPeopleTab();
        peopleTab.changePrimaryCorrespondent();
    }

    @Then("the correspondent is added to the case")
    public void assertNewCorrespondentIsAddedToTheCase() {
        peopleTab.assertAddedCorrespondentIsDisplayed();
    }

    @And("I progress the case to save the change of primary correspondent")
    public void iProgressTheCaseToSaveTheChangeOfPrimaryCorrespondent() {
        if (dcuCase()) {
            clickTheButton("Finish");
        }
        if (mpamCase()) {
            clickTheButton("Move to Triage");
        }
        if (mtsCase() | complaintCase() | toCase()) {
            clickTheButton("Continue");
        }
    }

    @And("I confirm the change of primary correspondent")
    public void iConfirmTheChangeOfPrimaryCorrespondent() {
        clickTheButton("Finish");
    }

    @And("I remove a non-primary correspondent from the case")
    public void iRemoveANonPrimaryCorrespondentFromTheCase() {
        String correspondentToRemove = getLabelOfAnUnselectedRadioButtonFromGroupWithHeading("Person we will write back to");
        correspondents.removeASpecificCorrespondent(correspondentToRemove);
        setSessionVariable("removedCorrespondent").to(correspondentToRemove);
    }

    @Then("the removed correspondent should no longer be visible")
    public void theRemovedCorrespondentShouldNoLongerBeVisible() {
        peopleTab.assertCorrespondentHasBeenRemoved(sessionVariableCalled("removedCorrespondent"));
    }
}
