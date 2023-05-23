package com.hocs.test.pages.wcs;

import com.hocs.test.pages.decs.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class OutOfContactTab extends BasePage {

    @FindBy(xpath = "//a[text()='Out of Contact']")
    public WebElementFacade outOfContactTab;

    public void selectOutOfContactTab() {
        safeClickOn(outOfContactTab);
    }

    public void selectPreOffer(){
        selectSpecificRadioButton("Before an offer was made");
        clickTheButton("Submit");
    }

    public void selectPostOffer(){
        selectSpecificRadioButton("After an offer was made");
        clickTheButton("Submit");
    }



}
