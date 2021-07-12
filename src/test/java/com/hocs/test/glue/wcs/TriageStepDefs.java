package com.hocs.test.glue.wcs;

import com.hocs.test.pages.platform.BasePage;
import com.hocs.test.pages.platform.Dashboard;
import com.hocs.test.pages.platform.Workstacks;
import com.hocs.test.pages.wcs.Triage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TriageStepDefs extends BasePage {

    Dashboard dashboard;

    Workstacks workstacks;

    Triage triage;

    @And("I select {string} as the casework team")
    public void selectCaseworkTeamFromDropdown(String teamName) {
        triage.selectCaseworkingTeam(teamName);
    }

    @Then("an error message is displayed as I have not selected a caseworking team")
    public void anErrorMessageIsDisplayedAsIHaveNotSelectedACaseworkingTeam() {
        triage.assertSelectACaseworkingTeamIsRequiredErrorMessage();
    }
}

