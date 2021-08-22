package com.hocs.test.pages.foi;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Allocation extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//th[text()='Request Question']/following-sibling::td")
    public WebElementFacade requestQuestion;

    @FindBy(xpath = "//h2[@class='govuk-heading-m']")
    public WebElementFacade allocationText;

    public void selectDirectorate(String directorate) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(directorate,"Directorate");
    }

    public void selectAcceptanceTeam(String team) {
        recordCaseData.selectSpecificOptionFromDropdownWithHeading(team,"Acceptance Team");
    }

    public void assertRequestQuestionIsCorrect() {
        String displayedRequestQuestion = requestQuestion.getText();
        String enteredRequestQuestion = sessionVariableCalled("requestQuestion");
        assertThat(displayedRequestQuestion.equals(enteredRequestQuestion), is(true));
    }

    public void assertAllocationText() {
        waitABit(3000);
        String displayedAllocationText = allocationText.getText();
        String acceptanceTeam = sessionVariableCalled("acceptanceTeam");
        String expectedAllocationText = "Case " + getCurrentCaseReference() +" will be allocated to "+ acceptanceTeam;
        assertThat(displayedAllocationText.equals(expectedAllocationText), is(true));
    }
}