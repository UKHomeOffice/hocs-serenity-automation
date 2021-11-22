package com.hocs.test.pages.managementUI;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.decs.BasePage;
import java.io.File;
import java.time.Duration;
import java.util.List;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class TemplateManagement extends BasePage {

    @org.openqa.selenium.support.FindBy(xpath = "//input[@id='case-types-input']")
    public WebElementFacade caseTypeTypeahead;

    @FindBy(xpath = "//button[text()='Add template']")
    public WebElementFacade addTemplateButton;

    @FindBy(id = "files")
    public WebElementFacade muiAddDocument;

    public void selectACaseType(String caseType) {
        waitFor(caseTypeTypeahead);
        caseTypeTypeahead.sendKeys(caseType);
        caseTypeTypeahead.sendKeys(Keys.RETURN);
        clickTheButton("Submit");
    }

    public void uploadDocumentOfType(String type) {
        setSessionVariable("docType").to(type);
        muiAddDocument.withTimeoutOf(Duration.ofSeconds(10)).waitUntilPresent();
        upload(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +  File.separator + "resources" +  File.separator +
                "documents" +  File.separator + "test."  + type).to(muiAddDocument);
    }

    public void addTemplate() {
        safeClickOn(addTemplateButton);
        uploadDocumentOfType("docx");
        clickTheButton("Submit");
    }

    public void removeTemplate() {
        List<WebElementFacade> listOfRemoveButtons = findAll("//a[text()='Remove']");
        setSessionVariable("initialNumberOfTemplates").to(listOfRemoveButtons.size());
        clickTheLink("Remove");
        waitABit(500);
    }

    public void assertTemplateRemoval() {
        int initialNumberOfTemplates = sessionVariableCalled("initialNumberOfTemplates");
        List<WebElementFacade> listOfRemoveButtons = findAll("//a[text()='Remove']");
        int finalNumberOfTemplates = listOfRemoveButtons.size();
        assertThat(initialNumberOfTemplates > finalNumberOfTemplates, is(true));
    }
}
