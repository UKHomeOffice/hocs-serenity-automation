package com.hocs.test.pages.draft;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.teamqueue.Teamqueue;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends Page {

    Homepage homepage;
    SuccessfulCaseCreation successfulCaseCreation;
    Teamqueue teamqueue;

    @FindBy(css = "label[for='InitialDraftDecision-Reject']")
    private WebElementFacade answeredByMyTeamNoRadioButton;

    @FindBy(css = "label[for='InitialDraftDecision-ACCEPT']")
    private WebElementFacade answeredByMyTeamYesRadioButton;

    @FindBy(xpath = "//a[text()='document']")
    private WebElementFacade draftStageAddDocumentsButton;

    @FindBy(id = "document_type")
    private WebElementFacade documentTypeDropDown;

    @FindBy(css = ".govuk-heading-l")
    public WebElementFacade draftAResponseHeader;

    @FindBy(id = "")
    private WebElementFacade draftingDeadline;

    @FindBy(id = "")
    private WebElementFacade rejectNoteField;

    @FindBy(id = "")
    private WebElementFacade emailReplyRadioButton;

    @FindBy(id = "")
    private WebElementFacade letterReplyRadioButton;

    @FindBy(id = "")
    private WebElementFacade phoneReplyRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocationNoteFreeTextField;

    @FindBy(id = "")
    private WebElementFacade downloadTemplateLink;

    @FindBy(id = "")
    private WebElementFacade addResponseButton;

    @FindBy(id = "")
    private WebElementFacade supportingDocumentsNoRadioButton;

    @FindBy(id = "")
    private WebElementFacade supportingDocumentsYesRadioButton;

    @FindBy(id = "")
    private WebElementFacade addSupportingDocumentsButton;

    @FindBy(id = "")
    private WebElementFacade standardLine;

    @FindBy(id = "")
    private WebElementFacade offlineQaRadioButton;

    @FindBy(id = "")
    private WebElementFacade onlineQaRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocateToOfflineQaDropdown;

    @FindBy(id = "")
    private WebElementFacade allocateToOnlineQaDropdown;

    // Basic Methods

    public void clickAddDocumentsButton() {
        draftStageAddDocumentsButton.click();
    }

    public void clearAllocationNoteField() {
        allocationNoteFreeTextField.clear();
    }

    public void selectDocumentTypeByIndex(int index) {
        documentTypeDropDown.selectByIndex(index);
    }

    public void clickAddResponseButton() {
        addResponseButton.click();
    }

    public void clickAnsweredByMyTeamNoRadioButton() {
        answeredByMyTeamNoRadioButton.click();
    }

    public void clickAnsweredByMyTeamYesRadioButton() {
        answeredByMyTeamYesRadioButton.click();
    }

    public void clickDownloadTemplateLink() {
        downloadTemplateLink.click();
    }

    public void clickEmailReplyRadioButton() {
        emailReplyRadioButton.click();
    }

    public void clickLetterReplyRadioButton() {
        letterReplyRadioButton.click();
    }

    public void clickNoSupportingDocumentsRadioButton() {
        supportingDocumentsNoRadioButton.click();
    }

    public void clickOfflineQaButton() {
        offlineQaRadioButton.click();
    }

    public void clickOnlineQaButton() {
        onlineQaRadioButton.click();
    }

    public void clickPhoneReplyRadioButton() {
        phoneReplyRadioButton.click();
    }

    public void clickStandardLine() {
        standardLine.click();
    }

    public void clickYesSupportingDocumentsRAdioButton() {
        supportingDocumentsYesRadioButton.click();
    }

    public Draft() {
    }

    public void enterAllocationNoteField() {
        clearAllocationNoteField();
        allocationNoteFreeTextField.sendKeys(generateRandomString());
    }

    public String getDraftingDeadline() {
        return draftingDeadline.getText();
    }

    public void selectOfflineQualityAssurer() {
        allocateToOfflineQaDropdown.selectByVisibleText("");
    }

    public void selectOnlineQualityAssurer() {
        allocateToOnlineQaDropdown.selectByVisibleText("");
    }

    // Multi Step Methods

    // Assertions

    public void assertCaseIsAtDraft(){
        String thisCaseType =
                Serenity.sessionVariableCalled("caseType").toString();
        if(thisCaseType.equals("DCU MIN")) {
            homepage.selectTeam1();
            homepage.assertInitialDraftInWorkStacks();
            //successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
            //homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com)");
            //homepage.goHome();
            //homepage.selectMyCases();
            //successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
            //assertCaption("Initial Draft");
        } else if (thisCaseType.equals("DCU TRO")) {
            homepage.selectTeam3();
            successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
            homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com");
        } else if (thisCaseType.equals("DCU N10")) {
            homepage.selectTeam3();
            successfulCaseCreation.selectCaseReferenceNumberViaLinkText();
            homepage.selectAllocationUserByVisibleText("Danny Large (danny.large@ten10.com");
        } else {
            System.out.println("The caseType is " + thisCaseType);
        }
    }

    public void assertEnterCallNotesError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterQaMethodError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterRejectionReasonsError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void draftingDeadlineIsDisplayed() {
        assertThat(isElementDisplayed(draftingDeadline), is(true));
    }

    public void assertDraftAResponseHeader() {
        String caseReferenceNumber
                = Serenity.sessionVariableCalled("caseReference").toString();
        assertTitle(caseReferenceNumber + "Draft a Response");
    }
}
