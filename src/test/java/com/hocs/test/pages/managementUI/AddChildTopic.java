package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.base_page.Page;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class AddChildTopic extends Page {

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

    public void selectAParentTopic(String parentTopic) {
        waitABit(1000);
        clickOn(parentTopicSearchBar);
        parentTopicSearchBar.sendKeys(parentTopic);
        setSessionVariable("parentTopic").to(parentTopic);
        parentTopicSearchBar.sendKeys(Keys.ENTER);
    }

    public void inputAChildTopicDisplayName(String childTopic) {
        waitABit(1000);
        clickOn(childTopicDisplayNameInputBar);
        typeInto(childTopicDisplayNameInputBar, childTopic);
        setSessionVariable("childTopic").to(childTopic);
        childTopicDisplayNameInputBar.sendKeys(Keys.ENTER);
    }

    public void inputNewChildTopic() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String childTopic = "CHILD " + formatter.format(LocalDateTime.now());
        setSessionVariable("newChildTopic").to(childTopic);
        inputAChildTopicDisplayName(childTopic);
    }

    public void assertParentTopicIsRequiredErrorMessage() {
        waitForAnyTextToAppear("The Parent Topic is required");
        assertThat(errorMessageLink.getText(), is("The Parent Topic is required"));
    }

    public void assertDisplayNameIsRequiredErrorMessage() {
        waitForAnyTextToAppear("The Display Name is required");
        assertThat(errorMessageLink.getText(), is("The Display Name is required"));
    }

    public void assertDuplicateTopicErrorMessage() {
        waitForAnyTextToAppear("A child topic with that name already exists");
        assertThat(errorMessageContents.getText(), is("A child topic with that name already exists"));
    }
}
