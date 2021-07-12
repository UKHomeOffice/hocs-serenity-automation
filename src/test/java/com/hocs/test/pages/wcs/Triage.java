package com.hocs.test.pages.wcs;

import static net.serenitybdd.core.Serenity.setSessionVariable;

import com.hocs.test.pages.platform.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Triage extends BasePage {

    @FindBy(xpath = "//select[@id='CaseworkTeamUUID']")
    public WebElementFacade selectCaseworkingTeamDropdown;

    @FindBy(xpath = "//a[@href='#CaseworkTeamUUID-error']")
    public WebElementFacade selectACaseworkingTeamIsRequiredErrorMessage;

    public void selectCaseworkingTeam(String teamName) {
        selectCaseworkingTeamDropdown.selectByVisibleText(teamName);
        clickOn(continueButton);
        setSessionVariable("selectedCaseworkTeam").to(teamName);
    }

    public void assertSelectACaseworkingTeamIsRequiredErrorMessage(){
        selectACaseworkingTeamIsRequiredErrorMessage.shouldContainText("Select a Caseworking team is required");
    }
}
