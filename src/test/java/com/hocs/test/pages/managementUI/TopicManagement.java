package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.decs.BasePage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class TopicManagement extends BasePage {

    public void assertAddChildTopicPageTitle() {
        assertThat(managementUIPageTitle.getText(), is("Add Child Topic"));
    }

    @FindBy(xpath = "//input[@id='parent-topics-input']")
    public WebElementFacade parentTopicSearchBar;

    @FindBy(css = "ul.govuk-error-summary__list a")
    public WebElementFacade errorMessageLink;

    @FindBy(css = ".govuk-error-summary__body")
    public WebElementFacade errorMessageContents;

    @FindBy(xpath = "//input[@id='displayName']")
    public WebElementFacade childTopicDisplayNameInputBar;

    @FindBy(id = "displayName")
    public WebElementFacade topicNameTextField;

    public void selectAParentTopic(String parentTopic) {
        selectSpecificOptionFromTypeaheadWithHeading(parentTopic,"Select The Parent Topic");
//        waitABit(1000);
//        safeClickOn(parentTopicSearchBar);
//        parentTopicSearchBar.sendKeys(parentTopic);
//        setSessionVariable("parentTopic").to(parentTopic);
//        parentTopicSearchBar.sendKeys(Keys.ENTER);
        setSessionVariable("parentTopic").to(parentTopic);
    }

    public void inputAParentTopicDisplayedName() {
        String parentTopicName = "Parent Topic - " + generateRandomString();
        topicNameTextField.sendKeys(parentTopicName);
        setSessionVariable("newParentTopic").to(parentTopicName);
    }

    public void inputAChildTopicDisplayName(String childTopic) {
        waitABit(1000);
        safeClickOn(childTopicDisplayNameInputBar);
        childTopicDisplayNameInputBar.sendKeys(childTopic);
        setSessionVariable("childTopic").to(childTopic);
        childTopicDisplayNameInputBar.sendKeys(Keys.ENTER);
    }

    public void inputNewChildTopic() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String childTopic = "CHILD " + formatter.format(LocalDateTime.now());
        setSessionVariable("newChildTopic").to(childTopic);
        inputAChildTopicDisplayName(childTopic);
        waitABit(1000);
    }

    public void assertParentTopicIsRequiredErrorMessage() {
        waitForAnyTextToAppear("The Parent Topic is required");
        errorMessageLink.shouldContainText("The Parent Topic is required");
    }

    public void assertDisplayNameIsRequiredErrorMessage() {
        waitForAnyTextToAppear("The Display Name is required");
        errorMessageLink.shouldContainText("The Display Name is required");
    }

    public void assertDuplicateTopicErrorMessage() {
        waitForAnyTextToAppear("A child topic with that name already exists");
        errorMessageContents.shouldContainText("A child topic with that name already exists");
    }
}
