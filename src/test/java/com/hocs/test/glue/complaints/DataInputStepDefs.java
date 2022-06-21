package com.hocs.test.glue.complaints;

import com.hocs.test.pages.complaints.DataInput;
import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Correspondents;
import com.hocs.test.pages.decs.Documents;
import io.cucumber.java.en.And;

public class DataInputStepDefs extends BasePage {

    Correspondents correspondents;

    DataInput dataInput;

    Documents documents;

    @And("I select {string} as the business area for the POGR case")
    public void iSelectAsTheBusinessAreaForThePOGRCase(String businessArea) {
        dataInput.selectSpecificBusinessArea(businessArea);
        safeClickOn(continueButton);
    }

    @And("I enter details on the Data Input screen")
    public void iCompleteTheDataInputScreen() {
        dataInput.completeDataInputScreen();
    }

    @And("I enter the date that the letter was sent")
    public void iEnterTheDateThatTheLetterWasSent() {
        dataInput.enterDateLetterSent();
        safeClickOn(continueButton);
    }

    @And("I select the investigating team for the case")
    public void iSelectTheInvestigatingTeamForTheCase() {
        dataInput.selectInvestigatingTeam();
        safeClickOn(finishButton);
    }

    @And("I record that the case is a Priority case")
    public void iRecordThatTheCaseIsAPriorityCase() {
        dataInput.selectCategory();
        dataInput.enterDescriptionOfComplaint();
        dataInput.checkPriorityCheckbox();
        dataInput.selectIsLoARequired();
        safeClickOn(continueButton);
    }

    @And("I record that the case was not received by post")
    public void iRecordThatTheCaseWasNotReceivedByPost() {
        dataInput.selectCategory();
        dataInput.enterDescriptionOfComplaint();
        dataInput.selectASpecificComplaintChannel("Email");
        dataInput.selectIsLoARequired();
        safeClickOn(continueButton);
    }

    @And("I record that the case was received by post")
    public void iRecordThatTheCaseWasReceivedByPost() {
        dataInput.selectCategory();
        dataInput.enterDescriptionOfComplaint();
        dataInput.selectASpecificComplaintChannel("Post");
        dataInput.selectIsLoARequired();
        safeClickOn(continueButton);
    }

    @And("I complete the Data Input stage with {string} as the business area")
    public void iCompleteTheDataInputStageWithAsTheBusinessArea(String businessArea) {
        dataInput.selectSpecificBusinessArea(businessArea);
        safeClickOn(continueButton);
        correspondents.addANonMemberCorrespondentOfType("Complainant");
        safeClickOn(continueButton);
        dataInput.completeDataInputScreen();
        safeClickOn(continueButton);
        documents.addADocumentOfDocumentType("Interim Letter");
        dataInput.enterDateLetterSent();
        safeClickOn(continueButton);
        if (businessArea.equalsIgnoreCase("GRO")) {
            dataInput.selectInvestigatingTeam();
            safeClickOn(finishButton);
        }
    }
}