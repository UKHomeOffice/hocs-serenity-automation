package com.hocs.test.pages.foi;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

public class Allocation extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//h2[@class='govuk-heading-m']")
    public WebElementFacade allocationText;

    public void selectAGroup() {
        String selectedGroup = recordCaseData.selectRandomOptionFromDropdownWithHeading("Group");
        setSessionVariable("foiGroup").to(selectedGroup);
    }

    public void selectAnAccountManager() { recordCaseData.selectRandomOptionFromDropdownWithHeading("Account Manager"); }

    public void assertAllocationText() {
        waitForDECSPageWithTitle("FOI Allocation");
        String displayedAllocationText = allocationText.getText();
        String foiGroup = sessionVariableCalled("foiGroup");
        String expectedAllocationText = "Case " + getCurrentCaseReference() +" will be allocated to "+ foiGroup;
        if (!displayedAllocationText.equals(expectedAllocationText)) {
            Assert.fail("Expected allocation text: " + expectedAllocationText +"\nDisplayed allocation text: " + displayedAllocationText);
        }
    }
}