package com.hocs.test.pages.homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class Homepage extends Page {

    @Managed
    WebDriver driver;

    @FindBy(linkText = "Create single case")
    private WebElementFacade createSingleCase;

    @FindBy(linkText = "View test form")
    private WebElementFacade testFormLink;

    public void pageTitleIsDisplayed() throws InterruptedException {
        waitFor(pageTitle);
        assertThat(pageTitle, is("Main"));
    }

    public void clickCreateSingleCase() {
        createSingleCase.click();
    }

    public void clickTestFormLink() {
        testFormLink.click();
    }

}
