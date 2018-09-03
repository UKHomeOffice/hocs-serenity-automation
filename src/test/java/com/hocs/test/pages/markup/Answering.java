package com.hocs.test.pages.markup;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Answering extends Page {

    @FindBy(id = "MarkupUnit")
    private WebElementFacade markupUnitDropdown;

    @FindBy(id = "MarkupTeam")
    private WebElementFacade markupTeamDropdown;

    @FindBy(id = "SignOffMinister")
    private WebElementFacade signOffMinisterDropdown;

    public void selectMarkupUnitByName(String markupUnit) {
        markupUnitDropdown.selectByVisibleText(markupUnit);
    }

    public void selectMarkupUnitByIndex(int index) {
        markupUnitDropdown.selectByIndex(index);
    }

    public void selectMarkupTeamByName(String markupTeam) {
        markupTeamDropdown.selectByVisibleText(markupTeam);
    }

    public void selectMarkupTeamByIndex(int index) {
        markupTeamDropdown.selectByIndex(index);
    }

    public void selectSignOffMinisterByName(String minister) {
        signOffMinisterDropdown.selectByVisibleText(minister);
    }

    public void selectSignOffMinisterByIndex(int index) {
        signOffMinisterDropdown.selectByIndex(index);
    }

}
