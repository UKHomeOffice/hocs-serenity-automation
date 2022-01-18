package com.hocs.test.pages.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.decs.RecordCaseData;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class DataInput extends BasePage {

    RecordCaseData recordCaseData;

    @FindBy(xpath = "//label[text()='Recipient (Member of Parliament)']/following-sibling::div//input")
    private WebElementFacade recipientTypeahead;

    @FindBy(xpath = "//div[contains(@class,'govuk-typeahead__single-value')]")
    public WebElementFacade selectedMemberRecipient;

    public void selectABusinessArea() {
        String selectedBusinessArea = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Business Area");
        setSessionVariable("businessArea").to(selectedBusinessArea);
    }

    public void selectAChannelRecieved() {
        String selectedChannelRecieved = recordCaseData.selectRandomRadioButtonFromGroupWithHeading("Channel Received");
        setSessionVariable("channelReceived").to(selectedChannelRecieved);
    }

    public void selectWhetherToAddRecipient(String yesNo) {
        selectSpecificRadioButtonFromGroupWithHeading(yesNo, "Do you wish to add a Recipient?");
    }

    public void selectIfRecipientIsMP(String yesNo) {
        recordCaseData.selectSpecificRadioButtonFromGroupWithHeading(yesNo, "Is the recipient an MP?");
    }

    public void selectAMemberRecipient() {
        safeClickOn(recipientTypeahead);
        waitABit(200);
        boolean selectableMemberVisible = false;
        List<WebElementFacade> memberOptions = null;
        while (!selectableMemberVisible) {
            recipientTypeahead.clear();
            recipientTypeahead.sendKeys(generateRandomStringOfLength(1));
            waitABit(1000);
            memberOptions = findAll("//div[contains(@class,'option')]");
            selectableMemberVisible = memberOptions.size() > 1;
        }
        Random random = new Random();
        safeClickOn(memberOptions.get(random.nextInt(memberOptions.size())));
        setSessionVariable("recipient").to(selectedMemberRecipient.getText());
        recordCaseData.addHeadingAndValueRecord("Recipient (Member of Parliament)", selectedMemberRecipient.getText());
    }

    public void selectANonMemberRecipient() {
        String recipient = recordCaseData.selectRandomOptionFromDropdownWithHeading("Recipient");
        setSessionVariable("recipient").to(recipient);
    }
}
