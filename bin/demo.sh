#!/bin/sh

mvn clean verify -B "-Dcucumber.options=--tags @critical"