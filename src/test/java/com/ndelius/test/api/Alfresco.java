package com.ndelius.test.api;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class Alfresco {

    // Alfresco Schema

//    protected static int crcIndexNo = 2;
//    protected static String[] crcNameArr = {"CPA Northumbria", "CPA Cheshire and Greater Manchster", "CPA London"};
//    protected static String[] crcIdArr = {"C01", "C07", "C17"};
//    protected static String N00 = "N00";
//    protected static String crn = "XXXX";
//    protected static String crn2 = "XXXX";
//    protected static String crn3 = "XXXX";
//    protected static String documentID = "XXXX";
//    protected int random6 = DateTimeUtils.generateRandomNumber();

    // Alfresco param data
//    private static final int DEFAULT_INPUT_VALUES_INDEX = 0;
//    private static final int DEFAULT_NUMBER_OF_DOCUMENTS = 1;
//    private static final int SORT_NUM = 0;
//    private static final int DEFAULT_ROW = 1;
//    private static final String DEFAULT_CRC = "CPA London";
//    private static final String OPTION = "OFFENDER";

    public Response runSearchAPI(String caseReferenceNumber, int code, int sortNum) {

//      todo: URL hardcoded for SR2 Alfresco check. This needs to be parameterized - Ryan Smith 03/07/2018
        RestAssured.baseURI = "http://spg-alf-310/noms-spg/search/";

        List<String> sort = new ArrayList<>();
        sort.add("\b");                             //Default Search
        sort.add("sort=lastModified");              //1
        sort.add("sort=docType");                   //2
        sort.add("sort=entityType");                //3
        sort.add("sort=entityId");                  //4
        sort.add("sort=fileName");                  //5
        sort.add("sort=fileExtension");             //6
        sort.add("sort=fileSize");                  //7
        sort.add("sort=startIndex");                //8
        sort.add(
                "sort=abc");                       //9 Incorrect sort format for Testing purposes, API_Search_Syntax

//        sleep("M");

        Response response = given().
                header("X-DocRepository-Remote-User", "N00").
                header("X-DocRepository-Real-Remote-User", "nDelius01").
                when().
                get("{caseReferenceNumber}?" + sort.get(sortNum), caseReferenceNumber).
                then().
                statusCode(code).
                contentType(JSON).
                extract().response();

        System.out.println("Search API SUCCESSFUL");

        return response;
    }

}
