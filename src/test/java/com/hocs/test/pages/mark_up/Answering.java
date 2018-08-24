package com.hocs.test.pages.mark_up;

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

}
