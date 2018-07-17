package com.ndelius.test.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    public String commonName;
    public String lat;
    public String lon;
}
