package com.ndelius.test.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.pages.PageObject;
import org.json.JSONObject;


public class ApiHelper extends PageObject {

    private Response response;
    private RequestSpecification request;
    private Response post;

    public void assertStatusLog(int statusCode) {
        response.then().statusCode(statusCode);
        response.then().log().all();
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
}