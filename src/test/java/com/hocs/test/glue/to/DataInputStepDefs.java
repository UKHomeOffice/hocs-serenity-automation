package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

public class DataInputStepDefs extends BasePage {

    DataInput dataInput;

    @When("I select which business area the correspondence is for")
    public void iSelectWhichBusinessAreaTheCorrespondenceIsFor() {
        dataInput.selectABusinessArea();
    }

    @And("I select which channel the correspondence was received by")
    public void iSelectWhichChannelTheCorrespondenceWasReceivedBy() {
        dataInput.selectAChannelRecieved();
    }

    @And("I choose to not add a recipient")
    public void iChooseToNotAddARecipient() {
        dataInput.selectWhetherToAddRecipient("No");
        clickTheButton("Continue");
    }

    @When("I add a Recipient to the case")
    public void iAddARecipientTypeRecipient() {
        dataInput.selectWhetherToAddRecipient("Yes");
        dataInput.selectARecipient();
        clickTheButton("Continue");
    }

    @And("I select whether the Home Secretary has an interest in the case")
    public void iSelectWhetherTheHomeSecretaryHasAnInterestInTheCase() {
        dataInput.selectAHomeSecInterestOption();
        clickTheButton("Continue");
    }

    @And("I add the newly created recipient to the case")
    public void iAddTheNewlyCreatedRecipientToTheCase() {
        String recipient = sessionVariableCalled("newRecipientName");
        dataInput.selectWhetherToAddRecipient("Yes");
        dataInput.selectSpecificRecipient(recipient);
        clickTheButton("Continue");
    }
}
