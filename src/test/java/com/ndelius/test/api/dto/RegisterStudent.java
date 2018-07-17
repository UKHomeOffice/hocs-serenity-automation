package com.ndelius.test.api.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterStudent {

    private final List<Student> registerStudent;

    public RegisterStudent(Student[] students) {
        registerStudent = Arrays.asList(students);
    }

}