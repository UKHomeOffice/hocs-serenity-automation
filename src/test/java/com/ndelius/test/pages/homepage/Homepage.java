package com.ndelius.test.pages.homepage;

import com.ndelius.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Homepage extends Page {

    @FindBy(linkText = "View test form")
    private WebElementFacade testFormLink;

    public void clickTestFormLink() {
        testFormLink.click();
    }

}
