package com.hocs.test.pages.wcs;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Triage extends BasePage {

    @FindBy(xpath = "//a[@href='#CaseworkTeamUUID-error']")
    public WebElementFacade selectACaseworkingTeamIsRequiredErrorMessage;

    public void selectACaseworkingTeam() {
        String selectedCaseworkTeam = selectRandomOptionFromDropdownWithHeading("Select a Caseworking team");
        clickContinueButton();
        setSessionVariable("selectedCaseworkTeam").to(selectedCaseworkTeam);
    }

    public void assertSelectACaseworkingTeamIsRequiredErrorMessage(){
        selectACaseworkingTeamIsRequiredErrorMessage.shouldContainText("Select a Caseworking team is required");
    }
}
