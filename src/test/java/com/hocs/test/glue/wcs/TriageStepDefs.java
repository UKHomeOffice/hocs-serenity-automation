package com.hocs.test.glue.wcs;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.Dashboard;
import com.hocs.test.pages.decs.Workstacks;
import com.hocs.test.pages.wcs.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TriageStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Triage triage;

    @And("I select a casework team")
    public void selectCaseworkTeamFromDropdown() {
        triage.selectACaseworkingTeam();
    }

    @Then("an error message is displayed as I have not selected a caseworking team")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedACaseworkingTeam() {
        triage.assertSelectACaseworkingTeamIsRequiredErrorMessage();
    }
}

