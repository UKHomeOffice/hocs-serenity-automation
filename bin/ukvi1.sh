#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@UKVIRegression1" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome -Denvirionment=cs-qa
