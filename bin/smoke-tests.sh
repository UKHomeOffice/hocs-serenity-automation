#!/bin/sh

mvn clean verify -B -Dcucumber.filter.tags="@test1 or @test2 or @test3 or @test4 " -Dwebdriver.remote.url=http://zalenium:4444/wd/hub -e -Dwebdriver.remote.driver=chrome