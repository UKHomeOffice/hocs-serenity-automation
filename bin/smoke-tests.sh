#!/bin/sh

mvn clean verify "-Dcucumber.options=--tags '@DCURegression or @MPAMRegression or @Regression'" -Dchrome.switches=--headless -Denvironment=QA