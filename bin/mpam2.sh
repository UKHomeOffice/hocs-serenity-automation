#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@MPAMRegression2" -e
