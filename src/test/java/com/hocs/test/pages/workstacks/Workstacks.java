package com.hocs.test.pages.workstacks;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import com.hocs.test.pages.Page;
import com.hocs.test.pages.homepage.Homepage;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import static org.hamcrest.CoreMatchers.is;

public class Workstacks extends Page {


    Homepage homepage;

    @FindBy(linkText = "Allocate to me")
    public WebElementFacade allocateToMeButton;

    public static void assertTeamHasNoCases() {
    }

    public static void assignCaseToTeamMember() {
    }

    public static void assignMultipleTeamSpecificCasesToTeammate() {
    }

    public void assignCaseToMyself() {
        homepage.firstStageFindMyCase();
    }

    public void clickAllocateToMeButton(){
        allocateToMeButton.click();
    }

    public void assertCaseReferenceIsVisible() {
        String caseReferenceNumber
                = Serenity.sessionVariableCalled("caseReference").toString();
        System.out.println(caseReferenceNumber);
        WebElement thisReference = getDriver().findElement(By.linkText(caseReferenceNumber));
        System.out.println(thisReference);
        element(thisReference).isDisplayed();
       // assertThat(isElementDisplayed(thisReference), is(true));

    }


    public static void assignTeamSpecificCasesToMe() {
    }

    public static void assertThatCaselessTeamNotVisible() {
    }

    public static void assertThatCasesAssignedToMe() {
    }

    public static void assertThatXCasesAreUnassigned() {
    }

    public static void assertThatXCasesAreAssignedToMe() {
    }

    public static void assertThatTeamHasXTotalCases() {
    }

    public static void assertThatTeamHasXUnassignedCases() {
    }

    public static void assertOverdueCases() {
    }

    public static void assertThatMyCaseIsPriority() {
    }

    public static void assertUnassignedCaseIsPriority() {
    }
}
