package com.hocs.test.pages.managementUI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.When;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TeamManagement extends Page {

    @FindBy(xpath = "//input[@id='react-select-2-input']")
    public WebElementFacade teamSearchBar;

    @FindBy(xpath = "//button[@class='govuk-button view-team-button']")
    public WebElementFacade viewTeamButton;

    public void assertTeamManagementPageTitle() {
        assertThat($("//h1").getText(), is("Team search"));
    }
}
