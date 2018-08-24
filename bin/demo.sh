#!/bin/sh

env
mvn clean verify "-Dcucumber.options=--tags @demo"