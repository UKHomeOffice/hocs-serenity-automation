package com.hocs.test.pages;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import config.User;
import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.hamcrest.core.Is;

public class SummaryTab extends BasePage {

    @FindBy(xpath = "//a[text()='Summary']")
    public WebElementFacade summaryTab;

    @FindBy(xpath = "//th[text()='Deadline']/following-sibling::td")
    private WebElementFacade deadline;

    @FindBy(xpath = "//th[text()='Primary topic']/following-sibling::td")
    private WebElementFacade primaryTopic;

    @FindBy(xpath = "//th[text()='Primary correspondent']/following-sibling::td")
    private WebElementFacade primaryCorrespondent;

    @FindBy(xpath = "//th[text()='How was the correspondence received?']/following-sibling::td")
    private WebElementFacade howCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='Should the response be copied to Number 10?']/following-sibling::td")
    private WebElementFacade shouldResponseCopyToN10;

    @FindBy(xpath = "//th[text()='When was the correspondence received?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='When was the correspondence sent?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceSent;

    @FindBy(xpath = "//h2[text()='Active stage']/following-sibling::table[1]/caption")
    private WebElementFacade activeStage;

    @FindBy(xpath = "//th[text()='Team']/following-sibling::td")
    private WebElementFacade currentTeam;

    @FindBy(xpath = "//th[text()='User']/following-sibling::td")
    private WebElementFacade allocatedUser;

    public void selectSummaryTab() {
        safeClickOn(summaryTab);
    }

    public String getPrimaryCorrespondent() {
        return primaryCorrespondent.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().getText();
    }

    public String getActiveStage() {
        return activeStage.getText();
    }

    public void assertCaseStage(String stage) {
        assertThat(getActiveStage().toUpperCase(), is(stage.toUpperCase()));
    }

    public void recordCaseOwner() {
        safeClickOn(summaryTab);
        WebElementFacade allocatedUser = findBy("//th[text()='User']/following-sibling::td");
        String user = allocatedUser.getText();
        setSessionVariable("allocatedUser").to(user);
    }

    public void assertCaseOwnerIs(User inputOwner) {
        safeClickOn(summaryTab);
        String owner = inputOwner.getUsername();
        String actualOwner = sessionVariableCalled("allocatedUser");
        assertThat(owner.equals(actualOwner), Is.is(true));
    }

    public void assertAllocatedUserIs(User user) {
        allocatedUser.shouldContainText(user.getUsername());
    }
}