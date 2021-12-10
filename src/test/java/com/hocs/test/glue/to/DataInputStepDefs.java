package com.hocs.test.glue.to;

import com.hocs.test.pages.decs.BasePage;
import com.hocs.test.pages.to.DataInput;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
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

    @And("I select an Addressee")
    public void iSelectAnAddressee() {
        dataInput.selectAnAdressee();
        clickTheButton("Continue");
    }
}
