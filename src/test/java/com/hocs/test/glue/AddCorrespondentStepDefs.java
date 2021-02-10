package com.hocs.test.glue;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.Dashboard;
import com.hocs.test.pages.mpam.Creation;
import io.cucumber.java.en.And;

public class AddCorrespondentStepDefs {

    AddCorrespondent addCorrespondent;

    Dashboard dashboard;

    CreateCase createCase;

    Creation creation;

    @And("I add a public correspondent")
    public void iAddAPublicCorrespondent() {
        addCorrespondent.addAPublicCorrespondent();
    }

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        addCorrespondent.addAMemberCorrespondent(name);
    }
}
