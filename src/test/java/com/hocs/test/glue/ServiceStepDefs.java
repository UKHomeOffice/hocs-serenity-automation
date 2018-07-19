package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.api.ApiHelper;
import com.hocs.test.api.workflow.WorkflowService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ServiceStepDefs {

    ApiHelper apiHelper;
    WorkflowService workflowService;

    @When("^I request \"([^\"]*)\" info$")
    public void whenIRequestServiceInfo(String service) {
        switch (service.toUpperCase()) {
            case "WORKFLOW SERVICE":
                workflowService.getInfo();
                break;
            default:
                fail(service + " is not defined within ServiceStepDefs.whenIRequestServiceInfo");
        }
    }

    @Then("API returns a (\\d+) response$")
    public void api_will_return_a_successful_response(int statusCode) {
        apiHelper.assertStatusLog(statusCode);
    }

}

