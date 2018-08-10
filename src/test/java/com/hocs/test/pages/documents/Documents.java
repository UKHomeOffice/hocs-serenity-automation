package com.hocs.test.pages.documents;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class Documents extends Page {

    @Managed
    WebDriver driver;

    @FindBy(id = "")
    private WebElementFacade previewDocument;

    @FindBy(id = "")
    private WebElementFacade downloadDocument;

    @FindBy(id = "")
    private WebElementFacade viewOriginalDocument;

    @FindBy(id = "")
    private WebElementFacade viewResponse;

    public void clickDownloadDocument() {
        downloadDocument.click();
    }

    public void clickPreviewDocument() {
        previewDocument.click();
    }
}
