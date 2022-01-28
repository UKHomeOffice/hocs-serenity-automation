package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    @When("I select which business area the correspondence is for")
    public void iSelectWhichBusinessAreaTheCorrespondenceIsFor() {
        dataInput.selectABusinessArea();
    }

    @And("I select which channel the correspondence was received by")
    public void iSelectWhichChannelTheCorrespondenceWasReceivedBy() {
        dataInput.selectAChannelRecieved();
        clickTheButton("Continue");
    }

    @And("I choose to not add a recipient")
    public void iChooseToNotAddARecipient() {
        dataInput.selectWhetherToAddRecipient("No");
        clickTheButton("Continue");
    }

    @When("I add a {string} recipient")
    public void iAddARecipientTypeRecipient(String recipientType) {
        dataInput.selectWhetherToAddRecipient("Yes");
        clickTheButton("Continue");
        if (recipientType.equalsIgnoreCase("MEMBER")) {
            dataInput.selectIfRecipientIsMember("Yes");
            dataInput.selectAMemberRecipient();
        } else {
            dataInput.selectIfRecipientIsMember("No");
            dataInput.selectANonMemberRecipient();
        }
        clickTheButton("Continue");
    }
}
