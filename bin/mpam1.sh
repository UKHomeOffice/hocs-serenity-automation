#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@MPAMRegression1" -e
