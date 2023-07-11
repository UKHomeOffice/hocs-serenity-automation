#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@COMPE2ETests" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome
