#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@test1" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome