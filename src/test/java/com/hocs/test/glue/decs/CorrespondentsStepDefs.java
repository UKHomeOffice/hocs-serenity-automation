package com.hocs.test.glue.decs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

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

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        correspondents.addASpecificMemberCorrespondent(name);
    }

    @And("I confirm the primary correspondent")
    public void IConfirmThePrimaryCorrespondent(){
        correspondents.confirmPrimaryCorrespondent();
    }

    @When("I manage the correspondents using the People tab")
    public void iManageTheCorrespondentsUsingThePeopleTab() {

    }

    @And("I add a {string} correspondent to the case")
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

    @And("I remove {string} as a correspondent of the case")
    public void iRemoveACorrespondentFromTheCase(String correspondent) {
        caseView.waitForCaseToLoad();
        peopleTab.refreshPeopleTab();
        peopleTab.removeCorrespondent(correspondent);
    }

    @And("I change the primary correspondent of the case to {string}")
    public void iChangePrimaryCorrespondent(String correspondent) {
        caseView.waitForCaseToLoad();
        peopleTab.refreshPeopleTab();
        peopleTab.changePrimaryCorrespondentToSpecificCorrespondent(correspondent);
    }

    @And("I change the primary correspondent of the case")
    public void iChangeThePrimaryCorrespondentOfTheCase() {
        caseView.waitForCaseToLoad();
        peopleTab.refreshPeopleTab();
        peopleTab.changePrimaryCorrespondent();
    }

    @Then("the new correspondent is added to the case")
    public void assertNewCorrespondentIsAddedToTheCase() {
        peopleTab.assertNewCorrespondentIsDisplayed();
    }

    @Then("the primary correspondent should be {string}")
    public void assertPrimaryCorrespondentHasChangedTo(String correspondent) {
        peopleTab.assertNewPrimaryCorrespondent(correspondent);
    }

    @Then("{string} should be removed as a correspondent on the case")
    public void assertCorrespondentRemovedFromCase(String correspondent) {
        peopleTab.assertCorrespondentHasBeenRemoved(correspondent);
    }
}
