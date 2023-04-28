#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@WCSRegression" -e
