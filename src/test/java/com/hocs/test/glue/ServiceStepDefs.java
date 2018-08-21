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
            case "CASE SERVICE":
                apiHelper.setupApiHelper("CASE");
                break;
            case "WORKFLOW SERVICE":
                apiHelper.setupApiHelper("WORKFLOW");
                break;
            default:
                fail(service + " is not defined within ServiceStepDefs.whenIRequestServiceInfo");
        }
    }

    @Then("^service returns a (\\d+) response$")
    public void apiWillReturnASuccessfulResponse(int statusCode) {
        apiHelper.assertResponse(statusCode);
    }

    @Then("^the response body has the correct contents$")
    public void theResponseBodyHasTheCorrectContents() {
        workflowService.assertResponseBody();
    }

    @Then("^the response body contains \"([^\"]*)\" $")
    public void theResponseBodyContains(String content) {
        apiHelper.assertResponseBodyContains(content);
    }

    @Then("^\"([^\"]*)\" is equal to \"([^\"]*)\"$")
    public void objectIsEqualTo(String object, String value) {
        apiHelper.assertResponseBodyJsonObject(object, value);
    }

}

