package com.hocs.test.pages.managementUI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.hocs.test.pages.Page;

public class UnitManagement extends Page {

    public void assertUnitManagementPageTitle() {
        assertThat($("//h1").getText(), is("Add Unit"));
    }

}
