package com.hocs.test.api;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;

import config.Environments;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.serenitybdd.core.pages.PageObject;
import org.json.JSONObject;

public class ApiHelper extends PageObject {

    private Response response;

    private RequestSpecification request;

    private Response post;

    private Headers headers;

    public void getInfo(String getInfo) {
        response = RestAssured.given().
                get(getInfo);
    }

    public void assertResponseBody() {
        response.then().
                statusCode(200).
                body("app.name", is("hocs-workflow"),
                        "app.fullname", is("Hocs Workflow Service"));
    }

    public void assertStatusLog(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void getResponseBody() {
        response.getBody();
    }

    public void getResponseContents() {
        response.then().log().body();
    }

    public void newStudent() {
        RestAssured.baseURI = "http://localhost:54801/api/student";
        request = RestAssured.given().header("auth", "Sh@r3dSe3cr3t");

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "Api"); // Cast
        requestParams.put("lastName", "Test");
        requestParams.put("year", "2");

        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toString());
    }

    public void postStudent() {
        // Post the request and check the response
        post = request.post("http://localhost:54801/api/student");
        post.then().statusCode(201);
//      post.then().assertThat().body("firstName", containsString ("Api"));
    }

    public void setupApiHelper(String api) {
        String environment = System.getProperty("environment");
        if (environment == null) {
            environment = "SR2";
        }

        switch (environment.toUpperCase()) {
            case "SR2":
                switch (api.toUpperCase()) {
                    case "ALFRESCO":
                        RestAssured.baseURI = Environments.DEV.getEnvironmentURL();
                        RestAssured.port = Integer.parseInt(Environments.DEV.getApiPort());
                        break;
                    case "USER MANAGEMENT TOOL":
                        RestAssured.baseURI = Environments.DEV.getEnvironmentURL();
                        break;
                    default:
                        fail(api
                                + " is not defined within ApiHelper, setupApiHelper method");
                        break;
                }
            case "TRAINING":
                switch (api.toUpperCase()) {
                    case "ALFRESCO":
                        RestAssured.baseURI = Environments.DEV.getEnvironmentURL();
                        RestAssured.port = Integer
                                .parseInt(Environments.DEV.getApiPort());
                        break;
                    case "USER MANAGEMENT TOOL":
                        RestAssured.baseURI = Environments.DEV.getEnvironmentURL();
                        break;
                    default:
                        fail(api
                                + " is not defined within ApiHelper, setupApiHelper method");
                        break;
                }
            default:
                fail(environment +
                        " is not defined within ApiHelper, setupApiHelper method");
        }

    }

    private void createAndSendRequest(String basePath, String requestType,
            String endpoint,
            @Nullable String contentType,
            @Nullable String requestBody,
            @Nullable String recordIdentifier) {
        if (basePath == null) {
            fail("The basePath passed into the createAndSendRequest method in the ApiHelper class was null.");
        } else {
            RestAssured.basePath = basePath;
        }

        switch (requestType.toUpperCase()) {
            case "GET":
                if (endpoint != null && recordIdentifier != null) {
                    response = given().headers(this.headers).contentType(contentType).when()
                            .get(endpoint + recordIdentifier)
                            .then()
                            .statusCode(200).extract()
                            .response();
                } else if (endpoint != null) {
                    response = given().headers(this.headers).contentType(contentType).when()
                            .get(endpoint).then()
                            .statusCode(200).extract()
                            .response();
                } else if (recordIdentifier == null) {
                    fail("The endpoint or record identifier is null when calling the createAndSendRequest method for a GET request.");
                }
                break;
            case "POST":
                if (requestBody == null || endpoint == null) {
                    fail("The request body " + requestBody + " or the endpoint " + endpoint
                            + " is null when calling the createAndSendRequest method for a POST request.");
                } else {
                    response = given().headers(this.headers).contentType(contentType)
                            .body(requestBody).when()
                            .post(endpoint).then().statusCode(200).extract().response();
                }
                break;
            case "PUT":
                if (requestBody == null || endpoint == null || recordIdentifier == null) {
                    fail("The request body " + requestBody + ", record identifier "
                            + recordIdentifier + " or endpoint " + endpoint
                            + " is null when calling the createAndSendRequest method for a PUT request.");
                } else {
                    response = given().headers(this.headers).contentType(contentType)
                            .body(requestBody).when()
                            .put(endpoint + recordIdentifier).then().statusCode(200).extract()
                            .response();
                }
                break;
            case "DELETE":
                if (endpoint == null || recordIdentifier == null) {
                    fail("The endpoint " + endpoint + " or record identifier " + recordIdentifier
                            + " is null when calling the createAndSendRequest method for a DELETE request.");
                } else {
                    response = given().headers(this.headers).when()
                            .delete(endpoint + recordIdentifier).then()
                            .statusCode(200).extract().response();
                }
                break;
            default:
                fail(requestType
                        + " is not defined within the ApiHelper class, createAndSendRequest method.");
        }
    }

    public void assertResponseStatusCodeIs(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void assertResponseBodyContains(String key, String value) {
        response.then().body(key, is(value));
    }

    private void setupListOfHeaders(List<config.Headers> headers) {
        List<Header> listOfHeaders = new ArrayList<>();
        Header h1;

        for (config.Headers header : headers) {
            h1 = new Header(header.getHeaderKey(), header.getHeaderValue());
            listOfHeaders.add(h1);
        }

        this.headers = new Headers(listOfHeaders);
    }

    public void setupHeadersForRequest(String listOfHeadersByName) {
        String[] list = listOfHeadersByName.split(",");
        List<config.Headers> listOfHeaders = new ArrayList<>();

        for (String headerName : list) {
            switch (headerName) {
                case "X_DOCREPOSITORY_REMOTE_USER":
                    listOfHeaders.add(config.Headers.X_DOCREPOSITORY_REMOTE_USER);
                    break;
                case "X_DOCREPOSITORY_REAL_REMOTE_USER":
                    listOfHeaders.add(config.Headers.X_DOCREPOSITORY_REAL_REMOTE_USER);
                    break;
                default:
                    fail(headerName
                            + " is not defined withing the ApiHelper class, addHeadersToAList method.");
            }
        }
        setupListOfHeaders(listOfHeaders);
    }

    public Response getResponse() {
        return response;
    }

    public void createAndSendARequestFor(String requestName) {
        switch (requestName.toUpperCase()) {
            case "TEST":
                setupApiHelper("User Management Tool");
                createAndSendRequest("", "", "", "", "", "");
                break;
            default:
                fail(requestName
                        + " is not defined within the ApiHelper class, createAndSendARequestFor method");
                break;
        }
    }

}

