#!/bin/sh

mvn clean verify -B "-Dcucumber.options=--tags  @test" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -Dwebdriver
.remote.driver=chrome -e