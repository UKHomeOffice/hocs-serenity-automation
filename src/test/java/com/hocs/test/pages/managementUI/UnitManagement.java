package com.hocs.test.pages.managementUI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UnitManagement extends Page {

    @FindBy(xpath = "//input[@id='displayName']")
    public WebElementFacade displayNameTextField;

    @FindBy(xpath = "//input[@id='shortCode']")
    public WebElementFacade shortCodeNameTextField;

    public void assertUnitManagementPageTitle() {
        assertThat($("//h1").getText(), is("Add Unit"));
    }

}
