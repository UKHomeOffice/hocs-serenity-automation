package com.hocs.test.pages.create_case;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class CaseDecision {

    @FindBy(id = "")
    private WebElementFacade policyResponseRadioButton;

    @FindBy(id = "")
    private WebElementFacade referToOgdRadioButton;

    @FindBy(id = "")
    private WebElementFacade FAQRadioButton;

    @FindBy(id = "")
    private WebElementFacade noReplyNeededRadioButton;

    @FindBy(id = "")
    private WebElementFacade allocationNoteFreeTextField;

    @FindBy(id = "")
    private WebElementFacade dateReceivedField;

    @FindBy(id = "")
    private WebElementFacade draftingDeadlineField;

    @FindBy(id = "")
    private WebElementFacade finalDeadlineField;

    @FindBy(id = "")
    private WebElementFacade addATopicButton;

}
