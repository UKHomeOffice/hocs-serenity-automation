package com.hocs.test.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UnallocatedCaseView extends BasePage {

    @FindBy(linkText = "Allocate to me")
    public WebElementFacade allocateToMeLink;

    @FindBy(xpath = "//h2[@class='govuk-heading-m'][text()='Case Details']")
    public WebElementFacade caseDetailsHeader;

    @FindBy(css = "[value = 'Allocate']")
    public WebElementFacade allocateButton;

    @FindBy(id = "user-id")
    public WebElementFacade allocateDropdown;

    // Basic methods

    public void clickAllocateToMeLink() {
        safeClickOn(allocateToMeLink);
    }

    public boolean checkAllocateToMeLinkVisible() {
        return allocateToMeLink.isVisible();
    }

    public boolean checkCaseDetailsHeaderVisible() { return caseDetailsHeader.withTimeoutOf(Duration.ofSeconds(60)).isVisible(); }

    //assertions

    public void assertCaseCannotBeAssigned() {
        assertThat(checkCaseDetailsHeaderVisible(), is(true));
        assertThat(checkAllocateToMeLinkVisible(), is(false));
    }

    public void allocateToUserByVisibleText(String allocationUser) {
        safeClickOn(allocateDropdown);
        allocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(allocateButton);
    }
}
