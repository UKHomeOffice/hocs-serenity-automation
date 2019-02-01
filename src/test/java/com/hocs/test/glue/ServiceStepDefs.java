package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.api.ApiHelper;
import com.hocs.test.api.workflowService.WorkflowService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ServiceStepDefs {

    private ApiHelper apiHelper;

    private WorkflowService workflowService;

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
                System.out.println(service
                        + " is not defined within " + getClass().getSimpleName()
                        + " class, " + getMethodName() + " method");
                service = null;
                assumeNotNull(service);
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

