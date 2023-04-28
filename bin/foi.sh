#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@FOIRegression" -e
