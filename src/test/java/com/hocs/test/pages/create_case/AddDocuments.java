package com.hocs.test.pages.create_case;

import com.hocs.test.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class AddDocuments extends Page {

    @FindBy(id = "add_document")
    private WebElementFacade addDocument;

    public void pageTitleIsDisplayed() {
        waitFor(pageTitle);
        pageTitle.containsText("Add documents");
    }

    public void uploadDocument() {
        upload("documents/test.docx").to(addDocument);
    }

}
