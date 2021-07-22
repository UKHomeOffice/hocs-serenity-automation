package com.hocs.test.pages.decs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class UnallocatedCaseView extends BasePage {

    @FindBy(linkText = "Allocate to me")
    public WebElementFacade allocateToMeLink;

    @FindBy(css = "[value = 'Allocate']")
    public WebElementFacade allocateButton;

    @FindBy(id = "user-id")
    public WebElementFacade allocateDropdown;

    // Basic methods

    public void clickAllocateToMeLink() {
        safeClickOn(allocateToMeLink);
    }

    public boolean caseCanBeAllocated() {
        return allocateToMeLink.isVisible();
    }

    public String getValueFromOpenCaseDetailsAccordionSectionForGivenHeading(String heading) {
        List<WebElementFacade> valuesForMatchingHeadings = findAll("//Strong[contains(text(),'" + heading + "')]/parent::span");
        WebElementFacade visibleValue = getOnlyCurrentlyVisibleElementFromList(valuesForMatchingHeadings);
        return visibleValue.getText();
    }

    //assertions

    public void assertCaseCannotBeAssigned() {
        assertThat(caseCanBeAllocated(), is(false));
    }

    public void allocateToUserByVisibleText(String allocationUser) {
        safeClickOn(allocateDropdown);
        allocateDropdown.selectByVisibleText(allocationUser);
        safeClickOn(allocateButton);
    }
}
