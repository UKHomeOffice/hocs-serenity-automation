package com.hocs.test.pages.decs;

import java.time.Duration;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.xpath.operations.Bool;
import org.junit.Assert;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PeopleTab extends BasePage {

    Correspondents correspondents;

    public void selectPeopleTab() {
        selectTheTab("People");
    }

    public void refreshPeopleTab() {
        refreshTheTab("People");
    }

    public void selectToManagePeople() {
        clickTheLink("Manage People");
    }

    public String getPeopleTabValueForGivenCorrespondentForGivenHeader(String correspondentName, String header) {
        selectPeopleTab();
        WebElementFacade displayedValueElement = findBy("//td[text()='" + correspondentName + "']//ancestor::tbody//th[text()='" +header + "']/following"
                + "-sibling::td");
        return displayedValueElement.withTimeoutOf(Duration.ofSeconds(10)).getText();
    }

    public void addAMemberCorrespondent() {
        selectToManagePeople();
        correspondents.addAMemberCorrespondent();
    }

    public void addAPublicCorrespondent() {
        selectToManagePeople();
        correspondents.addANonMemberCorrespondentOfType("Constituent");
    }

    public void editCorrespondent(String detail, String correspondent) {
        selectToManagePeople();
        WebElementFacade editHypertext = findBy("//label[contains(text(), '" + correspondent + "')]/ancestor::tr//a[text()='Edit']").withTimeoutOf(Duration.ofSeconds(10));
        safeClickOn(editHypertext);
        switch (detail.toUpperCase()) {
            case "FULL NAME":
                correspondents.enterCorrespondentFullName("Test - correspondent name");
                break;
            case "BUILDING":
                correspondents.enterCorrespondentAddressLine1("Test - correspondent building");
                break;
            case "STREET":
                correspondents.enterCorrespondentAddressLine2("Test - correspondent street");
                break;
            case "TOWN OR CITY":
                correspondents.enterCorrespondentTownOrCity("Test - correspondent town/city");
                break;
            case "POSTCODE":
                correspondents.enterCorrespondentPostcode("Test - correspondent postcode");
                break;
            case "TELEPHONE":
                correspondents.enterCorrespondentTelephoneNumber("Test - correspondent telephone");
                break;
            case "EMAIL ADDRESS":
                correspondents.enterCorrespondentEmailAddress("Test - correspondent email address");
                break;
            default:
                pendingStep(detail + " is not defined within " + getMethodName());
        }
        clickTheButton("Save");
    }

    public void selectDifferentPrimaryCorrespondent() {
            String newPrimaryCorrespondent = selectDifferentRadioButtonFromGroupWithHeading("Person we will write back to");
            setSessionVariable("primaryCorrespondent").to(newPrimaryCorrespondent);
    }

    public void assertAddedCorrespondentIsDisplayed() {
        selectPeopleTab();
        List<WebElementFacade> correspondentNames = findAll("//th[text()='Name']/following-sibling::td");
        int listSize = correspondentNames.size();
        boolean isCorrespondentDisplayed = false;
        int n = 0;
        while (n <= (listSize - 1) && !isCorrespondentDisplayed) {
            if (correspondentNames.get(n).getText().contains(sessionVariableCalled("correspondentFullName"))) {
                isCorrespondentDisplayed = true;
            }
            n++;
        }
        assertThat(isCorrespondentDisplayed, is(true));
    }

    public void assertCorrespondentHasBeenRemoved(String correspondent) {
        selectPeopleTab();
        List<WebElementFacade> correspondentNames = findAll("//th[text()='Name']/following-sibling::td");
        int n = 0;
        while (n < (correspondentNames.size())) {
            assertThat(correspondentNames.get(n).getText().contains(correspondent), is(false));
            n++;
        }
    }

    public void assertCorrespondentIsAttachedToCase(String correspondentName) {
        boolean correspondentPresent = false;
        List<WebElementFacade> correspondentsNameElements = findAll("//th[text()='Name']/following-sibling::td");
        for(WebElementFacade correspondentNameElement: correspondentsNameElements) {
            if(correspondentNameElement.getText().toUpperCase().contains(correspondentName.toUpperCase())){
                correspondentPresent = true;
            }
        }
        assertThat(correspondentPresent, is(true));
    }

    public void assertMPCorrespondentIsAddedToTheCase(String mpName) {
        boolean mpPresent = false;
        int n = 0;
        List<WebElementFacade> listOfMemberCorrespondents = findAll("//h2[contains(text(), 'Member')]/following-sibling::table//th[text()"
                + "='Name']/following-sibling::td");
        while (n <= listOfMemberCorrespondents.size()) {
            if (listOfMemberCorrespondents.get(n).getText().toUpperCase().contains(mpName.toUpperCase())) {
                mpPresent = true;
                break;
            }
            n++;
        }
        assertThat(mpPresent, is(true));
    }

    public void assertPublicCorrespondentAddedToTheCase(String correspondentName) {
        boolean correspondentPresent = false;
        int n = 0;
        List<WebElementFacade> listOfPublicCorrespondents = findAll("//h2[not(contains(text(), 'Member'))]/following-sibling::table//th[text()"
                + "='Name']/following-sibling::td");
        while (n < listOfPublicCorrespondents.size()) {
            if (listOfPublicCorrespondents.get(n).getText().toUpperCase().contains(correspondentName.toUpperCase())) {
                correspondentPresent = true;
                break;
            }
            n++;
        }
        assertThat(correspondentPresent, is(true));
    }

    public void assertValueIsPresentInPeopleTabForGivenHeader(String value, String header) {
        List<WebElementFacade> correspondentNameElements = getAllVisibleCorrespondentNameElements();
        boolean valueIsPresent = false;
        for (WebElementFacade correspondentNameElement : correspondentNameElements) {
            valueIsPresent = getPeopleTabValueForGivenCorrespondentForGivenHeader(correspondentNameElement.getText(), header).contains(value);
            if (valueIsPresent) {
                break;
            }
        }
        if (!valueIsPresent) {
            Assert.fail("No correspondents visible in the People tab have '" + value + "' in/as their '" + header +"' value");
        }
    }

    private List<WebElementFacade> getAllVisibleCorrespondentNameElements() {
         return findAll("//th[text()='Name']/following-sibling::td");
    }
}
