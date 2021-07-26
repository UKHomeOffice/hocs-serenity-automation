package com.hocs.test.glue.wcs;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.api.ApiHelper;
import com.hocs.test.api.workflowService.WorkflowService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ServiceStepDefs {

    private ApiHelper apiHelper;

    private WorkflowService workflowService;

    @When("I request {string} info")
    public void whenIRequestServiceInfo(String service) {
        switch (service.toUpperCase()) {
            case "CASE SERVICE":
                apiHelper.setupApiHelper("CASE");
                break;
            case "WORKFLOW SERVICE":
                apiHelper.setupApiHelper("WORKFLOW");
                break;
            default:
                pendingStep(service + " is not defined within " + getMethodName());
        }
    }

    @Then("service returns a {int} response")
    public void apiWillReturnASuccessfulResponse(int statusCode) {
        apiHelper.assertResponse(statusCode);
    }

    @Then("the response body has the correct contents")
    public void theResponseBodyHasTheCorrectContents() {
        workflowService.assertResponseBody();
    }

    @Then("the response body contains {string}")
    public void theResponseBodyContains(String content) {
        apiHelper.assertResponseBodyContains(content);
    }

    @Then("{string} is equal to {string}")
    public void objectIsEqualTo(String object, String value) {
        apiHelper.assertResponseBodyJsonObject(object, value);
    }

}

