#!/bin/sh

mvn clean verify "-Dcucumber.options=--tags @critical"