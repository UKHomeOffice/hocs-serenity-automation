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

    @When("I select a different correspondent as the primary correspondent")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
        if (getDECSCurrentPageTitle().equals("Manage People")) {
            peopleTab.selectDifferentPrimaryCorrespondent();
        } else {
            correspondents.selectDifferentPrimaryCorrespondent();
        }
    }

    @And("I confirm the primary correspondent")
    public void IConfirmThePrimaryCorrespondent(){
        correspondents.confirmPrimaryCorrespondent();
    }

    @And("I remove the primary correspondent")
    public void removePrimaryCorrespondent() {
        correspondents.removeACorrespondent();
    }

    @Then("there shouldn't be a primary correspondent displayed")
    public void thereShouldntBeAPrimaryCorrespondentDisplayed() {
        correspondents.assertNoPrimaryCorrespondentDisplayed();
    }

    @Then("the submitted correspondent should be visible in the list of correspondents")
    public void theSubmittedCorrespondentShouldBeVisibleInTheListOfCorrespondents() {
        correspondents.assertPrimaryCorrespondentIs(sessionVariableCalled("correspondentFullName"));
    }

    @And("I edit the primary correspondents name")
    public void iEditThePrimaryCorrespondent() {
        correspondents.editPrimaryCorrespondent();
    }

    @Then("the primary correspondents name should be updated")
    public void thePrimaryCorrespondentsNameShouldBeUpdated() {
        correspondents.assertPrimaryCorrespondentIs(sessionVariableCalled("correspondentFullName"));
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
        peopleTab.selectToManagePeople();
        peopleTab.selectDifferentPrimaryCorrespondent();
        clickFinishButton();
    }

    @Then("the correspondent is added to the case")
    public void assertNewCorrespondentIsAddedToTheCase() {
        peopleTab.assertAddedCorrespondentIsDisplayed();
    }

    @And("I progress the case to save the change of primary correspondent")
    public void iProgressTheCaseToSaveTheChangeOfPrimaryCorrespondent() {
        if (dcuCase()) {
            clickFinishButton();
        }
        if (mpamCase()) {
            clickTheButton("Move to Triage");
        }
        if (mtsCase() | complaintCase() | toCase()) {
            clickContinueButton();
        }
    }

    @And("I confirm the change of primary correspondent")
    public void iConfirmTheChangeOfPrimaryCorrespondent() {
        clickFinishButton();
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

    @Then("an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        correspondents.assertCorrespondentFullNameErrorMessage();
    }

    @Then("an error message should be displayed as I have not selected the correspondent type")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        correspondents.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("an error message should be displayed as I must select a member of parliament from the drop down")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        correspondents.assertMemberIsRequiredErrorMessage();
    }

    @Then("an error message should be displayed as I must select a correspondent type on this screen")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        correspondents.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("the Add a correspondent link is displayed")
    public void linkIsDisplayed() {
        correspondents.assertAddACorrespondentLinkIsVisible();
    }
}
