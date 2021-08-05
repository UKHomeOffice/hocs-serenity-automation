package com.hocs.test.pages.foi;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class FOIApproval extends BasePage {

    @FindBy(xpath = "//a[text()='Add an Approval Request']")
    public WebElementFacade addAnApprovalRequestHypertext;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElementFacade editHypertext;

}
