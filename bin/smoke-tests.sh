#!/bin/sh

mvn clean verify -B "-Dcucumber.options=--tags '@SmokeTests'" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -e