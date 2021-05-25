#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@DCURegression" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome -Dfailsafe.rerunFailingTestsCount=3