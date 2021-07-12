package com.hocs.test.glue.platform;

import com.hocs.test.pages.platform.AddCorrespondent;
import io.cucumber.java.en.And;

public class AddCorrespondentStepDefs {

    AddCorrespondent addCorrespondent;

    @And("I add a {string} correspondent")
    public void iAddAPublicCorrespondent(String correspondentType) {
        addCorrespondent.addAPublicCorrespondentOfType(correspondentType);
    }

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        addCorrespondent.addAMemberCorrespondent(name);
    }
}
