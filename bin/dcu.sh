#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@DCURegression" -e
