#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@test1" -Dwebdriver.remote.url=http://zalenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome