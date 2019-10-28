package com.hocs.test.pages.managementUI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class ChildTopic extends Page {

    @FindBy(xpath = "//div[@class='govuk-typeahead__control css-0']")
    public WebElementFacade selectParentTopicDropdown;

    @FindBy(xpath = "//input[@id='displayName']")
    public WebElementFacade displayNameTextField;

    public void assertChildTopicPageTitle() {
        assertThat(managementUIPageTitle.getText(), is("Add Child Topic"));
    }

}
