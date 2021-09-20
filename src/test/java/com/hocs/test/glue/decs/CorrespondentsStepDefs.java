package com.hocs.test.glue.decs;

import com.hocs.test.pages.decs.Correspondents;
import io.cucumber.java.en.And;

public class CorrespondentsStepDefs {

    Correspondents correspondents;

    @And("I add a {string} correspondent")
    public void iAddAPublicCorrespondent(String correspondentType) {
        correspondents.addAPublicCorrespondentOfType(correspondentType);
    }

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        correspondents.addAMemberCorrespondent(name);
    }

    @And("I confirm the primary correspondent")
    public void IConfirmThePrimaryCorrespondent(){
        correspondents.confirmPrimaryCorrespondent();
    }
}
