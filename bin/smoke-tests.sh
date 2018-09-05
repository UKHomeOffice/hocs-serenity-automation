#!/bin/sh

mvn clean verify -B -o "-Dcucumber.options=--tags @critical" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -Dwebdriver.remote.driver=chrome