package com.hocs.test.glue.decs;

import com.hocs.test.pages.decs.Correspondents;
import io.cucumber.java.en.And;

public class CorrespondentsStepDefs {

    Correspondents correspondents;

    @And("I add a {string} correspondent")
    public void iAddACorrespondent(String correspondentType) {
        if (correspondentType.equalsIgnoreCase("MEMBER")) {
            correspondents.addAMemberCorrespondent();
        } else {
            correspondents.addANonMemberCorrespondentOfType(correspondentType);
        }
    }

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        correspondents.addASpecificMemberCorrespondent(name);
    }

    @And("I confirm the primary correspondent")
    public void IConfirmThePrimaryCorrespondent(){
        correspondents.confirmPrimaryCorrespondent();
    }
}
