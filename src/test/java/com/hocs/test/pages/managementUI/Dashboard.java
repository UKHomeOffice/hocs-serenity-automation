package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.Page;
import java.util.WeakHashMap;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Dashboard extends Page {

    @FindBy (xpath = "//a[@href='/team-search']")
    public WebElementFacade addRemoveUsersButton;

    @FindBy(xpath = "//a[@href='/add-unit']")
    public WebElementFacade addUnitButton;



}
