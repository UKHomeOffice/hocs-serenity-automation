package com.hocs.test.pages.managementUI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;
import cucumber.api.java.en.When;

public class TeamManagement extends Page {

    public void assertTeamManagementPageTitle() {
        assertThat($("//h1").getText(), is("Team search"));
    }
}
