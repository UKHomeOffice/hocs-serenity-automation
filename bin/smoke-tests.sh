#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@DCURegression or @UKVIRegression or @Regression " -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e