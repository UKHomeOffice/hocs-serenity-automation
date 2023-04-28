#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@POGRRegression" -e
