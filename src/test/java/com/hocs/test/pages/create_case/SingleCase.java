package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SingleCase extends Page {

    @FindBy(id = "case-type-MIN")
    private WebElementFacade dcuMinRadioButton;

    @FindBy(id = "case-type-TRO")
    private WebElementFacade dcuTroRadioButton;

    @FindBy(id = "case-type-DTEN")
    private WebElementFacade dcuDtenRadioButton;

    public void clickDcuMinRadioButton() {
        dcuMinRadioButton.click();
    }

    public void clickDcuTroRadioButton() {
        dcuTroRadioButton.click();
    }

    public void clickDcuDtenRadioButton() {
        dcuDtenRadioButton.click();
    }


    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Create Case");
    }


}
