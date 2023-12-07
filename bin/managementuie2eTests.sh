#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@ManagmentUI2eTests" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome
