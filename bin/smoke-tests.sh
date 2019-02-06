#!/bin/sh

mvn clean verify -B "-Dcucumber.options=--tags @DCUMIN" -Dwebdriver.remote.url=http://selenium:4444/wd/hub -Dwebdriver.remote.driver=chrome